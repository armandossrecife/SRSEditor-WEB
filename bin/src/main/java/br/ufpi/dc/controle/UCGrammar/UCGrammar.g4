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

SIMBOLOS :  '='|'+'|'*'|'/'|'\\'|'&'|'%'|'$'|'#'|'@'|'['|']'|'('|')'|'{'|'}'|'_'|'<'|'>'|'\''|'´'|'`'|'º'|'?'|'?'|'ª'|'±'|'Æ'|'§';

TRACO: '-';

TERMINAL : '?'|'!'|';'|':';

VIRGULA : ',';

//NOVALINHA:'\r'? '\n' ;

PONTO : '.';
ASPAS : '"';

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

sentenca_uc: numero{numeroSentenca = numero;}
                   {addNumeroAoElementoDaFrase("NUM");}
                   {conceito ="";}
                   (
                       sentenca_acao_usuario 
                       {conceito ="";}
                       PONTO {testaConceito($PONTO.text);}
                             {addConceitoAoElementoDaFrase("PONTO");}
                      |sentenca_condicional
                      | SENAO {tipoFrase=3; //Nao utilizado
                               }
                              {testaConceito($SENAO.text);}                        
                              {addConceitoAoElementoDaFrase("SENAO");}                          

                      |sentenca_para
                      |sentenca_enquanto
                   ) 
             {conceito ="";} 
           ;

sentenca_enquanto : {conceito ="";} 
                    ENQUANTO  
                      {testaConceito($ENQUANTO.text);} 
                      {addConceitoAoElementoDaFrase("ENQUANTO");}    
                      {tipoFrase = 5;}
                      sentenca_logica 
                    ;

sentenca_para : {conceito ="";}
                PARA { testaConceito($PARA.text);
                       addConceitoAoElementoDaFrase("PARA");
                       tipoFrase = 4;
                     }
                det?
                qualquer_coisa_para
;

numero      :  {numero="";}(NUMERO{addNumero($NUMERO.text);})
                (PONTO{addNumero($PONTO.text);} | NUMERO{addNumero($NUMERO.text);})*
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
                             if (!$VERB.text.contains("<missing")){
                                 tipoFrase = 1;
                                 addConceitoAoElementoDaFrase("VERBO");
                                 elementosDaFrase.verbos.add($VERB.text);
                                 elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
                             }
                             
                           }  

                      complementos 

              ;

complementos:  palavras_reservadas?  qualquer_coisa lista_complemento*
//               rever a gramatica para que os complementos sejam qualquer coisa separada por vígula ou conjuções
              ;

lista_complemento: ({conceito ="";}
                       VIRGULA? //{testaConceito($VIRGULA.text);}
                                //{addConceitoAoElementoDaFrase("VIRGULA");}
                     |
                     {conceito ="";}
                       CONJUNCAO? //{testaConceito($CONJUNCAO.text);
                                  //addConceitoAoElementoDaFrase("E");}
                     )  qualquer_coisa palavras*  {addConceitoAoElementoDaFrase("ASPAS");}
                 ;

qualquer_coisa: {complemento = "";}
            (   
                {ultimoComplemento = "";}  PRON {ultimoComplemento = $PRON.text; addPalavraAoComplemento($PRON.text);}
                |{ultimoComplemento = "";} ADV  //{ultimoComplemento = $ADV.text; addPalavraAoComplemento($ADV.text);}
                |{ultimoComplemento = "";} VERB {
                                                  ultimoComplemento = $VERB.text; 
                                                  addPalavraAoComplemento($VERB.text);
                                                  elementosDaFrase.verbos.add($VERB.text);
                                                  elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
                                                 }
                 
                |{ultimoComplemento = "";} SUBS  {ultimoComplemento = $SUBS.text; addPalavraAoComplemento($SUBS.text);}
                |{ultimoComplemento = "";} NUM  {ultimoComplemento = $NUM.text; addPalavraAoComplemento($NUM.text);}
                |{ultimoComplemento = "";} PREP {ultimoComplemento = $PREP.text; 
                                                 if (!conceito.equals("")){
                                                    addPalavraAoComplemento($PREP.text);
                                                 }
                                                 }
                |{ultimoComplemento = "";} ADJ {ultimoComplemento = $ADJ.text; addPalavraAoComplemento($ADJ.text);}
                |{ultimoComplemento = "";} ART {ultimoComplemento = $ART.text;}
                |numero {ultimoComplemento = numero; addPalavraAoComplemento(numero);}
                
            )+                    
            {addConceitoAoElementoDaFrase("CCO1");}                          
            ;

