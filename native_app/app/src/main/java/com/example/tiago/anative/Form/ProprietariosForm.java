package com.example.tiago.anative.Form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.tiago.anative.Controle.ControleCidade;
import com.example.tiago.anative.Controle.ControleProprietario;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.Modelo.Cidade;
import com.example.tiago.anative.Modelo.Especie;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;

import java.util.ArrayList;

public class ProprietariosForm extends AppCompatActivity {

    EditText id = null;
    EditText nome = null;
    EditText identificacao = null;
    EditText rua = null;
    AutoCompleteTextView cidade = null;
    Button salvar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietario_form);


        id = (EditText) findViewById(R.id.idproprietario);
        nome = (EditText) findViewById(R.id.nome);
        identificacao = (EditText) findViewById(R.id.identificacao);
        rua = (EditText) findViewById(R.id.end_rua);

        cidade = (AutoCompleteTextView) findViewById(R.id.cidade_idcidade);
        definirCidades();
        salvar = (Button) findViewById(R.id.bt_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarProprietario();
            }
        });


        //update proprietario


        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if (params != null) {

            int idp = params.getInt("id");
            //System.out.println("o parametro que recebi foi"+idarvore);
            ControleProprietario ca = new ControleProprietario();
            Proprietario p = ca.obterProprietario(idp);
            id.setText("" + p.getId());
            nome.setText("" + p.getNome());
            identificacao.setText("" + p.getIdentificacao());
            rua.setText("" + p.getEnderecoRua());
            cidade.setText("Id - " + p.getCidade().getId() + " - " + p.getCidade().getNome());


        }


    }


    public void salvarProprietario() {
        if (validarDados()) {
            Proprietario p = new Proprietario();
            p.setId(Integer.parseInt(id.getText().toString()));
            p.setNome(nome.getText().toString());
            p.setIdentificacao(identificacao.getText().toString());
            p.setEnderecoRua(rua.getText().toString());
            Cidade c = new Cidade();
            String idc = cidade.getText().toString().split("-")[1].trim();

            c.setId(Integer.parseInt(idc));
            p.setCidade(c);
            p.setLatitude("999");
            p.setLongitude("999");

            ControleProprietario ca = new ControleProprietario();
            try {
                if (ca.salvarProprietario(p)) {
                    Util.exibeMensagem("Registro salvo com sucesso", getApplicationContext());
                } else Util.exibeMensagem("Erro na ao salvar", getApplicationContext());
            } catch (Exception exception) {
                Util.exibeMensagem("Erro na conexao, tente novamente", getApplicationContext());

            }

        }

    }

    public Boolean validarDados() {
        ArrayList<Boolean> arrayValidacao = new ArrayList<Boolean>();
        String retorno = "São obrigatórios:";
        if (nome.getText().toString().length() < 1) {
            retorno += "\n Nome";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }

        if (identificacao.getText().toString().length() < 1) {
            retorno += "\n Identificação";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }
        if (rua.getText().toString().length() < 1) {
            retorno += "\n Rua";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }
        if (cidade.getText().toString().length() < 1) {
            retorno += "\n Cidade";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }


   if (arrayValidacao.contains(false)) {
       Util.exibeMensagem(retorno, getApplicationContext());

       return false;
        } else return true;
    }

    private void definirCidades() {

        ControleCidade ce = new ControleCidade();
        ArrayList<Cidade> array = ce.obterTodasCidades();


        String[] listaItens = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            listaItens[i] = "Id- " + array.get(i).getId() + " - " + array.get(i).getNome();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, listaItens);
        cidade.setAdapter(adapter);

    }


}
