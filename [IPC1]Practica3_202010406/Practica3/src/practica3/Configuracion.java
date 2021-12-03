package practica3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Configuracion extends JFrame implements ActionListener{
    
    private JPanel items, velocidad, tiempo, botones, contenedor;
    private JRadioButton aumentoTiempo, ptsExtras, aumentoVelocidad, disminucionTiempo, penalizacion, congelacion;
    private JButton guardar, salir;
    private JComboBox level, frecuency;
    private JTextField time;
    
    public Configuracion(){
        items = new JPanel();
        items.setBackground(Color.BLACK);
        items.setLayout(null);
        
        velocidad = new JPanel();
        velocidad.setLayout(null);
        velocidad.setBackground(Color.BLACK);
        
        tiempo = new JPanel();
        tiempo.setLayout(null);
        tiempo.setBackground(Color.BLACK);
        
        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.setBackground(Color.BLACK);
        
        botones = new JPanel();
        botones.setLayout(null);
        botones.setBackground(Color.BLACK);
        
        setLayout(new GridLayout(1,2));
        contenedor.setLayout(new GridLayout(4,1));
        
        datosItems();
        datosVelocidad();
        datosTiempo();
        datosBotones();
        
        items.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "ITEMS"));
        velocidad.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "VELOCIDAD"));
        tiempo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "TIEMPO"));
        
        add(items);
        add(contenedor);
        
        contenedor.add(velocidad);
        
        ImageIcon image = new ImageIcon("src/Images/corazon.png");
        JLabel cora = new JLabel();
        cora.setIcon(image);
        cora.setBounds(100, 20, 50, 50);
        contenedor.add(cora);
        
        contenedor.add(tiempo);
        contenedor.add(botones);
        
                
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void datosItems(){
        ImageIcon image = new ImageIcon("src/Images/corazon.png");
        JLabel cora = new JLabel();
        cora.setIcon(image);
        cora.setBounds(100, 20, 50, 50);
        items.add(cora);
        
        aumentoTiempo = new JRadioButton("Aumento de Tiempo");
        aumentoTiempo.setForeground(Color.WHITE); aumentoTiempo.setBackground(Color.BLACK);
        aumentoTiempo.setBounds(50, 100, 150, 20);
        
        ptsExtras = new JRadioButton("Puntos Extra");
        ptsExtras.setForeground(Color.WHITE); ptsExtras.setBackground(Color.BLACK);
        ptsExtras.setBounds(50, 140, 150, 20);
        
        aumentoVelocidad = new JRadioButton("Aumento de Velocidad");
        aumentoVelocidad.setForeground(Color.WHITE); aumentoVelocidad.setBackground(Color.BLACK);
        aumentoVelocidad.setBounds(50, 180, 200, 20);
        
        disminucionTiempo = new JRadioButton("Disminucion de Tiempo");
        disminucionTiempo.setForeground(Color.WHITE); disminucionTiempo.setBackground(Color.BLACK);
        disminucionTiempo.setBounds(50, 220, 200, 20);
        
        penalizacion = new JRadioButton("Penalizacion");
        penalizacion.setForeground(Color.WHITE); penalizacion.setBackground(Color.BLACK);
        penalizacion.setBounds(50, 260, 150, 20);
        
        congelacion = new JRadioButton("Congelacion");
        congelacion.setForeground(Color.WHITE); congelacion.setBackground(Color.BLACK);
        congelacion.setBounds(50, 300, 150, 20);
        
        JLabel fr = new JLabel("Frecuencia");
        fr.setForeground(Color.WHITE);
        fr.setBounds(50, 360, 100, 20);
        
        frecuency = new JComboBox();
        frecuency.addItem("Poco Frecuente");
        frecuency.addItem("Normal");
        frecuency.addItem("Muy Frecuente");
        frecuency.setBounds(200, 360, 150, 30);
        
        items.add(aumentoTiempo);
        items.add(ptsExtras);
        items.add(aumentoVelocidad);
        items.add(disminucionTiempo);
        items.add(penalizacion);
        items.add(congelacion);
        items.add(fr);
        items.add(frecuency);
        
    }
    
    public void datosVelocidad(){
        JLabel label = new JLabel("Nivel");
        label.setForeground(Color.WHITE);
        label.setBounds(80, 50, 50, 20);
        
        level = new JComboBox();
        level.setBounds(220, 50, 100, 30);
        level.addItem("Normal");
        level.addItem("Rapido");
        
        velocidad.add(level);
        velocidad.add(label);
    }
    
    public void datosTiempo(){
        JLabel label = new JLabel("Tiempo");
        label.setForeground(Color.WHITE);
        label.setBounds(80, 50, 50, 20);
        
        time = new JTextField();
        time.setBounds(220, 50, 100, 30);
        
        tiempo.add(time);
        tiempo.add(label);
    }
    
    public void datosBotones(){
        guardar = new JButton("Guardar");
        guardar.setBackground(Color.green);
        guardar.setForeground(Color.GRAY);
        guardar.addActionListener(this);
        guardar.setBounds(70, 50, 100, 50);

        salir = new JButton("Salir");
        salir.setBackground(Color.red);
        salir.setForeground(Color.GRAY);
        salir.addActionListener(this);
        salir.setBounds(200, 50, 100, 50);
        
        botones.add(guardar);
        botones.add(salir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
