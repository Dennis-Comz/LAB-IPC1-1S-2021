package practica3;

import java.awt.Graphics;

public class Enemigos implements Runnable{

    Enemigo enemies[][] = new Enemigo[10][3];
    private final int veloDesplazamiento = 5;
    private int x = 900;
    private int y = 75;
    boolean estado = true;
    
    Juego jg = null;
    public Enemigos(Juego ga) {
        jg = ga;
        for (int i = 0; i < 10; i++) {
            enemies[i][0] = new Enemigo(jg, y, x);
            y += 50;
        }
        y = 75;
        x = 850;
        for (int i = 0; i < 10; i++) {
            enemies[i][1] = new Enemigo(jg, y, x);
            y += 50;
        }
        y = 75;
        x = 800;
        for (int i = 0; i < 10; i++) {
            enemies[i][2] = new Enemigo(jg, y, x);
            y += 50;            
        }
        
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public boolean movi(){
         x -= 10;
         if(x == 50){
             estado = false;
             return true;
         }
         return false;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(5);
            }catch(InterruptedException e){}
            movi();
        }
    }
    public void drawEnemies(Graphics g){
        for (int i = 0; i < 10; i++) {
            enemies[i][0].drawEnemy(g);
            enemies[i][1].drawEnemy(g);
            enemies[i][2].drawEnemy(g);
        }
    }
}