qualquer_coisa_para: {complemento = "";}
                
                (   
                {ultimoComplemento = "";}  PRON {ultimoComplemento = $PRON.text; addPalavraAoComplemento($PRON.text);}
                |{ultimoComplemento = "";} ADV  {ultimoComplemento = $ADV.text; addPalavraAoComplemento($ADV.text);}
                |{ultimoComplemento = "";} VERB {
                                                  ultimoComplemento = $VERB.text; 
                                                  elementosDaFrase.verbos.add($VERB.text);
                                                  elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
                                                 }
                 
                |{ultimoComplemento = "";} SUBS  {ultimoComplemento = $SUBS.text; addPalavraAoComplemento($SUBS.text);}
                |{ultimoComplemento = "";} NUM  {ultimoComplemento = $NUM.text; addPalavraAoComplemento($NUM.text);}
                |{ultimoComplemento = "";} PREP {ultimoComplemento = $PREP.text; 
                                                 if (!conceito.equals("")){
                                                    addPalavraAoComplemento($PREP.text);
                                                 }
                                                 }
                |{ultimoComplemento = "";} ADJ {ultimoComplemento = $ADJ.text; addPalavraAoComplemento($ADJ.text);}
                |{ultimoComplemento = "";} ART {ultimoComplemento = $ART.text;}
                |numero {ultimoComplemento = numero; addPalavraAoComplemento(numero);}
                
            )+                    
            {addConceitoAoElementoDaFrase("CCO1");}                          
            ;
palavras: {ultimoComplemento = "";} PALAVRA {ultimoComplemento = $PALAVRA.text; addPalavraAoComplemento($PALAVRA.text);};

palavras_reservadas : {conceito ="";}
                      (PALAVRASRESERVADAS {testaConceito($PALAVRASRESERVADAS.text);})+
                      {addConceitoAoElementoDaFrase("PALRES");}   
                    ;

palavras_uc_1:{complemento = "";}
            (   
                 {ultimoComplemento = "";} ART  {ultimoComplemento = $ART.text; addPalavraAoComplemento($ART.text);}
                |{ultimoComplemento = "";} PRON {ultimoComplemento = $PRON.text; addPalavraAoComplemento($PRON.text);}
                |{ultimoComplemento = "";} CONJ {ultimoComplemento = $CONJ.text; addPalavraAoComplemento($CONJ.text);}
                |{ultimoComplemento = "";} ADV  {ultimoComplemento = $ADV.text; addPalavraAoComplemento($ADV.text);}
                |{ultimoComplemento = "";} NUM  {ultimoComplemento = $NUM.text; addPalavraAoComplemento($NUM.text);}
                //|{ultimoComplemento = "";} CONJUNCAO {ultimoComplemento = $CONJUNCAO.text; addPalavraAoComplemento($CONJUNCAO.text);}
                //|{ultimoComplemento = "";} VIRGULA {ultimoComplemento = $VIRGULA.text;}
                |{ultimoComplemento = "";} ART {ultimoComplemento = $ART.text;}
                |numero {ultimoComplemento = numero; addPalavraAoComplemento(numero);}
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

sentenca_logica: expressao? (CONJUNCAO {
                                       conceito =""; 
                                       setProximo($CONJUNCAO.text,"96 usuario_classe_atributo");
                                       testaConceito($CONJUNCAO.text);
                                       addConceitoAoElementoDaFrase("EOU");
                                       } expressao)*;

expressao: 
           {conceito =""; } 
           e1?
           {addConceitoAoElementoDaFrase("E1");}
           {conceito =""; } 
                       VERB { testaConceito($VERB.text);
                              setProximo($VERB.text,"94 confVerbo");
                              ultimoVerbo = $VERB.text;
                              addConceitoAoElementoDaFrase("VERBSE");
                              elementosDaFrase.verbos.add($VERB.text);
                              elementosDaFrase.posVerbo.add(elementosDaFrase.elemento.size()-1);
                            }
           
           palavras_reservadas? palavras*  {addConceitoAoElementoDaFrase("ASPAS");}
              
           {conceito ="";} 
           (e1
           {setProximo($e1.text,"95 e_ou_entao");
           addConceitoAoElementoDaFrase("E2");}
           |
            sentenca_logica
           )
        ;
e1:{complemento = "";}
            (   
                {ultimoComplemento = "";}  PRON {ultimoComplemento = $PRON.text; addPalavraAoComplemento($PRON.text);}
                |{ultimoComplemento = "";} SUBS  {ultimoComplemento = $SUBS.text; addPalavraAoComplemento($SUBS.text);}
                |{ultimoComplemento = "";} NUM  {ultimoComplemento = $NUM.text; addPalavraAoComplemento($NUM.text);}
                |{ultimoComplemento = "";} PREP {ultimoComplemento = $PREP.text; 
                                                 if (!conceito.equals("")){
                                                    addPalavraAoComplemento($PREP.text);
                                                 }
                                                 }
                |{ultimoComplemento = "";} ADJ {ultimoComplemento = $ADJ.text; addPalavraAoComplemento($ADJ.text);}
                | ADV 
                | ART 
                |numero {ultimoComplemento = numero; addPalavraAoComplemento(numero);}
            )+                    
            ;

conceito_1: {conceito = "";} s1=SUBS{testaConceito($s1.text);}
                          
                              PREP?{addPreposicao($PREP.text);}
                              (
                                 ADJ {testaConceito($ADJ.text);}
                                 |s2=SUBS{testaConceito($s2.text);}
                              ) /*s3=SUBS?{
                                        testaConceito($s3.text);} Reavaliar porque fiz isso*/
                              {addConceito();
                                 int atual = getTokenStream().index()-1;
                                 getTokenStream().seek(atual);                            
                                }
                         ;
