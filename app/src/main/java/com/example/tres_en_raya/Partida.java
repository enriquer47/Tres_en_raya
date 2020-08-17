package com.example.tres_en_raya;

import java.util.Random;

public class Partida {
    public final int dificultad;
    public int jugador;
    private int[] casillas;
    private final int[][] COMBINACIONES={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public Partida(int dific){
        dificultad=dific;
        jugador=1;//jugador actual
        casillas=new int[9];
        for(int i = 0;i<9;i++) {
            casillas[i] = 0;
        }


    }
    public int ia(){
        int casilla;
        casilla=dosEnRaya(2);
        if(casilla!=-1)
            return casilla;
        if(dificultad>0){
            casilla=dosEnRaya(1);
            if(casilla!=-1)
                return casilla;
        }
        if(dificultad==2){
            if(casillas[4]==0)
                return 4;
            if(casillas[4]==2){
                if(casillas[1]==0)
                    return 1;
                if(casillas[3]==0)
                    return 3;
                if(casillas[5]==0)
                    return 5;
                if(casillas[7]==0)
                    return 7;
            }
            if(casillas[0]==0)
                return 0;
            if(casillas[2]==0)
                return 2;
            if(casillas[6]==0)
                return 6;
            if(casillas[8]==0)
                return 8;

        }
        Random casilla_azar=new Random();
        casilla=casilla_azar.nextInt(9);
        return casilla;
    }
    public int dosEnRaya(int jugadorActual){
        int casilla=-1;
        int cuantasLleva=0;
        for(int i=0; i<COMBINACIONES.length;i++) {
            for (int pos : COMBINACIONES[i]) {
                if(casillas[pos]==jugadorActual)
                    cuantasLleva++;
                if(casillas[pos]==0)
                    casilla=pos;
            }
            if(cuantasLleva==2&&casilla!=-1)
                return casilla;
            cuantasLleva=0;
            casilla=-1;
        }
        return -1;
    }

    public int turno(){
        boolean empate=true;
        boolean ult_movimiento=true;
        for(int i=0; i<COMBINACIONES.length;i++){
            for(int pos:COMBINACIONES[i]){
                System.out.println("\nValor en posicion " + pos + " " +casillas[pos]);
                if(casillas[pos]!=jugador)
                    ult_movimiento=false;
                if(casillas[pos]==0)
                    empate=false;
            }
            System.out.println("-------------------------");
            if(ult_movimiento)
                return jugador;
            ult_movimiento=true;
        }
        if(empate)
            return 3;
        if(jugador==2)
            jugador=1;
        else
            jugador=2;
        return 0;//por la cara
    }
    public boolean casilla_libre(int casilla){
        if (casillas[casilla]!=0)
            return false;
        else
            casillas[casilla]=jugador;
        return true;
    }
}
