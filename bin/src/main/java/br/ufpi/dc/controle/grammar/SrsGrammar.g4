grammar SrsGrammar;

@header {
package grammar;
         
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import tools.Constante;
import tools.ElementosFrase;

}


@members{
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

atributos:(atributo|outros_atributos)*;
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
outros_atributos: (ART|PRON|CONJ|{ conceito = "";} 
                                   ADJ 
                                 {testaConceito($ADJ.text);}
                                 {addConceito();}|
                   NUM|TERMINAL|PREP|SIMBOLOS|NUMERO|VERB|ADV);
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
                                  palavras?
                                  s1=SUBS{testaConceito($s1.text);}
                                  PREP? {addPreposicao($PREP.text);}
                                  (
                                  ADJ {testaConceito($ADJ.text);}
                                  |s2=SUBS{
                                        testaConceito($s2.text);
                                       }
                              )? {
                                  addConceito();
                                 };

//outros: (ART|PRON|CONJ|ADV|NUM|TERMINAL|PREP|SIMBOLOS|NUMERO);
outros: (ART|PRON|CONJ|ADV|NUM|TERMINAL|PREP|SIMBOLOS|NUMERO|ADJ|SUBS);
//        (ART|PRON|CONJ|ADV|NUM|TERMINAL|PREP|SIMBOLOS|NUMERO|ADJ)+;
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

o    : ART   ;
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
palavras: (ART|PREP|PRON|CONJ|ADV|NUM)*;

qualquer_coisa: (ART{temp = $ART.text;   System.out.println("Ultimo temp:  ART " + temp); testaConceito(temp);}          
                |PREP{temp = $PREP.text; System.out.println("Ultimo temp:  PREP " + temp); addPreposicao(temp);}
                |PRON{temp = $PRON.text; System.out.println("Ultimo temp:  PRON " + temp); testaConceito(temp);}
                |CONJ{temp = $CONJ.text;System.out.println("Ultimo temp:  CONJ " + temp); testaConceito(temp);}        
                |ADV{temp = $ADV.text;  System.out.println("Ultimo temp:  ADV " + temp); testaConceito(temp);}  
                |NUM{temp = $NUM.text;  System.out.println("Ultimo temp:  NUM " + temp); testaConceito(temp);}
                |SUBS{temp = $SUBS.text; System.out.println("Ultimo temp:  SUBS " + temp); testaConceito(temp);}        
                |ADJ{temp = $ADJ.text;   System.out.println("Ultimo temp:  ADJ " + temp); testaConceito(temp);}  
                |VERB{temp = $VERB.text; System.out.println("Ultimo temp:  VERB " + temp); testaConceito(temp);}
                |TERMINAL{temp = $TERMINAL.text; System.out.println("Ultimo temp:  TERMINAL " + temp); testaConceito(temp);}
                |SIMBOLOS{temp = $SIMBOLOS.text; System.out.println("Ultimo temp:  SIMBOLOS " + temp); testaConceito(temp);}
                |VIRGULA{temp = $VIRGULA.text;   System.out.println("Ultimo temp:  VIRGULA " + temp); testaConceito(temp);}  
                |SEPARADOR{temp = $SEPARADOR.text; System.out.println("Ultimo temp:  SEPARADOR " + temp); testaConceito(temp);}
                |DEVE{temp = $DEVE.text; System.out.println("Ultimo temp:  DEVE " + temp); testaConceito(temp);}
                )*{
                    adicionaConceitoTipado(Constante.QQQ);
                  }; 

inicio : p1 | f3;

p1               : osistemadeve sv palavras qualquer_coisa? PONTO{ if ($PONTO.text.equals(".")) {
                                                      proximo = "";
                                                    }
                                                  }; 


sv                   : {conceito =""; conceitoTokenizado="";} PERMITIR {
                                 setProximo($PERMITIR.text,"2 usuario");
                                 testaConceito($PERMITIR.text);
                                 adicionaConceitoTipado(Constante.PERMITIR);
                                 tipoFrase = 1;
                                 } f1
                     ; 

f1                   : palavras sn_usuario_f1 iteracao_f1*;    

iteracao_f1          : ({conceito =""; conceitoTokenizado="";} 
                        VERB {setProximo($VERB.text,"3 classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                              addConceitoAoElementoDaFrase("VERB"); //1
                             }
                        (iteracao_verbo* 
                         
                          palavras //Alterado em 14.04.2015.
                          sn_conceito_classe_f1
                          SEPARADOR?)
                         )+;

iteracao_verbo: (SEPARADOR{setProximo($SEPARADOR.text,"30 verbo");}| CONJ{setProximo($CONJ.text,"31 verbo");}) 
                         {conceito =""; conceitoTokenizado="";} 
                          VERB{setProximo($VERB.text,"31 verbo");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                              addConceitoAoElementoDaFrase("VERB_IT"); //2
                              ++iteracaoVerbo;
                             };

sn_usuario_f1        : det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"4 verbo");
                             testaConceito($s1.text);
                            } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS {testaConceito($s2.text);}
                        |
                        ADJ {testaConceito($ADJ.text);}
                       )?
                       {adicionaConceitoTipado(Constante.USUARIO);}
                     ;

