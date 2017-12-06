/*
 * Gramática para conduzir o analista
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar UCGrammar;
@header {
package UCGrammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import tools.Constante;
import tools.ElementosFrase;

}


@members{
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
                System.out.print(token    + " --> " + proximo + " | ");
                caminho = caminho + token + " --> " + proximo + " | ";
            }
    }
}
tokens {
    ART, ADJ, SUBS, VERB, PREP, PRON, CONJ, ADV,  NUM,  PALAVRAESTRANGEIRA, ENTAO
}

WS  :   ( ' ' 
        | '\t'  
        | '\r' 
        | '\n'
        ) {skip();} ;

fragment
LetrasAcentuadas  :'á'|'à'|'â'|'ã'|'é'|'ê'|'í'|'ó'|'ô'|'õ'|'ú'|'ü'|'ç'|'À'|'Á'|'Â'|'Ã'|'É'|'Ê'|'Í'|'Ó'|'Ô'|'Õ'|'Ú'|'Ü'|'Ç';

PALAVRA  :	('a'..'z'|'A'..'Z'|LetrasAcentuadas)+ ;

NUMERO   : '0'..'9';

SIMBOLOS :  '='|'+'|'*'|'/'|'\\'|'&'|'%'|'$'|'#'|'@'|'['|']'|'('|')'|'{'|'}'|'_'|'<'|'>'|'"'|'\''|'´'|'`'|'º'|'?'|'?'|'ª'|'±'|'Æ'|'§'|'"'|;

TRACO: '-';

TERMINAL : '?'|'!'|';'|':';

VIRGULA : ',';

//NOVALINHA:'\r'? '\n' ;

PONTO : '.';

//============================================================================================================
// Gramática para descrição de casos de uso.
//============================================================================================================

det : {conceito ="";} 
       det_base 
       pos_det?
      {addConceitoAoElementoDaFrase("DET");}
;
det_base: ART  {testaConceito($ART.text);}
        | PRON {testaConceito($PRON.text);}
        | NUM  {testaConceito($NUM.text);};

pos_det : NUM  {testaConceito($NUM.text);}
        | PRON {testaConceito($PRON.text);}
        ;

sentenca_uc: numero?  (sentenca_acao_usuario 
                       {conceito ="";}
                       PONTO {testaConceito($PONTO.text);}
                             {addConceitoAoElementoDaFrase("PONTO");}
                      |sentenca_condicional) 
             {conceito ="";} 
           ;

numero      :  {numero="";}(NUMERO{addNumero($NUMERO.text);})
               + (TERMINAL{addNumero($TERMINAL.text);} (NUMERO{addNumero($NUMERO.text);})?)*
             {addNumeroAoElementoDaFrase("NUM");}
            ;

sentenca_acao_usuario : 
                      det? {setProximo ($det.text,"90 usuario");}  
                      
                       s1=SUBS {setProximo($s1.text,"91 verbo");
                                testaConceito($s1.text);
                               } 
                          PREP? {addPreposicao($PREP.text);} 
                          (
                            s2=SUBS? {testaConceito($s2.text);}
                            |
                               ADJ? {testaConceito($ADJ.text);}
                            )
                      {addConceitoAoElementoDaFrase("SUJ");}
                      
                      {conceito ="";} 
                       palavras_uc_1? 
                      {addConceitoAoElementoDaFrase("PAC");}
                      {conceito ="";} 
                      
                      VERB { testaConceito($VERB.text);
                             setProximo($VERB.text,"92 confVerbo");
                             ultimoVerbo = $VERB.text;
                             addConceitoAoElementoDaFrase("VERBO");
                           }  

                      //alterar a gramática para a 
                      palavras_reservadas?
                      (conceitos_uc |caso_de_uso|palavras_uc)+
                      {tipoFrase = 1;}
              ;

caso_de_uso: {conceito = "";} VERB {testaConceito($VERB.text);}
             {elementosDaFrase.tipoElemento.add("VERBO");
              elementosDaFrase.elemento.add($VERB.text);}
             
             s1=SUBS{testaConceito($s1.text);}
                                  PREP? {addPreposicao($PREP.text);}
                                  (
                                  ADJ? {testaConceito($ADJ.text);}
                                  |s2=SUBS?{
                                        testaConceito($s2.text);
                                       }
                              ) 
                                {addConceitoAoElementoDaFrase("UC");}
                                {if (ok) {
                                           int j = elementosDaFrase.elemento.indexOf($VERB.text);
                                           elementosDaFrase.elemento.remove(j);
                                           elementosDaFrase.tipoElemento.remove(j);
                                           }
                                 };

conceitos_uc :  palavras_uc? conceito_uc lista_conceitos?
             ;

palavras_reservadas : {conceito ="";}
                      (PALAVRASRESERVADAS {testaConceito($PALAVRASRESERVADAS.text);})*
                      {addConceitoAoElementoDaFrase("PALRES");}   
                    ;


lista_conceitos : (palavras_uc?
                    ({conceito ="";}
                       VIRGULA? {testaConceito($VIRGULA.text);}
                               {addConceitoAoElementoDaFrase("VIRGULA");}
                     |
                     {conceito ="";}
                       CONJUNCAO? {testaConceito($CONJUNCAO.text);
                                  addConceitoAoElementoDaFrase("E");}
                    )
                det? 
                conceito_uc )* 
             ;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             

conceito_uc: det? {conceito ="";} 
                       s1=SUBS {testaConceito($s1.text);} 
                          PREP? {addPreposicao($PREP.text);} 
                          (
                           s2=SUBS? {testaConceito($s2.text);}
                           |
                              ADJ? {testaConceito($ADJ.text);}
                          )
                   {addConceitoAoElementoDaFrase("CCO1");}                          
           ;

palavras_uc:{complemento = "";}
           
            (   
                 {ultimoComplemento = "";} ART  {ultimoComplemento = $ART.text; addPalavraAoComplemento($ART.text);}
                |{ultimoComplemento = "";} PRON {ultimoComplemento = $PRON.text; addPalavraAoComplemento($PRON.text);}
                |{ultimoComplemento = "";} CONJ {ultimoComplemento = $CONJ.text; addPalavraAoComplemento($CONJ.text);}
                |{ultimoComplemento = "";} ADV  {ultimoComplemento = $ADV.text; addPalavraAoComplemento($ADV.text);}
                |{ultimoComplemento = "";} NUM  {ultimoComplemento = $NUM.text; addPalavraAoComplemento($NUM.text);}
                |{ultimoComplemento = "";} PREP {ultimoComplemento = $PREP.text; addPalavraAoComplemento($PREP.text);}
                |{ultimoComplemento = "";} ADJ {ultimoComplemento = $ADJ.text; addPalavraAoComplemento($ADJ.text);}
                |{ultimoComplemento = "";} CONJUNCAO {ultimoComplemento = $CONJUNCAO.text; addPalavraAoComplemento($CONJUNCAO.text);}
                |{ultimoComplemento = "";} VIRGULA {ultimoComplemento = $VIRGULA.text;}
                |{ultimoComplemento = "";} ART {ultimoComplemento = $ART.text;}
            )+;

palavras_uc_1:{complemento = "";}
           
            (   
                 {ultimoComplemento = "";} ART  {ultimoComplemento = $ART.text; addPalavraAoComplemento($ART.text);}
                |{ultimoComplemento = "";} PRON {ultimoComplemento = $PRON.text; addPalavraAoComplemento($PRON.text);}
                |{ultimoComplemento = "";} CONJ {ultimoComplemento = $CONJ.text; addPalavraAoComplemento($CONJ.text);}
                |{ultimoComplemento = "";} ADV  {ultimoComplemento = $ADV.text; addPalavraAoComplemento($ADV.text);}
                |{ultimoComplemento = "";} NUM  {ultimoComplemento = $NUM.text; addPalavraAoComplemento($NUM.text);}
                |{ultimoComplemento = "";} CONJUNCAO {ultimoComplemento = $CONJUNCAO.text; addPalavraAoComplemento($CONJUNCAO.text);}
                |{ultimoComplemento = "";} VIRGULA {ultimoComplemento = $VIRGULA.text;}
                |{ultimoComplemento = "";} ART {ultimoComplemento = $ART.text;}
            )+;

//Definição da sentença condicional
//A sentença condicional está errada pois deve esperar uma expressão lógica ou uma frase com uma ação.
sentenca_condicional: {conceito ="";} 
                      SE {testaConceito($SE.text);} 
                      {addConceitoAoElementoDaFrase("SE");}    
                      {tipoFrase = 2;}
                      sentenca_logica 
                      {conceito ="";}
                      ENTAO {testaConceito($ENTAO.text);}
                      {addConceitoAoElementoDaFrase("ENTAO");}    
                    ;


sentenca_logica: expressao (CONJUNCAO {setProximo($CONJUNCAO.text,"96 usuario_classe_atributo");} expressao)*;

expressao: det?
               {setProximo($det.text,"93 usuario_classe_atributo");
               conceito ="";} 
                    s1=SUBS {setProximo($s1.text,"92 verbo");
                                testaConceito($s1.text);
                               }
                       PREP? {addPreposicao($PREP.text);} 
                   (s2=SUBS? {testaConceito($s2.text);} | ADJ? {testaConceito($ADJ.text);})
           
           {addConceitoAoElementoDaFrase("E1");}

           {conceito =""; } 
                       VERB { testaConceito($VERB.text);
                              setProximo($VERB.text,"94 confVerbo");
                              ultimoVerbo = $VERB.text;
                              addConceitoAoElementoDaFrase("VEL");
                            }
           
           sinal? det?
           {conceito ="";} 
                    s1=SUBS {testaConceito($s1.text);} 
                       PREP? {addPreposicao($PREP.text);} 
                   (s2=SUBS? {testaConceito($s2.text);} | ADJ? {testaConceito($ADJ.text);})
           
          {setProximo($s1.text,"95 e_ou_entao");
           addConceitoAoElementoDaFrase("E2");}
           
        ;

sinal :  {conceito ="";} 
         c1=COMPARADOR {testaConceito($c1.text);}
         c2=COMPARADOR? {testaConceito($c2.text);}
         QUE? {testaConceito($QUE.text);}
         {addConceitoAoElementoDaFrase("SIN");}
         {conceito="";}
      ;

