package br.ufpi.dc.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufpi.dc.modelo.Usuario;

public class UsuarioDAO {
	
	public void salvar(Usuario usuario){
		EntityManager em = JPAUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean loginControl(String username, String password){
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.pwd = :password", Usuario.class);
		query.setParameter("username", username).setParameter("password", password);
		try{
			Usuario u = query.getSingleResult();
			
			if(u != null){
				return true;
			}
			return false;
		}catch (Exception e) {
			return false;
		}

	}
	
//	@SuppressWarnings({"unchecked" })
//	public List<Usuario> listarUsuarios(){
//		EntityManager em = JPAUtil.getEntityManager();
//		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
//		List<Usuario> listUsuarios = query.getResultList();
//				
//		return listUsuarios;
//		
//	}
	
}
