/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

import br.ufpi.dc.dao.DAO;
import br.ufpi.dc.entidades.entity.SinonimoDominio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static br.ufpi.dc.tools.Constante.projetoSelecionado;

/**
 *
 * @author helcio.soares
 */
public class ElementosFrase {

    public ArrayList<String> tipoElemento = new ArrayList<String>();
    public ArrayList<String> elemento = new ArrayList<String>();
    public ArrayList<String> verbos = new ArrayList<String>();
    public ArrayList<Integer> posVerbo = new ArrayList<Integer>();

    public int contar(String tipo) {
        int temp = 0;
        for (String string : tipoElemento) {
            if (string.equals(tipo)) {
                temp++;
            }
        }
        return temp;
    }

    public ArrayList<String> recuperaElementos(String tipo) {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i <= tipoElemento.size() - 1; i++) {
            if (tipo.equals(tipoElemento.get(i))) {
                temp.add(elemento.get(i));
            }
        }
        return temp;
    }

    public void addElemento(String elementoFrase, String tipo) {
        tipoElemento.add(tipo);
        elemento.add(elementoFrase);
    }

    public void addVerbo(String verbo) {
        verbos.add(verbo);
        posVerbo.add(elemento.size() - 1);
    }

    public void trocarPorSinonimos() throws Exception {
        ArrayList<String> temp = new ArrayList<String>();
        DAO<SinonimoDominio> sinonimoDominioDAO = new DAO(SinonimoDominio.class);
        Map<String, Object> params = new HashMap<String, Object>();
        String naoPode = "#NUM#PONTO#ASPAS#";

        params.put("idProjeto", projetoSelecionado);

        for (int i = 0; i <= elemento.size() - 1; i++) {
            if (!naoPode.contains(tipoElemento.get(i))) {
                String elementoDafrase = elemento.get(i);
                String lemmaElementoDaFrase = Constante.recuperarLemmaDaPalavra(elementoDafrase);
                if (lemmaElementoDaFrase != null) {
                    params.put("chave", lemmaElementoDaFrase);
                    List<SinonimoDominio> sinonimo = sinonimoDominioDAO.filtrarPorCampos(params, Constante.QUERYSINONIMOCHAVE1);
                    elementoDafrase = sinonimo.isEmpty() == true ? elementoDafrase : Constante.recuperarPalavraDoLemma(sinonimo.get(0).getSinonimo());
                    elemento.set(i, elementoDafrase);
                }
            }
        }
    }
}
