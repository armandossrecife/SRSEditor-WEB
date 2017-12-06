// Generated from D:\mestrado\netbeans\SRSEDITORM\src\UCGrammar\UCGrammar.g4 by ANTLR 4.1

package br.ufpi.dc.controle.UCGrammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import br.ufpi.dc.tools.Constante;
import br.ufpi.dc.tools.ElementosFrase;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class UCGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, PALAVRA=2, NUMERO=3, SIMBOLOS=4, TRACO=5, TERMINAL=6, VIRGULA=7, 
		PONTO=8, ASPAS=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"WS", "PALAVRA", "NUMERO", "SIMBOLOS", "'-'", "TERMINAL", "','", "'.'", 
		"'\"'"
	};
	public static final String[] ruleNames = {
		"WS", "LetrasAcentuadas", "PALAVRA", "NUMERO", "SIMBOLOS", "TRACO", "TERMINAL", 
		"VIRGULA", "PONTO", "ASPAS"
	};


	    public ElementosFrase elementosDaFrase     = new ElementosFrase(); 
	    public ArrayList<String> atores            = new ArrayList<String>(); 
	    public ArrayList<String> acoes             = new ArrayList<String>(); 
	    public ArrayList<String> conceitos         = new ArrayList<String>(); 
	    
	   
	    private boolean ok              = true;

	    private String conceito         = "";
	    
	    public String proximo           = "";
	    public String caminho           = "";
	    public String ultimaPalavra     = "";
	    public String complemento       = "";
	    public String ultimoAtor        = "";
	    public String ultimoVerbo       = "";
	    public String ultimoConceito    = "";
	    public String ultimoComplemento = "";
	    public String numero            = "";
	    public String numeroSentenca    = "";
	    public String pontoAntesSenao   = "";
	    public String pontoDepoisSenao  = "";
	    public int    tipoFrase         =  0;

	private void addConceitoAoElementoDaFrase(String tipo){
	                               
	       if (!conceito.equals("") && (ok) && !conceito.contains("<missing")){
	         elementosDaFrase.tipoElemento.add(tipo);
	         elementosDaFrase.elemento.add(conceito);
	       }    
	       ok = true;
	       conceito = "";
	    }
	private void addNumeroAoElementoDaFrase(String tipo){
	                               
	       if (!numero.equals("") && !numero.contains("<missing")){
	         elementosDaFrase.tipoElemento.add(tipo);
	         elementosDaFrase.elemento.add(numero);
	       }    
	    }

	private void addPalavraAoConceito(String palavra){
	         if (palavra != null){
	             if (conceito.equals("")){
	                 conceito           = palavra;
	             }else{
	                 conceito           = conceito + " " + palavra;   
	             }
	         }
	    }
	    
	private void addNumero(String palavra){
	         if (palavra != null){
	             if (numero.equals("")){
	                 numero = palavra;
	             }else{
	                 numero = numero + palavra;   
	             }
	         }
	    }

	private void addPalavraAoComplemento(String palavra){
	         if (palavra != null){
	             if (complemento.equals("")){
	                 complemento       = palavra;
	                 
	             }else{
	                 complemento           = complemento + " " + palavra;   
	             }
	         }
	        testaConceito(palavra);
	    }

	private void addPreposicao(String preposicao){
	         if (!conceito.equals("") ){
	             addPalavraAoConceito(preposicao);
	         }else{
	             ok = false;
	         }
	    } 
	    
	private void testaConceito(String palavra){
	       if (palavra!=null){
	//            if ((palavra.length()>=4 || palavra.toUpperCase().equals(palavra)) && (ok) && !palavra.contains("<missing")){
	            if ((ok) && !palavra.contains("<missing")){
	                 addPalavraAoConceito(palavra);
	                 ultimaPalavra = palavra;
	            }else{ 
	                  ok = false;
	            }
	       }
	    }

	private void setProximo(String token,String proximo){
		    if (token!= null && !token.equals("") && !conceito.contains("<missing")){
	                this.proximo = proximo;
	              //  System.out.print(token    + " --> " + proximo + " | ");
	                caminho = caminho + token + " --> " + proximo + " | ";
	            }
	    }

	private void addConceito(){
	                               
	       if (!conceito.equals("") && (ok) && !conceito.contains("<missing")){
	         conceitos.add(conceito.toLowerCase());
	       }    
	       
	       conceito = "";
	       ok = true;
	    }


	public UCGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "UCGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip(); break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\13\60\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\2\3\3\3\3\3\4\3\4\6\4\37\n\4\r\4\16\4 \3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\2\f\3\3\2\5\2\1\7\4\1\t\5\1"+
		"\13\6\1\r\7\1\17\b\1\21\t\1\23\n\1\25\13\1\3\2\7\5\2\13\f\17\17\"\"\20"+
		"\2\u00c2\u00c5\u00c9\u00c9\u00cb\u00cc\u00cf\u00cf\u00d5\u00d7\u00dc\u00dc"+
		"\u00de\u00de\u00e2\u00e5\u00e9\u00e9\u00eb\u00ec\u00ef\u00ef\u00f5\u00f7"+
		"\u00fc\u00fc\u00fe\u00fe\4\2C\\c|\17\2%-\61\61>B]_ab}}\177\177\u00a9\u00a9"+
		"\u00ac\u00ac\u00b3\u00b3\u00b6\u00b6\u00bc\u00bc\u00c8\u00c8\5\2##<=A"+
		"A\60\2\3\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\32\3\2"+
		"\2\2\7\36\3\2\2\2\t\"\3\2\2\2\13$\3\2\2\2\r&\3\2\2\2\17(\3\2\2\2\21*\3"+
		"\2\2\2\23,\3\2\2\2\25.\3\2\2\2\27\30\t\2\2\2\30\31\b\2\2\2\31\4\3\2\2"+
		"\2\32\33\t\3\2\2\33\6\3\2\2\2\34\37\t\4\2\2\35\37\5\5\3\2\36\34\3\2\2"+
		"\2\36\35\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\b\3\2\2\2\"#\4\62"+
		";\2#\n\3\2\2\2$%\t\5\2\2%\f\3\2\2\2&\'\7/\2\2\'\16\3\2\2\2()\t\6\2\2)"+
		"\20\3\2\2\2*+\7.\2\2+\22\3\2\2\2,-\7\60\2\2-\24\3\2\2\2./\7$\2\2/\26\3"+
		"\2\2\2\5\2\36 ";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}