package com.UCDb.comparadores;

import java.util.Comparator;

import com.UCDb.models.PerfilDisciplina;

public class DislikesComparator implements Comparator<PerfilDisciplina> {

	public DislikesComparator() {

	}

	@Override
	public int compare(PerfilDisciplina p1, PerfilDisciplina p2) {
		return p1.getQtdDislikes().compareTo(p2.getQtdDislikes());
	}

}
