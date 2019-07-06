package com.UCDb.services;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UCDb.models.Usuario;
import com.UCDb.repository.UsuarioRepository;

import javassist.NotFoundException;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario cadastrarUsuario(Usuario usuario) throws Exception {
		if (isValidEmailAddress(usuario.getEmail())) {
			if (validaEmailUnico(usuario.getEmail())) {
				return usuarioRepository.save(new Usuario(usuario.getPrimeiroNome(), usuario.getUltimoNome(),
						usuario.getEmail(), usuario.getSenha()));
			} else
				throw new Exception("Email ja cadastrado");
		} else
			throw new Exception("Email invalido");
	}

	private boolean validaEmailUnico(String email) {
		boolean answer = true;
		if (usuarioRepository.findById(email).isPresent())
			answer = false;
		return answer;
	}

	private boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findById(email).get();
	}

	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

	public boolean autenticaUsuario(Usuario usuario) throws NotFoundException {
		Usuario u = usuarioRepository.findById(usuario.getEmail()).get();
		if(u == null)
			throw new NotFoundException("Usuario não cadastrado");
		else if(usuario.getSenha().equals(u.getSenha()))
			return true;
		else
			return false;
	}
}
