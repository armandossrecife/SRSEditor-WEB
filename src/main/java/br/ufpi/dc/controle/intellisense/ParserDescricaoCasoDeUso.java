/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |
 Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.dao.DicionarioDAO;
//import visao.view.SRSJTree.Root;
//import visao.view.SRSJTree.TreeBuilder;
//import visao.view.SRSJTree.TreeProblemasNode;
import br.ufpi.dc.controle.analiseTexto.AnalisePeriodo;
import br.ufpi.dc.entidades.entity.Atributo;
import br.ufpi.dc.entidades.entity.Isr;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.Tabela;
import br.ufpi.dc.entidades.entity.tools.Sinonimo;
import br.ufpi.dc.controle.grammar.tools.UnderlineListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import br.ufpi.dc.tools.Constante;
//import visao.view.CasoDeUsoView;
//import visao.view.Editor;
//import visao.view.FluxoCasoDeUsoView;

/**
 *
 * @author helcio.soares
 */
public class ParserDescricaoCasoDeUso extends AbstractParser {

    private DAO<Sinonimo> sinonimoDAO = new DAO(Sinonimo.class);
    private Projeto projetoSelecionado;
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private AnalisePeriodo analisePeriodo;
    private ArrayList<Integer> indiceFrase = new ArrayList<Integer>();
    public static String frase;
    private ArrayList<String> elementosDaFrase;
    private HashMap<String, ElementoFrase> hashElementosFrase = new HashMap<>();
    private Queue pilhaInterface = new LinkedList();
    TreeBuilder treeBuilder;
    private String ultimoProximo;
    public static String novaFrase = "";
    private HashMap<String, ArrayList<String>> eHIsr;
    ArrayList<String> classes;
    private HashSet classesNaoCitada = new HashSet();

    public ParserDescricaoCasoDeUso(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
        treeBuilder = new TreeBuilder(dicionarioDAO, projetoSelecionado);
    }

    @Override
    public DefaultListModel avaliaFrase(String frase) {
        String fraseOriginal = frase;

        DefaultListModel listIntellisense = new DefaultListModel();
        String proximo = "";
        try {
            int i1 = frase.length();
            while (i1 > 0 && frase.charAt(i1 - 1) != '\n') {
                i1--;
            }
            if (i1 > 0) {
                frase = frase.substring(i1, frase.length());
            }

            this.frase = frase;
            Editor.jTextArea1.setText("");
            analisePeriodo = new AnalisePeriodo(frase);
            UnderlineListener.erroList.clear();
            analisePeriodo.ultimoProximoUC = this.ultimoProximo;
            proximo = analisePeriodo.sentencaUC(frase);

            indiceFrase = montaIndice(this.frase);
            listIntellisense = new DefaultListModel();
            listIntellisense = retornaListaConformeProximo(proximo, listIntellisense);
            novaFrase = montarSentencaExibirCampos(frase);

            if (frase != novaFrase) {
                int i = FluxoCasoDeUsoView.mapTextAtrea.indexOf(CasoDeUsoView.textAreaSelecionado);
                String novoTexto = FluxoCasoDeUsoView.mapTextAtrea.get(i).getText().replaceAll(frase, novaFrase);
                FluxoCasoDeUsoView.mapTextAtrea.get(i).setText(novoTexto);
                return new DefaultListModel();
            }

        } catch (Exception ex) {
            Logger.getLogger(ParserDescricaoCasoDeUso.class.getName()).log(Level.SEVERE, null, ex);
            listIntellisense = null;
        }
        return listIntellisense;
    }

