package br.com.gmissio.truco.model;

public class Carta {
    String nome;
    int peso;
    int naipe;//0 - copas | 1 - ouro | 2 - paus | 3 - espada

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    private int idImage;
    //camiho ou value of the image

    public Carta() {

    }

    public Carta(String nome, int peso, int naipe) {
        this.nome = nome;
        this.peso = peso;
        this.naipe = naipe;
    }

    public int getNaipe() {
        return naipe;
    }

    public void setNaipe(int naipe) {
        this.naipe = naipe;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "nome: " + this.nome + " | peso: " + this.peso + " | niepe: " + this.naipe + " ||| ";
    }
}
