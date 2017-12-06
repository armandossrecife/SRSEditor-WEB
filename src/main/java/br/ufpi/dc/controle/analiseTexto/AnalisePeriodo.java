/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.analiseTexto;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.controle.UCGrammar.UCGrammarLexer;
import br.ufpi.dc.controle.UCGrammar.UCGrammarParser;
import br.ufpi.dc.entidades.entity.tools.Sinonimo;
import br.ufpi.dc.controle.etiquetador.Etiquetador;
import br.ufpi.dc.controle.grammar.SrsGrammarLexer;
import br.ufpi.dc.controle.grammar.SrsGrammarParser;
import br.ufpi.dc.controle.grammar.tools.UnderlineListener;
import br.ufpi.dc.controle.intellisense.grammar.IntellisenseGrammarLexer;
import br.ufpi.dc.controle.intellisense.grammar.IntellisenseGrammarParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import br.ufpi.dc.controle.nGramas.NGrama;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import br.ufpi.dc.tools.Constante;
import br.ufpi.dc.tools.ElementosFrase;
//import visao.view.Editor;

/**
 *
 * @author helcio.soares
 */
@SuppressWarnings("deprecation")
public class AnalisePeriodo {

    public static String palavrasExcluir = "#ser#estar#permanecer#ficar#poder#dever#permitir#possibilitar#sistema#permitir#";
    public static String etiquetaUltimaNgramaPalavrasExcluir = "#SPS#DA0#DIO#";
    public static String etiquetasSentenca = "";
    public static ElementosFrase elementosDaFraseSRS = new ElementosFrase();
    public ArrayList<String> elementosFrase = new ArrayList<String>();
    public String ultimoProximoUC = "";
    private String sentencaAnalisada;

    final String ARQUIVOANALISETEXTO = "analiseTexto.txt";
    final String ARQUIVOATORES = "atores.txt";
    final String ARQUIVOPRONOMES = "arquivoPronomes.txt";
    private CommonTokenStream tokens;
    private ArrayList<String> tags = null;
    public static Etiquetador tagger;
    //public static HashMap<String, String> etiquetas = new HashMap<String, String>();
    //public static HashMap<String, String> lemmas = new HashMap<String, String>();

    private ArrayList<String> listaConceitosComplexos = new ArrayList<>();
    private HashMap<String, Integer> hashMapConceitosComplexos = new HashMap<>();
    public ArrayList<NGrama> conceitosComplexosOrdenadosParaView = new ArrayList<>();

    private ArrayList<String> listaFuncionalidade = new ArrayList<>();
    private HashMap<String, Integer> hashMapFuncionalidades = new HashMap<>();

    public ArrayList<NGrama> funcionalidadesOrdenadasView = new ArrayList<>();
    public int tipoFrase;
    public SrsGrammarParser srsParser;
    public UCGrammarParser ucParser;
    public IntellisenseGrammarParser intellisenseParser;
    public HashMap<Integer, ArrayList<String>> conceitosTipados;

    public String ultimaPalavraParser = "";
    public static String ultimaPalavraSentenca = "";
    public String complemento = "";
    public String ultimoComplemento = "";

    public String ultimoAtor = "";
    public String ultimoVerbo = "";
    public String ultimoConceito = "";
    public String numeroSentenca = "";

    public AnalisePeriodo() {
    }

    public AnalisePeriodo(String sentencaAnalisada) {
        this.sentencaAnalisada = sentencaAnalisada;
        etiquetarPadrao(sentencaAnalisada);
    }

    DAO<Sinonimo> sinonimoDAO = new DAO(Sinonimo.class);

    public void etiquetarPadrao(String sentencaAnalisada) {
        ArrayList<String> palavras = new ArrayList<String>();
        CommonToken ob = null;

        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(sentencaAnalisada));
        tokens = new CommonTokenStream(lexer);
        System.out.println(tokens.getText());
        Iterator it = tokens.getTokens().iterator();

        while (it.hasNext()) {
            ob = (CommonToken) it.next();
            palavras.add(ob.getText().toLowerCase());
        }

