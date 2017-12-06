/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.dao.DicionarioDAO;
//import visao.view.SRSJTree.Root;
//import visao.view.SRSJTree.TreeBuilder;
import br.ufpi.dc.controle.analiseTexto.AnalisePeriodo;
import br.ufpi.dc.entidades.entity.Acao;
import br.ufpi.dc.entidades.entity.tools.ElementoDeInterface;
import br.ufpi.dc.entidades.entity.Lemma;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.SinonimoDominio;
import br.ufpi.dc.entidades.entity.tools.MensagemErro;
import br.ufpi.dc.entidades.entity.tools.TipoConceito;
import br.ufpi.dc.entidades.entity.tools.TipoTabela;
import br.ufpi.dc.controle.etiquetador.Etiquetador;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.tree.DefaultMutableTreeNode;
import br.ufpi.dc.controle.nGramas.NGrama;
import br.ufpi.dc.controle.sinonimo.SinonimoTools;

/**
 *
 * @author helcio.soares
 */
public class Constante {
    //verificar barras
    // Veros especiais: aplicar, calcular, descontar.
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String USER_DIRECTORY = "user.dir";
    public static final String DOCUMENTOSESR = System.getProperty(USER_DIRECTORY)+ System.getProperty(FILE_SEPARATOR)+ "documentos"+ System.getProperty(FILE_SEPARATOR);
   // public static final String DOCUMENTOSESR = ".\\documentos\\";
    public static final String ARQUIVOS = System.getProperty(USER_DIRECTORY)+ System.getProperty(FILE_SEPARATOR)+ "arquivos"+ System.getProperty(FILE_SEPARATOR);
   // public static final String ARQUIVOS = ".\\arquivos\\";
    public static final String DIRETORIO = System.getProperty(USER_DIRECTORY)+ System.getProperty(FILE_SEPARATOR)+ "projetos"+ System.getProperty(FILE_SEPARATOR);
    //public static final String DIRETORIO = ".\\projetos\\";
    public static final String NOMEDOPRODUTO = "SRS EDITOR - ";
    public static final String INICIOFRASE = "o sistema deve ";
    public static final String BANCOMYSQL = "srs"; //srsUFPI

    public static final String SUBSTANTIVADOR = "#opção#comando#botão#tela#telade";
    public static final String COMPARADOR = "#igual#maior#menor#";
    public static final String CONJUNCAO = "#e#ou#";

    public static final int CONCEITO_SIMPLES = 101;
    public static final int CONCEITO_COMPOSTO = 102;
    public static final int ACAO = 103;
    public static final int SIGLA = 104;
    public static final int FUNCIONALIDADE = 105;
    public static final int QQQ = 700;

    public static final int CONCEITO1 = 200;
    public static final int CONCEITO = 1;
    public static final int USUARIO = 2;
    public static final int INTERFACE_USUARIO = 3;
    public static final int REQUISITOS_DE_ARMAZENAMENTO = 4;
    public static final int FUNCAODOPRODUTO = 5;
    public static final int TERMO_NAO_ENCONTRADO_NAS_SECOES = 14;
    public static final int PERMITIR = 7;
    public static final int CONCEITO_CLASSE = 8;
    public static final int DEVE = 9;
    public static final int SISTEMA = 10;
    public static final int ATRIBUTO = 11;
    public static final int ATRIBUTO_OK = 111;
    public static final int ATRIBUTO_APAGADO = 112;
    public static final int ATRIBUTO_DUPLICADO = 113;
    public static final int CASO_DE_USO = 6;

    public static final int ELEMENTO_DE_INTERFACE = 13;
    public static final int COMBOBOX = 1300;
    public static final int TEXTFIELD_IN = 1301;
    public static final int TEXTFIELD_OUT = 1302;
    public static final int BUTTON = 1303;
    public static final int CHECK = 1304;

    public static final int E_OU_ENTAO = 16;
    public static final int OPERADOR_LOGICO = 17;

    public static final int RAIZ_ = 0;
    public static final int ERRO_ = 1;
    public static final int AVISOS_ = 2;
    public static final int SEM_ERRO_ = 3;
    public static final int SEM_AVISO_ = 4;
    public static final int FORA_DO_PADRAO_ = 5;
    public static final int TERMO_NAO_ENCONTRADO_LEXICO_ = 15;
    public static final int TERMO_ENCONTRADO_ = 7;
    public static final int TERMO_ESPERADO_ = 8;
    public static final int CORTE = 50;
    public static final int SINONIMO_ = 30;

