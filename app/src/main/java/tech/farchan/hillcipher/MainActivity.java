package tech.farchan.hillcipher;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btExit, btEecryption, btDecryption;
    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        bgapp = findViewById(R.id.bgapp);
        clover = findViewById(R.id.clover);
        textsplash = findViewById(R.id.textsplash);
        texthome = findViewById(R.id.texthome);
        menus = findViewById(R.id.menus);

        bgapp.animate().translationY(-1900).setDuration(1800).setStartDelay(1300);
        clover.animate().alpha(0).setDuration(1200).setStartDelay(1200);
        textsplash.animate().translationY(800).alpha(0).setDuration(1800).setStartDelay(1300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        btExit = findViewById(R.id.btExit);
        btEecryption = findViewById(R.id.btEncryption);
        btDecryption = findViewById(R.id.btDecryption);

        btDecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Decryption.class);
                startActivity(i);
            }
        });

        btEecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Encryption.class);
                startActivity(i);
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "    M. Farchan A. \nA11.2017.10413 \n    Dinus University",
                        Toast.LENGTH_LONG).show();
                finishAffinity();
            }
        });
    }
}
