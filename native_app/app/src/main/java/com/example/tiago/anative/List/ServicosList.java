package com.example.tiago.anative.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.Form.ServicosForm;
import com.example.tiago.anative.R;

public class ServicosList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_list);
    }
    public void startServicosForm(View view) {

        Intent secondActivity = new Intent(this, ServicosForm.class);
        startActivity(secondActivity);
    }
}
