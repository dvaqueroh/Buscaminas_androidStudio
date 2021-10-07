package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ControlesActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controles);
        //
        btVolver = findViewById(R.id.btVolverControles);
        btVolver.setOnClickListener(this);
    }// fin onCreate

    @Override
    public void onClick(View v) {
        this.finish();
    }
}