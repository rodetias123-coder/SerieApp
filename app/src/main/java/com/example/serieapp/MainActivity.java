package com.example.serieapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText Calculo = findViewById(R.id.CalculoTermino);
        Button Convert = findViewById(R.id.Convertir);
        TextView Resultado = findViewById(R.id.Resultado);
        Convert.setOnClickListener(v -> {
            String input = Calculo.getText().toString();
            if (input.isEmpty()) {
                Resultado.setText("Campo vacío");
                return;
            }
            int n;
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                Resultado.setText("Número inválido");
                return;
            }
            if (n <= 0) {
                Resultado.setText("Debe ser mayor que 0");
                return;
            }
            double suma = 0;
            String proceso = "";
            double valor = 1;
            for (int k = 1; k <= n; k++) {
                double termino;
                if (k % 2 == 0) {
                    termino = -valor;
                } else {
                    termino = valor;
                }
                suma += termino;
                proceso += String.format("%.2f", termino);
                if (k < n) {
                    proceso += ", ";
                }
                valor += k + 0.5;
            }
            proceso += "\n\nSuma = " + String.format("%.2f", suma);
            Resultado.setText(proceso);
        });
    }
}