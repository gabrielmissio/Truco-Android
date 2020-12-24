package br.com.gmissio.truco.model;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    int numJogadores;
    Baralho baralho = new Baralho();
    List<Jogador> jogadores = new ArrayList<>();
    int pontosTimeA = 0;
    int pontosTimeB = 0;
    int numJogada = 0;
    int posicaoBaralho = 0;
    int vez = 0;
    List<String> controleRodada = new ArrayList<>();


    public Jogo() {

    }

    public Jogo(int numJogadores) {
        this.numJogadores = numJogadores;
        if(numJogadores%2 != 0) {
            //Lancar exeption
            System.out.println("num de jogadores invalido");
        }else {
            for(int i = 0; i < numJogadores; i++) {
                String time = "";
                if(i%2 == 0) {
                    time = "Time A";
                }else {
                    time = "Time B";
                }
                Jogador jogador = new Jogador(time, String.valueOf(i));
                jogadores.add(jogador);
            }
        }
        System.out.println("Jogadores criados com sucesso!");
    }

    public int getPosicaoBaralho() {
        return posicaoBaralho;
    }

    public List<String> getControleRodada() {
        return controleRodada;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public int getPontosTimeA() {
        return pontosTimeA;
    }

    public int getPontosTimeB() {
        return pontosTimeB;
    }

    public int getVez() {
        return vez;
    }

    public void setVez(int vez) {
        this.vez = vez;
    }

    public void distribuirCartas() {

        System.out.println("distribui###");
        System.out.println(baralho.toString());
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < numJogadores; j++) {
                jogadores.get(j).addCarta(baralho.getCarta());
            }
        }

    }

    public void reserControleRodada(){

        this.controleRodada.clear();
    }

    @Override
    public String toString() {

        return jogadores.toString();
    }

    public Carta cpuPlayCard(){

        if(controleRodada.size() < 2){
            return jogadores.get(1).getCartas().get(controleRodada.size());
        }
        return jogadores.get(1).getCartas().get(2);
    }

    public int compareCards(Carta cardPlayer, Carta cardCpu){
        if(cardPlayer.getPeso() > cardCpu.getPeso()){
            return 1;
        }else if(cardPlayer.getPeso() < cardCpu.getPeso()){
            return -1;
        }
        return 0;
    }

    public boolean oneVsCpu(Carta cardPlayer, Carta cardCpu){
        if(cardPlayer.getPeso() > cardCpu.getPeso()){
            controleRodada.add("Time A");
        }else if(cardPlayer.getPeso() < cardCpu.getPeso()){
            controleRodada.add("Time B");
        }else{
            controleRodada.add("Empardou");
        }
        return checkVitory();

    }

    private boolean checkVitory(){
        int timeA = 0;
        int timeB = 0;
        if(controleRodada.size() > 1){
            for (String x : controleRodada) {
                if(x.equals("Time A")){
                    timeA++;
                }else if(x.equals("Time B")){
                    timeB++;
                }
            }
            this.vez = 0;
            if(timeA > 1){
                pontosTimeA++;
                return true;
            }else if(timeB > 1){
                pontosTimeB++;
                return true;
            }else if(controleRodada.size() == 3){
                if(controleRodada.get(0).equals("Time A")){
                    pontosTimeA++;
                }else if(controleRodada.get(0).equals("Time B")){
                    pontosTimeB++;
                }else if(controleRodada.get(1).equals("Time A")){
                    pontosTimeA++;
                }else if(controleRodada.get(1).equals("Time B")){
                    pontosTimeB++;
                }else if(controleRodada.get(2).equals("Tima A")){
                    pontosTimeA++;
                }else if(controleRodada.get(2).equals("Time B")){
                    pontosTimeB++;
                }
                return true;
            } else if(controleRodada.contains("Empardou")) {
                if(controleRodada.contains("Time A")){
                    pontosTimeA++;
                    return true;
                }else if(controleRodada.contains("Time B")){
                    pontosTimeB++;
                    return true;
                }
            }
        }
        return false;

    }

    public void devolverCartas(){
        for (Jogador j: this.jogadores) {
            j.getCartas().clear();
        }
        posicaoBaralho++;
    }

}
