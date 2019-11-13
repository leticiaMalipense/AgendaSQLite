package br.edu.ifsp.agendasqlite.model;

import java.io.Serializable;

public class Contato implements Serializable {

    private int id;
    private String nome;
    private String fone;
    private String foneAlternativo;
    private String email;
    private String dataAniversario;
    private Boolean favorito = Boolean.FALSE;

    public Contato() {
    }

    public Contato(String nome, String fone, String foneAlternativo, String dataAniversario, String email) {
        this.nome = nome;
        this.fone = fone;
        this.foneAlternativo = foneAlternativo;
        this.dataAniversario = dataAniversario;
        this.email = email;
    }


    public boolean equals(Object obj)
    {
        Contato c2= (Contato) obj;
        if (this.id ==c2.getId())
            return true;
           else
            return false;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getFoneAlternativo() {
        return foneAlternativo;
    }

    public void setFoneAlternativo(String foneAlternativo) {
        this.foneAlternativo = foneAlternativo;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(String dataAniversario) {
        this.dataAniversario = dataAniversario;
    }
}
