package com.example.tiago.anative.Form;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tiago.anative.CameraActivity;
import com.example.tiago.anative.Controle.ControleArvore;
import com.example.tiago.anative.Controle.ControleEspecie;
import com.example.tiago.anative.Controle.ControleProprietario;
import com.example.tiago.anative.Controle.Util;
import com.example.tiago.anative.List.MenuList;
import com.example.tiago.anative.List.SolicitacoesList;
import com.example.tiago.anative.MapsActivity;
import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.Modelo.Especie;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArvoresForm extends AppCompatActivity implements LocationListener {


    EditText id = null;
    EditText latitude = null;
    EditText longitude = null;
    EditText idade = null;
    EditText geocode = null;
    AutoCompleteTextView especie = null;
    AutoCompleteTextView proprietario = null;
    CheckBox ativo = null;

    Button salvar = null;
    Button btGps = null;
    Button verMapa = null;

    LocationManager lm = null; // usado para pegar posição gps
    boolean atualizouLoc = false; //usado para vefificar se a posição gps foi atualizad
    Arvore v = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvores_form);


        id = (EditText) findViewById(R.id.id);
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.logetude);
        idade = (EditText) findViewById(R.id.idade);
        geocode = (EditText) findViewById(R.id.geocode);
        especie = (AutoCompleteTextView) findViewById(R.id.especie_idespecie);
        definirEspecies(); //carrega especies para o AutoCompleteTextView

        proprietario = (AutoCompleteTextView) findViewById(R.id.proprietaro);
        defineProprietarios();  //carrega proprietarios para o AutoCompleteTextView

        salvar = (Button) findViewById(R.id.btn_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarArvore();
            }
        });
        btGps = (Button) findViewById(R.id.bt_gps);
        btGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attLocalizacao();
            }
        });


        ativo = (CheckBox) findViewById(R.id.cb_ativo);
        verMapa = (Button) findViewById(R.id.bt_ver_mapa);
        verMapa.setClickable(false);
        verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMapa();
            }
        });


        //update arvore recebdno parametro


        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if (params != null) {
            verMapa.setClickable(true);
            int idarvore = params.getInt("id");
            ControleArvore ca = new ControleArvore();
            v = ca.consultarArvore(idarvore);
            id.setText("" + v.getId());
            idade.setText("" + v.getIdade());
            longitude.setText("" + v.getLongitude());
            latitude.setText("" + v.getLatitude());
            geocode.setText("" + v.getEnderecoGeoCode());
            especie.setText("Id- " + v.getEspecie().getId() + " - " + v.getEspecie().getNome());
                        proprietario.setText("Id - " + v.getPropietario().getId() + " - " + v.getPropietario().getNome());
            ativo.setChecked(true);
            if (v.getStatus().equalsIgnoreCase("Ativo")) {
                ativo.setChecked(true);
            } else {
                ativo.setChecked(false);
            }

        }

    }

    private void abrirMapa() {
        Bundle params = new Bundle();
        Intent secondActivity = new Intent(ArvoresForm.this, MapsActivity.class);
        startActivity(secondActivity);


        ArrayList<String> parametros = new ArrayList<String>(); //pos 0 = lat pos1 = long pos2 = desc arv

        parametros.add(0, latitude.getText().toString());
        parametros.add(1, longitude.getText().toString());
        try {
            ControleArvore ca = new ControleArvore();
            Arvore a = ca.consultarArvore(Integer.parseInt(id.getText().toString()));
            parametros.add(a.getGoogleMapsData());

        } catch (Exception ex) {
            parametros.add(2, "Não foi possivel consultar");

        }
        params.putStringArrayList("dados", parametros);


        secondActivity.putExtras(params);
        startActivity(secondActivity);

    }

    private void defineProprietarios() {
        ControleProprietario cp = new ControleProprietario();
        ArrayList<Proprietario> arrayProprietarios = cp.obterTodosProprietarios();
        String[] listaItensP = new String[arrayProprietarios.size()];
        for (int i = 0; i < arrayProprietarios.size(); i++) {
            listaItensP[i] = "Id - " + arrayProprietarios.get(i).getId() + " - " + arrayProprietarios.get(i).getNome();
        }

        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(this, R.layout.item, listaItensP);
        proprietario.setAdapter(adapterP);
    }

    private void definirEspecies() {

        ControleEspecie ce = new ControleEspecie();
        ArrayList<Especie> arrayEspecies = ce.obterTodasEspecies();


        String[] listaItens = new String[arrayEspecies.size()];
        for (int i = 0; i < arrayEspecies.size(); i++) {
            listaItens[i] = "Id- " + arrayEspecies.get(i).getId() + " - " + arrayEspecies.get(i).getNome();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, listaItens);
        especie.setAdapter(adapter);

    }


    public void startList(View view) {

        Intent secondActivity = new Intent(this, MenuList.class);
        startActivity(secondActivity);

    }


    public void attLocalizacao() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); //esse metodo só pega a ultima localizacao conhecida, não é o caso
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this); // pedir atualizacao localizacao e aguardar
            Util.exibeMensagem("Pedido atualizacao de GPS." +
                    "\nAguarde atualização da localização." +
                    "\nGps não funciona em locais fechados!", getApplicationContext());


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ArvoresForm.this
                    , Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else { //pedir permissão em tempo de execução
                ActivityCompat.requestPermissions(ArvoresForm.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }

            Util.exibeMensagem("Erro de permissão", getApplicationContext());
        }


    }


    private void salvarArvore() {

        if (validarDados()) {
            Arvore a = new Arvore();
            a.setId(Integer.parseInt(id.getText().toString()));
            a.setEnderecoGeoCode(geocode.getText().toString());
            Especie e = new Especie();
            String ide = especie.getText().toString().split("-")[1].trim();
            e.setId(Integer.parseInt(ide));
            a.setEspecie(e);

            a.setIdade(Integer.parseInt(idade.getText().toString()));
            a.setLatitude(latitude.getText().toString());
            a.setLongitude(longitude.getText().toString());
            Proprietario p = new Proprietario();
            String idp = proprietario.getText().toString().split("-")[1].trim();
            p.setId(Integer.parseInt(idp));
            a.setPropietario(p);
            a.setStatus("Ativo");
            if (ativo.isChecked()) {
                a.setStatus("Ativo");
            } else {
                a.setStatus("Inativo");

            }

            ControleArvore ca = new ControleArvore();
            try {
                if (ca.salvarArvore(a)) {
                    Util.exibeMensagem("Registro salvo com sucesso", getApplicationContext());
                } else
                    Util.exibeMensagem("Erro na ao salvar, verifique os dados", getApplicationContext());
            } catch (Exception exception) {
                Util.exibeMensagem("Erro na conexao, ex" + exception, getApplicationContext());
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) { //garante que foi atualizada a loc
        Util.exibeMensagem("Location update!", getApplicationContext());
        Util.exibeMensagem("" + location.getLongitude() + " , " + location.getLatitude(), getApplicationContext());
        if (location != null) {
            //  Util.exibeMensagem("Location update!", getApplicationContext());
            latitude.setText("" + location.getLatitude());
            longitude.setText("" + location.getLongitude());


            Geocoder geocoder = new Geocoder(ArvoresForm.this);
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String gc = addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2) + ",  " + addresses.get(0).getAddressLine(3) + ", ";
                geocode.setText("" + gc);
            } catch (IOException e) {
                Util.exibeMensagem("Erro: " + e, getApplicationContext());
                geocode.setText("Não foi possivel obter GeoCode");
                e.printStackTrace();
            }
            atualizouLoc = true;


            stopLocationUpdates();            // parar de pedir atualizacao


        } else
            Util.exibeMensagem("Sem latitude e longitude", getApplicationContext());

    }

    public void stopLocationUpdates() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            lm.removeUpdates(ArvoresForm.this);

            Util.exibeMensagem("Parei de pedir atualização", getApplicationContext());


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ArvoresForm.this
                    , Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(ArvoresForm.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }

            Util.exibeMensagem("Erro de permissão", getApplicationContext());
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Util.exibeMensagem(("GPS available again\n"), getApplicationContext());
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Util.exibeMensagem(("GPS out of service\n"), getApplicationContext());
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Util.exibeMensagem(("GPS temporarily unavailable\n"), getApplicationContext());
                break;
        }

    }

    @Override
    public void onProviderEnabled(String provider) {
        Util.exibeMensagem("Provider enabled!", getApplicationContext());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Util.exibeMensagem("Provider disabled!", getApplicationContext());
    }


    public Boolean validarDados() {
        ArrayList<Boolean> arrayValidacao = new ArrayList<Boolean>();
        String retorno = "São obrigatórios:"+"";
        if (geocode.getText().toString().length() < 1) {
            retorno += "\n GeoCode";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }

        if (idade.getText().toString().length() < 1) {
            retorno += "\n Idade";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }

        if (especie.getText().toString().length() < 1) {
            retorno += "\n Espécie ";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }

        if (proprietario.getText().toString().length() < 1) {
            retorno += "\n Proprietário ";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }

        if (id.getText().toString().length() < 1) {
            retorno += "\n ID";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }


        if (latitude.getText().toString().length() < 1) {
            retorno += "\n Latitude";
            arrayValidacao.add(false);
        } else {

            arrayValidacao.add(true);
        }
        if (longitude.getText().toString().length() < 1) {
            retorno += "\n Longitude";
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
