package br.ufpi.dc.controle.etiquetador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

public class Etiquetador {

    private List<String> etiquetas = new ArrayList<String>();
    private List<String> palavras = new ArrayList<String>();
    private TreeTaggerWrapper<String> tt;
    public static HashMap<String, String> lemmas = new HashMap<String, String>(); //Veerificar o uso
    public static HashMap<String, String> hashPalavraDoLemma = new HashMap<String, String>(); //Veerificar o uso
    public static HashMap<String, String> hashEtiquetas = new HashMap<String, String>();
    public static HashMap<String, String> hashVerboParticipio;
    public static HashMap<String, String> hashVerboGerundio;

    private String pronomesPossesivos = "#meu#meus#minha#minhas#você";
    String demonstrativos = "#desse#desses#dessa#dessas#";
    String verbos = "#atualizar#enviar#cadastrar#reservar#definir#listar#associar#inserir#verificar#abrir#processar#efetuar#exportar#retornar#reconhecer#avançar#exibir#mudar#salvar#deletar#adicionar#excluir#acessar#deleta#deletar";
    String substantivos = "#data#numero#";
    int i = 0;

    public Etiquetador(List<String> palavras) {
        this.palavras = palavras;
    }

    public void etiquetar() {
        System.setProperty("treetagger.home", "./TreeTagger");
        hashVerboParticipio = new HashMap<>();
        hashVerboGerundio = new HashMap<>();

        tt = new TreeTaggerWrapper<>();

        try {
            tt.setModel("./tree-tagger-pt/lib/pt.par:UTF8");
            tt.setHandler(new TokenHandler<String>() {
                @Override
                public void token(String token, String pos, String lemma) {
                    if (palavras.get(i).equals("\n")) {
                        etiquetas.add("ENTER");
                        i++;
                    }
                    if (token.equals("_")) {
                        pos = "Fz";
                    }
                    if (pronomesPossesivos.contains("#" + token.toLowerCase() + "#")) {
                        pos = "P";
                    }
                    if (token.toLowerCase().contains("usuári")) {
                        pos = "NCMS";
                    }
                    if (token.toLowerCase().equals("acessa")) {
                        pos = "VMI";
                        lemma = "acessar";
                    }
                    if (token.toLowerCase().equals("marca")) {
                        pos = "VMI";
                        lemma = "marcar";
                    }
                    if (demonstrativos.contains("#" + token.toLowerCase() + "#")) {
                        pos = "SP+DA";
                        //TODO lemma = "acessar";
                    }
                    if (verbos.contains("#" + token.toLowerCase() + "#")) {
                        pos = "VMI";
                    }

                    if (token.toLowerCase().equals("anexa")) {
                        pos = "VMI";
                        lemma = "anexar";
                    }

                    if (token.toLowerCase().equals("deleta")) {
                        pos = "VMI";
                        lemma = "deletar";
                    }
                    if (token.toLowerCase().equals("data")) {
                        pos = "NCMP";
                        lemma = "data";
                    }
                    if (token.toLowerCase().equals("número")) {
                        pos = "NCMP";
                        lemma = "numero";
                    }
                    if (token.toLowerCase().equals("após")) {
                        pos = "SPS";
                        lemma = "após";
                    }
                    if (token.toLowerCase().equals("após")) {
                        pos = "SPS";
                        lemma = "após";
                    }
                    if (token.toLowerCase().equals("maria")) {
                        pos = "NCMP";
                        lemma = "maria";
                    }
                    if (pos.equals("VMP")) {
                        pos = "NCMP";
                        lemma = token.toLowerCase();
                        hashVerboParticipio.put(token, "VMP");
                    }

                    if (pos.equals("VMG")) {
                        pos = "NCMP";
                        lemma = token.toLowerCase();
                        hashVerboGerundio.put(token, "VMG");
                    }
                    if (token.toLowerCase().equals("errado") || token.toLowerCase().equals("correto")) {
                        pos = "NCMP";
                        lemma = token.toLowerCase();
                    }
                    if (pos.charAt(0) == 'V') {
                        Etiquetador.lemmas.put(lemma.toLowerCase(), lemma.toLowerCase());
                        Etiquetador.hashEtiquetas.put(lemma.toLowerCase(), pos);
                    }

                    etiquetas.add(pos);
                    i++;

                    Etiquetador.lemmas.put(token.toLowerCase(), lemma.toLowerCase());
                    Etiquetador.lemmas.put(lemma.toLowerCase(), lemma.toLowerCase());
                    
                    if (token.toLowerCase().equals("entrega")) {
                        Etiquetador.lemmas.put(token.toLowerCase(), "entrega");
                    }
                    
                    Etiquetador.hashEtiquetas.put(token.toLowerCase(), pos);
                    Etiquetador.hashEtiquetas.put(lemma.toLowerCase(), pos);

                    Etiquetador.hashPalavraDoLemma.put(lemma.toLowerCase(), token);
                }

            });
            tt.process(palavras);
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Model não encontrado");
        } catch (TreeTaggerException t) {
            System.out.println("Erro na etiquetagem");
        } finally {
            tt.destroy();
        }
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public static String getLemma(String palavra) {
        return lemmas.get(palavra.toLowerCase());
    }

    public List<String> getPalavras() {
        return palavras;
    }

    public static HashMap<String, String> getHashEtiquetas() {
        return hashEtiquetas;
    }

}
