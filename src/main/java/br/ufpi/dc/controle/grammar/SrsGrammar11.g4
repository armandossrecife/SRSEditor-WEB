grammar SrsGrammar;

@header {
package grammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import tools.Constante;

}


@members{
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
         conceitos.add(conceito);
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
   
  
}

tokens {
    ART, ADJ, SUBS, VERB, PREP, PRON, CONJ, ADV,  NUM,  PALAVRAESTRANGEIRA 

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

TERMINAL : '.'|'?'|'!'|';'|':';

VIRGULA : ',';

MODELO: (('a'..'z'|'A'..'Z') '0'..'9')+;

// Padrões para reconhecer conceitos importantes

atributos:(atributo|outros)*;
atributo: {conceito = "";}s1=SUBS{testaConceito($s1.text);}
                          
                              PREP?{addPreposicao($PREP.text);}
                              (
                                 ADJ {addPreposicao($ADJ.text);}
                                 |s2=SUBS{
                                        addPreposicao($s2.text);
                                         
                                       }
                              )? 
                              {addConceito();}
                         ;
conceitos:(conceito|outros)*;
conceito: {conceito = "";}s1=SUBS{testaConceito($s1.text);}
                          
                              PREP?{addPreposicao($PREP.text);}
                              (
                                 ADJ {testaConceito($ADJ.text);}
                                 |s2=SUBS{
                                        testaConceito($s2.text);
                                         
                                       }
                              ) 
                              {addConceito();}
                         ;

funcionalidades:(funcionalidade|outros)*;
funcionalidade:  {conceito = "";} VERB {testaConceito($VERB.text);}
                                  s1=SUBS{testaConceito($s1.text);}
                                  PREP? {addPreposicao($PREP.text);}
                                  (
                                  ADJ? {testaConceito($ADJ.text);}
                                  |s2=SUBS?{
                                        testaConceito($s2.text);
                                       }
                              ) {addConceito();};

outros: (ART {temp = $ART.text;}
        |PRON{temp = $PRON.text;}
        |CONJ {temp = $CONJ.text;} 
        |ADV {temp = $ADV.text;}
        |NUM{temp = $NUM.text;}      
        |PREP{temp = $PREP.text;}
        |NUMERO  {temp = $NUMERO.text;} 
        |TERMINAL{temp = $TERMINAL.text;}
        |SIMBOLOS{temp = $SIMBOLOS.text;}
        |VIRGULA{temp = $VIRGULA.text;}  
        |SEPARADOR{temp = $SEPARADOR.text;});

// ============================= Gramática para gerar as estatísticas para construção do léxico do domínio =========
init : (PALAVRA {addPalavra($PALAVRA.text);} 
       | TERMINAL { 
                    addPalavra($TERMINAL.text);
                    oracoes.add(oracao+"\n");
                    oracao = "";
                    }
       |VIRGULA {addPalavra($VIRGULA.text);}
)*; 

// Gramática para reconhecimento de funcionlidades V3.
// Reconhece os dois tipos de frases definidas para descrever funcionalidades

osistemadeve: o sist deve? ;

o    : ART;
sist : {conceito =""; conceitoTokenizado="";} 
        SISTEMA 
        {setProximo($SISTEMA.text,"1 DEVE");
         testaConceito($SISTEMA.text);
         adicionaConceitoTipado(Constante.SISTEMA);
        };

deve : {conceito =""; conceitoTokenizado="";}
        DEVE {setProximo($DEVE.text, "2 verbo");
              testaConceito($DEVE.text);
              adicionaConceitoTipado(Constante.DEVE);
             };

det : det_base pos_det?;
det_base: ART | PRON | NUM;
pos_det : NUM | PRON;

palavras: (ART{temp = $ART.text;    System.out.println("Ultimo temp:  ART" + temp);}
           |PREP{temp = $PREP.text; System.out.println("Ultimo temp  PREP: " + temp);}
           |PRON{temp = $PRON.text; System.out.println("Ultimo temp  PRON: " + temp);}
           |CONJ{temp = $CONJ.text; System.out.println("Ultimo temp  CONJ: " + temp);}
           |ADV {temp = $ADV.text;  System.out.println("Ultimo temp  ADV: " + temp);}
           |NUM {temp = $NUM.text;  System.out.println("Ultimo temp: NUM" + temp);}
          )*;

qualquer_coisa: (ART{temp = $ART.text;   System.out.println("Ultimo temp:  ART" + temp);}          
                |PREP{temp = $PREP.text; System.out.println("Ultimo temp:  PREP" + temp);}
                |PRON{temp = $PRON.text; System.out.println("Ultimo temp:  PRON" + temp);}
                 |CONJ{temp = $CONJ.text;System.out.println("Ultimo temp:  CONJ" + temp);}        
                 |ADV{temp = $ADV.text;  System.out.println("Ultimo temp:  ADV" + temp);}  
                 |NUM{temp = $NUM.text;  System.out.println("Ultimo temp:  NUM" + temp);}
                 |SUBS{temp = $SUBS.text; System.out.println("Ultimo temp:  SUBS" + temp);}        
                 |ADJ{temp = $ADJ.text;   System.out.println("Ultimo temp:  ADJ" + temp);}  
                 |VERB{temp = $VERB.text; System.out.println("Ultimo temp:  VERB" + temp);}
                 |TERMINAL{temp = $TERMINAL.text; System.out.println("Ultimo temp:  TERMINAL" + temp);}
                 |SIMBOLOS{temp = $SIMBOLOS.text; System.out.println("Ultimo temp:  SIMBOLOS" + temp);}
                 |VIRGULA{temp = $VIRGULA.text;   System.out.println("Ultimo temp:  VIRGULA" + temp);}  
                 |SEPARADOR{temp = $SEPARADOR.text; System.out.println("Ultimo temp:  SEPARADOR" + temp);}
                )*;
                  
inicio : p1 | f3; 


p1               : osistemadeve sv PONTO { if ($PONTO.text.equals(".")) {
                                                      proximo = "";
                                                    }
                                                  }; 

sv                   : {conceito =""; conceitoTokenizado="";} PERMITIR {
                                 setProximo($PERMITIR.text,"2 usuario");
                                 testaConceito($PERMITIR.text);
                                 adicionaConceitoTipado(Constante.PERMITIR);
                                 } f1
                       |
                       (iteracao_f2)+
                     ; 

f1                   : palavras sn_usuario_f1 (iteracao_f1)+;    

iteracao_f1          : ({conceito =""; conceitoTokenizado="";} 
                        VERB {setProximo($VERB.text,"3 conceito_classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             }
                       //palavras //Alterado em 14.04.2015.
                       det //Alterado em 01.12.2015.
                       sn_conceito_classe_f1
                       SEPARADOR?)+;

sn_usuario_f1        : det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"4 verbo");
                             testaConceito($s1.text);
                            } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS? {testaConceito($s2.text);}
                        |
                        ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.USUARIO);};

