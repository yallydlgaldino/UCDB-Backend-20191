package com.UCDb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.UCDb.models.Usuario;
import com.UCDb.services.UsuarioService;

import javassist.NotFoundException;

@RestController
@CrossOrigin(value = "*")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@PostMapping(value="/cadastrarUsuario",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		Usuario novoUsuario = null;
		try {
			novoUsuario = service.cadastrarUsuario(usuario);
			return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping(value="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Usuario>> usuarios(){
		try {
			return new ResponseEntity<List<Usuario>>(service.getUsuarios(),HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody Usuario usuario) {
		try {
			if(service.autenticaUsuario(usuario))	
				return new ResponseEntity<>("Usuario validado", HttpStatus.CONTINUE);
			else
				return new ResponseEntity<String>("Usuario Não Autorizado", HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>("Usuario não Cadastrado", HttpStatus.NOT_FOUND);
		}
	}
}