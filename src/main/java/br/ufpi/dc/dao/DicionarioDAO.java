/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.dao;

import static br.ufpi.dc.controle.analiseTexto.AnalisePeriodo.tagger;
import br.ufpi.dc.entidades.entity.Atributo;
import br.ufpi.dc.entidades.entity.CasoDeUso;
import br.ufpi.dc.entidades.entity.DadoRadical;
import br.ufpi.dc.entidades.entity.Dados;
import br.ufpi.dc.entidades.entity.tools.ElementoDeInterface;
import br.ufpi.dc.entidades.entity.Isr;
import br.ufpi.dc.entidades.entity.Projeto;
import br.ufpi.dc.entidades.entity.Tabela;
import br.ufpi.dc.entidades.entity.Acao;
import br.ufpi.dc.entidades.entity.Conceito;
import br.ufpi.dc.entidades.entity.SinonimoDominio;
import br.ufpi.dc.entidades.entity.tools.Proximo;
import br.ufpi.dc.entidades.entity.tools.Sinonimo;
import br.ufpi.dc.entidades.entity.tools.Verbo;
import br.ufpi.dc.tools.Constante;
import br.ufpi.dc.controle.etiquetador.Etiquetador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.ufpi.dc.controle.nGramas.NGrama;
//import visao.view.tools.Constante;

/**
 *
 * @author helcio.soares
 */
public class DicionarioDAO {

    EntityManager em = new JPAUtil().getEntityManager();
    public DAO<Tabela> tabelaDAO = new DAO(Tabela.class);
    public DAO<Atributo> atributoDAO = new DAO(Atributo.class);
    public DAO<Conceito> conceitoDAO = new DAO(Conceito.class);
    public DAO<Verbo> verboDAO = new DAO(Verbo.class);
    public DAO<Isr> isrDAO = new DAO(Isr.class);
    public DAO<CasoDeUso> casoDeUsoDAO = new DAO(CasoDeUso.class);
    public DAO<ElementoDeInterface> elementoDeInterfaceDAO = new DAO(ElementoDeInterface.class);
    public DAO<Acao> acaoDAO = new DAO(Acao.class);
    private DAO<SinonimoDominio> sinonimoDominioDAO = new DAO(SinonimoDominio.class);
    private DAO<Proximo> proximoDAO = new DAO(Proximo.class);

