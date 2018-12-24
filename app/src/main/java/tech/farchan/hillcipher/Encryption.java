package tech.farchan.hillcipher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Encryption extends AppCompatActivity {

    Button btEncript, btClean, btBack;
    TextView tbPlain2, tbCipher2, tbx1, tbx2, tby1, tby2;

    String finalWord = "";
    int[] key = new int[4];
    int[] wordValu = new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        btEncript = findViewById(R.id.btEncrypt);
        btClean = findViewById(R.id.btClean2);
        btBack = findViewById(R.id.btBack2);


        tbPlain2 = findViewById(R.id.tbPlain2);
        tbCipher2 = findViewById(R.id.tbCipher2);
        tbx1 = findViewById(R.id.tbx1);
        tbx2 = findViewById(R.id.tbx2);
        tby1 = findViewById(R.id.tby1);
        tby2 = findViewById(R.id.tby2);

        btEncript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encript();
                tbCipher2.setText(finalWord);
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
                Intent i = new Intent(Encryption.this, MainActivity.class);
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

    public void encript() {
        try {
            String word = tbPlain2.getText().toString();
            key[0] = Integer.valueOf(tbx1.getText().toString());
            key[1] = Integer.valueOf(tby1.getText().toString());
            key[2] = Integer.valueOf(tbx2.getText().toString());
            key[3] = Integer.valueOf(tby2.getText().toString());

            for (int i = 0; i < word.length(); i++) {
                wordValu[i] = (int) word.toUpperCase().charAt(i) - 65;//Misal a = 0, maka A = 65 - 65 : 0 (Sesuai dengan index)
                //A = 65 - 65 = 0 ; B = 66 - 65 = 1
            }

            //Penghapusan Spasi
            int n = 0;
            int count = 0; // counter jumlah spasi
            for (int k = 0; k < word.length(); k++) {
                if (wordValu[k] != -33) { // -33 karena ASCII space 32 - 65 = -33
                    wordValu[n] = wordValu[k];
                    n++;
                } else {
                    count++;
                }
            }
            wordValu[n] = '\0'; //Memberikan Nilai NULL pada akhir kata atau termination

            for (int j = 0; j < word.length() - count; j += 2) {
                finalWord = finalWord +
                        (char) (((wordValu[j] * key[0] + wordValu[j + 1] * key[1]) % 26) + 65) +//Huruf Pertama (M)
                        (char) (((wordValu[j] * key[2] + wordValu[j + 1] * key[3]) % 26) + 65);// Huruf Kedua   (A)
            }

        } catch (Exception e) {
            //Do Nothing
        }
    }
}