/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import br.ufpi.dc.tools.Constante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.poi.hssf.record.formula.functions.Irr;

/**
 *
 * @author helcio.soares
 */
public class ParserIsr extends AbstractParser {

    private TreeBuilder treeBuilder;

    private Projeto projetoSelecionado;
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private Isr isr;

    public ParserIsr(Projeto projetoSelecionado, Isr isr) {
        this.projetoSelecionado = projetoSelecionado;
        this.treeBuilder = new TreeBuilder(dicionarioDAO, projetoSelecionado);
        this.isr = isr;
    }

    public Root validarAtributos(String sentenca) throws Exception {

        AnalisePeriodo analisePeriodo = new AnalisePeriodo(sentenca);
        ArrayList<String> candidatosAAtributo = analisePeriodo.recuperarAtributos(sentenca);
        candidatosAAtributo = Constante.eliminarRepetidosPorLemma(candidatosAAtributo, 1);
        return treeBuilder.construirArvoreAtributos(candidatosAAtributo, this.isr);
    }

}
