// Generated from D:\mestrado\netbeans\SRSEDITORM\src\UCGrammar\UCGrammar.g4 by ANTLR 4.1

package br.ufpi.dc.controle.UCGrammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import br.ufpi.dc.tools.Constante;
import br.ufpi.dc.tools.ElementosFrase;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class UCGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, PALAVRA=2, NUMERO=3, SIMBOLOS=4, TRACO=5, TERMINAL=6, VIRGULA=7, 
		PONTO=8, ASPAS=9, ART=10, ADJ=11, SUBS=12, VERB=13, PREP=14, PRON=15, 
		CONJ=16, ADV=17, NUM=18, PALAVRAESTRANGEIRA=19, ENTAO=20, SENAO=21, ENQUANTO=22, 
		PARA=23, CONJUNCAO=24, PALAVRASRESERVADAS=25, SE=26;
	public static final String[] tokenNames = {
		"<INVALID>", "WS", "PALAVRA", "NUMERO", "SIMBOLOS", "'-'", "TERMINAL", 
		"','", "'.'", "'\"'", "ART", "ADJ", "SUBS", "VERB", "PREP", "PRON", "CONJ", 
		"ADV", "NUM", "PALAVRAESTRANGEIRA", "ENTAO", "SENAO", "ENQUANTO", "PARA", 
		"CONJUNCAO", "PALAVRASRESERVADAS", "SE"
	};
	public static final int
		RULE_det = 0, RULE_det_base = 1, RULE_pos_det = 2, RULE_sentenca_uc = 3, 
		RULE_sentenca_enquanto = 4, RULE_sentenca_para = 5, RULE_numero = 6, RULE_sentenca_acao_usuario = 7, 
		RULE_complementos = 8, RULE_lista_complemento = 9, RULE_qualquer_coisa = 10, 
		RULE_qualquer_coisa_para = 11, RULE_palavras = 12, RULE_palavras_reservadas = 13, 
		RULE_palavras_uc_1 = 14, RULE_sentenca_condicional = 15, RULE_sentenca_logica = 16, 
		RULE_expressao = 17, RULE_e1 = 18, RULE_conceito_1 = 19;
	public static final String[] ruleNames = {
		"det", "det_base", "pos_det", "sentenca_uc", "sentenca_enquanto", "sentenca_para", 
		"numero", "sentenca_acao_usuario", "complementos", "lista_complemento", 
		"qualquer_coisa", "qualquer_coisa_para", "palavras", "palavras_reservadas", 
		"palavras_uc_1", "sentenca_condicional", "sentenca_logica", "expressao", 
		"e1", "conceito_1"
	};

	@Override
	public String getGrammarFileName() { return "UCGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public UCGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DetContext extends ParserRuleContext {
		public Pos_detContext pos_det() {
			return getRuleContext(Pos_detContext.class,0);
		}
		public Det_baseContext det_base() {
			return getRuleContext(Det_baseContext.class,0);
		}
		public DetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_det; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterDet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitDet(this);
		}
	}

	public final DetContext det() throws RecognitionException {
		DetContext _localctx = new DetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_det);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";
			setState(41); det_base();
			setState(43);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(42); pos_det();
				}
				break;
			}
			addConceitoAoElementoDaFrase("DET");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Det_baseContext extends ParserRuleContext {
		public Token ART;
		public Token PRON;
		public Token NUM;
		public TerminalNode PRON() { return getToken(UCGrammarParser.PRON, 0); }
		public TerminalNode NUM() { return getToken(UCGrammarParser.NUM, 0); }
		public TerminalNode ART() { return getToken(UCGrammarParser.ART, 0); }
		public Det_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_det_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterDet_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitDet_base(this);
		}
	}

	public final Det_baseContext det_base() throws RecognitionException {
		Det_baseContext _localctx = new Det_baseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_det_base);
		try {
			setState(53);
			switch (_input.LA(1)) {
			case ART:
				enterOuterAlt(_localctx, 1);
				{
				setState(47); ((Det_baseContext)_localctx).ART = match(ART);
				testaConceito((((Det_baseContext)_localctx).ART!=null?((Det_baseContext)_localctx).ART.getText():null));
				}
				break;
			case PRON:
				enterOuterAlt(_localctx, 2);
				{
				setState(49); ((Det_baseContext)_localctx).PRON = match(PRON);
				testaConceito((((Det_baseContext)_localctx).PRON!=null?((Det_baseContext)_localctx).PRON.getText():null));
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 3);
				{
				setState(51); ((Det_baseContext)_localctx).NUM = match(NUM);
				testaConceito((((Det_baseContext)_localctx).NUM!=null?((Det_baseContext)_localctx).NUM.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pos_detContext extends ParserRuleContext {
		public Token NUM;
		public Token PRON;
		public TerminalNode PRON() { return getToken(UCGrammarParser.PRON, 0); }
		public TerminalNode NUM() { return getToken(UCGrammarParser.NUM, 0); }
		public Pos_detContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos_det; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterPos_det(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitPos_det(this);
		}
	}

	public final Pos_detContext pos_det() throws RecognitionException {
		Pos_detContext _localctx = new Pos_detContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pos_det);
		try {
			setState(59);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(55); ((Pos_detContext)_localctx).NUM = match(NUM);
				testaConceito((((Pos_detContext)_localctx).NUM!=null?((Pos_detContext)_localctx).NUM.getText():null));
				}
				break;
			case PRON:
				enterOuterAlt(_localctx, 2);
				{
				setState(57); ((Pos_detContext)_localctx).PRON = match(PRON);
				testaConceito((((Pos_detContext)_localctx).PRON!=null?((Pos_detContext)_localctx).PRON.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sentenca_ucContext extends ParserRuleContext {
		public Token PONTO;
		public Token SENAO;
		public Sentenca_acao_usuarioContext sentenca_acao_usuario() {
			return getRuleContext(Sentenca_acao_usuarioContext.class,0);
		}
		public Sentenca_enquantoContext sentenca_enquanto() {
			return getRuleContext(Sentenca_enquantoContext.class,0);
		}
		public Sentenca_paraContext sentenca_para() {
			return getRuleContext(Sentenca_paraContext.class,0);
		}
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(UCGrammarParser.PONTO, 0); }
		public Sentenca_condicionalContext sentenca_condicional() {
			return getRuleContext(Sentenca_condicionalContext.class,0);
		}
		public TerminalNode SENAO() { return getToken(UCGrammarParser.SENAO, 0); }
		public Sentenca_ucContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenca_uc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterSentenca_uc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitSentenca_uc(this);
		}
	}

	public final Sentenca_ucContext sentenca_uc() throws RecognitionException {
		Sentenca_ucContext _localctx = new Sentenca_ucContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sentenca_uc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); numero();
			numeroSentenca = numero;
			addNumeroAoElementoDaFrase("NUM");
			conceito ="";
			setState(78);
			switch (_input.LA(1)) {
			case ART:
			case SUBS:
			case PRON:
			case NUM:
				{
				setState(65); sentenca_acao_usuario();
				conceito ="";
				setState(67); ((Sentenca_ucContext)_localctx).PONTO = match(PONTO);
				testaConceito((((Sentenca_ucContext)_localctx).PONTO!=null?((Sentenca_ucContext)_localctx).PONTO.getText():null));
				addConceitoAoElementoDaFrase("PONTO");
				}
				break;
			case SE:
				{
				setState(71); sentenca_condicional();
				}
				break;
			case SENAO:
				{
				setState(72); ((Sentenca_ucContext)_localctx).SENAO = match(SENAO);
				tipoFrase=3; //Nao utilizado
				                               
				testaConceito((((Sentenca_ucContext)_localctx).SENAO!=null?((Sentenca_ucContext)_localctx).SENAO.getText():null));
				addConceitoAoElementoDaFrase("SENAO");
				}
				break;
			case PARA:
				{
				setState(76); sentenca_para();
				}
				break;
			case ENQUANTO:
				{
				setState(77); sentenca_enquanto();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			conceito ="";
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sentenca_enquantoContext extends ParserRuleContext {
		public Token ENQUANTO;
		public Sentenca_logicaContext sentenca_logica() {
			return getRuleContext(Sentenca_logicaContext.class,0);
		}
		public TerminalNode ENQUANTO() { return getToken(UCGrammarParser.ENQUANTO, 0); }
		public Sentenca_enquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenca_enquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterSentenca_enquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitSentenca_enquanto(this);
		}
	}

	public final Sentenca_enquantoContext sentenca_enquanto() throws RecognitionException {
		Sentenca_enquantoContext _localctx = new Sentenca_enquantoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sentenca_enquanto);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";
			setState(83); ((Sentenca_enquantoContext)_localctx).ENQUANTO = match(ENQUANTO);
			testaConceito((((Sentenca_enquantoContext)_localctx).ENQUANTO!=null?((Sentenca_enquantoContext)_localctx).ENQUANTO.getText():null));
			addConceitoAoElementoDaFrase("ENQUANTO");
			tipoFrase = 5;
			setState(87); sentenca_logica();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sentenca_paraContext extends ParserRuleContext {
		public Token PARA;
		public TerminalNode PARA() { return getToken(UCGrammarParser.PARA, 0); }
		public Qualquer_coisa_paraContext qualquer_coisa_para() {
			return getRuleContext(Qualquer_coisa_paraContext.class,0);
		}
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sentenca_paraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenca_para; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterSentenca_para(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitSentenca_para(this);
		}
	}

	public final Sentenca_paraContext sentenca_para() throws RecognitionException {
		Sentenca_paraContext _localctx = new Sentenca_paraContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_sentenca_para);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";
			setState(90); ((Sentenca_paraContext)_localctx).PARA = match(PARA);
			 testaConceito((((Sentenca_paraContext)_localctx).PARA!=null?((Sentenca_paraContext)_localctx).PARA.getText():null));
			                       addConceitoAoElementoDaFrase("PARA");
			                       tipoFrase = 4;
			                     
			setState(93);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(92); det();
				}
				break;
			}
			setState(95); qualquer_coisa_para();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumeroContext extends ParserRuleContext {
		public Token NUMERO;
		public Token PONTO;
		public List<TerminalNode> NUMERO() { return getTokens(UCGrammarParser.NUMERO); }
		public List<TerminalNode> PONTO() { return getTokens(UCGrammarParser.PONTO); }
		public TerminalNode PONTO(int i) {
			return getToken(UCGrammarParser.PONTO, i);
		}
		public TerminalNode NUMERO(int i) {
			return getToken(UCGrammarParser.NUMERO, i);
		}
		public NumeroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numero; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterNumero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitNumero(this);
		}
	}

	public final NumeroContext numero() throws RecognitionException {
		NumeroContext _localctx = new NumeroContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numero);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			numero="";
			{
			setState(98); ((NumeroContext)_localctx).NUMERO = match(NUMERO);
			addNumero((((NumeroContext)_localctx).NUMERO!=null?((NumeroContext)_localctx).NUMERO.getText():null));
			}
			setState(107);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(105);
					switch (_input.LA(1)) {
					case PONTO:
						{
						setState(101); ((NumeroContext)_localctx).PONTO = match(PONTO);
						addNumero((((NumeroContext)_localctx).PONTO!=null?((NumeroContext)_localctx).PONTO.getText():null));
						}
						break;
					case NUMERO:
						{
						setState(103); ((NumeroContext)_localctx).NUMERO = match(NUMERO);
						addNumero((((NumeroContext)_localctx).NUMERO!=null?((NumeroContext)_localctx).NUMERO.getText():null));
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(109);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sentenca_acao_usuarioContext extends ParserRuleContext {
		public DetContext det;
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public Token VERB;
		public TerminalNode PREP() { return getToken(UCGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(UCGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(UCGrammarParser.ADJ, 0); }
		public Palavras_uc_1Context palavras_uc_1() {
			return getRuleContext(Palavras_uc_1Context.class,0);
		}
		public ComplementosContext complementos() {
			return getRuleContext(ComplementosContext.class,0);
		}
		public List<TerminalNode> SUBS() { return getTokens(UCGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public TerminalNode VERB() { return getToken(UCGrammarParser.VERB, 0); }
		public Sentenca_acao_usuarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenca_acao_usuario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterSentenca_acao_usuario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitSentenca_acao_usuario(this);
		}
	}

	public final Sentenca_acao_usuarioContext sentenca_acao_usuario() throws RecognitionException {
		Sentenca_acao_usuarioContext _localctx = new Sentenca_acao_usuarioContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sentenca_acao_usuario);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(110); ((Sentenca_acao_usuarioContext)_localctx).det = det();
				}
			}

			setProximo ((((Sentenca_acao_usuarioContext)_localctx).det!=null?_input.getText(((Sentenca_acao_usuarioContext)_localctx).det.start,((Sentenca_acao_usuarioContext)_localctx).det.stop):null),"90 usuario");
			setState(114); ((Sentenca_acao_usuarioContext)_localctx).s1 = match(SUBS);
			setProximo((((Sentenca_acao_usuarioContext)_localctx).s1!=null?((Sentenca_acao_usuarioContext)_localctx).s1.getText():null),"91 verbo");
			                                testaConceito((((Sentenca_acao_usuarioContext)_localctx).s1!=null?((Sentenca_acao_usuarioContext)_localctx).s1.getText():null));
			                               
			setState(117);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(116); ((Sentenca_acao_usuarioContext)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sentenca_acao_usuarioContext)_localctx).PREP!=null?((Sentenca_acao_usuarioContext)_localctx).PREP.getText():null));
			setState(128);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(121);
				_la = _input.LA(1);
				if (_la==SUBS) {
					{
					setState(120); ((Sentenca_acao_usuarioContext)_localctx).s2 = match(SUBS);
					}
				}

				testaConceito((((Sentenca_acao_usuarioContext)_localctx).s2!=null?((Sentenca_acao_usuarioContext)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(125);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(124); ((Sentenca_acao_usuarioContext)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sentenca_acao_usuarioContext)_localctx).ADJ!=null?((Sentenca_acao_usuarioContext)_localctx).ADJ.getText():null));
				}
				break;
			}
			addConceitoAoElementoDaFrase("SUJ");
			conceito ="";
			setState(133);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << ART) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(132); palavras_uc_1();
				}
			}

			addConceitoAoElementoDaFrase("PAC");
			conceito ="";
			setState(137); ((Sentenca_acao_usuarioContext)_localctx).VERB = match(VERB);
			 testaConceito((((Sentenca_acao_usuarioContext)_localctx).VERB!=null?((Sentenca_acao_usuarioContext)_localctx).VERB.getText():null));
			                             setProximo((((Sentenca_acao_usuarioContext)_localctx).VERB!=null?((Sentenca_acao_usuarioContext)_localctx).VERB.getText():null),"92 confVerbo");
			                             ultimoVerbo = (((Sentenca_acao_usuarioContext)_localctx).VERB!=null?((Sentenca_acao_usuarioContext)_localctx).VERB.getText():null);
			                             if (!(((Sentenca_acao_usuarioContext)_localctx).VERB!=null?((Sentenca_acao_usuarioContext)_localctx).VERB.getText():null).contains("<missing")){
			                                 tipoFrase = 1;
			                                 addConceitoAoElementoDaFrase("VERBO");
			                                 elementosDaFrase.verbos.add((((Sentenca_acao_usuarioContext)_localctx).VERB!=null?((Sentenca_acao_usuarioContext)_localctx).VERB.getText():null));
			                                 elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
			                             }
			                             
			                           
			setState(139); complementos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComplementosContext extends ParserRuleContext {
		public Qualquer_coisaContext qualquer_coisa() {
			return getRuleContext(Qualquer_coisaContext.class,0);
		}
		public Palavras_reservadasContext palavras_reservadas() {
			return getRuleContext(Palavras_reservadasContext.class,0);
		}
		public Lista_complementoContext lista_complemento(int i) {
			return getRuleContext(Lista_complementoContext.class,i);
		}
		public List<Lista_complementoContext> lista_complemento() {
			return getRuleContexts(Lista_complementoContext.class);
		}
		public ComplementosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complementos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterComplementos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitComplementos(this);
		}
	}

	public final ComplementosContext complementos() throws RecognitionException {
		ComplementosContext _localctx = new ComplementosContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_complementos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_la = _input.LA(1);
			if (_la==PALAVRASRESERVADAS) {
				{
				setState(141); palavras_reservadas();
				}
			}

			setState(144); qualquer_coisa();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << VIRGULA) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << ADV) | (1L << NUM) | (1L << CONJUNCAO))) != 0)) {
				{
				{
				setState(145); lista_complemento();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Lista_complementoContext extends ParserRuleContext {
		public Qualquer_coisaContext qualquer_coisa() {
			return getRuleContext(Qualquer_coisaContext.class,0);
		}
		public PalavrasContext palavras(int i) {
			return getRuleContext(PalavrasContext.class,i);
		}
		public List<PalavrasContext> palavras() {
			return getRuleContexts(PalavrasContext.class);
		}
		public TerminalNode VIRGULA() { return getToken(UCGrammarParser.VIRGULA, 0); }
		public TerminalNode CONJUNCAO() { return getToken(UCGrammarParser.CONJUNCAO, 0); }
		public Lista_complementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_complemento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterLista_complemento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitLista_complemento(this);
		}
	}

	public final Lista_complementoContext lista_complemento() throws RecognitionException {
		Lista_complementoContext _localctx = new Lista_complementoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lista_complemento);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				conceito ="";
				setState(153);
				_la = _input.LA(1);
				if (_la==VIRGULA) {
					{
					setState(152); match(VIRGULA);
					}
				}

				}
				break;

			case 2:
				{
				conceito ="";
				setState(157);
				_la = _input.LA(1);
				if (_la==CONJUNCAO) {
					{
					setState(156); match(CONJUNCAO);
					}
				}

				}
				break;
			}
			setState(161); qualquer_coisa();
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PALAVRA) {
				{
				{
				setState(162); palavras();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			addConceitoAoElementoDaFrase("ASPAS");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Qualquer_coisaContext extends ParserRuleContext {
		public Token PRON;
		public Token VERB;
		public Token SUBS;
		public Token NUM;
		public Token PREP;
		public Token ADJ;
		public Token ART;
		public TerminalNode ADV(int i) {
			return getToken(UCGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(UCGrammarParser.PRON); }
		public List<TerminalNode> ADV() { return getTokens(UCGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(UCGrammarParser.NUM); }
		public List<NumeroContext> numero() {
			return getRuleContexts(NumeroContext.class);
		}
		public TerminalNode ADJ(int i) {
			return getToken(UCGrammarParser.ADJ, i);
		}
		public List<TerminalNode> PREP() { return getTokens(UCGrammarParser.PREP); }
		public TerminalNode SUBS(int i) {
			return getToken(UCGrammarParser.SUBS, i);
		}
		public TerminalNode VERB(int i) {
			return getToken(UCGrammarParser.VERB, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(UCGrammarParser.PREP, i);
		}
		public TerminalNode ART(int i) {
			return getToken(UCGrammarParser.ART, i);
		}
		public TerminalNode NUM(int i) {
			return getToken(UCGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(UCGrammarParser.PRON, i);
		}
		public List<TerminalNode> ADJ() { return getTokens(UCGrammarParser.ADJ); }
		public NumeroContext numero(int i) {
			return getRuleContext(NumeroContext.class,i);
		}
		public List<TerminalNode> SUBS() { return getTokens(UCGrammarParser.SUBS); }
		public List<TerminalNode> VERB() { return getTokens(UCGrammarParser.VERB); }
		public List<TerminalNode> ART() { return getTokens(UCGrammarParser.ART); }
		public Qualquer_coisaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualquer_coisa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterQualquer_coisa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitQualquer_coisa(this);
		}
	}

	public final Qualquer_coisaContext qualquer_coisa() throws RecognitionException {
		Qualquer_coisaContext _localctx = new Qualquer_coisaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_qualquer_coisa);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			complemento = "";
			setState(197); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					setState(197);
					switch (_input.LA(1)) {
					case PRON:
						{
						ultimoComplemento = "";
						setState(172); ((Qualquer_coisaContext)_localctx).PRON = match(PRON);
						ultimoComplemento = (((Qualquer_coisaContext)_localctx).PRON!=null?((Qualquer_coisaContext)_localctx).PRON.getText():null); addPalavraAoComplemento((((Qualquer_coisaContext)_localctx).PRON!=null?((Qualquer_coisaContext)_localctx).PRON.getText():null));
						}
						break;
					case ADV:
						{
						ultimoComplemento = "";
						setState(175); match(ADV);
						}
						break;
					case VERB:
						{
						ultimoComplemento = "";
						setState(177); ((Qualquer_coisaContext)_localctx).VERB = match(VERB);

						                                                  ultimoComplemento = (((Qualquer_coisaContext)_localctx).VERB!=null?((Qualquer_coisaContext)_localctx).VERB.getText():null); 
						                                                  addPalavraAoComplemento((((Qualquer_coisaContext)_localctx).VERB!=null?((Qualquer_coisaContext)_localctx).VERB.getText():null));
						                                                  elementosDaFrase.verbos.add((((Qualquer_coisaContext)_localctx).VERB!=null?((Qualquer_coisaContext)_localctx).VERB.getText():null));
						                                                  elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
						                                                 
						}
						break;
					case SUBS:
						{
						ultimoComplemento = "";
						setState(180); ((Qualquer_coisaContext)_localctx).SUBS = match(SUBS);
						ultimoComplemento = (((Qualquer_coisaContext)_localctx).SUBS!=null?((Qualquer_coisaContext)_localctx).SUBS.getText():null); addPalavraAoComplemento((((Qualquer_coisaContext)_localctx).SUBS!=null?((Qualquer_coisaContext)_localctx).SUBS.getText():null));
						}
						break;
					case NUM:
						{
						ultimoComplemento = "";
						setState(183); ((Qualquer_coisaContext)_localctx).NUM = match(NUM);
						ultimoComplemento = (((Qualquer_coisaContext)_localctx).NUM!=null?((Qualquer_coisaContext)_localctx).NUM.getText():null); addPalavraAoComplemento((((Qualquer_coisaContext)_localctx).NUM!=null?((Qualquer_coisaContext)_localctx).NUM.getText():null));
						}
						break;
					case PREP:
						{
						ultimoComplemento = "";
						setState(186); ((Qualquer_coisaContext)_localctx).PREP = match(PREP);
						ultimoComplemento = (((Qualquer_coisaContext)_localctx).PREP!=null?((Qualquer_coisaContext)_localctx).PREP.getText():null); 
						                                                 if (!conceito.equals("")){
						                                                    addPalavraAoComplemento((((Qualquer_coisaContext)_localctx).PREP!=null?((Qualquer_coisaContext)_localctx).PREP.getText():null));
						                                                 }
						                                                 
						}
						break;
					case ADJ:
						{
						ultimoComplemento = "";
						setState(189); ((Qualquer_coisaContext)_localctx).ADJ = match(ADJ);
						ultimoComplemento = (((Qualquer_coisaContext)_localctx).ADJ!=null?((Qualquer_coisaContext)_localctx).ADJ.getText():null); addPalavraAoComplemento((((Qualquer_coisaContext)_localctx).ADJ!=null?((Qualquer_coisaContext)_localctx).ADJ.getText():null));
						}
						break;
					case ART:
						{
						ultimoComplemento = "";
						setState(192); ((Qualquer_coisaContext)_localctx).ART = match(ART);
						ultimoComplemento = (((Qualquer_coisaContext)_localctx).ART!=null?((Qualquer_coisaContext)_localctx).ART.getText():null);
						}
						break;
					case NUMERO:
						{
						setState(194); numero();
						ultimoComplemento = numero; addPalavraAoComplemento(numero);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(199); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			addConceitoAoElementoDaFrase("CCO1");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Qualquer_coisa_paraContext extends ParserRuleContext {
		public Token PRON;
		public Token ADV;
		public Token VERB;
		public Token SUBS;
		public Token NUM;
		public Token PREP;
		public Token ADJ;
		public Token ART;
		public TerminalNode ADV(int i) {
			return getToken(UCGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(UCGrammarParser.PRON); }
		public List<TerminalNode> ADV() { return getTokens(UCGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(UCGrammarParser.NUM); }
		public List<NumeroContext> numero() {
			return getRuleContexts(NumeroContext.class);
		}
		public TerminalNode ADJ(int i) {
			return getToken(UCGrammarParser.ADJ, i);
		}
		public List<TerminalNode> PREP() { return getTokens(UCGrammarParser.PREP); }
		public TerminalNode SUBS(int i) {
			return getToken(UCGrammarParser.SUBS, i);
		}
		public TerminalNode VERB(int i) {
			return getToken(UCGrammarParser.VERB, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(UCGrammarParser.PREP, i);
		}
		public TerminalNode ART(int i) {
			return getToken(UCGrammarParser.ART, i);
		}
		public TerminalNode NUM(int i) {
			return getToken(UCGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(UCGrammarParser.PRON, i);
		}
		public List<TerminalNode> ADJ() { return getTokens(UCGrammarParser.ADJ); }
		public NumeroContext numero(int i) {
			return getRuleContext(NumeroContext.class,i);
		}
		public List<TerminalNode> SUBS() { return getTokens(UCGrammarParser.SUBS); }
		public List<TerminalNode> VERB() { return getTokens(UCGrammarParser.VERB); }
		public List<TerminalNode> ART() { return getTokens(UCGrammarParser.ART); }
		public Qualquer_coisa_paraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualquer_coisa_para; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterQualquer_coisa_para(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitQualquer_coisa_para(this);
		}
	}

	public final Qualquer_coisa_paraContext qualquer_coisa_para() throws RecognitionException {
		Qualquer_coisa_paraContext _localctx = new Qualquer_coisa_paraContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_qualquer_coisa_para);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			complemento = "";
			setState(231); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(231);
				switch (_input.LA(1)) {
				case PRON:
					{
					ultimoComplemento = "";
					setState(205); ((Qualquer_coisa_paraContext)_localctx).PRON = match(PRON);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).PRON!=null?((Qualquer_coisa_paraContext)_localctx).PRON.getText():null); addPalavraAoComplemento((((Qualquer_coisa_paraContext)_localctx).PRON!=null?((Qualquer_coisa_paraContext)_localctx).PRON.getText():null));
					}
					break;
				case ADV:
					{
					ultimoComplemento = "";
					setState(208); ((Qualquer_coisa_paraContext)_localctx).ADV = match(ADV);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).ADV!=null?((Qualquer_coisa_paraContext)_localctx).ADV.getText():null); addPalavraAoComplemento((((Qualquer_coisa_paraContext)_localctx).ADV!=null?((Qualquer_coisa_paraContext)_localctx).ADV.getText():null));
					}
					break;
				case VERB:
					{
					ultimoComplemento = "";
					setState(211); ((Qualquer_coisa_paraContext)_localctx).VERB = match(VERB);

					                                                  ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).VERB!=null?((Qualquer_coisa_paraContext)_localctx).VERB.getText():null); 
					                                                  elementosDaFrase.verbos.add((((Qualquer_coisa_paraContext)_localctx).VERB!=null?((Qualquer_coisa_paraContext)_localctx).VERB.getText():null));
					                                                  elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
					                                                 
					}
					break;
				case SUBS:
					{
					ultimoComplemento = "";
					setState(214); ((Qualquer_coisa_paraContext)_localctx).SUBS = match(SUBS);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).SUBS!=null?((Qualquer_coisa_paraContext)_localctx).SUBS.getText():null); addPalavraAoComplemento((((Qualquer_coisa_paraContext)_localctx).SUBS!=null?((Qualquer_coisa_paraContext)_localctx).SUBS.getText():null));
					}
					break;
				case NUM:
					{
					ultimoComplemento = "";
					setState(217); ((Qualquer_coisa_paraContext)_localctx).NUM = match(NUM);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).NUM!=null?((Qualquer_coisa_paraContext)_localctx).NUM.getText():null); addPalavraAoComplemento((((Qualquer_coisa_paraContext)_localctx).NUM!=null?((Qualquer_coisa_paraContext)_localctx).NUM.getText():null));
					}
					break;
				case PREP:
					{
					ultimoComplemento = "";
					setState(220); ((Qualquer_coisa_paraContext)_localctx).PREP = match(PREP);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).PREP!=null?((Qualquer_coisa_paraContext)_localctx).PREP.getText():null); 
					                                                 if (!conceito.equals("")){
					                                                    addPalavraAoComplemento((((Qualquer_coisa_paraContext)_localctx).PREP!=null?((Qualquer_coisa_paraContext)_localctx).PREP.getText():null));
					                                                 }
					                                                 
					}
					break;
				case ADJ:
					{
					ultimoComplemento = "";
					setState(223); ((Qualquer_coisa_paraContext)_localctx).ADJ = match(ADJ);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).ADJ!=null?((Qualquer_coisa_paraContext)_localctx).ADJ.getText():null); addPalavraAoComplemento((((Qualquer_coisa_paraContext)_localctx).ADJ!=null?((Qualquer_coisa_paraContext)_localctx).ADJ.getText():null));
					}
					break;
				case ART:
					{
					ultimoComplemento = "";
					setState(226); ((Qualquer_coisa_paraContext)_localctx).ART = match(ART);
					ultimoComplemento = (((Qualquer_coisa_paraContext)_localctx).ART!=null?((Qualquer_coisa_paraContext)_localctx).ART.getText():null);
					}
					break;
				case NUMERO:
					{
					setState(228); numero();
					ultimoComplemento = numero; addPalavraAoComplemento(numero);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(233); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << ADV) | (1L << NUM))) != 0) );
			addConceitoAoElementoDaFrase("CCO1");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PalavrasContext extends ParserRuleContext {
		public Token PALAVRA;
		public TerminalNode PALAVRA() { return getToken(UCGrammarParser.PALAVRA, 0); }
		public PalavrasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_palavras; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterPalavras(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitPalavras(this);
		}
	}

	public final PalavrasContext palavras() throws RecognitionException {
		PalavrasContext _localctx = new PalavrasContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_palavras);
		try {
			enterOuterAlt(_localctx, 1);
			{
			ultimoComplemento = "";
			setState(238); ((PalavrasContext)_localctx).PALAVRA = match(PALAVRA);
			ultimoComplemento = (((PalavrasContext)_localctx).PALAVRA!=null?((PalavrasContext)_localctx).PALAVRA.getText():null); addPalavraAoComplemento((((PalavrasContext)_localctx).PALAVRA!=null?((PalavrasContext)_localctx).PALAVRA.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Palavras_reservadasContext extends ParserRuleContext {
		public Token PALAVRASRESERVADAS;
		public List<TerminalNode> PALAVRASRESERVADAS() { return getTokens(UCGrammarParser.PALAVRASRESERVADAS); }
		public TerminalNode PALAVRASRESERVADAS(int i) {
			return getToken(UCGrammarParser.PALAVRASRESERVADAS, i);
		}
		public Palavras_reservadasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_palavras_reservadas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterPalavras_reservadas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitPalavras_reservadas(this);
		}
	}

	public final Palavras_reservadasContext palavras_reservadas() throws RecognitionException {
		Palavras_reservadasContext _localctx = new Palavras_reservadasContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_palavras_reservadas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";
			setState(244); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(242); ((Palavras_reservadasContext)_localctx).PALAVRASRESERVADAS = match(PALAVRASRESERVADAS);
				testaConceito((((Palavras_reservadasContext)_localctx).PALAVRASRESERVADAS!=null?((Palavras_reservadasContext)_localctx).PALAVRASRESERVADAS.getText():null));
				}
				}
				setState(246); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PALAVRASRESERVADAS );
			addConceitoAoElementoDaFrase("PALRES");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Palavras_uc_1Context extends ParserRuleContext {
		public Token ART;
		public Token PRON;
		public Token CONJ;
		public Token ADV;
		public Token NUM;
		public TerminalNode ADV(int i) {
			return getToken(UCGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(UCGrammarParser.PRON); }
		public TerminalNode ART(int i) {
			return getToken(UCGrammarParser.ART, i);
		}
		public List<TerminalNode> ADV() { return getTokens(UCGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(UCGrammarParser.NUM); }
		public List<NumeroContext> numero() {
			return getRuleContexts(NumeroContext.class);
		}
		public TerminalNode NUM(int i) {
			return getToken(UCGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(UCGrammarParser.PRON, i);
		}
		public NumeroContext numero(int i) {
			return getRuleContext(NumeroContext.class,i);
		}
		public List<TerminalNode> CONJ() { return getTokens(UCGrammarParser.CONJ); }
		public List<TerminalNode> ART() { return getTokens(UCGrammarParser.ART); }
		public TerminalNode CONJ(int i) {
			return getToken(UCGrammarParser.CONJ, i);
		}
		public Palavras_uc_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_palavras_uc_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterPalavras_uc_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitPalavras_uc_1(this);
		}
	}

	public final Palavras_uc_1Context palavras_uc_1() throws RecognitionException {
		Palavras_uc_1Context _localctx = new Palavras_uc_1Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_palavras_uc_1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			complemento = "";
			setState(272); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(272);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					ultimoComplemento = "";
					setState(252); ((Palavras_uc_1Context)_localctx).ART = match(ART);
					ultimoComplemento = (((Palavras_uc_1Context)_localctx).ART!=null?((Palavras_uc_1Context)_localctx).ART.getText():null); addPalavraAoComplemento((((Palavras_uc_1Context)_localctx).ART!=null?((Palavras_uc_1Context)_localctx).ART.getText():null));
					}
					break;

				case 2:
					{
					ultimoComplemento = "";
					setState(255); ((Palavras_uc_1Context)_localctx).PRON = match(PRON);
					ultimoComplemento = (((Palavras_uc_1Context)_localctx).PRON!=null?((Palavras_uc_1Context)_localctx).PRON.getText():null); addPalavraAoComplemento((((Palavras_uc_1Context)_localctx).PRON!=null?((Palavras_uc_1Context)_localctx).PRON.getText():null));
					}
					break;

				case 3:
					{
					ultimoComplemento = "";
					setState(258); ((Palavras_uc_1Context)_localctx).CONJ = match(CONJ);
					ultimoComplemento = (((Palavras_uc_1Context)_localctx).CONJ!=null?((Palavras_uc_1Context)_localctx).CONJ.getText():null); addPalavraAoComplemento((((Palavras_uc_1Context)_localctx).CONJ!=null?((Palavras_uc_1Context)_localctx).CONJ.getText():null));
					}
					break;

				case 4:
					{
					ultimoComplemento = "";
					setState(261); ((Palavras_uc_1Context)_localctx).ADV = match(ADV);
					ultimoComplemento = (((Palavras_uc_1Context)_localctx).ADV!=null?((Palavras_uc_1Context)_localctx).ADV.getText():null); addPalavraAoComplemento((((Palavras_uc_1Context)_localctx).ADV!=null?((Palavras_uc_1Context)_localctx).ADV.getText():null));
					}
					break;

				case 5:
					{
					ultimoComplemento = "";
					setState(264); ((Palavras_uc_1Context)_localctx).NUM = match(NUM);
					ultimoComplemento = (((Palavras_uc_1Context)_localctx).NUM!=null?((Palavras_uc_1Context)_localctx).NUM.getText():null); addPalavraAoComplemento((((Palavras_uc_1Context)_localctx).NUM!=null?((Palavras_uc_1Context)_localctx).NUM.getText():null));
					}
					break;

				case 6:
					{
					ultimoComplemento = "";
					setState(267); ((Palavras_uc_1Context)_localctx).ART = match(ART);
					ultimoComplemento = (((Palavras_uc_1Context)_localctx).ART!=null?((Palavras_uc_1Context)_localctx).ART.getText():null);
					}
					break;

				case 7:
					{
					setState(269); numero();
					ultimoComplemento = numero; addPalavraAoComplemento(numero);
					}
					break;
				}
				}
				setState(274); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << ART) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sentenca_condicionalContext extends ParserRuleContext {
		public Token SE;
		public Token ENTAO;
		public Sentenca_logicaContext sentenca_logica() {
			return getRuleContext(Sentenca_logicaContext.class,0);
		}
		public TerminalNode ENTAO() { return getToken(UCGrammarParser.ENTAO, 0); }
		public TerminalNode SE() { return getToken(UCGrammarParser.SE, 0); }
		public Sentenca_condicionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenca_condicional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterSentenca_condicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitSentenca_condicional(this);
		}
	}

	public final Sentenca_condicionalContext sentenca_condicional() throws RecognitionException {
		Sentenca_condicionalContext _localctx = new Sentenca_condicionalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_sentenca_condicional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";
			setState(277); ((Sentenca_condicionalContext)_localctx).SE = match(SE);
			testaConceito((((Sentenca_condicionalContext)_localctx).SE!=null?((Sentenca_condicionalContext)_localctx).SE.getText():null));
			addConceitoAoElementoDaFrase("SE");
			tipoFrase = 2;
			setState(281); sentenca_logica();
			conceito ="";
			setState(283); ((Sentenca_condicionalContext)_localctx).ENTAO = match(ENTAO);
			testaConceito((((Sentenca_condicionalContext)_localctx).ENTAO!=null?((Sentenca_condicionalContext)_localctx).ENTAO.getText():null));
			addConceitoAoElementoDaFrase("ENTAO");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sentenca_logicaContext extends ParserRuleContext {
		public Token CONJUNCAO;
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public TerminalNode CONJUNCAO(int i) {
			return getToken(UCGrammarParser.CONJUNCAO, i);
		}
		public List<TerminalNode> CONJUNCAO() { return getTokens(UCGrammarParser.CONJUNCAO); }
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public Sentenca_logicaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentenca_logica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterSentenca_logica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitSentenca_logica(this);
		}
	}

	public final Sentenca_logicaContext sentenca_logica() throws RecognitionException {
		Sentenca_logicaContext _localctx = new Sentenca_logicaContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_sentenca_logica);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(287); expressao();
				}
			}

			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(290); ((Sentenca_logicaContext)_localctx).CONJUNCAO = match(CONJUNCAO);

					                                       conceito =""; 
					                                       setProximo((((Sentenca_logicaContext)_localctx).CONJUNCAO!=null?((Sentenca_logicaContext)_localctx).CONJUNCAO.getText():null),"96 usuario_classe_atributo");
					                                       testaConceito((((Sentenca_logicaContext)_localctx).CONJUNCAO!=null?((Sentenca_logicaContext)_localctx).CONJUNCAO.getText():null));
					                                       addConceitoAoElementoDaFrase("EOU");
					                                       
					setState(292); expressao();
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoContext extends ParserRuleContext {
		public E1Context e1;
		public Token VERB;
		public Sentenca_logicaContext sentenca_logica() {
			return getRuleContext(Sentenca_logicaContext.class,0);
		}
		public PalavrasContext palavras(int i) {
			return getRuleContext(PalavrasContext.class,i);
		}
		public List<E1Context> e1() {
			return getRuleContexts(E1Context.class);
		}
		public E1Context e1(int i) {
			return getRuleContext(E1Context.class,i);
		}
		public List<PalavrasContext> palavras() {
			return getRuleContexts(PalavrasContext.class);
		}
		public Palavras_reservadasContext palavras_reservadas() {
			return getRuleContext(Palavras_reservadasContext.class,0);
		}
		public TerminalNode VERB() { return getToken(UCGrammarParser.VERB, 0); }
		public ExpressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitExpressao(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expressao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; 
			setState(300);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << PREP) | (1L << PRON) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(299); ((ExpressaoContext)_localctx).e1 = e1();
				}
			}

			addConceitoAoElementoDaFrase("E1");
			conceito =""; 
			setState(304); ((ExpressaoContext)_localctx).VERB = match(VERB);
			 testaConceito((((ExpressaoContext)_localctx).VERB!=null?((ExpressaoContext)_localctx).VERB.getText():null));
			                              setProximo((((ExpressaoContext)_localctx).VERB!=null?((ExpressaoContext)_localctx).VERB.getText():null),"94 confVerbo");
			                              ultimoVerbo = (((ExpressaoContext)_localctx).VERB!=null?((ExpressaoContext)_localctx).VERB.getText():null);
			                              addConceitoAoElementoDaFrase("VERBSE");
			                              elementosDaFrase.verbos.add((((ExpressaoContext)_localctx).VERB!=null?((ExpressaoContext)_localctx).VERB.getText():null));
			                              elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
			                            
			setState(307);
			_la = _input.LA(1);
			if (_la==PALAVRASRESERVADAS) {
				{
				setState(306); palavras_reservadas();
				}
			}

			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PALAVRA) {
				{
				{
				setState(309); palavras();
				}
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			addConceitoAoElementoDaFrase("ASPAS");
			conceito ="";
			setState(321);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(317); ((ExpressaoContext)_localctx).e1 = e1();
				setProximo((((ExpressaoContext)_localctx).e1!=null?_input.getText(((ExpressaoContext)_localctx).e1.start,((ExpressaoContext)_localctx).e1.stop):null),"95 e_ou_entao");
				           addConceitoAoElementoDaFrase("E2");
				}
				break;

			case 2:
				{
				setState(320); sentenca_logica();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class E1Context extends ParserRuleContext {
		public Token PRON;
		public Token SUBS;
		public Token NUM;
		public Token PREP;
		public Token ADJ;
		public TerminalNode ADV(int i) {
			return getToken(UCGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(UCGrammarParser.PRON); }
		public List<TerminalNode> NUM() { return getTokens(UCGrammarParser.NUM); }
		public List<TerminalNode> ADV() { return getTokens(UCGrammarParser.ADV); }
		public List<NumeroContext> numero() {
			return getRuleContexts(NumeroContext.class);
		}
		public TerminalNode ADJ(int i) {
			return getToken(UCGrammarParser.ADJ, i);
		}
		public List<TerminalNode> PREP() { return getTokens(UCGrammarParser.PREP); }
		public TerminalNode SUBS(int i) {
			return getToken(UCGrammarParser.SUBS, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(UCGrammarParser.PREP, i);
		}
		public TerminalNode ART(int i) {
			return getToken(UCGrammarParser.ART, i);
		}
		public TerminalNode NUM(int i) {
			return getToken(UCGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(UCGrammarParser.PRON, i);
		}
		public List<TerminalNode> ADJ() { return getTokens(UCGrammarParser.ADJ); }
		public NumeroContext numero(int i) {
			return getRuleContext(NumeroContext.class,i);
		}
		public List<TerminalNode> SUBS() { return getTokens(UCGrammarParser.SUBS); }
		public List<TerminalNode> ART() { return getTokens(UCGrammarParser.ART); }
		public E1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_e1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterE1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitE1(this);
		}
	}

	public final E1Context e1() throws RecognitionException {
		E1Context _localctx = new E1Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_e1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			complemento = "";
			setState(344); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(344);
				switch (_input.LA(1)) {
				case PRON:
					{
					ultimoComplemento = "";
					setState(325); ((E1Context)_localctx).PRON = match(PRON);
					ultimoComplemento = (((E1Context)_localctx).PRON!=null?((E1Context)_localctx).PRON.getText():null); addPalavraAoComplemento((((E1Context)_localctx).PRON!=null?((E1Context)_localctx).PRON.getText():null));
					}
					break;
				case SUBS:
					{
					ultimoComplemento = "";
					setState(328); ((E1Context)_localctx).SUBS = match(SUBS);
					ultimoComplemento = (((E1Context)_localctx).SUBS!=null?((E1Context)_localctx).SUBS.getText():null); addPalavraAoComplemento((((E1Context)_localctx).SUBS!=null?((E1Context)_localctx).SUBS.getText():null));
					}
					break;
				case NUM:
					{
					ultimoComplemento = "";
					setState(331); ((E1Context)_localctx).NUM = match(NUM);
					ultimoComplemento = (((E1Context)_localctx).NUM!=null?((E1Context)_localctx).NUM.getText():null); addPalavraAoComplemento((((E1Context)_localctx).NUM!=null?((E1Context)_localctx).NUM.getText():null));
					}
					break;
				case PREP:
					{
					ultimoComplemento = "";
					setState(334); ((E1Context)_localctx).PREP = match(PREP);
					ultimoComplemento = (((E1Context)_localctx).PREP!=null?((E1Context)_localctx).PREP.getText():null); 
					                                                 if (!conceito.equals("")){
					                                                    addPalavraAoComplemento((((E1Context)_localctx).PREP!=null?((E1Context)_localctx).PREP.getText():null));
					                                                 }
					                                                 
					}
					break;
				case ADJ:
					{
					ultimoComplemento = "";
					setState(337); ((E1Context)_localctx).ADJ = match(ADJ);
					ultimoComplemento = (((E1Context)_localctx).ADJ!=null?((E1Context)_localctx).ADJ.getText():null); addPalavraAoComplemento((((E1Context)_localctx).ADJ!=null?((E1Context)_localctx).ADJ.getText():null));
					}
					break;
				case ADV:
					{
					setState(339); match(ADV);
					}
					break;
				case ART:
					{
					setState(340); match(ART);
					}
					break;
				case NUMERO:
					{
					setState(341); numero();
					ultimoComplemento = numero; addPalavraAoComplemento(numero);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(346); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << PREP) | (1L << PRON) | (1L << ADV) | (1L << NUM))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conceito_1Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token ADJ;
		public Token s2;
		public TerminalNode PREP() { return getToken(UCGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(UCGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(UCGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(UCGrammarParser.SUBS); }
		public Conceito_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceito_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).enterConceito_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UCGrammarListener ) ((UCGrammarListener)listener).exitConceito_1(this);
		}
	}

	public final Conceito_1Context conceito_1() throws RecognitionException {
		Conceito_1Context _localctx = new Conceito_1Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_conceito_1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(349); ((Conceito_1Context)_localctx).s1 = match(SUBS);
			testaConceito((((Conceito_1Context)_localctx).s1!=null?((Conceito_1Context)_localctx).s1.getText():null));
			setState(352);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(351); ((Conceito_1Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Conceito_1Context)_localctx).PREP!=null?((Conceito_1Context)_localctx).PREP.getText():null));
			setState(359);
			switch (_input.LA(1)) {
			case ADJ:
				{
				setState(355); ((Conceito_1Context)_localctx).ADJ = match(ADJ);
				testaConceito((((Conceito_1Context)_localctx).ADJ!=null?((Conceito_1Context)_localctx).ADJ.getText():null));
				}
				break;
			case SUBS:
				{
				setState(357); ((Conceito_1Context)_localctx).s2 = match(SUBS);
				testaConceito((((Conceito_1Context)_localctx).s2!=null?((Conceito_1Context)_localctx).s2.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			addConceito();
			                                 int atual = getTokenStream().index()-1;
			                                 getTokenStream().seek(atual);                            
			                                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\34\u016e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\5\2.\n\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\5\38\n\3\3\4\3\4\3\4\3\4\5\4>\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5Q\n\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7`\n\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\3\t\5\tr\n\t\3\t\3\t\3\t\3"+
		"\t\5\tx\n\t\3\t\3\t\5\t|\n\t\3\t\3\t\5\t\u0080\n\t\3\t\5\t\u0083\n\t\3"+
		"\t\3\t\3\t\5\t\u0088\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\5\n\u0091\n\n\3\n"+
		"\3\n\7\n\u0095\n\n\f\n\16\n\u0098\13\n\3\13\3\13\5\13\u009c\n\13\3\13"+
		"\3\13\5\13\u00a0\n\13\5\13\u00a2\n\13\3\13\3\13\7\13\u00a6\n\13\f\13\16"+
		"\13\u00a9\13\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00c8"+
		"\n\f\r\f\16\f\u00c9\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\6\r\u00ea\n\r\r\r\16\r\u00eb\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\6\17\u00f7\n\17\r\17\16\17\u00f8\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\6\20\u0113\n\20\r\20\16\20\u0114\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\5\22\u0123\n\22\3\22\3\22\3\22"+
		"\7\22\u0128\n\22\f\22\16\22\u012b\13\22\3\23\3\23\5\23\u012f\n\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0136\n\23\3\23\7\23\u0139\n\23\f\23\16\23\u013c"+
		"\13\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0144\n\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\6\24\u015b\n\24\r\24\16\24\u015c\3\25\3\25\3\25\3\25"+
		"\5\25\u0163\n\25\3\25\3\25\3\25\3\25\3\25\5\25\u016a\n\25\3\25\3\25\3"+
		"\25\2\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\2\u019a\2*\3\2"+
		"\2\2\4\67\3\2\2\2\6=\3\2\2\2\b?\3\2\2\2\nT\3\2\2\2\f[\3\2\2\2\16c\3\2"+
		"\2\2\20q\3\2\2\2\22\u0090\3\2\2\2\24\u00a1\3\2\2\2\26\u00ac\3\2\2\2\30"+
		"\u00cd\3\2\2\2\32\u00ef\3\2\2\2\34\u00f3\3\2\2\2\36\u00fc\3\2\2\2 \u0116"+
		"\3\2\2\2\"\u0122\3\2\2\2$\u012c\3\2\2\2&\u0145\3\2\2\2(\u015e\3\2\2\2"+
		"*+\b\2\1\2+-\5\4\3\2,.\5\6\4\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\60\b\2\1"+
		"\2\60\3\3\2\2\2\61\62\7\f\2\2\628\b\3\1\2\63\64\7\21\2\2\648\b\3\1\2\65"+
		"\66\7\24\2\2\668\b\3\1\2\67\61\3\2\2\2\67\63\3\2\2\2\67\65\3\2\2\28\5"+
		"\3\2\2\29:\7\24\2\2:>\b\4\1\2;<\7\21\2\2<>\b\4\1\2=9\3\2\2\2=;\3\2\2\2"+
		">\7\3\2\2\2?@\5\16\b\2@A\b\5\1\2AB\b\5\1\2BP\b\5\1\2CD\5\20\t\2DE\b\5"+
		"\1\2EF\7\n\2\2FG\b\5\1\2GH\b\5\1\2HQ\3\2\2\2IQ\5 \21\2JK\7\27\2\2KL\b"+
		"\5\1\2LM\b\5\1\2MQ\b\5\1\2NQ\5\f\7\2OQ\5\n\6\2PC\3\2\2\2PI\3\2\2\2PJ\3"+
		"\2\2\2PN\3\2\2\2PO\3\2\2\2QR\3\2\2\2RS\b\5\1\2S\t\3\2\2\2TU\b\6\1\2UV"+
		"\7\30\2\2VW\b\6\1\2WX\b\6\1\2XY\b\6\1\2YZ\5\"\22\2Z\13\3\2\2\2[\\\b\7"+
		"\1\2\\]\7\31\2\2]_\b\7\1\2^`\5\2\2\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\5"+
		"\30\r\2b\r\3\2\2\2cd\b\b\1\2de\7\5\2\2ef\b\b\1\2fm\3\2\2\2gh\7\n\2\2h"+
		"l\b\b\1\2ij\7\5\2\2jl\b\b\1\2kg\3\2\2\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2"+
		"mn\3\2\2\2n\17\3\2\2\2om\3\2\2\2pr\5\2\2\2qp\3\2\2\2qr\3\2\2\2rs\3\2\2"+
		"\2st\b\t\1\2tu\7\16\2\2uw\b\t\1\2vx\7\20\2\2wv\3\2\2\2wx\3\2\2\2xy\3\2"+
		"\2\2y\u0082\b\t\1\2z|\7\16\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\u0083\b"+
		"\t\1\2~\u0080\7\r\2\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2"+
		"\2\u0081\u0083\b\t\1\2\u0082{\3\2\2\2\u0082\177\3\2\2\2\u0083\u0084\3"+
		"\2\2\2\u0084\u0085\b\t\1\2\u0085\u0087\b\t\1\2\u0086\u0088\5\36\20\2\u0087"+
		"\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\t"+
		"\1\2\u008a\u008b\b\t\1\2\u008b\u008c\7\17\2\2\u008c\u008d\b\t\1\2\u008d"+
		"\u008e\5\22\n\2\u008e\21\3\2\2\2\u008f\u0091\5\34\17\2\u0090\u008f\3\2"+
		"\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0096\5\26\f\2\u0093"+
		"\u0095\5\24\13\2\u0094\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3"+
		"\2\2\2\u0096\u0097\3\2\2\2\u0097\23\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"\u009b\b\13\1\2\u009a\u009c\7\t\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3"+
		"\2\2\2\u009c\u00a2\3\2\2\2\u009d\u009f\b\13\1\2\u009e\u00a0\7\32\2\2\u009f"+
		"\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u0099\3\2"+
		"\2\2\u00a1\u009d\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a7\5\26\f\2\u00a4"+
		"\u00a6\5\32\16\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3"+
		"\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa"+
		"\u00ab\b\13\1\2\u00ab\25\3\2\2\2\u00ac\u00c7\b\f\1\2\u00ad\u00ae\b\f\1"+
		"\2\u00ae\u00af\7\21\2\2\u00af\u00c8\b\f\1\2\u00b0\u00b1\b\f\1\2\u00b1"+
		"\u00c8\7\23\2\2\u00b2\u00b3\b\f\1\2\u00b3\u00b4\7\17\2\2\u00b4\u00c8\b"+
		"\f\1\2\u00b5\u00b6\b\f\1\2\u00b6\u00b7\7\16\2\2\u00b7\u00c8\b\f\1\2\u00b8"+
		"\u00b9\b\f\1\2\u00b9\u00ba\7\24\2\2\u00ba\u00c8\b\f\1\2\u00bb\u00bc\b"+
		"\f\1\2\u00bc\u00bd\7\20\2\2\u00bd\u00c8\b\f\1\2\u00be\u00bf\b\f\1\2\u00bf"+
		"\u00c0\7\r\2\2\u00c0\u00c8\b\f\1\2\u00c1\u00c2\b\f\1\2\u00c2\u00c3\7\f"+
		"\2\2\u00c3\u00c8\b\f\1\2\u00c4\u00c5\5\16\b\2\u00c5\u00c6\b\f\1\2\u00c6"+
		"\u00c8\3\2\2\2\u00c7\u00ad\3\2\2\2\u00c7\u00b0\3\2\2\2\u00c7\u00b2\3\2"+
		"\2\2\u00c7\u00b5\3\2\2\2\u00c7\u00b8\3\2\2\2\u00c7\u00bb\3\2\2\2\u00c7"+
		"\u00be\3\2\2\2\u00c7\u00c1\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00cc\b\f\1\2\u00cc\27\3\2\2\2\u00cd\u00e9\b\r\1\2\u00ce\u00cf\b\r\1"+
		"\2\u00cf\u00d0\7\21\2\2\u00d0\u00ea\b\r\1\2\u00d1\u00d2\b\r\1\2\u00d2"+
		"\u00d3\7\23\2\2\u00d3\u00ea\b\r\1\2\u00d4\u00d5\b\r\1\2\u00d5\u00d6\7"+
		"\17\2\2\u00d6\u00ea\b\r\1\2\u00d7\u00d8\b\r\1\2\u00d8\u00d9\7\16\2\2\u00d9"+
		"\u00ea\b\r\1\2\u00da\u00db\b\r\1\2\u00db\u00dc\7\24\2\2\u00dc\u00ea\b"+
		"\r\1\2\u00dd\u00de\b\r\1\2\u00de\u00df\7\20\2\2\u00df\u00ea\b\r\1\2\u00e0"+
		"\u00e1\b\r\1\2\u00e1\u00e2\7\r\2\2\u00e2\u00ea\b\r\1\2\u00e3\u00e4\b\r"+
		"\1\2\u00e4\u00e5\7\f\2\2\u00e5\u00ea\b\r\1\2\u00e6\u00e7\5\16\b\2\u00e7"+
		"\u00e8\b\r\1\2\u00e8\u00ea\3\2\2\2\u00e9\u00ce\3\2\2\2\u00e9\u00d1\3\2"+
		"\2\2\u00e9\u00d4\3\2\2\2\u00e9\u00d7\3\2\2\2\u00e9\u00da\3\2\2\2\u00e9"+
		"\u00dd\3\2\2\2\u00e9\u00e0\3\2\2\2\u00e9\u00e3\3\2\2\2\u00e9\u00e6\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00ee\b\r\1\2\u00ee\31\3\2\2\2\u00ef\u00f0\b\16\1"+
		"\2\u00f0\u00f1\7\4\2\2\u00f1\u00f2\b\16\1\2\u00f2\33\3\2\2\2\u00f3\u00f6"+
		"\b\17\1\2\u00f4\u00f5\7\33\2\2\u00f5\u00f7\b\17\1\2\u00f6\u00f4\3\2\2"+
		"\2\u00f7\u00f8\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa"+
		"\3\2\2\2\u00fa\u00fb\b\17\1\2\u00fb\35\3\2\2\2\u00fc\u0112\b\20\1\2\u00fd"+
		"\u00fe\b\20\1\2\u00fe\u00ff\7\f\2\2\u00ff\u0113\b\20\1\2\u0100\u0101\b"+
		"\20\1\2\u0101\u0102\7\21\2\2\u0102\u0113\b\20\1\2\u0103\u0104\b\20\1\2"+
		"\u0104\u0105\7\22\2\2\u0105\u0113\b\20\1\2\u0106\u0107\b\20\1\2\u0107"+
		"\u0108\7\23\2\2\u0108\u0113\b\20\1\2\u0109\u010a\b\20\1\2\u010a\u010b"+
		"\7\24\2\2\u010b\u0113\b\20\1\2\u010c\u010d\b\20\1\2\u010d\u010e\7\f\2"+
		"\2\u010e\u0113\b\20\1\2\u010f\u0110\5\16\b\2\u0110\u0111\b\20\1\2\u0111"+
		"\u0113\3\2\2\2\u0112\u00fd\3\2\2\2\u0112\u0100\3\2\2\2\u0112\u0103\3\2"+
		"\2\2\u0112\u0106\3\2\2\2\u0112\u0109\3\2\2\2\u0112\u010c\3\2\2\2\u0112"+
		"\u010f\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115\37\3\2\2\2\u0116\u0117\b\21\1\2\u0117\u0118\7\34\2\2\u0118"+
		"\u0119\b\21\1\2\u0119\u011a\b\21\1\2\u011a\u011b\b\21\1\2\u011b\u011c"+
		"\5\"\22\2\u011c\u011d\b\21\1\2\u011d\u011e\7\26\2\2\u011e\u011f\b\21\1"+
		"\2\u011f\u0120\b\21\1\2\u0120!\3\2\2\2\u0121\u0123\5$\23\2\u0122\u0121"+
		"\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0129\3\2\2\2\u0124\u0125\7\32\2\2"+
		"\u0125\u0126\b\22\1\2\u0126\u0128\5$\23\2\u0127\u0124\3\2\2\2\u0128\u012b"+
		"\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a#\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012c\u012e\b\23\1\2\u012d\u012f\5&\24\2\u012e\u012d\3"+
		"\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b\23\1\2\u0131"+
		"\u0132\b\23\1\2\u0132\u0133\7\17\2\2\u0133\u0135\b\23\1\2\u0134\u0136"+
		"\5\34\17\2\u0135\u0134\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u013a\3\2\2\2"+
		"\u0137\u0139\5\32\16\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d"+
		"\u013e\b\23\1\2\u013e\u0143\b\23\1\2\u013f\u0140\5&\24\2\u0140\u0141\b"+
		"\23\1\2\u0141\u0144\3\2\2\2\u0142\u0144\5\"\22\2\u0143\u013f\3\2\2\2\u0143"+
		"\u0142\3\2\2\2\u0144%\3\2\2\2\u0145\u015a\b\24\1\2\u0146\u0147\b\24\1"+
		"\2\u0147\u0148\7\21\2\2\u0148\u015b\b\24\1\2\u0149\u014a\b\24\1\2\u014a"+
		"\u014b\7\16\2\2\u014b\u015b\b\24\1\2\u014c\u014d\b\24\1\2\u014d\u014e"+
		"\7\24\2\2\u014e\u015b\b\24\1\2\u014f\u0150\b\24\1\2\u0150\u0151\7\20\2"+
		"\2\u0151\u015b\b\24\1\2\u0152\u0153\b\24\1\2\u0153\u0154\7\r\2\2\u0154"+
		"\u015b\b\24\1\2\u0155\u015b\7\23\2\2\u0156\u015b\7\f\2\2\u0157\u0158\5"+
		"\16\b\2\u0158\u0159\b\24\1\2\u0159\u015b\3\2\2\2\u015a\u0146\3\2\2\2\u015a"+
		"\u0149\3\2\2\2\u015a\u014c\3\2\2\2\u015a\u014f\3\2\2\2\u015a\u0152\3\2"+
		"\2\2\u015a\u0155\3\2\2\2\u015a\u0156\3\2\2\2\u015a\u0157\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\'\3\2\2\2"+
		"\u015e\u015f\b\25\1\2\u015f\u0160\7\16\2\2\u0160\u0162\b\25\1\2\u0161"+
		"\u0163\7\20\2\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\3"+
		"\2\2\2\u0164\u0169\b\25\1\2\u0165\u0166\7\r\2\2\u0166\u016a\b\25\1\2\u0167"+
		"\u0168\7\16\2\2\u0168\u016a\b\25\1\2\u0169\u0165\3\2\2\2\u0169\u0167\3"+
		"\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\b\25\1\2\u016c)\3\2\2\2&-\67=P"+
		"_kmqw{\177\u0082\u0087\u0090\u0096\u009b\u009f\u00a1\u00a7\u00c7\u00c9"+
		"\u00e9\u00eb\u00f8\u0112\u0114\u0122\u0129\u012e\u0135\u013a\u0143\u015a"+
		"\u015c\u0162\u0169";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}