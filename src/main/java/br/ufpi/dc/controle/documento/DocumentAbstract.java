/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.documento;

import br.gov.pi.tce.word.Convert;
import br.gov.pi.tce.word.PredefinedStyles;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import word.w2004.Document2004;

/**
 *
 * @author ivo
 */
public abstract class DocumentAbstract {

    public static final String PORTRAIT = "portrait";
    public static final String LANDSCAPE = "landscape";
    private Document2004 doc;
    private PrintWriter writer = null;
    private final String orientacao;
    private final PredefinedStyles predefStyle;
    private String nomeArquivo;

    public DocumentAbstract(String orientacao, String nomeArquivo) {
        this.orientacao = orientacao;
        this.nomeArquivo = nomeArquivo;
        predefStyle = PredefinedStyles.getInstance();
        makeDoc();
    }

    public void makeDoc() {
        String author = "Hélcio Soares";
        doc = new Document2004();
        doc.setBound(
                Convert.centimeter2Twip(21),
                Convert.centimeter2Twip(29.5),
                Convert.centimeter2Twip(1),
                Convert.centimeter2Twip(1),
                Convert.centimeter2Twip(1),
                Convert.centimeter2Twip(1));
        doc.author(author);
        doc.lastAuthor(author);
        doc.manager(author);
        doc.company(author);
        doc.encoding(Document2004.Encoding.UTF_8); //or ISO8859-1. Default is UTF-8
        File fileObj = new File(nomeArquivo);
               
        try {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileObj), "UTF8")));
            //            writer = new PrintWriter(fileObj);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addContent(doc);


        try {
            String myWord = doc.getContent();
            myWord = setPageSize(myWord);
            writer.println(myWord);
            writer.flush();
            writer.close();
        } finally {
        }

    }

    public String setPageSize(String s) {
        String result = s;//.replaceAll("\"Cambria\"", "\"Arial\"");
        String size;
        if (this.orientacao.equals(LANDSCAPE)) {
            size = "\n    <w:sectPr wsp:rsidR=\"008979E8\" wsp:rsidSect=\"0094401C\">\n"
                    + "      <w:pgSz w:w=\"" + doc.getHeight() + "\" w:h=\"" + doc.getWidth() + "\" w:orient=\"" + orientacao + "\" />\n"
                    + "      <w:pgMar w:top=\"" + doc.getTopMargin() + "\" w:right=\"" + doc.getRightMargin() + "\" w:bottom=\"" + doc.getBottomMargin() + "\" w:left=\"" + doc.getLeftMargin() + "\" w:header=\"720\" w:footer=\"720\" w:gutter=\"0\"/>\n"
                    + "      <w:cols w:space=\"720\"/>\n" + "    </w:sectPr>";
        } else {
            size = "\n    <w:sectPr wsp:rsidR=\"008979E8\" wsp:rsidSect=\"0094401C\">\n"
                    + "      <w:pgSz w:w=\"" + doc.getWidth() + "\" w:h=\"" + doc.getHeight() + "\" w:orient=\"" + orientacao + "\" />\n"
                    + "      <w:pgMar w:top=\"" + doc.getTopMargin() + "\" w:right=\"" + doc.getRightMargin() + "\" w:bottom=\"" + doc.getBottomMargin() + "\" w:left=\"" + doc.getLeftMargin() + "\" w:header=\"720\" w:footer=\"720\" w:gutter=\"0\"/>\n"
                    + "      <w:cols w:space=\"720\"/>\n" + "    </w:sectPr>";
        }
        result = result.replace("</w:body>", size + "\n</w:body>");
        return result;
    }

    public abstract void addContent(Document2004 doc);
}
