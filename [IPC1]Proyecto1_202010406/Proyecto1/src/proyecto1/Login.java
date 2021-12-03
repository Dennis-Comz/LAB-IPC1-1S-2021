package proyecto1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

public class Login implements ActionListener{
    
    ObjetosActividades objetos;
    public JTextField userText;
    public JPasswordField passwordText;
    private JLabel success, title, label, passwordLabel;
    private JButton button;
    JPanel panel = new JPanel();
    JFrame framelog = new JFrame();
    
    public void displayLogin() throws IOException, FileNotFoundException, ClassNotFoundException{
        login();
        File file = new File("objetosActividades.bin");
        if(file.exists()){
            leerSerializable();            
        }
    }
    
    public void login() {
        framelog.setTitle("Login");
        framelog.setSize(500, 400);
        framelog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framelog.add(panel);
        
        panel.setLayout(null);
        
        title = new JLabel("DTT");
        title.setBounds(250, 50, 80, 25);
        panel.add(title);
        
        label = new JLabel("Codigo ");
        label.setBounds(10, 100,80,25);
        panel.add(label);
        
        userText = new JTextField(20);
        userText.setBounds(100, 100, 300, 25);
        panel.add(userText);
        
        passwordLabel = new JLabel("Contraseña ");
        passwordLabel.setBounds(10, 150, 80, 25);
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 150, 300, 25);
        panel.add(passwordText);
        
        button = new JButton("Iniciar Sesión");
        button.setBounds(100, 200, 300, 25);
        button.addActionListener(this);
        panel.add(button);
        
        success = new JLabel("");
        success.setBounds(100,230,300,25);
        panel.add(success);
       
        panel.setBackground(Color.yellow);
        framelog.setVisible(true);
    }
   
    public void loginSave(ObjetosActividades objeto){
        objetos = objeto;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = String.valueOf(passwordText.getPassword());
        if("admin".equals(user) && "admin".equals(password)){
            success.setText("Login success");
            framelog.setVisible(false);
            Administracion admin = new Administracion();
        }else{
            for (int i = 0; i < 50; i++) {
                if(objetos.profesores[i].getCodigo() == Integer.parseInt(user) && password.equals(objetos.profesores[i].getContra())){
                    framelog.setVisible(false);
                    ModuloProfesor moduProf = new ModuloProfesor(i, objetos);
                    break;
                }
            }            
        }
    }
    
    public void leerSerializable() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("objetosActividades.bin"));
        objetos = (ObjetosActividades) oi.readObject();
    }
    
    public void serializar() throws FileNotFoundException, IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("objetosActividades.bin"));
        os.writeObject(objetos);
        os.close();
    }
}