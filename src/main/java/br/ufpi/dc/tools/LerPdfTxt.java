/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

/**
 *
 * @author helcio.soares
 */
public class LerPdfTxt {
    private String charset = "utf-8";
    //private String docTexto = "";
    

    public String lerTXT(File fileName) {
        BufferedReader input=null;
        try {
            input = new BufferedReader(
             new InputStreamReader(new FileInputStream(
                             fileName.getAbsolutePath()), charset));
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
        }
        String conteudo = "";
        String linha;
        try {
            while ((linha = input.readLine()) != null) {
                conteudo = conteudo + linha + "\n";
            }
            return conteudo;
        } catch (IOException ex) {
            //Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                   // Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    /**
     * Específica para leitura de arquivos pdf
     * 
     * Esta função é que se encarrega de fazer a leitura do pdf e retornar o texto
     * 
     * @param filePDF
     * @return
     */
    public String lerPDF(File fileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
        } catch (IOException ex) {
           // Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
        }

        PDDocument pdfDocument = null;

        try {
            PDFParser parser = new PDFParser(fis);
            parser.parse();
            pdfDocument = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper(charset);
            
            return stripper.getText(pdfDocument);
        } catch (Throwable ex) {
            //Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pdfDocument != null) {
                try {
                    pdfDocument.close();
                } catch (IOException ex) {
                  // Logger.getLogger(Gramatica01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
//Recebe um File como entrada
//Retorna uma String 
//A String é o texto extraído do .doc

public String lerDoc(File fileDoc) throws IOException {
        FileInputStream fis;
        HWPFDocument hWPFDocument;
        try {
            fis = new FileInputStream(fileDoc);
            hWPFDocument = new HWPFDocument(fis);
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(null, "Erro na leitura do documento", "Erro na leitura do documento", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        WordExtractor wordExtractor = new WordExtractor(hWPFDocument);
        return wordExtractor.getText().trim();
    }
    
}
