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
import br.ufpi.dc.entidades.entity.CasoDeUso;
import br.ufpi.dc.entidades.entity.Isr;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.tools.StopWords;
import br.ufpi.dc.entidades.entity.Tabela;
import br.ufpi.dc.entidades.entity.Conceito;
import br.ufpi.dc.entidades.entity.tools.MensagemErro;
import br.ufpi.dc.entidades.entity.tools.TipoTabela;
import br.ufpi.dc.controle.grammar.SrsGrammarParser;
import br.ufpi.dc.controle.grammar.tools.UnderlineListener;
import br.ufpi.dc.controle.grammar.tools.VerboseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import org.antlr.works.debugger.tree.DBASTModel;
import org.apache.pdfbox.cos.COSName;
import org.apache.poi.hssf.record.formula.functions.Char;
import br.ufpi.dc.tools.Constante;
import br.ufpi.dc.tools.ElementosFrase;
//import visao.view.Editor;

/**
 *
 * @author helcio.soares
 */
public class ParserDescricaoFuncao extends AbstractParser {

    private Projeto projetoSelecionado;
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private TreeBuilder treeBuilder;
    private String errosFimdeSentenca = "#missing PONTO at '<EOF>'#no viable alternative at input '<EOF>'#";

    public ParserDescricaoFuncao(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
        treeBuilder = new TreeBuilder(dicionarioDAO, projetoSelecionado);
    }

