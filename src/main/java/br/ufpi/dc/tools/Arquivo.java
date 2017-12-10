/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author helcio.soares
 */
public class Arquivo {
        private File arquivo;  
        private String nomeArquivo;
        private Writer fos;
        
    public Arquivo(String nomeArquivo, boolean sobrepoe) throws FileNotFoundException, IOException {
        this.nomeArquivo = nomeArquivo;
        this.arquivo = new File(Constante.ARQUIVOS+nomeArquivo);  
        this.fos = new BufferedWriter(new FileWriter(Constante.ARQUIVOS+nomeArquivo, sobrepoe));  
    }
        
    public void escreveArquivo(String linhaArquivo) throws IOException{
       this.fos.append(linhaArquivo);  

    }

    public void flush() throws IOException {
        this.fos.flush();
    }
    public void fecha() throws IOException {
        this.fos.flush();
        this.fos.close();
    }

    

}
