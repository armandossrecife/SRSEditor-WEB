package br.ufpi.dc.modelo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

@Entity
@Table(name="usuario")
//@NamedQueries({
//	@NamedQuery(name="Usuario.control", query="SELECT u FROM Usuario u WHERE u.username = :username and u.password = :password")
////	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
////	@NamedQuery(name="Usuario.findById", query="SELECT u FROM Usuario u WHERE u.userid= :id"),
////	@NamedQuery(name="Usuario.findByUsername", query="SELECT u FROM Usuario u WHERE u.username= :username"),
////	@NamedQuery(name="Usuario.findByPassword", query="SELECT u FROM Usuario u WHERE u.password= :password")
//})

@ManagedBean
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private int id;
	@Column(name="username")
	private String username;
	@Column(name="pwd")
	private String pwd;
	
	public Usuario(){
		
	}
	
//	public Usuario(String nome, String email) {
//		super();
//		this.nome = nome;
//		this.email = email;
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
