package com.example.tiago.anative.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.Form.SolicitacoesForm;
import com.example.tiago.anative.R;

public class SolicitacoesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao_list);
    }
    public void startSolicitacoesForm(View view) {

        Intent secondActivity = new Intent(this, SolicitacoesForm.class);
        startActivity(secondActivity);
    }
}
