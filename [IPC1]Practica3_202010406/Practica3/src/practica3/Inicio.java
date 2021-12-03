package practica3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class Inicio implements ActionListener{
    
    private JButton newGame, config, maxpts, salir;
    private JFrame frame;
    
    public Inicio() {
        principal();
    }

    public void principal(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        
        JPanel panel = new JPanel();
        panel.setSize(400, 400);
        panel.setLayout(null);
        frame.add(panel);

    //Botones para iniciar el juego
        newGame = new JButton("Nuevo Juego");
        newGame.setBounds(150, 175, 200, 30);
        newGame.addActionListener(this);
        panel.add(newGame);

        config = new JButton("Configuración");
        config.setBounds(150, 225, 200, 30);
        config.addActionListener(this);
        panel.add(config);

        maxpts = new JButton("Puntación Máxima");
        maxpts.setBounds(150, 275, 200, 30);
        maxpts.addActionListener(this);
        panel.add(maxpts);

        salir = new JButton("Salir");
        salir.setBounds(150, 325, 200, 30);
        salir.addActionListener(this);
        panel.add(salir);


        ImageIcon image = new ImageIcon("src/Images/stars.jpg");
        JLabel label = new JLabel(image);
        label.setBounds(0, 0, 500, 500);
        panel.add(label);

//Coloca el frame en el centro de la pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGame){
            Juego game = new Juego();
            frame.setVisible(false);
        }else if(e.getSource() == config){
            Configuracion conf = new Configuracion();
            System.out.println("PRESIONADO");
        }else if(e.getSource() == maxpts){
            System.out.println("PRESIONADO");
        }else if(e.getSource() == salir){
            System.exit(0);
        }
    }
}