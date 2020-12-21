package br.com.gmissio.truco.model;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    int numJogadores;
    Baralho baralho = new Baralho();
    List<Jogador> jogadores = new ArrayList<>();
    int pontosTimeA = 0;
    int pontosTimeB = 0;
    int numJogada;
    int posicaoBaralho = 0;
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
    public int getNumJogada() {
        return numJogada;
    }

    public int getPosicaoBaralho() {
        return posicaoBaralho;
    }

    public void setPosicaoBaralho(int posicaoBaralho) {
        this.posicaoBaralho = posicaoBaralho;
    }


    public void setNumJogada(int numJogada) {
        this.numJogada = numJogada;
    }

    public List<String> getControleRodada() {
        return controleRodada;
    }

    public void setControleRodada(List<String> controleRodada) {
        this.controleRodada = controleRodada;
    }


    public int getNumJogadores() {
        return numJogadores;
    }

    public void setNumJogadores(int numJogadores) {
        this.numJogadores = numJogadores;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public int getPontosTimeA() {
        return pontosTimeA;
    }

    public void setPontosTimeA(int pontosTimeA) {
        this.pontosTimeA = pontosTimeA;
    }

    public int getPontosTimeB() {
        return pontosTimeB;
    }

    public void setPontosTimeB(int pontosTimeB) {
        this.pontosTimeB = pontosTimeB;
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


    public void rodadaCpu() {
        numJogada = 1;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < numJogadores; j++) {

                System.out.println("Jogada numero: " + numJogada);
                System.out.println("Jogador " + jogadores.get(0).getNome() + " joga a carta " + jogadores.get(0).getCarta().toString());
                Jogador jogador = jogadores.get(0);
                jogadores.remove(0);
                jogadores.add(jogador);
                numJogada++;
            }

        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return jogadores.toString();
    }

    public Carta cpuPlayCard(){

        return jogadores.get(1).getCartas().get(controleRodada.size());
    }

    public boolean oneVsCpu(Carta cardPlayer, Carta cardCpu){
        String vencedor = "Empata";
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
                if(x == "Time A"){
                    timeA++;
                }else if(x == "Time B"){
                    timeB++;
                }
            }
            if(timeA > 1){
                pontosTimeA++;
                return true;
            }else if(timeB > 1){
                pontosTimeB++;
                return true;
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
