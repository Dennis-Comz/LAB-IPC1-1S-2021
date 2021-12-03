package practica3;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;

public class Nave implements KeyListener{

    public int alturaNave = 50;
    public int anchoNave = 40;
    private int y = 0;
    private int posX = 0;
    boolean estado = false;
    
    //Declarando las clases para poder acceder luego
    Juego juego = null;
    Bala bala = null;
    
    //Carga de imagen de la nave
    private final Image jugadorNave = new ImageIcon("src/Images/jugador.png").getImage();
    
    //Funcion para poner la posicion inicial de la nave
    public Nave(Juego jg){
        juego = jg;
        y = 300;
        posX = 50;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyPress = e.getKeyCode();
        switch (keyPress) {
            case 38:
                if(y == 75){
                    y = 75;
                }else{
                    y += -5;
                }
            break;
            case 40:
                if(y == 500){
                    y = 500;
                }else{
                    y += 5;
                }
            break;
            case 32:
                bala = new Bala(posX + anchoNave, y + anchoNave + 5);
            break;
            default:
                break;
        }
    
    }
//    Eventos no utilizados
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    
    public void drawNave(Graphics g){
        g.drawImage(jugadorNave, posX, y, 100, 100, juego);
        if((bala != null)){
            bala.drawShow(g);
        }
    }
}
