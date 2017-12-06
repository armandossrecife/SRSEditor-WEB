package br.ufpi.dc.controle.dicionario;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.dao.DicionarioDAO;
import br.ufpi.dc.dao.JPAUtil;
import br.ufpi.dc.controle.analiseTexto.AnalisePeriodo;
import br.ufpi.dc.entidades.entity.tools.ClasseGramatical;
import br.ufpi.dc.entidades.entity.DadoRadical;
import br.ufpi.dc.entidades.entity.Dados;
import br.ufpi.dc.entidades.entity.Isr;
import br.ufpi.dc.entidades.entity.Lemma;
import br.ufpi.dc.entidades.entity.Palavra;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.Radical;
import br.ufpi.dc.entidades.entity.Referencia;
import br.ufpi.dc.entidades.entity.tools.StopWords;
import br.ufpi.dc.tools.Arquivo;
import br.ufpi.dc.tools.Constante;
import br.ufpi.dc.tools.LerPdfTxt;
import br.ufpi.dc.tools.Numeric;
import br.ufpi.dc.tools.Sigla;
import br.ufpi.dc.tools.Timer;
import br.ufpi.dc.entidades.entity.Tabela;
import br.ufpi.dc.entidades.entity.Acao;
import br.ufpi.dc.entidades.entity.Atributo;
import br.ufpi.dc.entidades.entity.CasoDeUso;
import br.ufpi.dc.entidades.entity.Conceito;
import br.ufpi.dc.entidades.entity.tools.Sinonimo;
import br.ufpi.dc.entidades.entity.SinonimoDominio;
import br.ufpi.dc.controle.grammar.SrsGrammarLexer;
import br.ufpi.dc.controle.grammar.SrsGrammarParser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import br.ufpi.dc.controle.nGramas.NGrama;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ptstemmer.Stemmer;
import ptstemmer.exceptions.PTStemmerException;
import br.ufpi.dc.controle.sinonimo.SinonimoTools;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author helcio.soares
 */
public class Dicionario {

    private ArrayList<String> oracoes = new ArrayList<>();
    private DAO<Referencia> referenciaDAO = new DAO(Referencia.class);
    private DAO<Radical> radicalDAO = new DAO(Radical.class);
    private DAO<Palavra> palavraDAO = new DAO(Palavra.class);
    private DAO<Lemma> lemmaDAO = new DAO(Lemma.class);
    private DAO<Dados> dadosDAO = new DAO(Dados.class);
    private DAO<DadoRadical> dadoRadicalDAO = new DAO(DadoRadical.class);
    private DAO<Projeto> dadosProjetoDAO = new DAO(Projeto.class);
    private DAO<StopWords> stopWordsDAO = new DAO(StopWords.class);
    private DAO<ClasseGramatical> classeGramaticalDAO = new DAO(ClasseGramatical.class);
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    public DAO<Conceito> conceitoDAO = new DAO(Conceito.class);
    private DAO<Acao> acaoDAO = new DAO(Acao.class);
    private DAO<SinonimoDominio> sinonimoDominioDAO = new DAO(SinonimoDominio.class);
    private DAO<Sinonimo> sinonimoDAO = new DAO(Sinonimo.class);

    private HashMap<String, StopWords> stopWordsHash = new HashMap<String, StopWords>();
    private HashMap<String, Referencia> referenciasHash = new HashMap<String, Referencia>();
    private HashMap<String, Palavra> palavrasHash = new HashMap<String, Palavra>();
    private HashMap<String, Lemma> lemmaHash = new HashMap<String, Lemma>();
    private HashMap<String, Radical> radicaisHash = new HashMap<String, Radical>();
    private HashMap<String, Dados> dadosHash = new HashMap<String, Dados>();
    private HashMap<String, DadoRadical> dadoRadicalHash = new HashMap<String, DadoRadical>();
    private HashMap<String, Projeto> projetoHash = new HashMap<String, Projeto>();
    private HashMap<Integer, ClasseGramatical> classeGramaticalHash = new HashMap<Integer, ClasseGramatical>();
    private HashMap<String, String> siglas = new HashMap<String, String>();
    private ArrayList<String> arquivos;
    private ArrayList<String> listaconeitos = new ArrayList<>();
    private ArrayList<String> listaVerbos = new ArrayList<>();
    private HashMap<String, String> palavraLemma = new HashMap<String, String>();
    private String nomeArquivo;
    private Projeto projetoSelecionado;

    private String verbosProibidos = "#haver#ser#ter#possuir#ficar#ajudar#existir#ir#vir#dever#permitir#possibilitar#inserir#"
            + "acessar#selecionar#proceder#utilizar#corresponder#administrar";

    private String substantivosParaIgnorar = "dados#campos#botão#busca#destino#contendo#critérios#maneira#estabelecidos"
            + "#criação#cadastrado#vedada#opção#ajustes#opção#campos#busca#informações#informação#contendo#critérios#maneira#"
            + "estabelecidos#criação#vedada#ajustes#campos#período#digitados#exercício#salva#mostrando#efetuados#lista#chamado#encontrando"
            + "#correções#responsáveis#cadastrados#parâmetros#cadastros#tribunal";

    String conceitosComplexosParaIgnorar = "situação processada#sagres contabilidade#sagres sistema#sistema de prestação#"
            + "chamado de sagres#importação da pc#programas de contabilidade#feita mediante lançamentos#"
            + "contas clicando#contas selecionada#simplificada ao tribunal#acompanhamento dos gastos#inconsistência do tipo#"
            + "contas do mês#retroação à competência#jurisdicionado anexa#hipótese de ocorrência#efetuados no mês"
            + "#sistema de acompanhamento#sociedade sagres#enviada ao tce#contas gerada#redução de envio#";

    private ArrayList<NGrama> conceitosComplexosView = new ArrayList<>();
    private ArrayList<NGrama> funcionalidadesOrdenadasView = new ArrayList<>();
    private ArrayList<NGrama> listaVerbosOrdenadosParaAView = new ArrayList<>();
    private ArrayList<NGrama> listaConceitosSimplesOrdenados;

    private Map<String, Object> params = new HashMap<String, Object>();

