package com.UCDb.models;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	long idComentario;

	@Autowired
	//@OneToMany
	HashMap<Integer, Resposta> respostas;
	//@ManyToOne
	int idPerfil;
	String emailAutor;
	String corpoComentario;

	public Comentario() {
	}

	public Comentario(String corpoComentario, String emailAutor, int idPerfil) {
		this.corpoComentario = corpoComentario;
		this.emailAutor = emailAutor;
		this.idPerfil = idPerfil;
	}

	public String getCorpoComentario() {
		return this.corpoComentario;
	}

	public String getEmailAutor() {
		return this.emailAutor;
	}

	public HashMap<Integer, Resposta> getRespostas() {
		return this.respostas;
	}
	
	public Resposta getResposta(int idResposta) {
		return this.respostas.get(idResposta);
	}

	public long getId() {
		return this.idComentario;
	}
	
	public long getPerfil() {
		return this.idPerfil;
	}

	public int getTotalRespostas() {
		return respostas.size();
	}
}