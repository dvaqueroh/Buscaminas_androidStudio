package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private long start,finish;
    private Casilla casilla;
    private Tablero tablero;
    public LinearLayout layout1;
    public Button btReiniciar;
    public TextView tvMinas,tvBanderas,tvTiempo;
    private int banderas = 10;
    private int cory,corx,tiempo;
    private int contMinas=54;
    static boolean terminado=false;
    boolean comienza;
    boolean comienzaTiempo=false;
    private CountDownTimer cuentaAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        layout1 = findViewById(R.id.linearLayout);
        tvTiempo = findViewById(R.id.etTime);
        tvBanderas = findViewById(R.id.tvBanderas);
        tvBanderas.setText(String.valueOf(banderas));
        tvMinas = findViewById((R.id.tvMinas));
        tvMinas.setText(String.valueOf(contMinas));
        btReiniciar = findViewById(R.id.btReiniciar);
        btReiniciar.setOnClickListener(this);
        tablero = new Tablero(this);
        tablero.setOnTouchListener(this);
        layout1.addView(tablero);
        //
        tiempo=3; // Tiempo para terminar el panel 3 minutos
        comienza=false;
        comienzaTiempo=false;
    }// fin oncreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if ( id == R.id.itcontroles) {
            //Toast.makeText(this,getString(R.string.controles),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ControlesActivity.class);
            startActivity(intent);
        }
        if ( id == R.id.itReglas) {
            Intent intent = new Intent(this, ReglasActivity.class);
            startActivity(intent);

            //Toast.makeText(this,getString(R.string.reglas),Toast.LENGTH_LONG).show();
        }
        if ( id == R.id.itSalir ) {
            Toast.makeText(this," Salir",Toast.LENGTH_LONG).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void comienza(){

        cuentaAtras = new CountDownTimer(tiempo *60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Este método se lanza por cada lapso de tiempo
                // transcurrido,
                if(terminado==false) {
                    tvTiempo.setText(String.valueOf(millisUntilFinished / 1000) + " s");
                    comienzaTiempo=true;
                }
            }//fin onTick

            @Override
            public void onFinish() {
                System.out.println(" *** TIEMPO AGOTADO ***");
                //Toast.makeText(this,"TIEMPO AGOTADO ", Toast.LENGTH_LONG).show();
                terminado=true;
                cuentaAtras.start();
                cuentaAtras.cancel();
            }
        };
        if(comienzaTiempo==false) {
            cuentaAtras.start();
        }
    }//fin comienza

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        corx = (int) event.getX();
        cory = (int) event.getY();
        //
        boolean terminar=false;

        int accion = event.getAction();

        //
        switch (accion){ // captura cuando se pulsa el boton y cuando se suelta y calcula el tiempo de pulsacion
            case MotionEvent.ACTION_DOWN:
                start = System.currentTimeMillis();
                //System.out.println("Start: "+start);
                return true;
            case MotionEvent.ACTION_UP:
                finish = System.currentTimeMillis();
                terminar = true;
                break;
        }
        //System.out.println("Start: "+start+" Finish: "+finish);
        //System.out.println(finish-start);
        if(terminar){
            if((finish - start) < 1500){// si la pulsacion es menor de 3 segundos
                System.out.println("** PULSACION CORTA ** ");
                if(terminado==false) {
                    //Toast.makeText(this,"has pinchado en:"+corx+" y "+cory, Toast.LENGTH_LONG).show();
                    clickCasilla(cory, corx);
                    //Toast.makeText(this,"ahora llama a invalidate", Toast.LENGTH_LONG).show();
                    tablero.invalidate();
                }
            }
            else{ // si la pulsacion es mayor de 1 segundo y medio
                System.out.println("** PULSACION LARGA ** ");
                boolean coincide =false;
                int columna,fila;
                for (int i = 0; i < 8 ; i++) {// FILA busca en el Array de las casillas la que coincida por posicion
                    for (int j = 0; j < 8; j++) {// COLUMNA
                        coincide = tablero.ArrayTablero[i][j].dentro(cory, corx); // guarda la casilla de la posicion del array
                        //System.out.println("*COINCIDE " + coincide);
                        if (coincide == true) { // comprueba el metodo de la casilla
                            if (banderas>0) {
                                if(tablero.ArrayTablero[i][j].bandera == false) {
                                    System.out.println("** PONE BANDERA** ");
                                    tablero.ArrayTablero[i][j].bandera = true;
                                    banderas--;
                                    tvBanderas.setText(String.valueOf(banderas));
                                    tablero.invalidate();
                                }
                                else{
                                    System.out.println("** QUITA BANDERA** ");
                                    tablero.ArrayTablero[i][j].bandera = false;
                                    banderas++;
                                    tvBanderas.setText(String.valueOf(banderas));
                                    tablero.invalidate();
                                }
                            }
                        }
                    }
                }
            }// fin else PULSACION LARGA
        }// fin if terminar

        return false;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btReiniciar:
                System.out.println("  BOTON REINICIAR ");
                //reiniciarActivity(this);
                // reinicia el contador y llama al metodo reiniciar tablero
                contMinas=54;
                banderas = 10;
                tiempo = 3;
                tvMinas.setText(String.valueOf(contMinas));
                tvBanderas.setText(String.valueOf(banderas));
                tablero.reiniciar();
                tablero.inicializado=false;
                cuentaAtras.cancel();
                terminado=false;
                comienzaTiempo=false;
                tablero.invalidate();
                break;
        }
    }// fin ONCLICK

    public void clickCasilla(int x,int y){

        boolean coincide =false;
        int columna,fila;
        //System.out.println("** CLICK CASILLA** ");
        for (int i = 0; i < 8 ; i++) {// FILA busca en el Array de las casillas la que coincida por posicion
            for (int j = 0; j < 8 ; j++) {// COLUMNA
                coincide = tablero.ArrayTablero[i][j].dentro(x,y); // guarda la casilla de la posicion del array
                System.out.println("*COINCIDE "+coincide);

                if(coincide==true){ // comprueba el metodo de la casilla
                    //comienza=true;
                    comienza();
                    if(tablero.ArrayTablero[i][j].contenido==99){
                        tablero.ArrayTablero[i][j].destapado=true;
                        //Toast.makeText(this,"BOMBA ", Toast.LENGTH_SHORT).show();
                        // TOAST PERSONALIZADO
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_explosion,
                                (ViewGroup) findViewById(R.id.custom_toast_container));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                        System.out.println(" *** BOMBA - JUEGO TERMINADO ***");
                        Toast.makeText(this,"JUEGO TERMINADO ", Toast.LENGTH_LONG).show();
                        cuentaAtras.cancel();
                        terminado=true;
                        comienzaTiempo=false;
                    }
                    else{
                        fila = i;
                        columna = j;
                        //System.out.println("** CASILLA ENCONTRADA **");
                        destapar(fila,columna);
                        if(contMinas==0){
                            Toast.makeText(this,"HAS GANADO! ", Toast.LENGTH_LONG).show();
                            System.out.println(" *** HAS GANADO ***");
                            terminado=true;
                            comienzaTiempo=false;
                            cuentaAtras.cancel();
                        }
                    }

                }
            }
        }

    }// fin devuelve casilla

    private void destapar(int fil, int col) {
        /*
        if (fil >= 0 && fil < 8 && col >= 0 && col < 8) {
            //Toast.makeText(this, "comprobando: " + fil + " y " + col, Toast.LENGTH_SHORT).show();
            if (tablero.ArrayTablero[fil][col].contenido == 0) { // si el contenido de la celda es 0
                tablero.ArrayTablero[fil][col].destapado = true;

                //Toast.makeText(this, "Celda DESTAPADA: " +tablero.ArrayTablero[fil][col].isDestapado() , Toast.LENGTH_SHORT).show();
            }
        }
        */
        //Tablero 8x8
        if (fil >= 0 && fil < 8 && col >= 0 && col < 8) {
            //System.out.println("Comprobando "+fil+" y "+col);
            if (tablero.ArrayTablero[fil][col].contenido == 0) { // si el contenido de la celda es 0
                tablero.ArrayTablero[fil][col].destapado = true;
                tablero.ArrayTablero[fil][col].contenido = 50;

                destapar(fil, col + 1);
                destapar (fil, col - 1);
                destapar (fil + 1, col);
                destapar (fil - 1, col);
                destapar (fil - 1, col - 1);
                destapar (fil - 1, col + 1);
                destapar (fil + 1, col + 1);
                destapar (fil + 1, col - 1);
                contMinas--;
                tvMinas.setText(String.valueOf(contMinas));
                System.out.println("  RESTA UNA CELDA 0 "+contMinas);
            }
            else if (tablero.ArrayTablero[fil][col].contenido >= 1 && tablero.ArrayTablero[fil][col].contenido <= 8) {
                if(tablero.ArrayTablero[fil][col].destapado == false) {
                    tablero.ArrayTablero[fil][col].destapado = true;
                    contMinas--;
                    tvMinas.setText(String.valueOf(contMinas));
                    System.out.println("  RESTA UNA CELDA con NUMERO " + contMinas);
                }
            }
        }

    }// fin DESTAPAR

}// fin clase main activity