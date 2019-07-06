package com.UCDb.comparadores;

import java.util.Comparator;

import com.UCDb.models.PerfilDisciplina;

public class LikesComparator implements Comparator<PerfilDisciplina> {

	public LikesComparator() {

	}

	@Override
	public int compare(PerfilDisciplina p1, PerfilDisciplina p2) {
		return p1.getQtdLikes().compareTo(p2.getQtdLikes());
	}

}
