/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.controle.analiseTexto.AnalisePeriodo;
import br.ufpi.dc.entidades.entity.Acao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.ufpi.dc.controle.nGramas.NGrama;
import br.ufpi.dc.controle.sinonimo.SinonimoTools;
import static br.ufpi.dc.tools.Constante.projetoSelecionado;

/**
 *
 * @author helcio.soares
 */
public class ConstrutorDeAcao {

    private final static String QUERYEXISTEACAO = "select a FROM Acao a    \n"
            + "where a.idProjeto = :idProjeto \n"
            + "and   a.verbo     = :verbo     \n"
            + "and   a.objeto    = :objeto    \n";

    private DAO<Acao> acaoDAO = new DAO(Acao.class);

    public void gravarAcao(String funcionalidade) {
        String conjugacaoVerbo = "ar#er#ir";
        SinonimoTools sinonimoTools = new SinonimoTools(projetoSelecionado);

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

        String etiqueta = AnalisePeriodo.tagger.getHashEtiquetas().get(verbo);
        if (etiqueta.equals("VMN") || etiqueta.equals("VMI")) {
            String lemma = AnalisePeriodo.getLemmas().get(verbo);
            if (verbo.length() > 4 && conjugacaoVerbo.contains(verbo.substring(verbo.length() - 2, verbo.length()))) {
                if (lemma != null) {
                    verbo = lemma;
                }
                verbo = sinonimoTools.recuperaSinonimo(verbo);
                String func = verbo + " " + objeto;

                try {
                    DAO<Acao> acaoDAO = new DAO(Acao.class);
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("idProjeto", projetoSelecionado);
                    params.put("verbo", verbo);
                    params.put("objeto", objeto);
                    Acao acaoConsulta = acaoDAO.buscaPorCampos(params, QUERYEXISTEACAO);
                    if (acaoConsulta == null) {
                        Acao acao = new Acao();
                        acao.setIdProjeto(projetoSelecionado);
                        acao.setVerbo(verbo);
                        acao.setObjeto(objeto);

                        acaoDAO.adiciona(acao);
                    }
                } catch (Exception e) {
                    System.out.println("PROBLEMA NA FUNCIONALIDADE:" + func);
                }
            }
        }
    }

}
