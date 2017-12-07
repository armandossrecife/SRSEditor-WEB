grammar IntellisenseGrammar;

@header {
package intellisense.grammar;
         
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
   
  
}

tokens {
    ART, ADJ, SUBS, VERB, PREP, PRON, CONJ, ADV,  NUM,  PALAVRAESTRANGEIRA 

}
WS  :   ( ' ' 
        | '\t'  
       | '\r' 
       | '\n'
        ) {skip();};

fragment
LetrasAcentuadas  :'á'|'à'|'â'|'ã'|'é'|'ê'|'í'|'ó'|'ô'|'õ'|'ú'|'ü'|'ç'|'À'|'Á'|'Â'|'Ã'|'É'|'Ê'|'Í'|'Ó'|'Ô'|'Õ'|'Ú'|'Ü'|'Ç';

PALAVRA  :	('a'..'z'|'A'..'Z'|LetrasAcentuadas)+ ;

NUMERO   : '0'..'9';

SIMBOLOS :  '='|'+'|'*'|'/'|'\\'|'&'|'%'|'$'|'#'|'@'|'['|']'|'('|')'|'{'|'}'|'_'|'<'|'>'|'"'|'\''|'´'|'`'|'º'|'?'|'?'|'ª'|'±'|'Æ'|'§'|'"'|;

TRACO: '-';

TERMINAL : '.'|'?'|'!'|';'|':';

VIRGULA : ',';

MODELO: (('a'..'z'|'A'..'Z') '0'..'9')+;

// Gramática para reconhecimento de funcionlidades V3.
// Reconhece os dois tipos de frases definidas para descrever funcionalidades

osistemadeve: o sist deve? ;

o    : ART  ;
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

inicio : p1 | f3;


p1               : osistemadeve sv palavras PONTO{ if ($PONTO.text.equals(".")) {
                                                      proximo = "";
                                                    }
                                                  }; 

sv                   : {conceito =""; conceitoTokenizado="";} PERMITIR {
                                 setProximo($PERMITIR.text,"2 usuario");
                                 testaConceito($PERMITIR.text);
                                 adicionaConceitoTipado(Constante.PERMITIR);
                                 } f1
                     ; 

f1                   : palavras sn_usuario_f1 iteracao_f1;    

iteracao_f1          : ({conceito =""; conceitoTokenizado="";} 
                        VERB {setProximo($VERB.text,"3 classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             }
                        (iteracao_verbo* 
                         
                          {conceito =""; conceitoTokenizado="";} 
                          palavras {setProximo($palavras.text,"3 classe");
                              testaConceito($palavras.text);
                             } 
                          sn_conceito_classe_f1
                          SEPARADOR?)
                         )+;

iteracao_verbo: (SEPARADOR{setProximo($SEPARADOR.text,"30 verbo");}| CONJ{setProximo($CONJ.text,"31 verbo");}) 
                         {conceito =""; conceitoTokenizado="";} 
                          VERB{setProximo($VERB.text,"31 verbo");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             };

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
                       s1=SUBS {setProximo ($s1.text,"5 verbo");
                                testaConceito($s1.text);
                                } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);};

f3: det (SISTEMA | sn_usuario_f3) DEVE  {setProximo($DEVE.text,"12 verbo");} palavras (iteracao_f3)+
    PONTO { if ($PONTO.text.equals(".")) {
               proximo = "";
            }
          }; 


iteracao_f3: {conceito ="";conceitoTokenizado="";}VERB {setProximo($VERB.text,"9 classe");
                              testaConceito($VERB.text);
                              adicionaConceitoTipado(Constante.FUNCAODOPRODUTO);
                             }
             (iteracao_verbo*
             
               {conceito =""; conceitoTokenizado="";} 
                       palavras {setProximo($palavras.text,"3 classe");
                       testaConceito($palavras.text);}
                       
               sn_conceito_classe_f3 palavras? sn_conceito_classe_f4 exp_temporal? 
              SEPARADOR?
             );










sn_usuario_f4: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"121 verbo");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                       (
                        s2=SUBS? {testaConceito($s2.text);}
                        |
                        ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.USUARIO);};



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
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);};  


sn_conceito_classe_f4: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"13 usuario");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);};       


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

sn_conceito_classe_f3: det? {conceito ="";conceitoTokenizado="";} 
                       s1=SUBS {setProximo   ($s1.text,"11 usuario");
                                testaConceito($s1.text);
                               } 
                       PREP? {addPreposicao($PREP.text);} 
                        (
                         s2=SUBS? {testaConceito($s2.text);}
                         |
                         ADJ? {testaConceito($ADJ.text);}
                       ){adicionaConceitoTipado(Constante.REQUISITOS_DE_ARMAZENAMENTO);};        


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
