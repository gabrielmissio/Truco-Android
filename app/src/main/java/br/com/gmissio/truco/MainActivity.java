package br.com.gmissio.truco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gmissio.truco.model.Carta;
import br.com.gmissio.truco.model.Jogador;
import br.com.gmissio.truco.model.Jogo;

public class MainActivity extends AppCompatActivity {

    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView imgCard4;
    ImageView imgCard5;
    ImageView imgCard6;
    ImageView imgCard1Player;
    ImageView imgCard2Player;
    ImageView imgCard3Player;
    ImageView imgCard1Cpu;
    ImageView imgCard2Cpu;
    ImageView imgCard3Cpu;
    TextView pontosTimeA;
    TextView pontosTimeB;
    ImageView cardsInCpuSide;
    ImageView cardsInYouSide;
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

        imgCard4 = findViewById(R.id.imgCard4);
        imgCard5 = findViewById(R.id.imgCard5);
        imgCard6 = findViewById(R.id.imgCard6);

        pontosTimeA = findViewById(R.id.pontosTimeA);
        pontosTimeB = findViewById(R.id.pontosTimeB);

        cardsInCpuSide = findViewById(R.id.cardsInCpuSide);
        cardsInYouSide = findViewById(R.id.cardsInYouSide);

       // textView.append("mais essa");
        System.out.println("---------------------------------");
        carregarCartas();

        card1.setOnClickListener(view -> {
            System.out.println("card 1 -------------------");
            playCard(0);
        });

        card2.setOnClickListener(view -> {
            System.out.println("card 2 -------------------");
            playCard(1);
        });

        card3.setOnClickListener(view -> {
                System.out.println("card 3 -------------------");
                playCard(2);

        });

        card1.setOnLongClickListener(view -> {
            tostCard(0);
            alertEnvido();
            return false;
        });

        card2.setOnLongClickListener(view -> {
            alertTruco();
            tostCard(1);
            return false;
        });

