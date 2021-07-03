package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    
    @GetMapping("/usuarios") /* Método buscar todos os usuários*/
    @ResponseBody /*Retorna os dados para o corpo da respostas*/
    public ResponseEntity<List<Usuario>> listarUsuarios(){
    	
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Retorna a lista em JSON*/
    	
    }
    
    @PostMapping("/criar-usuario")
    @ResponseBody /*Descrição da respostas*/
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
    	
    	Usuario user = usuarioRepository.save(usuario);
    			
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);	
    }
    
    @PutMapping("atualizar") /* mapeia a url */
    @ResponseBody /* Descrição da respostas */
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Informa um Id válido", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user,HttpStatus.OK);
    	    	
    }
    
    
    
    
    @DeleteMapping("/deletar-usuario")
    @ResponseBody
    public ResponseEntity<String> deleteUsuario(@RequestParam Long iduser){
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    
    
    @GetMapping("/usuarioId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUsuario(@RequestParam(name = "iduser") Long idUser){
    	Usuario usuario = usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @GetMapping("/buscar-por-nome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name ) {
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
        
    
    
}
