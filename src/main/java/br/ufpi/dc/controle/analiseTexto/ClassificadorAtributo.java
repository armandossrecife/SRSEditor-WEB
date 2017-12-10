/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.analiseTexto;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.dao.DicionarioDAO;
//import visao.view.SRSJTree.TreeBuilder;
//import visao.view.SRSJTree.TreeProblemasNode;
import br.ufpi.dc.entidades.entity.Atributo;
import br.ufpi.dc.entidades.entity.Isr;
import br.ufpi.dc.entidades.entity.Projeto;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import br.ufpi.dc.tools.Constante;
//import visao.view.Editor;
//import static visao.view.Editor.jTreeExplorar;

/**
 *
 * @author helcio.soares
 */
public class ClassificadorAtributo {

    private DAO<Atributo> atributoDAO = new DAO(Atributo.class);
    private ArrayList<String> elementosDaArvore;
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private TreeBuilder treeBuilder;
    private Isr isr;
    private Projeto projetoSelecionadoSelecionado;

    public ClassificadorAtributo(Projeto projetoSelecionadoSelecionado) {
        this.projetoSelecionadoSelecionado = projetoSelecionadoSelecionado;
        treeBuilder = new TreeBuilder(dicionarioDAO, projetoSelecionadoSelecionado);
    }

    public TreeProblemasNode classificarAtributo(ActionEvent evt, TreeProblemasNode node) throws Exception {

        JMenuItem j = (JMenuItem) evt.getSource();
        Atributo atributo = (Atributo) node.getBaseEntity();
        switch (j.getDisplayedMnemonicIndex()) {
            case 0:
                if (verificarSeAtributoEhDuplicado(atributo)) {
                    node.setTipoNode(Constante.ATRIBUTO_DUPLICADO);
                } else {
                    atributo.setIdIsrOrigem(atributo.getIdIsr());
                }
                if (atributo.getId() == null) { //Ferificar se existe um atributo com mesmo nome e projeto, se existir atualizá-lo
                    atributo.setValidado(1);
                    Atributo a1 = dicionarioDAO.recuperarAtributoNaoClassificado(atributo.getNomeLemma(), projetoSelecionadoSelecionado);
                    if (a1 != null) {
                        atributo.setId(a1.getId());
                        atributoDAO.atualiza(atributo);
                    } else {
                        atributoDAO.adiciona(atributo);
                    }
                } else {
                    atributo.setValidado(1);
                    atributoDAO.atualiza(atributo);
                }

                break;
            case 1:
                //if (!atributo.getIdIsr().equals(atributo.) )
                atributo.setValidado(0);
                atributo.setIdIsr(null);
                atributo.setIdIsrOrigem(null);
                if (node.getNomeOriginal()!=null && !node.getNomeOriginal().equals(node.getLabel())) {
                    node.setLabel(node.getNomeOriginal());
                }
                atributoDAO.atualiza(atributo);
                break;
            case 2:
                Isr isr = dicionarioDAO.isrDAO.buscaPorNomeLemma(Constante.recuperarLemmaDaPalavra(Constante.recuperarLemmaDaPalavra(j.getText())), projetoSelecionadoSelecionado.getId());
                if (isr == null){
                    isr = dicionarioDAO.isrDAO.buscaPorNomeLemma(Constante.recuperarLemmaDaPalavra(j.getText()), projetoSelecionadoSelecionado.getId());
                }
                atributo.setIdIsrOrigem(isr);
                atributoDAO.atualiza(atributo);
                break;
        }
        constuirElementosArvore();
        return node;
    }

    public boolean verificarSeAtributoEhDuplicado(Atributo atributo) {
        List<Atributo> listAtributotemp = new ArrayList<>();
        listAtributotemp = dicionarioDAO.carregarAtributosDuplicados(atributo.getNomeLemma(), projetoSelecionadoSelecionado.getId());

        return listAtributotemp.size() > 0;
    }
 
    public List<String> recuperarIsrAtributo(String atributo) {
        List<String> temp = new ArrayList<>();
        List<Atributo> listAtributotemp = new ArrayList<>();
        try {
            listAtributotemp = dicionarioDAO.atributoDAO.filtrarPorNomeLemma(Constante.recuperarLemmaDaPalavra(atributo), projetoSelecionadoSelecionado.getId());
        } catch (Exception ex) {
            Logger.getLogger(ClassificadorAtributo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listAtributotemp != null && listAtributotemp.size() > 0) {
            for (Atributo atributoDalista : listAtributotemp) {
                if (atributoDalista.getIdIsrOrigem() != null){
                    temp.add(atributoDalista.getIdIsrOrigem().getNome());
                }
            }
        }
        return temp;
    }

    private void constuirElementosArvore() throws Exception {
        isr = null;
        elementosDaArvore = new ArrayList<>();
        addElemento((DefaultMutableTreeNode) Editor.jTreeExplorar.getModel().getRoot());
        treeBuilder.construirArvoreAtributos(elementosDaArvore, isr);

    }

    public void addElemento(DefaultMutableTreeNode node) {
        //for (int i = 0; i < tree.getRowCount(); i++) {
        //Se não for, verifica se seus filhos contém o nó procurado  
        Enumeration e = node.children();
        e.hasMoreElements();
        if (!e.hasMoreElements()) {
            TreeProblemasNode no = (TreeProblemasNode) node.getUserObject();
            if (!no.getLabel().endsWith(Constante.ATRIBUTO_S)
                    && !no.getLabel().endsWith(Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES_S)
                    && !no.getLabel().endsWith(Constante.ATRIBUTO_APAGADO_S)
                    && no.getTipoNode() != Constante.ATRIBUTO_APAGADO) {
                elementosDaArvore.add(no.getLabel());
            }

            if (isr == null && no.getBaseEntity() != null) {
                isr = (Isr) ((Atributo) no.getBaseEntity()).getIdIsr();
            }

        } else {
            for (Enumeration e1 = node.children(); e1.hasMoreElements();) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) e1.nextElement();
                addElemento(child);
            }
        }
    }

}
