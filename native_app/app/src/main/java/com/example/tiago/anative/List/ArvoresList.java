package com.example.tiago.anative.List;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiago.anative.Controle.ControleArvore;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.Form.ArvoresForm;
import com.example.tiago.anative.MapsActivity;
import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArvoresList extends AppCompatActivity {


    GridView gridview = null;
    Button btMaps = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_list);


        btMaps = (Button) findViewById(R.id.bt_map);
        btMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControleArvore ca = new ControleArvore();
                ArrayList<Arvore> arvores = ca.obterTodasArvores();
                ArrayList<String> parametros = new ArrayList<String>(); //pos 0 = lat pos1 = long pos2 = desc arv
                for (int i = 0; i < arvores.size(); i++) {
                    try{
                        double a = Double.parseDouble(arvores.get(i).getLatitude());
                        double b = Double.parseDouble(arvores.get(i).getLongitude());

                        parametros.add(arvores.get(i).getLatitude());
                        parametros.add(arvores.get(i).getLongitude());
                        parametros.add(arvores.get(i).getGoogleMapsData());
                    }catch (Exception ex)
                    {
                        Util.exibeMensagem("Arvore "+arvores.get(i).getId()+" não tem uma localização valida",getApplicationContext());
                    }
                    parametros.add(arvores.get(i).getLatitude());
                    parametros.add(arvores.get(i).getLongitude());
                    parametros.add(arvores.get(i).getGoogleMapsData());


                }


                Bundle params = new Bundle();
                Intent secondActivity = new Intent(ArvoresList.this, MapsActivity.class);
                startActivity(secondActivity);


                params.putStringArrayList("dados", parametros);


                secondActivity.putExtras(params);
                startActivity(secondActivity);

            }
        });


        gridview = (GridView) findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.grid_item, getArvoresGrid());

        gridview.setVerticalSpacing(1);
        gridview.setHorizontalSpacing(1);

        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                String dados = ((TextView) v).getText().toString();
                int ida = obterIdGrid(dados);

                Toast.makeText(getApplicationContext(), "id:" + ida, Toast.LENGTH_SHORT).show();


                Bundle params = new Bundle();
                Intent secondActivity = new Intent(ArvoresList.this, ArvoresForm.class);
                startActivity(secondActivity);


                params.putInt("id", ida);
                secondActivity.putExtras(params);
                startActivity(secondActivity);


            }
        });
    }

    public void startArvoresForm(View view) {

        Intent secondActivity = new Intent(this, ArvoresForm.class);
        startActivity(secondActivity);
    }

    public String[] getArvoresGrid() {
        try {
            ControleArvore ca = new ControleArvore();
            ArrayList<Arvore> arvores = ca.obterTodasArvores();
            String[] retorno = new String[arvores.size()];
            for (int i = 0; i < arvores.size(); i++) {
                retorno[i] = "ID: " + arvores.get(i).getId() + "" +
                        "\nProprietario: " + arvores.get(i).getPropietario().getNome()+"" +
                        "\nEndereço: "+arvores.get(i).getEnderecoGeoCode()+"" +
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
            if (dados.charAt(i) != 'P' ) { //
                if (dados.charAt(i) == '0' || dados.charAt(i) == '1' || dados.charAt(i) == '2' || dados.charAt(i) == '3' || dados.charAt(i) == '4' || dados.charAt(i) == '5' || dados.charAt(i) == '6' || dados.charAt(i) == '7' || dados.charAt(i) == '8' || dados.charAt(i) == '9') {
                    ret += dados.charAt(i);

                }

            }
            if (dados.charAt(i) == 'P') {
                break;
            }

        }
        int retorno = Integer.parseInt("" + ret);
        return retorno;
    }


}
