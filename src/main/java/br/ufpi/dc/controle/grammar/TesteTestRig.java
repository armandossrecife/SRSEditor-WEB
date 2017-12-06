/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.grammar;

import br.ufpi.dc.controle.analiseTexto.AnalisePeriodo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.TestRig;

/**
 *
 * @author Rogerio
 */
@SuppressWarnings("deprecation")
public class TesteTestRig extends TestRig {

    public TesteTestRig(String[] args) throws Exception {
        super(args);
    }

    public void executa(Lexer lexer, Class parserClass, Parser parser, String input, String encoding) throws IOException, IllegalAccessException, InvocationTargetException, PrintException {
      //  InputStream is = System.in;
    //    is = new FileInputStream(inputFile);
    //    Reader r;
    //    r = new InputStreamReader(is, encoding);

        executaProcess(lexer, parserClass, parser, input);
    }

    public void executaProcess(Lexer lexer, Class<? extends Parser> parserClass, Parser parser, String texto) throws IOException, IllegalAccessException, InvocationTargetException, PrintException {
        try {
            ANTLRInputStream input = new ANTLRInputStream(texto);
            lexer.setInputStream(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Map<String, String> palavrasTags = new HashMap<String, String>();

            tokens.fill();
            AnalisePeriodo.etiquetarDescricaoFuncionalidade(tokens);
            
//            if (showTokens) {
//                for (Object tok : tokens.getTokens()) {
//                    System.out.println(tok);
//                }
//            }

            if (startRuleName.equals(LEXER_START_RULE_NAME)) {
                return;
            }

            if (diagnostics) {
                parser.addErrorListener(new DiagnosticErrorListener());
                parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
            }

            if (printTree || gui || psFile != null) {
                parser.setBuildParseTree(true);
            }

            if (SLL) { // overrides diagnostics
                parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
            }

            parser.setTokenStream(tokens);
            parser.setTrace(trace);

            try {
                Method startRule = parserClass.getMethod(startRuleName);
                ParserRuleContext tree = (ParserRuleContext) startRule.invoke(parser, (Object[]) null);

//                if (printTree) {
//                    System.out.println(tree.toStringTree(parser));
//                }
                if (gui) {
                    tree.inspect(parser);
                }
                if (psFile != null) {
                    tree.save(parser, psFile); // Generate postscript
                }
            } catch (NoSuchMethodException nsme) {
                System.err.println("No method for rule " + startRuleName + " or it has arguments");
            }
        } finally {
//            if (r != null) {
//                r.close();
//            }
//            if (is != null) {
//                is.close();
//            }
        }
    }
}
