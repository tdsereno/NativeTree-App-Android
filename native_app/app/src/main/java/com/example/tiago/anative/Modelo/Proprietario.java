package com.example.tiago.anative.Modelo;

/**
 * Created by Tiago on 15/05/2016.
 */
/**
 *
 * @author Tiago
 */

/**
 *
 * @author Tiago
 */
public class Proprietario {

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public String getEnderecoRua() {
        return enderecoRua;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public void setEnderecoRua(String enderecoRua) {
        this.enderecoRua = enderecoRua;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    int id;
    String nome, identificacao, enderecoRua, latitude, longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    Cidade cidade;

}