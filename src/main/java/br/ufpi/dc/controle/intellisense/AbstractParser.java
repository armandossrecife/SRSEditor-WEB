/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import br.ufpi.dc.dao.DicionarioDAO;
import br.ufpi.dc.entidades.entity.tools.Sinonimo;
import br.ufpi.dc.tools.Constante;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.text.JTextComponent;
//import visao.view.tools.Constante;

/**
 *
 * @author helcio.soares
 */
public abstract class AbstractParser {

    private DicionarioDAO dicionarioDAO = new DicionarioDAO();

    protected JTextComponent jTextComponent;

    public DefaultListModel avaliaFrase(String frase) {
        return null;
    }

    public List<String> buildListIntellisense(int idTipoSecao, int idProjeto) {
        DefaultListModel listIntellisense = new DefaultListModel();
        List<String> listaString = new ArrayList<>();
        List<String> listaStringVerbos = null;
        if (idTipoSecao == Constante.FUNCIONALIDADE) {
            listaString = dicionarioDAO.carregarConceitosComoString(idTipoSecao, idProjeto);
            return listaString;
        }
        
        if (idTipoSecao == Constante.ACAO) {
            listaString = dicionarioDAO.carregarConceitosComoString(idTipoSecao, idProjeto);
            listaStringVerbos = dicionarioDAO.carregarVerbos();
            for (String verbo : listaStringVerbos) {
                if (!listaString.contains(verbo)) {
                    listaString.add(verbo);
                }
            }
            return (listaString);
        }
//        if (idTipoSecao == Constante.CONCEITO) {
//            listaString = dicionarioDAO.carregarTabelaComoString(idTipoSecao, idProjeto);
//            List<String> listaStringClasse = dicionarioDAO.carregarRequisitosDeArmazenamento(idProjeto);
//            listaString.addAll(listaStringClasse);
//            return (listaString);
//        }
        if (idTipoSecao == Constante.REQUISITOS_DE_ARMAZENAMENTO) {
            List<String> listaStringClasse = dicionarioDAO.carregarRequisitosDeArmazenamento(idProjeto);
            return (listaStringClasse);
        }
        if (idTipoSecao == Constante.USUARIO) {
            listaString = dicionarioDAO.carregarTabelaPorTipoComoString(idTipoSecao, idProjeto,"nome");
            return (listaString);
        }

        if (idTipoSecao == Constante.INTERFACE_USUARIO) {
            listaString = dicionarioDAO.carregarTabelaPorTipoComoString(idTipoSecao, idProjeto,"nome");
            return (listaString);
        }
        if (idTipoSecao == Constante.ATRIBUTO) {
            List<String> listaStringTemp  = dicionarioDAO.carregarAtributoComoString(idProjeto);
            for (String listaStringTemp1 : listaStringTemp) {
                if (!listaString.contains(listaStringTemp1)){
                    listaString.add(listaStringTemp1);
                }
            }
            return (listaString);
        }
        
        if (idTipoSecao == Constante.CASO_DE_USO) {
            listaString = dicionarioDAO.carregarCasoDeUsoComoString(idProjeto);
            return (listaString);
        }
        if (idTipoSecao == Constante.ELEMENTO_DE_INTERFACE) {
            listaString = dicionarioDAO.carregarElementoDeInterfaceComoString();
            return listaString;
        }

        if (idTipoSecao == Constante.FUNCAODOPRODUTO) {
            listaString = dicionarioDAO.carregarTabelaPorTipoComoString(idTipoSecao, idProjeto, "nome");
            return listaString;
        }

        if (idTipoSecao == Constante.E_OU_ENTAO) {
            listaString = new ArrayList<String>();
            listaString.add("e");
            listaString.add("ou");
            listaString.add("então");
            return listaString;
        }

        if (idTipoSecao == Constante.OPERADOR_LOGICO) {
            return Constante.operadoresLogicos;
        }
        return null;
    }

    public DefaultListModel listToDefaultListModel(List<String> listaString) {
        DefaultListModel listIntellisense = new DefaultListModel();
        for (int i = 0; i < listaString.size(); i++) {
            listIntellisense.add(i, listaString.get(i));
        }
        return listIntellisense;
    }

    public List<String> retornaOpcoesVerbo(Sinonimo sinonimo) {
        DefaultListModel listIntellisense = new DefaultListModel();
        List<String> listaString = dicionarioDAO.carregarOpcoesComoString(sinonimo);
        return listaString;
    }

    public List<String> retornaOpcoesFuncionalidades(String verbo, int idProjeto) {
        DefaultListModel listIntellisense = new DefaultListModel();
        List<String> listaString = dicionarioDAO.carregarOpcoesFuncionalidadesComoString(verbo, idProjeto);
        return listaString;

    }
}
