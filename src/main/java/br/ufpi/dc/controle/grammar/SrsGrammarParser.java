// Generated from D:\mestrado\netbeans\SRSEDITORMCertidoes\src\grammar\SrsGrammar.g4 by ANTLR 4.1

package br.ufpi.dc.controle.grammar;
         
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
public class SrsGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, PALAVRA=2, NUMERO=3, SIMBOLOS=4, TRACO=5, TERMINAL=6, VIRGULA=7, 
		MODELO=8, ART=9, ADJ=10, SUBS=11, VERB=12, PREP=13, PRON=14, CONJ=15, 
		ADV=16, NUM=17, PALAVRAESTRANGEIRA=18, SISTEMA=19, DEVE=20, SEPARADOR=21, 
		PONTO=22, PERMITIR=23;
	public static final String[] tokenNames = {
		"<INVALID>", "WS", "PALAVRA", "NUMERO", "SIMBOLOS", "'-'", "TERMINAL", 
		"','", "MODELO", "ART", "ADJ", "SUBS", "VERB", "PREP", "PRON", "CONJ", 
		"ADV", "NUM", "PALAVRAESTRANGEIRA", "SISTEMA", "DEVE", "SEPARADOR", "PONTO", 
		"PERMITIR"
	};
	public static final int
		RULE_atributos = 0, RULE_atributo = 1, RULE_outros_atributos = 2, RULE_conceitos = 3, 
		RULE_conceito = 4, RULE_funcionalidades = 5, RULE_funcionalidade = 6, 
		RULE_outros = 7, RULE_init = 8, RULE_osistemadeve = 9, RULE_o = 10, RULE_sist = 11, 
		RULE_deve = 12, RULE_det = 13, RULE_det_base = 14, RULE_pos_det = 15, 
		RULE_palavras = 16, RULE_qualquer_coisa = 17, RULE_inicio = 18, RULE_p1 = 19, 
		RULE_sv = 20, RULE_f1 = 21, RULE_iteracao_f1 = 22, RULE_iteracao_verbo = 23, 
		RULE_sn_usuario_f1 = 24, RULE_sn_conceito_classe_f1 = 25, RULE_f3 = 26, 
		RULE_iteracao_f3 = 27, RULE_usuario_receptor = 28, RULE_exp_temporal = 29, 
		RULE_sn_conceito_classe_f5 = 30, RULE_sn_usuario_f4 = 31, RULE_sn_usuario_f3 = 32, 
		RULE_sn_conceito_classe_f3 = 33, RULE_sub1 = 34, RULE_com_ou_sem_prep = 35, 
		RULE_com_prop = 36, RULE_sem_prop = 37, RULE_adjetivo = 38, RULE_conceitos_1 = 39, 
		RULE_conceito_1 = 40, RULE_substantivo = 41, RULE_verbo = 42, RULE_outros_1 = 43;
	public static final String[] ruleNames = {
		"atributos", "atributo", "outros_atributos", "conceitos", "conceito", 
		"funcionalidades", "funcionalidade", "outros", "init", "osistemadeve", 
		"o", "sist", "deve", "det", "det_base", "pos_det", "palavras", "qualquer_coisa", 
		"inicio", "p1", "sv", "f1", "iteracao_f1", "iteracao_verbo", "sn_usuario_f1", 
		"sn_conceito_classe_f1", "f3", "iteracao_f3", "usuario_receptor", "exp_temporal", 
		"sn_conceito_classe_f5", "sn_usuario_f4", "sn_usuario_f3", "sn_conceito_classe_f3", 
		"sub1", "com_ou_sem_prep", "com_prop", "sem_prop", "adjetivo", "conceitos_1", 
		"conceito_1", "substantivo", "verbo", "outros_1"
	};

	@Override
	public String getGrammarFileName() { return "SrsGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	    public ElementosFrase elementosDaFrase     = new ElementosFrase(); 
	    public ArrayList<String> oracoes    = new ArrayList<String>(); 
	    public ArrayList<String> conceitos  = new ArrayList<String>(); 
	    
	    public ArrayList<Integer> listTokenDoConceito   = new ArrayList<Integer>();
	    public ArrayList<String> listConceitoTokenizado = new ArrayList<String>();
	   
	    public HashMap<Integer,ArrayList<String>> conceitosTipados = new HashMap<Integer,ArrayList<String>>();
	    public HashMap<String, ArrayList<Integer>> podeSer = new HashMap();
	   
	    private boolean ok                = true;
	    public  int     tipoFrase         = 0;
	    public  int     indicePQqq       = -1;
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
	    public int    iteracaoVerbo     =  1;
	    public String verboVerbo        = "";
	    public String deveVerbo         = "";
	    private String PALAVRAESPECIAL  = "!@#$%&";
	    
	    
	private void addConceitoAoElementoDaFrase(String tipo){
	                               
	       if (!conceito.equals("") && (ok) && !conceito.contains("<missing")){
	         elementosDaFrase.tipoElemento.add(tipo);
	         elementosDaFrase.elemento.add(conceito);
	       }    
	       ok = true;
	       conceito = "";
	    }

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
	//       conceito = "";
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
		      if (palavra.toUpperCase().equals(palavra) || (ok) && !palavra.contains("<missing")){
	                 addPalavraAoConceito(palavra);
	                 ultimaPalavra = palavra;
	            }else if (!palavra.equals(PALAVRAESPECIAL)){ 
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
	       
	       ArrayList<Integer> tipoQuePodeSer = podeSer.get(conceito);
	       if (tipoQuePodeSer== null){
	           tipoQuePodeSer = new ArrayList<Integer>();
	       }
	       
	       if (!tipoQuePodeSer.contains(tipoConceito)){
	             tipoQuePodeSer.add(tipoConceito);                                        
	       }
	       podeSer.put(conceito,tipoQuePodeSer);
	    }
	   
	   public String adicionarVerbos(String v1, String v2, String s){
	          if (!v1.contains("<missing") && !v2.contains("<missing")){
	                if (!s.equals("")){
	                    return s + "#" + v1 + " " +v2;
	                }else{
	                    return v1 + " " +v2;  
	                }
	              }
	          return s;
	          }   

	public SrsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AtributosContext extends ParserRuleContext {
		public Outros_atributosContext outros_atributos(int i) {
			return getRuleContext(Outros_atributosContext.class,i);
		}
		public AtributoContext atributo(int i) {
			return getRuleContext(AtributoContext.class,i);
		}
		public List<AtributoContext> atributo() {
			return getRuleContexts(AtributoContext.class);
		}
		public List<Outros_atributosContext> outros_atributos() {
			return getRuleContexts(Outros_atributosContext.class);
		}
		public AtributosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterAtributos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitAtributos(this);
		}
	}

	public final AtributosContext atributos() throws RecognitionException {
		AtributosContext _localctx = new AtributosContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_atributos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(90);
				switch (_input.LA(1)) {
				case SUBS:
					{
					setState(88); atributo();
					}
					break;
				case NUMERO:
				case SIMBOLOS:
				case TERMINAL:
				case ART:
				case ADJ:
				case VERB:
				case PREP:
				case PRON:
				case CONJ:
				case ADV:
				case NUM:
					{
					setState(89); outros_atributos();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(94);
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

	public static class AtributoContext extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token ADJ;
		public Token s2;
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public AtributoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterAtributo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitAtributo(this);
		}
	}

	public final AtributoContext atributo() throws RecognitionException {
		AtributoContext _localctx = new AtributoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_atributo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(96); ((AtributoContext)_localctx).s1 = match(SUBS);
			testaConceito((((AtributoContext)_localctx).s1!=null?((AtributoContext)_localctx).s1.getText():null));
			setState(99);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(98); ((AtributoContext)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((AtributoContext)_localctx).PREP!=null?((AtributoContext)_localctx).PREP.getText():null));
			setState(106);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(102); ((AtributoContext)_localctx).ADJ = match(ADJ);
				addPreposicao((((AtributoContext)_localctx).ADJ!=null?((AtributoContext)_localctx).ADJ.getText():null));
				}
				break;

			case 2:
				{
				setState(104); ((AtributoContext)_localctx).s2 = match(SUBS);

				                                        addPreposicao((((AtributoContext)_localctx).s2!=null?((AtributoContext)_localctx).s2.getText():null));
				                                         
				                                       
				}
				break;
			}
			addConceito();
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

	public static class Outros_atributosContext extends ParserRuleContext {
		public Token ADJ;
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode PRON() { return getToken(SrsGrammarParser.PRON, 0); }
		public TerminalNode SIMBOLOS() { return getToken(SrsGrammarParser.SIMBOLOS, 0); }
		public TerminalNode NUM() { return getToken(SrsGrammarParser.NUM, 0); }
		public TerminalNode NUMERO() { return getToken(SrsGrammarParser.NUMERO, 0); }
		public TerminalNode ADV() { return getToken(SrsGrammarParser.ADV, 0); }
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public TerminalNode TERMINAL() { return getToken(SrsGrammarParser.TERMINAL, 0); }
		public TerminalNode CONJ() { return getToken(SrsGrammarParser.CONJ, 0); }
		public TerminalNode ART() { return getToken(SrsGrammarParser.ART, 0); }
		public TerminalNode VERB() { return getToken(SrsGrammarParser.VERB, 0); }
		public Outros_atributosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_atributos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterOutros_atributos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitOutros_atributos(this);
		}
	}

	public final Outros_atributosContext outros_atributos() throws RecognitionException {
		Outros_atributosContext _localctx = new Outros_atributosContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_outros_atributos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			switch (_input.LA(1)) {
			case ART:
				{
				setState(110); match(ART);
				}
				break;
			case PRON:
				{
				setState(111); match(PRON);
				}
				break;
			case CONJ:
				{
				setState(112); match(CONJ);
				}
				break;
			case ADJ:
				{
				 conceito = "";
				setState(114); ((Outros_atributosContext)_localctx).ADJ = match(ADJ);
				testaConceito((((Outros_atributosContext)_localctx).ADJ!=null?((Outros_atributosContext)_localctx).ADJ.getText():null));
				addConceito();
				}
				break;
			case NUM:
				{
				setState(117); match(NUM);
				}
				break;
			case TERMINAL:
				{
				setState(118); match(TERMINAL);
				}
				break;
			case PREP:
				{
				setState(119); match(PREP);
				}
				break;
			case SIMBOLOS:
				{
				setState(120); match(SIMBOLOS);
				}
				break;
			case NUMERO:
				{
				setState(121); match(NUMERO);
				}
				break;
			case VERB:
				{
				setState(122); match(VERB);
				}
				break;
			case ADV:
				{
				setState(123); match(ADV);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ConceitosContext extends ParserRuleContext {
		public List<OutrosContext> outros() {
			return getRuleContexts(OutrosContext.class);
		}
		public List<ConceitoContext> conceito() {
			return getRuleContexts(ConceitoContext.class);
		}
		public ConceitoContext conceito(int i) {
			return getRuleContext(ConceitoContext.class,i);
		}
		public OutrosContext outros(int i) {
			return getRuleContext(OutrosContext.class,i);
		}
		public ConceitosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceitos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterConceitos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitConceitos(this);
		}
	}

	public final ConceitosContext conceitos() throws RecognitionException {
		ConceitosContext _localctx = new ConceitosContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_conceitos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(128);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(126); conceito();
					}
					break;

				case 2:
					{
					setState(127); outros();
					}
					break;
				}
				}
				setState(132);
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

	public static class ConceitoContext extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token ADJ;
		public Token s2;
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public ConceitoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceito; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterConceito(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitConceito(this);
		}
	}

	public final ConceitoContext conceito() throws RecognitionException {
		ConceitoContext _localctx = new ConceitoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conceito);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(134); ((ConceitoContext)_localctx).s1 = match(SUBS);
			testaConceito((((ConceitoContext)_localctx).s1!=null?((ConceitoContext)_localctx).s1.getText():null));
			setState(137);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(136); ((ConceitoContext)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((ConceitoContext)_localctx).PREP!=null?((ConceitoContext)_localctx).PREP.getText():null));
			setState(144);
			switch (_input.LA(1)) {
			case ADJ:
				{
				setState(140); ((ConceitoContext)_localctx).ADJ = match(ADJ);
				testaConceito((((ConceitoContext)_localctx).ADJ!=null?((ConceitoContext)_localctx).ADJ.getText():null));
				}
				break;
			case SUBS:
				{
				setState(142); ((ConceitoContext)_localctx).s2 = match(SUBS);

				                                        testaConceito((((ConceitoContext)_localctx).s2!=null?((ConceitoContext)_localctx).s2.getText():null));
				                                         
				                                       
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			addConceito();
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

	public static class FuncionalidadesContext extends ParserRuleContext {
		public List<OutrosContext> outros() {
			return getRuleContexts(OutrosContext.class);
		}
		public FuncionalidadeContext funcionalidade(int i) {
			return getRuleContext(FuncionalidadeContext.class,i);
		}
		public OutrosContext outros(int i) {
			return getRuleContext(OutrosContext.class,i);
		}
		public List<FuncionalidadeContext> funcionalidade() {
			return getRuleContexts(FuncionalidadeContext.class);
		}
		public FuncionalidadesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcionalidades; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterFuncionalidades(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitFuncionalidades(this);
		}
	}

	public final FuncionalidadesContext funcionalidades() throws RecognitionException {
		FuncionalidadesContext _localctx = new FuncionalidadesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcionalidades);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(150);
				switch (_input.LA(1)) {
				case VERB:
					{
					setState(148); funcionalidade();
					}
					break;
				case NUMERO:
				case SIMBOLOS:
				case TERMINAL:
				case ART:
				case ADJ:
				case SUBS:
				case PREP:
				case PRON:
				case CONJ:
				case ADV:
				case NUM:
					{
					setState(149); outros();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(154);
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

	public static class FuncionalidadeContext extends ParserRuleContext {
		public Token VERB;
		public Token s1;
		public Token PREP;
		public Token ADJ;
		public Token s2;
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public TerminalNode VERB() { return getToken(SrsGrammarParser.VERB, 0); }
		public FuncionalidadeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcionalidade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterFuncionalidade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitFuncionalidade(this);
		}
	}

	public final FuncionalidadeContext funcionalidade() throws RecognitionException {
		FuncionalidadeContext _localctx = new FuncionalidadeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcionalidade);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(156); ((FuncionalidadeContext)_localctx).VERB = match(VERB);
			testaConceito((((FuncionalidadeContext)_localctx).VERB!=null?((FuncionalidadeContext)_localctx).VERB.getText():null));
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(158); palavras();
				}
				break;
			}
			setState(161); ((FuncionalidadeContext)_localctx).s1 = match(SUBS);
			testaConceito((((FuncionalidadeContext)_localctx).s1!=null?((FuncionalidadeContext)_localctx).s1.getText():null));
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(163); ((FuncionalidadeContext)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((FuncionalidadeContext)_localctx).PREP!=null?((FuncionalidadeContext)_localctx).PREP.getText():null));
			setState(171);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(167); ((FuncionalidadeContext)_localctx).ADJ = match(ADJ);
				testaConceito((((FuncionalidadeContext)_localctx).ADJ!=null?((FuncionalidadeContext)_localctx).ADJ.getText():null));
				}
				break;

			case 2:
				{
				setState(169); ((FuncionalidadeContext)_localctx).s2 = match(SUBS);

				                                        testaConceito((((FuncionalidadeContext)_localctx).s2!=null?((FuncionalidadeContext)_localctx).s2.getText():null));
				                                       
				}
				break;
			}

			                                  addConceito();
			                                 
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

	public static class OutrosContext extends ParserRuleContext {
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode PRON() { return getToken(SrsGrammarParser.PRON, 0); }
		public TerminalNode SIMBOLOS() { return getToken(SrsGrammarParser.SIMBOLOS, 0); }
		public TerminalNode ADV() { return getToken(SrsGrammarParser.ADV, 0); }
		public TerminalNode NUM() { return getToken(SrsGrammarParser.NUM, 0); }
		public TerminalNode NUMERO() { return getToken(SrsGrammarParser.NUMERO, 0); }
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public TerminalNode SUBS() { return getToken(SrsGrammarParser.SUBS, 0); }
		public TerminalNode TERMINAL() { return getToken(SrsGrammarParser.TERMINAL, 0); }
		public TerminalNode CONJ() { return getToken(SrsGrammarParser.CONJ, 0); }
		public TerminalNode ART() { return getToken(SrsGrammarParser.ART, 0); }
		public OutrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterOutros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitOutros(this);
		}
	}

	public final OutrosContext outros() throws RecognitionException {
		OutrosContext _localctx = new OutrosContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_outros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) ) {
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

	public static class InitContext extends ParserRuleContext {
		public Token PALAVRA;
		public Token TERMINAL;
		public Token VIRGULA;
		public TerminalNode PALAVRA(int i) {
			return getToken(SrsGrammarParser.PALAVRA, i);
		}
		public TerminalNode VIRGULA(int i) {
			return getToken(SrsGrammarParser.VIRGULA, i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(SrsGrammarParser.TERMINAL); }
		public List<TerminalNode> VIRGULA() { return getTokens(SrsGrammarParser.VIRGULA); }
		public List<TerminalNode> PALAVRA() { return getTokens(SrsGrammarParser.PALAVRA); }
		public TerminalNode TERMINAL(int i) {
			return getToken(SrsGrammarParser.TERMINAL, i);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitInit(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PALAVRA) | (1L << TERMINAL) | (1L << VIRGULA))) != 0)) {
				{
				setState(183);
				switch (_input.LA(1)) {
				case PALAVRA:
					{
					setState(177); ((InitContext)_localctx).PALAVRA = match(PALAVRA);
					addPalavra((((InitContext)_localctx).PALAVRA!=null?((InitContext)_localctx).PALAVRA.getText():null));
					}
					break;
				case TERMINAL:
					{
					setState(179); ((InitContext)_localctx).TERMINAL = match(TERMINAL);
					 
					                    addPalavra((((InitContext)_localctx).TERMINAL!=null?((InitContext)_localctx).TERMINAL.getText():null));
					                    oracoes.add(oracao+"\n");
					                    oracao = "";
					                    
					}
					break;
				case VIRGULA:
					{
					setState(181); ((InitContext)_localctx).VIRGULA = match(VIRGULA);
					addPalavra((((InitContext)_localctx).VIRGULA!=null?((InitContext)_localctx).VIRGULA.getText():null));
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(187);
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
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterOsistemadeve(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitOsistemadeve(this);
		}
	}

	public final OsistemadeveContext osistemadeve() throws RecognitionException {
		OsistemadeveContext _localctx = new OsistemadeveContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_osistemadeve);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); o();
			setState(189); sist();
			setState(191);
			_la = _input.LA(1);
			if (_la==DEVE) {
				{
				setState(190); deve();
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
		public TerminalNode ART() { return getToken(SrsGrammarParser.ART, 0); }
		public OContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_o; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterO(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitO(this);
		}
	}

	public final OContext o() throws RecognitionException {
		OContext _localctx = new OContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_o);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); match(ART);
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
		public TerminalNode SISTEMA() { return getToken(SrsGrammarParser.SISTEMA, 0); }
		public SistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSist(this);
		}
	}

	public final SistContext sist() throws RecognitionException {
		SistContext _localctx = new SistContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sist);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; conceitoTokenizado="";
			setState(196); ((SistContext)_localctx).SISTEMA = match(SISTEMA);
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
		public TerminalNode DEVE() { return getToken(SrsGrammarParser.DEVE, 0); }
		public DeveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deve; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterDeve(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitDeve(this);
		}
	}

	public final DeveContext deve() throws RecognitionException {
		DeveContext _localctx = new DeveContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_deve);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; conceitoTokenizado="";
			setState(200); ((DeveContext)_localctx).DEVE = match(DEVE);
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
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterDet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitDet(this);
		}
	}

	public final DetContext det() throws RecognitionException {
		DetContext _localctx = new DetContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_det);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); det_base();
			setState(205);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(204); pos_det();
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
		public TerminalNode PRON() { return getToken(SrsGrammarParser.PRON, 0); }
		public TerminalNode NUM() { return getToken(SrsGrammarParser.NUM, 0); }
		public TerminalNode ART() { return getToken(SrsGrammarParser.ART, 0); }
		public Det_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_det_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterDet_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitDet_base(this);
		}
	}

	public final Det_baseContext det_base() throws RecognitionException {
		Det_baseContext _localctx = new Det_baseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_det_base);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
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
		public TerminalNode PRON() { return getToken(SrsGrammarParser.PRON, 0); }
		public TerminalNode NUM() { return getToken(SrsGrammarParser.NUM, 0); }
		public Pos_detContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos_det; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterPos_det(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitPos_det(this);
		}
	}

	public final Pos_detContext pos_det() throws RecognitionException {
		Pos_detContext _localctx = new Pos_detContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pos_det);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
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
		public List<TerminalNode> PREP() { return getTokens(SrsGrammarParser.PREP); }
		public TerminalNode ADV(int i) {
			return getToken(SrsGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(SrsGrammarParser.PRON); }
		public TerminalNode ART(int i) {
			return getToken(SrsGrammarParser.ART, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(SrsGrammarParser.PREP, i);
		}
		public List<TerminalNode> ADV() { return getTokens(SrsGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(SrsGrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(SrsGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(SrsGrammarParser.PRON, i);
		}
		public List<TerminalNode> CONJ() { return getTokens(SrsGrammarParser.CONJ); }
		public List<TerminalNode> ART() { return getTokens(SrsGrammarParser.ART); }
		public TerminalNode CONJ(int i) {
			return getToken(SrsGrammarParser.CONJ, i);
		}
		public PalavrasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_palavras; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterPalavras(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitPalavras(this);
		}
	}

	public final PalavrasContext palavras() throws RecognitionException {
		PalavrasContext _localctx = new PalavrasContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_palavras);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(211);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class Qualquer_coisaContext extends ParserRuleContext {
		public Token ART;
		public Token PREP;
		public Token PRON;
		public Token CONJ;
		public Token ADV;
		public Token NUM;
		public Token SUBS;
		public Token ADJ;
		public Token VERB;
		public Token TERMINAL;
		public Token SIMBOLOS;
		public Token VIRGULA;
		public Token SEPARADOR;
		public Token DEVE;
		public List<TerminalNode> SIMBOLOS() { return getTokens(SrsGrammarParser.SIMBOLOS); }
		public List<TerminalNode> ADV() { return getTokens(SrsGrammarParser.ADV); }
		public TerminalNode VIRGULA(int i) {
			return getToken(SrsGrammarParser.VIRGULA, i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(SrsGrammarParser.VIRGULA); }
		public TerminalNode SIMBOLOS(int i) {
			return getToken(SrsGrammarParser.SIMBOLOS, i);
		}
		public TerminalNode ADJ(int i) {
			return getToken(SrsGrammarParser.ADJ, i);
		}
		public List<TerminalNode> PREP() { return getTokens(SrsGrammarParser.PREP); }
		public TerminalNode PREP(int i) {
			return getToken(SrsGrammarParser.PREP, i);
		}
		public List<TerminalNode> ADJ() { return getTokens(SrsGrammarParser.ADJ); }
		public List<TerminalNode> DEVE() { return getTokens(SrsGrammarParser.DEVE); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public List<TerminalNode> ART() { return getTokens(SrsGrammarParser.ART); }
		public TerminalNode CONJ(int i) {
			return getToken(SrsGrammarParser.CONJ, i);
		}
		public TerminalNode ADV(int i) {
			return getToken(SrsGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(SrsGrammarParser.PRON); }
		public List<TerminalNode> NUM() { return getTokens(SrsGrammarParser.NUM); }
		public List<TerminalNode> SEPARADOR() { return getTokens(SrsGrammarParser.SEPARADOR); }
		public TerminalNode DEVE(int i) {
			return getToken(SrsGrammarParser.DEVE, i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(SrsGrammarParser.TERMINAL); }
		public List<TerminalNode> CONJ() { return getTokens(SrsGrammarParser.CONJ); }
		public TerminalNode TERMINAL(int i) {
			return getToken(SrsGrammarParser.TERMINAL, i);
		}
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode SEPARADOR(int i) {
			return getToken(SrsGrammarParser.SEPARADOR, i);
		}
		public TerminalNode VERB(int i) {
			return getToken(SrsGrammarParser.VERB, i);
		}
		public TerminalNode ART(int i) {
			return getToken(SrsGrammarParser.ART, i);
		}
		public TerminalNode NUM(int i) {
			return getToken(SrsGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(SrsGrammarParser.PRON, i);
		}
		public List<TerminalNode> VERB() { return getTokens(SrsGrammarParser.VERB); }
		public Qualquer_coisaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualquer_coisa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterQualquer_coisa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitQualquer_coisa(this);
		}
	}

	public final Qualquer_coisaContext qualquer_coisa() throws RecognitionException {
		Qualquer_coisaContext _localctx = new Qualquer_coisaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_qualquer_coisa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SIMBOLOS) | (1L << TERMINAL) | (1L << VIRGULA) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM) | (1L << DEVE) | (1L << SEPARADOR))) != 0)) {
				{
				setState(245);
				switch (_input.LA(1)) {
				case ART:
					{
					setState(217); ((Qualquer_coisaContext)_localctx).ART = match(ART);
					temp = (((Qualquer_coisaContext)_localctx).ART!=null?((Qualquer_coisaContext)_localctx).ART.getText():null);   System.out.println("Ultimo temp:  ART " + temp); testaConceito(temp);
					}
					break;
				case PREP:
					{
					setState(219); ((Qualquer_coisaContext)_localctx).PREP = match(PREP);
					temp = (((Qualquer_coisaContext)_localctx).PREP!=null?((Qualquer_coisaContext)_localctx).PREP.getText():null); System.out.println("Ultimo temp:  PREP " + temp); addPreposicao(temp);
					}
					break;
				case PRON:
					{
					setState(221); ((Qualquer_coisaContext)_localctx).PRON = match(PRON);
					temp = (((Qualquer_coisaContext)_localctx).PRON!=null?((Qualquer_coisaContext)_localctx).PRON.getText():null); System.out.println("Ultimo temp:  PRON " + temp); testaConceito(temp);
					}
					break;
				case CONJ:
					{
					setState(223); ((Qualquer_coisaContext)_localctx).CONJ = match(CONJ);
					temp = (((Qualquer_coisaContext)_localctx).CONJ!=null?((Qualquer_coisaContext)_localctx).CONJ.getText():null);System.out.println("Ultimo temp:  CONJ " + temp); testaConceito(temp);
					}
					break;
				case ADV:
					{
					setState(225); ((Qualquer_coisaContext)_localctx).ADV = match(ADV);
					temp = (((Qualquer_coisaContext)_localctx).ADV!=null?((Qualquer_coisaContext)_localctx).ADV.getText():null);  System.out.println("Ultimo temp:  ADV " + temp); testaConceito(temp);
					}
					break;
				case NUM:
					{
					setState(227); ((Qualquer_coisaContext)_localctx).NUM = match(NUM);
					temp = (((Qualquer_coisaContext)_localctx).NUM!=null?((Qualquer_coisaContext)_localctx).NUM.getText():null);  System.out.println("Ultimo temp:  NUM " + temp); testaConceito(temp);
					}
					break;
				case SUBS:
					{
					setState(229); ((Qualquer_coisaContext)_localctx).SUBS = match(SUBS);
					temp = (((Qualquer_coisaContext)_localctx).SUBS!=null?((Qualquer_coisaContext)_localctx).SUBS.getText():null); System.out.println("Ultimo temp:  SUBS " + temp); testaConceito(temp);
					}
					break;
				case ADJ:
					{
					setState(231); ((Qualquer_coisaContext)_localctx).ADJ = match(ADJ);
					temp = (((Qualquer_coisaContext)_localctx).ADJ!=null?((Qualquer_coisaContext)_localctx).ADJ.getText():null);   System.out.println("Ultimo temp:  ADJ " + temp); testaConceito(temp);
					}
					break;
				case VERB:
					{
					setState(233); ((Qualquer_coisaContext)_localctx).VERB = match(VERB);
					temp = (((Qualquer_coisaContext)_localctx).VERB!=null?((Qualquer_coisaContext)_localctx).VERB.getText():null); System.out.println("Ultimo temp:  VERB " + temp); testaConceito(temp);
					}
					break;
				case TERMINAL:
					{
					setState(235); ((Qualquer_coisaContext)_localctx).TERMINAL = match(TERMINAL);
					temp = (((Qualquer_coisaContext)_localctx).TERMINAL!=null?((Qualquer_coisaContext)_localctx).TERMINAL.getText():null); System.out.println("Ultimo temp:  TERMINAL " + temp); testaConceito(temp);
					}
					break;
				case SIMBOLOS:
					{
					setState(237); ((Qualquer_coisaContext)_localctx).SIMBOLOS = match(SIMBOLOS);
					temp = (((Qualquer_coisaContext)_localctx).SIMBOLOS!=null?((Qualquer_coisaContext)_localctx).SIMBOLOS.getText():null); System.out.println("Ultimo temp:  SIMBOLOS " + temp); testaConceito(temp);
					}
					break;
				case VIRGULA:
					{
					setState(239); ((Qualquer_coisaContext)_localctx).VIRGULA = match(VIRGULA);
					temp = (((Qualquer_coisaContext)_localctx).VIRGULA!=null?((Qualquer_coisaContext)_localctx).VIRGULA.getText():null);   System.out.println("Ultimo temp:  VIRGULA " + temp); testaConceito(temp);
					}
					break;
				case SEPARADOR:
					{
					setState(241); ((Qualquer_coisaContext)_localctx).SEPARADOR = match(SEPARADOR);
					temp = (((Qualquer_coisaContext)_localctx).SEPARADOR!=null?((Qualquer_coisaContext)_localctx).SEPARADOR.getText():null); System.out.println("Ultimo temp:  SEPARADOR " + temp); testaConceito(temp);
					}
					break;
				case DEVE:
					{
					setState(243); ((Qualquer_coisaContext)_localctx).DEVE = match(DEVE);
					temp = (((Qualquer_coisaContext)_localctx).DEVE!=null?((Qualquer_coisaContext)_localctx).DEVE.getText():null); System.out.println("Ultimo temp:  DEVE " + temp); testaConceito(temp);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			                    adicionaConceitoTipado(Constante.QQQ);
			                  
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
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterInicio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitInicio(this);
		}
	}

	public final InicioContext inicio() throws RecognitionException {
		InicioContext _localctx = new InicioContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_inicio);
		try {
			setState(254);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(252); p1();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(253); f3();
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
		public Qualquer_coisaContext qualquer_coisa() {
			return getRuleContext(Qualquer_coisaContext.class,0);
		}
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(SrsGrammarParser.PONTO, 0); }
		public OsistemadeveContext osistemadeve() {
			return getRuleContext(OsistemadeveContext.class,0);
		}
		public P1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_p1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterP1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitP1(this);
		}
	}

	public final P1Context p1() throws RecognitionException {
		P1Context _localctx = new P1Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_p1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); osistemadeve();
			setState(257); sv();
			setState(258); palavras();
			setState(260);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(259); qualquer_coisa();
				}
				break;
			}
			setState(262); ((P1Context)_localctx).PONTO = match(PONTO);
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
		public TerminalNode PERMITIR() { return getToken(SrsGrammarParser.PERMITIR, 0); }
		public F1Context f1() {
			return getRuleContext(F1Context.class,0);
		}
		public SvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSv(this);
		}
	}

	public final SvContext sv() throws RecognitionException {
		SvContext _localctx = new SvContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_sv);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito =""; conceitoTokenizado="";
			setState(266); ((SvContext)_localctx).PERMITIR = match(PERMITIR);

			                                 setProximo((((SvContext)_localctx).PERMITIR!=null?((SvContext)_localctx).PERMITIR.getText():null),"2 usuario");
			                                 testaConceito((((SvContext)_localctx).PERMITIR!=null?((SvContext)_localctx).PERMITIR.getText():null));
			                                 adicionaConceitoTipado(Constante.PERMITIR);
			                                 tipoFrase = 1;
			                                 
			setState(268); f1();
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
		public List<Iteracao_f1Context> iteracao_f1() {
			return getRuleContexts(Iteracao_f1Context.class);
		}
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public Sn_usuario_f1Context sn_usuario_f1() {
			return getRuleContext(Sn_usuario_f1Context.class,0);
		}
		public Iteracao_f1Context iteracao_f1(int i) {
			return getRuleContext(Iteracao_f1Context.class,i);
		}
		public F1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterF1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitF1(this);
		}
	}

	public final F1Context f1() throws RecognitionException {
		F1Context _localctx = new F1Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_f1);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(270); palavras();
			setState(271); sn_usuario_f1();
			setState(275);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(272); iteracao_f1();
					}
					} 
				}
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class Iteracao_f1Context extends ParserRuleContext {
		public Token VERB;
		public Iteracao_verboContext iteracao_verbo(int i) {
			return getRuleContext(Iteracao_verboContext.class,i);
		}
		public List<Iteracao_verboContext> iteracao_verbo() {
			return getRuleContexts(Iteracao_verboContext.class);
		}
		public TerminalNode SEPARADOR(int i) {
			return getToken(SrsGrammarParser.SEPARADOR, i);
		}
		public TerminalNode VERB(int i) {
			return getToken(SrsGrammarParser.VERB, i);
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
		public List<TerminalNode> SEPARADOR() { return getTokens(SrsGrammarParser.SEPARADOR); }
		public List<TerminalNode> VERB() { return getTokens(SrsGrammarParser.VERB); }
		public Iteracao_f1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteracao_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterIteracao_f1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitIteracao_f1(this);
		}
	}

	public final Iteracao_f1Context iteracao_f1() throws RecognitionException {
		Iteracao_f1Context _localctx = new Iteracao_f1Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_iteracao_f1);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(292); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					conceito =""; conceitoTokenizado="";
					setState(279); ((Iteracao_f1Context)_localctx).VERB = match(VERB);
					setProximo((((Iteracao_f1Context)_localctx).VERB!=null?((Iteracao_f1Context)_localctx).VERB.getText():null),"3 classe");
					                              testaConceito((((Iteracao_f1Context)_localctx).VERB!=null?((Iteracao_f1Context)_localctx).VERB.getText():null));
					                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
					                              addConceitoAoElementoDaFrase("VERB"); //1
					                             
					{
					setState(284);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(281); iteracao_verbo();
							}
							} 
						}
						setState(286);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
					}
					setState(287); palavras();
					setState(288); sn_conceito_classe_f1();
					setState(290);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						setState(289); match(SEPARADOR);
						}
						break;
					}
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(294); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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

	public static class Iteracao_verboContext extends ParserRuleContext {
		public Token SEPARADOR;
		public Token CONJ;
		public Token VERB;
		public TerminalNode SEPARADOR() { return getToken(SrsGrammarParser.SEPARADOR, 0); }
		public TerminalNode CONJ() { return getToken(SrsGrammarParser.CONJ, 0); }
		public TerminalNode VERB() { return getToken(SrsGrammarParser.VERB, 0); }
		public Iteracao_verboContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteracao_verbo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterIteracao_verbo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitIteracao_verbo(this);
		}
	}

	public final Iteracao_verboContext iteracao_verbo() throws RecognitionException {
		Iteracao_verboContext _localctx = new Iteracao_verboContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_iteracao_verbo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			switch (_input.LA(1)) {
			case SEPARADOR:
				{
				setState(296); ((Iteracao_verboContext)_localctx).SEPARADOR = match(SEPARADOR);
				setProximo((((Iteracao_verboContext)_localctx).SEPARADOR!=null?((Iteracao_verboContext)_localctx).SEPARADOR.getText():null),"30 verbo");
				}
				break;
			case CONJ:
				{
				setState(298); ((Iteracao_verboContext)_localctx).CONJ = match(CONJ);
				setProximo((((Iteracao_verboContext)_localctx).CONJ!=null?((Iteracao_verboContext)_localctx).CONJ.getText():null),"31 verbo");
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			conceito =""; conceitoTokenizado="";
			setState(303); ((Iteracao_verboContext)_localctx).VERB = match(VERB);
			setProximo((((Iteracao_verboContext)_localctx).VERB!=null?((Iteracao_verboContext)_localctx).VERB.getText():null),"31 verbo");
			                              testaConceito((((Iteracao_verboContext)_localctx).VERB!=null?((Iteracao_verboContext)_localctx).VERB.getText():null));
			                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
			                              addConceitoAoElementoDaFrase("VERB_IT"); //2
			                              ++iteracaoVerbo;
			                             
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
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_usuario_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSn_usuario_f1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSn_usuario_f1(this);
		}
	}

	public final Sn_usuario_f1Context sn_usuario_f1() throws RecognitionException {
		Sn_usuario_f1Context _localctx = new Sn_usuario_f1Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_sn_usuario_f1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(306); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(310); ((Sn_usuario_f1Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_usuario_f1Context)_localctx).s1!=null?((Sn_usuario_f1Context)_localctx).s1.getText():null),"4 verbo");
			                             testaConceito((((Sn_usuario_f1Context)_localctx).s1!=null?((Sn_usuario_f1Context)_localctx).s1.getText():null));
			                            
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(312); ((Sn_usuario_f1Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Sn_usuario_f1Context)_localctx).PREP!=null?((Sn_usuario_f1Context)_localctx).PREP.getText():null));
			setState(320);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(316); ((Sn_usuario_f1Context)_localctx).s2 = match(SUBS);
				testaConceito((((Sn_usuario_f1Context)_localctx).s2!=null?((Sn_usuario_f1Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(318); ((Sn_usuario_f1Context)_localctx).ADJ = match(ADJ);
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
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_conceito_classe_f1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSn_conceito_classe_f1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSn_conceito_classe_f1(this);
		}
	}

	public final Sn_conceito_classe_f1Context sn_conceito_classe_f1() throws RecognitionException {
		Sn_conceito_classe_f1Context _localctx = new Sn_conceito_classe_f1Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_sn_conceito_classe_f1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(324); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(328); ((Sn_conceito_classe_f1Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_conceito_classe_f1Context)_localctx).s1!=null?((Sn_conceito_classe_f1Context)_localctx).s1.getText():null),"5 verbo");
			                             testaConceito((((Sn_conceito_classe_f1Context)_localctx).s1!=null?((Sn_conceito_classe_f1Context)_localctx).s1.getText():null));
			                            
			setState(331);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(330); ((Sn_conceito_classe_f1Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Sn_conceito_classe_f1Context)_localctx).PREP!=null?((Sn_conceito_classe_f1Context)_localctx).PREP.getText():null));
			setState(338);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(334); ((Sn_conceito_classe_f1Context)_localctx).s2 = match(SUBS);
				testaConceito((((Sn_conceito_classe_f1Context)_localctx).s2!=null?((Sn_conceito_classe_f1Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(336); ((Sn_conceito_classe_f1Context)_localctx).ADJ = match(ADJ);
				testaConceito((((Sn_conceito_classe_f1Context)_localctx).ADJ!=null?((Sn_conceito_classe_f1Context)_localctx).ADJ.getText():null));
				}
				break;
			}

			                          adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
			                          addConceitoAoElementoDaFrase("CONCEITO"); //1
			                        
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
		public Qualquer_coisaContext qualquer_coisa() {
			return getRuleContext(Qualquer_coisaContext.class,0);
		}
		public TerminalNode DEVE() { return getToken(SrsGrammarParser.DEVE, 0); }
		public TerminalNode SISTEMA() { return getToken(SrsGrammarParser.SISTEMA, 0); }
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(SrsGrammarParser.PONTO, 0); }
		public Iteracao_f3Context iteracao_f3() {
			return getRuleContext(Iteracao_f3Context.class,0);
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
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterF3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitF3(this);
		}
	}

	public final F3Context f3() throws RecognitionException {
		F3Context _localctx = new F3Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_f3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); det();
			setState(345);
			switch (_input.LA(1)) {
			case SISTEMA:
				{
				setState(343); match(SISTEMA);
				}
				break;
			case ART:
			case SUBS:
			case PRON:
			case NUM:
				{
				setState(344); sn_usuario_f3();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(347); ((F3Context)_localctx).DEVE = match(DEVE);
			setProximo((((F3Context)_localctx).DEVE!=null?((F3Context)_localctx).DEVE.getText():null),"12 verbo");
			setState(349); palavras();
			{
			setState(350); iteracao_f3();
			}
			setState(352);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(351); qualquer_coisa();
				}
				break;
			}
			setState(354); ((F3Context)_localctx).PONTO = match(PONTO);
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
		public Iteracao_verboContext iteracao_verbo(int i) {
			return getRuleContext(Iteracao_verboContext.class,i);
		}
		public Usuario_receptorContext usuario_receptor() {
			return getRuleContext(Usuario_receptorContext.class,0);
		}
		public List<Iteracao_verboContext> iteracao_verbo() {
			return getRuleContexts(Iteracao_verboContext.class);
		}
		public Sn_conceito_classe_f3Context sn_conceito_classe_f3() {
			return getRuleContext(Sn_conceito_classe_f3Context.class,0);
		}
		public Exp_temporalContext exp_temporal() {
			return getRuleContext(Exp_temporalContext.class,0);
		}
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public TerminalNode SEPARADOR() { return getToken(SrsGrammarParser.SEPARADOR, 0); }
		public TerminalNode VERB() { return getToken(SrsGrammarParser.VERB, 0); }
		public Iteracao_f3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteracao_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterIteracao_f3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitIteracao_f3(this);
		}
	}

	public final Iteracao_f3Context iteracao_f3() throws RecognitionException {
		Iteracao_f3Context _localctx = new Iteracao_f3Context(_ctx, getState());
		enterRule(_localctx, 54, RULE_iteracao_f3);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";conceitoTokenizado="";
			setState(358); ((Iteracao_f3Context)_localctx).VERB = match(VERB);
			setProximo((((Iteracao_f3Context)_localctx).VERB!=null?((Iteracao_f3Context)_localctx).VERB.getText():null),"9 classe");
			                              testaConceito((((Iteracao_f3Context)_localctx).VERB!=null?((Iteracao_f3Context)_localctx).VERB.getText():null));
			                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
			                              addConceitoAoElementoDaFrase("VERB"); //5
			                              tipoFrase = 2;
			                             
			{
			setState(363);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(360); iteracao_verbo();
					}
					} 
				}
				setState(365);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			conceito =""; conceitoTokenizado="";
			setState(367); palavras();
			setProximo(PALAVRAESPECIAL,"3 classe");
			                       testaConceito(PALAVRAESPECIAL);
			setState(369); sn_conceito_classe_f3();
			setState(371);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(370); usuario_receptor();
				}
				break;
			}
			setState(374);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(373); exp_temporal();
				}
				break;
			}
			setState(377);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(376); match(SEPARADOR);
				}
				break;
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

	public static class Usuario_receptorContext extends ParserRuleContext {
		public Token s1;
		public Token PREP;
		public Token s2;
		public Token ADJ;
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public PalavrasContext palavras() {
			return getRuleContext(PalavrasContext.class,0);
		}
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public Usuario_receptorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usuario_receptor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterUsuario_receptor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitUsuario_receptor(this);
		}
	}

	public final Usuario_receptorContext usuario_receptor() throws RecognitionException {
		Usuario_receptorContext _localctx = new Usuario_receptorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_usuario_receptor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379); palavras();
			conceito ="";conceitoTokenizado="";
			setState(381); ((Usuario_receptorContext)_localctx).s1 = match(SUBS);
			setProximo   ((((Usuario_receptorContext)_localctx).s1!=null?((Usuario_receptorContext)_localctx).s1.getText():null),"13 usuario_verbo");
			                                testaConceito((((Usuario_receptorContext)_localctx).s1!=null?((Usuario_receptorContext)_localctx).s1.getText():null));
			                               
			setState(384);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(383); ((Usuario_receptorContext)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Usuario_receptorContext)_localctx).PREP!=null?((Usuario_receptorContext)_localctx).PREP.getText():null));
			setState(391);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(387); ((Usuario_receptorContext)_localctx).s2 = match(SUBS);
				testaConceito((((Usuario_receptorContext)_localctx).s2!=null?((Usuario_receptorContext)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(389); ((Usuario_receptorContext)_localctx).ADJ = match(ADJ);
				testaConceito((((Usuario_receptorContext)_localctx).ADJ!=null?((Usuario_receptorContext)_localctx).ADJ.getText():null));
				}
				break;
			}

			                          adicionaConceitoTipado(Constante.USUARIO);//2
			                         
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
		public TerminalNode VERB() { return getToken(SrsGrammarParser.VERB, 0); }
		public Sn_conceito_classe_f5Context sn_conceito_classe_f5() {
			return getRuleContext(Sn_conceito_classe_f5Context.class,0);
		}
		public Exp_temporalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_temporal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterExp_temporal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitExp_temporal(this);
		}
	}

	public final Exp_temporalContext exp_temporal() throws RecognitionException {
		Exp_temporalContext _localctx = new Exp_temporalContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_exp_temporal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(395); palavras();
				}
				break;
			}
			setState(398); sn_usuario_f4();
			conceito ="";conceitoTokenizado="";
			setState(400); ((Exp_temporalContext)_localctx).VERB = match(VERB);
			setProximo((((Exp_temporalContext)_localctx).VERB!=null?((Exp_temporalContext)_localctx).VERB.getText():null),"9 classe");
			                              testaConceito((((Exp_temporalContext)_localctx).VERB!=null?((Exp_temporalContext)_localctx).VERB.getText():null));
			                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
			                             
			setState(402); palavras();
			setState(403); sn_conceito_classe_f5();
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
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_conceito_classe_f5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSn_conceito_classe_f5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSn_conceito_classe_f5(this);
		}
	}

	public final Sn_conceito_classe_f5Context sn_conceito_classe_f5() throws RecognitionException {
		Sn_conceito_classe_f5Context _localctx = new Sn_conceito_classe_f5Context(_ctx, getState());
		enterRule(_localctx, 60, RULE_sn_conceito_classe_f5);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(405); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(409); ((Sn_conceito_classe_f5Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_conceito_classe_f5Context)_localctx).s1!=null?((Sn_conceito_classe_f5Context)_localctx).s1.getText():null),"14 verbo");
			                                testaConceito((((Sn_conceito_classe_f5Context)_localctx).s1!=null?((Sn_conceito_classe_f5Context)_localctx).s1.getText():null));
			                               
			setState(412);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(411); ((Sn_conceito_classe_f5Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Sn_conceito_classe_f5Context)_localctx).PREP!=null?((Sn_conceito_classe_f5Context)_localctx).PREP.getText():null));
			setState(419);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(415); ((Sn_conceito_classe_f5Context)_localctx).s2 = match(SUBS);
				testaConceito((((Sn_conceito_classe_f5Context)_localctx).s2!=null?((Sn_conceito_classe_f5Context)_localctx).s2.getText():null));
				}
				break;

			case 2:
				{
				setState(417); ((Sn_conceito_classe_f5Context)_localctx).ADJ = match(ADJ);
				testaConceito((((Sn_conceito_classe_f5Context)_localctx).ADJ!=null?((Sn_conceito_classe_f5Context)_localctx).ADJ.getText():null));
				}
				break;
			}

			                          adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);//3
			                          adicionaConceitoTipado(Constante.USUARIO);//4
			                         
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
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_usuario_f4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSn_usuario_f4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSn_usuario_f4(this);
		}
	}

	public final Sn_usuario_f4Context sn_usuario_f4() throws RecognitionException {
		Sn_usuario_f4Context _localctx = new Sn_usuario_f4Context(_ctx, getState());
		enterRule(_localctx, 62, RULE_sn_usuario_f4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(423); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(427); ((Sn_usuario_f4Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_usuario_f4Context)_localctx).s1!=null?((Sn_usuario_f4Context)_localctx).s1.getText():null),"12 verbo");
			                                testaConceito((((Sn_usuario_f4Context)_localctx).s1!=null?((Sn_usuario_f4Context)_localctx).s1.getText():null));
			                               
			setState(430);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(429); ((Sn_usuario_f4Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sn_usuario_f4Context)_localctx).PREP!=null?((Sn_usuario_f4Context)_localctx).PREP.getText():null));
			setState(437);
			switch (_input.LA(1)) {
			case SUBS:
				{
				setState(433); ((Sn_usuario_f4Context)_localctx).s2 = match(SUBS);
				testaConceito((((Sn_usuario_f4Context)_localctx).s2!=null?((Sn_usuario_f4Context)_localctx).s2.getText():null));
				}
				break;
			case ADJ:
				{
				setState(435); ((Sn_usuario_f4Context)_localctx).ADJ = match(ADJ);
				testaConceito((((Sn_usuario_f4Context)_localctx).ADJ!=null?((Sn_usuario_f4Context)_localctx).ADJ.getText():null));
				}
				break;
			case VERB:
				break;
			default:
				throw new NoViableAltException(this);
			}

			                           adicionaConceitoTipado(Constante.USUARIO);
			                           adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);//10
			                         
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
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public DetContext det() {
			return getRuleContext(DetContext.class,0);
		}
		public Sn_usuario_f3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_usuario_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSn_usuario_f3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSn_usuario_f3(this);
		}
	}

	public final Sn_usuario_f3Context sn_usuario_f3() throws RecognitionException {
		Sn_usuario_f3Context _localctx = new Sn_usuario_f3Context(_ctx, getState());
		enterRule(_localctx, 64, RULE_sn_usuario_f3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ART) | (1L << PRON) | (1L << NUM))) != 0)) {
				{
				setState(441); det();
				}
			}

			conceito ="";conceitoTokenizado="";
			setState(445); ((Sn_usuario_f3Context)_localctx).s1 = match(SUBS);
			setProximo   ((((Sn_usuario_f3Context)_localctx).s1!=null?((Sn_usuario_f3Context)_localctx).s1.getText():null),"10 verbo");
			                                testaConceito((((Sn_usuario_f3Context)_localctx).s1!=null?((Sn_usuario_f3Context)_localctx).s1.getText():null));
			                               
			setState(448);
			_la = _input.LA(1);
			if (_la==PREP) {
				{
				setState(447); ((Sn_usuario_f3Context)_localctx).PREP = match(PREP);
				}
			}

			addPreposicao((((Sn_usuario_f3Context)_localctx).PREP!=null?((Sn_usuario_f3Context)_localctx).PREP.getText():null));
			setState(455);
			switch (_input.LA(1)) {
			case SUBS:
				{
				setState(451); ((Sn_usuario_f3Context)_localctx).s2 = match(SUBS);
				testaConceito((((Sn_usuario_f3Context)_localctx).s2!=null?((Sn_usuario_f3Context)_localctx).s2.getText():null));
				}
				break;
			case ADJ:
				{
				setState(453); ((Sn_usuario_f3Context)_localctx).ADJ = match(ADJ);
				testaConceito((((Sn_usuario_f3Context)_localctx).ADJ!=null?((Sn_usuario_f3Context)_localctx).ADJ.getText():null));
				}
				break;
			case DEVE:
				break;
			default:
				throw new NoViableAltException(this);
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
		public Sub1Context sub1;
		public Sub1Context sub1() {
			return getRuleContext(Sub1Context.class,0);
		}
		public Com_ou_sem_prepContext com_ou_sem_prep() {
			return getRuleContext(Com_ou_sem_prepContext.class,0);
		}
		public Sn_conceito_classe_f3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sn_conceito_classe_f3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSn_conceito_classe_f3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSn_conceito_classe_f3(this);
		}
	}

	public final Sn_conceito_classe_f3Context sn_conceito_classe_f3() throws RecognitionException {
		Sn_conceito_classe_f3Context _localctx = new Sn_conceito_classe_f3Context(_ctx, getState());
		enterRule(_localctx, 66, RULE_sn_conceito_classe_f3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito ="";conceitoTokenizado="";
			setState(460); ((Sn_conceito_classe_f3Context)_localctx).sub1 = sub1();
			setProximo((((Sn_conceito_classe_f3Context)_localctx).sub1!=null?_input.getText(((Sn_conceito_classe_f3Context)_localctx).sub1.start,((Sn_conceito_classe_f3Context)_localctx).sub1.stop):null),"11 usuario_classe");
			setState(463);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(462); com_ou_sem_prep();
				}
				break;
			}
			adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
			                        adicionaConceitoTipado(Constante.USUARIO);
			                        addConceitoAoElementoDaFrase("CONCEITO1");//4
			                        
			 adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);//5
			                       
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

	public static class Sub1Context extends ParserRuleContext {
		public Token SUBS;
		public TerminalNode SUBS() { return getToken(SrsGrammarParser.SUBS, 0); }
		public Sub1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSub1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSub1(this);
		}
	}

	public final Sub1Context sub1() throws RecognitionException {
		Sub1Context _localctx = new Sub1Context(_ctx, getState());
		enterRule(_localctx, 68, RULE_sub1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468); ((Sub1Context)_localctx).SUBS = match(SUBS);
			testaConceito((((Sub1Context)_localctx).SUBS!=null?((Sub1Context)_localctx).SUBS.getText():null));
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

	public static class Com_ou_sem_prepContext extends ParserRuleContext {
		public Com_propContext com_prop() {
			return getRuleContext(Com_propContext.class,0);
		}
		public Sem_propContext sem_prop() {
			return getRuleContext(Sem_propContext.class,0);
		}
		public Com_ou_sem_prepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_com_ou_sem_prep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterCom_ou_sem_prep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitCom_ou_sem_prep(this);
		}
	}

	public final Com_ou_sem_prepContext com_ou_sem_prep() throws RecognitionException {
		Com_ou_sem_prepContext _localctx = new Com_ou_sem_prepContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_com_ou_sem_prep);
		try {
			setState(473);
			switch (_input.LA(1)) {
			case PREP:
				enterOuterAlt(_localctx, 1);
				{
				setState(471); com_prop();
				}
				break;
			case ADJ:
			case SUBS:
				enterOuterAlt(_localctx, 2);
				{
				setState(472); sem_prop();
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

	public static class Com_propContext extends ParserRuleContext {
		public Token PREP;
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public Sub1Context sub1() {
			return getRuleContext(Sub1Context.class,0);
		}
		public AdjetivoContext adjetivo() {
			return getRuleContext(AdjetivoContext.class,0);
		}
		public Com_propContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_com_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterCom_prop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitCom_prop(this);
		}
	}

	public final Com_propContext com_prop() throws RecognitionException {
		Com_propContext _localctx = new Com_propContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_com_prop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475); ((Com_propContext)_localctx).PREP = match(PREP);
			addPreposicao((((Com_propContext)_localctx).PREP!=null?((Com_propContext)_localctx).PREP.getText():null));
			setState(479);
			switch (_input.LA(1)) {
			case ADJ:
				{
				setState(477); adjetivo();
				}
				break;
			case SUBS:
				{
				setState(478); sub1();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Sem_propContext extends ParserRuleContext {
		public Sub1Context sub1() {
			return getRuleContext(Sub1Context.class,0);
		}
		public AdjetivoContext adjetivo() {
			return getRuleContext(AdjetivoContext.class,0);
		}
		public Sem_propContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sem_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSem_prop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSem_prop(this);
		}
	}

	public final Sem_propContext sem_prop() throws RecognitionException {
		Sem_propContext _localctx = new Sem_propContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_sem_prop);
		try {
			setState(483);
			switch (_input.LA(1)) {
			case ADJ:
				enterOuterAlt(_localctx, 1);
				{
				setState(481); adjetivo();
				}
				break;
			case SUBS:
				enterOuterAlt(_localctx, 2);
				{
				setState(482); sub1();
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

	public static class AdjetivoContext extends ParserRuleContext {
		public Token ADJ;
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public AdjetivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_adjetivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterAdjetivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitAdjetivo(this);
		}
	}

	public final AdjetivoContext adjetivo() throws RecognitionException {
		AdjetivoContext _localctx = new AdjetivoContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_adjetivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485); ((AdjetivoContext)_localctx).ADJ = match(ADJ);
			testaConceito((((AdjetivoContext)_localctx).ADJ!=null?((AdjetivoContext)_localctx).ADJ.getText():null));
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
		public Outros_1Context outros_1(int i) {
			return getRuleContext(Outros_1Context.class,i);
		}
		public VerboContext verbo(int i) {
			return getRuleContext(VerboContext.class,i);
		}
		public List<Conceito_1Context> conceito_1() {
			return getRuleContexts(Conceito_1Context.class);
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
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterConceitos_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitConceitos_1(this);
		}
	}

	public final Conceitos_1Context conceitos_1() throws RecognitionException {
		Conceitos_1Context _localctx = new Conceitos_1Context(_ctx, getState());
		enterRule(_localctx, 78, RULE_conceitos_1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << ADJ) | (1L << SUBS) | (1L << VERB) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) {
				{
				setState(491);
				switch (_input.LA(1)) {
				case SUBS:
					{
					setState(488); conceito_1();
					}
					break;
				case VERB:
					{
					setState(489); verbo();
					}
					break;
				case NUMERO:
				case SIMBOLOS:
				case TERMINAL:
				case ART:
				case ADJ:
				case PREP:
				case PRON:
				case CONJ:
				case ADV:
				case NUM:
					{
					setState(490); outros_1();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(495);
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
		public TerminalNode PREP() { return getToken(SrsGrammarParser.PREP, 0); }
		public TerminalNode SUBS(int i) {
			return getToken(SrsGrammarParser.SUBS, i);
		}
		public TerminalNode ADJ() { return getToken(SrsGrammarParser.ADJ, 0); }
		public List<TerminalNode> SUBS() { return getTokens(SrsGrammarParser.SUBS); }
		public Conceito_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceito_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterConceito_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitConceito_1(this);
		}
	}

	public final Conceito_1Context conceito_1() throws RecognitionException {
		Conceito_1Context _localctx = new Conceito_1Context(_ctx, getState());
		enterRule(_localctx, 80, RULE_conceito_1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(497); ((Conceito_1Context)_localctx).s1 = match(SUBS);
			testaConceito((((Conceito_1Context)_localctx).s1!=null?((Conceito_1Context)_localctx).s1.getText():null));
			setState(500);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(499); ((Conceito_1Context)_localctx).PREP = match(PREP);
				}
				break;
			}
			addPreposicao((((Conceito_1Context)_localctx).PREP!=null?((Conceito_1Context)_localctx).PREP.getText():null));
			setState(507);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(503); ((Conceito_1Context)_localctx).ADJ = match(ADJ);
				testaConceito((((Conceito_1Context)_localctx).ADJ!=null?((Conceito_1Context)_localctx).ADJ.getText():null));
				}
				break;

			case 2:
				{
				setState(505); ((Conceito_1Context)_localctx).s2 = match(SUBS);
				testaConceito((((Conceito_1Context)_localctx).s2!=null?((Conceito_1Context)_localctx).s2.getText():null));
				}
				break;
			}
			addConceito();
			                                
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
		public TerminalNode SUBS() { return getToken(SrsGrammarParser.SUBS, 0); }
		public SubstantivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_substantivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterSubstantivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitSubstantivo(this);
		}
	}

	public final SubstantivoContext substantivo() throws RecognitionException {
		SubstantivoContext _localctx = new SubstantivoContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_substantivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(512); ((SubstantivoContext)_localctx).SUBS = match(SUBS);
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
		public TerminalNode VERB() { return getToken(SrsGrammarParser.VERB, 0); }
		public VerboContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verbo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterVerbo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitVerbo(this);
		}
	}

	public final VerboContext verbo() throws RecognitionException {
		VerboContext _localctx = new VerboContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_verbo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			conceito = "";
			setState(516); ((VerboContext)_localctx).VERB = match(VERB);
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
			return getToken(SrsGrammarParser.ADV, i);
		}
		public List<TerminalNode> PRON() { return getTokens(SrsGrammarParser.PRON); }
		public List<TerminalNode> SIMBOLOS() { return getTokens(SrsGrammarParser.SIMBOLOS); }
		public List<TerminalNode> ADV() { return getTokens(SrsGrammarParser.ADV); }
		public List<TerminalNode> NUM() { return getTokens(SrsGrammarParser.NUM); }
		public List<TerminalNode> NUMERO() { return getTokens(SrsGrammarParser.NUMERO); }
		public List<TerminalNode> TERMINAL() { return getTokens(SrsGrammarParser.TERMINAL); }
		public List<TerminalNode> CONJ() { return getTokens(SrsGrammarParser.CONJ); }
		public TerminalNode SIMBOLOS(int i) {
			return getToken(SrsGrammarParser.SIMBOLOS, i);
		}
		public TerminalNode TERMINAL(int i) {
			return getToken(SrsGrammarParser.TERMINAL, i);
		}
		public TerminalNode NUMERO(int i) {
			return getToken(SrsGrammarParser.NUMERO, i);
		}
		public TerminalNode ADJ(int i) {
			return getToken(SrsGrammarParser.ADJ, i);
		}
		public List<TerminalNode> PREP() { return getTokens(SrsGrammarParser.PREP); }
		public TerminalNode ART(int i) {
			return getToken(SrsGrammarParser.ART, i);
		}
		public TerminalNode PREP(int i) {
			return getToken(SrsGrammarParser.PREP, i);
		}
		public TerminalNode NUM(int i) {
			return getToken(SrsGrammarParser.NUM, i);
		}
		public TerminalNode PRON(int i) {
			return getToken(SrsGrammarParser.PRON, i);
		}
		public List<TerminalNode> ADJ() { return getTokens(SrsGrammarParser.ADJ); }
		public List<TerminalNode> ART() { return getTokens(SrsGrammarParser.ART); }
		public TerminalNode CONJ(int i) {
			return getToken(SrsGrammarParser.CONJ, i);
		}
		public Outros_1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).enterOutros_1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SrsGrammarListener ) ((SrsGrammarListener)listener).exitOutros_1(this);
		}
	}

	public final Outros_1Context outros_1() throws RecognitionException {
		Outros_1Context _localctx = new Outros_1Context(_ctx, getState());
		enterRule(_localctx, 86, RULE_outros_1);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(520); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(519);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMERO) | (1L << SIMBOLOS) | (1L << TERMINAL) | (1L << ART) | (1L << ADJ) | (1L << PREP) | (1L << PRON) | (1L << CONJ) | (1L << ADV) | (1L << NUM))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(522); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\31\u020f\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\7\2]\n\2\f\2\16\2`\13\2\3\3\3\3\3\3\3\3\5\3f\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\5\3m\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4\177\n\4\3\5\3\5\7\5\u0083\n\5\f\5\16\5\u0086"+
		"\13\5\3\6\3\6\3\6\3\6\5\6\u008c\n\6\3\6\3\6\3\6\3\6\3\6\5\6\u0093\n\6"+
		"\3\6\3\6\3\7\3\7\7\7\u0099\n\7\f\7\16\7\u009c\13\7\3\b\3\b\3\b\3\b\5\b"+
		"\u00a2\n\b\3\b\3\b\3\b\5\b\u00a7\n\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ae\n\b"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00ba\n\n\f\n\16\n\u00bd"+
		"\13\n\3\13\3\13\3\13\5\13\u00c2\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\5\17\u00d0\n\17\3\20\3\20\3\21\3\21\3\22\7\22\u00d7"+
		"\n\22\f\22\16\22\u00da\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\7\23\u00f8\n\23\f\23\16\23\u00fb\13\23\3"+
		"\23\3\23\3\24\3\24\5\24\u0101\n\24\3\25\3\25\3\25\3\25\5\25\u0107\n\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\7\27\u0114\n\27"+
		"\f\27\16\27\u0117\13\27\3\30\3\30\3\30\3\30\7\30\u011d\n\30\f\30\16\30"+
		"\u0120\13\30\3\30\3\30\3\30\5\30\u0125\n\30\6\30\u0127\n\30\r\30\16\30"+
		"\u0128\3\31\3\31\3\31\3\31\5\31\u012f\n\31\3\31\3\31\3\31\3\31\3\32\5"+
		"\32\u0136\n\32\3\32\3\32\3\32\3\32\5\32\u013c\n\32\3\32\3\32\3\32\3\32"+
		"\3\32\5\32\u0143\n\32\3\32\3\32\3\33\5\33\u0148\n\33\3\33\3\33\3\33\3"+
		"\33\5\33\u014e\n\33\3\33\3\33\3\33\3\33\3\33\5\33\u0155\n\33\3\33\3\33"+
		"\3\34\3\34\3\34\5\34\u015c\n\34\3\34\3\34\3\34\3\34\3\34\5\34\u0163\n"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\7\35\u016c\n\35\f\35\16\35\u016f"+
		"\13\35\3\35\3\35\3\35\3\35\3\35\5\35\u0176\n\35\3\35\5\35\u0179\n\35\3"+
		"\35\5\35\u017c\n\35\3\36\3\36\3\36\3\36\3\36\5\36\u0183\n\36\3\36\3\36"+
		"\3\36\3\36\3\36\5\36\u018a\n\36\3\36\3\36\3\37\5\37\u018f\n\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3 \5 \u0199\n \3 \3 \3 \3 \5 \u019f\n \3"+
		" \3 \3 \3 \3 \5 \u01a6\n \3 \3 \3!\5!\u01ab\n!\3!\3!\3!\3!\5!\u01b1\n"+
		"!\3!\3!\3!\3!\3!\5!\u01b8\n!\3!\3!\3\"\5\"\u01bd\n\"\3\"\3\"\3\"\3\"\5"+
		"\"\u01c3\n\"\3\"\3\"\3\"\3\"\3\"\5\"\u01ca\n\"\3\"\3\"\3#\3#\3#\3#\5#"+
		"\u01d2\n#\3#\3#\3#\3$\3$\3$\3%\3%\5%\u01dc\n%\3&\3&\3&\3&\5&\u01e2\n&"+
		"\3\'\3\'\5\'\u01e6\n\'\3(\3(\3(\3)\3)\3)\7)\u01ee\n)\f)\16)\u01f1\13)"+
		"\3*\3*\3*\3*\5*\u01f7\n*\3*\3*\3*\3*\3*\5*\u01fe\n*\3*\3*\3+\3+\3+\3+"+
		"\3,\3,\3,\3,\3-\6-\u020b\n-\r-\16-\u020c\3-\2.\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVX\2\7\6\2\5\6\b\b"+
		"\13\r\17\23\5\2\13\13\20\20\23\23\4\2\20\20\23\23\4\2\13\13\17\23\6\2"+
		"\5\6\b\b\13\f\17\23\u023f\2^\3\2\2\2\4a\3\2\2\2\6~\3\2\2\2\b\u0084\3\2"+
		"\2\2\n\u0087\3\2\2\2\f\u009a\3\2\2\2\16\u009d\3\2\2\2\20\u00b1\3\2\2\2"+
		"\22\u00bb\3\2\2\2\24\u00be\3\2\2\2\26\u00c3\3\2\2\2\30\u00c5\3\2\2\2\32"+
		"\u00c9\3\2\2\2\34\u00cd\3\2\2\2\36\u00d1\3\2\2\2 \u00d3\3\2\2\2\"\u00d8"+
		"\3\2\2\2$\u00f9\3\2\2\2&\u0100\3\2\2\2(\u0102\3\2\2\2*\u010b\3\2\2\2,"+
		"\u0110\3\2\2\2.\u0126\3\2\2\2\60\u012e\3\2\2\2\62\u0135\3\2\2\2\64\u0147"+
		"\3\2\2\2\66\u0158\3\2\2\28\u0167\3\2\2\2:\u017d\3\2\2\2<\u018e\3\2\2\2"+
		">\u0198\3\2\2\2@\u01aa\3\2\2\2B\u01bc\3\2\2\2D\u01cd\3\2\2\2F\u01d6\3"+
		"\2\2\2H\u01db\3\2\2\2J\u01dd\3\2\2\2L\u01e5\3\2\2\2N\u01e7\3\2\2\2P\u01ef"+
		"\3\2\2\2R\u01f2\3\2\2\2T\u0201\3\2\2\2V\u0205\3\2\2\2X\u020a\3\2\2\2Z"+
		"]\5\4\3\2[]\5\6\4\2\\Z\3\2\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2"+
		"\2_\3\3\2\2\2`^\3\2\2\2ab\b\3\1\2bc\7\r\2\2ce\b\3\1\2df\7\17\2\2ed\3\2"+
		"\2\2ef\3\2\2\2fg\3\2\2\2gl\b\3\1\2hi\7\f\2\2im\b\3\1\2jk\7\r\2\2km\b\3"+
		"\1\2lh\3\2\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\3\1\2o\5\3\2\2\2p\177"+
		"\7\13\2\2q\177\7\20\2\2r\177\7\21\2\2st\b\4\1\2tu\7\f\2\2uv\b\4\1\2v\177"+
		"\b\4\1\2w\177\7\23\2\2x\177\7\b\2\2y\177\7\17\2\2z\177\7\6\2\2{\177\7"+
		"\5\2\2|\177\7\16\2\2}\177\7\22\2\2~p\3\2\2\2~q\3\2\2\2~r\3\2\2\2~s\3\2"+
		"\2\2~w\3\2\2\2~x\3\2\2\2~y\3\2\2\2~z\3\2\2\2~{\3\2\2\2~|\3\2\2\2~}\3\2"+
		"\2\2\177\7\3\2\2\2\u0080\u0083\5\n\6\2\u0081\u0083\5\20\t\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\t\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\b\6\1\2"+
		"\u0088\u0089\7\r\2\2\u0089\u008b\b\6\1\2\u008a\u008c\7\17\2\2\u008b\u008a"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0092\b\6\1\2\u008e"+
		"\u008f\7\f\2\2\u008f\u0093\b\6\1\2\u0090\u0091\7\r\2\2\u0091\u0093\b\6"+
		"\1\2\u0092\u008e\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\b\6\1\2\u0095\13\3\2\2\2\u0096\u0099\5\16\b\2\u0097\u0099\5\20"+
		"\t\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\r\3\2\2\2\u009c\u009a\3\2\2\2"+
		"\u009d\u009e\b\b\1\2\u009e\u009f\7\16\2\2\u009f\u00a1\b\b\1\2\u00a0\u00a2"+
		"\5\"\22\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2"+
		"\u00a3\u00a4\7\r\2\2\u00a4\u00a6\b\b\1\2\u00a5\u00a7\7\17\2\2\u00a6\u00a5"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00ad\b\b\1\2\u00a9"+
		"\u00aa\7\f\2\2\u00aa\u00ae\b\b\1\2\u00ab\u00ac\7\r\2\2\u00ac\u00ae\b\b"+
		"\1\2\u00ad\u00a9\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\b\b\1\2\u00b0\17\3\2\2\2\u00b1\u00b2\t\2\2"+
		"\2\u00b2\21\3\2\2\2\u00b3\u00b4\7\4\2\2\u00b4\u00ba\b\n\1\2\u00b5\u00b6"+
		"\7\b\2\2\u00b6\u00ba\b\n\1\2\u00b7\u00b8\7\t\2\2\u00b8\u00ba\b\n\1\2\u00b9"+
		"\u00b3\3\2\2\2\u00b9\u00b5\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bd\3\2"+
		"\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\23\3\2\2\2\u00bd\u00bb"+
		"\3\2\2\2\u00be\u00bf\5\26\f\2\u00bf\u00c1\5\30\r\2\u00c0\u00c2\5\32\16"+
		"\2\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\25\3\2\2\2\u00c3\u00c4"+
		"\7\13\2\2\u00c4\27\3\2\2\2\u00c5\u00c6\b\r\1\2\u00c6\u00c7\7\25\2\2\u00c7"+
		"\u00c8\b\r\1\2\u00c8\31\3\2\2\2\u00c9\u00ca\b\16\1\2\u00ca\u00cb\7\26"+
		"\2\2\u00cb\u00cc\b\16\1\2\u00cc\33\3\2\2\2\u00cd\u00cf\5\36\20\2\u00ce"+
		"\u00d0\5 \21\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\35\3\2\2"+
		"\2\u00d1\u00d2\t\3\2\2\u00d2\37\3\2\2\2\u00d3\u00d4\t\4\2\2\u00d4!\3\2"+
		"\2\2\u00d5\u00d7\t\5\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9#\3\2\2\2\u00da\u00d8\3\2\2\2"+
		"\u00db\u00dc\7\13\2\2\u00dc\u00f8\b\23\1\2\u00dd\u00de\7\17\2\2\u00de"+
		"\u00f8\b\23\1\2\u00df\u00e0\7\20\2\2\u00e0\u00f8\b\23\1\2\u00e1\u00e2"+
		"\7\21\2\2\u00e2\u00f8\b\23\1\2\u00e3\u00e4\7\22\2\2\u00e4\u00f8\b\23\1"+
		"\2\u00e5\u00e6\7\23\2\2\u00e6\u00f8\b\23\1\2\u00e7\u00e8\7\r\2\2\u00e8"+
		"\u00f8\b\23\1\2\u00e9\u00ea\7\f\2\2\u00ea\u00f8\b\23\1\2\u00eb\u00ec\7"+
		"\16\2\2\u00ec\u00f8\b\23\1\2\u00ed\u00ee\7\b\2\2\u00ee\u00f8\b\23\1\2"+
		"\u00ef\u00f0\7\6\2\2\u00f0\u00f8\b\23\1\2\u00f1\u00f2\7\t\2\2\u00f2\u00f8"+
		"\b\23\1\2\u00f3\u00f4\7\27\2\2\u00f4\u00f8\b\23\1\2\u00f5\u00f6\7\26\2"+
		"\2\u00f6\u00f8\b\23\1\2\u00f7\u00db\3\2\2\2\u00f7\u00dd\3\2\2\2\u00f7"+
		"\u00df\3\2\2\2\u00f7\u00e1\3\2\2\2\u00f7\u00e3\3\2\2\2\u00f7\u00e5\3\2"+
		"\2\2\u00f7\u00e7\3\2\2\2\u00f7\u00e9\3\2\2\2\u00f7\u00eb\3\2\2\2\u00f7"+
		"\u00ed\3\2\2\2\u00f7\u00ef\3\2\2\2\u00f7\u00f1\3\2\2\2\u00f7\u00f3\3\2"+
		"\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\b\23"+
		"\1\2\u00fd%\3\2\2\2\u00fe\u0101\5(\25\2\u00ff\u0101\5\66\34\2\u0100\u00fe"+
		"\3\2\2\2\u0100\u00ff\3\2\2\2\u0101\'\3\2\2\2\u0102\u0103\5\24\13\2\u0103"+
		"\u0104\5*\26\2\u0104\u0106\5\"\22\2\u0105\u0107\5$\23\2\u0106\u0105\3"+
		"\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\7\30\2\2\u0109"+
		"\u010a\b\25\1\2\u010a)\3\2\2\2\u010b\u010c\b\26\1\2\u010c\u010d\7\31\2"+
		"\2\u010d\u010e\b\26\1\2\u010e\u010f\5,\27\2\u010f+\3\2\2\2\u0110\u0111"+
		"\5\"\22\2\u0111\u0115\5\62\32\2\u0112\u0114\5.\30\2\u0113\u0112\3\2\2"+
		"\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116-"+
		"\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0119\b\30\1\2\u0119\u011a\7\16\2\2"+
		"\u011a\u011e\b\30\1\2\u011b\u011d\5\60\31\2\u011c\u011b\3\2\2\2\u011d"+
		"\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2"+
		"\2\2\u0120\u011e\3\2\2\2\u0121\u0122\5\"\22\2\u0122\u0124\5\64\33\2\u0123"+
		"\u0125\7\27\2\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3"+
		"\2\2\2\u0126\u0118\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129/\3\2\2\2\u012a\u012b\7\27\2\2\u012b\u012f\b\31\1"+
		"\2\u012c\u012d\7\21\2\2\u012d\u012f\b\31\1\2\u012e\u012a\3\2\2\2\u012e"+
		"\u012c\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b\31\1\2\u0131\u0132\7"+
		"\16\2\2\u0132\u0133\b\31\1\2\u0133\61\3\2\2\2\u0134\u0136\5\34\17\2\u0135"+
		"\u0134\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\b\32"+
		"\1\2\u0138\u0139\7\r\2\2\u0139\u013b\b\32\1\2\u013a\u013c\7\17\2\2\u013b"+
		"\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u0142\b\32"+
		"\1\2\u013e\u013f\7\r\2\2\u013f\u0143\b\32\1\2\u0140\u0141\7\f\2\2\u0141"+
		"\u0143\b\32\1\2\u0142\u013e\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3"+
		"\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\b\32\1\2\u0145\63\3\2\2\2\u0146"+
		"\u0148\5\34\17\2\u0147\u0146\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\3"+
		"\2\2\2\u0149\u014a\b\33\1\2\u014a\u014b\7\r\2\2\u014b\u014d\b\33\1\2\u014c"+
		"\u014e\7\17\2\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3"+
		"\2\2\2\u014f\u0154\b\33\1\2\u0150\u0151\7\r\2\2\u0151\u0155\b\33\1\2\u0152"+
		"\u0153\7\f\2\2\u0153\u0155\b\33\1\2\u0154\u0150\3\2\2\2\u0154\u0152\3"+
		"\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\b\33\1\2\u0157"+
		"\65\3\2\2\2\u0158\u015b\5\34\17\2\u0159\u015c\7\25\2\2\u015a\u015c\5B"+
		"\"\2\u015b\u0159\3\2\2\2\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d"+
		"\u015e\7\26\2\2\u015e\u015f\b\34\1\2\u015f\u0160\5\"\22\2\u0160\u0162"+
		"\58\35\2\u0161\u0163\5$\23\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163"+
		"\u0164\3\2\2\2\u0164\u0165\7\30\2\2\u0165\u0166\b\34\1\2\u0166\67\3\2"+
		"\2\2\u0167\u0168\b\35\1\2\u0168\u0169\7\16\2\2\u0169\u016d\b\35\1\2\u016a"+
		"\u016c\5\60\31\2\u016b\u016a\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3"+
		"\2\2\2\u016d\u016e\3\2\2\2\u016e\u0170\3\2\2\2\u016f\u016d\3\2\2\2\u0170"+
		"\u0171\b\35\1\2\u0171\u0172\5\"\22\2\u0172\u0173\b\35\1\2\u0173\u0175"+
		"\5D#\2\u0174\u0176\5:\36\2\u0175\u0174\3\2\2\2\u0175\u0176\3\2\2\2\u0176"+
		"\u0178\3\2\2\2\u0177\u0179\5<\37\2\u0178\u0177\3\2\2\2\u0178\u0179\3\2"+
		"\2\2\u0179\u017b\3\2\2\2\u017a\u017c\7\27\2\2\u017b\u017a\3\2\2\2\u017b"+
		"\u017c\3\2\2\2\u017c9\3\2\2\2\u017d\u017e\5\"\22\2\u017e\u017f\b\36\1"+
		"\2\u017f\u0180\7\r\2\2\u0180\u0182\b\36\1\2\u0181\u0183\7\17\2\2\u0182"+
		"\u0181\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0189\b\36"+
		"\1\2\u0185\u0186\7\r\2\2\u0186\u018a\b\36\1\2\u0187\u0188\7\f\2\2\u0188"+
		"\u018a\b\36\1\2\u0189\u0185\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3"+
		"\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\b\36\1\2\u018c;\3\2\2\2\u018d\u018f"+
		"\5\"\22\2\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\3\2\2\2"+
		"\u0190\u0191\5@!\2\u0191\u0192\b\37\1\2\u0192\u0193\7\16\2\2\u0193\u0194"+
		"\b\37\1\2\u0194\u0195\5\"\22\2\u0195\u0196\5> \2\u0196=\3\2\2\2\u0197"+
		"\u0199\5\34\17\2\u0198\u0197\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3"+
		"\2\2\2\u019a\u019b\b \1\2\u019b\u019c\7\r\2\2\u019c\u019e\b \1\2\u019d"+
		"\u019f\7\17\2\2\u019e\u019d\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\3"+
		"\2\2\2\u01a0\u01a5\b \1\2\u01a1\u01a2\7\r\2\2\u01a2\u01a6\b \1\2\u01a3"+
		"\u01a4\7\f\2\2\u01a4\u01a6\b \1\2\u01a5\u01a1\3\2\2\2\u01a5\u01a3\3\2"+
		"\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\b \1\2\u01a8"+
		"?\3\2\2\2\u01a9\u01ab\5\34\17\2\u01aa\u01a9\3\2\2\2\u01aa\u01ab\3\2\2"+
		"\2\u01ab\u01ac\3\2\2\2\u01ac\u01ad\b!\1\2\u01ad\u01ae\7\r\2\2\u01ae\u01b0"+
		"\b!\1\2\u01af\u01b1\7\17\2\2\u01b0\u01af\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01b7\b!\1\2\u01b3\u01b4\7\r\2\2\u01b4\u01b8\b!\1"+
		"\2\u01b5\u01b6\7\f\2\2\u01b6\u01b8\b!\1\2\u01b7\u01b3\3\2\2\2\u01b7\u01b5"+
		"\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\b!\1\2\u01ba"+
		"A\3\2\2\2\u01bb\u01bd\5\34\17\2\u01bc\u01bb\3\2\2\2\u01bc\u01bd\3\2\2"+
		"\2\u01bd\u01be\3\2\2\2\u01be\u01bf\b\"\1\2\u01bf\u01c0\7\r\2\2\u01c0\u01c2"+
		"\b\"\1\2\u01c1\u01c3\7\17\2\2\u01c2\u01c1\3\2\2\2\u01c2\u01c3\3\2\2\2"+
		"\u01c3\u01c4\3\2\2\2\u01c4\u01c9\b\"\1\2\u01c5\u01c6\7\r\2\2\u01c6\u01ca"+
		"\b\"\1\2\u01c7\u01c8\7\f\2\2\u01c8\u01ca\b\"\1\2\u01c9\u01c5\3\2\2\2\u01c9"+
		"\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01cc\b\""+
		"\1\2\u01ccC\3\2\2\2\u01cd\u01ce\b#\1\2\u01ce\u01cf\5F$\2\u01cf\u01d1\b"+
		"#\1\2\u01d0\u01d2\5H%\2\u01d1\u01d0\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2"+
		"\u01d3\3\2\2\2\u01d3\u01d4\b#\1\2\u01d4\u01d5\b#\1\2\u01d5E\3\2\2\2\u01d6"+
		"\u01d7\7\r\2\2\u01d7\u01d8\b$\1\2\u01d8G\3\2\2\2\u01d9\u01dc\5J&\2\u01da"+
		"\u01dc\5L\'\2\u01db\u01d9\3\2\2\2\u01db\u01da\3\2\2\2\u01dcI\3\2\2\2\u01dd"+
		"\u01de\7\17\2\2\u01de\u01e1\b&\1\2\u01df\u01e2\5N(\2\u01e0\u01e2\5F$\2"+
		"\u01e1\u01df\3\2\2\2\u01e1\u01e0\3\2\2\2\u01e2K\3\2\2\2\u01e3\u01e6\5"+
		"N(\2\u01e4\u01e6\5F$\2\u01e5\u01e3\3\2\2\2\u01e5\u01e4\3\2\2\2\u01e6M"+
		"\3\2\2\2\u01e7\u01e8\7\f\2\2\u01e8\u01e9\b(\1\2\u01e9O\3\2\2\2\u01ea\u01ee"+
		"\5R*\2\u01eb\u01ee\5V,\2\u01ec\u01ee\5X-\2\u01ed\u01ea\3\2\2\2\u01ed\u01eb"+
		"\3\2\2\2\u01ed\u01ec\3\2\2\2\u01ee\u01f1\3\2\2\2\u01ef\u01ed\3\2\2\2\u01ef"+
		"\u01f0\3\2\2\2\u01f0Q\3\2\2\2\u01f1\u01ef\3\2\2\2\u01f2\u01f3\b*\1\2\u01f3"+
		"\u01f4\7\r\2\2\u01f4\u01f6\b*\1\2\u01f5\u01f7\7\17\2\2\u01f6\u01f5\3\2"+
		"\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01fd\b*\1\2\u01f9"+
		"\u01fa\7\f\2\2\u01fa\u01fe\b*\1\2\u01fb\u01fc\7\r\2\2\u01fc\u01fe\b*\1"+
		"\2\u01fd\u01f9\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff"+
		"\3\2\2\2\u01ff\u0200\b*\1\2\u0200S\3\2\2\2\u0201\u0202\b+\1\2\u0202\u0203"+
		"\7\r\2\2\u0203\u0204\b+\1\2\u0204U\3\2\2\2\u0205\u0206\b,\1\2\u0206\u0207"+
		"\7\16\2\2\u0207\u0208\b,\1\2\u0208W\3\2\2\2\u0209\u020b\t\6\2\2\u020a"+
		"\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020a\3\2\2\2\u020c\u020d\3\2"+
		"\2\2\u020dY\3\2\2\2?\\^el~\u0082\u0084\u008b\u0092\u0098\u009a\u00a1\u00a6"+
		"\u00ad\u00b9\u00bb\u00c1\u00cf\u00d8\u00f7\u00f9\u0100\u0106\u0115\u011e"+
		"\u0124\u0128\u012e\u0135\u013b\u0142\u0147\u014d\u0154\u015b\u0162\u016d"+
		"\u0175\u0178\u017b\u0182\u0189\u018e\u0198\u019e\u01a5\u01aa\u01b0\u01b7"+
		"\u01bc\u01c2\u01c9\u01d1\u01db\u01e1\u01e5\u01ed\u01ef\u01f6\u01fd\u020c";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}