/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.importar;

import br.ufpi.dc.dao.JPAUtil;
import br.ufpi.dc.tools.Constante;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.EntityManager;
//import visao.view.tools.Constante;

/**
 *
 * @author helcio.soares
 */
public class Importador {

    private ArrayList<String> arquivos = new ArrayList<>();

    public Importador() {
        arquivos.add("srs_m_verbo");
        arquivos.add("srs_m_tipo_tabela");
        arquivos.add("srs_m_tipo_elemento_interface");
        arquivos.add("srs_m_tipo_conceito");
        arquivos.add("srs_m_classe_gramatical");
        arquivos.add("srs_m_gramatica");
        arquivos.add("srs_m_elemento_de_interface");
        arquivos.add("srs_m_mensagem_erro");
        arquivos.add("srs_m_proximo");
        arquivos.add("srs_m_sinonimo");
        arquivos.add("srs_m_stop_words");
        arquivos.add("srs_m_projeto");
        arquivos.add("srs_m_referencia");
        arquivos.add("srs_m_sinonimo");
        arquivos.add("srs_m_lemma");
        arquivos.add("srs_m_radical");
        arquivos.add("srs_m_palavra");
        arquivos.add("srs_m_conceito");
        arquivos.add("srs_m_dados");
        arquivos.add("srs_m_dado_radical");
        arquivos.add("srs_m_acao");
//        arquivos.add("srs_m_sinonimo_dominio");
//        arquivos.add("srs_m_tabela");
//        arquivos.add("srs_m_caso_de_uso");
//        arquivos.add("srs_m_isr");
//        arquivos.add("srs_m_atributo");
//        arquivos.add("srs_m_introducao");
    }

    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    private Long contarTodos(Class classe) {
        EntityManager em = new JPAUtil().getEntityManager();
        Long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n").getSingleResult();
        return result;
    }

    public void importarTabela(String arquivo, Class classe) throws IOException {
        EntityManager em = new JPAUtil().getEntityManager();

        if (Objects.equals(contarTodos(classe), Long.valueOf("0"))) {
            String sql = readFile(arquivo, StandardCharsets.UTF_8);
            String[] linhas = sql.split("\n");
            em.clear();
            em.getTransaction().begin();
            for (String linha : linhas) {
                String trimmedLine = linha.trim();
                if (trimmedLine.startsWith("INSERT")) {
                    StringBuilder linhaStringBuilder = new StringBuilder(linha);
                    int i = linha.indexOf("VALUES");
                    String nomeClasse = classe.getSimpleName();
                    //String campos = Constante.hashTabelas.get(nomeClasse);
                    String campos="";       
                    linha = linhaStringBuilder.insert(i, " " + campos +" ").toString();
                    em.createNativeQuery(linha).executeUpdate();
                    System.out.println(linha);
                }
            }

            em.getTransaction().commit();
        }
    }

    public void importarTabelasIniciais() throws IOException, ClassNotFoundException {
        for (String arquivo : arquivos) {
            String nomeClasse = retornarNomeClasse(arquivo);
            Class classe = null;
            try {
                classe = Class.forName("entidades.entity.tools." + nomeClasse);
            } catch (Exception e) {
                classe = Class.forName("entidades.entity." + nomeClasse);
            }
            System.out.println(nomeClasse);
            //modificar para deixar portavel essa parte
            String sep = System.getProperty(Constante.FILE_SEPARATOR);
            //System.out.println(System.getProperty(Constante.USER_DIRECTORY));
            String diretorioatual = System.getProperty(Constante.USER_DIRECTORY);
            importarTabela(diretorioatual+ sep + "importar" + sep +  arquivo + ".sql", classe);
        }

    }
/**
 * 
 * @param nomeArquivo
 * @return o nome da classe no stilo camelcase
 */
    private String retornarNomeClasse(String nomeArquivo) {
        String temp = "";
        nomeArquivo = nomeArquivo.substring(5, nomeArquivo.length());
        int i = 0;
        while (i <= nomeArquivo.length() - 1) {
            if (nomeArquivo.charAt(i) != '_') {
                temp = temp + nomeArquivo.charAt(i);
            } else {
                i++;
                temp = temp + Character.toUpperCase(nomeArquivo.charAt(i));
            }
            i++;
        }
        //nomeArquivo = 
        return temp;
    }
}
