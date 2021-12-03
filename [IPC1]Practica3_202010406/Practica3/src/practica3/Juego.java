package practica3;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Juego extends JFrame implements Runnable{
    
    public int Ancho = 1000;
    public int Altura = 600;
    private final int velocidadJuego = 50;
    Nave nave = null;
    Enemigos ens = null;
    Configuracion config = null;
    
    //Graficos donde se situara el juego
    Graphics pantallaInicial, puntos;
    BufferedImage imgPantalla;
    
    public Juego(){
        setTitle("Space Invaders");
        //Inicializacion de clases y asignacion de graficos
        nave = new Nave(this);
        ens = new Enemigos(this);
        imgPantalla = new BufferedImage(Ancho, Altura, BufferedImage.TYPE_INT_ARGB);
        pantallaInicial = imgPantalla.createGraphics();
        puntos = imgPantalla.createGraphics();
        
        //Valores por defecto del frame
        addKeyListener(nave);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Ancho, Altura);
        setVisible(true);
        iniciar();
    }
    
    //Metodo que crea e inicia el hilo para la ejecucion continua del juego
    public void iniciar(){
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void paint(Graphics g){
        //Pantallas principales
        pantallaInicial.setColor(Color.BLACK);
        pantallaInicial.fillRect(0, 0, Ancho, Altura);
        puntos.setColor(Color.red);
        puntos.fillRect(0, 0, 1000, 75);
        
        //Objetos sobre pantallas
        nave.drawNave(pantallaInicial);
        ens.drawEnemies(pantallaInicial);
        g.drawImage(imgPantalla, 0, 0, this);
    }
    
    @Override
    public void update(Graphics g){ paint(g); }
    
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(velocidadJuego);
            }catch(InterruptedException ie){}
            repaint();
        }
    }   
}