    public Integer numeroPrimeiraLinha;

    private AnalisePeriodo analisePeriodo;

    public Dicionario() {
    }

    public Dicionario(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public ArrayList<NGrama> getListaConceitosSimplesOrdenados() {
        return listaConceitosSimplesOrdenados;
    }

    public ArrayList<String> getListaVerbos() {
        return listaVerbos;
    }

    public HashMap<String, String> getSiglas() {
        return siglas;
    }

    public void construirDicionario(Projeto idProjeto) throws IOException, Exception {
        this.projetoSelecionado = idProjeto;
        Arquivo tempo = new Arquivo("tempo.dat", true);
        Timer timer = new Timer();
        carregarBanco(idProjeto);
        String textoLido = "";
        LerPdfTxt lerPdfTxt = new LerPdfTxt();

        for (String nomeArquivo : arquivos) {
            System.out.println("Arquivo: " + nomeArquivo);
            File fileName = new File(Constante.DIRETORIO + nomeArquivo);
            if (fileName.getName().endsWith(".pdf")) {
                textoLido = lerPdfTxt.lerPDF(fileName);
            } else if ((fileName.getName().endsWith(".txt"))) {
                textoLido = lerPdfTxt.lerTXT(fileName);
            } else if (fileName.getName().endsWith(".doc")) {
                try {
                    textoLido = lerPdfTxt.lerDoc(fileName);
                } catch (IOException ex) {
                    Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            timer.elapsed("Inicio");
            analisaAssunto(nomeArquivo, textoLido);
            tempo.escreveArquivo(nomeArquivo + "#" + timer.elapsed("fim") + "\n");
            System.gc();
        }

        timer.elapsed("Inicio");
        gravar();
        construirListas(idProjeto);
        tempo.escreveArquivo("Tempo de gravação#" + timer.elapsed("fim") + "\n");
        tempo.fecha();
    }

    private void analisaAssunto(String nomeArquivo, String texto) throws Exception {
        //ArrayList<String> tags = null;
        ArrayList<String> tagsMorf = null;
        CommonTokenStream tokensMorf = null;
        CommonTokenStream tokens = null;
        Referencia referenciaBanco = referenciasHash.get(nomeArquivo);
        referenciaBanco.setQtd(0);
        referenciaDAO.atualiza(referenciaBanco);
        this.nomeArquivo = nomeArquivo;
        SrsGrammarLexer lexer = new SrsGrammarLexer(new ANTLRInputStream(texto));
        tokens = new CommonTokenStream(lexer);
        SrsGrammarParser parser = new SrsGrammarParser(tokens);
        System.out.println(tokens.getText());

        analisePeriodo = new AnalisePeriodo(texto);

        SrsGrammarLexer lexerMorf = new SrsGrammarLexer(new ANTLRInputStream(texto));
        tokensMorf = new CommonTokenStream(lexerMorf);
        System.out.println(tokensMorf.getText());
        tagsMorf = etiquetar(tokensMorf);

        try {
            Sigla sigla = new Sigla();
            siglas.putAll(sigla.reconheceSiglas(tokens));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator it1 = tokens.getTokens().iterator();
        CommonToken ob = null;
        int qtdPalavrasRelevantes = 0;
        int qtdRadicais = 0;

        while (it1.hasNext()) {
            ob = (CommonToken) it1.next();
            //Retirando o caractere '-' do inicio da palavra
            if (ob.getText().length() > 1 && ob.getText().charAt(0) == '-') {
                ob.setText(ob.getText().substring(1));
            }
            //Retirando o caractere '-' do fim da palavra
            if (ob.getText().length() > 1 && (ob.getText().charAt(ob.getText().length() - 1) == '-')) {
                ob.setText(ob.getText().substring(0, ob.getText().length() - 2));
            }

            String palavra = ob.getText().toLowerCase();
            String palavraLemmaa = ob.getText();

            //Filtrando as palavras relevantes do texto
            if ((ob.getType() == SrsGrammarLexer.PALAVRA) && (ob.getText().length() > 4) && stopWordsHash.get(palavra) == null && !ehTudoIgual(palavra)) {
                qtdPalavrasRelevantes++;

                String radical;
                //Recuperando radical da palavra
                //Obtem radical utilizando o PTStemmer
                radical = getRadicalPTStemmer(palavra);

                String lemma;
                //Obtem lemma utilizando o TreeTagger
//                lemma = AnalisePeriodo.tagger.getLemma(palavraLemmaa);
                lemma = AnalisePeriodo.recuperaLemma(palavraLemmaa);
                palavraLemma.put(palavraLemmaa, lemma);

                Lemma lemmaBanco = new Lemma(null, lemma);
                Radical radicalBanco = new Radical(null, radical);
                Palavra palavraBanco = new Palavra(null, palavra, lemmaBanco, radicalBanco);

                if (!palavrasHash.containsKey(palavra)) {
                    palavrasHash.put(palavra, palavraBanco);
                }

                if (!radicaisHash.containsKey(radical)) {
                    radicaisHash.put(radical, radicalBanco);
                }

                if (!lemmaHash.containsKey(lemma)) {
                    lemmaHash.put(lemma, lemmaBanco);
                }

                Dados dadosBanco = new Dados();
                dadosBanco.setF(1);
                dadosBanco.setIdPalavra(palavraBanco);
                dadosBanco.setIdRadical(radicalBanco);
                dadosBanco.setIdReferencia(referenciaBanco);
                dadosBanco.setIdProjeto(projetoSelecionado);
                String chaveDados = projetoSelecionado.getId() + referenciaBanco.getId() + radical + palavra;
                if (!dadosHash.containsKey(chaveDados)) {
                    dadosHash.put(chaveDados, dadosBanco);
                    palavraBanco = palavrasHash.get(palavra);
                    dadosBanco.setQtdDoc(dadosBanco.getQtdDoc() + 1);
                } else {
                    Integer i = dadosHash.get(chaveDados).getF() + 1;
                    dadosBanco.setF(i);
                    dadosBanco.setQtdDoc(1);
                    dadosHash.replace(chaveDados, dadosBanco);
                }

                DadoRadical dadoRadicalBanco = new DadoRadical();
                dadoRadicalBanco.setF(1);
                dadoRadicalBanco.setIdRadical(radicalBanco);
                dadoRadicalBanco.setIdReferencia(referenciaBanco);
                dadoRadicalBanco.setIdProjeto(projetoSelecionado);
                String chaveDadoRadical = projetoSelecionado.getId() + referenciaBanco.getId() + radical;
                if (!dadoRadicalHash.containsKey(chaveDadoRadical)) {
                    dadoRadicalHash.put(chaveDadoRadical, dadoRadicalBanco);
                    radicalBanco = radicaisHash.get(radical);
                    dadoRadicalBanco.setQtdDoc(dadoRadicalBanco.getQtdDoc() + 1);
                } else {
                    Integer i = dadoRadicalHash.get(chaveDadoRadical).getF() + 1;
                    dadoRadicalBanco.setF(i);
                    dadoRadicalBanco.setQtdDoc(1);
                    dadoRadicalHash.replace(chaveDadoRadical, dadoRadicalBanco);
                }

            }
        }
        referenciaBanco.setQtdRelevante(qtdPalavrasRelevantes);
        referenciaBanco.setPercRelevante(((float) qtdPalavrasRelevantes / tokens.size()) * 100);
        referenciaDAO.atualiza(referenciaBanco);

        tagsMorf = etiquetar(tokensMorf);

        ParseTree tree;
        tree = parser.init();
        oracoes = parser.oracoes;
        ArrayList<String> oracoes1 = new ArrayList<>();
        for (String oracao : oracoes) {
            if (!oracao.equals("\n")) {
                oracoes1.add(oracao);
            }
        }
        parser.oracoes.clear();
        parser.oracoes.addAll(oracoes1);

        addAbreviatura();

    }

    private void gravar() throws IOException, Exception {
//        for (Map.Entry<String, Lemma> entry : lemmaHash.entrySet()) {
//            Lemma lemma = entry.getValue();
//            if (lemma.getId() == null) {
//                lemmaDAO.adiciona(lemma);
//            } else {
//                lemmaDAO.atualiza(lemma);
//            }
//        }
//
//        for (Map.Entry<String, Radical> entry : radicaisHash.entrySet()) {
//            Radical radical = entry.getValue();
//            if (radical.getId() == null) {
//                radicalDAO.adiciona(radical);
//            } else {
//                radicalDAO.atualiza(radical);
//            }
//        }
//        String sqlPalavra = "select p from Palavra p where p.de = :palavra";
//        params.clear();
//
//        for (Map.Entry<String, Palavra> entry : palavrasHash.entrySet()) {
//            Palavra palavra = entry.getValue();
//            if (palavra.getId() == null) {
//                palavra.setIdLemma(lemmaHash.get(palavra.getIdLemma().getDe()));
//                palavra.setIdRadical(radicaisHash.get(palavra.getIdRadical().getDe()));
//                palavra.setIdClasseGramatical(palavrasHash.get(palavra.getDe()).getIdClasseGramatical());
//                params.put("palavra", palavra.getDe());
//                if (palavraDAO.buscaPorCampos(params, sqlPalavra) == null) {
//                    palavraDAO.adiciona(palavra);
//                    palavra = palavraDAO.buscaPorCampos(params, sqlPalavra);
//                }
//            }
//        }

        int i = 0;
        for (Map.Entry<String, Dados> entry : dadosHash.entrySet()) {
            Dados dado = entry.getValue();
            Dados gr = new Dados();

            Palavra palavra = palavrasHash.get(dado.getIdPalavra().getDe());
            palavra = adicionarPalavra(palavra);
            gr.setIdProjeto(dado.getIdProjeto());
            gr.setIdPalavra(palavra);
            Radical radical = adicionarRadical(radicaisHash.get(dado.getIdRadical().getDe()));
            gr.setIdRadical(radical);
            gr.setIdReferencia(referenciasHash.get(dado.getIdReferencia().getNomeArquivo()));
            gr.setF(dado.getF());
            gr.setQtdDoc(dado.getQtdDoc());
            gr.setRf(((float) gr.getF() / gr.getIdReferencia().getQtdRelevante()) * 100);
            gr.setIdf((float) gr.getF() / gr.getQtdDoc());
            gr.setIdf(Numeric.formatFloat(gr.getIdf()));
            gr.setRf(Numeric.formatFloat(gr.getRf()));
            //   gr.setId(i);
            i++;

            Dados dadosDoBanco = dicionarioDAO.getIdDados(projetoSelecionado.getId(), gr.getIdReferencia().getId(), gr.getIdRadical().getId(), gr.getIdPalavra().getId());

            if (dadosDoBanco == null) {
                dadosDAO.adiciona(gr);
            } else {
                if (!dadosDoBanco.igual(gr)) {
                    dadosDoBanco.merge(gr);
                    dadosDAO.atualiza(dadosDoBanco);
                }
            }
        }

        i = 0;
        for (Map.Entry<String, DadoRadical> entry : dadoRadicalHash.entrySet()) {
            DadoRadical dadoRadical = entry.getValue();
            DadoRadical gr = new DadoRadical();

            gr.setIdProjeto(dadoRadical.getIdProjeto());
            Radical radical = adicionarRadical(radicaisHash.get(dadoRadical.getIdRadical().getDe()));

            gr.setIdRadical(radical);
            gr.setIdReferencia(referenciasHash.get(dadoRadical.getIdReferencia().getNomeArquivo()));
            gr.setF(dadoRadical.getF());
            gr.setQtdDoc(dadoRadical.getQtdDoc());

            gr.setRf(((float) gr.getF() / gr.getIdReferencia().getQtdRelevante()) * 100);
            gr.setIdf((float) gr.getF() * gr.getQtdDoc());
            gr.setIdf(Numeric.formatFloat(gr.getIdf()));
            gr.setRf(Numeric.formatFloat(gr.getRf()));
            gr.setId(i);
            i++;
            DadoRadical dadoRadicalDoBanco = dicionarioDAO.getIdDadoRadical(projetoSelecionado.getId(),
                    gr.getIdReferencia().getId(), gr.getIdRadical().getDe());
            if (dadoRadicalDoBanco == null) {
//                System.out.println("dadosBanco: " + gr);
//                System.out.println("GR: " + gr);
                dadoRadicalDAO.adiciona(gr);
            } else {
                if (!dadoRadicalDoBanco.igual(gr)) {
                    dadoRadicalDoBanco.merge(gr);
                    dadoRadicalDAO.atualiza(dadoRadicalDoBanco);
                }
            }
        }
    }

    private Palavra adicionarPalavra(Palavra palavra) {
        palavra.setIdLemma(adicionarLemma(palavra.getIdLemma()));
        palavra.setIdRadical(adicionarRadical(palavra.getIdRadical()));
        Palavra palavraBanco = palavraDAO.buscaPorDe(palavra.getDe(), null);

        if (palavraBanco == null) {
            palavraDAO.adiciona(palavra);
            return palavra;
        }
        return palavraBanco;
    }

    private Lemma adicionarLemma(Lemma lemma) {
        Lemma lemmaBanco = lemmaDAO.buscaPorDe(lemma.getDe(), null);

        if (lemmaBanco == null) {
            lemmaDAO.adiciona(lemma);
            return lemma;
        }
        return lemmaBanco;
    }

    private Radical adicionarRadical(Radical radical) {
        Radical radicalBanco = radicalDAO.buscaPorDe(radical.getDe(), null);

        if (radicalBanco == null) {
            radicalDAO.adiciona(radical);
            return radical;
        }
        return radicalBanco;
    }

    public static boolean ehTudoIgual(String s) {
        boolean b = true;
        char a = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            b = b && a == s.charAt(i);
        }
        return b;
    }

    private String getRadicalPTStemmer(String palavra) {
        Stemmer st = null;
        try {
            st = Stemmer.StemmerFactory(Stemmer.StemmerType.SAVOY);
        } catch (PTStemmerException ex) {
            ex.printStackTrace();
        }
        st.enableCaching(10000);
        return st.getWordStem(palavra);
    }

    public String preProcesamento(String texto) {
        String stringTemp = "";
        for (int i = 0; i < texto.length() - 1; i++) {

        }
        return stringTemp;
    }

    public void addAbreviatura() {
//        for (Map.Entry<String, String> entrySet : siglas.entrySet()) {
//            String key = entrySet.getKey();
//            String value = entrySet.getValue();
//            siglasEncontradas.append(key + " - " + value);
//        }
    }

    private ArrayList<String> etiquetar(CommonTokenStream tokens) {
        ArrayList<String> temp;
        ArrayList<String> sTokens = new ArrayList<>();
        ArrayList<String> simbolos = new ArrayList<>();
        temp = analisePeriodo.etiquetar(tokens);

        for (int i = 0; i < tokens.getTokens().size() - 1; i++) {
            int typeToken = ((CommonToken) tokens.getTokens().get(i)).getType();
            String sToken = ((CommonToken) tokens.getTokens().get(i)).getText();

            try {
                ClasseGramatical classeGramatical = classeGramaticalHash.get(typeToken);
                if (classeGramatical == null) {
                    classeGramatical = classeGramaticalDAO.buscaPorId(11);
                }
                palavrasHash.get(sToken.toLowerCase()).setIdClasseGramatical(classeGramatical);
            } catch (Exception e) {
            }
        }
        return temp;
    }

    private void carregarBanco(Projeto idProjeto) {
        stopWordsHash = carregaStopWords();
        referenciasHash = carregaReferencia(idProjeto);
        palavrasHash = carregaPalavras();
        classeGramaticalHash = carregaClasseGramatical();
        lemmaHash = carregaLemma();
        radicaisHash = carregaRadical();
    }

    HashMap<String, Palavra> carregaPalavras() {
        HashMap<String, Palavra> hashTemp = new HashMap<String, Palavra>();
        List<Palavra> palavras = palavraDAO.listaTodos();
        for (Palavra palavra : palavras) {
            hashTemp.put(palavra.getDe(), palavra);
        }
        return hashTemp;
    }

    HashMap<String, Radical> carregaRadical() {
        HashMap<String, Radical> hashTemp = new HashMap<String, Radical>();
        List<Radical> radicais = radicalDAO.listaTodos();
        for (Radical radical : radicais) {
            hashTemp.put(radical.getDe(), radical);

        }
        return hashTemp;
    }

    HashMap<String, Referencia> carregaReferencia(Projeto idProjeto) {
        arquivos = new ArrayList<String>();
        HashMap<String, Referencia> hashTemp = new HashMap<>();
        List<Referencia> referencias = referenciaDAO.listaTodos();
        for (Referencia referencia : referencias) {
            hashTemp.put(referencia.getNomeArquivo(), referencia);
            if (referencia.getIdProjeto().equals(idProjeto)) {
                arquivos.add(referencia.getNomeArquivo());
            }
        }
        return hashTemp;
    }

    HashMap<String, StopWords> carregaStopWords() {
        HashMap<String, StopWords> hashTemp = new HashMap<>();
        List<StopWords> stopWords = stopWordsDAO.listaTodos();
        for (StopWords stopWord : stopWords) {
            hashTemp.put(stopWord.getDe(), stopWord);
        }
        return hashTemp;
    }

    HashMap<String, Dados> carregaDados(Projeto idProjeto) {
        HashMap<String, Dados> hashTemp = new HashMap<>();
        List<Dados> dados = dadosDAO.listaTodos();
        for (Dados dado : dados) {
            String chaveDados = projetoSelecionado.getId() + dado.getIdReferencia().getId()
                    + dado.getIdRadical().getDe() + dado.getIdPalavra().getDe();
            hashTemp.put(chaveDados, dado);
        }
        return hashTemp;
    }

    HashMap<String, DadoRadical> carregaDadoRadical(Projeto idProjeto) {
        HashMap<String, DadoRadical> hashTemp = new HashMap<>();
        List<DadoRadical> dadosRadical = dadoRadicalDAO.listaTodos();
        for (DadoRadical dadoRadical : dadosRadical) {
            String chaveDadoRadical = projetoSelecionado.getId() + dadoRadical.getIdReferencia().getId()
                    + dadoRadical.getIdRadical().getDe();
            hashTemp.put(chaveDadoRadical, dadoRadical);
        }
        return hashTemp;
    }

    private HashMap<Integer, ClasseGramatical> carregaClasseGramatical() {
        HashMap<Integer, ClasseGramatical> hashTemp = new HashMap<>();
        List<ClasseGramatical> dadosclasseGramatical = classeGramaticalDAO.listaTodos();
        for (ClasseGramatical dadoclasseGramatical : dadosclasseGramatical) {
            hashTemp.put(dadoclasseGramatical.getId(), dadoclasseGramatical);
        }
        return hashTemp;
    }

    private HashMap<String, Lemma> carregaLemma() {
        HashMap<String, Lemma> hashTemp = new HashMap<String, Lemma>();
        List<Lemma> lemmas = lemmaDAO.listaTodos();
        for (Lemma lemma : lemmas) {
            hashTemp.put(lemma.getDe(), lemma);
        }
        return hashTemp;
    }

    public void construirListas(Projeto idProjeto) throws Exception {

        for (Map.Entry<String, String> entrySet : palavraLemma.entrySet()) {
            String palavra = entrySet.getKey();
            String lemma = entrySet.getValue();

        }
        List listaRadicais = dicionarioDAO.getAnaliseIdf(idProjeto);
        listaConceitosSimplesOrdenados = new ArrayList<>();
        for (int i = 0; i < listaRadicais.size() - 1; i++) { //Definir ponto de corte
            Object[] objRadical = (Object[]) listaRadicais.get(i);
            Integer idRadical = (Integer) objRadical[0];
            List l = dicionarioDAO.getPalavraMaisFrequanteIdf(idProjeto, idRadical);
            //System.out.println(idRadical);
            Object[] ob = (Object[]) l.get(0);
            String temp = (String) ob[4];
            Integer idClasseGramatical = (Integer) ob[0];
            Long f = (Long) ob[5];
            if (!substantivosParaIgnorar.contains(temp) && idClasseGramatical.equals(SrsGrammarParser.SUBS)) {
                listaconeitos.add(temp);
                NGrama nGrama = new NGrama(temp, new Double(f));
                listaConceitosSimplesOrdenados.add(nGrama);
            }

        }
        Collections.sort(listaConceitosSimplesOrdenados);

        analisePeriodo.analisarTexto1(oracoes);
        conceitosComplexosView = analisePeriodo.conceitosComplexosOrdenadosParaView;
        funcionalidadesOrdenadasView = analisePeriodo.funcionalidadesOrdenadasView;
        refinarListaFuncionalidades();
        refinarListaVerbos();
        refinarListaDeConceitosComplexos();
        gravarListas();
    }

    private void refinarListaFuncionalidades() {
        ArrayList<NGrama> funcionalidadesParaExluir = new ArrayList<>();
        String conjugacaoVerbo = "ar#er#ir";
        SinonimoTools sinonimoTools = new SinonimoTools(projetoSelecionado);

        for (NGrama nGrama : funcionalidadesOrdenadasView) {
            String funcionalidade = nGrama.getDe();
            //System.out.println("Funcionalidade: " + nGrama.getDe());
            String[] palavrasFuncionalidade = funcionalidade.split(" ");
            String verbo = palavrasFuncionalidade[0];
            String objeto = "";
            int limite = 0;
            String etiq = AnalisePeriodo.tagger.getHashEtiquetas().get(palavrasFuncionalidade[palavrasFuncionalidade.length - 1]);
            if (AnalisePeriodo.etiquetaUltimaNgramaPalavrasExcluir.contains("#" + etiq + "#")) {
                limite = palavrasFuncionalidade.length - 1;
            } else {
                limite = palavrasFuncionalidade.length;
            }
            for (int i = 1; i < limite; i++) {
                if (objeto.equals("")) {
                    objeto = palavrasFuncionalidade[i];
                } else {
                    objeto = objeto + " " + palavrasFuncionalidade[i];
                }
            }
            //String etiqueta = AnalisePeriodo.getHashEtiquetas().get(verbo);
            String etiqueta = AnalisePeriodo.tagger.getHashEtiquetas().get(verbo);

            if (etiqueta.equals("VMN") || etiqueta.equals("VMI")) {
                //   System.out.println("VERBO QUE DEU Pau " + verbo);
                String lemma = AnalisePeriodo.getLemmas().get(verbo);
                if (validaVerboDasFuncionalidades(verbo, lemma)) {
                    if (verbo.length() > 4 && conjugacaoVerbo.contains(verbo.substring(verbo.length() - 2, verbo.length()))) {
                        if (lemma != null) {
                            verbo = lemma;
                        }
                    } else {
                        funcionalidadesParaExluir.add(nGrama);
                    }
                    verbo = sinonimoTools.recuperaSinonimo(verbo);
                    String func = verbo + " " + objeto;
                    nGrama.setDe(func);
                    //gravar verbo + substantivo na tabela de ação.
                    try {
                        Acao acao = new Acao();
                        acao.setIdProjeto(projetoSelecionado);
                        acao.setVerbo(verbo);
                        acao.setObjeto(objeto);
                        acaoDAO.adiciona(acao);

                    } catch (Exception e) {
                        System.out.println("PROBLEMA NA FUNCIONALIDADE:" + func);
                    }
                } else {
                    funcionalidadesParaExluir.add(nGrama);
                }
            } else {
                funcionalidadesParaExluir.add(nGrama);
            }

        }

        funcionalidadesOrdenadasView.removeAll(funcionalidadesParaExluir);
    }

    private boolean validaVerboDasFuncionalidades(String verbo, String lemma) {

        if (stopWordsHash.containsKey(verbo)) {
            return false;
        }

        if (verbosProibidos.contains(lemma)) {
            return false;
        }

        String modo = AnalisePeriodo.tagger.getHashEtiquetas().get(verbo).substring(2);
        if (modo.equals("P")) {
            return false;
        }

        return true;
    }

    private void refinarListaVerbos() {
        //  System.out.println("Verbos.:");
        Lemma lemma;
        for (NGrama nGrama : funcionalidadesOrdenadasView) {
            String funcionalidade = nGrama.getDe();
            String[] palavrasFuncionalidade = funcionalidade.split(" ");
            String verbo = palavrasFuncionalidade[0];
            String radical = verbo.substring(0, verbo.length() - 2);
            NGrama verboOrdenado = dicionarioDAO.recuperaFrequenciaPalavra(radical, projetoSelecionado);
            verboOrdenado.setDe(verbo);
            listaVerbosOrdenadosParaAView.add(verboOrdenado);
        }
        Collections.sort(listaVerbosOrdenadosParaAView);

    }

    public void construirListaAtores() {
        carregarBanco(projetoSelecionado);
        try {
            ArrayList<String> sTokens = new ArrayList<>();
            ArrayList<String> simbolos = new ArrayList<>();
            analisePeriodo.analisarTexto(oracoes, sTokens, simbolos);
        } catch (Exception ex) {
            Logger.getLogger(Dicionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void refinarListaDeConceitosComplexos() {
        ArrayList<NGrama> conceitosComplexosParaExcluir = new ArrayList<>();
        for (NGrama conceitosComplexo : conceitosComplexosView) {
            String[] palavrasConceito = conceitosComplexo.getDe().split(" ");
            String conceito1 = palavrasConceito[0];
            String conceito2 = palavrasConceito[palavrasConceito.length - 1];
            if (!(aplicaCorteConceitosSimples(conceito1) || aplicaCorteConceitosSimples(conceito2))
                    || (substantivosParaIgnorar.contains(conceito1) || substantivosParaIgnorar.contains(conceito2))) {
                conceitosComplexosParaExcluir.add(conceitosComplexo);
            }
        }

        for (NGrama conceitosComplexosParaExcluir1 : conceitosComplexosParaExcluir) {
            conceitosComplexosView.remove(conceitosComplexosParaExcluir1);
        }
    }

    private boolean aplicaCorteConceitosSimples(String conceito) {
        int corte = (listaconeitos.size() * Constante.CORTE) / 100;
        for (int i = 0; i < corte; i++) {
            if (listaConceitosSimplesOrdenados.get(i).getDe().equals(conceito)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<NGrama> aplicaCorteLista(ArrayList<NGrama> lista) {
        int corte = (lista.size() * 50) / 100;
        ArrayList<NGrama> listaTemp = new ArrayList<>();
        for (int i = corte + 1; i <= lista.size() - 1; i++) {
            listaTemp.add(lista.get(i));
        }
        boolean removeAll = lista.removeAll(listaTemp);
        return lista;
    }

    public void gravarLista(ArrayList<NGrama> lista, int tipoConceito) {
        DAO<Conceito> conceitoDAO = new DAO(Conceito.class);

        for (NGrama nGrama : lista) {
            if (!conceitosComplexosParaIgnorar.contains(nGrama.getDe())) {
                String deLemma = recuperaLemma(nGrama.getDe());
                Conceito conceitoBanco = dicionarioDAO.recuperarConceito(deLemma, projetoSelecionado, tipoConceito);
                if (conceitoBanco == null) {
                    conceitoBanco = new Conceito();
                    conceitoBanco.setDe(nGrama.getDe());
                    conceitoBanco.setF(nGrama.getFreq());
                    conceitoBanco.setIdProjeto(projetoSelecionado.getId());
                    conceitoBanco.setUtilizado(0);

                    if (tipoConceito == Constante.ACAO && sinonimoDAO.buscaPorDe(nGrama.getDe(), null) != null) {
                        conceitoBanco.setUtilizado(-1);
                    }

                    conceitoBanco.setIdTipoConceito(tipoConceito);
                    conceitoBanco.setNomeLemma(deLemma);
                    conceitoDAO.adiciona(conceitoBanco);
                } else {
                    conceitoBanco.setF(nGrama.getFreq());
                    conceitoDAO.atualiza(conceitoBanco);

                }
            }
        }
    }

    public void gravarListaConceito(ArrayList<NGrama> lista, int tipoConceito) {
        DAO<Conceito> conceitoDAO = new DAO(Conceito.class);
        int corte = (lista.size() * Constante.CORTE) / 100;
        int i = 0;
        for (NGrama nGrama : lista) {
            i++;
            if (i <= corte) {
                String deLemma = recuperaLemma(nGrama.getDe());
                Conceito conceitoBanco = dicionarioDAO.recuperarConceito(deLemma, projetoSelecionado, tipoConceito);
                if (conceitoBanco == null) {
                    conceitoBanco = new Conceito();
                    conceitoBanco.setDe(nGrama.getDe());
                    conceitoBanco.setF(nGrama.getFreq());
                    conceitoBanco.setIdProjeto(projetoSelecionado.getId());
                    if (i > corte) {
                        conceitoBanco.setUtilizado(-1);
                    } else {
                        conceitoBanco.setUtilizado(0);
                    }
                    conceitoBanco.setIdTipoConceito(tipoConceito);
                    conceitoBanco.setNomeLemma(deLemma);
                    //System.out.println("Conceito que deu pau: " + conceitoBanco.getDe());
                    conceitoDAO.adiciona(conceitoBanco);
                } else {
                    conceitoBanco.setF(nGrama.getFreq());
                    conceitoDAO.atualiza(conceitoBanco);
                }
            }
        }
    }

    private void gravarListas() {
        gravarListaConceito(listaConceitosSimplesOrdenados, Constante.CONCEITO_SIMPLES);
        gravarLista(conceitosComplexosView, Constante.CONCEITO_COMPOSTO);
        gravarLista(listaVerbosOrdenadosParaAView, Constante.ACAO);
        gravarLista(funcionalidadesOrdenadasView, Constante.FUNCIONALIDADE);

        ArrayList<NGrama> listaSiglas = new ArrayList<>();
        for (Map.Entry<String, String> entrySet : siglas.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            NGrama nGrama = new NGrama(key + "#" + value, new Double("0"));
            listaSiglas.add(nGrama);
        }
        gravarLista(listaSiglas, Constante.SIGLA);
    }

    public void gravarTabela(Conceito conceito, Projeto projetoSelecionado, JMenuItem j) throws Exception {
        int idTipoTabela = j.getDisplayedMnemonicIndex();

        switch (idTipoTabela) {
            case 0:
                gravarAtributo(conceito, projetoSelecionado, j);
                break;
            case Constante.REQUISITOS_DE_ARMAZENAMENTO:
                gravarIsr(conceito, projetoSelecionado);
                break;
            case Constante.CASO_DE_USO:
                gravarCasoDeUso(conceito, projetoSelecionado);
                break;            default:
                gravarTabela(conceito, projetoSelecionado, idTipoTabela);
        }
    }

    private void gravarCasoDeUso(Conceito conceito, Projeto projetoSelecionado) {
        DAO<CasoDeUso> casoDeUsoDAO = new DAO(CasoDeUso.class);
        CasoDeUso casoDeUsoBanco = casoDeUsoDAO.buscaPorNomeLemma(conceito.getNomeLemma(), projetoSelecionado.getId());
        if (casoDeUsoBanco == null) {
            casoDeUsoBanco = new CasoDeUso();
            casoDeUsoBanco.setIdProjeto(projetoSelecionado);
            casoDeUsoBanco.setNome(conceito.getDe());
            casoDeUsoBanco.setNomeLemma(conceito.getNomeLemma());
            casoDeUsoDAO.adiciona(casoDeUsoBanco);
        } else {
            casoDeUsoBanco.setNomeLemma(conceito.getNomeLemma());
            casoDeUsoDAO.atualiza(casoDeUsoBanco);
        }

    }

    private void gravarIsr(Conceito conceito, Projeto projetoSelecionado) {
        DAO<Isr> isrDAO = new DAO(Isr.class);
        Isr isrBanco = isrDAO.buscaPorNomeLemma(conceito.getNomeLemma(), projetoSelecionado.getId());
        if (isrBanco == null) {
            isrBanco = new Isr();
            isrBanco.setIdProjeto(projetoSelecionado);
            isrBanco.setNome(conceito.getDe());
            isrBanco.setNomeLemma(conceito.getNomeLemma());
            isrDAO.adiciona(isrBanco);
        } else {
            isrBanco.setNomeLemma(conceito.getNomeLemma());
            isrDAO.atualiza(isrBanco);
        }
    }

    private void gravarTabela(Conceito conceito, Projeto projetoSelecionado, int idTipoTabela) {
        DAO<Tabela> tabelaDAO = new DAO(Tabela.class);
        Tabela tabelaBanco = tabelaDAO.buscaPorNome(conceito.getDe(), projetoSelecionado.getId());
        if (tabelaBanco == null) {
            tabelaBanco = new Tabela();
            tabelaBanco.setNome(conceito.getDe());
            tabelaBanco.setNomeLemma(conceito.getNomeLemma());
            tabelaBanco.setIdProjeto(projetoSelecionado);
            tabelaBanco.setIdTipoTabela(idTipoTabela);
            tabelaDAO.adiciona(tabelaBanco);
        } else {
            tabelaBanco.setIdTipoTabela(idTipoTabela);
            tabelaBanco.setNomeLemma(conceito.getNomeLemma());
            tabelaDAO.atualiza(tabelaBanco);
        }
    }

    private String recuperaLemma(String de) {
        String retorno = "";
        for (StringTokenizer stringTokenizer = new StringTokenizer(de); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            String lemmaS = "";
            String lemma = palavraLemma.get(token);
            if (lemma != null) {
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

    public void agruparSinonimos(int[] linhasSelecionadas, int numeroPrimeiraLinha, java.util.List<br.ufpi.dc.entidades.entity.Conceito> listaSelecionada) throws Exception {
        Conceito c1 = (Conceito) listaSelecionada.get(numeroPrimeiraLinha);
        String sinonimo = c1.getNomeLemma();

        params.clear();
        params.put("idProjeto", projetoSelecionado);
        params.put("chave", sinonimo);

        SinonimoDominio sinonimoDominioSinonimo = sinonimoDominioDAO.buscaPorCampos(params, Constante.QUERYSINONIMOCHAVE);
        if (sinonimoDominioSinonimo == null) {
            sinonimoDominioSinonimo = new SinonimoDominio();
            sinonimoDominioSinonimo.setIdProjeto(projetoSelecionado);
            sinonimoDominioSinonimo.setChave(sinonimo);
            sinonimoDominioSinonimo.setSinonimo(sinonimo);
            sinonimoDominioSinonimo.setConceitoChave(c1);
            sinonimoDominioSinonimo.setConceitoSinonimo(c1);
            sinonimoDominioDAO.adiciona(sinonimoDominioSinonimo);
        } else {
            sinonimoDominioSinonimo.setChave(sinonimo);
            sinonimoDominioSinonimo.setSinonimo(sinonimo);
            sinonimoDominioSinonimo.setConceitoChave(c1);
            sinonimoDominioSinonimo.setConceitoSinonimo(c1);
            sinonimoDominioDAO.atualiza(sinonimoDominioSinonimo);
        }

        for (int idx = 0; idx < linhasSelecionadas.length; idx++) {
            if (linhasSelecionadas[idx] != numeroPrimeiraLinha) {
                Conceito c2 = (Conceito) listaSelecionada.get(linhasSelecionadas[idx]);
                String chave = c2.getNomeLemma();
                params.put("chave", chave);
                SinonimoDominio sinonimoDominio = sinonimoDominioDAO.buscaPorCampos(params, Constante.QUERYSINONIMOCHAVE);
                Conceito c3 = conceitoDAO.buscaPorNomeLemma(chave, projetoSelecionado.getId());

                if (sinonimoDominio == null) {
                    sinonimoDominio = new SinonimoDominio();
                    sinonimoDominio.setIdProjeto(projetoSelecionado);
                    sinonimoDominio.setChave(chave);
                    sinonimoDominio.setSinonimo(sinonimo);
                    sinonimoDominio.setConceitoChave(c2);
                    sinonimoDominio.setConceitoSinonimo(c1);
                    sinonimoDominioDAO.adiciona(sinonimoDominio);
                } else {
                    sinonimoDominio.setChave(chave);
                    sinonimoDominio.setSinonimo(sinonimo);
                    sinonimoDominio.setConceitoChave(c2);
                    sinonimoDominio.setConceitoSinonimo(c1);
                    sinonimoDominioDAO.atualiza(sinonimoDominio);
                }
                c3.setUtilizado(-1);
                conceitoDAO.atualiza(c3);
            }
        }
    }

    public void desAgruparSinonimos(int[] linhasSelecionadas, int numeroPrimeiraLinha, java.util.List<br.ufpi.dc.entidades.entity.Conceito> listaSelecionada) throws Exception {
        Conceito c1 = (Conceito) listaSelecionada.get(numeroPrimeiraLinha);
        String sinonimo = c1.getNomeLemma();

        String selectSinonimo = "select s from SinonimoDominio s "
                + "where  s.idProjeto.id = " + projetoSelecionado.getId() + "\n"
                + "and s.sinonimo = '" + sinonimo + "'";

        String updateSinonimo = "update SinonimoDominio     \n"
                + "set sinonimo = null, conceito_chave = null, coneito_sinonimo = null \n"
                + " where idProjeto.id = " + projetoSelecionado.getId() + "\n"
                + " and   sinonimo  = '" + sinonimo + "'";

        String deleteSinonimo = "delete from SinonimoDominio     \n"
                + " where idProjeto.id = " + projetoSelecionado.getId() + "\n"
                + " and   sinonimo  = '" + sinonimo + "'";

        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();

        Query q = em.createQuery(selectSinonimo);
        List<SinonimoDominio> listSinonimoDominio = q.getResultList();

        for (SinonimoDominio sinonimoDominio : listSinonimoDominio) {
            Conceito conceito = conceitoDAO.buscaPorNomeLemma(sinonimoDominio.getChave(), projetoSelecionado.getId());
            conceito.setUtilizado(0);
            conceitoDAO.atualiza(conceito);
        }

        em.getTransaction().begin();
        Query q1 = em.createQuery(deleteSinonimo);
        q1.executeUpdate();
        em.getTransaction().commit();

    }

    public JTable selecionarLinhas(JTable tabelaSelecionada, List<Conceito> listaSelecionada, Integer numeroPrimeiraLinha) {
        JTable temp = tabelaSelecionada;
        Conceito c1 = (Conceito) listaSelecionada.get(numeroPrimeiraLinha);
        String chave = c1.getNomeLemma();
        Map<String, Object> params = new HashMap<String, Object>();
        ListSelectionModel selectionModel = tabelaSelecionada.getSelectionModel();

        if (tabelaSelecionada.getSelectedRowCount() == 1) {
            params.put("idProjeto", projetoSelecionado);
            params.put("chave", chave);
            SinonimoDominio sinonimoDominio = sinonimoDominioDAO.buscaPorCampos(params, Constante.QUERYSINONIMOCHAVE);
            params.remove("chave");
            if (sinonimoDominio != null) {
                String sinonimo = sinonimoDominio.getSinonimo();
                params.put("sinonimo", sinonimo);

                List<SinonimoDominio> listaSinonimoDominio = sinonimoDominioDAO.filtrarPorCampos(params, Constante.QUERYSINONIMOSINONIMO);
                if (listaSinonimoDominio.size() > 0) {
                    int ordem = 0;
                    for (SinonimoDominio sinonimoTemp : listaSinonimoDominio) {
                        int i = listaSelecionadaIndexOf(listaSelecionada, sinonimoTemp.getChave());
                        if (sinonimoTemp.getChave().equals(sinonimoTemp.getSinonimo())) {
                            numeroPrimeiraLinha = i;
                        }
                        if (i >= 0) {
                            if (ordem == 0) {
                                selectionModel.setSelectionInterval(i, i);
                            } else {
                                selectionModel.addSelectionInterval(i, i);
                            }
                        }
                        ordem++;
                    }
                }
            }
            this.numeroPrimeiraLinha = numeroPrimeiraLinha;
        }
        return temp;
    }

    private int listaSelecionadaIndexOf(List<Conceito> listaSelecionada, String chave) {
        for (int i = 0; i <= listaSelecionada.size() - 1; i++) {
            if (listaSelecionada.get(i).getNomeLemma().equals(chave)) {
                return i;
            }
        }
        return -1;
    }

    private void gravarAtributo(Conceito conceito, Projeto projetoSelecionado, JMenuItem j) throws Exception {
        DAO<Isr> isrDAO = new DAO(Isr.class);
        DAO<Atributo> atributoDAO = new DAO(Atributo.class);
        String lemma = Constante.recuperarLemmaDaPalavra(j.getText());
        Isr isr = isrDAO.buscaPorNomeLemma(lemma, projetoSelecionado.getId());
        params.clear();
        params.put("idProjeto", projetoSelecionado);
        params.put("idIsr", isr);
        params.put("nomeLemma", conceito.getNomeLemma());
        String sql = "select a from Atributo a where a.idProjeto = :idProjeto and a.nomeLemma = :nomeLemma and a.idIsr = :idIsr";
        Atributo a = atributoDAO.buscaPorCampos(params, sql);

        if (a == null) {
            a = new Atributo();
            a.setIdIsr(isr);
            a.setNome(conceito.getDe());
            a.setNomeLemma(conceito.getNomeLemma());
            a.setValidado(1);
            a.setIdProjeto(projetoSelecionado);
            String dadosArmazenados = isr.getDadosArmazenados();
            if (dadosArmazenados == null) {
                isr.setDadosArmazenados("- " + conceito.getDe());
            } else if (!dadosArmazenados.toLowerCase().contains(conceito.getDe())) {
                isr.setDadosArmazenados(dadosArmazenados + "\n- " + conceito.getDe());
            }
            Isr isrOrigem = isrDAO.buscaPorNomeLemma(conceito.getNomeLemma(), projetoSelecionado.getId());

            if (isrOrigem != null) {
                a.setIdIsrOrigem(isrOrigem);
            } else {
                a.setIdIsrOrigem(isr);
            }
            atributoDAO.adiciona(a);
            isrDAO.atualiza(isr);
        }
    }

}
