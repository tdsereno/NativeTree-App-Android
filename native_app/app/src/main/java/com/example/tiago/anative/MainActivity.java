package com.example.tiago.anative;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiago.anative.Controle.ConexaoWeb;
import com.example.tiago.anative.Controle.ControleArvore;
import com.example.tiago.anative.Controle.ControleEspecie;
import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.Modelo.Especie;
import com.example.tiago.anative.Modelo.Proprietario;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView tv = null;
    Button btSalvarArvore = null;
    Button btConsultarArvores = null;
    Button btConsultaIndividual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConexaoWeb conex = new ConexaoWeb();
        tv = (TextView) findViewById(R.id.tv_conex);
        btSalvarArvore = (Button) findViewById(R.id.bt_salvar_arvore);
        btConsultaIndividual = (Button) findViewById(R.id.bt_consulta_individual);
        btConsultarArvores = (Button) findViewById(R.id.bt_consultar_arvores);
        btConsultarArvores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //consulta todas arovres e exibe em forma de texto na tela, usado para testes da comunicação e interpretação dos objetos
                consultaArvores();
            }

        });

btSalvarArvore.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        salvarArvore();
    }
});

    btConsultaIndividual.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            consultarArvoreIndividual(2);
        }
    });
    }

    private void consultarArvoreIndividual(int id) {
ControleArvore ca = new ControleArvore();
        Arvore a = ca.consultarArvore(id);
        String retorno = "Arvore "  + ":\n id: " + a.getId() + "\n" + "latitude:" + a.getLatitude() + "\n" + "longitude:" + a.getLongitude() + "\n GeoCode:" + a.getEnderecoGeoCode() + "\n nome Especie " + a.getEspecie().getNome() + "\n Proprietario: " + a.getPropietario().getNome();
tv.setText(retorno);


    }


    private  void salvarArvore()
    {
        Arvore a = new Arvore();
        a.setId(0);
        a.setEnderecoGeoCode("Rua QWERTYUIOP, Numero 12234, Bairro LKJHGF, ");
        Especie e = new Especie();
        e.setId(1);
        a.setEspecie(e);
        a.setIdade(99);
        a.setLatitude("999");
        a.setLongitude("888");
        Proprietario p = new Proprietario();
        p.setId(1);
        a.setPropietario(p);
        a.setStatus("Viva");
ControleArvore ca = new ControleArvore();
        if(ca.salvarArvore(a)){
        tv.setText("Arvore cadastrada");
    } else tv.setText("Erro ao cadastrar arvore");
    }

    private void consultaArvores() {
        ControleArvore ce = new ControleArvore();
        ArrayList<Arvore> arvores = ce.obterTodasArvores();
        String retorno = "Dados: \n";
        for (int i = 0; i < arvores.size(); i++) {
            retorno += "Arvore " + (i + 1) + ":\n id: " + arvores.get(i).getId() + "\n" + "latitude:" + arvores.get(i).getLatitude() + "\n" + "longitude:" + arvores.get(i).getLongitude() + "\n GeoCode:" + arvores.get(i).getEnderecoGeoCode() + "\n nome Especie " + arvores.get(i).getEspecie().getNome() + "\n Proprietario: " + arvores.get(i).getPropietario().getNome();
            retorno += "\n";


        }

        tv.setText(retorno);

    }
}