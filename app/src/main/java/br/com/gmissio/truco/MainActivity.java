package br.com.gmissio.truco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.gmissio.truco.model.Carta;
import br.com.gmissio.truco.model.Jogador;
import br.com.gmissio.truco.model.Jogo;

public class MainActivity extends AppCompatActivity {

    ImageView card1;
    ImageView card2;
    ImageView card3;
    TextView textView;
    Jogo jogo = new Jogo(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.imgCard1);
        card2 = findViewById(R.id.imgCard2);
        card3 = findViewById(R.id.imgCard3);

       // textView.append("mais essa");
        System.out.println("---------------------------------");
        carregarCartas();

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


    }


}/*

 Resources resources = getResources();
            int resourceId = resources.getIdentifier(referenceCardName.replaceAll(" ", ""),
                    "drawable", getPackageName());
            carta.setIdImage(resourceId);
*/