package br.com.example.fiap.mob8.exerciciocotacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCotacao = (Button)findViewById(R.id.btnCotacao);

        btnCotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCotacao = new Intent(getApplicationContext(),CotacaoActivity.class);
                startActivity(iCotacao);
            }
        });

    }



}