    public static final String RAIZ = "Raíz";
    public static final String ERRO = "Erros";
    public static final String AVISOS = "Avisos";
    public static final String SEM_ERRO = "Sem erro";
    public static final String SEM_AVISO = "Sem aviso";
    public static final String FORA_DO_PADRAO = "Sentença fora do padrão";
    public static final String TERMO_NAO_ENCONTRADO_LEXICO = "Não encontrados no lexico";
    public static final String TERMO_ENCONTRADO = "Encontrado: ";
    public static final String TERMO_NAO_ENCONTRADO_NAS_SECOES_S = "Não classificados ";
    public static final String ATRIBUTO_S = "Atributos";
    public static final String SINONIMO_S = "Sinonimos";

    public final static String QUERYATRIBUTOISR = "select s FROM Atributo s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.idIsr       = :isr   \n";

    public final static String QUERYSINONIMOS = "select s FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.sinonimo  <> s.chave   \n"
            + "and   s.sinonimo  <> s.chave     ";

    public final static String QUERYSINONIMOCHAVE = "select s FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.chave     = :chave     "
            + "and   s.sinonimo  <> null      ";

    public final static String QUERYSINONIMOSINONIMO = "select s FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.sinonimo  = :sinonimo    ";

    public final static String QUERYSINONIMOCHAVE1 = "select s FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.chave     = :chave     "
            + "and   s.sinonimo  <> null      "
            + "and   s.chave     <> s.sinonimo  ";

    public final static String QUERYSINONIMOSDISTINCT = "select  distinct s.sinonimo FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.sinonimo  <> null      "
            + "and   s.chave     <> s.sinonimo  "
            + "order by s.sinonimo";

    public final static String QUERYSINONIMOSINONIMO1 = "select s FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.sinonimo  = :sinonimo    "
            + "and   s.chave    <> :sinonimo    ";

    public final static String QUERYSINONIMOVERBO = "select s FROM SinonimoDominio s    \n"
            + "where s.idProjeto = :idProjeto \n"
            + "and   s.sinonimo  = :sinonimo    ";

    public final static String QUERYREFERENCIA = "select r FROM Referencia r    \n"
            + "where r.idProjeto = :idProjeto ";

    public final static String QUERYTABELA = "select t FROM Tabela t    \n"
            + "where t.idProjeto     = :idProjeto \n"
            + "and   t.idTipoTabela  = :tipoTabela  ";

    public static HashMap<Integer, TipoTabela> hashTipoTabela = new HashMap<Integer, TipoTabela>();
    public static HashMap<String, ElementoDeInterface> hashElementoDeInterface = new HashMap<String, ElementoDeInterface>();
    public static HashSet<String> substantivosParaIgnorar = new HashSet<String>();
    public static HashSet<String> verbosDeLigacao = new HashSet<String>();
    public static List<String> operadoresLogicos = new ArrayList<String>();
    public static HashSet<String> palavrasReservadas = new HashSet<String>();
    public static HashMap<String, String> mensagensHash = new HashMap<String, String>();
    private static DAO<MensagemErro> mensagenErroDAO = new DAO(MensagemErro.class);
    public static HashMap<String, Root> arvoresSentencas = new HashMap<String, Root>();
    public static TreeBuilder treeBuilder = new TreeBuilder();
    public static Projeto projetoSelecionado;
    public static String ATRIBUTO_APAGADO_S = "Atributos não descritos";
    public static HashMap<String, String> hashTabelas = new HashMap();

