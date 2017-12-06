// Generated from D:\mestrado\netbeans\SRSEDITORM\src\UCGrammar\UCGrammar.g4 by ANTLR 4.1

package br.ufpi.dc.controle.UCGrammar;
         
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link UCGrammarParser}.
 */
@SuppressWarnings("deprecation")
public interface UCGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#complementos}.
	 * @param ctx the parse tree
	 */
	void enterComplementos(@NotNull UCGrammarParser.ComplementosContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#complementos}.
	 * @param ctx the parse tree
	 */
	void exitComplementos(@NotNull UCGrammarParser.ComplementosContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#numero}.
	 * @param ctx the parse tree
	 */
	void enterNumero(@NotNull UCGrammarParser.NumeroContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#numero}.
	 * @param ctx the parse tree
	 */
	void exitNumero(@NotNull UCGrammarParser.NumeroContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#sentenca_acao_usuario}.
	 * @param ctx the parse tree
	 */
	void enterSentenca_acao_usuario(@NotNull UCGrammarParser.Sentenca_acao_usuarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#sentenca_acao_usuario}.
	 * @param ctx the parse tree
	 */
	void exitSentenca_acao_usuario(@NotNull UCGrammarParser.Sentenca_acao_usuarioContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#palavras_uc_1}.
	 * @param ctx the parse tree
	 */
	void enterPalavras_uc_1(@NotNull UCGrammarParser.Palavras_uc_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#palavras_uc_1}.
	 * @param ctx the parse tree
	 */
	void exitPalavras_uc_1(@NotNull UCGrammarParser.Palavras_uc_1Context ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#conceito_1}.
	 * @param ctx the parse tree
	 */
	void enterConceito_1(@NotNull UCGrammarParser.Conceito_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#conceito_1}.
	 * @param ctx the parse tree
	 */
	void exitConceito_1(@NotNull UCGrammarParser.Conceito_1Context ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#qualquer_coisa}.
	 * @param ctx the parse tree
	 */
	void enterQualquer_coisa(@NotNull UCGrammarParser.Qualquer_coisaContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#qualquer_coisa}.
	 * @param ctx the parse tree
	 */
	void exitQualquer_coisa(@NotNull UCGrammarParser.Qualquer_coisaContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#qualquer_coisa_para}.
	 * @param ctx the parse tree
	 */
	void enterQualquer_coisa_para(@NotNull UCGrammarParser.Qualquer_coisa_paraContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#qualquer_coisa_para}.
	 * @param ctx the parse tree
	 */
	void exitQualquer_coisa_para(@NotNull UCGrammarParser.Qualquer_coisa_paraContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#sentenca_condicional}.
	 * @param ctx the parse tree
	 */
	void enterSentenca_condicional(@NotNull UCGrammarParser.Sentenca_condicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#sentenca_condicional}.
	 * @param ctx the parse tree
	 */
	void exitSentenca_condicional(@NotNull UCGrammarParser.Sentenca_condicionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(@NotNull UCGrammarParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(@NotNull UCGrammarParser.ExpressaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#e1}.
	 * @param ctx the parse tree
	 */
	void enterE1(@NotNull UCGrammarParser.E1Context ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#e1}.
	 * @param ctx the parse tree
	 */
	void exitE1(@NotNull UCGrammarParser.E1Context ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#pos_det}.
	 * @param ctx the parse tree
	 */
	void enterPos_det(@NotNull UCGrammarParser.Pos_detContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#pos_det}.
	 * @param ctx the parse tree
	 */
	void exitPos_det(@NotNull UCGrammarParser.Pos_detContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#palavras}.
	 * @param ctx the parse tree
	 */
	void enterPalavras(@NotNull UCGrammarParser.PalavrasContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#palavras}.
	 * @param ctx the parse tree
	 */
	void exitPalavras(@NotNull UCGrammarParser.PalavrasContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#det}.
	 * @param ctx the parse tree
	 */
	void enterDet(@NotNull UCGrammarParser.DetContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#det}.
	 * @param ctx the parse tree
	 */
	void exitDet(@NotNull UCGrammarParser.DetContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#lista_complemento}.
	 * @param ctx the parse tree
	 */
	void enterLista_complemento(@NotNull UCGrammarParser.Lista_complementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#lista_complemento}.
	 * @param ctx the parse tree
	 */
	void exitLista_complemento(@NotNull UCGrammarParser.Lista_complementoContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#palavras_reservadas}.
	 * @param ctx the parse tree
	 */
	void enterPalavras_reservadas(@NotNull UCGrammarParser.Palavras_reservadasContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#palavras_reservadas}.
	 * @param ctx the parse tree
	 */
	void exitPalavras_reservadas(@NotNull UCGrammarParser.Palavras_reservadasContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#sentenca_logica}.
	 * @param ctx the parse tree
	 */
	void enterSentenca_logica(@NotNull UCGrammarParser.Sentenca_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#sentenca_logica}.
	 * @param ctx the parse tree
	 */
	void exitSentenca_logica(@NotNull UCGrammarParser.Sentenca_logicaContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#det_base}.
	 * @param ctx the parse tree
	 */
	void enterDet_base(@NotNull UCGrammarParser.Det_baseContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#det_base}.
	 * @param ctx the parse tree
	 */
	void exitDet_base(@NotNull UCGrammarParser.Det_baseContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#sentenca_enquanto}.
	 * @param ctx the parse tree
	 */
	void enterSentenca_enquanto(@NotNull UCGrammarParser.Sentenca_enquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#sentenca_enquanto}.
	 * @param ctx the parse tree
	 */
	void exitSentenca_enquanto(@NotNull UCGrammarParser.Sentenca_enquantoContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#sentenca_para}.
	 * @param ctx the parse tree
	 */
	void enterSentenca_para(@NotNull UCGrammarParser.Sentenca_paraContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#sentenca_para}.
	 * @param ctx the parse tree
	 */
	void exitSentenca_para(@NotNull UCGrammarParser.Sentenca_paraContext ctx);

	/**
	 * Enter a parse tree produced by {@link UCGrammarParser#sentenca_uc}.
	 * @param ctx the parse tree
	 */
	void enterSentenca_uc(@NotNull UCGrammarParser.Sentenca_ucContext ctx);
	/**
	 * Exit a parse tree produced by {@link UCGrammarParser#sentenca_uc}.
	 * @param ctx the parse tree
	 */
	void exitSentenca_uc(@NotNull UCGrammarParser.Sentenca_ucContext ctx);
}