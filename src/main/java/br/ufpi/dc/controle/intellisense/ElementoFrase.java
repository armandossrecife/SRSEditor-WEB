/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import br.ufpi.dc.entidades.entity.Tabela;
import java.util.ArrayList;

/**
 *
 * @author helcio.soares
 */
public class ElementoFrase {

    private String nome;
    private ArrayList<Tabela> filhos = new ArrayList<Tabela>();
    private int tipoElemento;

    public ElementoFrase(Integer tipoElemento, String elemento) {
        this.tipoElemento = tipoElemento;
        this.nome = elemento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String elemento) {
        this.nome = elemento;
    }

    public ArrayList<Tabela> getFilhos() {
        return filhos;
    }

    public void setFilhos(ArrayList<Tabela> filhos) {
        this.filhos = filhos;
    }

    public int getIndexOf(String nome) {
        for (int i = 0; i < filhos.size() - 1; i++) {
            Tabela filho = filhos.get(i);
            if (filho.getNomeLemma().equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    public void addFilho(Tabela filho) {
        if (!this.filhos.contains(filho)) {
            this.filhos.add(filho);
        }
    }

    public Tabela get(int i) {
        return this.filhos.get(i);
    }

    public int getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(int tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

}
