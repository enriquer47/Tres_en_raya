package com.example.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Iniciamos el array casillas que identifica cada casilla y la almacena en el array
        CASILLAS=new int[9];
        CASILLAS[0]=R.id.a1;
        CASILLAS[1]=R.id.a2;
        CASILLAS[2]=R.id.a3;
        CASILLAS[3]=R.id.b1;
        CASILLAS[4]=R.id.b2;
        CASILLAS[5]=R.id.b3;
        CASILLAS[6]=R.id.c1;
        CASILLAS[7]=R.id.c2;
        CASILLAS[8]=R.id.c3;


    }
    public void aJugar(View vista){
        ImageView imagen;
        for(int cadaCasilla:CASILLAS){
            imagen=(ImageView)findViewById(cadaCasilla);
            imagen.setImageResource(R.drawable.casilla);
        }
        jugadores=1;
        if(vista.getId()==R.id.dos_jug){
            jugadores=2;
        }
        RadioGroup configDificultad=(RadioGroup)findViewById(R.id.configD);
        int id=configDificultad.getCheckedRadioButtonId();
        int dificultad=0;
        if(id==R.id.normal){
            dificultad=1;
        }else if(id==R.id.imposible) {
            dificultad = 2;
        }
        partida=new Partida(dificultad);
        ((Button)findViewById(R.id.un_jug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(0);
        ((Button)findViewById(R.id.dos_jug)).setEnabled(false);
    }
    public void toque(View vista){
        if(partida!=null){//si he empezado
            int casilla=-1;
            for(int i=0;i<9&&casilla==-1;i++){ //aqui puse la condicion de casilla por no poner un break
                if(CASILLAS[i]==vista.getId()){
                    casilla=i;
                }
            }

            if (partida.casilla_libre(casilla)) {
                marcar(casilla);
                int resultado=partida.turno();
                if(resultado>0){
                    terminar(resultado);
                    return;
                }
                if(jugadores==1) {
                    casilla = partida.ia();
                    while (!partida.casilla_libre(casilla))
                        casilla = partida.ia();
                    marcar(casilla);
                    resultado = partida.turno();
                }
                if(resultado>0) {
                    terminar(resultado);

                }
            }
        }



    }
    private void terminar(int resultado) {
        String mensaje;
        if (resultado == 1) {
            mensaje = "Ganan los círculos";
        } else {
            if (resultado == 2) {
                mensaje = "Ganan las cruces";
            } else {
                mensaje = "Empate";
            }
        }
        Toast toast=Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        partida=null;
        ((Button)findViewById(R.id.un_jug)).setEnabled(true);
        ((Button)findViewById(R.id.dos_jug)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(1);

    }
    private void marcar(int casilla){
        ImageView imagen;
        imagen=(ImageView) findViewById(CASILLAS[casilla]);
        if(partida.jugador==1) {
            imagen.setImageResource(R.drawable.circulo);
        }else{
            imagen.setImageResource(R.drawable.aspa);
        }
    }
    private int jugadores;
    private int[] CASILLAS;
    private Partida partida;


}