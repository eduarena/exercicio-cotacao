package br.com.example.fiap.mob8.exerciciocotacao;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.example.fiap.mob8.exerciciocotacao.adapter.CotacaoAdapter;
import br.com.example.fiap.mob8.exerciciocotacao.model.Cotacao;

public class CotacaoActivity extends AppCompatActivity {

    private final String TAG = "cotaçao";
    private final String URL_SERVICE = "http://developers.agenciaideias.com.br/cotacoes/json";

    private final String BOVESPA = "bovespa";
    private final String MOEDA_DOLAR = "dolar";
    private final String MOEDA_EURO = "euro";
    private final String ATUALIZACAO = "atualizacao";


    private GetCotacoesTask getCotacoesTask;
    private ProgressDialog dialog;
    private Cotacao cotacao;

    TextView tvValueBovespaCotacao;
    TextView tvValueBovespaVariacao;
    TextView tvValueDolarCotacao;
    TextView tvValueDolarVariacao;
    TextView tvValueEuroCotacao;
    TextView tvValueEuroVariacao;
    TextView tvValueAtualizacao;

    Button btnAtualizarCotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotacao);

        tvValueBovespaCotacao = (TextView) findViewById(R.id.tvValueBovespaCotacao);
        tvValueBovespaVariacao = (TextView) findViewById(R.id.tvValueBovespaVariacao);

        tvValueDolarCotacao = (TextView) findViewById(R.id.tvValueDolarCotacao);
        tvValueDolarVariacao = (TextView) findViewById(R.id.tvValueDolarVariacao);

        tvValueEuroCotacao = (TextView) findViewById(R.id.tvValueEuroCotacao);
        tvValueEuroVariacao = (TextView) findViewById(R.id.tvValueEuroVariacao);

        tvValueAtualizacao = (TextView) findViewById(R.id.tvValueAtualizacao);

        btnAtualizarCotacao = (Button)findViewById(R.id.btnAtualizarCotacao);

        btnAtualizarCotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarCotacao();
            }
        });

        atualizarCotacao();

    }

    private void atualizarCotacao(){
        dialog = ProgressDialog.show(CotacaoActivity.this,"Baixando cotações","Por favor aguarde...");
        getCotacoesTask = new GetCotacoesTask();
        getCotacoesTask.execute(URL_SERVICE);
    }

    private void resetValues(){
        tvValueBovespaCotacao.setText("");
        tvValueBovespaVariacao.setText("");

        tvValueDolarCotacao.setText("");
        tvValueDolarVariacao.setText("");

        tvValueEuroCotacao.setText("");
        tvValueEuroVariacao.setText("");

        tvValueAtualizacao.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
        getCotacoesTask.cancel(true);
    }

    private class GetCotacoesTask extends AsyncTask<String, Void, Cotacao> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Cotacao doInBackground(String... params) {
            try{
                return getCotacoes(params[0]);
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Cotacao result) {
            super.onPostExecute(result);
            dialog.dismiss();

            if(result != null){

                tvValueBovespaCotacao.setText(result.getBovespaCotacao());
                tvValueBovespaVariacao.setText(result.getBovespaVariacao());

                tvValueDolarCotacao.setText(result.getDolarCotacao());
                tvValueDolarVariacao.setText(result.getDolarVariacao());

                tvValueEuroCotacao.setText(result.getEuroCotacao());
                tvValueEuroVariacao.setText(result.getEuroVariacao());

                tvValueAtualizacao.setText(result.getAtualizacao());

            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        CotacaoActivity.this).setTitle("Atenção")
                        .setMessage("Não foi possivel acessar essas informções...")
                        .setPositiveButton("OK", null);
                builder.create().show();

            }

        }



        private Cotacao getCotacoes(String jsonString) throws IOException {

            String content = "";

            URL urlService = new URL(URL_SERVICE);
            HttpURLConnection conn = (HttpURLConnection) urlService.openConnection();

            conn.setDoInput(true);
            conn.connect();

            //é mais rápido para leitura de caracteres ou linhas.
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String linha = null;

            while ((linha = reader.readLine()) != null){
                sb.append(linha + "\n");

                content = sb.toString();
            }

            cotacao = new Cotacao();

            try {

                JSONObject json = new JSONObject(content);

                JSONObject bovespa = json.getJSONObject(BOVESPA);
                JSONObject moedaDolar = json.getJSONObject(MOEDA_DOLAR);
                JSONObject moedaEuro = json.getJSONObject(MOEDA_EURO);
                String atualizacao = json.getString(ATUALIZACAO);


                cotacao.setBovespaCotacao(bovespa.getString("cotacao"));
                cotacao.setBovespaVariacao(bovespa.getString("variacao"));

                cotacao.setDolarCotacao(moedaDolar.getString("cotacao"));
                cotacao.setDolarVariacao(moedaDolar.getString("variacao"));

                cotacao.setEuroCotacao(moedaEuro.getString("cotacao"));
                cotacao.setEuroVariacao(moedaEuro.getString("variacao"));

                cotacao.setAtualizacao(atualizacao);

            }catch (JSONException e){
                Log.e(TAG, "Erro no parsing do JSON", e);
            }

            return cotacao;

        }

    }


}