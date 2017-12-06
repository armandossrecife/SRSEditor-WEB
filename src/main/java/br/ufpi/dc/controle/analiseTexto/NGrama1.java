/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.analiseTexto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author helcio.soares
 */
public class NGrama1 implements Comparable<Object> {

    private String de;
    private int freq;

    public NGrama1(String de, int freq) {
        this.de = de;
        this.freq = freq;
    }

    @Override
    public int compareTo(Object o) {
        NGrama1 nGrama = (NGrama1) o;
        if (this.freq > nGrama.getFreq()) {
            return -1;
        }
        if (this.freq < nGrama.getFreq()) {
            return 1;
        }
        if (this.freq == nGrama.getFreq()) {
            return 0;
        }
        return 0;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.de);
        hash = 37 * hash + this.freq;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NGrama1 other = (NGrama1) obj;
        if (!Objects.equals(this.de, other.de)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.de;
    }

//    public static void main(String[] args) {
//        HashMap<String, Integer> conceitos = new HashMap<>();
//
//        String s1 = "Maria";
//        conceitos.put("Maria", 1);
//        conceitos.put("Joao", 1);
//        conceitos.put("Jose", 1);
//        System.out.println(conceitos);
//        Integer temp = 1;
//        if (conceitos.get(s1) != null) {
//            temp = conceitos.get(s1);
//            temp++;
//        }
//        conceitos.put(s1, temp);
//        System.out.println(conceitos);
//
////        List<NGrama> conceitos = new List<NGrama>();
////        NGrama maria = new NGrama("Maria", 15);
////        NGrama joao = new NGrama("Joao", 10);
////        NGrama jose = new NGrama("Jose", 5);
////        conceitos.add(maria);
////        conceitos.add(joao);
////        conceitos.add(jose);
////        System.out.println(conceitos);
////        NGrama temp = conceitos.get(2);
////
////        temp.setFreq(20);
////        Collections.sort(conceitos);
////        System.out.println(conceitos);
//
//        TreeSet<NGrama> conceitos = new TreeSet<NGrama>();
//        NGrama maria = new NGrama("Maria", 15);
//        NGrama joao = new NGrama("Joao", 50);
//        NGrama jose = new NGrama("Jose", 555);
//        conceitos.add(maria);
//        conceitos.add(joao);
//        conceitos.add(jose);
//        System.out.println(conceitos);
//
//    }

}
