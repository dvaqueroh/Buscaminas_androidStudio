package com.example.buscaminas;

public class Casilla {
    public int x,y,ancho;
    public int contenido=0;
    public boolean destapado=false;
    public boolean bandera=false;

    public void fijarxy(int x,int y, int ancho) {
        this.x=x;
        this.y=y;
        this.ancho=ancho;
    }
    public boolean dentro(int xx,int yy) {
        //System.out.println("** METODO DENTRO** ");
        //System.out.println("PASADO " +xx +" - "+yy);
        //System.out.println(" CASILLA "+ this.x +" - "+this.y);
        if (xx>=this.x && xx<=this.x+ancho && yy>=this.y && yy<=this.y+ancho){
            System.out.println("*** ENCONTRADA *** " +xx +" - "+yy);
            return true;
        }
        else
            return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getContenido() {
        return contenido;
    }

    public void setContenido(int contenido) {
        this.contenido = contenido;
    }

    public boolean isDestapado() {
        return destapado;
    }

    public void setDestapado(boolean destapado) {
        this.destapado = destapado;
    }

}// fin clase Casilla
