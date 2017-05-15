package com.example.tiago.anative.Modelo;

/**
 * Created by Tiago on 15/05/2016.
 */


/**
 *
 * @author Tiago
 */
public class Arvore {

    int id, idade;

public String getGoogleMapsData(){
    return  "ID:" +this.getId()+"Especie:"+this.getEspecie().getNome();
}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    String enderecoGeoCode, status, latitude, longitude;

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
    Proprietario propietario;
    Especie especie;

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }




    public String getEnderecoGeoCode() {
        return enderecoGeoCode;
    }

    public void setEnderecoGeoCode(String enderecoGeoCode) {
        this.enderecoGeoCode = enderecoGeoCode;
    }

    public Proprietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Proprietario propietario) {
        this.propietario = propietario;
    }


}
