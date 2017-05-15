package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Usuario;

import java.util.concurrent.ExecutionException;

/**
 * Created by Tiago on 18/06/2016.
 */
public class ControleUsuario {
    public boolean logar(Usuario usuario) {


        String retornoWeb = "";

        try {String url = "/NativeServer/actions?acao=autenticar&user="+ usuario.getLogin() + "&pass=" + usuario.getSenha() + "";
            url = Util.encodeString(url);
            retornoWeb = new ConexaoWeb().execute(url, "").get();
        } catch (InterruptedException e) {
            retornoWeb = "erro ao conectar com o servidor";
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (retornoWeb.equalsIgnoreCase("0")) // nao encontrou um registro com o nome de usuario e senha informados
        {
            return false;
        } else if (retornoWeb.equalsIgnoreCase("erro ao conectar com o servidor")) {
            return false;
        } else {

            try {
                int id = Integer.parseInt(retornoWeb);
                return true;
            }catch (Exception ex)
            {

                return false;
            }


        }
    }


}