    @Override
    public DefaultListModel avaliaFrase(String frase) {
        DefaultListModel listIntellisense = new DefaultListModel();
        String proximo = "";
        AnalisePeriodo analisePeriodo = new AnalisePeriodo(frase);
        String etiquetaUltimaPalavra = "";
        String ultimaPalavra = "";
        try {
            listIntellisense = new DefaultListModel();
            if (frase.trim().equals("") || frase.trim().toLowerCase().equals("o")
                    || frase.trim().toLowerCase().equals("a")) {
                listIntellisense = retornaListaConformeProximo("usuario", listIntellisense);
                listIntellisense.add(0, "sistema deve ");
            } else {
                Editor.jTextArea1.setText("");
                UnderlineListener.erroList.clear();
                proximo = analisePeriodo.intellisense(frase, false);
                String etiquetas = analisePeriodo.etiquetasSentenca;
                ultimaPalavra = AnalisePeriodo.ultimaPalavraSentenca;
                etiquetaUltimaPalavra = AnalisePeriodo.getHashEtiquetas().get(ultimaPalavra);

                if (etiquetas.equals("SISTEMA") || etiquetas.equals("SUBS")) {
                    listIntellisense.add(0, "deve");
                    listIntellisense.add(1, "pode");
                    listIntellisense.add(2, "deverá");
                    listIntellisense.add(3, "poderá");
                } else if (etiquetas.equals("SISTEMADEVE")) {
                    listIntellisense = retornaListaConformeProximo("verbo", listIntellisense);
                    listIntellisense.add(0, "permitir");
                } else if (etiquetas.equals("SUBSDEVE")) {
                    listIntellisense = retornaListaConformeProximo("verbo", listIntellisense);
                } else if (etiquetaUltimaPalavra.charAt(0) == 'V' && proximo.contains("verbo")) {
                    listIntellisense = retornaListaConformeProximo("classe", listIntellisense);
                } else {
                    listIntellisense = retornaListaConformeProximo(proximo, listIntellisense);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ParserDescricaoFuncao.class.getName()).log(Level.SEVERE, null, ex);
            listIntellisense = null;
        }
        return listIntellisense;
    }

    private DefaultListModel retornaListaConformeProximo(String proximo, DefaultListModel listIntellisense) {
        List<String> listaOpcoes = new ArrayList<>();
        if (proximo.contains("verbo")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.ACAO, this.projetoSelecionado.getId()));
        }
        if (proximo.contains("classe")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.REQUISITOS_DE_ARMAZENAMENTO, this.projetoSelecionado.getId()));
        }
        if (proximo.contains("usuario")) {
            listaOpcoes.addAll(buildListIntellisense(Constante.USUARIO, this.projetoSelecionado.getId()));
        }
        if (proximo.contains("deve")) {
            listaOpcoes.add(0, "deve");
            listaOpcoes.add(0, "pode");
        }
        listIntellisense = listToDefaultListModel(listaOpcoes);
        return listIntellisense;
    }

    public Root validarSentenca(String frase) throws Exception {
        ArrayList<DefaultMutableTreeNode> noTemp = new ArrayList<DefaultMutableTreeNode>();
        int tipoFrase = 0;
        Editor.jTextArea1.setText("");
        AnalisePeriodo analisePeriodo = new AnalisePeriodo(frase);
        analisePeriodo.sintagmas(frase);
        tipoFrase = analisePeriodo.tipoFrase;
        ArrayList<String> erroList = UnderlineListener.erroList;
        DefaultMutableTreeNode raizE = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.RAIZ_, Constante.RAIZ, null));
        DefaultMutableTreeNode raizA = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.RAIZ_, Constante.RAIZ, null));
        DefaultMutableTreeNode raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SEM_ERRO_, Constante.SEM_ERRO, null));
        DefaultMutableTreeNode raizAvisos = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, Constante.AVISOS, null));
        DefaultMutableTreeNode raizExplorar = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, Constante.AVISOS, null));

        ArrayList<String> elementosDaFrase = analisePeriodo.elementosFrase;
        // elementosDaFrase = Constante.trocarPorListaDeLemma(elementosDaFrase);
        elementosDaFrase = Constante.eliminarRepetidosPorLemma(elementosDaFrase, 1);
        elementosDaFrase = Constante.trocarPorsinonimo(elementosDaFrase, 2);

        String textTemp = Editor.jTextArea1.getText();
        textTemp = textTemp + "\n Tipo da sentença:" + tipoFrase;
        Editor.jTextArea1.setText(textTemp);
        ElementosFrase e = analisePeriodo.srsParser.elementosDaFrase;
        String mensagemSentencaForaDoPadrao = verificaTipoDeFrase(e);
        //Construir Lista de Erros.
        if (mensagemSentencaForaDoPadrao.isEmpty() && tipoFrase != 0) {
            if (erroList.size() > 0) {
                raizErro = construirListaErros(analisePeriodo, erroList);
                raizE.add(raizErro);
                noTemp = treeBuilder.construirListaAvisos(elementosDaFrase, new HashMap<String, ElementoFrase>());
                raizAvisos = noTemp.get(0);
                raizA.add(raizAvisos);
                raizExplorar = noTemp.get(1);
            } else {
                raizErro = construirListaErrosSemantico(analisePeriodo.conceitosTipados, analisePeriodo.srsParser.podeSer);
                // Construir lista de Avisos
                raizE.add(raizErro);
                noTemp = treeBuilder.construirListaAvisos(elementosDaFrase, new HashMap<String, ElementoFrase>());
                raizAvisos = noTemp.get(0);
                raizA.add(raizAvisos);
                raizExplorar = noTemp.get(1);
            }

        } else {
            raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.FORA_DO_PADRAO_, Constante.FORA_DO_PADRAO, null));
            if (!mensagemSentencaForaDoPadrao.isEmpty()) {
                DefaultMutableTreeNode no = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.FORA_DO_PADRAO_, mensagemSentencaForaDoPadrao, null));
                raizErro.add(no);
            }
            DefaultMutableTreeNode outroNo = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.FORA_DO_PADRAO_, "Voce deve escrever a sentença na ordem direta: ator + ação + objeto + complemento", null));
            raizErro.add(outroNo);
            raizE.add(raizErro);
            noTemp = treeBuilder.construirListaAvisos(elementosDaFrase, new HashMap<String, ElementoFrase>());
            raizAvisos = noTemp.get(0);
            raizA.add(raizAvisos);
            raizExplorar = noTemp.get(1);
        }

        DefaultTreeModel treeModelErros = new DefaultTreeModel(raizE);
        DefaultTreeModel treeModelAvisos = new DefaultTreeModel(raizA);
        DefaultTreeModel treeModelExplorar = new DefaultTreeModel(raizExplorar);

        treeBuilder.desenharRaiz(treeModelErros, treeModelAvisos, treeModelExplorar);
        Root raiz = new Root(treeModelErros, treeModelAvisos, treeModelExplorar);
        return raiz;

    }

    private String verificaTipoDeFrase(ElementosFrase e) {
        String mensagemSentencaForaDoPadrao = "";
        String verbosErrados = "";
        for (int i = 0; i <= e.elemento.size() - 1; i++) {

            if (e.tipoElemento.get(i).contains("VERB")) {
                String verbo = e.elemento.get(i);
                String lemmaVerbo = AnalisePeriodo.recuperaLemma(verbo);
                if (Constante.verbosDeLigacao.contains(lemmaVerbo)) {
                    verbosErrados = verbosErrados.equals("") ? "(" + verbo : verbosErrados + ", " + verbo;
                }
            }
        }
        if (!verbosErrados.isEmpty()) {
            verbosErrados = verbosErrados + ")";
            mensagemSentencaForaDoPadrao = verbosErrados.indexOf(",") == -1 ? "O verbo " + verbosErrados + " não representa uma ação."
                    : "Os verbos " + verbosErrados + " não representam uma ação.";
        }
        return mensagemSentencaForaDoPadrao;
    }

    private DefaultMutableTreeNode construirListaErrosSemantico(HashMap<Integer, ArrayList<String>> conceitosTipados, HashMap<String, ArrayList<Integer>> podeSer) throws Exception {
        DefaultMutableTreeNode treeNodeTipoEsperado = null;
        DefaultMutableTreeNode treeNodeTipoEncontrado = null;
        DefaultMutableTreeNode treeNodePalavraErrada = null;
        DefaultMutableTreeNode raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Erro de semântica", null));

        for (Map.Entry<Integer, ArrayList<String>> entrySet : conceitosTipados.entrySet()) {
            Integer tipoConceitoEsperado = entrySet.getKey();
            ArrayList<String> elmentosDaFraseTipatos = entrySet.getValue();
            if (tipoConceitoEsperado.equals(Constante.USUARIO) || tipoConceitoEsperado.equals(Constante.REQUISITOS_DE_ARMAZENAMENTO)) {
                for (String elementoDaFrase : elmentosDaFraseTipatos) {

                    //Verificar se existem sinonimos apenas para aqueles termos que fazem parte do léxico.
                    String lemmaElementoDaFrase = Constante.recuperarLemmaDaPalavra(elementoDaFrase);
                    List<String> listaSinonimo = dicionarioDAO.retornaSinonimos(lemmaElementoDaFrase, projetoSelecionado);
                    listaSinonimo.add(lemmaElementoDaFrase);
                    for (String lemmaElementoDaFrase1 : listaSinonimo) {
                        //Atualizar aqui no outro projeto
                        List<Integer> listaTiposRecuperados = dicionarioDAO.recuperarTipoPorNomeLemma(lemmaElementoDaFrase1, projetoSelecionado.getId());
                        if (!listaTiposRecuperados.isEmpty()) {
                            if (tipoConceitoEsperado.equals(Constante.USUARIO) || tipoConceitoEsperado.equals(Constante.REQUISITOS_DE_ARMAZENAMENTO)) {
                                //Criar método validarUsuario
                                int tamanholistaTiposRecuperados = listaTiposRecuperados.size();
                                ArrayList tiposQuePodeSer = podeSer.get(elementoDaFrase);
                                System.out.println("ERRO:" + elementoDaFrase);
                                if (!tiposQuePodeSer.isEmpty()) {
                                     listaTiposRecuperados.removeAll(podeSer.get(elementoDaFrase));
                                }
//                                if (!listaTiposRecuperados.contains(tipoConceitoEsperado)) {
                                if (listaTiposRecuperados.size()==tamanholistaTiposRecuperados) {
                                    String sTiposEncontrados = "";
                                    for (Integer listaTiposRecuperado : listaTiposRecuperados) {
                                        if (sTiposEncontrados.equals("")) {
                                            sTiposEncontrados = Constante.hashTipoTabela.get(listaTiposRecuperado).getDe();
                                        } else {
                                            sTiposEncontrados = sTiposEncontrados + "/" + Constante.hashTipoTabela.get(listaTiposRecuperado);
                                        }
                                    }
                                    treeNodeTipoEsperado = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SEM_ERRO_, "Esperado: " + Constante.hashTipoTabela.get(tipoConceitoEsperado), null));
                                    treeNodeTipoEncontrado = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_ENCONTRADO_, "Encontrado: " + sTiposEncontrados, null));
                                    treeNodePalavraErrada = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_ENCONTRADO_, elementoDaFrase, null));
                                    treeNodePalavraErrada.add(treeNodeTipoEncontrado);
                                    treeNodePalavraErrada.add(treeNodeTipoEsperado);
                                    raizErro.add(treeNodePalavraErrada);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (raizErro.getChildCount() == 0) {
            raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SEM_ERRO_, Constante.SEM_ERRO, null));
        }
        return raizErro;
    }

    private DefaultMutableTreeNode construirListaErros(AnalisePeriodo analisePeriodo, ArrayList<String> erroList) {
        DefaultMutableTreeNode treeNodePalavraErrada = null;
        DefaultMutableTreeNode treeNodeTipoEsperado = null;
        DefaultMutableTreeNode treeNodeTipoEncontrado = null;

        DefaultMutableTreeNode raizErro = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ERRO_, "Erro de sintaxe", null));
        for (String erro : erroList) {
            String palavraErrada = "";
            String esperado = "";
            String encontrado = "";
            if (!errosFimdeSentenca.contains("#" + erro + "#")) {
                erro = retirarAspas(erro);
                String[] linhas = erro.split("#");
                erro = linhas[0];
                palavraErrada = linhas[1];
                esperado = Constante.mensagensHash.get("1" + erro);

                if (esperado == null) {
                    esperado = erro;
                }
                encontrado = analisePeriodo.recuperarClassePalavra(palavraErrada);
            } else {
                palavraErrada = "Fim de sentença";
                esperado = "Esperado: PONTO final.";
            }
            treeNodeTipoEsperado = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SEM_ERRO_, esperado, null));
            treeNodeTipoEncontrado = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_ENCONTRADO_, "Encontrado: " + encontrado, null));
            treeNodePalavraErrada = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_ENCONTRADO_, palavraErrada, null));
            treeNodePalavraErrada.add(treeNodeTipoEncontrado);
            treeNodePalavraErrada.add(treeNodeTipoEsperado);
            raizErro.add(treeNodePalavraErrada);
        }
        return raizErro;
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

}
