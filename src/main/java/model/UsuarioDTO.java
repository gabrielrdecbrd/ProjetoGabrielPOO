package model;

public class UsuarioDTO {
    private int id_usuario;
    private String nome_usuario;
    private String senha_usuario;

    // Retorna o id do usuário
    public int getId_usuario() {
        return id_usuario;
    }

    // Define o id do usuário
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    // Retorna o nome/login do usuário
    public String getNome_usuario() {
        return nome_usuario;
    }

    // Define o nome/login do usuário
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    // Retorna a senha do usuário
    public String getSenha_usuario() {
        return senha_usuario;
    }

    // Define a senha do usuário
    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
}
