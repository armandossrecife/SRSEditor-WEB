/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar newEmptyCombinedGrammar;
DETERMINANTE:'A';
SISTEMA:'A';
VERBOPARAO :'A';
PERMITIR:'A';
VERBO:'A';
PREPOSICAO:'A';
ADJETIVO:'A';
SUBSTANTIVO:'A';

sentenca                   : padrao_1 usuario (acao requisito_de_armazenamento)+
                           ;
padrao_1                   : DETERMINANTE SISTEMA VERBOPARAO PERMITIR
                           ;
usuario                    : locucao_substantiva
                           ;
acao                       : VERBO
                           ;
requisito_de_armazenamento : locucao_substantiva
                           ;
locucao_substantiva        : SUBSTANTIVO PREPOSICAO? (SUBSTANTIVO? | ADJETIVO?)
                           ;









