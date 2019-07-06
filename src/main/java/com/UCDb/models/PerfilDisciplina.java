package com.UCDb.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PerfilDisciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	ArrayList<String> likes;
	ArrayList<String> dislikes;

	@Column(name = "idDisciplina")
	@Id
	Integer id;
	@Column(name = "nomeDisciplina")
	String nome;
	@Column(name = "qtdLikes")
	private int qtdLikes;
	@Column(name = "qtdDislikes")
	private int qtdDislikes;

	public PerfilDisciplina() {
	}

	public PerfilDisciplina(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
		this.dislikes = new ArrayList<String>();
		this.likes = new ArrayList<String>();
	}

	public ArrayList<String> getLikes() {
		return likes;
	}

	public ArrayList<String> getDislikes() {
		return dislikes;
	}

	public Integer getQtdLikes() {
		return qtdLikes;
	}

	public Integer getQtdDislikes() {
		return qtdDislikes;
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return this.id;
	}

	public void updatePerfil() {
		this.qtdLikes = likes.size();
		this.qtdDislikes = dislikes.size();
	}
}
