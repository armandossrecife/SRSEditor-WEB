/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.documento;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.dao.DicionarioDAO;
import br.gov.pi.tce.word.Convert;
import br.gov.pi.tce.word.ParagraphStyles;
import br.gov.pi.tce.word.PredefinedStyles;
import br.gov.pi.tce.word.Style;
import br.gov.pi.tce.word.Table;
import br.gov.pi.tce.word.TableCell;
import br.gov.pi.tce.word.Write;
import br.ufpi.dc.entidades.entity.CasoDeUso;
import br.ufpi.dc.entidades.entity.Introducao;
import br.ufpi.dc.entidades.entity.Isr;
import br.ufpi.dc.entidades.entity.Lemma;
import br.ufpi.dc.entidades.entity.Palavra;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.Referencia;
import br.ufpi.dc.entidades.entity.Tabela;
import br.ufpi.dc.entidades.entity.SinonimoDominio;
import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import br.ufpi.dc.tools.Constante;
import word.w2004.Document2004;
import word.w2004.elements.Paragraph;
import word.w2004.style.ParagraphStyle;

/**
 *
 * @author helcio.soares
 */
public class MakeDocSRS {

    private Document2004 document;
    private Projeto idProjeto;
    private DAO<Tabela> tabelaDAO = new DAO(Tabela.class);
    private Map<String, Object> params = new HashMap<String, Object>();
    private DAO<SinonimoDominio> sinonimoDominioDAO = new DAO(SinonimoDominio.class);
    private List<String> sinonimos;
    private HashMap<String, String> hashMapSinonimos;

    public MakeDocSRS(Projeto idProjeto) {
        this.idProjeto = idProjeto;

        new DocumentAbstract(DocumentAbstract.PORTRAIT, Constante.DOCUMENTOSESR + idProjeto.getDe() + ".doc") {
            @Override
            public void addContent(Document2004 doc) {
                document = doc;
                imprimirTitulo();
                imprimirIntroducao();
                imprimirUsuarioExternosEInterfaceDeUsuarios();
                imprimirRequisitosDeArmazenamento();
                imprimirFuncoesDoProduto();
                imprimirCasosDeUso();
                //imprimirDicionarioDeSinonimos();
            }
        };
    }

    public static void main(String[] args) throws IOException {
        DAO<Projeto> projetoDAO = new DAO(Projeto.class);
        Projeto projeto = projetoDAO.buscaPorId(1);
        new MakeDocSRS(projeto);
    }

    private void imprimirTitulo() {

        Table t = new Table(1, 1);
        t.setStyle(PredefinedStyles.StyleType.PageHeader);
        t
                .cell(0, 0)
                .type("<b>" + idProjeto.getDe() + "<b>").setHAlign(ParagraphStyle.Align.CENTER)
                .setVAlign(TableCell.VAlign.CENTER)
                .setColor(Color.LIGHT_GRAY);

        document.addEle(t.getContent());

    }

    private void imprimirIntroducao() {
        DAO<Introducao> introducaoDao = new DAO(Introducao.class);
        Introducao introducao = introducaoDao.buscaPorProjeto(idProjeto.getId());
        if(introducao!=null){
        ParagraphStyles ps = ParagraphStyles.getInstance();

        Paragraph paragrafo1 = Paragraph.with("Titulo1");
        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo1);
        paragrafo1.addTab(Paragraph.TabAlign.LEFT, Convert.centimeter2Twip(4.5), Paragraph.TabPattern.NONE);

        document.addEle(Write.type("<b>1.0 Introdução<b>", paragrafo1));

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>1.1 Objetivo<b>", paragrafo1));
        imprimirParagrafo(introducao.getObjetivo() == null ? " " : introducao.getObjetivo(), paragrafo1, PredefinedStyles.StyleType.Normal);

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>1.2 Escopo<b>", paragrafo1));
        imprimirParagrafo(introducao.getEscopo() == null ? " " : introducao.getEscopo(), paragrafo1, PredefinedStyles.StyleType.Normal);

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("\n<b>1.3 Visão geral<b>", paragrafo1));
        imprimirParagrafo(introducao.getVisaoGeral() == null ? " " : introducao.getVisaoGeral(), paragrafo1, PredefinedStyles.StyleType.Normal);
        imprimirReferencia(paragrafo1);

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>1.5 Definições e Siglas<b>", paragrafo1));