        tagger = new Etiquetador(palavras);
        tagger.etiquetar();
    }

    private static int ehPalavraReservada(List<Token> tokens, int i) {
        String palavra = "";
        int j = 0;
        while (Constante.palavrasReservadas.contains(palavra + tokens.get(i + j).getText())) {
            palavra = palavra + tokens.get(i + j).getText();
            j = j + 1;
        }
        if (j == 1 && palavra.length() <= 2 && !palavra.equals("se")) {
            return 0;
        }
        return j;
    }

    public ArrayList<String> recuperarConceitosComplexos(String frase) throws Exception {

        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        SrsGrammarParser parser = new SrsGrammarParser(tokens);
        System.out.println(tokens.getText());

        tags = etiquetar(tokens);
        parser.conceitos();

        listaConceitosComplexos.addAll(parser.conceitos);
        return parser.conceitos;
    }

    public ArrayList<String> recuperarAtributos(String frase) throws Exception {

        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        SrsGrammarParser parser = new SrsGrammarParser(tokens);
        System.out.println(tokens.getText());

        etiquetar(tokens);
        parser.atributos();

        listaConceitosComplexos.addAll(parser.conceitos);
        return parser.conceitos;
    }

    public ArrayList<String> recuperarElementosDafrase(String frase) throws Exception {

        ArrayList<String> elementosFrase = recuperarOutrosConceitosComplexos(frase);
        elementosFrase = eliminarElementos(elementosFrase);
        ArrayList<String> funcionalidades = recuperarFuncionalidades(frase);

        if (srsParser != null && srsParser.iteracaoVerbo > 0) {
            for (int i = 0; i < srsParser.elementosDaFrase.elemento.size() - 2; i++) {
                funcionalidades.add(srsParser.elementosDaFrase.elemento.get(i) + " "
                        + srsParser.elementosDaFrase.elemento.get(srsParser.elementosDaFrase.elemento.size() - 1));
            }
        }

        funcionalidades = eliminarElementos(funcionalidades);
        elementosFrase = eliminarDuplicidade(elementosFrase, funcionalidades);
        elementosFrase = eliminarPreposicoesNoFinal(elementosFrase);
        
        return elementosFrase;
    }

    private void verificarTelaDe() {
        for (int i = 0; i <= ucParser.elementosDaFrase.elemento.size() - 1; i++) {
            String elemento = ucParser.elementosDaFrase.elemento.get(i);
            if (elemento.toLowerCase().equals("a tela de") || elemento.toLowerCase().equals("tela de")) {
                if (ucParser.elementosDaFrase.elemento.size() - 1 >= i + 1) {
                    String proximoElemento = "tela de " + ucParser.elementosDaFrase.elemento.get(i + 1);
                    ucParser.elementosDaFrase.elemento.set(i + 1, proximoElemento);
                }
            }
        }
    }

    public ArrayList<String> recuperarElementosDafraseCasosDeUso(String frase) throws Exception {
        verificarTelaDe();
        ArrayList<String> elementosFraseLocal = new ArrayList<String>();
        String tiposValidos = "#SUJ#CCO1#E1#E2#";
        for (int i = 0; i <= ucParser.elementosDaFrase.elemento.size() - 1; i++) {
            String e = ucParser.elementosDaFrase.elemento.get(i);
            String t = ucParser.elementosDaFrase.tipoElemento.get(i);
            if (tiposValidos.contains("#" + t + "#") && !elementosFraseLocal.contains(e)) {
                elementosFraseLocal.add(e);
            }
        }
        elementosFraseLocal = eliminarElementos(elementosFraseLocal);

        ArrayList<String> funcionalidades = recuperarFuncionalidades(frase);
        funcionalidades = eliminarFuncionalidades(funcionalidades);

        funcionalidades = eliminarElementos(funcionalidades);
        elementosFraseLocal = eliminarDuplicidade(elementosFraseLocal, funcionalidades);
        elementosFraseLocal = eliminarPreposicoesNoFinal(elementosFraseLocal);
        return elementosFraseLocal;
    }

    public ArrayList<String> recuperarOutrosConceitosComplexos(String frase) throws Exception {

        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        SrsGrammarParser parser = new SrsGrammarParser(tokens);
        System.out.println(tokens.getText());
        tags = etiquetar(tokens);
        parser.conceitos_1();
        listaConceitosComplexos.addAll(parser.conceitos);
        //System.out.println("Conceitos:" + parser.conceitos);
        return parser.conceitos;
    }

    public ArrayList<String> recuperarFuncionalidades(String frase) throws Exception {
        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        SrsGrammarParser parser = new SrsGrammarParser(tokens);
        System.out.println(tokens.getText());

        etiquetar(tokens);
        parser.funcionalidades();
        listaFuncionalidade.addAll(parser.conceitos);
        return parser.conceitos;
    }

    public String sentencaUC(String frase) throws Exception {
        frase = frase.toLowerCase();
        UCGrammarLexer lexer = new UCGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        UCGrammarParser parser = new UCGrammarParser(tokens);
        System.out.println(tokens.getText());

        etiquetarUC(tokens);
        Editor.jTextArea1.setText(frase);
        parser.addErrorListener(new UnderlineListener());
        parser.sentenca_uc();

        this.ucParser = parser;
//        System.out.println("\n" + parser.elementosDaFrase.tipoElemento);
//        System.out.println("\n" + parser.elementosDaFrase.elemento);
//        System.out.println("\n" + parser.elementosDaFrase.verbos);
//        System.out.println("\n" + parser.elementosDaFrase.posVerbo);
        String textErros = Editor.jTextArea1.getText();
        Editor.jTextArea1.setText(textErros + "\n" + parser.elementosDaFrase);

        ultimaPalavraParser = parser.ultimaPalavra;
        complemento = parser.complemento;
        ultimoComplemento = parser.complemento;
        ultimoAtor = parser.ultimoAtor;
        ultimoVerbo = parser.ultimoVerbo;
        numeroSentenca = parser.numeroSentenca;
        String proximo = (parser.ultimaPalavra.equals(".")) ? "" : parser.proximo;
        tipoFrase = parser.tipoFrase;

        verificarComplementos(parser);

        this.elementosFrase = recuperarElementosDafraseCasosDeUso(frase);
//        System.out.println("\n" + parser.elementosDaFrase.tipoElemento);
//        System.out.println("\n" + parser.elementosDaFrase.elemento);
//        System.out.println("\n" + parser.elementosDaFrase.verbos);
//        System.out.println("\n" + parser.elementosDaFrase.posVerbo);
        return proximo;
    }

    public void verificarComplementos(UCGrammarParser parser) throws Exception {

        HashMap<Integer, ArrayList<String>> elementosModificados = new HashMap<>();

        for (int i = 0; i <= parser.elementosDaFrase.elemento.size() - 1; i++) {
            String elementoDaFrase = parser.elementosDaFrase.elemento.get(i);
            if (parser.elementosDaFrase.tipoElemento.get(i).equals("CCO1")) {
                String[] brancos = elementoDaFrase.split(" ");
                if (brancos.length > 3) {
                    ArrayList<String> maisComplementos = recuperarOutrosConceitosComplexos(elementoDaFrase);
                    elementosModificados.put(i, maisComplementos);
                }
            }
        }
        if (!elementosModificados.isEmpty()) {
            for (Map.Entry<Integer, ArrayList<String>> entrySet : elementosModificados.entrySet()) {
                Integer key = entrySet.getKey();
                ArrayList<String> value = entrySet.getValue();
                ArrayList<String> elementosDaFrase = parser.elementosDaFrase.elemento;
                ArrayList<String> tipoElemento = parser.elementosDaFrase.tipoElemento;

                elementosDaFrase.set(key, value.get(0));
                key++;
                for (int j = 1; j <= value.size() - 1; j++) {
                    elementosDaFrase.add(key, value.get(j));
                    String etiqueta = Etiquetador.hashEtiquetas.get(value.get(j).toLowerCase());
                    if (etiqueta != null) {
                        if (etiqueta.charAt(0) == 'V') {
                            tipoElemento.add(key, "VERBO");
                        } else {
                            tipoElemento.add(key, "CCO1");
                        }
                    } else {
                        tipoElemento.add(key, "CCO1");
                    }
                    key++;
                }
            }
            parser.elementosDaFrase.verbos.clear();
            parser.elementosDaFrase.posVerbo.clear();

            for (int i = 0; i <= parser.elementosDaFrase.elemento.size() - 1; i++) {
                String e = parser.elementosDaFrase.elemento.get(i);

                if (parser.elementosDaFrase.tipoElemento.get(i).contains("VERB")) {
                    parser.elementosDaFrase.verbos.add(e);
                    parser.elementosDaFrase.posVerbo.add(i);
                }
            }
        }
        int k = 0;
    }

    public String sintagmas(String frase) throws Exception {
        frase = frase.toLowerCase();
        etiquetasSentenca = "";
        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        SrsGrammarParser parser = new SrsGrammarParser(tokens);
        System.out.println(tokens.getText());

        tags = etiquetarDescricaoFuncionalidade(tokens); //demora pra porra
        Editor.jTextArea1.setText(frase);
        parser.addErrorListener(new UnderlineListener());
        UnderlineListener.erroList.clear();
        //System.out.println("");
        parser.inicio();
        tipoFrase = parser.tipoFrase;
        this.srsParser = parser;
        String textErros = Editor.jTextArea1.getText();
        textErros = textErros + "\n" + parser.caminho + "\n" + parser.conceitosTipados + "\n" + parser.tipoFrase;;
        Editor.jTextArea1.setText(textErros);
        conceitosTipados = corrigirConceitosTipados(parser.conceitosTipados); //+1 parser

        //System.out.println("Conceitos antes:" + parser.conceitos);
        recuperarElementosDafraseFuncaoDoProduto(frase);// +1 parser

        textErros = textErros + "\n Número de iterações" + parser.iteracaoVerbo;
//        System.out.println("\n Número de iterações" + parser.iteracaoVerbo);
//        System.out.println("");
//        System.out.println(parser.elementosDaFrase.elemento);
//        System.out.println(parser.elementosDaFrase.tipoElemento);
//        System.out.println("Elementos da frase:" + elementosFrase);
        srsParser.podeSer = eliminarPreposicaoFinDoConceito(srsParser.podeSer);
        return parser.proximo;
    }

    public void recuperarElementosDafraseFuncaoDoProduto(String frase) throws Exception {
        ArrayList<String> funcionalidades = recuperarFuncionalidades(frase);

        if (srsParser != null && srsParser.iteracaoVerbo > 0) {
            for (int i = 0; i <= srsParser.elementosDaFrase.elemento.size() - 2; i++) {
                funcionalidades.add(srsParser.elementosDaFrase.elemento.get(i) + " "
                        + srsParser.elementosDaFrase.elemento.get(srsParser.elementosDaFrase.elemento.size() - 1));
            }
        }
        funcionalidades = eliminarElementos(funcionalidades);
        funcionalidades = eliminarFuncionalidades(funcionalidades);
        this.elementosFrase = eliminarDuplicidade(this.elementosFrase, funcionalidades);
        this.elementosFrase = eliminarPreposicoesNoFinal(this.elementosFrase);
    }

    public String intellisense(String frase, boolean showTree) throws Exception {
        frase = frase.toLowerCase();
        etiquetasSentenca = "";
        IntellisenseGrammarLexer lexer = new IntellisenseGrammarLexer(new ANTLRInputStream(frase));
        tokens = new CommonTokenStream(lexer);
        IntellisenseGrammarParser parser = new IntellisenseGrammarParser(tokens);
        System.out.println();
        System.out.println(tokens.getText());

        etiquetarDescricaoFuncionalidadeIntellisense(tokens);
        //System.out.println("");
        Editor.jTextArea1.setText(frase);
        parser.addErrorListener(new UnderlineListener());
        parser.inicio();

        this.intellisenseParser = parser;
        String textErros = Editor.jTextArea1.getText();
        textErros = textErros + "\n" + parser.caminho + "\n" + parser.conceitosTipados + "\n" + parser.tipoFrase;;
        Editor.jTextArea1.setText(textErros);

        /* Teste de Rig*/
        conceitosTipados = parser.conceitosTipados;
        ultimaPalavraParser = parser.ultimaPalavra;
        return parser.proximo;
    }

    public void analisarTexto(ArrayList<String> texto, ArrayList<String> palavrasTratadas, ArrayList<String> pontuacaoSimbolos) throws Exception {
        for (String frase : texto) {
            sintagmas(frase);
        }
    }

    public void analisarTexto1(ArrayList<String> texto) throws Exception {
        for (String frase : texto) {
            recuperarConceitosComplexos(frase);
            recuperarFuncionalidades(frase);
        }

        refinarListas(listaConceitosComplexos, hashMapConceitosComplexos);
        refinarListasComplexas(hashMapConceitosComplexos);
        conceitosComplexosOrdenadosParaView = ordenarListaComplexa(hashMapConceitosComplexos);

        refinarListas(listaFuncionalidade, hashMapFuncionalidades);
        refinarListasComplexas(hashMapFuncionalidades);
        funcionalidadesOrdenadasView = ordenarListaComplexa(hashMapFuncionalidades);
    }

    private void refinarListas(ArrayList<String> lista, HashMap<String, Integer> listaParaView) {
        for (String conceito : lista) {
            Integer temp = 1;
            if (listaParaView.get(conceito) != null) {
                temp = listaParaView.get(conceito);
                temp++;
            }
            listaParaView.put(conceito, temp);
        }
    }

    private void refinarListasComplexas(HashMap<String, Integer> listaComplexos) {
        //Excluir elementos com apenas uma ocorrencia
        //Excluir erros do ANTLR
        HashMap<String, Integer> conceitosParaExcluir = new HashMap<>();
        for (Map.Entry<String, Integer> entrySet : listaComplexos.entrySet()) {
            String key = entrySet.getKey();
            Integer value = entrySet.getValue();
            if (key.contains("<")) {
                conceitosParaExcluir.put(key, value);
            } else {
                // System.out.println(key);
            }
        }
        //Excluindo os conceitos com erros ou com apenas umas ocorrencia
        for (Map.Entry<String, Integer> entrySet : conceitosParaExcluir.entrySet()) {
            String key = entrySet.getKey();
            Integer value = entrySet.getValue();
            listaComplexos.remove(key, value);
        }

    }

    private ArrayList<NGrama> ordenarListaComplexa(HashMap<String, Integer> listaParaAView) {
        ArrayList<NGrama> listaOrdenada = new ArrayList<NGrama>();
        for (Map.Entry<String, Integer> entrySet : listaParaAView.entrySet()) {
            String key = entrySet.getKey();
            Double value = (double) entrySet.getValue();
            NGrama nGrama = new NGrama(key, value);
            listaOrdenada.add(nGrama);
        }
        Collections.sort(listaOrdenada);
        return listaOrdenada;
    }

    public static ArrayList<String> etiquetar(CommonTokenStream tokens) {

        ultimaPalavraSentenca = tokens.getTokens().get(tokens.getTokens().size() - 2).getText();

        for (int i = 0; i < tokens.getTokens().size() - 1; i++) {
            //Detecção e atribuição das etiquetas aos seus respectivos tokens
            String palavraEtiquetada = tokens.getTokens().get(i).getText().toLowerCase();
            String etiqueta = tagger.getHashEtiquetas().get(palavraEtiquetada);
            String lemma = "";
            try {
                lemma = tagger.getLemma(palavraEtiquetada);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Character.isDigit(palavraEtiquetada.charAt(0))) {
                ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.NUMERO);

            } else {
                switch (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(0)) {
                    case 'V':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.VERB);
                        break;
                    case 'N':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.SUBS);
                        break;
                    case 'D':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.ART);
                        break;
                    case 'P':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PRON);
                        break;
                    case 'S':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PREP);
                        break;
                    case 'A':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.ADJ);
                        break;
                    case 'Z':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.NUM);
                        break;
                    case 'C':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.CONJ);
                        break;

                    case 'F':
                        try {
                            if (tagger.getEtiquetas().get(i).charAt(1) == 'c') {
                                ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.TERMINAL);
                            } else if (tagger.getEtiquetas().get(i).charAt(1) == 'd') {
                            } else {
                                ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.SIMBOLOS);
                            }
                        } catch (Exception e) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PALAVRAESTRANGEIRA);
                        }
                        break;
                    case 'R':
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.ADV);
                        break;
                } //Case
            } //if chaAt
        }
        return (ArrayList<String>) tagger.getEtiquetas();
    }

    public static ArrayList<String> etiquetarDescricaoFuncionalidade(CommonTokenStream tokens) {
        String verbos = "#dever#poder#";
        elementosDaFraseSRS = new ElementosFrase();
        etiquetasSentenca = "";

        ultimaPalavraSentenca = tokens.getTokens().get(tokens.getTokens().size() - 2).getText();

        for (int i = 0; i < tokens.getTokens().size() - 1; i++) {
            //Detecção e atribuição das etiquetas aos seus respectivos tokens
            String palavraEtiquetada = tokens.getTokens().get(i).getText().toLowerCase();
            String lemma = "";
            try {
                lemma = tagger.getLemma(palavraEtiquetada);
            } catch (Exception e) {
                //   System.out.println("erro em: " + palavraEtiquetada);
                e.printStackTrace();
            }

            switch (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(0)) {
                case 'V':
                    String verbo = tokens.getTokens().get(i).getText().toLowerCase();
                    verbo = Etiquetador.lemmas.get(verbo.toLowerCase());
                    if (verbo.toLowerCase().equals("permitir")) {
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PERMITIR);
                        etiquetasSentenca = etiquetasSentenca + "PERMITIR";
                        //System.out.print(palavraEtiquetada + ":" + "PERMITIR ");
                        elementosDaFraseSRS.addElemento(palavraEtiquetada, "PERMITIR");
                    } else {
                        if (verbos.contains(verbo.toLowerCase())) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.DEVE);
                            etiquetasSentenca = etiquetasSentenca + "DEVE";
                            // System.out.print(palavraEtiquetada + ":" + "DEVE ");
                            elementosDaFraseSRS.addElemento(palavraEtiquetada, "DEVE");
                        } else {
                            ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.VERB);
                            etiquetasSentenca = etiquetasSentenca + "VERB";
                            //System.out.print(palavraEtiquetada + ":" + "VERB ");
                            elementosDaFraseSRS.addElemento(palavraEtiquetada, "VERB");
                        }
                        elementosDaFraseSRS.addVerbo(verbo);
                    }
                    break;
                case 'N':
                    if (tokens.getTokens().get(i).getText().toLowerCase().equals("sistema") && i <= 2) {
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.SISTEMA);
                        etiquetasSentenca = etiquetasSentenca + "SISTEMA";
                        //System.out.print(palavraEtiquetada + ":" + "SISTEMA ");
                        elementosDaFraseSRS.addElemento(palavraEtiquetada, "SISTEMA");
                    } else {
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.SUBS);
                        etiquetasSentenca = etiquetasSentenca + "SUBS";
                        //System.out.print(palavraEtiquetada + ":" + "SUBS ");
                        elementosDaFraseSRS.addElemento(palavraEtiquetada, "SUBS");
                    }
                    break;
                case 'D':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.ART);
                    //System.out.print(palavraEtiquetada + ":" + "ART ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "ART");
                    break;
                case 'P':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PRON);
                    //System.out.print(palavraEtiquetada + ":" + "PRON ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "PRON");
                    break;
                case 'S':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PREP);
                    //System.out.print(palavraEtiquetada + ":" + "PREP ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "PREP");
                    break;
                case 'A':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.ADJ);
                    //System.out.print(palavraEtiquetada + ":" + "ADJ ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "ADJ");
                    break;
                case 'Z':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.NUM);
                    //System.out.print(palavraEtiquetada + ":" + "NUM ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "NUM");
                    break;
                case 'C':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.CONJ);
                    //System.out.print(palavraEtiquetada + ":" + "CONJ ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "CONJ");
                    break;
                case 'F':
                    try {
                        if (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(1) == 'c') {
                            ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.SEPARADOR);
                            //System.out.print(palavraEtiquetada + ":" + "SEPARADOR ");
                            elementosDaFraseSRS.addElemento(palavraEtiquetada, "SEPARADOR");
                        } else if (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(1) == 'd') {
                        } else {
                            if (tokens.getTokens().get(i).getText().toLowerCase().equals(".")) {
                                ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PONTO);
                                //System.out.print(palavraEtiquetada + ":" + "PONTO ");
                                elementosDaFraseSRS.addElemento(palavraEtiquetada, "PONTO");
                            } else {
                                ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.SIMBOLOS);
                                //System.out.print(palavraEtiquetada + ":" + "SIMBOLOS ");
                                elementosDaFraseSRS.addElemento(palavraEtiquetada, "SIMBOLOS");
                            }
                        }
                    } catch (Exception e) {
                        ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.PALAVRAESTRANGEIRA);
                        //System.out.print(palavraEtiquetada + ":" + "PALAVRAESTRANGEIRA ");
                        elementosDaFraseSRS.addElemento(palavraEtiquetada, "PALAVRAESTRANGEIRA");
                    }
                    break;
                case 'R':
                    ((CommonToken) tokens.getTokens().get(i)).setType(SrsGrammarParser.ADV);
                    //System.out.print(palavraEtiquetada + ":" + "ADV ");
                    elementosDaFraseSRS.addElemento(palavraEtiquetada, "ADV");
                    break;
            }
        }
        return (ArrayList<String>) tagger.getEtiquetas();
    }

    public static ArrayList<String> etiquetarDescricaoFuncionalidadeIntellisense(CommonTokenStream tokens) {
        String verbos = "#dever#poder#";
        etiquetasSentenca = "";
        ultimaPalavraSentenca = tokens.getTokens().get(tokens.getTokens().size() - 2).getText();

        for (int i = 0; i < tokens.getTokens().size() - 1; i++) {
            //Detecção e atribuição das etiquetas aos seus respectivos tokens
            String palavraEtiquetada = tokens.getTokens().get(i).getText().toLowerCase();
            String lemma = "";
            try {
                lemma = tagger.getLemma(palavraEtiquetada);

            } catch (Exception e) {
                // System.out.println("erro em: " + palavraEtiquetada);
                e.printStackTrace();
            }
            switch (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(0)) {
                case 'V':
                    String verbo = tokens.getTokens().get(i).getText().toLowerCase();
                    verbo = Etiquetador.lemmas.get(verbo.toLowerCase());
                    if (verbo.toLowerCase().equals("permitir")) {
                        ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.PERMITIR);
                        etiquetasSentenca = etiquetasSentenca + "PERMITIR";
                        //System.out.print(palavraEtiquetada + ":" + "PERMITIR ");
                    } else {
                        if (verbos.contains(verbo.toLowerCase())) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.DEVE);
                            etiquetasSentenca = etiquetasSentenca + "DEVE";
                            //System.out.print(palavraEtiquetada + ":" + "DEVE ");
                        } else {
                            ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.VERB);
                            etiquetasSentenca = etiquetasSentenca + "VERB";
                            //System.out.print(palavraEtiquetada + ":" + "VERB ");
                        }
                    }
                    break;
                case 'N':
                    if (tokens.getTokens().get(i).getText().toLowerCase().equals("sistema") && i <= 2) {
                        ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.SISTEMA);
                        etiquetasSentenca = etiquetasSentenca + "SISTEMA";
                        //System.out.print(palavraEtiquetada + ":" + "SISTEMA ");
                    } else {
                        ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.SUBS);
                        etiquetasSentenca = etiquetasSentenca + "SUBS";
                        //System.out.print(palavraEtiquetada + ":" + "SUBS ");
                    }
                    break;
                case 'D':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.ART);
                    //System.out.print(palavraEtiquetada + ":" + "ART ");
                    break;
                case 'P':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.PRON);
                    //System.out.print(palavraEtiquetada + ":" + "PRON ");
                    break;
                case 'S':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.PREP);
                    //System.out.print(palavraEtiquetada + ":" + "PREP ");
                    break;
                case 'A':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.ADJ);
                    //System.out.print(palavraEtiquetada + ":" + "ADJ ");
                    break;
                case 'Z':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.NUM);
                    //System.out.print(palavraEtiquetada + ":" + "NUM ");
                    break;
                case 'C':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.CONJ);
                    //System.out.print(palavraEtiquetada + ":" + "CONJ ");
                    break;
                case 'F':
                    try {
                        if (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(1) == 'c') {
                            ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.SEPARADOR);
                            //System.out.print(palavraEtiquetada + ":" + "SEPARADOR ");
                        } else if (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(1) == 'd') {
                        } else {
                            if (tokens.getTokens().get(i).getText().toLowerCase().equals(".")) {
                                ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.PONTO);
                                //System.out.print(palavraEtiquetada + ":" + "PONTO ");
                            } else {
                                ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.SIMBOLOS);
                                //System.out.print(palavraEtiquetada + ":" + "SIMBOLOS ");
                            }
                        }
                    } catch (Exception e) {
                        ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.PALAVRAESTRANGEIRA);
                        //System.out.print(palavraEtiquetada + ":" + "PALAVRAESTRANGEIRA ");
                    }
                    break;
                case 'R':
                    ((CommonToken) tokens.getTokens().get(i)).setType(IntellisenseGrammarParser.ADV);
                    //System.out.print(palavraEtiquetada + ":" + "ADV ");
                    break;
            }
        }
        return (ArrayList<String>) tagger.getEtiquetas();
    }

    public static ArrayList<String> etiquetarUC(CommonTokenStream tokens) {
        int iniciouSetras = -1;
        int inicio = -1;
        int fim = -1;
        int aspas = 0;
        boolean frasePara = false;
        boolean fraseEnquanto = false;

        String palavraAnterior = "";
        ultimaPalavraSentenca = tokens.getTokens().get(tokens.getTokens().size() - 2).getText();

        for (int i = 0; i < tokens.getTokens().size() - 1; i++) {
            //Detecção e atribuição das etiquetas aos seus respectivos tokens
            String palavraEtiquetada = tokens.getTokens().get(i).getText().toLowerCase();
            String lemma = "";
            try {
                lemma = tagger.getLemma(palavraEtiquetada);
            } catch (Exception e) {
                //System.out.println("erro em: " + palavraEtiquetada);
                e.printStackTrace();
            }
            if (Character.isDigit(palavraEtiquetada.charAt(0))) {
                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.NUMERO);
            } else {
                iniciouSetras++;
                if (i >= inicio && i <= fim) {
                    ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PALAVRASRESERVADAS);
                    palavraAnterior = tokens.getTokens().get(i).getText().toLowerCase();
                    continue;
                }
                if (palavraEtiquetada.equals("\"")) {
                    ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.ASPAS);
                    aspas = aspas == 0 ? 1 : 0;
                    continue;
                }
                if (aspas == 1) {
                    ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PALAVRA);
                    continue;
                }

                switch (tagger.getHashEtiquetas().get(palavraEtiquetada).charAt(0)) {
                    case 'V':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.VERB);
                        if (Constante.SUBSTANTIVADOR.contains("#" + palavraAnterior + "#")) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SUBS);
                        } else {
                            int numeroDePalavrasResevardas = ehPalavraReservada(tokens.getTokens(), i + 1);
                            if (numeroDePalavrasResevardas > 0) {
                                inicio = i + 1;
                                fim = i + numeroDePalavrasResevardas;
                            } else {
                                inicio = -1;
                                fim = -1;
                            }
                        }
                        break;
                    case 'N':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SUBS);
                        if (frasePara && (Etiquetador.hashVerboParticipio.get(palavraEtiquetada) != null
                                || Etiquetador.hashVerboGerundio.get(palavraEtiquetada) != null)) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.VERB);
                        }
                        break;
                    case 'D':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.ART);
