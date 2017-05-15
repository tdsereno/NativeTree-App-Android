package com.example.tiago.anative.Controle;

import com.example.tiago.anative.Modelo.Arvore;
import com.example.tiago.anative.Modelo.Cidade;
import com.example.tiago.anative.Modelo.Especie;
import com.example.tiago.anative.Modelo.Proprietario;
import com.example.tiago.anative.Modelo.Usuario;

import java.util.ArrayList;

/**
 * Created by Tiago on 15/05/2016.
 */
public class Interpretador {

    private String caracterDivisor = "@#"; // caracter que faz a divisao dos campos retornados pelo servidor web

    //recebe o retorno do servidor e retorna um ArrayList com todas arvores
    public ArrayList<Arvore> LerArvoresWeb(String retornoWeb) {
        // no servidor web vem cada arvore vem assim -->
        //  arvore@#1@#1@#30@#13133.333@#1244.444@#Rua ABC, LajeadoAlter11@#1@#1@#morta

        String[] vetArvores = retornoWeb.split("arvore"); //cada posicao do vetor ira ter os dados de uma arvore
        ArrayList<Arvore> arvores = new ArrayList();
        for (int i = 1; i < vetArvores.length; i++) {
            arvores.add(DecodificarArvore(vetArvores[i]));

        }

        return arvores;
    }

    // usado para transformar uma linha do trecho do codigo recebido do servidor, em um Objeto Arvore
    private Arvore DecodificarArvore(String arvoreCodifica) {
        String[] arvores = arvoreCodifica.split(caracterDivisor);
        Arvore v = new Arvore();
        v.setId(Integer.parseInt(arvores[1]));
        ControleEspecie ce = new ControleEspecie();

        Especie p = new Especie();
        p.setId(Integer.parseInt(arvores[2]));
        p.setNome(arvores[10]);
        v.setEspecie(p);
        v.setIdade(Integer.parseInt(arvores[3]));
        v.setLatitude(arvores[4]);
        v.setLongitude(arvores[5]);
        v.setEnderecoGeoCode(arvores[6]);
        Proprietario prop = new Proprietario();

        ControleProprietario cp = new ControleProprietario();
        prop.setId(Integer.parseInt(arvores[7]));
        prop.setNome(arvores[9]);
        v.setPropietario(prop);
        v.setStatus(arvores[8]);

        return v;
    }


    public ArrayList<Especie> LerEspeciesWeb(String retornoWeb) {

        String[] vetEspecies = retornoWeb.split("especie"); //cada posicao do vetor ira ter os dados de uma arvore
        ArrayList<Especie> especies = new ArrayList();
        for (int i = 1; i < vetEspecies.length; i++) {
            especies.add(DecodificarEspecie(vetEspecies[i]));


        }

        return especies;
    }

    private Especie DecodificarEspecie(String especieCodifica) {
        String[] especies = especieCodifica.split(caracterDivisor);
        Especie e = new Especie();
        e.setId(Integer.parseInt(especies[1]));
        e.setNome(especies[2]);
//        e.setDscricao(especies[3]);
        return e;

    }


    public ArrayList<Proprietario> LerProprietariosWeb(String retornoWeb) {

        String[] vetProprietario = retornoWeb.split("proprietario"); //cada posicao do vetor ira ter os dados de uma arvore
        ArrayList<Proprietario> proprietarios = new ArrayList();
        for (int i = 1; i < vetProprietario.length; i++) {
            proprietarios.add(DecodificarProprietario(vetProprietario[i]));


        }

        return proprietarios;
    }


    private Proprietario DecodificarProprietario(String proprietarioCodifica) {
        String[] proprietarios = proprietarioCodifica.split(caracterDivisor);
        Proprietario p = new Proprietario();
        p.setId(Integer.parseInt(proprietarios[1]));
        p.setNome(proprietarios[2]);
        p.setIdentificacao(proprietarios[3]);
        p.setEnderecoRua(proprietarios[4]);
        Cidade c = new Cidade();
        c.setId(Integer.parseInt(proprietarios[5]));
        c.setNome(proprietarios[6]);
        p.setCidade(c);
        return p;

    }


    public ArrayList<Cidade> LerCidadesWeb(String retornoWeb) {



        String[] vet = retornoWeb.split("cidade"); //cada posicao do vetor ira ter os dados de uma cidade
        ArrayList<Cidade> cidades = new ArrayList();
        for (int i = 1; i < vet.length; i++) {
            cidades.add(DecodificarCidade(vet[i]));


        }

        return cidades;
    }

    private Cidade DecodificarCidade(String cidadeCodifica) {
        String[] cidades = cidadeCodifica.split(caracterDivisor);
        Cidade e = new Cidade();
        e.setId(Integer.parseInt(cidades[1]));
        e.setNome(cidades[2]);
//        e.setDscricao(especies[3]);
        return e;

    }
}

