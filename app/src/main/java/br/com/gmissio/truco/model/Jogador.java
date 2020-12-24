package br.com.gmissio.truco.model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {

    List<Carta> cartas = new ArrayList<>();
    String time;
    String nome;

    public Jogador() {

    }

    public Jogador(String time, String nome) {
        this.time = time;
        this.nome = nome;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void addCarta(Carta carta) {
        cartas.add(carta);
    }

    public Carta getCarta() {
        if(this.cartas.size() > 0 ) {
            Carta carta = this.cartas.get(0);
            this.cartas.remove(0);
            return carta;
        }
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Time: " + this.time + " cartas: " + this.cartas.toString() + "\n";
    }
}
