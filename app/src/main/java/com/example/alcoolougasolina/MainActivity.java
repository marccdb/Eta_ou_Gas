package com.example.alcoolougasolina;

import android.annotation.SuppressLint;
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
    private AdView mAdView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,"ca-app-pub-9348805108429232~2913044891");

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);
        textResultado = findViewById(R.id.textResultado);
        editKmGas = findViewById(R.id.editKmGas);
        editKmEt = findViewById(R.id.editKmEt);
        textRendimento = findViewById(R.id.textRendimento);
        textKmReal = findViewById(R.id.textKmReal);
        textKmReal2 = findViewById(R.id.textKmReal2);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
                    textKmReal.setText("Rendimento:");
                    textKmReal2.setText("");
                    textResultado.setText("GASOLINA");
                    textRendimento.setText(valorResultadoTexto + "%");
                } else {
                    textKmReal.setText("Rendimento:");
                    textKmReal2.setText("");
                    textResultado.setText("ETANOL");
                    textRendimento.setText(valorResultadoTexto + "%");
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


                //valorResultado = EtKmCalculo / GasKmCalculo;
                if (GasKmCalculo > EtKmCalculo) {
                    textResultado.setText("GASOLINA");
                    textKmReal.setText("Quilometro/Real gasto com Gasolina: " + GasKmCalculoTexto + " Km");
                    textKmReal2.setText("Quilometro/Real gasto com Etanol: " + EtKmCalculoTexto + " Km");
                    textRendimento.setText("");
                } else if (GasKmCalculo < EtKmCalculo) {
                    textResultado.setText("ETANOL");
                    textKmReal.setText("Quilometro/Real gasto com Gasolina: " + GasKmCalculoTexto + " Km");
                    textKmReal2.setText("Quilometro/Real gasto com Etanol: " + EtKmCalculoTexto + " Km");
                    textRendimento.setText("");
                }


            }


        }
    }

/*
    public void limpar(View view) {
        //editGasolina.setText("");
        //editEtanol.setText("");
        //editKmGas.setText("");
        //editKmEt.setText("");
        textResultado.setText("");
        textRendimento.setText("");
        textKmReal.setText("Rendimento");

    }*/

}
