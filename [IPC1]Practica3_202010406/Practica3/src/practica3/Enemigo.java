package practica3;

import javax.swing.ImageIcon;
import java.awt.*;

public class Enemigo {
    
    public int alturaEnemigo = 75;
    public int anchoEnemigo = 75;
    private int y = 0;
    private int posX = 0;
    boolean estado = false;
    
    Juego juego = null;
    
    private final Image naveEnemigo = new ImageIcon("src/Images/alien.png").getImage();
    
    public Enemigo(Juego j, int posy, int x){
        juego = j;
        y = posy;
        posX = x;
    }
    
    public void drawEnemy(Graphics g){
        g.drawImage(naveEnemigo, posX, y, alturaEnemigo, anchoEnemigo, juego);
    }
}
