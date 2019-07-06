package com.UCDb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Column(name = "primeiroNome")
	@NotNull(message = "O nome não pode ser nulo")
	@NotEmpty(message = "O nome não pode ser vazio")
	private String primeiroNome;
	@Column(name = "ultimoNome")
	@NotNull(message = "O nome não pode ser nulo")
	@NotEmpty(message = "O Nome não pode ser vazio")
	private String ultimoNome;
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "senha")
	@NotNull(message = "A senha não pode ser nula")
	@NotEmpty(message = "A enha não pode ser vazia")
	private String senha;
	
	public Usuario() {		
	}
	
	public Usuario(String primeiroNome, String ultimoNome, String email, String senha) {
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.senha = senha;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	
}
