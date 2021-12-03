package practica3;

import java.awt.Color;
import java.awt.Graphics;

public class Bala implements Runnable{
    
    //Valores constantes
    private final int veloDisp = 5;
    private final int AnchoDisp = 7;
    private final int AlturaDisp = 5;
    
    //Valores para poder hacer el movimiento de la bala
    private int y = 0;
    private int altDisp = 0;
    boolean estadoDisp = true;
        
    //Constructor que se inicializa en clase nave
    public Bala(int xPos, int yPos){
        altDisp = xPos;
        y = yPos;
        Thread thread = new Thread(this);
        thread.start();
    }
    
    //Se cambia el valor del estado de la bala para hacerla desaparecer 
    //cuando alcance cierto punto y se incrementa el valor de su movimiento 
    private boolean mov(){
        altDisp += 2;
        if(altDisp > 900){
            estadoDisp = false;
            return true;
        }
        return false;
    }
    
    //Se compara si la vala sigue viva para seguir dibujandola o para poner su color negro
    //al alcanzar la altura maxima
    public void drawShow(Graphics g){
        if(estadoDisp){
            g.setColor(Color.WHITE);
        }else{
            g.setColor(Color.BLACK);
        }
        g.fillRect(altDisp, y, AnchoDisp, AlturaDisp);
    }
    
    public boolean getEstadoDisp(){
        return estadoDisp;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(veloDisp);
            }catch(InterruptedException e){}
            mov();
        }
    }
}
