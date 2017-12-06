/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

/**
 *
 * @author helcio.soares
 */
public class Timer {    
    private long tempoInicial;
    private long tempoFinal;
    private long diferenca;

    public long elapsed(String msg) {
        
        if (msg.equals("Inicio")){
            this.tempoInicial = System.currentTimeMillis();
            return 0;
        } else{
            this.tempoFinal = System.currentTimeMillis();
            diferenca = this.tempoFinal - this.tempoInicial;

            return diferenca;
        }
            
        
        
    }
    
}
