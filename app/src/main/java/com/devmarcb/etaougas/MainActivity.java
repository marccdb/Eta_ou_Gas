package com.devmarcb.etaougas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private EditText editGasolina;
    private EditText editEtanol;
    private TextView textResultado;
    private EditText editKmGas;
    private EditText editKmEt;
    private TextView textRendimento;
    private TextView textKmReal;
    private TextView textKmReal2;
    private TextView textRecomendacao;
    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-9348805108429232~2913044891");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView = findViewById(R.id.adView);
        mAdView.loadAd(adRequest);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);
        textResultado = findViewById(R.id.textResultado);
        editKmGas = findViewById(R.id.editKmGas);
        editKmEt = findViewById(R.id.editKmEt);
        textRendimento = findViewById(R.id.textRendimento);
        textKmReal = findViewById(R.id.textKmReal);
        textKmReal2 = findViewById(R.id.textKmReal2);
        textRecomendacao = findViewById(R.id.textRecomendacao);
    }

    public void calcularResultado(View view) {

        //verificação de digitação

        if (editGasolina.length() == 0) {
            editGasolina.setError("Insira o valor");
        }
        if (editEtanol.length() == 0) {
            editEtanol.setError("Insira o valor");
        } else {

            double valorGasolina = Double.parseDouble(editGasolina.getText().toString());
            double valorEtanol = Double.parseDouble(editEtanol.getText().toString());

            boolean KmEtDigitado = editKmEt.length() != 0;
            boolean KmGasDigitado = editKmGas.length() != 0;

            double valorResultado = 0;

            if (!KmEtDigitado && !KmGasDigitado) {
                valorResultado = valorEtanol / valorGasolina;

                double porcentagem = (valorResultado) * 100;
                String valorResultadoTexto = Double.toString(porcentagem);
                valorResultadoTexto = String.format("%.2f", porcentagem);

                if (valorResultado >= 0.7) {
                    textKmReal.setText("Rendimento -> ");
                    textRecomendacao.setText("Recomendação -> ");
                    textKmReal2.setText("");
                    textResultado.setText("GASOLINA");
                    textResultado.setTextColor(getResources().getColor(R.color.corGasolina));
                    textRendimento.setText(valorResultadoTexto + "%");
                    textRendimento.setTextColor(getResources().getColor(R.color.corGasolina));
                    textKmReal.setTextColor(getResources().getColor(R.color.colorAccent));

                } else {
                    textKmReal.setText("Rendimento ->");
                    textRecomendacao.setText("Recomendação -> ");
                    textKmReal2.setText("");
                    textResultado.setText("ETANOL");
                    textResultado.setTextColor(getResources().getColor(R.color.corEtanol));
                    textRendimento.setText(valorResultadoTexto + "%");
                    textRendimento.setTextColor(getResources().getColor(R.color.corEtanol));
                    textKmReal.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            } else if (KmEtDigitado && !KmGasDigitado) {
                editKmGas.setError("Insira o valor");


            } else if (!KmEtDigitado && KmGasDigitado) {

                editKmEt.setError("Insira o valor");

            } else {
                double valorKmEt = Double.parseDouble(editKmEt.getText().toString());
                double valorKmGas = Double.parseDouble(editKmGas.getText().toString());

                double EtKmCalculo = valorKmEt / valorEtanol;
                double GasKmCalculo = valorKmGas / valorGasolina;

                String EtKmCalculoTexto = Double.toString(EtKmCalculo);
                String GasKmCalculoTexto = Double.toString(GasKmCalculo);
                EtKmCalculoTexto = String.format("%.2f", EtKmCalculo);
                GasKmCalculoTexto = String.format("%.2f", GasKmCalculo);

                if (GasKmCalculo > EtKmCalculo) {
                    textResultado.setText("GASOLINA");
                    textResultado.setTextColor(getResources().getColor(R.color.corGasolina));
                    textKmReal.setText("Km/Real gasto com Gasolina: " + GasKmCalculoTexto + " Km");
                    textKmReal.setTextColor(getResources().getColor(R.color.corGasolina));
                    textKmReal2.setText("Km/Real gasto com Etanol: " + EtKmCalculoTexto + " Km");
                    textKmReal2.setTextColor(getResources().getColor(R.color.corEtanol));
                    textRendimento.setText("");
                } else if (GasKmCalculo < EtKmCalculo) {
                    textResultado.setText("ETANOL");
                    textResultado.setTextColor(getResources().getColor(R.color.corEtanol));
                    textKmReal.setText("Km/Real gasto com Gasolina: " + GasKmCalculoTexto + " Km");
                    textKmReal.setTextColor(getResources().getColor(R.color.corGasolina));
                    textKmReal2.setText("Km/Real gasto com Etanol: " + EtKmCalculoTexto + " Km");
                    textKmReal2.setTextColor(getResources().getColor(R.color.corEtanol));
                    textRendimento.setText("");
                }
            }
        }
    }
}