        imprimirTabelas(paragrafo1, Constante.CONCEITO, "C");
        }else{
            JOptionPane.showMessageDialog(null, "preencher os campos de introdução");
        }
    }

    private void imprimirReferencia(Paragraph paragrafo1) {
        DAO<Referencia> referenciaDAO = new DAO(Referencia.class);
        params.clear();
        params.put("idProjeto", this.idProjeto);

        List<Referencia> listaRerefencia = referenciaDAO.filtrarPorCampos(params, Constante.QUERYREFERENCIA);

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>1.4 Referências<b>", paragrafo1));

        Table t = new Table(listaRerefencia.size(), 3);
        for (int i = 0; i <= listaRerefencia.size() - 1; i++) {

            TableCell cNumero = t.cell(i, 0);
            cNumero.type("<b>REF-" + Constante.formatarNumero(i + 1) + "<b>");
            cNumero.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal).setAlign(ParagraphStyle.Align.CENTER);

            TableCell cNome = t.cell(i, 1);
            cNome.type(listaRerefencia.get(i).getDe() == null ? " " : listaRerefencia.get(i).getDe());
            cNome.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal);

            TableCell cDescricao = t.cell(i, 2);
            cDescricao.type(listaRerefencia.get(i).getNomeArquivo() == null ? " " : listaRerefencia.get(i).getNomeArquivo());
            cDescricao.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal);

        }
        if(listaRerefencia.size()!=0){
            t.addHeaderWidth(
                new String[]{"ID", "Descrição", "Arquivo"},
                new Double[]{-1.9, -11.1, -5.5});

            document.addEle(t.getContent());
        }else{
            JOptionPane.showMessageDialog(null, "insira a referência");
        }
    }

    private void imprimirUsuarioExternosEInterfaceDeUsuarios() {
        ParagraphStyles ps = ParagraphStyles.getInstance();

        Paragraph paragrafo1 = Paragraph.with("");
        paragrafo1.addTab(Paragraph.TabAlign.LEFT, Convert.centimeter2Twip(4.5), Paragraph.TabPattern.NONE);

        document.addEle(Write.type(" "));
        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo1);
        document.addEle(Write.type("<b>2.0 Descrição Geral do Produto<b>", paragrafo1));

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>2.1 Atores e Sistemas Externos<b>", paragrafo1));
        imprimirTabelas(paragrafo1, Constante.USUARIO, "U");

        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>2.2 Interfaces de Usuários<b>", paragrafo1));
        imprimirTabelas(paragrafo1, Constante.INTERFACE_USUARIO, "IU");
    }

    private void imprimirFuncoesDoProduto() {
        ParagraphStyles ps = ParagraphStyles.getInstance();

        Paragraph paragrafo1 = Paragraph.with("");
        paragrafo1.addTab(Paragraph.TabAlign.LEFT, Convert.centimeter2Twip(4.5), Paragraph.TabPattern.NONE);

        document.addEle(Write.type(" "));
        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>2.4 Funções do Produto<b>", paragrafo1));
        imprimirTabelas(paragrafo1, Constante.FUNCAODOPRODUTO, "FP");
    }

    private void imprimirRequisitosDeArmazenamento() {
        ParagraphStyles ps = ParagraphStyles.getInstance();

        Paragraph paragrafo1 = Paragraph.with("");
        paragrafo1.addTab(Paragraph.TabAlign.LEFT, Convert.centimeter2Twip(4.5), Paragraph.TabPattern.NONE);

        document.addEle(Write.type(" "));
        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>2.3 Requisitos de Armazenamento<b>", paragrafo1));

        DAO<Isr> isrDAO = new DAO(Isr.class);
        List<Isr> listaIsr = isrDAO.filtraPorProjeto(idProjeto);

        for (int i = 0; i <= listaIsr.size() - 1; i++) {
            Table t = new Table(2, 2);

            TableCell cDescricaoD = t.cell(0, 0);
            cDescricaoD.type("<b>Descrição<b>");
            cDescricaoD.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal).setAlign(ParagraphStyle.Align.LEFT);

            TableCell cDescricao = t.cell(0, 1);
            cDescricao.type(listaIsr.get(i).getDe() == null ? " " : listaIsr.get(i).getDe());
            cDescricao.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal).setAlign(ParagraphStyle.Align.LEFT);

            TableCell cNome = t.cell(1, 0);
            cNome.type("<b>Dados armazenados<b>");
            cNome.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal).setAlign(ParagraphStyle.Align.LEFT);

            imprimeTextoCelula(t, 1, 1, listaIsr.get(i).getDadosArmazenados() == null ? " " : listaIsr.get(i).getDadosArmazenados(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            t.addHeaderWidth(
                    new String[]{"RA" + (i + 1), listaIsr.get(i).getNome()},
                    new Double[]{-4.2, -13.8});

            document.addEle(t.getContent());
            document.addEle(Write.type(" "));
        }

    }

    private void imprimirCasosDeUso() {
        ParagraphStyles ps = ParagraphStyles.getInstance();

        Paragraph paragrafo1 = Paragraph.with("");
        paragrafo1.addTab(Paragraph.TabAlign.LEFT, Convert.centimeter2Twip(4.5), Paragraph.TabPattern.NONE);

        document.addEle(Write.type(" "));
        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo2);
        document.addEle(Write.type("<b>2.5 Casos de Uso<b>", paragrafo1));

        DAO<CasoDeUso> casoDeUsoDAO = new DAO(CasoDeUso.class);
        List<CasoDeUso> listacasoDeUsoDAO = casoDeUsoDAO.filtraPorProjeto(idProjeto);

        for (int i = 0; i <= listacasoDeUsoDAO.size() - 1; i++) {
            Table t = new Table(8, 2);
            // e aqui!
            imprimirCelula(t, 0, 0, "<b>Ator principal<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 0, 1, listacasoDeUsoDAO.get(i).getAtor(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 1, 0, "<b>Descrição<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 1, 1, listacasoDeUsoDAO.get(i).getDescricao(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 2, 0, "<b>Propósito<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 2, 1, listacasoDeUsoDAO.get(i).getProposito(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 3, 0, "<b>Pré-condição<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 3, 1, listacasoDeUsoDAO.get(i).getPreCondicao(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 4, 0, "<b>Pós-condição<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 4, 1, listacasoDeUsoDAO.get(i).getPosCondicao(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 5, 0, "<b>Fluxo principal<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 5, 1, listacasoDeUsoDAO.get(i).getFluxoPrincipal(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 6, 0, "<b>Fluxo alternativo<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 6, 1, listacasoDeUsoDAO.get(i).getFluxoAlternativo(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            imprimirCelula(t, 7, 0, "<b>Fluxo exceção<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, 7, 1, listacasoDeUsoDAO.get(i).getFluxoExcecao(), PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.JUSTIFIED);

            t.addHeaderWidth(
                    new String[]{"UC" + "-" + Constante.formatarNumero(i + 1), listacasoDeUsoDAO.get(i).getNome()},
                    new Double[]{-4.2, -13.8});

            document.addEle(t.getContent());
            document.addEle(Write.type(" "));
        }
    }

    private void imprimirDicionarioDeSinonimos() {
        ParagraphStyles ps = ParagraphStyles.getInstance();
        carregarDicionarioDeSinonimos();

        Paragraph paragrafo1 = Paragraph.with("");
        paragrafo1.addTab(Paragraph.TabAlign.LEFT, Convert.centimeter2Twip(4.5), Paragraph.TabPattern.NONE);

        document.addEle(Write.type(" "));
        paragrafo1.withStyle().setStyleType(PredefinedStyles.StyleType.Titulo1);
        document.addEle(Write.type("<b>3.0 Dicionário de sinonimos<b>", paragrafo1));
        document.addEle(Write.type(" "));
        Table t = new Table(sinonimos.size(), 2);

        for (int i = 0; i <= sinonimos.size() - 1; i++) {
            String sinonimo = sinonimos.get(i);
            String chaves = hashMapSinonimos.get(sinonimo);
            imprimirCelula(t, i, 0, "<b>" + sinonimo + "<b>", PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);
            imprimirCelula(t, i, 1, chaves, PredefinedStyles.StyleType.Normal, ParagraphStyle.Align.LEFT);

        }
            t.addHeaderWidth(
                    new String[]{"<b>Nome<b>", "<b>Sinonimos<b>"},
                    new Double[]{-4.2, -13.8});
            
        document.addEle(t.getContent());
        document.addEle(Write.type(" "));
    }

    private HashMap<String, String> carregarDicionarioDeSinonimos() {
        DicionarioDAO dicionarioDAO = new DicionarioDAO();
        hashMapSinonimos = new HashMap<>();
        params.clear();
        params.put("idProjeto", this.idProjeto);
        sinonimos = dicionarioDAO.filtrarSinonimosDistintos(params);

        for (int i = 0; i <= sinonimos.size() - 1; i++) {
            params.clear();
            params.put("idProjeto", this.idProjeto);
            params.put("sinonimo", sinonimos.get(i));
            List<SinonimoDominio> listaChaves = sinonimoDominioDAO.filtrarPorCampos(params, Constante.QUERYSINONIMOSINONIMO1);
            String temp = "";

            for (int m = 0; m <= listaChaves.size() - 1; m++) {
                SinonimoDominio sinonimoDominio = listaChaves.get(m);
                if (listaChaves.size() == 1) {
                    temp = sinonimoDominio.getConceitoChave().getDe() + ".";
                } else if (m == 0) {
                    temp = temp + sinonimoDominio.getConceitoChave().getDe();
                } else if (m < listaChaves.size() - 1) {
                    temp = temp + ", " + sinonimoDominio.getConceitoChave().getDe();
                } else {
                    temp = temp + " e " + sinonimoDominio.getConceitoChave().getDe() + ".";
                }
            }
            hashMapSinonimos.put(sinonimos.get(i), temp);
        }
        return hashMapSinonimos;
    }

    private void imprimirTabelas(Paragraph paragrafo1, Integer tipoTabela, String id) {
        List<Tabela> listaTabela = carregarTabela(tipoTabela);
        Table t = new Table(listaTabela.size(), 3);
        for (int i = 0; i <= listaTabela.size() - 1; i++) {

            TableCell cNumero = t.cell(i, 0);
            cNumero.type("<b>" + id + "-" + Constante.formatarNumero(i + 1) + "<b>");
            cNumero.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal).setAlign(ParagraphStyle.Align.CENTER);

            TableCell cNome = t.cell(i, 1);
            cNome.type(listaTabela.get(i).getNome() == null ? " " : listaTabela.get(i).getNome());
            cNome.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal);

            TableCell cDescricao = t.cell(i, 2);
            cDescricao.type(listaTabela.get(i).getDe() == null || listaTabela.get(i).getDe().equals("") ? " " : listaTabela.get(i).getDe());
            cDescricao.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal);

        }
        if(listaTabela.size()!=0){
            t.addHeaderWidth(
                new String[]{"ID", "Nome", "Descrição"},
                new Double[]{-1.5, -5.1, -11.1});

            document.addEle(t.getContent());
        }
    }

    private List<Tabela> carregarTabela(int tipoTabela) {
        params.clear();
        params.put("idProjeto", this.idProjeto);
        params.put("tipoTabela", tipoTabela);
        List<Tabela> listaTabela = tabelaDAO.filtrarPorCampos(params, Constante.QUERYTABELA);
        return listaTabela;
    }

    private void imprimirCelula(Table t, int linha, int coluna, String label, PredefinedStyles.StyleType styleType, ParagraphStyle.Align align) {
        TableCell c = t.cell(linha, coluna);
        String[] textoTemp = Constante.formatarString(label == null ? " " : label);
        for (int j = 0; j < textoTemp.length; j++) {
            c.type(textoTemp[j].equals("") ? " " : textoTemp[j]); //Aqui 
        }
        c.getParagraphs().get(0).withStyle().setStyleType(PredefinedStyles.StyleType.Normal).setAlign(ParagraphStyle.Align.LEFT);
    }

    private void imprimirParagrafo(String texto, Paragraph paragrafo1, PredefinedStyles.StyleType styleType) {
        paragrafo1.withStyle().setStyleType(styleType);
        String[] vectorTexto = Constante.formatarString(texto);
        for (int i = 0; i <= vectorTexto.length - 1; i++) {
            document.addEle(Write.type("     " + vectorTexto[i], paragrafo1));
            //document.addEle(Write.type("  "));
        }
    }

    private void imprimeTextoCelula(Table t, int i, int i0, String texto, PredefinedStyles.StyleType styleType, ParagraphStyle.Align align) {
        TableCell c = t.cell(1, 1);
        String[] textoTemp = Constante.formatarString(texto);
        for (int j = 0; j < textoTemp.length; j++) {
            c.type(textoTemp[j]);
        }
        c.getParagraphs().get(0).withStyle().setStyleType(styleType).setAlign(align);
    }

}
