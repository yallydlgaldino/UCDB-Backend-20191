package com.UCDb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.UCDb.models.Disciplina;
import com.UCDb.models.PerfilDisciplina;
import com.UCDb.services.DisciplinaService;

@RestController
@CrossOrigin(value="*")
public class DisciplinaController {

	@Autowired
	DisciplinaService service;

	@ResponseBody
	@RequestMapping(value = "/subject/createSubject", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody Disciplina disciplina) {
		String returnMessage = "Disciplina Criada com sucesso";
		HttpStatus http = HttpStatus.CREATED;
		try {
			service.adicionarDisciplina(disciplina);
		} catch (Exception e) {
			e.printStackTrace();
			http = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(returnMessage, http);
	}

	@ResponseBody
	@RequestMapping(value = "/subject/search{word}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Object> searchBySubstring(@RequestParam String nome) {
		try {
			return new ResponseEntity<Object>(service.pesquisaPorSubstring(nome), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/subject/searchId{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Object> searchProfileSubjectById(@RequestParam int id) {
		try {
			return new ResponseEntity<>(service.pesquisaPerfilPorId(id), HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/subject/AllSubjects", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Disciplina>> getAllSubjects() {
		List<Disciplina> list = service.getDisciplinas();
		if (list.isEmpty())
			return new ResponseEntity<List<Disciplina>>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(list, HttpStatus.FOUND);
	}

	@ResponseBody
	@RequestMapping(value = "/subject/likeSubject/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> like(@RequestParam int idDisciplina, @RequestBody String emailAutor) {
		try {
			service.like(idDisciplina, emailAutor);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/subject/likeSubject/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<String> dislike(@RequestParam int idDisciplina, @RequestBody String emailAutor) {
		try {
			service.dislike(idDisciplina, emailAutor);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/subject/listProfileSubjects/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<ArrayList<PerfilDisciplina>> listAllProfileSubjects(
			@RequestParam(name = "op", required = false, defaultValue = "0") int op) {
		ArrayList<PerfilDisciplina> list = service.getProfileSubjetcsOrdened(op);
		if (list.isEmpty())
			return new ResponseEntity<ArrayList<PerfilDisciplina>>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ArrayList<PerfilDisciplina>>(list, HttpStatus.FOUND);
	}
}