    public static void iniciaTipos() {
        DAO<TipoTabela> tipoTabelaDAO = new DAO(TipoTabela.class);
        List<TipoTabela> tipoTabelaList = tipoTabelaDAO.listaTodos();
        for (TipoTabela tipoTablela : tipoTabelaList) {
            hashTipoTabela.put(tipoTablela.getId(), tipoTablela);
        }

        verbosDeLigacao.add("ir");
        verbosDeLigacao.add("estar");
        verbosDeLigacao.add("ficar");
        verbosDeLigacao.add("tornar");
        verbosDeLigacao.add("permanecer");
        verbosDeLigacao.add("continuar");
        verbosDeLigacao.add("ser");
        verbosDeLigacao.add("for");

        operadoresLogicos.add("maior que");
        operadoresLogicos.add("menor que");
        operadoresLogicos.add("igual a");
        operadoresLogicos.add("diferente de");
        operadoresLogicos.add("maior igual");
        operadoresLogicos.add("menor igual");

        palavrasReservadas.add("maiorouigual");
        palavrasReservadas.add("maiorigual");
        palavrasReservadas.add("menorouigual");
        palavrasReservadas.add("menorigual");
        palavrasReservadas.add("diferente");
        palavrasReservadas.add("diferentede");
        palavrasReservadas.add("igual");
        palavrasReservadas.add("iguala");
        palavrasReservadas.add("menor");
        palavrasReservadas.add("menorou");
        palavrasReservadas.add("menorque");
        palavrasReservadas.add("maior");
        palavrasReservadas.add("maiorou");
        palavrasReservadas.add("maiorque");
        palavrasReservadas.add("o");
        palavrasReservadas.add("os");
        palavrasReservadas.add("campo");
        palavrasReservadas.add("dado");
        palavrasReservadas.add("comando");
        palavrasReservadas.add("campos");
        palavrasReservadas.add("oscampos");
        palavrasReservadas.add("oscamposdo");
        palavrasReservadas.add("oscamposda");
        palavrasReservadas.add("osdados");
        palavrasReservadas.add("ocampo");
        palavrasReservadas.add("odado");
        palavrasReservadas.add("ocomando");
        palavrasReservadas.add("ocaso");
        palavrasReservadas.add("ocasode");
        palavrasReservadas.add("ocasodeuso");
        palavrasReservadas.add("obotão");

        palavrasReservadas.add("a");
        palavrasReservadas.add("aopção");
        palavrasReservadas.add("na");
        palavrasReservadas.add("naopção");

        palavrasReservadas.add("no");
        palavrasReservadas.add("nobotão");

        palavrasReservadas.add("atela");
        palavrasReservadas.add("atelade");
        palavrasReservadas.add("se");

        mensagensHash = carregaMensagens();

        substantivosParaIgnorar.add("vazio");
        substantivosParaIgnorar.add("nulo");
        substantivosParaIgnorar.add("branco");
        substantivosParaIgnorar.add("embranco");
        substantivosParaIgnorar.add("em branco");
        substantivosParaIgnorar.add("certo");
        substantivosParaIgnorar.add("errado");
        substantivosParaIgnorar.add("informado");
        substantivosParaIgnorar.add("digitado");
        substantivosParaIgnorar.add("correto");
        

        hashTabelas.put("Acao", "(`id`,`objeto`,`verbo`,`id_projeto`)");
        hashTabelas.put("Atributo", "(`id`,`nome`,`nome_lemma`,`validado`,`id_isr`,`id_isr_origem`,`id_projeto`)");
        hashTabelas.put("CasoDeUso", "(`id`, `ator`, `descricao`, `fluxo_alternativo`, `fluxo_excecao`, `fluxo_principal`, `nome`, `nome_lemma`, `pos_condicao`, `pre_condicao`, `proposito`, `id_projeto`)");
        hashTabelas.put("ClasseGramatical", "(`id`,`de`)");
        hashTabelas.put("Conceito", "(`id`,`de`,`f`,`id_projeto`,`id_tipo_conceito`,`nome_lemma`,`utilizado`)");
        hashTabelas.put("DadoRadical", "(`id`,`f`,`idf`,`qtd_doc`,`rf`,`id_projeto`,`id_radical`,`id_referencia`)");
        hashTabelas.put("Dados", "(`id`,`f`,`idf`,`qtd_doc`,`rf`,`id_palavra`,`id_projeto`,`id_radical`,`id_referencia`)");
        hashTabelas.put("ElementoDeInterface", "(`id`,`de`,`tipo`)");
        hashTabelas.put("Gramatica", "(`id`,`de`,`nome`)");
        hashTabelas.put("Introducao", "	(`id`,`escopo`,`objetivo`,`visao_geral`,`id_projeto`)");
        hashTabelas.put("Isr", "(`id`,`dados_armazenados`,`de`,`nome`,`nome_lemma`,`proposito`,`id_projeto`)");
        hashTabelas.put("Lemma", "(`id`,`de`)");
        hashTabelas.put("MensagemErro", "(`id`,`erro`,`mensagem`,`id_gramatica`)");
        hashTabelas.put("Palavra", "(`id`,`de`,`id_classe_gramatical`,`id_lemma`,`id_radical`)");
        hashTabelas.put("Projeto", "(`id`,`de`,`path`)");
        hashTabelas.put("Proximo", "(`id`,`de`,`id_verbo`)");
        hashTabelas.put("Radical", "(`id`,`de`)");
        hashTabelas.put("Referencia", "	(`id`,`de`,`nome_arquivo`,`perc_relevante`,`qtd`,`qtd_radical`,`qtd_relevante`,`id_projeto`)");
        hashTabelas.put("Sinonimo", "(`id`,`de`,`id_verbo`)");
        hashTabelas.put("SinonimoDominio", "(`id`,`chave`,`sinonimo`,`conceito_chave`,`conceito_sinonimo`,`id_projeto`)");
        hashTabelas.put("StopWords", "(`de`)");
        hashTabelas.put("Tabela", "	(`id`,`de`,`id_tipo_tabela`,`nome`,`nome_lemma`,`id_projeto`)");
        hashTabelas.put("TipoConceito", "(`id`,`de`)");
        hashTabelas.put("TipoElementoInterface", "(`id`, `de`)");
        hashTabelas.put("TipoTabela", "	(`id`, `carrega_arvore`, `de`)");
        hashTabelas.put("Verbo", "(`id`,`de`,`prox`)");

    }

