/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.sinonimo;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.dao.DicionarioDAO;
import br.ufpi.dc.entidades.entity.Lemma;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.Tabela;
import br.ufpi.dc.entidades.entity.tools.Sinonimo;
import br.ufpi.dc.entidades.entity.tools.Verbo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.ufpi.dc.controle.nGramas.NGrama;

/**
 *
 * @author helcio.soares
 */
public class SinonimoTools {

    private DAO<Sinonimo> sinonimoDAO   = new DAO(Sinonimo.class);
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private DAO<Verbo> verbosDAO        = new DAO(Verbo.class);

    private Projeto projetoSelecionado;
    private HashMap<String, Sinonimo> sinonimoHash = new HashMap<String, Sinonimo>();
    private HashMap<Integer, String> verbosHash    = new HashMap<Integer, String>();

    public SinonimoTools(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
        sinonimoHash            = carregaSinonimos();
        verbosHash              = carregaVerboSinonimo();
    }

    public String recuperaSinonimo(String verbo) {
        Sinonimo sinonimoDoVerbo = sinonimoHash.get(verbo);

        if (sinonimoDoVerbo != null) {
            System.out.println(verbo);
            int idVerdo = sinonimoDoVerbo.getIdVerbo().getId();
            return verbosHash.get(idVerdo);
        }
        return verbo;
    }

    public String recuperaSinonimoMaisFrequente(String verbo) {
        Sinonimo sinonimoDoVerbo = sinonimoHash.get(verbo);
        ArrayList<NGrama> listaVerbosOrdenados = new ArrayList<>();

        if (sinonimoDoVerbo != null) {
            System.out.println(verbo);
            int idVerdo = sinonimoDoVerbo.getIdVerbo().getId();
            List<Sinonimo> listaSinonimo = recuperaListaSinonimos(idVerdo);

            for (Sinonimo verboSinonimo : listaSinonimo) {
                String verboConsultado = verboSinonimo.getDe();
                String radical         = verboConsultado.substring(0, verboConsultado.length() - 2);
                NGrama verboOrdenado   = dicionarioDAO.recuperaFrequenciaPalavra(radical, projetoSelecionado);
                verboOrdenado.setDe(verboConsultado);
                listaVerbosOrdenados.add(verboOrdenado);
            }
            Collections.sort(listaVerbosOrdenados);
            return listaVerbosOrdenados.get(0).getDe();
        }
        return verbo;
    }

    private List<Sinonimo> recuperaListaSinonimos(int pIdVerdo) {

        String sql = "select c from Sinonimo c "
                + "where  c.idVerbo = :pIdVerdo ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdVerdo", pIdVerdo);

        List<Sinonimo> listaRetorno = sinonimoDAO.filtrarPorCampos(params, sql);
        return listaRetorno;
    }

    private HashMap<String, Sinonimo> carregaSinonimos() {
        HashMap<String, Sinonimo> hashTemp = new HashMap<String, Sinonimo>();
        List<Sinonimo> sinonimos           = sinonimoDAO.listaTodos();
        for (Sinonimo sinonimo : sinonimos) {
            hashTemp.put(sinonimo.getDe(), sinonimo);
        }
        return hashTemp;
    }

    private HashMap<Integer, String> carregaVerboSinonimo() {
        HashMap<Integer, String> hashTemp = new HashMap<Integer, String>();
        List<Verbo> verbosSininimos       = verbosDAO.listaTodos();
        for (Verbo verbo : verbosSininimos) {
            hashTemp.put(verbo.getId(), verbo.getDe());
        }
        return hashTemp;
    }

}
