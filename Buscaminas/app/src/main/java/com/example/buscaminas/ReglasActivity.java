package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReglasActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglas);
        //
        btVolver = findViewById(R.id.btVolverReglas);
        btVolver.setOnClickListener(this);
    }// fin oncreate

    @Override
    public void onClick(View v) {
        this.finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
}