    private static HashMap<String, String> carregaMensagens() {
        HashMap<String, String> hashTemp = new HashMap<>();
        List<MensagemErro> listMenssagemErro = mensagenErroDAO.listaTodos();
        for (MensagemErro erro : listMenssagemErro) {
            hashTemp.put(erro.getIdGramatica().getId() + erro.getErro(), erro.getMensagem());
        }
        return hashTemp;
    }

    public static void iniciarElementosDeInterface() {
        DAO<ElementoDeInterface> elementoDeInterfaceDAO = new DAO(ElementoDeInterface.class);
        List<ElementoDeInterface> elementoDeInterfaceList = elementoDeInterfaceDAO.listaTodos();
        for (ElementoDeInterface elementoDeInterface : elementoDeInterfaceList) {
            hashElementoDeInterface.put(elementoDeInterface.getDe().toLowerCase(), elementoDeInterface);
        }
    }

    public static DefaultMutableTreeNode findNode(DefaultMutableTreeNode node, String userObject) {
        //Verifica se ele mesmo não é o nó procurado.  
        if (node.toString().equals(userObject)) {
            return node;
        }

        //Se não for, verifica se seus filhos contém o nó procurado  
        for (Enumeration e = node.children(); e.hasMoreElements();) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) e.nextElement();
            DefaultMutableTreeNode node1 = findNode(child, userObject);
            if (node1 != null) {
                return node1;
            }
        }
        return null;
    }

    public static String recuperarPalavraDoLemma(String text) throws Exception {
        String lemma = null;
        AnalisePeriodo a = new AnalisePeriodo();

        if (AnalisePeriodo.getHashEtiquetas().size() == 0) {
            a.etiquetarPadrao(text);
        }

        String[] palavras = text.split(" ");

        String lemmaDaPalavra = null;
        for (String palavra1 : palavras) {
            String palavra = palavra1.toLowerCase();
            lemmaDaPalavra = AnalisePeriodo.getHashPalavraDoLemma().get(palavra);
            if (lemmaDaPalavra == null) {
                a.etiquetarPadrao(text);
            }
            if (AnalisePeriodo.getHashEtiquetas().get(palavra).charAt(0) == 'S') {
                lemmaDaPalavra = palavra;
            } else {
                lemmaDaPalavra = AnalisePeriodo.getLemmas().get(palavra);
            }

            if (lemma != null) {
                lemma = lemma + " " + lemmaDaPalavra;
            } else {
                lemma = lemmaDaPalavra;
            }
        }
        return lemma;
    }

    public static String recuperarLemmaDaPalavra(String text) throws Exception {
        String lemma = null;
        AnalisePeriodo a = new AnalisePeriodo();

        if (AnalisePeriodo.getHashEtiquetas().size() == 0) {
            a.etiquetarPadrao(text);
        }

        String[] palavras = text.split(" ");

        int i = 0;

        String lemmaDaPalavra = null;
        for (String palavra1 : palavras) {
            String palavra = palavra1.toLowerCase();
            lemmaDaPalavra = AnalisePeriodo.getLemmas().get(palavra);
            if (lemmaDaPalavra == null) {
                a.etiquetarPadrao(text);
            }
            // System.out.println("PALAVRA QUE DEU PAU: "+ palavra);
            if (AnalisePeriodo.getHashEtiquetas().get(palavra) != null && AnalisePeriodo.getHashEtiquetas().get(palavra).charAt(0) == 'S') {
                lemmaDaPalavra = palavra;
            } else {
                lemmaDaPalavra = AnalisePeriodo.getLemmas().get(palavra.toLowerCase());
            }

            if (lemma != null) {
                lemma = lemma + " " + lemmaDaPalavra;
            } else {
                lemma = lemmaDaPalavra;
            }
            i++;
        }
        return lemma;
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new RandomAccessFile(sourceFile, "rw").getChannel();
            destination = new RandomAccessFile(destFile, "rw").getChannel();

            long position = 0;
            long count = source.size();

            source.transferTo(position, count, destination);
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public static void carregarBanco() {
        iniciarElementosDeInterface();
        DicionarioDAO dicionarioDAO = new DicionarioDAO();
        dicionarioDAO.etiquetarTudo(projetoSelecionado);
    }

    public static void desenharArvores(String sentenca) {
        Root raiz = arvoresSentencas.get(sentenca);
        if (raiz != null) {
            treeBuilder.desenharRaiz(raiz.treeModelErros, raiz.treeModelAvisos, raiz.treeModelExplorar);
        } else {
            treeBuilder.desenharRaiz(null, null, null);
        }
    }

    public static String formatarNumero(Integer numero) {
        String temp = numero >= 10 ? numero.toString() : "0" + numero;
        return temp;
    }

    public static String[] formatarString(String texto) {
        String[] temp = texto.split("\n");
        return temp;
    }

    public static ArrayList<String> trocarPorsinonimo(ArrayList<String> elementosDaFrase, Integer tipoElementosDafrase) throws Exception {
        //tipoElementosDafrase = 1 : lsta original
        //tipoElementosDafrase = 2 : lista de lemmas

        ArrayList<String> temp = new ArrayList<String>();
        DAO<SinonimoDominio> sinonimoDominioDAO = new DAO(SinonimoDominio.class);
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("idProjeto", projetoSelecionado);

        for (String elementoDafrase : elementosDaFrase) {
            String lemmaElementoDaFrase = tipoElementosDafrase == 1 ? Constante.recuperarLemmaDaPalavra(elementoDafrase) : elementoDafrase;
            params.put("chave", lemmaElementoDaFrase);
            List<SinonimoDominio> sinonimo = sinonimoDominioDAO.filtrarPorCampos(params, Constante.QUERYSINONIMOCHAVE1);
            elementoDafrase = sinonimo.isEmpty() == true ? elementoDafrase : Constante.recuperarPalavraDoLemma(sinonimo.get(0).getSinonimo());
            temp.add(elementoDafrase);
        }
        return temp;
    }

    public static ArrayList<String> trocarPorListaDeLemma(ArrayList<String> elementosDafrase) throws Exception {
        ArrayList<String> listaLemmas = new ArrayList<String>();
        for (String elementoDafrase : elementosDafrase) {
            String lemma = recuperarLemmaDaPalavra(elementoDafrase);
            listaLemmas.add(lemma);
        }
        return listaLemmas;
    }

    public static ArrayList<String> eliminarRepetidosPorLemma(ArrayList<String> elementosDafrase, Integer tipoElementosDafrase) throws Exception {
        //tipoElementosDafrase = 1 : lsta original
        //tipoElementosDafrase = 2 : lista de lemmas

        ArrayList<String> temp = new ArrayList<String>();

        ArrayList<String> listaLemmas = tipoElementosDafrase == 1 ? trocarPorListaDeLemma(elementosDafrase) : elementosDafrase;

        HashSet<Integer> jaFoi = new HashSet<Integer>();

        for (int i = 0; i <= elementosDafrase.size() - 1; i++) {
            int j = listaLemmas.lastIndexOf(listaLemmas.get(i));
            if (j > i) {
                String s1 = elementosDafrase.get(i);
                String s2 = elementosDafrase.get(j);
                temp.add(s1.length() < s2.length() ? s1 : s2);
                jaFoi.add(j);
            } else if (!jaFoi.contains(j)) {
                temp.add(elementosDafrase.get(i));
            }
        }
        return temp;
    }

    
}
