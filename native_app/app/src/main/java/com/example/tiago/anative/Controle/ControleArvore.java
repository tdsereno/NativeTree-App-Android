package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Arvore;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 21/05/2016.
 */
public class ControleArvore {



    public ArrayList<Arvore> obterTodasArvores() {

        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listAllArvore", "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerArvoresWeb(retornoWeb);

    }

    public Arvore consultarArvore(int id)
    {

        String retornoWeb = "";
 //http://nativetree-nangu.c9users.io:8080/NativeServer/actions?acao=listArvore&id=2
        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listArvore&id="+id, "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerArvoresWeb(retornoWeb).get(0);

    }

    public boolean salvarArvore(Arvore a)
    { // http://nativetree-nangu.c9users.io:8080/NativeServer/actions?acao=cadarvore&id=0&idespecie=1&idade=25&lat=33.333&longt=44.444&idpropietario=1&status=viva&geocode=Rua%20ABC,%20Lajeado
        String linkMontado = "/NativeServer/actions?acao=cadarvore&id="+a.getId()+"&idespecie="+a.getEspecie().getId()+"&idade="+a.getIdade()+"&lat="+a.getLatitude()+"&longt="+a.getLongitude()+"&idpropietario="+a.getPropietario().getId()+"&status="+a.getStatus()+"&geocode="+Util.encodeString(a.getEnderecoGeoCode())+"";
        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute(linkMontado, "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (retornoWeb.contains("1 - Registro salvo com sucesso"))
        {
            return true;
        } else
            return false;

    }




}
