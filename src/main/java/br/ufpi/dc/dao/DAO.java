package br.ufpi.dc.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.ufpi.dc.entidades.entity.Projeto;

public class DAO<T> {

    private final Class<T> classe;

    @SuppressWarnings("rawtypes")
    public static DAO<?> getDao(Class cl) {
        @SuppressWarnings("unchecked")
        DAO<?> dao = new DAO<Class<?>>(cl);
        return dao;
    }

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void adiciona(T t) {
        //consegue a entity manager
        EntityManager em = new JPAUtil().getEntityManager();
        //abre transacao
        em.getTransaction().begin();

        //persiste o objeto
        em.persist(t);

        //commita a transacao
        em.getTransaction().commit();
//        em.flush();
        em.getEntityManagerFactory().getCache().evictAll();

        //fecha a entity manager
        //em.close();
    }

    public void remove(T t) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(t));
        em.getTransaction().commit();
        //em.close();
    }

    public void atualiza(T t) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        t = em.merge(t);
        em.getTransaction().commit();
        em.getEntityManagerFactory().getCache().evictAll();
        //em.close();
    }

    public List<T> listaTodos() {
        EntityManager em = new JPAUtil().getEntityManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        em.clear();

        List<T> lista = em.createQuery(query).getResultList();

        //em.close();
        return lista;
    }

    public List<T> listaTodosPaginada(int firstResult, int maxResults) {
        EntityManager em = new JPAUtil().getEntityManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        em.clear();

        List<T> lista = em.createQuery(query).setFirstResult(firstResult)
                .setMaxResults(maxResults).getResultList();

        //em.close();
        return lista;
    }

    public T buscaPorId(Integer id) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        return em.find(classe, id);
    }

    public int contaTodos() {
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();

        Integer result = (Integer) em.createQuery("select count(n) from " + classe.getSimpleName() + " n").getSingleResult();
        //em.close();

        return result;
    }

    public T buscaPorDe(String de, Integer idProjeto) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        if (idProjeto != null) {
            temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.de = " + "\"" + de + "\" "
                    + "and t.idProjeto.id = " + idProjeto).getResultList();
        } else {
            temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.de = " + "\"" + de + "\"").getResultList();
        }
        try {
            return temp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public T buscaPorProjeto(Integer projeto) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.idProjeto.id = " + projeto).getResultList();

        try {
            return temp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public List<T> filtraPorProjeto(Projeto projeto) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        temp = em.createQuery("select t from " + classe.getSimpleName() + " t where " + "t.idProjeto.id = " + projeto.getId()).getResultList();

        try {
            return temp;
        } catch (Exception e) {
        }
        return null;
    }

    public T buscaPorNome(String de, Integer idProjeto) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        if (idProjeto != null) {
            //verificar essas barras
            temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.nome = " + "\"" + de + "\""
                    + " and t.idProjeto.id = " + idProjeto).getResultList();
        }else{
        temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.nome = " + "\"" + de + "\"").getResultList();
        }

        try {
            return temp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public T buscaPorNomeLemma(String de, Integer idProjeto) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        if (idProjeto != null) {
            if (classe.getSimpleName().equals("Isr") || classe.getSimpleName().equals("CasoDeUso")) {
                String s = "select t from " + classe.getSimpleName() + " t where t.nomeLemma = " + "\"" + de + "\""
                        + " and t.idProjeto.id = " + idProjeto;
                temp = em.createQuery(s).getResultList();
            } else {
                temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.nomeLemma = " + "\"" + de + "\""
                        + " and t.idProjeto = " + idProjeto).getResultList();
            }
        } else {
            temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.nomeLemma = " + "\"" + de + "\"").getResultList();

        }
        try {
            return temp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public List<T> filtrarPorNomeLemma(String de, Integer idProjeto) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        if (idProjeto != null) {
            if (classe.getSimpleName().equals("Isr") || classe.getSimpleName().equals("CasoDeUso")
                    || classe.getSimpleName().equals("Atributo")) {
                String s = "select t from " + classe.getSimpleName() + " t where t.nomeLemma = " + "\"" + de + "\""
                        + " and t.idProjeto.id = " + idProjeto;
                temp = em.createQuery(s).getResultList();
            } else {
                temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.nomeLemma = " + "\"" + de + "\""
                        + " and t.idProjeto = " + idProjeto).getResultList();
            }
        } else {
            temp = em.createQuery("select t from " + classe.getSimpleName() + " t where t.nomeLemma = " + "\"" + de + "\"").getResultList();

        }
        try {
            return temp;
        } catch (Exception e) {
        }
        return null;
    }
    
    public T buscaPorCampos(Map<String, Object> params, String query) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        Query q = em.createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }

        temp = q.getResultList();
        try {
            return temp.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public List<T> filtrarPorCampos(Map<String, Object> params, String query) {
        List<T> temp = null;
        EntityManager em = new JPAUtil().getEntityManager();
        em.clear();
        Query q = em.createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }

        temp = q.getResultList();
        return temp;
    }

}
