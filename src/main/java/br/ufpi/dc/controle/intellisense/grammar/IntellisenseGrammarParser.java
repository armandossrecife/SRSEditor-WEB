// Generated from D:\mestrado\netbeans\SRSEDITORM\src\intellisense\grammar\IntellisenseGrammar.g4 by ANTLR 4.1

package br.ufpi.dc.controle.intellisense.grammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;



import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import br.ufpi.dc.tools.Constante;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IntellisenseGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, PALAVRA=2, NUMERO=3, SIMBOLOS=4, TRACO=5, TERMINAL=6, VIRGULA=7, 
		MODELO=8, ART=9, ADJ=10, SUBS=11, VERB=12, PREP=13, PRON=14, CONJ=15, 
		ADV=16, NUM=17, PALAVRAESTRANGEIRA=18, SISTEMA=19, DEVE=20, PONTO=21, 
		PERMITIR=22, SEPARADOR=23;
	public static final String[] tokenNames = {
		"<INVALID>", "WS", "PALAVRA", "NUMERO", "SIMBOLOS", "'-'", "TERMINAL", 
		"','", "MODELO", "ART", "ADJ", "SUBS", "VERB", "PREP", "PRON", "CONJ", 
		"ADV", "NUM", "PALAVRAESTRANGEIRA", "SISTEMA", "DEVE", "PONTO", "PERMITIR", 
		"SEPARADOR"
	};
	public static final int
		RULE_osistemadeve = 0, RULE_o = 1, RULE_sist = 2, RULE_deve = 3, RULE_det = 4, 
		RULE_det_base = 5, RULE_pos_det = 6, RULE_palavras = 7, RULE_inicio = 8, 
		RULE_p1 = 9, RULE_sv = 10, RULE_f1 = 11, RULE_iteracao_f1 = 12, RULE_iteracao_verbo = 13, 
		RULE_sn_usuario_f1 = 14, RULE_sn_conceito_classe_f1 = 15, RULE_f3 = 16, 
		RULE_iteracao_f3 = 17, RULE_sn_usuario_f4 = 18, RULE_exp_temporal = 19, 
		RULE_sn_conceito_classe_f5 = 20, RULE_sn_conceito_classe_f4 = 21, RULE_sn_usuario_f3 = 22, 
		RULE_sn_conceito_classe_f3 = 23, RULE_conceitos_1 = 24, RULE_conceito_1 = 25, 
		RULE_substantivo = 26, RULE_verbo = 27, RULE_outros_1 = 28;
	public static final String[] ruleNames = {
		"osistemadeve", "o", "sist", "deve", "det", "det_base", "pos_det", "palavras", 
		"inicio", "p1", "sv", "f1", "iteracao_f1", "iteracao_verbo", "sn_usuario_f1", 
		"sn_conceito_classe_f1", "f3", "iteracao_f3", "sn_usuario_f4", "exp_temporal", 
		"sn_conceito_classe_f5", "sn_conceito_classe_f4", "sn_usuario_f3", "sn_conceito_classe_f3", 
		"conceitos_1", "conceito_1", "substantivo", "verbo", "outros_1"
	};

	@Override
	public String getGrammarFileName() { return "IntellisenseGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	    public ArrayList<String> oracoes    = new ArrayList<String>(); 
	    public ArrayList<String> conceitos  = new ArrayList<String>(); 
	    
	    public ArrayList<Integer> listTokenDoConceito   = new ArrayList<Integer>();
	    public ArrayList<String> listConceitoTokenizado = new ArrayList<String>();
	    
	    public HashMap<Integer,ArrayList<String>> conceitosTipados = new HashMap<Integer,ArrayList<String>>();
	   
	    private boolean ok                = true;
	    public  int     tipoFrase         = 0;

	    private String terminal           = ".?!;:,";
	    private String conceito           = "";
	    private String conceitoTokenizado = "";
	    
	    public String oracao            = ""; 
	    public String proximo           = "";
	    public String caminho           = "";
	    public String ultimaPalavra     = "";
	    public String complemento       = "";
	    public String ultimoAtor        = "";
	    public String ultimoVerbo       = "";
	    public String ultimoConceito    = "";
	    public String ultimoComplemento = "";
	    public String numero            = "";
	    public String pontoAntesSenao   = "";
	    public String pontoDepoisSenao  = "";
	    public String temp              = "";
	    
	    
	    private void addPalavra(String palavra){
	      if (palavra!=null){
	           palavra = palavra.toLowerCase();
	           if (terminal.contains(palavra)){                                 
	               oracao = oracao + palavra;
	           }else{
	                 if (oracao.equals("")){
	                     oracao = palavra;
	                 }
	                 else{
	                      oracao = oracao + " " + palavra;
	                     }
	                 }
	           }
	    }
	    
	    private void addConceito(){
	                               
	       if (!conceito.equals("") && (ok) && !conceito.contains("<missing")){
	         conceitos.add(conceito.toLowerCase());
	       }    
	       
	       conceito = "";
	       ok = true;
	    }
	  
	    private void addPalavraAoConceito(String palavra){
	         if (palavra != null){
	             if (conceito.equals("")){
	                 conceito           = palavra;
	                 conceitoTokenizado = palavra;
	             }else{
	                 conceito           = conceito + " " + palavra;   
	                 conceitoTokenizado = conceitoTokenizado+palavra;
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
	            if ((palavra.length()>=4 || palavra.toUpperCase().equals(palavra)) && (ok) && !palavra.contains("<missing")){
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
	                System.out.print(token    + " --> " + proximo + " | ");
	                caminho = caminho + token + " --> " + proximo + " | ";
	            }
	    }

	   public void adicionaConceitoTipado(Integer tipoConceito){
	       if(!conceito.equals("") && conceitosTipados.containsKey(tipoConceito)){
	          conceitosTipados.get(tipoConceito).add(conceito);
	       }else{
	          if (!conceito.equals("")){
	             conceitosTipados.put(tipoConceito,new ArrayList<String>());
	             conceitosTipados.get(tipoConceito).add(conceito);
	          }
	       }
	       if (!conceito.equals("")){      
	         listTokenDoConceito.add(tipoConceito);
	         listConceitoTokenizado.add(conceitoTokenizado);   
	       }
	   }
	   
	  

	public IntellisenseGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class OsistemadeveContext extends ParserRuleContext {
		public OContext o() {
			return getRuleContext(OContext.class,0);
		}
		public SistContext sist() {
			return getRuleContext(SistContext.class,0);
		}
		public DeveContext deve() {
			return getRuleContext(DeveContext.class,0);
		}
		public OsistemadeveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_osistemadeve; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterOsistemadeve(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitOsistemadeve(this);
		}
	}

	public final OsistemadeveContext osistemadeve() throws RecognitionException {
		OsistemadeveContext _localctx = new OsistemadeveContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_osistemadeve);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); o();
			setState(59); sist();
			setState(61);
			_la = _input.LA(1);
			if (_la==DEVE) {
				{
				setState(60); deve();
				}
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

	public static class OContext extends ParserRuleContext {
		public TerminalNode ART() { return getToken(IntellisenseGrammarParser.ART, 0); }
		public OContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_o; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterO(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitO(this);
		}
	}

	public final OContext o() throws RecognitionException {
		OContext _localctx = new OContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_o);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); match(ART);
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

	public static class SistContext extends ParserRuleContext {
		public Token SISTEMA;
		public TerminalNode SISTEMA() { return getToken(IntellisenseGrammarParser.SISTEMA, 0); }
		public SistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSist(this);
		}
	}

	public final SistContext sist() throws RecognitionException {
		SistContext _localctx = new SistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sist);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; conceitoTokenizado="";
			setState(66); ((SistContext)_localctx).SISTEMA = match(SISTEMA);
			setProximo((((SistContext)_localctx).SISTEMA!=null?((SistContext)_localctx).SISTEMA.getText():null),"1 DEVE");
			         testaConceito((((SistContext)_localctx).SISTEMA!=null?((SistContext)_localctx).SISTEMA.getText():null));
			         adicionaConceitoTipado(Constante.SISTEMA);
			        
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

	public static class DeveContext extends ParserRuleContext {
		public Token DEVE;
		public TerminalNode DEVE() { return getToken(IntellisenseGrammarParser.DEVE, 0); }
		public DeveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deve; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterDeve(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitDeve(this);
		}
	}

	public final DeveContext deve() throws RecognitionException {
		DeveContext _localctx = new DeveContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_deve);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; conceitoTokenizado="";
			setState(70); ((DeveContext)_localctx).DEVE = match(DEVE);
			setProximo((((DeveContext)_localctx).DEVE!=null?((DeveContext)_localctx).DEVE.getText():null), "2 verbo");
			              testaConceito((((DeveContext)_localctx).DEVE!=null?((DeveContext)_localctx).DEVE.getText():null));
			              adicionaConceitoTipado(Constante.DEVE);
			             
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
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterDet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitDet(this);
		}
	}

	public final DetContext det() throws RecognitionException {
		DetContext _localctx = new DetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_det);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73); det_base();
			setState(75);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(74); pos_det();
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

	public static class Det_baseContext extends ParserRuleContext {
		public TerminalNode PRON() { return getToken(IntellisenseGrammarParser.PRON, 0); }
		public TerminalNode NUM() { return getToken(IntellisenseGrammarParser.NUM, 0); }
		public TerminalNode ART() { return getToken(IntellisenseGrammarParser.ART, 0); }
		public Det_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_det_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterDet_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitDet_base(this);
		}
	}

	public final Det_baseContext det_base() throws RecognitionException {
		Det_baseContext _localctx = new Det_baseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_det_base);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		public TerminalNode PRON() { return getToken(IntellisenseGrammarParser.PRON, 0); }
		public TerminalNode NUM() { return getToken(IntellisenseGrammarParser.NUM, 0); }
		public Pos_detContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos_det; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterPos_det(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitPos_det(this);
		}
	}

	public final Pos_detContext pos_det() throws RecognitionException {
		Pos_detContext _localctx = new Pos_detContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pos_det);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_la = _input.LA(1);
			if ( !(_la==PRON || _la==NUM) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		public List<TerminalNode> PREP() { return getTokens(IntellisenseGrammarParser.PREP); }
		public TerminalNode ADV(int i) {
			return getToken(IntellisenseGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(IntellisenseGrammarParser.PRON); }
		public TerminalNode ART(int i) {
			return getToken(IntellisenseGrammarParser.ART, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(IntellisenseGrammarParser.PREP, i);
		}
		public List<TerminalNode> ADV() { return getTokens(IntellisenseGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(IntellisenseGrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(IntellisenseGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(IntellisenseGrammarParser.PRON, i);
		}
		public List<TerminalNode> CONJ() { return getTokens(IntellisenseGrammarParser.CONJ); }
		public List<TerminalNode> ART() { return getTokens(IntellisenseGrammarParser.ART); }
		public TerminalNode CONJ(int i) {
			return getToken(IntellisenseGrammarParser.CONJ, i);
		}
		public PalavrasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_palavras; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterPalavras(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitPalavras(this);
		}
	}

	public final PalavrasContext palavras() throws RecognitionException {
		PalavrasContext _localctx = new PalavrasContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_palavras);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(81);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class InicioContext extends ParserRuleContext {
		public P1Context p1() {
			return getRuleContext(P1Context.class,0);
		}
		public F3Context f3() {
			return getRuleContext(F3Context.class,0);
		}
		public InicioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inicio; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterInicio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitInicio(this);
		}
	}

	public final InicioContext inicio() throws RecognitionException {
		InicioContext _localctx = new InicioContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_inicio);
		try {
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87); p1();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88); f3();
				}
				break;
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

	public static class P1Context extends ParserRuleContext {
		public Token PONTO;
		public SvContext sv() {
			return getRuleContext(SvContext.class,0);
		}
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(IntellisenseGrammarParser.PONTO, 0); }
		public OsistemadeveContext osistemadeve() {
			return getRuleContext(OsistemadeveContext.class,0);
		}
		public P1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterP1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitP1(this);
		}
	}

	public final P1Context p1() throws RecognitionException {
		P1Context _localctx = new P1Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_p1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); osistemadeve();
			setState(92); sv();
			setState(93); palavras();
			setState(94); ((P1Context)_localctx).PONTO = match(PONTO);
			 if ((((P1Context)_localctx).PONTO!=null?((P1Context)_localctx).PONTO.getText():null).equals(".")) {
			                                                      proximo = "";
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

	public static class SvContext extends ParserRuleContext {
		public Token PERMITIR;
		public TerminalNode PERMITIR() { return getToken(IntellisenseGrammarParser.PERMITIR, 0); }
		public F1Context f1() {
			return getRuleContext(F1Context.class,0);
		}
		public SvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSv(this);
		}
	}

	public final SvContext sv() throws RecognitionException {
		SvContext _localctx = new SvContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sv);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; conceitoTokenizado="";
			setState(98); ((SvContext)_localctx).PERMITIR = match(PERMITIR);

			                                 setProximo((((SvContext)_localctx).PERMITIR!=null?((SvContext)_localctx).PERMITIR.getText():null),"2 usuario");
			                                 testaConceito((((SvContext)_localctx).PERMITIR!=null?((SvContext)_localctx).PERMITIR.getText():null));
			                                 adicionaConceitoTipado(Constante.PERMITIR);
			                                 
			setState(100); f1();
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

	public static class F1Context extends ParserRuleContext {
		public Iteracao_f1Context iteracao_f1() {
			return getRuleContext(Iteracao_f1Context.class,0);
		}
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public Sn_usuario_f1Context sn_usuario_f1() {
			return getRuleContext(Sn_usuario_f1Context.class,0);
		}
		public F1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterF1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitF1(this);
		}
	}

	public final F1Context f1() throws RecognitionException {
		F1Context _localctx = new F1Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_f1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); palavras();
			setState(103); sn_usuario_f1();
			setState(104); iteracao_f1();
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

	public static class Iteracao_f1Context extends ParserRuleContext {
		public Token VERB;
		public PalavrasContext palavras;
		public Iteracao_verboContext iteracao_verbo(int i) {
			return getRuleContext(Iteracao_verboContext.class,i);
		}
		public List<Iteracao_verboContext> iteracao_verbo() {
			return getRuleContexts(Iteracao_verboContext.class);
		}
		public TerminalNode SEPARADOR(int i) {
			return getToken(IntellisenseGrammarParser.SEPARADOR, i);
		}
		public TerminalNode VERB(int i) {
			return getToken(IntellisenseGrammarParser.VERB, i);
		}
		public Sn_conceito_classe_f1Context sn_conceito_classe_f1(int i) {
			return getRuleContext(Sn_conceito_classe_f1Context.class,i);
		}
		public PalavrasContext palavras(int i) {
			return getRuleContext(PalavrasContext.class,i);
		}
		public List<Sn_conceito_classe_f1Context> sn_conceito_classe_f1() {
			return getRuleContexts(Sn_conceito_classe_f1Context.class);
		}
		public List<PalavrasContext> palavras() {
			return getRuleContexts(PalavrasContext.class);
		}
		public List<TerminalNode> SEPARADOR() { return getTokens(IntellisenseGrammarParser.SEPARADOR); }
		public List<TerminalNode> VERB() { return getTokens(IntellisenseGrammarParser.VERB); }
		public Iteracao_f1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteracao_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterIteracao_f1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitIteracao_f1(this);
		}
	}

	public final Iteracao_f1Context iteracao_f1() throws RecognitionException {
		Iteracao_f1Context _localctx = new Iteracao_f1Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_iteracao_f1);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				conceito =""; conceitoTokenizado="";
				setState(107); ((Iteracao_f1Context)_localctx).VERB = match(VERB);
				setProximo((((Iteracao_f1Context)_localctx).VERB!=null?((Iteracao_f1Context)_localctx).VERB.getText():null),"3 classe");
				                              testaConceito((((Iteracao_f1Context)_localctx).VERB!=null?((Iteracao_f1Context)_localctx).VERB.getText():null));
				                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
				                             
				{
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(109); iteracao_verbo();
						}
						} 
					}
					setState(114);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				conceito =""; conceitoTokenizado="";
				setState(116); ((Iteracao_f1Context)_localctx).palavras = palavras();
				setProximo((((Iteracao_f1Context)_localctx).palavras!=null?_input.getText(((Iteracao_f1Context)_localctx).palavras.start,((Iteracao_f1Context)_localctx).palavras.stop):null),"3 classe");
				                              testaConceito((((Iteracao_f1Context)_localctx).palavras!=null?_input.getText(((Iteracao_f1Context)_localctx).palavras.start,((Iteracao_f1Context)_localctx).palavras.stop):null));
				                             
				setState(118); sn_conceito_classe_f1();
				setState(120);
				_la = _input.LA(1);
				if (_la==SEPARADOR) {
					{
					setState(119); match(SEPARADOR);
					}
				}

				}
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VERB );
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

	public static class Iteracao_verboContext extends ParserRuleContext {
		public Token SEPARADOR;
		public Token CONJ;
		public Token VERB;
		public TerminalNode SEPARADOR() { return getToken(IntellisenseGrammarParser.SEPARADOR, 0); }
		public TerminalNode CONJ() { return getToken(IntellisenseGrammarParser.CONJ, 0); }
		public TerminalNode VERB() { return getToken(IntellisenseGrammarParser.VERB, 0); }
		public Iteracao_verboContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteracao_verbo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterIteracao_verbo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitIteracao_verbo(this);
		}
	}

	public final Iteracao_verboContext iteracao_verbo() throws RecognitionException {
		Iteracao_verboContext _localctx = new Iteracao_verboContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_iteracao_verbo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			switch (_input.LA(1)) {
			case SEPARADOR:
				{
				setState(126); ((Iteracao_verboContext)_localctx).SEPARADOR = match(SEPARADOR);
				setProximo((((Iteracao_verboContext)_localctx).SEPARADOR!=null?((Iteracao_verboContext)_localctx).SEPARADOR.getText():null),"30 verbo");
				}
				break;
			case CONJ:
				{
				setState(128); ((Iteracao_verboContext)_localctx).CONJ = match(CONJ);
				setProximo((((Iteracao_verboContext)_localctx).CONJ!=null?((Iteracao_verboContext)_localctx).CONJ.getText():null),"31 verbo");
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			conceito =""; conceitoTokenizado="";
			setState(133); ((Iteracao_verboContext)_localctx).VERB = match(VERB);
			setProximo((((Iteracao_verboContext)_localctx).VERB!=null?((Iteracao_verboContext)_localctx).VERB.getText():null),"31 verbo");
			                              testaConceito((((Iteracao_verboContext)_localctx).VERB!=null?((Iteracao_verboContext)_localctx).VERB.getText():null));
			                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
			                             
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

	public static class Sn_usuario_f1Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_usuario_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_usuario_f1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_usuario_f1(this);
		}
	}

	public final Sn_usuario_f1Context sn_usuario_f1() throws RecognitionException {
		Sn_usuario_f1Context _localctx = new Sn_usuario_f1Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_sn_usuario_f1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(136); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(140); ((Sn_usuario_f1Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_usuario_f1Context)_localctx).s1!=null?((Sn_usuario_f1Context)_localctx).s1.getText():null),"4 verbo");
			                                testaConceito((((Sn_usuario_f1Context)_localctx).s1!=null?((Sn_usuario_f1Context)_localctx).s1.getText():null));
			                               
			setState(143);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(142); ((Sn_usuario_f1Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sn_usuario_f1Context)_localctx).PREP!=null?((Sn_usuario_f1Context)_localctx).PREP.getText():null));
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(147);
				_la = _input.LA(1);
				if (_la==SUBS) {
					{
					setState(146); ((Sn_usuario_f1Context)_localctx).s2 = match(SUBS);
					}
				}

				testaConceito((((Sn_usuario_f1Context)_localctx).s2!=null?((Sn_usuario_f1Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(151);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(150); ((Sn_usuario_f1Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_usuario_f1Context)_localctx).ADJ!=null?((Sn_usuario_f1Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.USUARIO);
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

	public static class Sn_conceito_classe_f1Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_conceito_classe_f1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_conceito_classe_f1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_conceito_classe_f1(this);
		}
	}

	public final Sn_conceito_classe_f1Context sn_conceito_classe_f1() throws RecognitionException {
		Sn_conceito_classe_f1Context _localctx = new Sn_conceito_classe_f1Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_sn_conceito_classe_f1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(158); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(162); ((Sn_conceito_classe_f1Context)_localctx).s1 = match(SUBS);
			setProximo ((((Sn_conceito_classe_f1Context)_localctx).s1!=null?((Sn_conceito_classe_f1Context)_localctx).s1.getText():null),"5 verbo");
			                                testaConceito((((Sn_conceito_classe_f1Context)_localctx).s1!=null?((Sn_conceito_classe_f1Context)_localctx).s1.getText():null));
			                                
			setState(165);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(164); ((Sn_conceito_classe_f1Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Sn_conceito_classe_f1Context)_localctx).PREP!=null?((Sn_conceito_classe_f1Context)_localctx).PREP.getText():null));
			setState(176);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(169);
				_la = _input.LA(1);
				if (_la==SUBS) {
					{
					setState(168); ((Sn_conceito_classe_f1Context)_localctx).s2 = match(SUBS);
					}
				}

				testaConceito((((Sn_conceito_classe_f1Context)_localctx).s2!=null?((Sn_conceito_classe_f1Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(173);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(172); ((Sn_conceito_classe_f1Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_conceito_classe_f1Context)_localctx).ADJ!=null?((Sn_conceito_classe_f1Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
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

	public static class F3Context extends ParserRuleContext {
		public Token DEVE;
		public Token PONTO;
		public Iteracao_f3Context iteracao_f3(int i) {
			return getRuleContext(Iteracao_f3Context.class,i);
		}
		public TerminalNode DEVE() { return getToken(IntellisenseGrammarParser.DEVE, 0); }
		public TerminalNode SISTEMA() { return getToken(IntellisenseGrammarParser.SISTEMA, 0); }
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(IntellisenseGrammarParser.PONTO, 0); }
		public List<Iteracao_f3Context> iteracao_f3() {
			return getRuleContexts(Iteracao_f3Context.class);
		}
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f3Context sn_usuario_f3() {
			return getRuleContext(Sn_usuario_f3Context.class,0);
		}
		public F3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterF3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitF3(this);
		}
	}

	public final F3Context f3() throws RecognitionException {
		F3Context _localctx = new F3Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_f3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); det();
			setState(183);
			switch (_input.LA(1)) {
			case SISTEMA:
				{
				setState(181); match(SISTEMA);
				}
				break;
			case ART:
			case SUBS:
			case PRON:
			case NUM:
				{
				setState(182); sn_usuario_f3();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(185); ((F3Context)_localctx).DEVE = match(DEVE);
			setProximo((((F3Context)_localctx).DEVE!=null?((F3Context)_localctx).DEVE.getText():null),"12 verbo");
			setState(187); palavras();
			setState(189); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(188); iteracao_f3();
				}
				}
				setState(191); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VERB );
			setState(193); ((F3Context)_localctx).PONTO = match(PONTO);
			 if ((((F3Context)_localctx).PONTO!=null?((F3Context)_localctx).PONTO.getText():null).equals(".")) {
			               proximo = "";
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

	public static class Iteracao_f3Context extends ParserRuleContext {
		public Token VERB;
		public PalavrasContext palavras;
		public Iteracao_verboContext iteracao_verbo(int i) {
			return getRuleContext(Iteracao_verboContext.class,i);
		}
		public List<Iteracao_verboContext> iteracao_verbo() {
			return getRuleContexts(Iteracao_verboContext.class);
		}
		public Sn_conceito_classe_f4Context sn_conceito_classe_f4() {
			return getRuleContext(Sn_conceito_classe_f4Context.class,0);
		}
		public Sn_conceito_classe_f3Context sn_conceito_classe_f3() {
			return getRuleContext(Sn_conceito_classe_f3Context.class,0);
		}
		public Exp_temporalContext exp_temporal() {
			return getRuleContext(Exp_temporalContext.class,0);
		}
		public PalavrasContext palavras(int i) {
			return getRuleContext(PalavrasContext.class,i);
		}
		public List<PalavrasContext> palavras() {
			return getRuleContexts(PalavrasContext.class);
		}
		public TerminalNode SEPARADOR() { return getToken(IntellisenseGrammarParser.SEPARADOR, 0); }
		public TerminalNode VERB() { return getToken(IntellisenseGrammarParser.VERB, 0); }
		public Iteracao_f3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteracao_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterIteracao_f3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitIteracao_f3(this);
		}
	}

	public final Iteracao_f3Context iteracao_f3() throws RecognitionException {
		Iteracao_f3Context _localctx = new Iteracao_f3Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_iteracao_f3);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";conceitoTokenizado="";
			setState(197); ((Iteracao_f3Context)_localctx).VERB = match(VERB);
			setProximo((((Iteracao_f3Context)_localctx).VERB!=null?((Iteracao_f3Context)_localctx).VERB.getText():null),"9 classe");
			                              testaConceito((((Iteracao_f3Context)_localctx).VERB!=null?((Iteracao_f3Context)_localctx).VERB.getText():null));
			                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
			                             
			{
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(199); iteracao_verbo();
					}
					} 
				}
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			conceito =""; conceitoTokenizado="";
			setState(206); ((Iteracao_f3Context)_localctx).palavras = palavras();
			setProximo((((Iteracao_f3Context)_localctx).palavras!=null?_input.getText(((Iteracao_f3Context)_localctx).palavras.start,((Iteracao_f3Context)_localctx).palavras.stop):null),"3 classe");
			                       testaConceito((((Iteracao_f3Context)_localctx).palavras!=null?_input.getText(((Iteracao_f3Context)_localctx).palavras.start,((Iteracao_f3Context)_localctx).palavras.stop):null));
			setState(208); sn_conceito_classe_f3();
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(209); ((Iteracao_f3Context)_localctx).palavras = palavras();
				}
				break;
			}
			setState(212); sn_conceito_classe_f4();
			setState(214);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << SUBS) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(213); exp_temporal();
				}
			}

			setState(217);
			_la = _input.LA(1);
			if (_la==SEPARADOR) {
				{
				setState(216); match(SEPARADOR);
				}
			}

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

	public static class Sn_usuario_f4Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_usuario_f4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_usuario_f4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_usuario_f4(this);
		}
	}

	public final Sn_usuario_f4Context sn_usuario_f4() throws RecognitionException {
		Sn_usuario_f4Context _localctx = new Sn_usuario_f4Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_sn_usuario_f4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(219); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(223); ((Sn_usuario_f4Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_usuario_f4Context)_localctx).s1!=null?((Sn_usuario_f4Context)_localctx).s1.getText():null),"121 verbo");
			                                testaConceito((((Sn_usuario_f4Context)_localctx).s1!=null?((Sn_usuario_f4Context)_localctx).s1.getText():null));
			                               
			setState(226);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(225); ((Sn_usuario_f4Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sn_usuario_f4Context)_localctx).PREP!=null?((Sn_usuario_f4Context)_localctx).PREP.getText():null));
			setState(237);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(230);
				_la = _input.LA(1);
				if (_la==SUBS) {
					{
					setState(229); ((Sn_usuario_f4Context)_localctx).s2 = match(SUBS);
					}
				}

				testaConceito((((Sn_usuario_f4Context)_localctx).s2!=null?((Sn_usuario_f4Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(234);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(233); ((Sn_usuario_f4Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_usuario_f4Context)_localctx).ADJ!=null?((Sn_usuario_f4Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.USUARIO);
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

	public static class Exp_temporalContext extends ParserRuleContext {
		public Token VERB;
		public PalavrasContext palavras(int i) {
			return getRuleContext(PalavrasContext.class,i);
		}
		public List<PalavrasContext> palavras() {
			return getRuleContexts(PalavrasContext.class);
		}
		public Sn_usuario_f4Context sn_usuario_f4() {
			return getRuleContext(Sn_usuario_f4Context.class,0);
		}
		public TerminalNode VERB() { return getToken(IntellisenseGrammarParser.VERB, 0); }
		public Sn_conceito_classe_f5Context sn_conceito_classe_f5() {
			return getRuleContext(Sn_conceito_classe_f5Context.class,0);
		}
		public Exp_temporalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_temporal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterExp_temporal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitExp_temporal(this);
		}
	}

	public final Exp_temporalContext exp_temporal() throws RecognitionException {
		Exp_temporalContext _localctx = new Exp_temporalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exp_temporal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(241); palavras();
				}
				break;
			}
			setState(244); sn_usuario_f4();
			conceito ="";conceitoTokenizado="";
			setState(246); ((Exp_temporalContext)_localctx).VERB = match(VERB);
			setProximo((((Exp_temporalContext)_localctx).VERB!=null?((Exp_temporalContext)_localctx).VERB.getText():null),"9 classe");
			                              testaConceito((((Exp_temporalContext)_localctx).VERB!=null?((Exp_temporalContext)_localctx).VERB.getText():null));
			                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
			                             
			setState(248); palavras();
			setState(249); sn_conceito_classe_f5();
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

	public static class Sn_conceito_classe_f5Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_conceito_classe_f5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_conceito_classe_f5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_conceito_classe_f5(this);
		}
	}

	public final Sn_conceito_classe_f5Context sn_conceito_classe_f5() throws RecognitionException {
		Sn_conceito_classe_f5Context _localctx = new Sn_conceito_classe_f5Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_sn_conceito_classe_f5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(251); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(255); ((Sn_conceito_classe_f5Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_conceito_classe_f5Context)_localctx).s1!=null?((Sn_conceito_classe_f5Context)_localctx).s1.getText():null),"14 verbo");
			                                testaConceito((((Sn_conceito_classe_f5Context)_localctx).s1!=null?((Sn_conceito_classe_f5Context)_localctx).s1.getText():null));
			                               
			setState(258);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(257); ((Sn_conceito_classe_f5Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sn_conceito_classe_f5Context)_localctx).PREP!=null?((Sn_conceito_classe_f5Context)_localctx).PREP.getText():null));
			setState(269);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(262);
				_la = _input.LA(1);
				if (_la==SUBS) {
					{
					setState(261); ((Sn_conceito_classe_f5Context)_localctx).s2 = match(SUBS);
					}
				}

				testaConceito((((Sn_conceito_classe_f5Context)_localctx).s2!=null?((Sn_conceito_classe_f5Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(266);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(265); ((Sn_conceito_classe_f5Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_conceito_classe_f5Context)_localctx).ADJ!=null?((Sn_conceito_classe_f5Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
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

	public static class Sn_conceito_classe_f4Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_conceito_classe_f4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_conceito_classe_f4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_conceito_classe_f4(this);
		}
	}

	public final Sn_conceito_classe_f4Context sn_conceito_classe_f4() throws RecognitionException {
		Sn_conceito_classe_f4Context _localctx = new Sn_conceito_classe_f4Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_sn_conceito_classe_f4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(273); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(277); ((Sn_conceito_classe_f4Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_conceito_classe_f4Context)_localctx).s1!=null?((Sn_conceito_classe_f4Context)_localctx).s1.getText():null),"13 usuario");
			                                testaConceito((((Sn_conceito_classe_f4Context)_localctx).s1!=null?((Sn_conceito_classe_f4Context)_localctx).s1.getText():null));
			                               
			setState(280);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(279); ((Sn_conceito_classe_f4Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Sn_conceito_classe_f4Context)_localctx).PREP!=null?((Sn_conceito_classe_f4Context)_localctx).PREP.getText():null));
			setState(291);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(284);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(283); ((Sn_conceito_classe_f4Context)_localctx).s2 = match(SUBS);
					}
					break;
				}
				testaConceito((((Sn_conceito_classe_f4Context)_localctx).s2!=null?((Sn_conceito_classe_f4Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(288);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(287); ((Sn_conceito_classe_f4Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_conceito_classe_f4Context)_localctx).ADJ!=null?((Sn_conceito_classe_f4Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
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

	public static class Sn_usuario_f3Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_usuario_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_usuario_f3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_usuario_f3(this);
		}
	}

	public final Sn_usuario_f3Context sn_usuario_f3() throws RecognitionException {
		Sn_usuario_f3Context _localctx = new Sn_usuario_f3Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_sn_usuario_f3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(295); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(299); ((Sn_usuario_f3Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_usuario_f3Context)_localctx).s1!=null?((Sn_usuario_f3Context)_localctx).s1.getText():null),"10 verbo");
			                                testaConceito((((Sn_usuario_f3Context)_localctx).s1!=null?((Sn_usuario_f3Context)_localctx).s1.getText():null));
			                               
			setState(302);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(301); ((Sn_usuario_f3Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sn_usuario_f3Context)_localctx).PREP!=null?((Sn_usuario_f3Context)_localctx).PREP.getText():null));
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(306);
				_la = _input.LA(1);
				if (_la==SUBS) {
					{
					setState(305); ((Sn_usuario_f3Context)_localctx).s2 = match(SUBS);
					}
				}

				testaConceito((((Sn_usuario_f3Context)_localctx).s2!=null?((Sn_usuario_f3Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(310);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(309); ((Sn_usuario_f3Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_usuario_f3Context)_localctx).ADJ!=null?((Sn_usuario_f3Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.USUARIO);
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

	public static class Sn_conceito_classe_f3Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_conceito_classe_f3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSn_conceito_classe_f3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSn_conceito_classe_f3(this);
		}
	}

	public final Sn_conceito_classe_f3Context sn_conceito_classe_f3() throws RecognitionException {
		Sn_conceito_classe_f3Context _localctx = new Sn_conceito_classe_f3Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_sn_conceito_classe_f3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(317); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(321); ((Sn_conceito_classe_f3Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_conceito_classe_f3Context)_localctx).s1!=null?((Sn_conceito_classe_f3Context)_localctx).s1.getText():null),"11 usuario");
			                                testaConceito((((Sn_conceito_classe_f3Context)_localctx).s1!=null?((Sn_conceito_classe_f3Context)_localctx).s1.getText():null));
			                               
			setState(324);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(323); ((Sn_conceito_classe_f3Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Sn_conceito_classe_f3Context)_localctx).PREP!=null?((Sn_conceito_classe_f3Context)_localctx).PREP.getText():null));
			setState(335);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(328);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(327); ((Sn_conceito_classe_f3Context)_localctx).s2 = match(SUBS);
					}
					break;
				}
				testaConceito((((Sn_conceito_classe_f3Context)_localctx).s2!=null?((Sn_conceito_classe_f3Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(332);
				_la = _input.LA(1);
				if (_la==ADJ) {
					{
					setState(331); ((Sn_conceito_classe_f3Context)_localctx).ADJ = match(ADJ);
					}
				}

				testaConceito((((Sn_conceito_classe_f3Context)_localctx).ADJ!=null?((Sn_conceito_classe_f3Context)_localctx).ADJ.getText():null));
				}
				break;
			}
			adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
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

	public static class Conceitos_1Context extends ParserRuleContext {
		public List<VerboContext> verbo() {
			return getRuleContexts(VerboContext.class);
		}
		public Conceito_1Context conceito_1(int i) {
			return getRuleContext(Conceito_1Context.class,i);
		}
		public SubstantivoContext substantivo(int i) {
			return getRuleContext(SubstantivoContext.class,i);
		}
		public Outros_1Context outros_1(int i) {
			return getRuleContext(Outros_1Context.class,i);
		}
		public VerboContext verbo(int i) {
			return getRuleContext(VerboContext.class,i);
		}
		public List<Conceito_1Context> conceito_1() {
			return getRuleContexts(Conceito_1Context.class);
		}
		public List<SubstantivoContext> substantivo() {
			return getRuleContexts(SubstantivoContext.class);
		}
		public List<Outros_1Context> outros_1() {
			return getRuleContexts(Outros_1Context.class);
		}
		public Conceitos_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceitos_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterConceitos_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitConceitos_1(this);
		}
	}

	public final Conceitos_1Context conceitos_1() throws RecognitionException {
		Conceitos_1Context _localctx = new Conceitos_1Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_conceitos_1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(343);
				switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(339); conceito_1();
					}
					break;

				case 2:
					{
					setState(340); substantivo();
					}
					break;

				case 3:
					{
					setState(341); verbo();
					}
					break;

				case 4:
					{
					setState(342); outros_1();
					}
					break;
				}
				}
				setState(347);
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

	public static class Conceito_1Context extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token ADJ;
		public Token s2;
		public TerminalNode PREP() { return getToken(IntellisenseGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(IntellisenseGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(IntellisenseGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(IntellisenseGrammarParser.SUBS); }
		public Conceito_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceito_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterConceito_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitConceito_1(this);
		}
	}

	public final Conceito_1Context conceito_1() throws RecognitionException {
		Conceito_1Context _localctx = new Conceito_1Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_conceito_1);
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

	public static class SubstantivoContext extends ParserRuleContext {
		public Token SUBS;
		public TerminalNode SUBS() { return getToken(IntellisenseGrammarParser.SUBS, 0); }
		public SubstantivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_substantivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterSubstantivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitSubstantivo(this);
		}
	}

	public final SubstantivoContext substantivo() throws RecognitionException {
		SubstantivoContext _localctx = new SubstantivoContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_substantivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(364); ((SubstantivoContext)_localctx).SUBS = match(SUBS);
			testaConceito((((SubstantivoContext)_localctx).SUBS!=null?((SubstantivoContext)_localctx).SUBS.getText():null));addConceito();
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

	public static class VerboContext extends ParserRuleContext {
		public Token VERB;
		public TerminalNode VERB() { return getToken(IntellisenseGrammarParser.VERB, 0); }
		public VerboContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verbo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterVerbo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitVerbo(this);
		}
	}

	public final VerboContext verbo() throws RecognitionException {
		VerboContext _localctx = new VerboContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_verbo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(368); ((VerboContext)_localctx).VERB = match(VERB);
			testaConceito((((VerboContext)_localctx).VERB!=null?((VerboContext)_localctx).VERB.getText():null));addConceito();
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

	public static class Outros_1Context extends ParserRuleContext {
		public TerminalNode ADV(int i) {
			return getToken(IntellisenseGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(IntellisenseGrammarParser.PRON); }
		public List<TerminalNode> SIMBOLOS() { return getTokens(IntellisenseGrammarParser.SIMBOLOS); }
		public List<TerminalNode> ADV() { return getTokens(IntellisenseGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(IntellisenseGrammarParser.NUM); }
		public List<TerminalNode> NUMERO() { return getTokens(IntellisenseGrammarParser.NUMERO); }
		public List<TerminalNode> TERMINAL() { return getTokens(IntellisenseGrammarParser.TERMINAL); }
		public List<TerminalNode> CONJ() { return getTokens(IntellisenseGrammarParser.CONJ); }
		public TerminalNode SIMBOLOS(int i) {
			return getToken(IntellisenseGrammarParser.SIMBOLOS, i);
		}
		public TerminalNode TERMINAL(int i) {
			return getToken(IntellisenseGrammarParser.TERMINAL, i);
		}
		public TerminalNode NUMERO(int i) {
			return getToken(IntellisenseGrammarParser.NUMERO, i);
		}
		public List<TerminalNode> PREP() { return getTokens(IntellisenseGrammarParser.PREP); }
		public TerminalNode ART(int i) {
			return getToken(IntellisenseGrammarParser.ART, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(IntellisenseGrammarParser.PREP, i);
		}
		public TerminalNode NUM(int i) {
			return getToken(IntellisenseGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(IntellisenseGrammarParser.PRON, i);
		}
		public List<TerminalNode> ART() { return getTokens(IntellisenseGrammarParser.ART); }
		public TerminalNode CONJ(int i) {
			return getToken(IntellisenseGrammarParser.CONJ, i);
		}
		public Outros_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).enterOutros_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IntellisenseGrammarListener ) ((IntellisenseGrammarListener)listener).exitOutros_1(this);
		}
	}

	public final Outros_1Context outros_1() throws RecognitionException {
		Outros_1Context _localctx = new Outros_1Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_outros_1);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(372); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(371);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(374); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\31\u017b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\5\2@\n"+
		"\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\5\6N\n\6\3\7\3\7\3"+
		"\b\3\b\3\t\7\tU\n\t\f\t\16\tX\13\t\3\n\3\n\5\n\\\n\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\7\16q\n\16\f\16\16\16t\13\16\3\16\3\16\3\16\3\16\3\16\5\16{\n\16\6\16"+
		"}\n\16\r\16\16\16~\3\17\3\17\3\17\3\17\5\17\u0085\n\17\3\17\3\17\3\17"+
		"\3\17\3\20\5\20\u008c\n\20\3\20\3\20\3\20\3\20\5\20\u0092\n\20\3\20\3"+
		"\20\5\20\u0096\n\20\3\20\3\20\5\20\u009a\n\20\3\20\5\20\u009d\n\20\3\20"+
		"\3\20\3\21\5\21\u00a2\n\21\3\21\3\21\3\21\3\21\5\21\u00a8\n\21\3\21\3"+
		"\21\5\21\u00ac\n\21\3\21\3\21\5\21\u00b0\n\21\3\21\5\21\u00b3\n\21\3\21"+
		"\3\21\3\22\3\22\3\22\5\22\u00ba\n\22\3\22\3\22\3\22\3\22\6\22\u00c0\n"+
		"\22\r\22\16\22\u00c1\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u00cb\n\23"+
		"\f\23\16\23\u00ce\13\23\3\23\3\23\3\23\3\23\3\23\5\23\u00d5\n\23\3\23"+
		"\3\23\5\23\u00d9\n\23\3\23\5\23\u00dc\n\23\3\24\5\24\u00df\n\24\3\24\3"+
		"\24\3\24\3\24\5\24\u00e5\n\24\3\24\3\24\5\24\u00e9\n\24\3\24\3\24\5\24"+
		"\u00ed\n\24\3\24\5\24\u00f0\n\24\3\24\3\24\3\25\5\25\u00f5\n\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\26\5\26\u00ff\n\26\3\26\3\26\3\26\3\26"+
		"\5\26\u0105\n\26\3\26\3\26\5\26\u0109\n\26\3\26\3\26\5\26\u010d\n\26\3"+
		"\26\5\26\u0110\n\26\3\26\3\26\3\27\5\27\u0115\n\27\3\27\3\27\3\27\3\27"+
		"\5\27\u011b\n\27\3\27\3\27\5\27\u011f\n\27\3\27\3\27\5\27\u0123\n\27\3"+
		"\27\5\27\u0126\n\27\3\27\3\27\3\30\5\30\u012b\n\30\3\30\3\30\3\30\3\30"+
		"\5\30\u0131\n\30\3\30\3\30\5\30\u0135\n\30\3\30\3\30\5\30\u0139\n\30\3"+
		"\30\5\30\u013c\n\30\3\30\3\30\3\31\5\31\u0141\n\31\3\31\3\31\3\31\3\31"+
		"\5\31\u0147\n\31\3\31\3\31\5\31\u014b\n\31\3\31\3\31\5\31\u014f\n\31\3"+
		"\31\5\31\u0152\n\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u015a\n\32\f\32"+
		"\16\32\u015d\13\32\3\33\3\33\3\33\3\33\5\33\u0163\n\33\3\33\3\33\3\33"+
		"\3\33\3\33\5\33\u016a\n\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\36\6\36\u0177\n\36\r\36\16\36\u0178\3\36\2\37\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\6\5\2\13\13\20\20\23\23"+
		"\4\2\20\20\23\23\4\2\13\13\17\23\6\2\5\6\b\b\13\13\17\23\u0196\2<\3\2"+
		"\2\2\4A\3\2\2\2\6C\3\2\2\2\bG\3\2\2\2\nK\3\2\2\2\fO\3\2\2\2\16Q\3\2\2"+
		"\2\20V\3\2\2\2\22[\3\2\2\2\24]\3\2\2\2\26c\3\2\2\2\30h\3\2\2\2\32|\3\2"+
		"\2\2\34\u0084\3\2\2\2\36\u008b\3\2\2\2 \u00a1\3\2\2\2\"\u00b6\3\2\2\2"+
		"$\u00c6\3\2\2\2&\u00de\3\2\2\2(\u00f4\3\2\2\2*\u00fe\3\2\2\2,\u0114\3"+
		"\2\2\2.\u012a\3\2\2\2\60\u0140\3\2\2\2\62\u015b\3\2\2\2\64\u015e\3\2\2"+
		"\2\66\u016d\3\2\2\28\u0171\3\2\2\2:\u0176\3\2\2\2<=\5\4\3\2=?\5\6\4\2"+
		">@\5\b\5\2?>\3\2\2\2?@\3\2\2\2@\3\3\2\2\2AB\7\13\2\2B\5\3\2\2\2CD\b\4"+
		"\1\2DE\7\25\2\2EF\b\4\1\2F\7\3\2\2\2GH\b\5\1\2HI\7\26\2\2IJ\b\5\1\2J\t"+
		"\3\2\2\2KM\5\f\7\2LN\5\16\b\2ML\3\2\2\2MN\3\2\2\2N\13\3\2\2\2OP\t\2\2"+
		"\2P\r\3\2\2\2QR\t\3\2\2R\17\3\2\2\2SU\t\4\2\2TS\3\2\2\2UX\3\2\2\2VT\3"+
		"\2\2\2VW\3\2\2\2W\21\3\2\2\2XV\3\2\2\2Y\\\5\24\13\2Z\\\5\"\22\2[Y\3\2"+
		"\2\2[Z\3\2\2\2\\\23\3\2\2\2]^\5\2\2\2^_\5\26\f\2_`\5\20\t\2`a\7\27\2\2"+
		"ab\b\13\1\2b\25\3\2\2\2cd\b\f\1\2de\7\30\2\2ef\b\f\1\2fg\5\30\r\2g\27"+
		"\3\2\2\2hi\5\20\t\2ij\5\36\20\2jk\5\32\16\2k\31\3\2\2\2lm\b\16\1\2mn\7"+
		"\16\2\2nr\b\16\1\2oq\5\34\17\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2"+
		"su\3\2\2\2tr\3\2\2\2uv\b\16\1\2vw\5\20\t\2wx\b\16\1\2xz\5 \21\2y{\7\31"+
		"\2\2zy\3\2\2\2z{\3\2\2\2{}\3\2\2\2|l\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177"+
		"\3\2\2\2\177\33\3\2\2\2\u0080\u0081\7\31\2\2\u0081\u0085\b\17\1\2\u0082"+
		"\u0083\7\21\2\2\u0083\u0085\b\17\1\2\u0084\u0080\3\2\2\2\u0084\u0082\3"+
		"\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\b\17\1\2\u0087\u0088\7\16\2\2\u0088"+
		"\u0089\b\17\1\2\u0089\35\3\2\2\2\u008a\u008c\5\n\6\2\u008b\u008a\3\2\2"+
		"\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\20\1\2\u008e"+
		"\u008f\7\r\2\2\u008f\u0091\b\20\1\2\u0090\u0092\7\17\2\2\u0091\u0090\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u009c\b\20\1\2\u0094"+
		"\u0096\7\r\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u009d\b\20\1\2\u0098\u009a\7\f\2\2\u0099\u0098\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\b\20\1\2\u009c\u0095\3"+
		"\2\2\2\u009c\u0099\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\b\20\1\2\u009f"+
		"\37\3\2\2\2\u00a0\u00a2\5\n\6\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2"+
		"\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\21\1\2\u00a4\u00a5\7\r\2\2\u00a5"+
		"\u00a7\b\21\1\2\u00a6\u00a8\7\17\2\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b2\b\21\1\2\u00aa\u00ac\7\r\2\2\u00ab"+
		"\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b3\b\21"+
		"\1\2\u00ae\u00b0\7\f\2\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b3\b\21\1\2\u00b2\u00ab\3\2\2\2\u00b2\u00af\3"+
		"\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\b\21\1\2\u00b5!\3\2\2\2\u00b6\u00b9"+
		"\5\n\6\2\u00b7\u00ba\7\25\2\2\u00b8\u00ba\5.\30\2\u00b9\u00b7\3\2\2\2"+
		"\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\26\2\2\u00bc\u00bd"+
		"\b\22\1\2\u00bd\u00bf\5\20\t\2\u00be\u00c0\5$\23\2\u00bf\u00be\3\2\2\2"+
		"\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3"+
		"\3\2\2\2\u00c3\u00c4\7\27\2\2\u00c4\u00c5\b\22\1\2\u00c5#\3\2\2\2\u00c6"+
		"\u00c7\b\23\1\2\u00c7\u00c8\7\16\2\2\u00c8\u00cc\b\23\1\2\u00c9\u00cb"+
		"\5\34\17\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2"+
		"\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0"+
		"\b\23\1\2\u00d0\u00d1\5\20\t\2\u00d1\u00d2\b\23\1\2\u00d2\u00d4\5\60\31"+
		"\2\u00d3\u00d5\5\20\t\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d8\5,\27\2\u00d7\u00d9\5(\25\2\u00d8\u00d7\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\3\2\2\2\u00da\u00dc\7\31\2\2\u00db"+
		"\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc%\3\2\2\2\u00dd\u00df\5\n\6\2"+
		"\u00de\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1"+
		"\b\24\1\2\u00e1\u00e2\7\r\2\2\u00e2\u00e4\b\24\1\2\u00e3\u00e5\7\17\2"+
		"\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00ef"+
		"\b\24\1\2\u00e7\u00e9\7\r\2\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2"+
		"\u00e9\u00ea\3\2\2\2\u00ea\u00f0\b\24\1\2\u00eb\u00ed\7\f\2\2\u00ec\u00eb"+
		"\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\b\24\1\2"+
		"\u00ef\u00e8\3\2\2\2\u00ef\u00ec\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2"+
		"\b\24\1\2\u00f2\'\3\2\2\2\u00f3\u00f5\5\20\t\2\u00f4\u00f3\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\5&\24\2\u00f7\u00f8\b\25"+
		"\1\2\u00f8\u00f9\7\16\2\2\u00f9\u00fa\b\25\1\2\u00fa\u00fb\5\20\t\2\u00fb"+
		"\u00fc\5*\26\2\u00fc)\3\2\2\2\u00fd\u00ff\5\n\6\2\u00fe\u00fd\3\2\2\2"+
		"\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\b\26\1\2\u0101\u0102"+
		"\7\r\2\2\u0102\u0104\b\26\1\2\u0103\u0105\7\17\2\2\u0104\u0103\3\2\2\2"+
		"\u0104\u0105\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u010f\b\26\1\2\u0107\u0109"+
		"\7\r\2\2\u0108\u0107\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a"+
		"\u0110\b\26\1\2\u010b\u010d\7\f\2\2\u010c\u010b\3\2\2\2\u010c\u010d\3"+
		"\2\2\2\u010d\u010e\3\2\2\2\u010e\u0110\b\26\1\2\u010f\u0108\3\2\2\2\u010f"+
		"\u010c\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\b\26\1\2\u0112+\3\2\2\2"+
		"\u0113\u0115\5\n\6\2\u0114\u0113\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116"+
		"\3\2\2\2\u0116\u0117\b\27\1\2\u0117\u0118\7\r\2\2\u0118\u011a\b\27\1\2"+
		"\u0119\u011b\7\17\2\2\u011a\u0119\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u0125\b\27\1\2\u011d\u011f\7\r\2\2\u011e\u011d\3\2\2\2"+
		"\u011e\u011f\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0126\b\27\1\2\u0121\u0123"+
		"\7\f\2\2\u0122\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\u0126\b\27\1\2\u0125\u011e\3\2\2\2\u0125\u0122\3\2\2\2\u0126\u0127\3"+
		"\2\2\2\u0127\u0128\b\27\1\2\u0128-\3\2\2\2\u0129\u012b\5\n\6\2\u012a\u0129"+
		"\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\b\30\1\2"+
		"\u012d\u012e\7\r\2\2\u012e\u0130\b\30\1\2\u012f\u0131\7\17\2\2\u0130\u012f"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u013b\b\30\1\2"+
		"\u0133\u0135\7\r\2\2\u0134\u0133\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136"+
		"\3\2\2\2\u0136\u013c\b\30\1\2\u0137\u0139\7\f\2\2\u0138\u0137\3\2\2\2"+
		"\u0138\u0139\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\b\30\1\2\u013b\u0134"+
		"\3\2\2\2\u013b\u0138\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\b\30\1\2"+
		"\u013e/\3\2\2\2\u013f\u0141\5\n\6\2\u0140\u013f\3\2\2\2\u0140\u0141\3"+
		"\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\b\31\1\2\u0143\u0144\7\r\2\2\u0144"+
		"\u0146\b\31\1\2\u0145\u0147\7\17\2\2\u0146\u0145\3\2\2\2\u0146\u0147\3"+
		"\2\2\2\u0147\u0148\3\2\2\2\u0148\u0151\b\31\1\2\u0149\u014b\7\r\2\2\u014a"+
		"\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u0152\b\31"+
		"\1\2\u014d\u014f\7\f\2\2\u014e\u014d\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0152\b\31\1\2\u0151\u014a\3\2\2\2\u0151\u014e\3"+
		"\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\b\31\1\2\u0154\61\3\2\2\2\u0155"+
		"\u015a\5\64\33\2\u0156\u015a\5\66\34\2\u0157\u015a\58\35\2\u0158\u015a"+
		"\5:\36\2\u0159\u0155\3\2\2\2\u0159\u0156\3\2\2\2\u0159\u0157\3\2\2\2\u0159"+
		"\u0158\3\2\2\2\u015a\u015d\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2"+
		"\2\2\u015c\63\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u015f\b\33\1\2\u015f\u0160"+
		"\7\r\2\2\u0160\u0162\b\33\1\2\u0161\u0163\7\17\2\2\u0162\u0161\3\2\2\2"+
		"\u0162\u0163\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0169\b\33\1\2\u0165\u0166"+
		"\7\f\2\2\u0166\u016a\b\33\1\2\u0167\u0168\7\r\2\2\u0168\u016a\b\33\1\2"+
		"\u0169\u0165\3\2\2\2\u0169\u0167\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c"+
		"\b\33\1\2\u016c\65\3\2\2\2\u016d\u016e\b\34\1\2\u016e\u016f\7\r\2\2\u016f"+
		"\u0170\b\34\1\2\u0170\67\3\2\2\2\u0171\u0172\b\35\1\2\u0172\u0173\7\16"+
		"\2\2\u0173\u0174\b\35\1\2\u01749\3\2\2\2\u0175\u0177\t\5\2\2\u0176\u0175"+
		"\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179"+
		";\3\2\2\29?MV[rz~\u0084\u008b\u0091\u0095\u0099\u009c\u00a1\u00a7\u00ab"+
		"\u00af\u00b2\u00b9\u00c1\u00cc\u00d4\u00d8\u00db\u00de\u00e4\u00e8\u00ec"+
		"\u00ef\u00f4\u00fe\u0104\u0108\u010c\u010f\u0114\u011a\u011e\u0122\u0125"+
		"\u012a\u0130\u0134\u0138\u013b\u0140\u0146\u014a\u014e\u0151\u0159\u015b"+
		"\u0162\u0169\u0178";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}