sn_conceito_classe_f1: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"5 verbo");
                             testaConceito($s1.text);
                            } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.CONCEITO_CLASSE);};


iteracao_f2          : {conceito ="";conceitoTokenizado="";}  
                        VERB {setProximo($VERB.text,"6 conceito_classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                              }
                       sn_conceito_classe_f2 
                       palavras? 
                       sn_usuario_f2? SEPARADOR?; 

sn_usuario_f2        : det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"7 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS? {testaConceito($s2.text);}
                        |
                        ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.USUARIO);};

sn_conceito_classe_f2: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"8 usuario");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.CONCEITO_CLASSE);};

f3: det sn_usuario_f3 DEVE  {setProximo($DEVE.text,"12 verbo");} palavras (iteracao_f3)+ qualquer_coisa?
    PONTO { if ($PONTO.text.equals(".")) {
               proximo = "";
            }
          }; 

iteracao_f3: {conceito ="";conceitoTokenizado="";}VERB {setProximo($VERB.text,"9 conceito_classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             }
             sn_conceito_classe_f3 palavras? sn_conceito_classe_f4 exp_temporal? 
             SEPARADOR?;

exp_temporal:  palavras? sn_usuario_f4 {conceito ="";conceitoTokenizado="";}VERB {setProximo($VERB.text,"9 conceito_classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             } palavras sn_conceito_classe_f5;

sn_usuario_f3: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"10 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS? {testaConceito($s2.text);}
                        |
                        ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.USUARIO);};

sn_usuario_f4: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"12 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS? {testaConceito($s2.text);}
                        |
                        ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.USUARIO);};

sn_conceito_classe_f5: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"14 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.CONCEITO_CLASSE);};  

sn_conceito_classe_f4: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"13 usuario_verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.CONCEITO_CLASSE);};       

sn_conceito_classe_f3: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"11 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.CONCEITO_CLASSE);};        

//============================== Gramática para detectar o tipo de frase =======================================
frase: tipo_frase_1 (PALAVRAS)*|tipo_frase_2 (PALAVRAS)*|tipo_frase_3 (PALAVRAS)*;
tipo_frase_1:ART? {addPalavra($ART.text);} SISTEMA{addPalavra($SISTEMA.text);} DEVE? {addPalavra($DEVE.text);} PERMITIR {addPalavra($PERMITIR.text);} {tipoFrase = 1;};
tipo_frase_2:ART? {addPalavra($ART.text);} SISTEMA{addPalavra($SISTEMA.text);} DEVE? {addPalavra($DEVE.text);} VERB     {addPalavra($VERB.text);}{tipoFrase = 2;};
tipo_frase_3:ART? {addPalavra($ART.text);} s1=SUBS {addPalavra($s1.text);} PREP?{addPalavra($PREP.text);} (s2=SUBS?{addPalavra($s2.text);}|ADJ?{addPalavra($ADJ.text);}) DEVE{addPalavra($DEVE.text);} palavras VERB{addPalavra($VERB.text);} s3=SUBS {addPalavra($s3.text);} prep2=PREP?{addPalavra($prep2.text);} (s4=SUBS?{addPalavra($s4.text);}|adj1=ADJ?{addPalavra($adj1.text);}) {tipoFrase = 3;};
tipo_frase_4:ART? {addPalavra($ART.text);} SISTEMA {addPalavra($SISTEMA.text);}  PERMITIR {addPalavra($PERMITIR.text);} {tipoFrase = 4;};

//=================================== Gramática para recuperar conceitos da sentenca ============================
conceitos_1:(conceito_1|substantivo|verbo|outros_1)*;

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
substantivo: {conceito = "";} SUBS {testaConceito($SUBS.text);addConceito();};

verbo      : {conceito = "";} VERB {testaConceito($VERB.text);addConceito();};

outros_1:(ART|PREP|PRON|CONJ|ADV|NUM|TERMINAL|SIMBOLOS|NUMERO|SIMBOLOS)+;
