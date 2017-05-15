package com.example.tiago.anative.List;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiago.anative.Controle.ControleProprietario;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.Form.ArvoresForm;
import com.example.tiago.anative.Form.ProprietariosForm;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ProprietariosList extends AppCompatActivity {


    GridView gridview = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietarios_list);

        gridview = (GridView) findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               R.layout.grid_item, getProprietariosGrid());
        gridview.setAdapter(adapter);
        gridview.setVerticalSpacing(1);
        gridview.setHorizontalSpacing(1);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String dados = ((TextView) v).getText().toString();
                int ida = obterIdGrid(dados);

                Toast.makeText(getApplicationContext(), "id:" + ida, Toast.LENGTH_SHORT).show();


                Bundle params = new Bundle();
                Intent secondActivity = new Intent(ProprietariosList.this, ProprietariosForm.class);
                startActivity(secondActivity);


                params.putInt("id", ida);
                secondActivity.putExtras(params);
                startActivity(secondActivity);


            }
        });


    }


    public void startProprietariosForm(View view) {

        Intent secondActivity = new Intent(this, ProprietariosForm.class);
        startActivity(secondActivity);
    }


    public String[] getProprietariosGrid() {
        try {
            ControleProprietario ca = new ControleProprietario();
            ArrayList<Proprietario> proprietarios = ca.obterTodosProprietarios();
            String[] retorno = new String[proprietarios.size()];
            for (int i = 0; i < proprietarios.size(); i++) {
                retorno[i] = "ID: " + proprietarios.get(i).getId() + "  " +
                        "\nNome: " + proprietarios.get(i).getNome() +
                        "\nCpf: " + proprietarios.get(i).getIdentificacao() +
                        "\nCidade:"+proprietarios.get(i).getCidade().getNome() +
                        "\n";
            }
            return retorno;
        } catch (Exception exception) {
            return new String[]{
                    "Erro conexão", "Erro conexão"};
        }

    }

    //POG
    public static int obterIdGrid(String dados) { //recebe os dados contidos na grid e retorna o id
        String ret = "";
        for (int i = 4; i < dados.length(); i++) {
            if (dados.charAt(i) != 'N') { // PRIMEIRO CAMPO DEPOIS DO ID
                if (dados.charAt(i) == '0' || dados.charAt(i) == '1' || dados.charAt(i) == '2' || dados.charAt(i) == '3' || dados.charAt(i) == '4' || dados.charAt(i) == '5' || dados.charAt(i) == '6' || dados.charAt(i) == '7' || dados.charAt(i) == '8' || dados.charAt(i) == '9') {
                    ret += dados.charAt(i);

                }

            }
            if (dados.charAt(i) == 'N') {
                break;
            }

        }
        int retorno = Integer.parseInt("" + ret);
        return retorno;
    }

}