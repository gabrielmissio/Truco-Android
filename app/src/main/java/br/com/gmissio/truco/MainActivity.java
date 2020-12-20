package br.com.gmissio.truco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.gmissio.truco.model.Carta;
import br.com.gmissio.truco.model.Jogador;
import br.com.gmissio.truco.model.Jogo;

public class MainActivity extends AppCompatActivity {

    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView imgCard1Player;
    ImageView imgCard2Player;
    ImageView imgCard3Player;
    ImageView imgCard1Cpu;
    ImageView imgCard2Cpu;
    ImageView imgCard3Cpu;
    TextView textView;
    Jogo jogo = new Jogo(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.imgCard1);
        card2 = findViewById(R.id.imgCard2);
        card3 = findViewById(R.id.imgCard3);

        imgCard1Player = findViewById(R.id.imgCard1Player);
        imgCard2Player = findViewById(R.id.imgCard2Player);
        imgCard3Player = findViewById(R.id.imgCard3Player);

        imgCard1Cpu = findViewById(R.id.imgCard1Cpu);
        imgCard2Cpu = findViewById(R.id.imgCard2Cpu);
        imgCard3Cpu = findViewById(R.id.imgCard3Cpu);


       // textView.append("mais essa");
        System.out.println("---------------------------------");
        carregarCartas();

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("card 1 -------------------");
                playCard(0);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("card 2 -------------------");
                playCard(1);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("card 3 -------------------");
                playCard(2);
            }
        });

    }

    public void carregarCartas(){
        jogo.getBaralho().gerarBaralho();
        for(int i = 0; i < 40; i++){

            Carta carta = jogo.getBaralho().getList_baralho().get(i);
            String referenceCardName = "card_" + carta.getNaipe() + "_" + carta.getNome();
            Resources resources = getResources();
            int resourceId = resources.getIdentifier(referenceCardName.replaceAll(" ", ""),
                    "drawable", getPackageName());
            carta.setIdImage(resourceId);
        }
        startGame();

    }

    public void startGame(){
        jogo.distribuirCartas();
        Jogador jogador = jogo.getJogadores().get(0);//index de jogadores sempre sera o user

        card1.setImageResource(jogador.getCartas().get(0).getIdImage());
        card2.setImageResource(jogador.getCartas().get(1).getIdImage());
        card3.setImageResource(jogador.getCartas().get(2).getIdImage());//cada jogador sempre começara com 3 cartas

        Jogador cpu = jogo.getJogadores().get(1);


    }

    public void playCard(int indexCard){

        int cardRodada = jogo.getControleRodada().size();
        Jogador jogador = jogo.getJogadores().get(0);
        Carta cartaJogada = jogador.getCartas().get(indexCard);
        Carta cartaCpu = jogo.cpuPlayCard();
//        switch (cardRodada){
//            case 0:
//                imgCard1Player.setVisibility(View.VISIBLE);
//                imgCard1Player.setImageResource(cartaJogada.getIdImage());
//                break;
//            case 1:
//                imgCard2Player.setVisibility(View.VISIBLE);
//                imgCard2Player.setImageResource(cartaJogada.getIdImage());
//                break;
//            case 2:
//                imgCard3Player.setVisibility(View.VISIBLE);
//                imgCard2Player.setImageResource(cartaJogada.getIdImage());
//                break;
//
//        }

        switch (indexCard){

            case 0:
                card1.setVisibility(View.INVISIBLE);
                switch (cardRodada){

                    case 0:
                        imgCard1Player.setVisibility(View.VISIBLE);
                        imgCard1Player.setImageResource(cartaJogada.getIdImage());
                        break;
                    case 1:
                        imgCard2Player.setVisibility(View.VISIBLE);
                        imgCard2Player.setImageResource(cartaJogada.getIdImage());
                        break;
                    case 2:
                        imgCard3Player.setVisibility(View.VISIBLE);
                        imgCard2Player.setImageResource(cartaJogada.getIdImage());
                        break;

                }
                break;
            case 1:
                card2.setVisibility(View.INVISIBLE);
                switch (cardRodada){

                    case 0:
                        imgCard1Player.setVisibility(View.VISIBLE);
                        imgCard1Player.setImageResource(cartaJogada.getIdImage());
                        break;
                    case 1:
                        imgCard2Player.setVisibility(View.VISIBLE);
                        imgCard2Player.setImageResource(cartaJogada.getIdImage());
                        break;
                    case 2:
                        imgCard3Player.setVisibility(View.VISIBLE);
                        imgCard2Player.setImageResource(cartaJogada.getIdImage());
                        break;

                }
                break;
            case 2:
                card3.setVisibility(View.INVISIBLE);
                switch (cardRodada){

                    case 0:
                        imgCard1Player.setVisibility(View.VISIBLE);
                        imgCard1Player.setImageResource(cartaJogada.getIdImage());
                        break;
                    case 1:
                        imgCard2Player.setVisibility(View.VISIBLE);
                        imgCard2Player.setImageResource(cartaJogada.getIdImage());
                        break;
                    case 2:
                        imgCard3Player.setVisibility(View.VISIBLE);
                        imgCard2Player.setImageResource(cartaJogada.getIdImage());
                        break;

                }
                break;

        }

    }


}/*

 Resources resources = getResources();
            int resourceId = resources.getIdentifier(referenceCardName.replaceAll(" ", ""),
                    "drawable", getPackageName());
            carta.setIdImage(resourceId);
*/