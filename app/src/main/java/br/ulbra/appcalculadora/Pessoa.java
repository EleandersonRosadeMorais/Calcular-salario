package br.ulbra.appcalculadora;

import java.io.Serializable;

public class Pessoa implements Serializable {
    // Modelo de dados - representa um usuário do sistema

    private Integer id;
    private String nome;
    private String email;
    private String senha;

    // Getters e Setters - acesso aos atributos
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return nome;
    }
}