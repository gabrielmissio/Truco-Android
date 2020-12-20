package br.com.gmissio.truco.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {

    public List<Carta> getList_baralho() {
        return list_baralho;
    }

    List<Carta> list_baralho = new ArrayList<>();

    public Baralho() {

    }

    public void gerarBaralho() {
        list_baralho.clear();
        for(int i = 0; i < 4; i++) {
            for(int j = 4; j < 13; j++) {//cartas do 4 ao 12 (sem 8 e 9 eh claro)
                if(!(j == 8 || j == 9)) {
                    Carta carta = new Carta(String.valueOf(j), j-3, i);
                    list_baralho.add(carta);
                }//7 de faca 4 de tras para frente index 24
                //index 12 7 belo
            }
            for(int j = 1; j < 4; j++) {//cartas do um ao 3
                if(!((j == 1 && i == 3) || (j == 1 && i == 2))) {//12 peso = 9
                    Carta carta = new Carta(String.valueOf(j), j + 9, i);
                    list_baralho.add(carta);
                }
            }
        }
        list_baralho.remove(32);
        list_baralho.remove(13);
        cartasEspeciais();
        System.out.println(list_baralho.toString());
        System.out.println(list_baralho.size());
    }

    //0 - copas | 1 - ouro | 2 - paus | 3 - espada
    private void cartasEspeciais() {
        //String nome, int peso, int naipe
        Carta espadadao = new Carta("3", 16, 3);
        Carta pauzao = new Carta("2", 15, 2);
        Carta sete_faca = new Carta("7", 14, 3);
        Carta sete_belo = new Carta("7", 13, 1);
        list_baralho.add(espadadao);
        list_baralho.add(pauzao);
        list_baralho.add(sete_belo);
        list_baralho.add(sete_faca);

        Collections.shuffle(list_baralho);//embaralha o baralho
        System.out.println("Baralho->>>> " + list_baralho.toString());
    }


    public Carta getCarta() {
        System.out.println("####################");
        Carta carta = list_baralho.get(0);
        list_baralho.remove(0);
        System.out.println(carta.toString());
        return carta;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return list_baralho.toString();
    }
}
