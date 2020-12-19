package br.com.gmissio.truco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView card1;
    ImageView card2;
    ImageView card3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.imgCard1);
        card2 = findViewById(R.id.imgCard2);
        card3 = findViewById(R.id.imgCard3);
        textView = findViewById(R.id.text1);
        textView.append("mais essa");
        System.out.println("---------------------------------");
        Resources resources = getResources();
        final int resourceId = resources.getIdentifier("card_3_1",
                "drawable", getPackageName());

        card1.setImageResource(resourceId);
        //@drawable/card_1_1"
        card2.setImageResource(R.drawable.card_1_3);

    }
}