sn_conceito_classe_f1: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"5 verbo");
                             testaConceito($s1.text);
                            } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS {testaConceito($s2.text);}
                         |
                         ADJ {testaConceito($ADJ.text);}
                       )?{
                          adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                          addConceitoAoElementoDaFrase("CONCEITO"); //1
                        };


f3: det (SISTEMA | sn_usuario_f3) DEVE  {setProximo($DEVE.text,"12 verbo");} palavras (iteracao_f3) qualquer_coisa?
    PONTO { if ($PONTO.text.equals(".")) {
               proximo = "";
            }
          }; 


iteracao_f3: {conceito ="";conceitoTokenizado="";}VERB {setProximo($VERB.text,"9 classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                              addConceitoAoElementoDaFrase("VERB"); //5
                              tipoFrase = 2;
                             }
             (iteracao_verbo*
             
               {conceito =""; conceitoTokenizado="";} 
                       palavras {setProximo(PALAVRAESPECIAL,"3 classe");
                       testaConceito(PALAVRAESPECIAL);}
                      
               sn_conceito_classe_f3 usuario_receptor? exp_temporal? 
              SEPARADOR?
             );

usuario_receptor : palavras {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"13 usuario_verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS {testaConceito($s2.text);}
                         |
                         ADJ {testaConceito($ADJ.text);}
                       )?{
                          adicionaConceitoTipado(Constante.USUARIO);//2
                         };       


exp_temporal:  palavras? sn_usuario_f4 {conceito ="";conceitoTokenizado="";}VERB {setProximo($VERB.text,"9 classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             } palavras sn_conceito_classe_f5;

sn_conceito_classe_f5: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"14 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS {testaConceito($s2.text);}
                         |
                         ADJ {testaConceito($ADJ.text);}
                       )?{
                          adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);//3
                          adicionaConceitoTipado(Constante.USUARIO);//4
                         };  

sn_usuario_f4: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"12 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS {testaConceito($s2.text);}
                        |
                        ADJ {testaConceito($ADJ.text);}
                       )?{
                           adicionaConceitoTipado(Constante.USUARIO);
                           adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);//10
                         };

sn_usuario_f3: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"10 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS {testaConceito($s2.text);}
                        |
                        ADJ {testaConceito($ADJ.text);}
                       )?{ adicionaConceitoTipado(Constante.USUARIO);
                         };

sn_conceito_classe_f3: {conceito ="";conceitoTokenizado="";}  
                       sub1 {setProximo($sub1.text,"11 usuario_classe");}
                       com_ou_sem_prep? 
                       {adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                        adicionaConceitoTipado(Constante.USUARIO);
                        addConceitoAoElementoDaFrase("CONCEITO1");//4
                        } 
                       { adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);//5
                       };                       

sub1            : SUBS  {testaConceito($SUBS.text);};

com_ou_sem_prep : com_prop | sem_prop;

com_prop        : PREP {addPreposicao($PREP.text);} 
                  (adjetivo | sub1);

sem_prop        :  adjetivo | sub1;

adjetivo:  ADJ {testaConceito($ADJ.text);};

conceitos_1:(conceito_1|verbo|outros_1)*;
conceito_1: {conceito = "";} s1=SUBS{testaConceito($s1.text);}
                          
                              PREP?{addPreposicao($PREP.text);}
                              (
                                 ADJ {testaConceito($ADJ.text);}
                                 |s2=SUBS{testaConceito($s2.text);}
                              )? 
                              {addConceito();
                                }
                         ;
substantivo: {conceito = "";} SUBS {testaConceito($SUBS.text);addConceito();};
verbo      : {conceito = "";} VERB {testaConceito($VERB.text);addConceito();};
outros_1:(ART|PREP|PRON|CONJ|ADV|NUM|TERMINAL|SIMBOLOS|NUMERO|SIMBOLOS|ADJ)+;