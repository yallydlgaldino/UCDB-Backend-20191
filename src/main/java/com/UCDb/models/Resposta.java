package com.UCDb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String corpoResposta;
	//@ManyToOne
	private int idComentario;
	
	public Resposta() {
	}
	
	public Resposta(String corpoResposta, int idComentario) {
		this.corpoResposta = corpoResposta;
		this.idComentario = idComentario;
	}
	
	public long getId() {
		return this.id;
	}

	public String getCorpoResposta() {
		return this.corpoResposta;
	}

	public int getComentario() {
		return this.idComentario;
	}

}
