package com.example.tiago.anative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.tiago.anative.Controle.ControleUsuario;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.Modelo.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginForm extends AppCompatActivity {


    AutoCompleteTextView login = null;
    EditText senha = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        login = (AutoCompleteTextView) findViewById(R.id.et_login);
        senha = (EditText) findViewById(R.id.et_senha);

    }

    public void startSecondActivity(View view) {
        if (validarDados()) {
            if (validarUsuario()) {
                Intent secondActivity = new Intent(this, MenuList.class);
                startActivity(secondActivity);
            }else Util.exibeMensagem("Usuario ou senha incorreto",getApplicationContext());
        }
    }


    public Boolean validarUsuario() {
        Usuario usuario = new Usuario();
        ControleUsuario cu = new ControleUsuario();

        usuario.setLogin(login.getText().toString());
        usuario.setSenha(senha.getText().toString());
        //autenticar

        if (cu.logar(usuario)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validarDados() {
        ArrayList<Boolean> arrayValidacao = new ArrayList<Boolean>();
        String retorno = "";
        if (login.getText().toString().length() < 1) {
            retorno = "Login é obrigatório";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }

        if (senha.getText().toString().length() < 1) {
            retorno += "\nSenha é obrigatório";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }
        if (arrayValidacao.contains(false)) {
            Util.exibeMensagem(retorno, getApplicationContext());

            return false;
        } else return true;
    }
}

