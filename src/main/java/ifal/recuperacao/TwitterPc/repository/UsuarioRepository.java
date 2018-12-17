package ifal.recuperacao.TwitterPc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifal.recuperacao.TwitterPc.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByEmailAndSenha(String email, String senha);

}