    private String montarSentencaExibirCampos(String frase) throws Exception {
        String temp = frase;
        int j = analisePeriodo.ucParser.elementosDaFrase.tipoElemento.indexOf("VERBO");
        String verbo = j >= 0 ? analisePeriodo.ucParser.elementosDaFrase.elemento.get(j) : "";
        String verboOriginal = verbo;

        if (!"".equals(verbo)) {
            verbo = AnalisePeriodo.getLemmas().get(analisePeriodo.ultimoVerbo);
            Sinonimo sinonimo;
            sinonimo = sinonimoDAO.buscaPorDe(verbo, null);
            verbo = sinonimo != null ? sinonimo.getIdVerbo().getDe() : verbo;
            if ((verbo.equals("digitar") || verbo.equals("exibir"))
                    && analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(j + 1).equals("PALRES")) {
                String complementoVerbo = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j + 1);

                if (complementoVerbo.contains("dados")) {
                    String classe = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j + 2);
                    String lemmaClasse = Constante.recuperarLemmaDaPalavra(classe);
                    DAO<Isr> isrDAO = new DAO(Isr.class);
                    Isr isr = isrDAO.buscaPorNomeLemma(lemmaClasse, null);

                    if (isr != null) {
                        DAO<Atributo> atributoDAO = new DAO(Atributo.class);
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("idProjeto", projetoSelecionado);
                        params.put("isr", isr);
                        List<Atributo> atributos = atributoDAO.filtrarPorCampos(params, Constante.QUERYATRIBUTOISR);
                        temp = atributos.isEmpty() ? temp : frase.substring(0, frase.indexOf(verboOriginal) + verboOriginal.length() + 1) + "os campos";

                        for (int m = 0; m <= atributos.size() - 1; m++) {
                            Atributo atributo = atributos.get(m);
                            if (m == 0) {
                                temp = temp + " " + atributo.getNome();
                            } else if (m < atributos.size() - 1) {
                                temp = temp + ", " + atributo.getNome();
                            } else {
                                temp = temp + " e " + atributo.getNome() + ".";
                            }
                        }
                    }
                }
            }
        }
        return temp;
    }

    private DefaultListModel retornaListaConformeProximo(String proximo, DefaultListModel listIntellisense) {

        String verbo;
        String ultimaPalavra = analisePeriodo.ultimoVerbo;
        int j = analisePeriodo.ucParser.elementosDaFrase.tipoElemento.indexOf("VERBO");
        int t = analisePeriodo.ucParser.elementosDaFrase.tipoElemento.size();
        String complementoDoVerbo = "";
        if (j < t - 1 && analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(j + 1).equals("PALRES")) {
            complementoDoVerbo = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j + 1);
        }
        verbo = AnalisePeriodo.getLemmas().get(analisePeriodo.ultimoVerbo);
        Sinonimo sinonimo;
        sinonimo = sinonimoDAO.buscaPorDe(verbo, null);
        List<String> listaOpcoes = new ArrayList<>();

        try {
            if (sinonimo != null && complementoDoVerbo.equals("")) { //Se for um verbo da base de verbos
                listaOpcoes = retornaOpcoesVerbo(sinonimo);
            } //else {
            listaOpcoes.addAll(retornaOpcoesFuncoes(ultimaPalavra));
            if (!listaOpcoes.isEmpty()) {
                proximo = "confVerbo";
            }
            ///      }
            if (proximo.contains("confVerbo") /*&& listaOpcoes.size() == 0*/) {
                if (sinonimo != null) {
                    proximo = sinonimo.getIdVerbo().getProx();
                    if (proximo.equals("tratarVerbo")) {
                        verbo = sinonimo.getIdVerbo().getDe();
                        proximo = tratarVerbo(verbo, complementoDoVerbo);
                    }
                } else {
                    if (Constante.verbosDeLigacao.contains(verbo)) {
                        proximo = "operador_logico";
                    } else {
                        proximo = "";
                    }
                }
            }
        } catch (Exception e) {
        }
        listaOpcoes.addAll(recuperaListaConformeProximo(proximo));

        listIntellisense = listToDefaultListModel(listaOpcoes);
        this.ultimoProximo = proximo;
        return listIntellisense;
    }

    private String tratarVerbo(String verbo, String complemento) {

        String proximo = "";
        if (verbo.equals("exibir")) {
            if (complemento.contains("os dados")) {
                proximo = "classe";
            } else if (complemento.contains("campos d")) {
                proximo = "classe";
            } else if (complemento.contains("campo")) {
                proximo = "atributo";
            } else {
                proximo = "interface";
            }
            //também pode ser um campo, mas ainda nao encontrei um padrão para isso.
            // se estiver em um contexto de pesquisa, pode-se sugerir "o resultado".
        }

        if (verbo.equals("inserir")) {
            if (this.frase.toLowerCase().contains("os dados")) {
                proximo = "classe";
            } else {
                proximo = "atributo";
            }
        }

        if (verbo.equals("selecionar")) {
            if (complemento.contains("a opção")
                    || this.frase.toLowerCase().contains("o comando")
                    || this.frase.toLowerCase().contains("o botão")) {
                proximo = "elemento_de_interface"; //Falta implementar: aprender enquanto o susário digita
            } else if (complemento.contains("campo")) {
                proximo = "atributo";
            } else if (complemento.contains("os dados")) {
                proximo = "classe";
            }
        }

        if (verbo.equals("executar")) {
            if (complemento.equals("comando")) {
                proximo = "elemento_de_interface";
            } else if (complemento.contains("caso de uso")) {
                proximo = "caso_de_uso";
            } else {
                proximo = "";
            }
        }
        if (verbo.equals("clicar") || verbo.equals("pressionar") || verbo.equals("escolher")) {
            if (complemento.equals("botão")
                    || complemento.equals("opção")
                    || complemento.equals("comando")) {
                proximo = "elemento_de_interface";
            } else if (verbo.equals("escolher")) {
                proximo = "atributo";
            } else {
                proximo = "";
            }
        }

        return proximo;
    }

    // expressão depois do verbo
    private String retornaTipoProximo(String verbo, String palavrasReservadas) {

        String proximo = "";
        if (verbo.equals("exibir")) {
            if (palavrasReservadas.contains("os dados")) {
                proximo = "classe";
            } else if (palavrasReservadas.contains("campos d")) {
                proximo = "classe";
            } else if (palavrasReservadas.contains("campo")) {
                proximo = "atributo";
            }
            if (palavrasReservadas.contains("tela")) {
                proximo = "interface";
            }
            //também pode ser um campo, mas ainda nao encontrei um padrão para isso.
            // se estiver em um contexto de pesquisa, pode-se sugerir "o resultado".
        }

        if (verbo.equals("inserir")) {
            if (palavrasReservadas.contains("os dados")) {
                proximo = "classe";
            } else {
                proximo = "atributo";
            }
        }

        if (verbo.equals("selecionar")) {
            if (palavrasReservadas.contains("a opção")
                    || palavrasReservadas.contains("o comando")
                    || palavrasReservadas.contains("o botão")) {
                proximo = "elemento_de_interface"; //Falta implementar: aprender enquanto o susário digita
            } else if (palavrasReservadas.contains("campo")) {
                proximo = "atributo";
            } else if (palavrasReservadas.contains("os dados")) {
                proximo = "classe";
            }
        }

        if (verbo.equals("executar")) {
            if (palavrasReservadas.contains("comando")) {
                proximo = "elemento_de_interface";
            } else if (palavrasReservadas.contains("caso de uso")) {
                proximo = "caso_de_uso";
            } else {
                proximo = "";
            }
        }
        if (verbo.equals("clicar") || verbo.equals("pressionar") || verbo.equals("escolher")) {
            if (palavrasReservadas.contains("botão")
                    || palavrasReservadas.contains("opção")
                    || palavrasReservadas.contains("comando")) {
                proximo = "elemento_de_interface";
            } else if (verbo.equals("escolher")) { //alterar
                proximo = "atributo";
            } else {
                proximo = "";

            }
        }

        if (verbo.equals("abrir") || verbo.equals("fechar")) {
            proximo = "interface";
        }
        if (verbo.equals("alterar")) {
            proximo = "atributo";
        }
        if (verbo.equals("marcar")) {
            proximo = "elemento_de_interface";
        }
        return proximo;
    }

    public Root validarSentenca(String descricaoCasoDeUso) throws Exception {

        ArrayList<DefaultMutableTreeNode> noTemp = new ArrayList<DefaultMutableTreeNode>();
        ArrayList<DefaultMutableTreeNode> raizesFluxos = new ArrayList<DefaultMutableTreeNode>();
        raizesFluxos.add(new DefaultMutableTreeNode(new TreeProblemasNode(Constante.CONCEITO1, "Fluxo principal", null)));
        raizesFluxos.add(new DefaultMutableTreeNode(new TreeProblemasNode(Constante.CONCEITO1, "Fluxo alternativo", null)));
        raizesFluxos.add(new DefaultMutableTreeNode(new TreeProblemasNode(Constante.CONCEITO1, "Fluxo de exceção", null)));
        DefaultMutableTreeNode raizA = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.RAIZ_, Constante.RAIZ, null));

        DefaultMutableTreeNode raizE = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Erro", null));
        DefaultTreeModel treeModel = null;
        elementosDaFrase = new ArrayList<String>();
        eHIsr = new HashMap<>();
        //       elementosFrase.
        String[] fluxo = descricaoCasoDeUso.split("#");
        int numeroFluxo = 0;
        Editor.jTreeErros.removeAll();

        treeModel = new DefaultTreeModel(raizE);
        for (String fluxo1 : fluxo) {
            DefaultMutableTreeNode raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Erro", null));
            DefaultMutableTreeNode raizAlerta = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Alerta", null));
            ArrayList<String> sentencasUC = construirSentencas(fluxo1);
            for (String sentencaUC : sentencasUC) {
                if (!sentencaUC.trim().equals("")) {
                    analisarSentenca(sentencaUC, raizErro, raizAlerta, numeroFluxo);
                    //addConceitos(sentencaUC); //2
                }
            }
            //elementosDaFrase = Constante.trocarPorListaDeLemma(elementosDaFrase);
            elementosDaFrase = Constante.eliminarRepetidosPorLemma(elementosDaFrase, 1);
            elementosDaFrase = Constante.trocarPorsinonimo(elementosDaFrase, 1);

            if (raizErro.getChildCount() != 0) {
                int n = raizErro.getChildCount();
                for (int i = 0; i <= n - 1; i++) {
                    DefaultMutableTreeNode neto = (DefaultMutableTreeNode) raizErro.getChildAt(0);
                    raizesFluxos.get(numeroFluxo).add(neto);
                }

            }
            if (raizAlerta.getChildCount() != 0) {
                int n = raizAlerta.getChildCount();
                for (int i = 0; i <= n - 1; i++) {
                    DefaultMutableTreeNode child = (DefaultMutableTreeNode) raizAlerta.getChildAt(0);
                    raizesFluxos.get(numeroFluxo).add(child);
                }
            }
            numeroFluxo++;
        }
        for (Iterator<DefaultMutableTreeNode> it = raizesFluxos.iterator(); it.hasNext();) {
            DefaultMutableTreeNode raizFluxo = it.next();
            if (raizFluxo.getChildCount() != 0) {
                raizE.add(raizFluxo);
            }
        }

        DefaultTreeModel treeModelErros = new DefaultTreeModel(raizE);

        //Transformar isso em um método
        ArrayList<String> elementoDaFraseTemp = new ArrayList(elementosDaFrase);

        for (String elementoDaFrase : elementoDaFraseTemp) {
            ElementoFrase e = null;
            if (eHIsr.get(elementoDaFrase) != null) {
                e = hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + elementoDaFrase);
                if (e == null) {
                    elementosDaFrase.remove(elementoDaFrase);
                }
            }
        }

        //Verificar se existem classes que tem classes como atributos (e o atributo aparece no caso de uso) e a classe atributo
        //não aparece como atributo da classe pai.
        for (Map.Entry<String, ElementoFrase> entrySet : hashElementosFrase.entrySet()) {
            String keyClassePai = entrySet.getKey();
            ElementoFrase classePai = entrySet.getValue();
            if (classePai.getTipoElemento() == Constante.REQUISITOS_DE_ARMAZENAMENTO) {
                for (Map.Entry<String, ElementoFrase> entrySet1 : hashElementosFrase.entrySet()) {
                    String keyClasseFilho = entrySet1.getKey();
                    ElementoFrase classeFilho = entrySet1.getValue();
                    if (classeFilho.getTipoElemento() == Constante.REQUISITOS_DE_ARMAZENAMENTO && !keyClassePai.equals(keyClasseFilho)) {
                        List<String> classesPai = dicionarioDAO.recuperarClassesAtributo(classeFilho.getNome(), projetoSelecionado.getId());
                        if (classesPai.contains(classePai.getNome())) {
                            if (classePai.getIndexOf(classeFilho.getNome()) == -1) {
                                Tabela t = new Tabela();
                                t.setNome(classeFilho.getNome());
                                t.setNomeLemma(classeFilho.getNome());
                                t.setIdTipoTabela(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                                classePai.addFilho(t);
                                hashElementosFrase.put(keyClassePai, classePai);
                            }
                        }
                    }
                }
            }
        }

        // Verificar se o elemento é uma  classe e não foi citado e só tem um atributo citado no caso de uso e esse atributo é uma classe então excluir esse elemento.
        ArrayList<String> elementosParaExcluir = new ArrayList();

        for (Map.Entry<String, ElementoFrase> entrySet : hashElementosFrase.entrySet()) {
            String classe = entrySet.getKey();
            ElementoFrase elementoDaFrase = entrySet.getValue();
            String nomeFilho = elementoDaFrase.getFilhos() != null && elementoDaFrase.getFilhos().size() > 0 ? elementoDaFrase.getFilhos().get(0).getNome() : "";
            if (elementoDaFrase.getTipoElemento() == Constante.REQUISITOS_DE_ARMAZENAMENTO && classesNaoCitada.contains(elementoDaFrase.getNome())
                    && elementoDaFrase.getFilhos().size() == 1 && hashElementosFrase.containsKey(Constante.REQUISITOS_DE_ARMAZENAMENTO + nomeFilho)) {
                elementosParaExcluir.add(elementoDaFrase.getNome());
            }
        }

        for (String elementoParaExcluir : elementosParaExcluir) {
            hashElementosFrase.remove(Constante.REQUISITOS_DE_ARMAZENAMENTO + elementoParaExcluir);
            elementosDaFrase.remove(elementoParaExcluir);
        }

        noTemp = treeBuilder.construirListaAvisos(elementosDaFrase, hashElementosFrase);
        raizA.add(noTemp.get(0));
        DefaultMutableTreeNode raizExplorar = noTemp.get(1);

        DefaultTreeModel treeModelAvisos = new DefaultTreeModel(raizA);
        DefaultTreeModel treeModelExplorar = new DefaultTreeModel(raizExplorar);

        treeBuilder.desenharRaiz(treeModelErros, treeModelAvisos, treeModelExplorar);
        Root raiz = new Root(treeModelErros, treeModelAvisos, treeModelExplorar);
        return raiz;

    }

    private String retirarAspas(String erro) {
        String retorno = "";
        String palavraErrada = "";
        int i = 0;
        int count = 0;
        while (i < erro.length()) {
            if (erro.charAt(i) == '\'') {
                count++;
                i++;
            }
            if ((count % 2) == 0) {
                if (i < erro.length()) {
                    retorno = retorno + erro.charAt(i);
                }
            } else {
                palavraErrada = palavraErrada + erro.charAt(i);
            }
            i++;
        }
        return retorno + "#" + palavraErrada;
    }

    private List<String> transformaTerceiraPessoa(List<String> listIntellisense) {
        List<String> listTemp = new ArrayList<>();
        for (int i = 0; i < listIntellisense.size(); i++) {
            String verbo = listIntellisense.get(i).toString();
            verbo = transformaVerboEmTerceiraPessoa(verbo);
            listTemp.add(i, verbo);

        }
        return listTemp;
    }

    private String transformaVerboEmTerceiraPessoa(String verbo) {
        String conjug = verbo.substring(verbo.length() - 2, verbo.length());
        verbo = verbo.substring(0, verbo.length() - 2);
        if (conjug.equals("ar")) {
            verbo = verbo + "a";
        } else {
            verbo = verbo + "e";
        }
        return verbo;
    }

    private String transformaTerceiraPessoaEmVerbo(String palavra, String retorno) {
        String conjug = palavra.substring(palavra.length() - 1, palavra.length());
        palavra = palavra.substring(0, palavra.length() - 1);
        if (conjug.equals("a")) {
            palavra = palavra + "ar";
        } else {
            palavra = palavra + retorno;
        }
        return palavra;
    }

    private ArrayList<Integer> montaIndice(String complemento) {
        indiceFrase.clear();

        for (int i = complemento.length() - 1; i >= 0; i--) {
            int i1 = i - 1;

            if (i1 > -1 && complemento.charAt(i - 1) == ' ' && (complemento.charAt(i) != ' ')) {
                indiceFrase.add(i);
            }
            if (i == 0 && complemento.charAt(i) != ' ') {
                indiceFrase.add(i);
            }
        }
        //String temp = retornaFrase(complemento, indiceComplemento.get(0));
        return indiceFrase;
    }

    private String retornaFrase(String complemento, Integer indice) {
        try {
            return complemento.substring(indiceFrase.get(indice - 1), complemento.length());

        } catch (Exception e) {
            return "";
        }
    }

    private List<String> retornaOpcoesFuncoes(String verbo) {
        List<String> listIntellisenseTemp = new ArrayList<>(); //Verificar 
        //Procurar os verbos da base de funcionalidades
        String conjug = verbo.substring(verbo.length() - 1, verbo.length());
        if (conjug.equals("a")) {
            verbo = transformaTerceiraPessoaEmVerbo(verbo, "");
            listIntellisenseTemp = retornaOpcoesFuncionalidades(verbo, projetoSelecionado.getId());
            if (listIntellisenseTemp.isEmpty()) {
                if ("ei".contains(conjug)) {
                    verbo = transformaTerceiraPessoaEmVerbo(verbo, "e");
                    listIntellisenseTemp = retornaOpcoesFuncionalidades(verbo, projetoSelecionado.getId());
                    if (listIntellisenseTemp.isEmpty()) {
                        verbo = transformaTerceiraPessoaEmVerbo(verbo, "i");
                        listIntellisenseTemp = retornaOpcoesFuncionalidades(verbo, projetoSelecionado.getId());
                    }
                }
            }
        }
        return listIntellisenseTemp;
    }

    private ArrayList<String> construirSentencas(String descricaoCasoDeUso) {
        ArrayList<String> sentencasUC = new ArrayList<>();
        String sentenca = "";
        boolean ok = true;
        boolean sentencaSE = false;
        analisePeriodo = new AnalisePeriodo(descricaoCasoDeUso);

        for (int i = 0; i <= descricaoCasoDeUso.length(); i++) {
            if (i == descricaoCasoDeUso.length() || descricaoCasoDeUso.charAt(i) == '\n') {
                sentencasUC.add(sentenca);
                sentenca = "";
                ok = true;
                continue;
            }

            if (ok && Character.isAlphabetic(descricaoCasoDeUso.charAt(i))) {
                String se = descricaoCasoDeUso.substring(i, i + 3);
                if (se.trim().toLowerCase().equals("se")) {
                    sentencaSE = true;
                }
                ok = false;
            }
            sentenca = sentenca + descricaoCasoDeUso.charAt(i);
        }
        return sentencasUC;
    }

    private void analisarSentenca(String sentencaUC, DefaultMutableTreeNode raizE, DefaultMutableTreeNode raizA, int fluxo) throws Exception {
        System.out.println(sentencaUC);
        Editor.jTextArea1.setText("");
        UnderlineListener.erroList.clear();
        analisePeriodo.sentencaUC(sentencaUC);
        String linha = analisePeriodo.numeroSentenca;

        //Início da construção de erros sintáticos.
        DefaultMutableTreeNode treeNodeErro = null;
        if (linha.contains("<")) {
            treeNodeErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "A sentença '" + sentencaUC + "' não está numerada.", null));
            raizE.add(treeNodeErro);
            return;
        }
        analisePeriodo.ucParser.elementosDaFrase.trocarPorSinonimos();
        if (analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(1).equals("SE")) {
            analisePeriodo.tipoFrase = 2;
        }
        switch (analisePeriodo.tipoFrase) {
            case 0: {
                //Sentença incompleta 
                treeNodeErro = analisarSentencaIncompletaTipo0(raizE, linha, fluxo);
                break;
            }
            case 1: {
                //Ação do usuário
                treeNodeErro = analisarSentencaAcaoUsuarioTipo1(raizE, raizA, linha, fluxo);
                break;
            }
            case 2: {
                //Sentença condicional SE
                treeNodeErro = analisarSentencaSETipo2(raizE, raizA, linha, fluxo);
                break;
            }
            case 3: {
                //SENAO
                return;
            }
            case 4: {
                //PARA
                treeNodeErro = analisarSentencaPARATipo4(raizE, raizA, linha, fluxo);
                break;
            }
            case 5: {
                //PARA
                treeNodeErro = analisarSentencaSETipo2(raizE, raizA, linha, fluxo);
                break;
            }

        }
        //Início da construção de erros semanticos.
        if (treeNodeErro == null && analisePeriodo.tipoFrase != 2
                && analisePeriodo.tipoFrase != 4
                && analisePeriodo.tipoFrase != 5) {
            //Testar objetos
            //Verificar se os objetos dos verbos são adequados, ou seja se depois do verbo abrir nao tem algo que nao seja uma interface de usuário
            verificaAtor(raizA, linha, "");
            verificaComplementos(raizA, linha, "", "", "");
        }
        for (String temp1 : analisePeriodo.elementosFrase) {
            if (!elementosDaFrase.contains(temp1)) {
                elementosDaFrase.add(temp1);
            }
        }
    }

    private DefaultMutableTreeNode analisarSentencaPARATipo4(DefaultMutableTreeNode raizE, DefaultMutableTreeNode raizA, String linha, int fluxo) throws Exception {
        DefaultMutableTreeNode treeNode = null;

        for (String elementoDafrase : analisePeriodo.elementosFrase) {
            if (verificarClassificacao(elementoDafrase).isEmpty() && !Constante.substantivosParaIgnorar.contains(elementoDafrase)) {
                treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". O termo '" + elementoDafrase + "' não foi classificado", null));
                raizA.add(treeNode);
            }
        }
        return treeNode;
    }

    private DefaultMutableTreeNode analisarSentencaIncompletaTipo0(DefaultMutableTreeNode raizE, String linha, int fluxo) {
        DefaultMutableTreeNode treeNode = null;
        //raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.FORA_DO_PADRAO_, Constante.FORA_DO_PADRAO));
        if (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.contains("VERBO")) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Sentença incompleta: Ação não encontrada.", null));
            raizE.add(treeNode);
        } else if (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.contains("CCO1")) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Sentença incompleta: Objeto não encontrado.", null));
            raizE.add(treeNode);
        }
        return treeNode;
    }

    private DefaultMutableTreeNode analisarSentencaAcaoUsuarioTipo1(DefaultMutableTreeNode raizE, DefaultMutableTreeNode raizA, String linha, int fluxo) {
        DefaultMutableTreeNode treeNode = null;
        if (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.contains("CCO1")) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Sentença incompleta: Objeto não encontrado.", null));
            raizE.add(treeNode);
            return treeNode;
        }
        if (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.contains("SUJ")) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Sentença incompleta: Ator não encontrado.", null));
            raizE.add(treeNode);
        }
        if (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.contains("VERBO")) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Sentença incompleta: Ação não encontrada.", null));
            raizE.add(treeNode);
        } else {
            // Verifica se o verbo é executar
            int j = analisePeriodo.ucParser.elementosDaFrase.tipoElemento.indexOf("VERBO");
            String verbo = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j);
            String verboOriginal = verbo;
            verbo = AnalisePeriodo.getLemmas().get(verbo);
            Sinonimo sinonimo = sinonimoDAO.buscaPorDe(verbo, null);
            verbo = (sinonimo == null ? verbo : sinonimo.getIdVerbo().getDe());
            String oCasoDeUso = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j + 1);
            int qtdVerbo = analisePeriodo.ucParser.elementosDaFrase.verbos.size();

            if ((!verbo.equals("executar") && (qtdVerbo > 1) && !verbo.equals("validar")) || (verbo.equals("executar") && (oCasoDeUso.toLowerCase().equals("o caso de uso")) && (qtdVerbo > 2))) {
                treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ": A sentença contém mais de uma ação.", null));
                raizE.add(treeNode);
            }

            String palavra = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j + 1);
            if (palavra.toLowerCase().equals("se")) {
                if (verbo.toLowerCase().equals("validar")) {
                    treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ": Para maior clareza, trocar '" + verboOriginal + " se' por 'valida que'.", null));
                    raizA.add(treeNode);
                } else {
                    treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ": É recomendado o uso da sentença 'se' apenas nos fluxos alternativo ou de exceção", null));
                    raizA.add(treeNode);
                }
            }

        }
        return treeNode;
    }

    //Analisar sentença SE
    private DefaultMutableTreeNode analisarSentencaSETipo2(DefaultMutableTreeNode raizE, DefaultMutableTreeNode raizA, String linha, int fluxo) throws Exception {
        DefaultMutableTreeNode treeNode = null;

        if (fluxo == 0 && analisePeriodo.tipoFrase == 2) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". É recomendado o uso da sentença condicional apenas nos fluxos alternativo ou de exceção!", null));
            raizA.add(treeNode);
        }

        if (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.contains("ENTAO") && analisePeriodo.tipoFrase == 2) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Sentença incompleta: ENTÃO não encontrado.", null));
            raizE.add(treeNode);
        }

        int i = analisePeriodo.ucParser.elementosDaFrase.contar("VERBSE");
        int j = analisePeriodo.ucParser.elementosDaFrase.contar("EOU");
        if ((i < 0) || (j >= 0 && i != j + 1)) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Sentença " + linha + ". Cada expressão lógica da sentença condicional deve possuir um verbo.", null));
            raizE.add(treeNode);
        }

        //Verificar se cada verbo contem atores e objetos
        ArrayList<String> verbosSentencaSE = analisePeriodo.ucParser.elementosDaFrase.recuperaElementos("VERBSE");
        for (int k = 0; k <= verbosSentencaSE.size() - 1; k++) {
            int pos = analisePeriodo.ucParser.elementosDaFrase.posVerbo.get(k);
            String verbo = analisePeriodo.ucParser.elementosDaFrase.verbos.get(k);
            String lemmaVerbo = AnalisePeriodo.getLemmas().get(verbo);
            boolean faltaE1 = true;
            boolean faltaE2 = true;
            try {
                faltaE1 = !analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos - 1).equals("E1");
            } catch (Exception e) {
            }
            try {
                faltaE2 = (!analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 1).equals("ASPAS")
                        && !analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 2).equals("ASPAS"));

                if (faltaE2) {
                    faltaE2 = !(analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 1).equals("E2")
                            || analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 2).equals("E2"));
                }

            } catch (Exception e) {
            }

            if (!verbo.equals("existir")) {
                String mensagemNaoTemE1;
                String mensagemNaoTemE2;
                if (Constante.verbosDeLigacao.contains(lemmaVerbo)) {
                    mensagemNaoTemE1 = "Sentença " + linha + ". O verbo '" + verbo + "' necessita de dois operandos";
                    mensagemNaoTemE2 = "Sentença " + linha + ". O verbo '" + verbo + "' necessita de dois operandos";
                } else {
                    mensagemNaoTemE1 = "Sentença " + linha + ". A ação '" + verbo + "' necessita de um ator que a inicie";
                    mensagemNaoTemE2 = "Sentença " + linha + ". A ação '" + verbo + "' necessita de um objeto";
                }

                if (faltaE1) {
                    treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, mensagemNaoTemE1, null));
                    raizE.add(treeNode);
                }
                if (faltaE2) {
                    treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, mensagemNaoTemE2, null));
                    raizE.add(treeNode);
                }
            }
        }
        //Verificar se os verbos sao de ligação ou de ação
        i = 0;
        for (String verboSentencaSE : verbosSentencaSE) {
            String verbo = AnalisePeriodo.getLemmas().get(verboSentencaSE);
            int pos = analisePeriodo.ucParser.elementosDaFrase.posVerbo.get(i);
            String sujeito = analisePeriodo.ucParser.elementosDaFrase.elemento.get(pos - 1);
            String palavrasReservadas = "-1";
            String e2 = "";

            //Verifica se é verbo de ligação
            //Existe um erro aqui para sentenças como : se existir então
            if (pos + 2 <= analisePeriodo.ucParser.elementosDaFrase.tipoElemento.size() - 1 && !analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 1).equals("ASPAS") && !analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 2).equals("ASPAS")) {
                if (analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(pos + 1).equals("PALRES")) {
                    palavrasReservadas = analisePeriodo.ucParser.elementosDaFrase.elemento.get(pos + 1);
                    e2 = analisePeriodo.ucParser.elementosDaFrase.elemento.get(pos + 2);
                } else {
                    e2 = analisePeriodo.ucParser.elementosDaFrase.elemento.get(pos + 1);
                }

                if (Constante.verbosDeLigacao.contains(verbo)) {
                    verificaComplementosMesmoTipoE1E2(raizA, linha, sujeito, e2);

                } else {
                    if (verbo.equals("existir")) {
                        verificarVerboExistir(raizA, linha, pos);
                    } else {
                        verificaAtor(raizA, linha, sujeito);
                        verificaComplementos(raizA, linha, verboSentencaSE, palavrasReservadas, e2);
                    }
                }
            }
            i++;
        }
        return treeNode;
    }

    private void verificarVerboExistir(DefaultMutableTreeNode raizA, String linha, int posicaoVerboExistir) throws Exception {
        DefaultMutableTreeNode treeNode = null;
        String e1 = "";
        String e2 = "";
        String lemmaE1ouE2 = "";
        Isr isr;
        Atributo atributo;
        ArrayList<String> complementos = new ArrayList<>();

        if (analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(posicaoVerboExistir - 1).equals("E1")) {
            e1 = analisePeriodo.ucParser.elementosDaFrase.elemento.get(posicaoVerboExistir - 1);
            lemmaE1ouE2 = Constante.recuperarLemmaDaPalavra(e1);
            complementos = analisePeriodo.ucParser.elementosDaFrase.recuperaElementos("E1");
        } else if (analisePeriodo.ucParser.elementosDaFrase.tipoElemento.get(posicaoVerboExistir + 1).equals("E2")) {
            e2 = analisePeriodo.ucParser.elementosDaFrase.elemento.get(posicaoVerboExistir + 1);
            lemmaE1ouE2 = Constante.recuperarLemmaDaPalavra(e2);
            complementos = analisePeriodo.ucParser.elementosDaFrase.recuperaElementos("E2");
        }
        if (!e1.equals("") || !e2.equals("")) {
            isr = dicionarioDAO.recuperarIsrPorNomeLemma(lemmaE1ouE2, projetoSelecionado.getId());
            atributo = dicionarioDAO.recuperarAtributoPorNomeLemma(lemmaE1ouE2, projetoSelecionado.getId());

            if (isr == null && atributo == null) {
                if (!e1.equals("")) {
                    treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". O termo '" + e1 + "' não foi classificado como Atributo ou Requisito de Armazenamendo.", null));
                } else if (!e2.equals("")){
                    treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". O termo '" + e2 + "' não foi classificado como Atributo ou Requisito de Armazenamendo.", null));
                    
                }
                raizA.add(treeNode);
            }
        } else {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". Sentença imcompleta.", null));
            raizA.add(treeNode);
        }

        adicionarComplementosAoPai(raizA, linha, "", "atributo", complementos);
    }

    private void verificaComplementosMesmoTipoE1E2(DefaultMutableTreeNode raizA, String linha, String e1, String e2) throws Exception {
        //   System.out.println("Tenho que fazer!");
        DefaultMutableTreeNode treeNode = null;
        ArrayList<String> complementos = new ArrayList<>();

        if (verificarClassificacao(e1).isEmpty() && !Constante.substantivosParaIgnorar.contains(e1)) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". O termo '" + e1 + "' não foi classificado", null));
            raizA.add(treeNode);
        } else {
            complementos = analisePeriodo.ucParser.elementosDaFrase.recuperaElementos("E1");
        }
        if (verificarClassificacao(e2).isEmpty() && !Constante.substantivosParaIgnorar.contains(e2)) {
            treeNode = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ". O termo '" + e2 + "' não foi classificado", null));
            raizA.add(treeNode);
        } else {
            complementos.addAll(analisePeriodo.ucParser.elementosDaFrase.recuperaElementos("E2"));
        }
        adicionarComplementosAoPai(raizA, linha, "", "atributo", complementos);
    }

    private ArrayList<Integer> verificarClassificacao(String termo) throws Exception {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        String lemma = Constante.recuperarLemmaDaPalavra(termo);
        List<String> listaSinonimo = dicionarioDAO.retornaSinonimos(lemma, projetoSelecionado);
        listaSinonimo.add(termo);

        for (String sinonimo : listaSinonimo) {
            List<Integer> listaTiposRecuperados = dicionarioDAO.recuperarTipoPorNomeLemma(sinonimo, projetoSelecionado.getId());
            if (!listaTiposRecuperados.isEmpty()) {
                listaTiposRecuperados.removeAll(temp);
                temp.addAll(listaTiposRecuperados);
            }
        }
        return temp;
    }

    private void verificaAtor(DefaultMutableTreeNode raiz, String linha, String sujeito) throws Exception {
        DefaultMutableTreeNode treeNodeErro = null;
        if (sujeito.equals("")) {
            int j = retornaSujeito("SUJ");

            if (j < 0) {
                j = retornaSujeito("E1");
            }
            if (j >= 0) {
                sujeito = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j);
            } else {
            }
        }
        ArrayList<String> proximos = (ArrayList<String>) recuperaListaConformeProximo("usuario");
        proximos = toLowerCase(proximos);

        ArrayList<String> lemmasProximos = Constante.trocarPorListaDeLemma(proximos);
