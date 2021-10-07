package com.example.buscaminas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class Tablero extends View {
    MainActivity mainAct;
    Casilla casilla;
    static Canvas canvas;
    static int ladoTab, ladoCel;
    static boolean inicializado;
    static Casilla[][] ArrayTablero = new Casilla[8][8];


    public Tablero(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        //Obetenemos el ancho y el alto de la vista
        int x = getWidth();
        int y = getHeight();
        //Paint paint = new Paint();
        canvas.drawRGB(210,210,210);
        this.dibujaTablero(canvas);

    }// fin ondraw

    static void rellenaTablero(){
        int desplazamiento=0;
        int despVertical = 0;
        int espacio = 10;
        Rect celda;
        Paint pincel1 = new Paint();
        pincel1.setARGB(255, 120, 120, 120);//gris para la casillas
        pincel1.setStyle(Paint.Style.FILL_AND_STROKE);
        int filaActu = 0;
        for (int i= 0; i < 8; i++) {// i - FILA
            for (int j = 0; j < 8; j++) {// j - COLUMNA
                //System.out.print(i + " " + j + "  ");
                Casilla casi = new Casilla();
                casi = ArrayTablero[i][j];
                if (casi == null) { // crea una casilla nueva
                        //pinta la celca, que es un RECTANGULO
                        casi = new Casilla();
                        casi.fijarxy(i * (ladoCel + espacio), j * (ladoCel + espacio), ladoCel);
                        ArrayTablero[i][j] = casi;
                        //System.out.println("casilla creada, posicion"+i+"-"+j+" COORDENDAS "+casi.x+" - "+casi.y);
                        //celda = new Rect(desplazamiento, despVertical, desplazamiento + ladoCel, despVertical + ladoCel);
                        //canvas.drawRect(celda, pincel1);
                }
            }
        }
        creaBombas();
        inicializado=true;
    }// fin rellena tablaro

    public void dibujaTablero(Canvas canvas){
        //inicializado = false;
        int desplazamiento=0;
        int despVertical = 0;
        int espacio = 10;
        Rect celda;
        ladoTab = canvas.getWidth();
        //System.out.println("** LADO DE LA TABLERO** "+ladoTab);
        //ancho de cada celda en tablero de 8
        ladoCel = (ladoTab/8)-8;
        //System.out.println("** LADO DE LA CELDA** "+ladoCel);
        // pincel
        Paint pincel1 = new Paint();
        Paint pincel2 = new Paint();

        //Primer parámetro: Es el grado de transparecia (255 – Opaco)
        pincel1.setARGB(255, 120, 120, 120);//gris para la casillas
        pincel2.setARGB(255, 100, 100, 100);//gris para la casillas
        //Establece el grosor del pincel
        //pincel2.setStrokeWidth(20);
        //Define el estilo para el pincel (STROKE - Solo el perímetro)
        pincel1.setStyle(Paint.Style.FILL_AND_STROKE);
        pincel2.setStyle(Paint.Style.STROKE);
        // cuadros

        if(inicializado==false){
            rellenaTablero();
        }
        int filaActu = 0;
        for (int i= 0; i < 8; i++) {// i - FILA
            for (int j = 0; j < 8; j++) {// j - COLUMNA
                //System.out.print(i + " " + j + "  ");
                Casilla casi = new Casilla();
                casi = ArrayTablero[i][j] ;

                    if (casi.destapado == true) { // si la casilla esta destapada, no la pinta pero pone el NUMERO
                        //System.out.print(" casilla destapada, PINTA NUMERO");
                        if(casi.contenido==99){// si es una bomba

                            Bitmap bomba1 = BitmapFactory.decodeResource(getResources(),R.drawable.bomba01);
                            Bitmap bombaCelda = Bitmap.createScaledBitmap(bomba1, ladoCel, ladoCel, false);
                            canvas.drawBitmap(bombaCelda,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 50){// contenido 50  la casilla esta destapada sin numero
                            celda = new Rect(desplazamiento, despVertical, desplazamiento + ladoCel, despVertical + ladoCel);
                            canvas.drawRect(celda, pincel2);

                        }

                        else if(casi.contenido == 1){

                            Bitmap num1 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_01);
                            Bitmap num1cel = Bitmap.createScaledBitmap(num1, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num1cel,desplazamiento,despVertical,new Paint());
                        }
                        else if (casi.contenido == 2){

                            Bitmap num2 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_02);
                            Bitmap num2cel = Bitmap.createScaledBitmap(num2, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num2cel,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 3){

                            Bitmap num3 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_03);
                            Bitmap num3cel = Bitmap.createScaledBitmap(num3, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num3cel,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 4){
                            Bitmap num4 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_04);
                            Bitmap num4cel = Bitmap.createScaledBitmap(num4, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num4cel,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 5){
                            Bitmap num5 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_05);
                            Bitmap num5cel = Bitmap.createScaledBitmap(num5, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num5cel,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 6){
                            Bitmap num6 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_06);
                            Bitmap num6cel = Bitmap.createScaledBitmap(num6, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num6cel,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 7){
                            Bitmap num7 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_07);
                            Bitmap num7cel = Bitmap.createScaledBitmap(num7, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num7cel,desplazamiento,despVertical,new Paint());
                        }
                        else if(casi.contenido == 8){
                            Bitmap num8 = BitmapFactory.decodeResource(getResources(),R.drawable.num_ojo_08);
                            Bitmap num8cel = Bitmap.createScaledBitmap(num8, ladoCel, ladoCel, false);
                            canvas.drawBitmap(num8cel,desplazamiento,despVertical,new Paint());
                        }

                    }
                    else { // si la casilla NO esta destapada, la pinta
                        if(casi.bandera==true){// SI marca bandera en esa casilla
                            Bitmap band = BitmapFactory.decodeResource(getResources(),R.drawable.bandera_pirata);
                            Bitmap BandPir = Bitmap.createScaledBitmap(band, ladoCel, ladoCel, false);
                            canvas.drawBitmap(BandPir,desplazamiento,despVertical,new Paint());
                        }
                        else {
                            celda = new Rect(desplazamiento, despVertical, desplazamiento + ladoCel, despVertical + ladoCel);
                            canvas.drawRect(celda, pincel1);
                        }
                    }

                desplazamiento += ladoCel + espacio;
                filaActu = filaActu+ladoCel;
            }// fin por J //HORIZONTAL
            desplazamiento =0;
            despVertical += ladoCel+espacio;

        }// fin for I // VERTICAL
    }// fin dibujaTablero

    public static void creaBombas(){
        Random r = new Random();
        int filaRand,coluRand;
        for (int i = 0; i <= 9; i++) {
            //System.out.println(" CREA BOMBA "+i);
            do {
                filaRand = r.nextInt(7);
                //System.out.println(" Fila random " + filaRand);
                coluRand = r.nextInt(7);
                //System.out.println(" Columna random " + coluRand);
            }
            while(ArrayTablero[filaRand][coluRand].contenido==99);
            // hace bucle hasta que los numeros generados no coincidan con una bomba ya puesta
            ArrayTablero[filaRand][coluRand].contenido=99;
            compruebaBombas(filaRand,coluRand);
            //System.out.println(" contenido: "+ArrayTablero[filaRand][coluRand].contenido);
        }
        inicializado=true;
    }// fin crea bombas

    public static void compruebaBombas(int fil, int col){
        //Recorre el contorno de la bomba e incrementa su contenido

            for (int f2 = max(0, fil - 1); f2 < min(7, fil + 2); f2++) {
                for (int c2 = max(0, col - 1); c2 < min(7, col + 2); c2++) {
                    if (ArrayTablero[f2][c2].contenido != 99) { //Si no es bomba
                        ArrayTablero[f2][c2].contenido += 1; //Incrementa el contenido
                        //System.out.println(" Actualiza numero de la casilla " + f2 + "-" + c2 + " contenido-" + ArrayTablero[f2][c2].contenido);
                    }
                }
            }

        /*
        if (fil >= 0 && fil < 7 && col >= 1 && col < 7) {
                if (ArrayTablero[fil][col].contenido != 99) { // si la celda no es bomba, contenido +1
                    ArrayTablero[fil][col].contenido += 1;
                    System.out.println(" Actualiza numero de la casilla " + fil + "-" + col + " contenido-" + ArrayTablero[fil][col].contenido);

                }
                else {
                    //ArrayTablero[fil][col].destapado = true;
                    System.out.println(" Actualizando celdas alrededor de la bomba");

                    compruebaBombas(fil, col + 1);
                    compruebaBombas(fil, col - 1);
                    compruebaBombas(fil + 1, col);
                    compruebaBombas(fil - 1, col);
                    compruebaBombas(fil - 1, col - 1);
                    compruebaBombas(fil - 1, col + 1);
                    compruebaBombas(fil + 1, col + 1);
                    compruebaBombas(fil + 1, col - 1);
                }

        }

         */
    }// fin comprueba bombas
    public static int max(int a, int b){
        return Math.max(a,b);
    }
    public static int min(int a, int b){
        return Math.min(a,b);
    }

    public static void reiniciar(){
        for (int i= 0; i < 8; i++) {// i - FILA
            for (int j = 0; j < 8; j++) {// j - COLUMNA
                //System.out.print(i + " " + j + "  ");
                ArrayTablero[i][j].contenido=0;
                ArrayTablero[i][j].destapado=false;
                ArrayTablero[i][j].bandera=false;
            }
        }


    }
}// fin clase Tablero