        card3.setOnLongClickListener(view -> {
            tostCard(2);
            return false;
        });

    }

    public void carregarImagensCartas(){
        for(int i = 0; i < 40; i++){

            Carta carta = jogo.getBaralho().getList_baralho().get(i);
            String referenceCardName = "card_" + carta.getNaipe() + "_" + carta.getNome();
            Resources resources = getResources();
            int resourceId = resources.getIdentifier(referenceCardName.replaceAll(" ", ""),
                    "drawable", getPackageName());
            carta.setIdImage(resourceId);
        }
    }


    public void carregarCartas(){
        jogo.getBaralho().gerarBaralho();
        carregarImagensCartas();
        startGame();

    }

    public void startGame(){
        jogo.distribuirCartas();
        Jogador jogador = jogo.getJogadores().get(0);//index de jogadores sempre sera o user

        card1.setImageResource(jogador.getCartas().get(0).getIdImage());
        card2.setImageResource(jogador.getCartas().get(1).getIdImage());
        card3.setImageResource(jogador.getCartas().get(2).getIdImage());//cada jogador sempre começara com 3 cartas

        System.out.println("-------------------aquis");

    }

    public void playCard(int indexCard){

        int cardRodada = jogo.getControleRodada().size();
        Jogador jogador = jogo.getJogadores().get(0);
        Carta cartaJogada = jogador.getCartas().get(indexCard);
        Carta cartaCpu = jogo.cpuPlayCard();

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
                        imgCard3Player.setImageResource(cartaJogada.getIdImage());
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
                        imgCard3Player.setImageResource(cartaJogada.getIdImage());
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
                        imgCard3Player.setImageResource(cartaJogada.getIdImage());
                        break;

                }
                break;

        }
        new android.os.Handler().postDelayed(
                () -> {
                    cpuJoga();
                    if(jogo.oneVsCpu(cartaJogada, cartaCpu)){
                        System.out.println("Pontooooo: " + jogo.getPontosTimeA() + " --- " + jogo.getPontosTimeB());
                        pontosTimeA.setText(String.valueOf(jogo.getPontosTimeA()));
                        pontosTimeB.setText(String.valueOf(jogo.getPontosTimeB()));
                        if(jogo.getVez() == 0){
                            new android.os.Handler().postDelayed(
                                    this::reset,
                                    500);
                        }

                    }
                    Toast toast = Toast.makeText(getApplicationContext(),String.valueOf(jogo.getControleRodada().size() ), Toast.LENGTH_LONG);
                    toast.show();
                    if(jogo.compareCards(cartaJogada, cartaCpu) == 1){
                        jogo.setVez(0);
                    }else if (jogo.compareCards(cartaJogada, cartaCpu) == -1){
                        jogo.setVez(1);
                        new android.os.Handler().postDelayed(
                                this::cpuJoga,
                                800);
                    }else if(jogo.compareCards(cartaJogada, cartaCpu) == 0 && jogo.getVez() == 1){
                        new android.os.Handler().postDelayed(
                                this::cpuJoga,
                                800);
                    }
                },
                1000);

    }

    public void reset(){
        jogo.devolverCartas();
        jogo.reserControleRodada();
        jogo.getBaralho().gerarBaralho();
        carregarImagensCartas();
        startGame();

        card1.setVisibility(View.VISIBLE);
        card2.setVisibility(View.VISIBLE);
        card3.setVisibility(View.VISIBLE);

        imgCard4.setVisibility(View.VISIBLE);
        imgCard5.setVisibility(View.VISIBLE);
        imgCard6.setVisibility(View.VISIBLE);

        imgCard1Cpu.setVisibility(View.INVISIBLE);
        imgCard2Cpu.setVisibility(View.INVISIBLE);
        imgCard3Cpu.setVisibility(View.INVISIBLE);

        imgCard1Player.setVisibility(View.INVISIBLE);
        imgCard2Player.setVisibility(View.INVISIBLE);
        imgCard3Player.setVisibility(View.INVISIBLE);

        if(jogo.getPosicaoBaralho() %2 == 0){
            cardsInCpuSide.setVisibility(View.VISIBLE);
            cardsInYouSide.setVisibility(View.INVISIBLE);
            jogo.setVez(0);
        }else{
            cardsInCpuSide.setVisibility(View.INVISIBLE);
            cardsInYouSide.setVisibility(View.VISIBLE);
            jogo.setVez(1);
            if(jogo.getVez() == 1){
                new android.os.Handler().postDelayed(
                        this::cpuJoga,
                    1000);
            }

        }



    }

    public void cpuJoga(){

        System.out.println("8888888888888888888888 " + jogo.getControleRodada().size());
        int cardRodada = jogo.getControleRodada().size();
        Carta cartaCpu = jogo.cpuPlayCard();

        switch (cardRodada){
            case 0:
                imgCard4.setVisibility(View.INVISIBLE);
                imgCard1Cpu.setVisibility(View.VISIBLE);
                imgCard1Cpu.setImageResource(cartaCpu.getIdImage());
                break;
            case 1:
                imgCard5.setVisibility(View.INVISIBLE);
                imgCard2Cpu.setVisibility(View.VISIBLE);
                imgCard2Cpu.setImageResource(cartaCpu.getIdImage());
                break;
            case 2:
                imgCard6.setVisibility(View.INVISIBLE);
                imgCard3Cpu.setVisibility(View.VISIBLE);
                imgCard3Cpu.setImageResource(cartaCpu.getIdImage());
                break;

        }

    }

    public void tostCard(int indexCard){
//                Toast toast = Toast.makeText(getApplicationContext(), "text", Toast.LENGTH_LONG);
//                toast.show();
        Jogador jogador = jogo.getJogadores().get(0);
        Carta cartaJogada = jogador.getCartas().get(indexCard);
        Toast toast = Toast.makeText(getApplicationContext(), cartaJogada.toString() , Toast.LENGTH_LONG);
        toast.show();

    }

    public void alertTruco(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Truco");

        alertDialog.setMessage("CPU chamou o jogo");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Quero", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                //...

            } });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não quero", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                //...

            }});

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Retruco", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                //...

            }});
        alertDialog.show();
    }

    public void alertEnvido(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Envido");

        alertDialog.setMessage("CPU chamou os pontos");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Quero", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                //...

            } });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Não quero", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                //...

            }});

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Real envido", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                //...

            }});
        alertDialog.show();
    }
}