//        ArrayList<String> lemmasProximos = recuperaLemmasArray(proximos);
        ArrayList<String> lemmasProximos1 = lemmasProximos;
        String lemmaSujeito = Constante.recuperarLemmaDaPalavra(sujeito);

        List<String> listaSinonimo = dicionarioDAO.retornaSinonimos(lemmaSujeito, projetoSelecionado);

        int tamanho1 = lemmasProximos.size();
        lemmasProximos.removeAll(listaSinonimo);
        int tamanho2 = lemmasProximos.size();

        if (!lemmasProximos1.contains(lemmaSujeito) && (!sujeito.toLowerCase().contains("sistema")) && (tamanho1 == tamanho2)) {
            treeNodeErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ": '" + sujeito + "' não está classificado como ATOR.", null));
            raiz.add(treeNodeErro);
        }

    }

    private int retornaSujeito(String tipoSujeito) {
        return analisePeriodo.ucParser.elementosDaFrase.tipoElemento.indexOf(tipoSujeito);
    }

    private void verificaComplementos(DefaultMutableTreeNode raizE, String linha, String verbo, String palavrasReservadas, String e2) throws Exception {
        //Verificar o erro da sentenca: O usuário seleciona a opção voltar.
        DefaultMutableTreeNode treeNodeErro = null;

        if (verbo.equals("")) {
            int j = retornarVerbo("VERBO");
            if (j < 0) {
                j = retornarVerbo("VERBSE");
            }
            if (j >= 0) {
                verbo = analisePeriodo.ucParser.elementosDaFrase.elemento.get(j);
            } else {
                System.err.println("Erro: " + frase + linha);
            }
        }
        verbo = AnalisePeriodo.getLemmas().get(verbo);
        Sinonimo sinonimo = sinonimoDAO.buscaPorDe(verbo, null);
        verbo = (sinonimo == null ? verbo : sinonimo.getIdVerbo().getDe());

        if (palavrasReservadas.equals("")) {
            int k = analisePeriodo.ucParser.elementosDaFrase.tipoElemento.indexOf("PALRES");
            palavrasReservadas = (k >= 0 ? analisePeriodo.ucParser.elementosDaFrase.elemento.get(k) : "");
        } else if (palavrasReservadas.equals("-1")) {
            palavrasReservadas = "";
        }

        String tipoProximo = retornaTipoProximo(verbo, palavrasReservadas);
//        verificarTelaDe();
        if (!tipoProximo.equals("")) {
            ArrayList<String> complementos = new ArrayList<>();
            if (e2.equals("")) {
                complementos = analisePeriodo.ucParser.elementosDaFrase.recuperaElementos("CCO1");
                adicionarComplementosAoPai(raizE, linha, verbo, tipoProximo, complementos);
            } else {
                complementos.add(e2);
            }
            if (!tipoProximo.equals("elemento_de_interface")) {
                ArrayList<String> proximos = (ArrayList<String>) recuperaListaConformeProximo(tipoProximo);
                proximos = toLowerCase(proximos);
                ArrayList<String> lemmasProximos = Constante.trocarPorListaDeLemma(proximos);
                ArrayList<String> lemmasProximos1 = lemmasProximos;
                // ArrayList<String> lemmasProximos = recuperaLemmasArray(proximos);
                int tamanho1 = lemmasProximos.size();
                int i = 0;
                for (String complemento : complementos) {

                    String lemmaComplemento = Constante.recuperarLemmaDaPalavra(complemento);
                    List<String> listaSinonimo = dicionarioDAO.retornaSinonimos(lemmaComplemento, projetoSelecionado);
                    lemmasProximos.removeAll(listaSinonimo);
                    int tamanho2 = lemmasProximos.size();

                    if (!lemmasProximos1.contains(lemmaComplemento) && tamanho1 == tamanho2) {
                        String novoElemento = complemento.replace("tela de ", "");
                        if (tipoProximo.equals("interface") && (lemmaComplemento.contains("tela de")
                                && lemmasProximos.contains(novoElemento))) {
                            complementos.set(i, novoElemento);
                            analisePeriodo.elementosFrase.set(analisePeriodo.elementosFrase.indexOf(complemento), novoElemento);
                            ElementoFrase el = hashElementosFrase.get(Constante.INTERFACE_USUARIO + complemento);
                            hashElementosFrase.remove(Constante.INTERFACE_USUARIO + complemento);
                            hashElementosFrase.put(Constante.INTERFACE_USUARIO + novoElemento, el);
                            continue;
                        }
                        treeNodeErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ": '" + complemento + "' não está classificado como " + tipoProximo + ".", null));
                        raizE.add(treeNodeErro);
                    }
                    i++;
                }
            }
        }
    }

    private void adicionarComplementosAoPai(DefaultMutableTreeNode raiz, String linha, String verbo, String tipoProximo, ArrayList<String> complementos) throws Exception {
        DefaultMutableTreeNode treeNodeErro = null;

        if (!complementos.isEmpty()) {
            String nomeInterfaceDeUsuario = complementos.get(0);
            //String nomeLemmaInterfaceDeUsuario = Constante.recuperarLemmaDaPalavra(nomeInterfaceDeUsuario);

            if (tipoProximo.equals("interface")) {
                pilhaInterface.add(nomeInterfaceDeUsuario);
                if (hashElementosFrase.get(Constante.INTERFACE_USUARIO + (String) pilhaInterface.element()) == null) {
                    hashElementosFrase.put(Constante.INTERFACE_USUARIO + (String) pilhaInterface.element(), new ElementoFrase(Constante.INTERFACE_USUARIO, nomeInterfaceDeUsuario));
                }
            }

            if ((tipoProximo.equals("elemento_de_interface") || tipoProximo.equals("atributo")) && (verbo.equals("clicar") || verbo.equals("inserir") || verbo.equals("exibir")
                    || verbo.equals("escolher") || verbo.equals("marcar"))) {

                if (!pilhaInterface.isEmpty()) {
                    ElementoFrase temp = hashElementosFrase.get(Constante.INTERFACE_USUARIO + (String) pilhaInterface.element());
                    for (String complemento : complementos) {
                        Tabela t = new Tabela();
                        t.setNome(complemento);
                        String lemma = Constante.recuperarLemmaDaPalavra(complemento);
                        t.setNomeLemma(lemma);

                        if (tipoProximo.equals("atributo") && verbo.equals("escolher")) {
                            t.setIdTipoTabela(Constante.COMBOBOX);
                        }

                        if (tipoProximo.equals("atributo") && verbo.equals("exibir")) {
                            t.setIdTipoTabela(Constante.TEXTFIELD_OUT);
                        }

                        if (tipoProximo.equals("atributo") && (verbo.equals("inserir"))) {
                            t.setIdTipoTabela(Constante.TEXTFIELD_IN);
                        }

                        if (tipoProximo.equals("elemento_de_interface") && verbo.equals("clicar")) {
                            t.setIdTipoTabela(Constante.BUTTON);
                        }

                        if (tipoProximo.equals("elemento_de_interface") && verbo.equals("marcar")) {
                            t.setIdTipoTabela(Constante.CHECK);
                        }
                        temp.addFilho(t);
                        adicionarAtributosAoPai(complemento);
                    }

                    hashElementosFrase.put(Constante.INTERFACE_USUARIO + (String) pilhaInterface.element(), temp);
                } else {
                    treeNodeErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, "Sentença " + linha + ": Nenhuma interface de usuário foi utilizada anteriormente.", null));
                    raiz.add(treeNodeErro);
                }
            }

            if (tipoProximo.equals("atributo")) {
                for (String complemento : complementos) {
                    adicionarAtributosAoPai(complemento);
                }
            }
        }
    }

    private void adicionarAtributosAoPai(String complemento) throws Exception {
        DAO<Atributo> atributoDao = new DAO(Atributo.class);
        DAO<Isr> isrDao = new DAO(Isr.class);
        Map<String, Object> params = new HashMap<>();
        String lemmaAtributo = Constante.recuperarLemmaDaPalavra(complemento);
        params.clear();
        params.put("idProjeto", projetoSelecionado);
        params.put("nomeLemma", lemmaAtributo);
        List<Atributo> listaAtributo = atributoDao.filtrarPorCampos(params, "select t from Atributo t where t.nomeLemma = :nomeLemma"
                + " and t.idProjeto = :idProjeto and t.validado = 1");

        if (listaAtributo != null) {
            for (Atributo atributo : listaAtributo) {
                // Isr isrPai = atributo.getIdIsr() == atributo.getIdIsrOrigem() || atributo.getIdIsrOrigem() == null ? atributo.getIdIsr() : atributo.getIdIsrOrigem();
                Isr isrPai;
                if (atributo.getIdIsr() == atributo.getIdIsrOrigem() || atributo.getIdIsrOrigem() == null) {
                    isrPai = atributo.getIdIsr();
                } else {
                    isrPai = atributo.getIdIsrOrigem();
                    if (hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributo.getIdIsr().getNome().toLowerCase()) == null) {
                        hashElementosFrase.put(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributo.getIdIsr().getNome().toLowerCase(), new ElementoFrase(Constante.REQUISITOS_DE_ARMAZENAMENTO, atributo.getIdIsr().getNome().toLowerCase()));
                        Isr isrAtributo = dicionarioDAO.isrDAO.buscaPorNomeLemma(atributo.getNomeLemma(), projetoSelecionado.getId());
                        if (isrAtributo != null) {
                            classesNaoCitada.add(isrPai.getNome());
                        }
                    }
                    ElementoFrase temp = hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributo.getIdIsr().getNome().toLowerCase().toLowerCase());

                    Tabela t = new Tabela();
                    t.setNome(atributo.getIdIsrOrigem().getNome());
                    t.setIdTipoTabela(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                    t.setNomeLemma(atributo.getIdIsrOrigem().getNomeLemma());
                    temp.addFilho(t);

                    hashElementosFrase.put(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributo.getIdIsr().getNome().toLowerCase(), temp);
                }

                if (hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + isrPai.getNome().toLowerCase()) == null) {
                    hashElementosFrase.put(Constante.REQUISITOS_DE_ARMAZENAMENTO + isrPai.getNome().toLowerCase(), new ElementoFrase(Constante.REQUISITOS_DE_ARMAZENAMENTO, isrPai.getNome().toLowerCase()));
                    if (!elementosDaFrase.contains(isrPai.getNome())) { //aqui trocar o nome do atributo pela classe
                        elementosDaFrase.add(isrPai.getNome());
                    }
                    Isr isrAtributo = dicionarioDAO.isrDAO.buscaPorNomeLemma(atributo.getNomeLemma(), projetoSelecionado.getId());
                    if (isrAtributo != null) {
                        classesNaoCitada.add(isrPai.getNome());
                    }
                }

                ElementoFrase temp = hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + isrPai.getNome().toLowerCase());

                Tabela t = new Tabela();
                t.setNome(atributo.getNome());
                Isr atributoIsr = isrDao.buscaPorNomeLemma(atributo.getNomeLemma(), projetoSelecionado.getId());
                if (atributoIsr != null) {
                    t.setIdTipoTabela(Constante.REQUISITOS_DE_ARMAZENAMENTO);

                    if (hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributoIsr.getNome().toLowerCase()) == null) {
                        hashElementosFrase.put(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributoIsr.getNome().toLowerCase(), new ElementoFrase(Constante.REQUISITOS_DE_ARMAZENAMENTO, atributoIsr.getNome().toLowerCase()));
                    }
