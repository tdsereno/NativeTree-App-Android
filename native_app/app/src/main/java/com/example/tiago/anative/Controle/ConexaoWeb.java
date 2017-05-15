package com.example.tiago.anative.Controle;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URL;

/**
 * Created by Tiago on 15/05/2016.
 */
//essa classe recebe uma url e retorna o conteudo da pagina
public class ConexaoWeb extends AsyncTask<String, Void, String> {


    private String ipServer = "http://nativetree-nangu.c9users.io:8080";

    public String getDataFromWebService(String url) {
        url = ipServer +url; // concatenado o ip do servidor com o caminho a ser chamado
        System.out.println("a url foi "+url);

        String responseStr = "";

        DefaultHttpClient httpclient = new DefaultHttpClient();

        try {


            //Usamos o GET para pergar o conteudo que esta na raiz do endereco passado acima
            HttpGet httpget = new HttpGet(url);

            //Executando a conexao para a pagina solicitada com o GET setado anteriormente
            HttpResponse response = httpclient.execute(httpget);

            //A Resposta da conexao vem no formato Entity
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                responseStr = EntityUtils.toString(entity).trim();
                return responseStr;

            }
        } catch (Exception e) {
            responseStr = "erro ao conectar na pagina" + e.toString();
            e.printStackTrace();
        }
        return responseStr;
    }


    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        return getDataFromWebService(url);

    }
}
