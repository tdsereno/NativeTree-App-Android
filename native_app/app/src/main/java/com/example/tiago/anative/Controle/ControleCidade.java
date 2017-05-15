package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Cidade;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 29/05/2016.
 */
public class ControleCidade {

    public ArrayList<Cidade> obterTodasCidades() {

        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listAllCidades", "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerCidadesWeb(retornoWeb);

    }
}
