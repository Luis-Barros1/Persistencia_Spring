package br.com.projeto.aula3.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.projeto.aula3.model.Usuario;

@Repository //Indica essa classe como um repositório
public class UsuarioRepository {
    
    //Definição dos atributos estáticos da classe
    private static String SELECT_ALL = "SELECT * FROM USUARIO";
    private static String INSERT = "INSERT INTO USUARIO (nome,email,senha) VALUES(?,?,?)";
    private static String DELETE = "DELETE FROM USUARIO WHERE id = ?";
    private static String UPDATE = "UPDATE USUARIO SET nome = ?, email = ?, senha = ? WHERE id = ?";
    private static String AUTENTICATE = "SELECT * FROM USUARIO WHERE email = ? AND senha = ?";

    //Injeção de dependência JDBCTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Métodos
    //Insere um usuário na tabela
    public Usuario inserir(Usuario usu) {
        jdbcTemplate.update(INSERT, new Object[] {
                usu.getNome(), usu.getEmail(), usu.getSenha()
        });
        return usu;
    }

    //Seleciona todos os usuários da tabela
    public List<Usuario> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL,
                Usuario.getRowMapper());
    }

    //Remove um usuário da tabela com base em seu ID
    public void remover(int idUsuario){
        jdbcTemplate.update(DELETE, idUsuario);
    }

    //Remove um dado usuário da tabela
    public void remover(Usuario usu){
        jdbcTemplate.update(DELETE, usu.getId());
    }

    //Edita um usuário
    public Usuario editar(Usuario usu){
        jdbcTemplate.update(UPDATE, new Object[]{
            usu.getNome(),
            usu.getEmail(),
            usu.getSenha(),
            usu.getId()
        });
        return usu;
    }

    //Autentica a existência de um usuário e sua senha
    public boolean autenticar(String email, String senha){
        List<Usuario> listaAutenticacao = jdbcTemplate.query(AUTENTICATE,Usuario.getRowMapper(), new Object[]{
            email,
            senha
        });

        return listaAutenticacao.size() > 0;
    }
}