    public List getAnaliseIdf(Projeto idProjeto) {
        List l = null;
        try {
            Query q = em.createQuery("select dr.idRadical.id, sum(dr.idf) FROM DadoRadical dr\n"
                    + "where dr.idProjeto.id = :pIdProjeto \n"
                    + "group by dr.idRadical\n"
                    + "order by sum(dr.idf) desc");
            q.setParameter("pIdProjeto", idProjeto.getId());
            l = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DicionarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return l;
        //return analiseIdfDAO.listaTodos();
    }

    public List getPalavraMaisFrequanteIdf(Projeto idProjeto, Integer idRadical) {
//        em.refresh(this);
        Query q = em.createQuery(
                "select p.idClasseGramatical.id, d.idProjeto.id, d.idPalavra.id, d.idRadical.id, p.de, sum(d.f) from Dados d join Palavra p on d.idPalavra=p "
                + "where d.idRadical.id = :pIdRadical "
                + "and d.idProjeto = :pIdProjeto "
                + "group by p.idClasseGramatical.id, d.idProjeto.id, d.idPalavra.id, d.idRadical.id, p.de "
                + "order by sum(d.f) desc");
        q.setMaxResults(1);
        q.setParameter("pIdRadical", idRadical);
        q.setParameter("pIdProjeto", idProjeto);
        q.setHint("toplink.refresh", "true");
        List l = q.getResultList();
        return l;
    }

    public Dados getIdDados(Integer idProjeto, Integer idReferencia, Integer idRadical, Integer idPalavra) {
        try {

            Query q = em.createQuery("select d from Dados d\n"
                    + "where d.idProjeto.id    = :pIdProjeto    and  \n"
                    + "      d.idReferencia.id = :pIdReferencia and\n"
                    + "      d.idRadical.id    = :pRadical      and\n"
                    + "      d.idPalavra.id    = :pPalavra ");

            q.setParameter("pIdProjeto", idProjeto);
            q.setParameter("pIdReferencia", idReferencia);
            q.setParameter("pRadical", idRadical);
            q.setParameter("pPalavra", idPalavra);
            q.setHint("toplink.refresh", "true");
            Dados dados = (Dados) q.getSingleResult();
            return dados;
        } catch (Exception e) {
        }
        return null;
    }

    public DadoRadical getIdDadoRadical(Integer idProjeto, Integer idReferencia, String radical) {
        DadoRadical dadoRadical = null;
        try {

            Query q = em.createQuery("select dr from DadoRadical dr\n"
                    + "where dr.idProjeto.id    = :pIdProjeto    and\n"
                    + "      dr.idReferencia.id = :pIdReferencia and\n"
                    + "      dr.idRadical.de    = :pRadical         \n");

            q.setParameter("pIdProjeto", idProjeto);
            q.setParameter("pIdReferencia", idReferencia);
            q.setParameter("pRadical", radical);
            q.setHint("toplink.refresh", "true");
            dadoRadical = (DadoRadical) q.getSingleResult();
        } catch (Exception e) {
        }
        return dadoRadical;
    }

    public NGrama recuperaFrequenciaPalavra(String radical, Projeto projeto) {
        NGrama temp = null;
        try {
            Query q = em.createQuery(" SELECT dr.idProjeto.id, sum(dr.idf) idf FROM Dados dr "
                    + "join Palavra r on r.id = dr.idPalavra.id "
                    + "where r.de like '" + radical + "%' and dr.idProjeto = :pIdProjeto "
                    + "group by dr.idProjeto.id");
            q.setParameter("pIdProjeto", projeto);
            List l = q.getResultList();
            if (l.size() == 0) {
                temp = new NGrama(radical, new Double(0));
            }
            Object[] ob = (Object[]) l.get(0);
            Double f = (Double) ob[1];
            temp = new NGrama(radical, f);
        } catch (Exception e) {
        }
        return temp;
    }

    public Conceito recuperarConceito(String de, Projeto projeto, Integer idTipoConceito) {
        Conceito c = null;
        try {
            Query q = em.createQuery(" SELECT c FROM Conceito c "
                    + "where c.nomeLemma = :pDe and c.idProjeto = :pIdProjeto "
                    + "and c.idTipoConceito = :pIdTipoConceito");
            q.setParameter("pDe", de);
            q.setParameter("pIdProjeto", projeto.getId());
            q.setParameter("pIdTipoConceito", idTipoConceito);
            c = (Conceito) q.getSingleResult();
        } catch (Exception e) {
        }
        return c;
    }

    public List<Conceito> carregarConceitos(int tipoConceito, int idProjetoSelecionado) {
        String sql = "select c from Conceito c "
                + "where  c.idTipoConceito = :pIdTipoConceito "
                + "and    c.idProjeto = :pIdProjeto "
                + "and    c.utilizado >=0 "
                + "order by c.f desc, c.utilizado desc ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdTipoConceito", tipoConceito);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Conceito> listaTemp = conceitoDAO.filtrarPorCampos(params, sql);
        return listaTemp;
    }

