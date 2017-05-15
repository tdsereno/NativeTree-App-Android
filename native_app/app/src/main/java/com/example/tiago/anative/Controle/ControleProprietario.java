package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Proprietario;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 21/05/2016.
 */
public class ControleProprietario {


    public ArrayList<Proprietario> obterTodosProprietarios() {

        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listAllProprietarios", "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerProprietariosWeb(retornoWeb);

    }

    public  Proprietario obterProprietario(int id)
    {
        String retornoWeb = "";

        try {
            retornoWeb = new ConexaoWeb().execute("/NativeServer/actions?acao=listProprietario&id="+id+"").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return new Interpretador().LerProprietariosWeb(retornoWeb).get(0);

    }


    public boolean salvarProprietario(Proprietario p)
    { // http://nativetree-nangu.c9users.io:8080/NativeServer/actions?acao=cadarvore&id=0&idespecie=1&idade=25&lat=33.333&longt=44.444&idpropietario=1&status=viva&geocode=Rua%20ABC,%20Lajeado
        String linkMontado = "/NativeServer/actions?acao=cadProp&id="+p.getId()+"&nome="+p.getNome()+"&identificacao="+p.getIdentificacao()+"&idcidade="+p.getCidade().getId()+"&endrua="+Util.encodeString(p.getEnderecoRua())+"&lat="+p.getLatitude()+"&long="+p.getLongitude()+"";
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
