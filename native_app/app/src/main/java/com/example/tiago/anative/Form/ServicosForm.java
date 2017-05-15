package com.example.tiago.anative.Form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tiago.anative.R;

public class ServicosForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_form);
    }
    public void startServicosForm(View view) {

        Intent secondActivity = new Intent(this, ServicosForm.class);
        startActivity(secondActivity);
    }

}
