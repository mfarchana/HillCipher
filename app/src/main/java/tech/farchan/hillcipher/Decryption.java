package tech.farchan.hillcipher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Decryption extends AppCompatActivity {

    Button btDecrypt, btClean, btBack;
    TextView tbPlain2, tbCipher2, tbx1, tbx2, tby1, tby2;

    String finalWord = "";
    int[] key = new int[4];
    int[] wordValu = new int[100];
    int det;
    int sV;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        btDecrypt = findViewById(R.id.btDecrypt);
        btClean = findViewById(R.id.btClean1);
        btBack = findViewById(R.id.btBack1);

        tbPlain2 = findViewById(R.id.tbPlain1);
        tbCipher2 = findViewById(R.id.tbCipher1);
        tbx1 = findViewById(R.id.tbx1);
        tbx2 = findViewById(R.id.tbx2);
        tby1 = findViewById(R.id.tby1);
        tby2 = findViewById(R.id.tby2);

        btDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decript();
                tbPlain2.setText(String.valueOf(finalWord));
                finalWord = "";
            }
        });

        btClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Decryption.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void clean() {
        tbx1.setText("");
        tbx2.setText("");
        tby1.setText("");
        tby2.setText("");
        tbPlain2.setText("");
        tbCipher2.setText("");
    }

    public void decript() {
        try {
            String word = tbCipher2.getText().toString();
            key[0] = Integer.valueOf(tbx1.getText().toString());
            key[1] = Integer.valueOf(tby1.getText().toString());
            key[2] = Integer.valueOf(tbx2.getText().toString());
            key[3] = Integer.valueOf(tby2.getText().toString());

            det = (key[0] * key[3]) - (key[1] * key[2]);
            sV = 0;
            for (int i = 0; i < word.length(); i++) {
                wordValu[i] = (int) word.toUpperCase().charAt(i) - 65;
            }

            int m = key[0];
            key[0] = key[3];
            key[3] = m;
            key[1] = 0 - key[1]; //Biar mines
            key[2] = 0 - key[2]; //Biar mines

            ///Determinan Invers Modulo
            int hitung;
            for (int k = 0; k < 300; k++) {
                hitung = (1 + (26 * k)) / det;
                if (hitung % 1 == 0) { // Jika hitung INT maka sV di inisialisasi
                    sV = hitung;
                }
            }

            ///Perkalian invers modulo
            for (int i = 0; i < 4; i++) {
                if (key[i] < 0) { // Jika Bilangan Negatif
                    temp = (key[i] * sV);
                    key[i] = 26 - (Math.abs(temp) % 26);
                } else {
                    key[i] = (key[i] * sV) % 26;
                }
            }

            for (int j = 0; j < word.length(); j += 2) {
                finalWord = finalWord +
                        (char) (((wordValu[j] * key[0] + wordValu[j + 1] * key[1]) % 26) + 65) +
                        (char) (((wordValu[j] * key[2] + wordValu[j + 1] * key[3]) % 26) + 65);
            }

        } catch (Exception e) {
            //Do Nothing
        }
    }
}
