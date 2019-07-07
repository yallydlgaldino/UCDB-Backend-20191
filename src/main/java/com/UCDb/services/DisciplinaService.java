package com.UCDb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UCDb.comparadores.DislikesComparator;
import com.UCDb.comparadores.LikesComparator;
import com.UCDb.models.Disciplina;
import com.UCDb.models.PerfilDisciplina;
import com.UCDb.repository.ComentarioRepository;
import com.UCDb.repository.DisciplinaRepository;
import com.UCDb.repository.PerfilRepository;
import com.UCDb.repository.RespostaReposiotry;

import javassist.NotFoundException;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	PerfilRepository perfilRepository;
	

	public Disciplina adicionarDisciplina(Disciplina disciplina) {
		Disciplina novaDisciplina = disciplinaRepository.save(disciplina);
		PerfilDisciplina perfil = new PerfilDisciplina(novaDisciplina.getId(), novaDisciplina.getName());
		perfilRepository.save(perfil);
		return novaDisciplina;
	}

	public ArrayList<Disciplina> pesquisaPorSubstring(String subString) throws Exception {
		ArrayList<Disciplina> listaRetorno = new ArrayList<Disciplina>();
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		for (Disciplina disciplina : disciplinas) {
			if (disciplina.getName().toLowerCase().contains(subString.toLowerCase()))
				listaRetorno.add(disciplina);
		}
		if (listaRetorno.isEmpty())
			throw new NotFoundException("Nenhuma disciplina encontrada");
		return listaRetorno;
	}

	public PerfilDisciplina pesquisaPerfilPorId(int idDisciplina) throws Exception {
		PerfilDisciplina perfilDisciplina = perfilRepository.findById(idDisciplina).get();
		if (perfilDisciplina != null)
			return perfilDisciplina;
		else
			throw new NotFoundException("Disciplina não encontrada");
	}

	public void like(int idDisciplina, String emailAutor) throws Exception {
		PerfilDisciplina perfil = perfilRepository.findById(idDisciplina).get();
		if (perfil == null)
			throw new NotFoundException("id Incorreto");
		else {
			ArrayList<String> likes = perfil.getLikes();
			if (likes.contains(emailAutor))
				likes.remove(emailAutor);
			else
				likes.add(emailAutor);
			perfil.updatePerfil();
			perfilRepository.save(perfil);
		}
	}
	
	public void dislike(int idDisciplina, String emailAutor) throws Exception {
		PerfilDisciplina perfil = perfilRepository.findById(idDisciplina).get();
		if (perfil == null)
			throw new NotFoundException("id Incorreto");
		else {
			ArrayList<String> dislikes = perfil.getDislikes();
			if (dislikes.contains(emailAutor))
				dislikes.remove(emailAutor);
			else
				dislikes.add(emailAutor);
			perfil.updatePerfil();
			perfilRepository.save(perfil);
		}
	}
	
	public ArrayList<PerfilDisciplina> perfilDisciplinasOrdenado(int op) {
		if (op == 1) // op == 1, ordena os perfis por Like de forma crescente
			return listarPerfisPorLikesCrescente();
		else if (op == 2) // op == 2, ordena os perfis por Dislike de forma crescente
			return listarPerfisPorDislikesCrescente();
		else if (op == 3) // op == 1, ordena os perfis por Like de forma decrescente
			return listarPerfisPorLikesDecrescente();
		else if (op == 4) // op == 2, ordena os perfis por Dislike de forma decrescente
			return listarPerfisPorDislikesDecrescente();
		else
			return getArrayListPerfisDisciplinas();
	}
	
	private ArrayList<PerfilDisciplina> listarPerfisPorDislikesCrescente() {
		ArrayList<PerfilDisciplina> list = getArrayListPerfisDisciplinas();
		DislikesComparator comparator = new DislikesComparator();
		Collections.sort(list, comparator);
		Collections.reverse(list);
		if(list.get(0).getQtdDislikes()==0)
			return getArrayListPerfisDisciplinas();
		return list;
	}

	private ArrayList<PerfilDisciplina> listarPerfisPorLikesCrescente () {
		ArrayList<PerfilDisciplina> list = getArrayListPerfisDisciplinas();
		LikesComparator comparator = new LikesComparator();
		Collections.sort(list, comparator);
		Collections.reverse(list);
		if(list.get(0).getQtdLikes()==0)
			return getArrayListPerfisDisciplinas();
		return list;
	}
	
	private ArrayList<PerfilDisciplina> listarPerfisPorDislikesDecrescente() {
		ArrayList<PerfilDisciplina> list = getArrayListPerfisDisciplinas();
		DislikesComparator comparator = new DislikesComparator();
		Collections.sort(list, comparator);
		if(list.get(0).getQtdDislikes()==0)
			return getArrayListPerfisDisciplinas();
		return list;
	}

	private ArrayList<PerfilDisciplina> listarPerfisPorLikesDecrescente() {
		ArrayList<PerfilDisciplina> list = getArrayListPerfisDisciplinas();
		LikesComparator comparator = new LikesComparator();
		Collections.sort(list, comparator);
		if(list.get(0).getQtdLikes()==0)
			return getArrayListPerfisDisciplinas();
		return list;
	}

	private ArrayList<PerfilDisciplina> getArrayListPerfisDisciplinas() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		ArrayList<PerfilDisciplina> perfis = new ArrayList<PerfilDisciplina>();
		for (Disciplina disciplina : disciplinas) {
			perfis.add(perfilRepository.findById(disciplina.getId()).get());
		}
		return perfis;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinaRepository.findAll();
	}
}