package ifal.recuperacao.TwitterPc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ifal.recuperacao.TwitterPc.exception.ResourceNotFoundException;
import ifal.recuperacao.TwitterPc.model.Tweet;
import ifal.recuperacao.TwitterPc.model.Usuario;
import ifal.recuperacao.TwitterPc.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioR;
	
	@GetMapping("/usuario")
	public Page<Usuario> retorneUsuarios(Pageable pageable) {
		
		return usuarioR.findAll(pageable);
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario retornarUsuarioPeloId(@PathVariable Integer id) {
		return usuarioR.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}
	
	@PostMapping("/usuario")
	public Usuario salvarUsuario(@Valid @RequestBody Usuario usuario) {
		
		return usuarioR.save(usuario);
	}
	
	@PostMapping("/usuario/{id}/adicionarTweet")
	public Usuario adicionarTweet(@PathVariable Integer id, @Valid @RequestBody Tweet tweet) {
		return usuarioR.findById(id).map(usuario -> {
			usuario.adicionarTweet(tweet);
			return usuarioR.save(usuario);
		}).orElseThrow(() -> new ResourceNotFoundException("Tweet nao encontrado: " + id));
	}
	
	@PostMapping("/login")
	public Usuario login(@RequestBody Map<String, String> params) {
		return usuarioR.findByEmailAndSenha(params.get("email"), params.get("senha"));
	}
	
	@PutMapping("/usuario/{id}")
	public Usuario atualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuarioRequest) {
		return usuarioR.findById(id).map(usuario -> {
			usuario.setNome(usuarioRequest.getNome());
			usuario.setTelefone(usuarioRequest.getTelefone());
			usuario.setEmail(usuarioRequest.getEmail());
			usuario.setSenha(usuarioRequest.getSenha());
			
			return usuarioR.save(usuario);
		}).orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Integer id){
		return usuarioR.findById(id).map(usuario -> {
			usuarioR.delete(usuario);
			
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}
	
	

}
