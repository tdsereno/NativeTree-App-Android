package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Especie;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 21/05/2016.
 */
public class ControleEspecie {



    public ArrayList<Especie> obterTodasEspecies() {

        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listAllEspecie", "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerEspeciesWeb(retornoWeb);

    }

    public Especie obterEspecie(int id)
    {
        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listEspecie&id="+id+"").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerEspeciesWeb(retornoWeb).get(0);

    }


}
