package com.example.tiago.anative.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.R;

public class MenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
    }
    public void startArvoresList(View view) {

        Intent secondActivity = new Intent(this, ArvoresList.class);
        startActivity(secondActivity);
    }
    public void startProprietariosList(View view) {

        Intent secondActivity = new Intent(this, ProprietariosList.class);
        startActivity(secondActivity);
    }
    public void startServicosList(View view) {

        Intent secondActivity = new Intent(this, ServicosList.class);
        startActivity(secondActivity);
    }
    public void startSolicitacoesList(View view) {

        Intent secondActivity = new Intent(this, SolicitacoesList.class);
        startActivity(secondActivity);
    }
}
