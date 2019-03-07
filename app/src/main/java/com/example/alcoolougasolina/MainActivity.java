package com.example.alcoolougasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editGasolina;
    private EditText editEtanol;
    private TextView textResultado;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void calcularResultado(View view) {

        EditText editGasolina = findViewById(R.id.editGasolina);
        EditText editEtanol = findViewById(R.id.editEtanol);
        TextView textResultado = findViewById(R.id.textResultado);

        //verificação de digitação
        if (editGasolina.length() == 0) {
            editGasolina.setError("Insira o valor");
        }
        if (editEtanol.length() == 0) {
            editEtanol.setError("Insira o valor");

            //execução do código
        } else {

            double valorGasolina = Double.parseDouble(editGasolina.getText().toString());
            double valorEtanol = Double.parseDouble(editEtanol.getText().toString());

            double valorResultado = valorEtanol / valorGasolina;

            if (valorResultado >= 0.7) {
                textResultado.setText("A recomendação é abastecer com GASOLINA");
            } else if (valorResultado < 0.7) {
                textResultado.setText("A recomedação é abastecer com ETANOL");
            }


        }
    }

}