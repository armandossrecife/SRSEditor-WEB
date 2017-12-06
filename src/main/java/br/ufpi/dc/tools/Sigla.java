/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

import br.ufpi.dc.controle.grammar.SrsGrammarLexer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author helcio.soares
 */
public class Sigla {

    private HashMap<String, String> siglas = new HashMap<String, String>();
    private String pattern = "[A-Z]{2,5}";
    private ArrayList<String> palavrasDescartaveis = new ArrayList(Arrays.asList("sobre", "dos", "das"));

    public HashMap<String, String> reconheceSiglas(CommonTokenStream tokens) {

        CommonToken ob = null;
        for (int i = 0; i < tokens.size(); i++) {
            String descricaoSigla = null;
            ob = (CommonToken) tokens.get(i);
            if (ob.getText().matches(pattern)) {
                if ((tokens.get(i - 1).getText().equals("(")) && (tokens.get(i + 1).getText().equals(")"))) {
                    descricaoSigla = verificaDescricaoAtras(i, tokens);
                    if (descricaoSigla != null) {
                        siglas.put(ob.getText(), descricaoSigla);
                    } else {
                        descricaoSigla = verificaDescricaoFrente(i, tokens);
                        if (descricaoSigla != null) {
                            siglas.put(ob.getText(), descricaoSigla);
                        }
                    }
                } else {
                    if ((tokens.get(i - 1).getType() == SrsGrammarLexer.TRACO)) {
                        descricaoSigla = verificaDescricaoAtras(i, tokens);
                        if (descricaoSigla != null) {
                            siglas.put(ob.getText(), descricaoSigla);
                        }
                    }
                    if (tokens.get(i + 1).getType() == SrsGrammarLexer.TRACO) {
                        descricaoSigla = verificaDescricaoFrente(i, tokens);
                        if (descricaoSigla != null) {
                            siglas.put(ob.getText(), descricaoSigla);
                        }
                    }
                }
            }
        }
        return siglas;

    }

    private String verificaDescricaoAtras(int i, CommonTokenStream tokens) {
        String token = tokens.get(i).getText();
        int numeroDePreposisoes = 0;
        int numeroDePalavrasAnteriores = i - 2;
        String descricaoSigla = "";
        String tokenAnterior = "";
        while ((i - numeroDePalavrasAnteriores) - numeroDePreposisoes <= token.length() + 1) {
            tokenAnterior = tokens.get(numeroDePalavrasAnteriores).getText();
            if (palavrasDescartaveis.contains(tokenAnterior.toLowerCase())) {
                descricaoSigla = tokenAnterior + " " + descricaoSigla;
                numeroDePreposisoes++;
                numeroDePalavrasAnteriores--;
                tokenAnterior = tokens.get(numeroDePalavrasAnteriores).getText();

            }
            while (tokenAnterior.length() <= 2) {
                if (tokenAnterior.length() <= 2) {
                    descricaoSigla = tokenAnterior + " " + descricaoSigla;
                    numeroDePreposisoes++;
                }
                numeroDePalavrasAnteriores--;
                tokenAnterior = tokens.get(numeroDePalavrasAnteriores).getText();
            }
            int indiceSigla = token.length() - (i - numeroDePalavrasAnteriores) + numeroDePreposisoes + 1;
            String charSigla = token.charAt(indiceSigla) + "";
            String charTokenAnterior = tokenAnterior.charAt(0) + "";
            if (charSigla.equals(charTokenAnterior.toUpperCase())) {
                descricaoSigla = tokenAnterior + " " + descricaoSigla;
                numeroDePalavrasAnteriores--;
            } else {
                return null;
            }
        }
        return descricaoSigla.substring(0, descricaoSigla.length() - 1);
    }

    private String verificaDescricaoFrente(int i, CommonTokenStream tokens) {
        String sigla = tokens.get(i).getText();
        int numeroDePreposisoes = 0;
        int numeroDePalavrasPosteriores = i + 2;
        String descricaoSigla = "";
        String tokenAnterior = "";
        int indiceSigla = (numeroDePalavrasPosteriores - numeroDePreposisoes) - (i + 2);
        while (indiceSigla < sigla.length() - 1) {
            tokenAnterior = tokens.get(numeroDePalavrasPosteriores).getText();
            if (palavrasDescartaveis.contains(tokenAnterior.toLowerCase())) {
                descricaoSigla = descricaoSigla + " " + tokenAnterior;
                numeroDePreposisoes++;
                numeroDePalavrasPosteriores++;
                tokenAnterior = tokens.get(numeroDePalavrasPosteriores).getText();
            }
            while (tokenAnterior.length() <= 2) {
                if (tokenAnterior.length() <= 2) {
                    descricaoSigla = descricaoSigla + " " + tokenAnterior;
                    numeroDePreposisoes++;
                }
                numeroDePalavrasPosteriores++;
                tokenAnterior = tokens.get(numeroDePalavrasPosteriores).getText();
            }
            indiceSigla = (numeroDePalavrasPosteriores - numeroDePreposisoes) - (i + 2);
            if (indiceSigla >= sigla.length()) {
                return null;
            }
            String charSigla = sigla.charAt(indiceSigla) + "";
            String charTokenAnterior = tokenAnterior.charAt(0) + "";
            if (charSigla.equals(charTokenAnterior.toUpperCase())) {
                descricaoSigla = descricaoSigla + " " + tokenAnterior;
                numeroDePalavrasPosteriores++;
            } else {
                return null;
            }
        }
        return descricaoSigla.substring(1, descricaoSigla.length());
    }
}
