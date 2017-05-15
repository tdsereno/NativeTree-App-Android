package com.example.tiago.anative.Modelo;

/**
 * Created by Tiago on 15/05/2016.
 */
/**
 *
 * @author Tiago
 */
public class Cidade {

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
    
    int id;
    String nome;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    Estado estado;
    
}
