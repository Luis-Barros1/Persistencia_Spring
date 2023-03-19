package br.com.projeto.aula3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.aula3.model.Usuario;
import br.com.projeto.aula3.repository.UsuarioRepository;

@RestController                      // Define a classe como controller
@RequestMapping("/usuarios")         // Define o prefixo dos endpoints como "/usuarios"
public class UsuarioController {

    //Injeção de dependência
    @Autowired
    private UsuarioRepository usuarioRepository;



    //Endpoints
    //GET: Retorna todos os usuários
    @GetMapping("/visualizar")
    public List<Usuario> obterTodos(){
        return usuarioRepository.obterTodos();
    }

    //POST: Insere um novo usuário
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.inserir(usuario);
    }

    //PUT: Edita um usuário
    @PutMapping("/editar")
    public Usuario editarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.editar(usuario);
    }

    //DELETE: Remove um usuário com base no ID
    @DeleteMapping("/deletar/{id}")
    public void deletarUsuario(@PathVariable int id){
        usuarioRepository.remover(id);
    }

    //DELETE: Remove um usuário passado no corpo da requisição
    @DeleteMapping("/deletar")
    public Usuario deletarUsuario(@RequestBody Usuario usuario){
        usuarioRepository.remover(usuario);
        return usuario;
    }

    //POST: Recebe usuário e senha do corpo da requisição, valida sua existência no banco e retorna um boolean informando se está ou não autenticado
    @PostMapping("/autenticar")
    public boolean autenticarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.autenticar(usuario.getEmail(), usuario.getSenha());
    }
}