//                    ElementoFrase temp1 = hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributoIsr.getNome().toLowerCase());
//
//                    t.setNomeLemma(lemmaAtributo);
//                    temp1.addFilho(t);

                    hashElementosFrase.put(Constante.REQUISITOS_DE_ARMAZENAMENTO + atributo.getIdIsr().getNome().toLowerCase(), temp);
                } else {
                    t.setIdTipoTabela(Constante.ATRIBUTO);
                }
                t.setNomeLemma(lemmaAtributo);
                temp.addFilho(t);

                hashElementosFrase.put(Constante.REQUISITOS_DE_ARMAZENAMENTO + isrPai.getNome().toLowerCase(), temp);
            }
        }
    }

    private int retornarVerbo(String tipoVerbo) {
        return analisePeriodo.ucParser.elementosDaFrase.tipoElemento.indexOf(tipoVerbo);
    }

    private List<String> recuperaListaConformeProximo(String proximo) {
        List<String> listaOpcoes = new ArrayList<>();

        if (proximo.contains("verbo")) {
            List<String> listaVerbos = buildListIntellisense(Constante.ACAO, projetoSelecionado.getId());
            listaVerbos = transformaTerceiraPessoa(listaVerbos);
            listaOpcoes.addAll(listaVerbos);

        }
        if (proximo.contains("classe")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.REQUISITOS_DE_ARMAZENAMENTO, projetoSelecionado.getId()));
        }
        if (proximo.contains("usuario")) {
            if (!CasoDeUsoView.nomeAtor.getText().equals("")) {
                listaOpcoes.add(CasoDeUsoView.nomeAtor.getText());
                listaOpcoes.add("sistema");
            } else {
                listaOpcoes.addAll(buildListIntellisense(Constante.USUARIO, projetoSelecionado.getId()));
                listaOpcoes.add("sistema");
            }
        }
        if (proximo.equals("interface")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.INTERFACE_USUARIO, projetoSelecionado.getId()));
        }

        if (proximo.contains("atributo")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.ATRIBUTO, projetoSelecionado.getId()));
        }
        if (proximo.contains("caso_de_uso")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.CASO_DE_USO, projetoSelecionado.getId()));
        }
        if (proximo.contains("elemento_de_interface")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.ELEMENTO_DE_INTERFACE, projetoSelecionado.getId()));
        }

        if (proximo.contains("e_ou_entao")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.E_OU_ENTAO, projetoSelecionado.getId()));
        }

        if (proximo.contains("operador_logico")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.OPERADOR_LOGICO, projetoSelecionado.getId()));
        }
        return listaOpcoes;
    }

    private ArrayList<String> toLowerCase(ArrayList<String> proximos) {
        ArrayList<String> temp = new ArrayList<>();
        for (String string : proximos) {
            temp.add(string.toLowerCase());
        }
        return temp;
    }

}