//                        if (iniciouSetras == 1 && palavraEtiquetada.toLowerCase().equals("cada")) {
//                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.CADA);
//                        }
                        break;
                    case 'P':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PRON);
                        if (iniciouSetras == 0 && palavraEtiquetada.toLowerCase().equals("se")) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SE);
                        }
                        break;
                    case 'S':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PREP);
                        if (iniciouSetras == 0 && palavraEtiquetada.toLowerCase().equals("para")) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PARA);
                            frasePara = true;
                        }
                        break;
                    case 'A':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.ADJ);
                        if (Constante.COMPARADOR.contains(tokens.getTokens().get(i).getText().toLowerCase())) {
//                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.COMPARADOR);
                        }
                        break;
                    case 'Z':
                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.NUM);
                        break;
                    case 'C':
                        if (Constante.CONJUNCAO.contains(tokens.getTokens().get(i).getText().toLowerCase())) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.CONJUNCAO);
                        } else if (tokens.getTokens().get(i).getText().toLowerCase().equals("se")) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SE);
                        } else {
                            if (Constante.COMPARADOR.contains(tokens.getTokens().get(i - 1).getText().toLowerCase())) {
//                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.QUE);
                            } else {
                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.CONJ);
                            }
                        }
                        break;
                    case 'F':
                        try {
                            if (tokens.getTokens().get(i).getText().toLowerCase().equals(".")) {
                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PONTO);
                                iniciouSetras--;
                            } else {
                                if (tokens.getTokens().get(i).getText().toLowerCase().equals(",")) {
                                    ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.VIRGULA);
                                } else {
                                    if (tagger.getEtiquetas().get(i).charAt(1) == 'c' || tagger.getEtiquetas().get(i).charAt(1) == 'p') {
                                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.TERMINAL);
                                    } else if (tagger.getEtiquetas().get(i).charAt(1) == 'd') {
                                    } else {
                                        ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SIMBOLOS);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.PALAVRAESTRANGEIRA);
                        }
                        break;
                    case 'R':
                        if (tokens.getTokens().get(i).getText().toLowerCase().equals("então")) {
                            ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.ENTAO);
                        } else {
                            if (tokens.getTokens().get(i).getText().toLowerCase().equals("senão")) {
                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SENAO);
                            } else if (iniciouSetras == 0 && palavraEtiquetada.toLowerCase().equals("enquanto")) {
                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.ENQUANTO);
                                fraseEnquanto = true;
                            } else if (Constante.SUBSTANTIVADOR.contains("#" + palavraAnterior + "#")) {
                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.SUBS);
                            } else {
                                ((CommonToken) tokens.getTokens().get(i)).setType(UCGrammarParser.ADV);
                            }
                        }
                        break;
                } //Case
            } //if chaAt
            palavraAnterior = tokens.getTokens().get(i).getText().toLowerCase();
        }

        return (ArrayList<String>) tagger.getEtiquetas();
    }

    public String recuperarClassePalavra(String palavra) {
        String tag = Etiquetador.hashEtiquetas.get(palavra.toLowerCase());
        try {

            switch (tag.charAt(0)) {
                case 'V':
                    return "VERBO";
                case 'N':
                    return "SUBSTANTIVO";
                case 'D':
                    return "DETERMINANTE";
                case 'P':
                    return "PRONOME";
                case 'S':
                    return "PREPOSIÇÂO";
                case 'A':
                    return "ADJETIVO";
                case 'Z':
                    return "NUMERAL";
                case 'C':
                    return "CONJUNÇÂO";
                case 'F':
                    try {
                        return "SINAL DE PONTUAÇÂO";

                    } catch (Exception e) {
                        return "TERMO NAO RECONHECIDO";
                    }
                case 'R':
                    return "ADVERBIO";
            }
        } catch (Exception e) {
            return "ERRO NÂO IDENTIFICADO";
        }
        return "TERMO NÃO RECONHECIDO";
    }

    /**
     * @todo Este procedimento nao serve para termos compostos
     */
    public static String recuperaLemma(String de) {
        String retorno = "";
        for (StringTokenizer stringTokenizer = new StringTokenizer(de); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            String lemmaS = "";
            String lemma = Etiquetador.lemmas.get(token.toLowerCase());
            if (lemma != null && !lemma.contains("+")) {
                lemmaS = lemma;
            } else {
                lemmaS = token;
            }
            if (retorno.equals("")) {
                retorno = lemmaS;
            } else {
                retorno = retorno + " " + lemmaS;
            }
        }
        return retorno;
    }

    private ArrayList<String> eliminarFuncionalidades(ArrayList<String> funcionalidades) {
        String verbosParaExcluir = "#exibir#inserir#executar#deletar#gravar#clicar#abrir#selecionar#fechar#alterar#permitir#";
        ArrayList<String> novasFuncionalidades = new ArrayList<>();
        for (String funcionalidade : funcionalidades) {
            int espaco = funcionalidade.indexOf(" ");
            if (espaco != -1) {

                String verbo = funcionalidade.substring(0, espaco);
                String resto = funcionalidade.substring(espaco + 1, funcionalidade.length());
                String verboInfinitivo = Etiquetador.lemmas.get(verbo.toLowerCase());
                Sinonimo sinonimo = sinonimoDAO.buscaPorDe(verboInfinitivo, null);
                verboInfinitivo = sinonimo == null ? verboInfinitivo : sinonimo.getIdVerbo().getDe();

                if (!verbosParaExcluir.contains(verboInfinitivo) && !palavrasExcluir.contains(verboInfinitivo)) {
                    novasFuncionalidades.add(verboInfinitivo + " " + resto);
                }
            }

        }
        return novasFuncionalidades;
    }

    private ArrayList<String> eliminarElementos(ArrayList<String> elementosFrase) {
        ArrayList<String> listExcluir = new ArrayList<>();
        for (String excluir : elementosFrase) {
            int espaco = excluir.indexOf(" ");
            for (StringTokenizer stringTokenizer = new StringTokenizer(excluir); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken().toLowerCase();
                String lemma = Etiquetador.lemmas.get(token.toLowerCase());
                if (espaco == -1 && palavrasExcluir.contains(lemma)) {
                    listExcluir.add(excluir);
                }
            }
        }
        elementosFrase.removeAll(listExcluir);
        return elementosFrase;
    }

    private ArrayList<String> eliminarDuplicidade(ArrayList<String> elementosFrase, ArrayList<String> funcionalidades) {
        ArrayList<String> paraExcluir = new ArrayList<>();
        for (String elementodDafrase : funcionalidades) {
            String[] palavras = elementodDafrase.split(" ");
            String verbo = palavras[0];
            if (elementosFrase.contains(verbo)) {
                paraExcluir.add(verbo);
            }
        }

        elementosFrase.removeAll(paraExcluir);
        elementosFrase.addAll(funcionalidades);
        return elementosFrase;
    }

    private ArrayList<String> eliminarPreposicoesNoFinal(ArrayList<String> elementosFrase) {
        ArrayList<String> elementosFraseTemp = new ArrayList<>();
        for (String conceitoComplexo : elementosFrase) {
            String objeto = eliminarPreposicoesNoFinal(conceitoComplexo);
            elementosFraseTemp.add(objeto);
        }
        return elementosFraseTemp;
    }

    private String eliminarPreposicoesNoFinal(String conceitoComplexo) {
        int limite = 0;
        String objeto = "";
        String[] palavras = conceitoComplexo.split(" ");

        String etiq = Etiquetador.hashEtiquetas.get(palavras[palavras.length - 1].toLowerCase());
        if (etiquetaUltimaNgramaPalavrasExcluir.contains("#" + etiq + "#")) {
            limite = palavras.length - 1;
        } else {
            limite = palavras.length;
        }
        for (int i = 0; i <= limite - 1; i++) {
            if (objeto.equals("")) {
                objeto = palavras[i];
            } else {
                objeto = objeto + " " + palavras[i];
            }
        }
        return objeto;
    }

    private HashMap<Integer, ArrayList<String>> corrigirConceitosTipados(HashMap<Integer, ArrayList<String>> conceitosTipados) throws Exception {
        HashMap<Integer, ArrayList<String>> conceitosTipadosTemp = new HashMap<Integer, ArrayList<String>>();
        for (Map.Entry<Integer, ArrayList<String>> entrySet : conceitosTipados.entrySet()) {
            Integer key = entrySet.getKey();
            ArrayList<String> value = entrySet.getValue();
            ArrayList<String> valueTemp = eliminarPreposicoesNoFinal(value);
            conceitosTipadosTemp.put(key, valueTemp);
            if (key == Constante.USUARIO || key == Constante.REQUISITOS_DE_ARMAZENAMENTO) {
                for (String e : valueTemp) {
                    if (!this.elementosFrase.contains(e)) {
                        this.elementosFrase.add(e);
                    }
                }
            } else if (key == Constante.QQQ) {
                adicionarElementosDaFraseOrigemQQQ(valueTemp);
            }
        }
        return conceitosTipadosTemp;
    }

    private void adicionarElementosDaFraseOrigemQQQ(ArrayList<String> elementosFraseOriginal) throws Exception {

        for (int i = 0; i <= elementosFraseOriginal.size() - 1; i++) {
            String elementoDaFrase = elementosFraseOriginal.get(i);
            String palavras[] = elementoDaFrase.split(" ");
            if (palavras.length > 2) {
                ArrayList<String> maisComplementos = recuperarOutrosConceitosComplexos(elementoDaFrase);
                for (String maisComplemento : maisComplementos) {
                    if (!this.elementosFrase.contains(maisComplemento)) {
                        this.elementosFrase.add(maisComplemento);
                    }
                }
            } else {
                if (!this.elementosFrase.contains(elementoDaFrase)) {
                    this.elementosFrase.add(elementoDaFrase);
                }
            }
        }

    }

    public static HashMap<String, String> getLemmas() {
        return Etiquetador.lemmas;
    }

    public static HashMap<String, String> getHashPalavraDoLemma() {
        return Etiquetador.hashPalavraDoLemma;
    }

    public static HashMap<String, String> getHashEtiquetas() {
        return Etiquetador.hashEtiquetas;
    }

    //incluir este método e sua chamada no outro projeto
    private HashMap<String, ArrayList<Integer>> eliminarPreposicaoFinDoConceito(HashMap<String, ArrayList<Integer>> podeSer) {
        HashMap<String, ArrayList<Integer>> temp = new HashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entrySet : podeSer.entrySet()) {
            String key = entrySet.getKey();
            ArrayList<Integer> value = entrySet.getValue();
            key = eliminarPreposicoesNoFinal(key);
            temp.put(key, value);
        }
        return temp;
    }
}
