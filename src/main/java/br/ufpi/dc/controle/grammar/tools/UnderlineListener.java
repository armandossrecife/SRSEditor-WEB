/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.grammar.tools;

import java.util.ArrayList;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
//import visao.view.Editor;

/**
 *
 * @author helcio.soares
 */
public class UnderlineListener extends BaseErrorListener {
    public static ArrayList<String> erroList = new ArrayList<>();

    public void syntaxError(Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line, int charPositionInLine,
            String msg,
            RecognitionException e) {

//        String erro = "line " + line + ":" + charPositionInLine + " " + msg;
        String erro = msg;
        String msgTextArea = Editor.jTextArea1.getText();
        Editor.jTextArea1.setText(msgTextArea + "\n" + erro);
        underlineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
        erroList.add(erro);
        System.err.println(erro);
        
    }

    protected void underlineError(Recognizer recognizer,
            Token offendingToken, int line,
            int charPositionInLine) {
        CommonTokenStream tokens
                = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];
        String msgTextArea = Editor.jTextArea1.getText();
        String erro = errorLine+"\n";
        for (int i = 0; i < charPositionInLine; i++) {
            erro = erro +"  ";
        }
        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if (start >= 0 && stop >= 0) {
            for (int i = start; i <= stop; i++) {
                erro = erro +"^";
            }
        }
       // Editor.jTextArea1.setText(msgTextArea + "\n" + erro);
        System.err.println();
    }
}
