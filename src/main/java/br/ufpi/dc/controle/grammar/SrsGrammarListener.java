// Generated from D:\mestrado\netbeans\SRSEDITORMCertidoes\src\grammar\SrsGrammar.g4 by ANTLR 4.1

package br.ufpi.dc.controle.grammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
//import visao.view.tools.Constante;
//import visao.view.tools.ElementosFrase;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SrsGrammarParser}.
 */
public interface SrsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#p1}.
	 * @param ctx the parse tree
	 */
	void enterP1(@NotNull SrsGrammarParser.P1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#p1}.
	 * @param ctx the parse tree
	 */
	void exitP1(@NotNull SrsGrammarParser.P1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sist}.
	 * @param ctx the parse tree
	 */
	void enterSist(@NotNull SrsGrammarParser.SistContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sist}.
	 * @param ctx the parse tree
	 */
	void exitSist(@NotNull SrsGrammarParser.SistContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#conceitos}.
	 * @param ctx the parse tree
	 */
	void enterConceitos(@NotNull SrsGrammarParser.ConceitosContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#conceitos}.
	 * @param ctx the parse tree
	 */
	void exitConceitos(@NotNull SrsGrammarParser.ConceitosContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#conceito_1}.
	 * @param ctx the parse tree
	 */
	void enterConceito_1(@NotNull SrsGrammarParser.Conceito_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#conceito_1}.
	 * @param ctx the parse tree
	 */
	void exitConceito_1(@NotNull SrsGrammarParser.Conceito_1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#osistemadeve}.
	 * @param ctx the parse tree
	 */
	void enterOsistemadeve(@NotNull SrsGrammarParser.OsistemadeveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#osistemadeve}.
	 * @param ctx the parse tree
	 */
	void exitOsistemadeve(@NotNull SrsGrammarParser.OsistemadeveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sub1}.
	 * @param ctx the parse tree
	 */
	void enterSub1(@NotNull SrsGrammarParser.Sub1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sub1}.
	 * @param ctx the parse tree
	 */
	void exitSub1(@NotNull SrsGrammarParser.Sub1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#funcionalidade}.
	 * @param ctx the parse tree
	 */
	void enterFuncionalidade(@NotNull SrsGrammarParser.FuncionalidadeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#funcionalidade}.
	 * @param ctx the parse tree
	 */
	void exitFuncionalidade(@NotNull SrsGrammarParser.FuncionalidadeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#qualquer_coisa}.
	 * @param ctx the parse tree
	 */
	void enterQualquer_coisa(@NotNull SrsGrammarParser.Qualquer_coisaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#qualquer_coisa}.
	 * @param ctx the parse tree
	 */
	void exitQualquer_coisa(@NotNull SrsGrammarParser.Qualquer_coisaContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#inicio}.
	 * @param ctx the parse tree
	 */
	void enterInicio(@NotNull SrsGrammarParser.InicioContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#inicio}.
	 * @param ctx the parse tree
	 */
	void exitInicio(@NotNull SrsGrammarParser.InicioContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#exp_temporal}.
	 * @param ctx the parse tree
	 */
	void enterExp_temporal(@NotNull SrsGrammarParser.Exp_temporalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#exp_temporal}.
	 * @param ctx the parse tree
	 */
	void exitExp_temporal(@NotNull SrsGrammarParser.Exp_temporalContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#com_ou_sem_prep}.
	 * @param ctx the parse tree
	 */
	void enterCom_ou_sem_prep(@NotNull SrsGrammarParser.Com_ou_sem_prepContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#com_ou_sem_prep}.
	 * @param ctx the parse tree
	 */
	void exitCom_ou_sem_prep(@NotNull SrsGrammarParser.Com_ou_sem_prepContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#f1}.
	 * @param ctx the parse tree
	 */
	void enterF1(@NotNull SrsGrammarParser.F1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#f1}.
	 * @param ctx the parse tree
	 */
	void exitF1(@NotNull SrsGrammarParser.F1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#pos_det}.
	 * @param ctx the parse tree
	 */
	void enterPos_det(@NotNull SrsGrammarParser.Pos_detContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#pos_det}.
	 * @param ctx the parse tree
	 */
	void exitPos_det(@NotNull SrsGrammarParser.Pos_detContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#palavras}.
	 * @param ctx the parse tree
	 */
	void enterPalavras(@NotNull SrsGrammarParser.PalavrasContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#palavras}.
	 * @param ctx the parse tree
	 */
	void exitPalavras(@NotNull SrsGrammarParser.PalavrasContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#f3}.
	 * @param ctx the parse tree
	 */
	void enterF3(@NotNull SrsGrammarParser.F3Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#f3}.
	 * @param ctx the parse tree
	 */
	void exitF3(@NotNull SrsGrammarParser.F3Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#det}.
	 * @param ctx the parse tree
	 */
	void enterDet(@NotNull SrsGrammarParser.DetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#det}.
	 * @param ctx the parse tree
	 */
	void exitDet(@NotNull SrsGrammarParser.DetContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#iteracao_f1}.
	 * @param ctx the parse tree
	 */
	void enterIteracao_f1(@NotNull SrsGrammarParser.Iteracao_f1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#iteracao_f1}.
	 * @param ctx the parse tree
	 */
	void exitIteracao_f1(@NotNull SrsGrammarParser.Iteracao_f1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#iteracao_f3}.
	 * @param ctx the parse tree
	 */
	void enterIteracao_f3(@NotNull SrsGrammarParser.Iteracao_f3Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#iteracao_f3}.
	 * @param ctx the parse tree
	 */
	void exitIteracao_f3(@NotNull SrsGrammarParser.Iteracao_f3Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#det_base}.
	 * @param ctx the parse tree
	 */
	void enterDet_base(@NotNull SrsGrammarParser.Det_baseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#det_base}.
	 * @param ctx the parse tree
	 */
	void exitDet_base(@NotNull SrsGrammarParser.Det_baseContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#outros}.
	 * @param ctx the parse tree
	 */
	void enterOutros(@NotNull SrsGrammarParser.OutrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#outros}.
	 * @param ctx the parse tree
	 */
	void exitOutros(@NotNull SrsGrammarParser.OutrosContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#atributo}.
	 * @param ctx the parse tree
	 */
	void enterAtributo(@NotNull SrsGrammarParser.AtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#atributo}.
	 * @param ctx the parse tree
	 */
	void exitAtributo(@NotNull SrsGrammarParser.AtributoContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#deve}.
	 * @param ctx the parse tree
	 */
	void enterDeve(@NotNull SrsGrammarParser.DeveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#deve}.
	 * @param ctx the parse tree
	 */
	void exitDeve(@NotNull SrsGrammarParser.DeveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#usuario_receptor}.
	 * @param ctx the parse tree
	 */
	void enterUsuario_receptor(@NotNull SrsGrammarParser.Usuario_receptorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#usuario_receptor}.
	 * @param ctx the parse tree
	 */
	void exitUsuario_receptor(@NotNull SrsGrammarParser.Usuario_receptorContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#verbo}.
	 * @param ctx the parse tree
	 */
	void enterVerbo(@NotNull SrsGrammarParser.VerboContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#verbo}.
	 * @param ctx the parse tree
	 */
	void exitVerbo(@NotNull SrsGrammarParser.VerboContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(@NotNull SrsGrammarParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(@NotNull SrsGrammarParser.InitContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sv}.
	 * @param ctx the parse tree
	 */
	void enterSv(@NotNull SrsGrammarParser.SvContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sv}.
	 * @param ctx the parse tree
	 */
	void exitSv(@NotNull SrsGrammarParser.SvContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sn_usuario_f4}.
	 * @param ctx the parse tree
	 */
	void enterSn_usuario_f4(@NotNull SrsGrammarParser.Sn_usuario_f4Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sn_usuario_f4}.
	 * @param ctx the parse tree
	 */
	void exitSn_usuario_f4(@NotNull SrsGrammarParser.Sn_usuario_f4Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#adjetivo}.
	 * @param ctx the parse tree
	 */
	void enterAdjetivo(@NotNull SrsGrammarParser.AdjetivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#adjetivo}.
	 * @param ctx the parse tree
	 */
	void exitAdjetivo(@NotNull SrsGrammarParser.AdjetivoContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sn_usuario_f3}.
	 * @param ctx the parse tree
	 */
	void enterSn_usuario_f3(@NotNull SrsGrammarParser.Sn_usuario_f3Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sn_usuario_f3}.
	 * @param ctx the parse tree
	 */
	void exitSn_usuario_f3(@NotNull SrsGrammarParser.Sn_usuario_f3Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#iteracao_verbo}.
	 * @param ctx the parse tree
	 */
	void enterIteracao_verbo(@NotNull SrsGrammarParser.Iteracao_verboContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#iteracao_verbo}.
	 * @param ctx the parse tree
	 */
	void exitIteracao_verbo(@NotNull SrsGrammarParser.Iteracao_verboContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sn_usuario_f1}.
	 * @param ctx the parse tree
	 */
	void enterSn_usuario_f1(@NotNull SrsGrammarParser.Sn_usuario_f1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sn_usuario_f1}.
	 * @param ctx the parse tree
	 */
	void exitSn_usuario_f1(@NotNull SrsGrammarParser.Sn_usuario_f1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sn_conceito_classe_f5}.
	 * @param ctx the parse tree
	 */
	void enterSn_conceito_classe_f5(@NotNull SrsGrammarParser.Sn_conceito_classe_f5Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sn_conceito_classe_f5}.
	 * @param ctx the parse tree
	 */
	void exitSn_conceito_classe_f5(@NotNull SrsGrammarParser.Sn_conceito_classe_f5Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sn_conceito_classe_f3}.
	 * @param ctx the parse tree
	 */
	void enterSn_conceito_classe_f3(@NotNull SrsGrammarParser.Sn_conceito_classe_f3Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sn_conceito_classe_f3}.
	 * @param ctx the parse tree
	 */
	void exitSn_conceito_classe_f3(@NotNull SrsGrammarParser.Sn_conceito_classe_f3Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sem_prop}.
	 * @param ctx the parse tree
	 */
	void enterSem_prop(@NotNull SrsGrammarParser.Sem_propContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sem_prop}.
	 * @param ctx the parse tree
	 */
	void exitSem_prop(@NotNull SrsGrammarParser.Sem_propContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#outros_atributos}.
	 * @param ctx the parse tree
	 */
	void enterOutros_atributos(@NotNull SrsGrammarParser.Outros_atributosContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#outros_atributos}.
	 * @param ctx the parse tree
	 */
	void exitOutros_atributos(@NotNull SrsGrammarParser.Outros_atributosContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#sn_conceito_classe_f1}.
	 * @param ctx the parse tree
	 */
	void enterSn_conceito_classe_f1(@NotNull SrsGrammarParser.Sn_conceito_classe_f1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#sn_conceito_classe_f1}.
	 * @param ctx the parse tree
	 */
	void exitSn_conceito_classe_f1(@NotNull SrsGrammarParser.Sn_conceito_classe_f1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#o}.
	 * @param ctx the parse tree
	 */
	void enterO(@NotNull SrsGrammarParser.OContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#o}.
	 * @param ctx the parse tree
	 */
	void exitO(@NotNull SrsGrammarParser.OContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#outros_1}.
	 * @param ctx the parse tree
	 */
	void enterOutros_1(@NotNull SrsGrammarParser.Outros_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#outros_1}.
	 * @param ctx the parse tree
	 */
	void exitOutros_1(@NotNull SrsGrammarParser.Outros_1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#com_prop}.
	 * @param ctx the parse tree
	 */
	void enterCom_prop(@NotNull SrsGrammarParser.Com_propContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#com_prop}.
	 * @param ctx the parse tree
	 */
	void exitCom_prop(@NotNull SrsGrammarParser.Com_propContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#substantivo}.
	 * @param ctx the parse tree
	 */
	void enterSubstantivo(@NotNull SrsGrammarParser.SubstantivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#substantivo}.
	 * @param ctx the parse tree
	 */
	void exitSubstantivo(@NotNull SrsGrammarParser.SubstantivoContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#conceitos_1}.
	 * @param ctx the parse tree
	 */
	void enterConceitos_1(@NotNull SrsGrammarParser.Conceitos_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#conceitos_1}.
	 * @param ctx the parse tree
	 */
	void exitConceitos_1(@NotNull SrsGrammarParser.Conceitos_1Context ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#atributos}.
	 * @param ctx the parse tree
	 */
	void enterAtributos(@NotNull SrsGrammarParser.AtributosContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#atributos}.
	 * @param ctx the parse tree
	 */
	void exitAtributos(@NotNull SrsGrammarParser.AtributosContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#conceito}.
	 * @param ctx the parse tree
	 */
	void enterConceito(@NotNull SrsGrammarParser.ConceitoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#conceito}.
	 * @param ctx the parse tree
	 */
	void exitConceito(@NotNull SrsGrammarParser.ConceitoContext ctx);

	/**
	 * Enter a parse tree produced by {@link SrsGrammarParser#funcionalidades}.
	 * @param ctx the parse tree
	 */
	void enterFuncionalidades(@NotNull SrsGrammarParser.FuncionalidadesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SrsGrammarParser#funcionalidades}.
	 * @param ctx the parse tree
	 */
	void exitFuncionalidades(@NotNull SrsGrammarParser.FuncionalidadesContext ctx);
}