    public List<String> carregarConceitosComoString(int tipoConceito, int idProjetoSelecionado) {
        String sql = "select c from Conceito c "
                + "where  c.idTipoConceito = :pIdTipoConceito "
                + "and    c.idProjeto = :pIdProjeto "
                + "order by c.de ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdTipoConceito", tipoConceito);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Conceito> listaTemp = conceitoDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Conceito conceito : listaTemp) {
            listaRetorno.add(conceito.getDe());
        }
        return listaRetorno;
    }

    public List<String> carregarTodosConceitosComoString(int idProjetoSelecionado) {
        String sql = "select c from Conceito c "
                + "where  c.idProjeto = :pIdProjeto "
                + "order by c.de ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Conceito> listaTemp = conceitoDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Conceito conceito : listaTemp) {
            listaRetorno.add(conceito.getDe());
        }
        return listaRetorno;
    }

    public List<String> carregarTabelaPorTipoComoString(int idTipoTabela, int idProjetoSelecionado, String tipoRetorno) {
        String sql = "select c from Tabela c "
                + "where  c.idTipoTabela = :pIdTipoTabela "
                + "and    c.idProjeto.id = :pIdProjeto "
                + "order by c.de";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdTipoTabela", idTipoTabela);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Tabela> listaTemp = tabelaDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Tabela tabela : listaTemp) {
            if (tipoRetorno.equals("nome")) {
                listaRetorno.add(tabela.getNome());
            } else {
                listaRetorno.add(tabela.getNomeLemma());

            }
        }
        return listaRetorno;
    }

    public List<String> carregarTabelaComoString(int idProjetoSelecionado) {
        String sql = "select c from Tabela c "
                + "where  c.idProjeto.id = :pIdProjeto "
                + "order by c.idTipoTabela";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Tabela> listaTemp = tabelaDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Tabela tabela : listaTemp) {
            listaRetorno.add(tabela.getNomeLemma());
        }
        return listaRetorno;
    }

    public Tabela recuperarTabelaPorTipoENome(int idTipoTabela, String nome, int idProjetoSelecionado) {
        String sql = "select c from Tabela c "
                + "where  c.idTipoTabela = :pIdTipoTabela "
                + "and    c.nome = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdTipoTabela", idTipoTabela);
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Tabela> listaTemp = tabelaDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public Tabela recuperarTabelaPorENome(String nome, int idProjetoSelecionado) {
        String sql = "select c from Tabela c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Tabela> listaTemp = tabelaDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public CasoDeUso recuperarCasoDeUsoPorNomeLemma(String nome, int idProjetoSelecionado) {
        String sql = "select c from CasoDeUso c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<CasoDeUso> listaTemp = casoDeUsoDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public CasoDeUso recuperarCasoDeUsoPorNome(String nome, int idProjetoSelecionado) {
        String sql = "select c from CasoDeUso c "
                + "where  c.nome = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<CasoDeUso> listaTemp = casoDeUsoDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public List<Tabela> recuperarTabelaPorNomeLemma(String nome, int idProjetoSelecionado) {
        String sql = "select c from Tabela c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Tabela> listaTemp = tabelaDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp;
        } catch (Exception e) {
        }
        return null;
    }

    public Isr recuperarIsrPorENome(String nome, int idProjetoSelecionado) {
        String sql = "select c from Isr c "
                + "where  c.nome = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Isr> listaTemp = isrDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public Isr recuperarIsrPorNomeLemma(String nome, int idProjetoSelecionado) {
        String sql = "select c from Isr c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Isr> listaTemp = isrDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public Atributo recuperarAtributoPorNomeLemma(String nome, int idProjetoSelecionado) {

        String sql = "select c from Atributo c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Atributo> listaTemp = atributoDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public List<Integer> recuperarTipoPorNomeLemma(String nome, int idProjetoSelecionado) {
        List<Integer> listaRetorno = new ArrayList<Integer>();
        //procura na tabela tabela
        String sql = "select c from Tabela c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Tabela> listaTemp = tabelaDAO.filtrarPorCampos(params, sql);
        for (Tabela tabela : listaTemp) {
            listaRetorno.add(tabela.getIdTipoTabela());
        }

        // procura na tabela isr
        params.clear();
        sql = "select c from Isr c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        Isr tempIsr = isrDAO.buscaPorCampos(params, sql);
        if (tempIsr != null) {
            listaRetorno.add(Constante.REQUISITOS_DE_ARMAZENAMENTO);
        }

        // procura na tabela Atributo
        params.clear();
        sql = "select c from Atributo c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        Atributo tempAtributo = atributoDAO.buscaPorCampos(params, sql);
        if (tempAtributo != null) {
            listaRetorno.add(Constante.ATRIBUTO);
        }

        // procura na tabela Atributo
        params.clear();
        sql = "select c from CasoDeUso c "
                + "where  c.nomeLemma = :pNome "
                + "and    c.idProjeto.id = :pIdProjeto ";

        params.put("pNome", nome);
        params.put("pIdProjeto", idProjetoSelecionado);

        CasoDeUso tempCasoDeUso = casoDeUsoDAO.buscaPorCampos(params, sql);

        if (tempCasoDeUso != null) {
            listaRetorno.add(Constante.CASO_DE_USO);
        }

        try {
            return listaRetorno;
        } catch (Exception e) {
        }
        return null;
    }

    public List<String> carregarRequisitosDeArmazenamento(int idProjetoSelecionado) {
        String sql = "select c from Isr c "
                + "where  c.idProjeto.id = :pIdProjeto "
                + "order by c.nome ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdProjeto", idProjetoSelecionado);

        List<Isr> listaTemp = isrDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Isr isr : listaTemp) {
            listaRetorno.add(isr.getNome());
        }
        return listaRetorno;
    }

    public Conceito recuperarConceitoPorLemmaNome(String de, Integer idProjeto) {
        String sql = "select c from Conceito c "
                + "where  c.nomeLemma = :pDe "
                + "and    c.idProjeto = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pDe", de);
        params.put("pIdProjeto", idProjeto);

        List<Conceito> listaTemp = conceitoDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public Conceito recuperarConceitoPorNome(String de, Integer idProjeto) {
        String sql = "select c from Conceito c "
                + "where  c.de = :pDe "
                + "and    c.idProjeto = :pIdProjeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pDe", de);
        params.put("pIdProjeto", idProjeto);

        List<Conceito> listaTemp = conceitoDAO.filtrarPorCampos(params, sql);
        try {
            return listaTemp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public List<String> carregarAtributoComoString(int idProjeto) {
        String sql = "select c from Atributo c "
                + "where  c.idIsr.idProjeto.id = :pIdProjeto "
                + "and c.validado <> 0 "
                + "order by c.nome ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdProjeto", idProjeto);

        List<Atributo> listaTemp = atributoDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Atributo atributo : listaTemp) {
            listaRetorno.add(atributo.getNome());
        }
        return listaRetorno;
    }

    public List<String> carregarCasoDeUsoComoString(int idProjeto) {
        String sql = "select c from CasoDeUso c "
                + "where  c.idProjeto.id = :pIdProjeto "
                + "order by c.nome ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pIdProjeto", idProjeto);

        List<CasoDeUso> listaTemp = casoDeUsoDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (CasoDeUso atributo : listaTemp) {
            listaRetorno.add(atributo.getNome());
        }
        return listaRetorno;
    }

    public List<String> carregarElementoDeInterfaceComoString() {
        List<ElementoDeInterface> listaTemp = elementoDeInterfaceDAO.listaTodos();
        List<String> listaRetorno = new ArrayList<String>();
        for (ElementoDeInterface atributo : listaTemp) {
            listaRetorno.add(atributo.getDe());
        }
        return listaRetorno;
    }

    public List<String> carregarVerbos() {
        String sql = "select c from Verbo c "
                + "order by c.de ";
        Query q = em.createQuery(sql);

        List<Verbo> listaTemp = q.getResultList();
        List<String> listaRetorno = new ArrayList<String>();
        for (Verbo verbo : listaTemp) {
            listaRetorno.add(verbo.getDe());
        }
        return listaRetorno;
    }

    public List<String> carregarOpcoesComoString(Sinonimo sinonimo) {
        String sql = "select c from Proximo c "
                + "where  c.idVerbo = :idVerbo "
                + "order by c.de ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idVerbo", sinonimo.getIdVerbo());

        List<Proximo> listaTemp = proximoDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Proximo sino : listaTemp) {
            listaRetorno.add(sino.getDe());
        }
        return listaRetorno;
    }

    public List<String> carregarOpcoesFuncionalidadesComoString(String verbo, int idProjeto) {
        String sql = "select c from Acao c "
                + "where  c.verbo = :verbo and c.idProjeto.id = :idProjeto "
                + "order by c.objeto ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("verbo", verbo);
        params.put("idProjeto", idProjeto);

        List<Acao> listaTemp = acaoDAO.filtrarPorCampos(params, sql);
        List<String> listaRetorno = new ArrayList<String>();
        for (Acao acao : listaTemp) {
            listaRetorno.add(acao.getObjeto());
        }
        return listaRetorno;
    }

    public List<String> retornaSinonimos(String lemmaElementoDaFrase, Projeto idProjeto) {
        List<String> temp = new ArrayList<>();
        List<SinonimoDominio> temp1 = new ArrayList<>();
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("idProjeto", idProjeto);
        params.put("chave", lemmaElementoDaFrase);
        SinonimoDominio s = sinonimoDominioDAO.buscaPorCampos(params, Constante.QUERYSINONIMOCHAVE1);
        if (s != null) {
            temp.add(s.getSinonimo());
        }

        params.clear();
        params.put("idProjeto", idProjeto);
        params.put("sinonimo", lemmaElementoDaFrase);

        temp1 = sinonimoDominioDAO.filtrarPorCampos(params, Constante.QUERYSINONIMOSINONIMO1);

        for (SinonimoDominio temp11 : temp1) {
            temp.add(temp11.getChave());
        }

        return temp;
    }

    public String recuperarSinonimoPorChave1(String lemma, Projeto idProjeto) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idProjeto", idProjeto);
        params.put("chave", lemma);
        SinonimoDominio s = sinonimoDominioDAO.buscaPorCampos(params, Constante.QUERYSINONIMOCHAVE1);
        return s == null ? "" : s.getSinonimo();
    }

    public void etiquetarTudo(Projeto idProjeto) {
        List<String> palavras = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();

        palavras = verificarNgramas(carregarTodosConceitosComoString(idProjeto.getId()), palavras);
        temp = verificarNgramas(carregarTabelaComoString(idProjeto.getId()), temp);
        temp = verificarNgramas(carregarCasoDeUsoComoString(idProjeto.getId()), temp);
        temp = verificarNgramas(carregarAtributoComoString(idProjeto.getId()), temp);
        temp = verificarNgramas(carregarRequisitosDeArmazenamento(idProjeto.getId()), temp);
        temp.removeAll(palavras);
        palavras.addAll(temp);

        tagger = new Etiquetador(palavras);
        tagger.etiquetar();
    }

    private List<String> verificarNgramas(List<String> listaPalavras, List<String> palavras) {

        for (String temp1 : listaPalavras) {
            if(temp1!=null)
            if (temp1.indexOf(" ") >= 0) {
                String[] termos = temp1.split(" ");
                for (int i = 0; i <= termos.length - 1; i++) {
                    if (!palavras.contains(termos[i])) {
                        palavras.add(termos[i]);
                    }
                }
            } else {
                palavras.add(temp1);
            }
        }
        return palavras;
    }

    public List<Atributo> carregarAtributosDuplicados(String lemmaAtributo, Integer idProjeto/*, Isr idIsr*/) {
        String sql = "select a from Atributo a "
                + "where  a.nomeLemma = :lemmaAtributo "
                + "and    a.idProjeto.id = :idProjeto "
                + "and    a.validado     <> 0 "
                + "and    a.idIsrOrigem  <> null";
//              + "and    a.idIsr        <> :idIsr";

        Map<String, Object> params = new HashMap<>();
        params.put("lemmaAtributo", lemmaAtributo);
        params.put("idProjeto", idProjeto);
//        params.put("idIsr", idIsr);

        List<Atributo> listaTemp = atributoDAO.filtrarPorCampos(params, sql);
        return listaTemp;
    }
    public List<Atributo> carregarAtributosPorIrs(Integer idProjeto, Isr idIsr) {
        String sql = "select a from Atributo a "
                + "where  a.idProjeto.id = :idProjeto "
                + "and    a.idIsr        = :idIsr       "
                + "and    a.validado     <> 0         ";

        Map<String, Object> params = new HashMap<>();
        params.put("idProjeto", idProjeto);
        params.put("idIsr", idIsr);

        List<Atributo> listaTemp = atributoDAO.filtrarPorCampos(params, sql);
        return listaTemp;
    }
    

    public List<String> recuperarClassesAtributo(String lemmaAtributo, Integer idProjeto/*, Isr idIsr*/) {
        List<String> listaTemp = new ArrayList<>();
        for (Atributo atributo : carregarAtributosDuplicados(lemmaAtributo, idProjeto)) {
            listaTemp.add(atributo.getIdIsr().getNomeLemma());
        }
        return listaTemp;
    }

    public void updateAtributos(String lemmaAtributo, Isr isr, Boolean duplicado, Integer idProjeto) {
        //verificar esse método
        String sql = "update Atributo  \n"
                + "set   idIsrOrigem  = " + isr + "\n"
                + "where idProjeto.id = " + idProjeto + "\n"
                + "and   nomeLemma    = '" + lemmaAtributo + "'"
                + "and   validado     <> 0";

        Query q = em.createQuery(sql);
        em.getTransaction().begin();
        Query q1 = em.createQuery(sql);
        q1.executeUpdate();
        em.getTransaction().commit();
    }

    public List<String> filtrarSinonimosDistintos(Map<String, Object> params) {
        List<String> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        Query q = em.createQuery(Constante.QUERYSINONIMOSDISTINCT);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }

        temp = q.getResultList();
        return temp;
    }

    public Atributo recuperarAtributoNaoClassificado(String lemmaAtributo, Projeto projetoSelecionadoSelecionado) {
       
        Atributo temp = null ;
        String sql = "select a from Atributo a "
                + "where  a.nomeLemma = :lemmaAtributo "
                + "and    a.idProjeto.id = :idProjeto "
                + "and    a.validado     = 0 "
                + "and    a.idIsrOrigem  = null "
                + "and    a.idIsr        = null";

        Map<String, Object> params = new HashMap<>();
        params.put("lemmaAtributo", lemmaAtributo);
        params.put("idProjeto", projetoSelecionadoSelecionado.getId());
        temp = atributoDAO.buscaPorCampos(params, sql);
        return temp;
    }
}
