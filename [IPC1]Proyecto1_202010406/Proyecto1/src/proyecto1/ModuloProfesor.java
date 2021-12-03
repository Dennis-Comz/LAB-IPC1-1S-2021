package proyecto1;

import java.io.Serializable;
import com.google.gson.Gson;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ModuloProfesor implements ActionListener, Serializable {
    
    JFrame frame, frameac;
    ObjetosActividades objetosguardados;
    JButton[] buttons;
    JButton actualizarDatos, actualizar, cargaMasiva, estudiantesMejorRen, salir;
    JButton estudiantesPeorRen, selecCsv, crearActividad;
    JTextField nameText, lastNameText, emailText, passwordText, nombreAc;
    JTextField descripAc, ptsAc;
    JComboBox genero;
    JPanel panelac, panelAdminC;
    DefaultTableModel modelAlumnos, modelActividades;
    JTable alumnos, actividades, tt, tp;
    JScrollPane scrollAlumnos, scrollActividades;
    int codigo, idProf, contador, promedio = 0, valorActividades, posicionnota, pos = 0;
    double top5Mejor[] = new double[5];
    double top5Peor[] = new double[5];
    String btnNombre, botonpresionado = "";
    String[] buttonsNames;
    
    public ModuloProfesor(int posicion, ObjetosActividades object) {
        frame = new JFrame("Modulo Profesor");
        frame.setSize(500, 400);
        frame.setBackground(Color.yellow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel btns = new JPanel();
        btns.setLayout(null);
        btns.setBackground(Color.yellow);
        frame.add(btns);
        
        salir = new JButton("SALIR");
        salir.setBounds(300, 300, 100, 20);
        salir.setBackground(Color.CYAN);
        salir.addActionListener(this);
        btns.add(salir);
        
        objetosguardados = object;
        pos = posicion;
        
        contador = 0;
        for (int i = 0; i < objetosguardados.cursos.length; i++) {
            if(objetosguardados.cursos[i] != null){
                if(objetosguardados.profesores[pos].getCodigo() == objetosguardados.cursos[i].getIdProf()){
                    contador++;
                }                            
            }else{
                break;
            }
        }
        buttonsNames = new String[contador];
        int btnNms = 0;
        for (int i = 0; i < objetosguardados.cursos.length; i++) {
            if(objetosguardados.cursos[i] != null){
                if(objetosguardados.profesores[pos].getCodigo() == objetosguardados.cursos[i].getIdProf()){
                    buttonsNames[btnNms] = objetosguardados.cursos[i].getNombre();
                    btnNms++;
                }                            
            }else{
                break;
            }
        }
        
        actualizarDatos = new JButton("Actualizar Datos");
        actualizarDatos.setBounds(300, 10, 150, 30);
        actualizarDatos.addActionListener(this);
        btns.add(actualizarDatos);
        
        JLabel title = new JLabel("Cursos Asignados");
        title.setBounds(50, 50, 150, 20);
        btns.add(title);
        
        buttons = new JButton[buttonsNames.length];
        int Ypar = 100;
        int Yimpar = 100;
        int contadorBP = 1;
        for (int i = 0; i < buttonsNames.length; i++) {
            JButton tmpBtn = new JButton(buttonsNames[i]);
            if( contadorBP % 2 != 0){
                tmpBtn.setBounds(50, Yimpar, 175, 75);
                Yimpar += 50;
            }else if( contadorBP % 2 == 0){
                tmpBtn.setBounds(275, Ypar, 175, 75);
                Ypar += 50;
            }
            contadorBP++;
            buttons[i] = tmpBtn;
            buttons[i].addActionListener(this);
            btns.add(buttons[i]);
        }
        

        frame.setVisible(true);
    }

    public void actualizarDatos(){
        frameac = new JFrame();
        frameac.setSize(500, 500);
        panelac = new JPanel();
        panelac.setLayout(null);
        panelac.setBackground(Color.yellow);
        frameac.add(panelac);
        
        JLabel titulo = new JLabel("Actualizar Datos");
        titulo.setBounds(50, 50, 150, 20);
        panelac.add(titulo);
        
        JLabel name = new JLabel("Nombre");
        name.setBounds(20, 100, 150, 20);
        panelac.add(name);
        nameText = new JTextField();
        nameText.setBounds(100, 100, 250, 30);
        nameText.setText(objetosguardados.profesores[pos].getNombre());
        panelac.add(nameText);
        
        JLabel lastName = new JLabel("Apellido");
        lastName.setBounds(20, 150, 150, 20);
        panelac.add(lastName);
        lastNameText = new JTextField();
        lastNameText.setBounds(100, 150, 250, 30);
        lastNameText.setText(objetosguardados.profesores[pos].getApellido());
        panelac.add(lastNameText);
        
        JLabel email = new JLabel("Correo");
        email.setBounds(20, 200, 150, 20);
        panelac.add(email);
        emailText = new JTextField();
        emailText.setBounds(100, 200, 250, 30);
        emailText.setText(objetosguardados.profesores[pos].getCorreo());
        panelac.add(emailText);

        JLabel pass = new JLabel("Contraseña");
        pass.setBounds(20, 250, 150, 20);
        panelac.add(pass);
        passwordText = new JTextField();
        passwordText.setBounds(100, 250, 250, 30);
        passwordText.setText(objetosguardados.profesores[pos].getContra());
        panelac.add(passwordText);
        
        JLabel gene = new JLabel("Genero");
        gene.setBounds(20, 300, 150, 20);
        panelac.add(gene);
        String[] opcionGene = {"M","F"};
        genero = new JComboBox(opcionGene);
        genero.setBounds(100, 300, 75, 30);
        if(objetosguardados.profesores[pos].getGenero().equals(opcionGene[0])){
            genero.setSelectedItem(opcionGene[0]);
        }else if(objetosguardados.profesores[pos].getGenero().equals(opcionGene[1])){
            genero.setSelectedItem(opcionGene[1]);
        }
        panelac.add(genero);
        
        actualizar = new JButton("Actualizar");
        actualizar.setBounds(200, 350, 200, 50);
        actualizar.addActionListener(this);
        panelac.add(actualizar);
        
        frameac.setVisible(true);
    }
    
    public void adminCurso(String titulo){
        JFrame adminFrame = new JFrame();
        adminFrame.setSize(1000, 800);
        panelAdminC = new JPanel();
        panelAdminC.setLayout(null);
        panelAdminC.setBackground(Color.yellow);
        adminFrame.add(panelAdminC);
        
        btnNombre = titulo;
        JLabel title = new JLabel(titulo);
        title.setBounds(30, 20, 100, 50);
        panelAdminC.add(title);
        
//Lado Izquierdo
        JLabel tituloAlumnos = new JLabel("Listado de Alumnos");
        tituloAlumnos.setBounds(20, 50, 150, 20);
        panelAdminC.add(tituloAlumnos);
        
        String[] columnasAlumnos = {"Codigo", "Nombre", "Apellido", "Acciones"};
        modelAlumnos = new DefaultTableModel(null, columnasAlumnos){@Override public boolean isCellEditable(int row, int column) {return false;}};
        alumnos = new JTable(modelAlumnos);
        scrollAlumnos = new JScrollPane(alumnos);
        scrollAlumnos.setBounds(20, 70, 450, 330);
        panelAdminC.add(scrollAlumnos);
        cargaMasiva = new JButton("Carga Masiva Alumnos");
        cargaMasiva.addActionListener(this);
        cargaMasiva.setBounds(20, 420, 450, 40);
        panelAdminC.add(cargaMasiva);
        
        JLabel report = new JLabel("Reportes");
        report.setBounds(50, 520, 75, 20);
        estudiantesMejorRen = new JButton("Top 5: Estudiante con Mejor Rendimientos");
        estudiantesMejorRen.addActionListener(this);
        estudiantesMejorRen.setBounds(20, 570, 450, 50);
        estudiantesPeorRen = new JButton("Top 5: Estudiante con Peor Rendimientos");
        estudiantesPeorRen.addActionListener(this);
        estudiantesPeorRen.setBounds(20, 640, 450, 50);
        panelAdminC.add(report);
        panelAdminC.add(estudiantesMejorRen);
        panelAdminC.add(estudiantesPeorRen);

//Lado Derecho
        JLabel tituloActividades = new JLabel("Actividades");
        tituloActividades.setBounds(500, 50, 150, 20);
        panelAdminC.add(tituloActividades);
        
        String[] columnasActividades = {"Nombre", "Descripcion", "Ponderacion", "Promedio"};
        modelActividades = new DefaultTableModel(null, columnasActividades){@Override public boolean isCellEditable(int row, int column) {return false;}};
        actividades = new JTable(modelActividades);
        scrollActividades = new JScrollPane(actividades);
        scrollActividades.setBounds(500, 70, 450, 150);
        panelAdminC.add(scrollActividades);
        
        valorActividades = 0;
        JLabel acumu = new JLabel("Acumulado:");
        acumu.setBounds(680, 250, 100, 20);
        JLabel acumuValor = new JLabel(String.valueOf(valorActividades) + "/100");
        acumuValor.setBounds(780, 250, 50, 20);
        panelAdminC.add(acumu);
        panelAdminC.add(acumuValor);
        
        JLabel crearAc =  new JLabel("Crear Actividades");
        crearAc.setBounds(530, 400, 100, 20);
        panelAdminC.add(crearAc);
        
        JLabel nm = new JLabel("Nombre");
        nm.setBounds(550, 450, 100, 20);
        panelAdminC.add(nm);
        nombreAc = new JTextField();
        nombreAc.setBounds(670, 450, 260, 30);
        panelAdminC.add(nombreAc);
        
        JLabel descrip = new JLabel("Descripcion");
        descrip.setBounds(550, 500, 100, 20);
        panelAdminC.add(descrip);
        descripAc = new JTextField();
        descripAc.setBounds(670, 500, 260, 30);
        panelAdminC.add(descripAc);
        
        JLabel pts = new JLabel("Ponderacion");
        pts.setBounds(550, 550, 100, 20);
        panelAdminC.add(pts);
        ptsAc = new JTextField();
        ptsAc.setBounds(670, 550, 260, 30);
        panelAdminC.add(ptsAc);
        
        JLabel nts = new JLabel("Notas");
        nts.setBounds(550, 600, 100, 20);
        panelAdminC.add(nts);
        selecCsv = new JButton("Seleccionar archivo CSV");
        selecCsv.addActionListener(this);
        selecCsv.setBounds(670, 600, 260, 30);
        panelAdminC.add(selecCsv);
        
        crearActividad = new JButton("Crear Actividad");
        crearActividad.addActionListener(this);
        crearActividad.setBounds(550, 650, 380, 50);
        panelAdminC.add(crearActividad);
        
        adminFrame.setVisible(true);
    }
    
    public void cargaMasivaAlu(String btnPresionado) throws HeadlessException{
        JFrame cargamasiva = new JFrame();
        cargamasiva.setTitle("Carga Masiva");
        cargamasiva.setSize(300, 200);
        
        JFileChooser fc = new JFileChooser();
        int opcion = fc.showOpenDialog(cargamasiva);
        if(opcion == JFileChooser.APPROVE_OPTION){
            String path = String.valueOf(fc.getSelectedFile());
            try{
                Gson gson = new Gson();
                
                Reader reader = Files.newBufferedReader(Paths.get(path));
                String[] temporal = new String[4];
                
                Alumno[] alumnosJson = gson.fromJson(reader, Alumno[].class);
                int idCurso = 0;
                for (int i = 0; i < objetosguardados.cursos.length; i++) {
                    if(objetosguardados.cursos[i] != null){
                        if(btnPresionado.equals(objetosguardados.cursos[i].getNombre())){
                            idCurso = i;
                        }                        
                    }
                }
                JLabel masInfo = new JLabel("Ver mas informacion");
                masInfo.setForeground(Color.BLUE.darker());
                masInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                
                int contadoralumnos = 0;
                for(Alumno alumno: alumnosJson){
                    objetosguardados.asignarAlumno(alumno.getCodigo(), idCurso, contadoralumnos);
                    temporal[0] = String.valueOf(alumno.getCodigo());
                    for (int i = 0; i < objetosguardados.contadorAlumnos; i++) {
                        if(objetosguardados.alumnos[i] != null){
                            if(alumno.getCodigo() == objetosguardados.alumnos[i].getCodigo()){
                             temporal[1] = objetosguardados.alumnos[i].getNombre();
                             temporal[2] = objetosguardados.alumnos[i].getApellido();
                            }
                        }
                    }
                    temporal[3] = String.valueOf(masInfo.getText());
                    modelAlumnos.addRow(temporal);
                    contadoralumnos++;                    
                }
                reader.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void cargarCsv() throws FileNotFoundException{
        JFrame cargamasiva = new JFrame();
        cargamasiva.setTitle("Carga Masiva");
        cargamasiva.setSize(300, 200);
        
        String path = "";
        JFileChooser fc = new JFileChooser();
        int opcion = fc.showOpenDialog(cargamasiva);
        if(opcion == JFileChooser.APPROVE_OPTION){
            path = String.valueOf(fc.getSelectedFile());
        }
        
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        
        posicionnota = 0;
        for (int i = 0; i < buttons.length; i++) {
            if(botonpresionado.equals(buttons[i].getText())){
                posicionnota = i;
            }
        }
        
        promedio = Integer.parseInt(ptsAc.getText());
        int ctnnotas = 0;
        double suma = 0;
        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String[] entrada = lineaEntrada.split(",");
                double n = Double.parseDouble(entrada[1]);
                objetosguardados.asignarNotas(Integer.parseInt(entrada[0]), n, posicionnota);
                suma += n;
                ctnnotas++;
            }
        }
        valorActividades++;
        promedio = Math.round((float)suma / (float)ctnnotas);
    }
    
    public void crearActividad(){
        String[] temporal = new String[4];
        temporal[0] = nombreAc.getText();
        temporal[1] = descripAc.getText();
        temporal[2] = ptsAc.getText();
        temporal[3] = String.valueOf(promedio);
        modelActividades.addRow(temporal);
        nombreAc.setText("");
        descripAc.setText("");
        ptsAc.setText("");
    }
    
    public void bubblesort(double arr[]){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if(arr[j] > arr[j+1]){    
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    private void print(JTable table, String titulo) {
    Document document = new Document(PageSize.A4);
    try {
      char cm = '"';
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(titulo + ".pdf"));

      document.open();
      PdfContentByte cb = writer.getDirectContent();

      cb.saveState();
      
      PdfTemplate pdfTemplate = cb.createTemplate(table.getWidth(), table.getHeight());
      Graphics2D g2 = pdfTemplate.createGraphics(table.getWidth(), table.getHeight());
      
      table.print(g2);
      cb.addTemplate(pdfTemplate, 50, 700);
      
      g2.dispose();
      cb.restoreState();
      
      File file = new File(titulo + ".pdf");
      Desktop desktop = Desktop.getDesktop();
      desktop.open(file);
      
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    document.close();
    }
    public void top5Mejor(){
        double arr[] = new double[objetosguardados.contadorAlumnos];
        for (int i = 0; i < objetosguardados.contadorAlumnos; i++) {
            arr[i] = objetosguardados.alumnos[i].notas[posicionnota];
        }
        bubblesort(arr);
        int n = objetosguardados.contadorAlumnos;
        int contadort = 0;
        for (int i = n-1; i > 0 ; i--) {
            if(contadort >= 0 && contadort <= 4){
                top5Mejor[contador] = arr[i];
                System.out.println("======================");
                System.out.println("MEJOR");
                System.out.println("======================");
                System.out.println(arr[i] + " ");
            }
            contadort++;
        }
        String[] titulos = {"Posicion", "Codigo", "Nombre", "Apellido", "Nota Por Actividad", "Nota Acumulada"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tt = new JTable(mt);
        JScrollPane js = new JScrollPane(tt);
        js.setBounds(10, 50, 500, 400);
        
        int posicion = 1;
        for (int i = 0; i < objetosguardados.contadorAlumnos; i++) {
            for (int j = 0; j < 5; j++) {
                if(top5Mejor[j] == objetosguardados.alumnos[i].notas[posicionnota]){
                    String[] temporal = new String[6];
                    temporal[0] = String.valueOf(posicion);
                    temporal[1] = String.valueOf(objetosguardados.alumnos[i].getCodigo());
                    temporal[2] = objetosguardados.alumnos[i].getNombre();
                    temporal[3] = objetosguardados.alumnos[i].getApellido();
                    temporal[4] = String.valueOf(objetosguardados.alumnos[i].notas[posicionnota]);
                    temporal[5] = "0";
                    mt.addRow(temporal);
                }
            }
        }
        
        print(tt, "Top5Mejor");
    }
    
    public void top5Peor(){
        double arr[] = new double[objetosguardados.contadorAlumnos];
        for (int i = 0; i < objetosguardados.contadorAlumnos; i++) {
            arr[i] = objetosguardados.alumnos[i].notas[posicionnota];
        }
        bubblesort(arr);
        int n = objetosguardados.contadorAlumnos;
        int contadort = 0;
        for (int i = 0; i < n-1 ; i++) {
            if(contadort >= 0 && contadort <= 4){
                top5Peor[contador] = arr[i];
                System.out.println("======================");
                System.out.println("PEOR");
                System.out.println("======================");
                System.out.println(arr[i] + " ");
            }
            contadort++;
        }
        String[] titulos = {"Posicion", "Codigo", "Nombre", "Apellido", "Nota Por Actividad", "Nota Acumulada"};
        DefaultTableModel mt = new DefaultTableModel(null, titulos);
        tp = new JTable(mt);
        JScrollPane js = new JScrollPane(tp);
        js.setBounds(10, 50, 500, 400);
        
        int posicion = 1;
        for (int i = 0; i < objetosguardados.contadorAlumnos; i++) {
            for (int j = 0; j < 5; j++) {
                if(top5Mejor[j] == objetosguardados.alumnos[i].notas[posicionnota]){
                    String[] temporal = new String[6];
                    temporal[0] = String.valueOf(posicion);
                    temporal[1] = String.valueOf(objetosguardados.alumnos[i].getCodigo());
                    temporal[2] = objetosguardados.alumnos[i].getNombre();
                    temporal[3] = objetosguardados.alumnos[i].getApellido();
                    temporal[4] = String.valueOf(objetosguardados.alumnos[i].notas[posicionnota]);
                    temporal[5] = "0";
                    mt.addRow(temporal);
                }
            }
        }
        
        print(tp, "Top5Peor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < contador; i++) {
            if(e.getSource() == buttons[i]){
                botonpresionado = buttons[i].getText();
                adminCurso(buttons[i].getText());
            }
        }
        if(e.getSource() == actualizarDatos){
            actualizarDatos();
        }else if(e.getSource() == actualizar){
            objetosguardados.profesores[pos].setNombre(nameText.getText());
            objetosguardados.profesores[pos].setApellido(lastNameText.getText());
            objetosguardados.profesores[pos].setCorreo(emailText.getText());
            objetosguardados.profesores[pos].setContra(passwordText.getText());
            objetosguardados.profesores[pos].setGenero(String.valueOf(genero.getSelectedItem()));
            System.out.println("CAMBIOS REALIZADOS");
            frameac.dispose();
        }else if(e.getSource() == cargaMasiva){
            System.out.println("SI FUNCIONA");
            cargaMasivaAlu(btnNombre);
        }else if(e.getSource() == selecCsv){
            try {
                cargarCsv();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ModuloProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource() == crearActividad){
            crearActividad();
        }else if(e.getSource() == estudiantesMejorRen){
            top5Mejor();
        }else if(e.getSource() == estudiantesPeorRen){
            top5Peor();
        }else if(e.getSource() == salir){
            Login lg = new Login();
            lg.loginSave(objetosguardados);
            try {
                lg.displayLogin();
            } catch (IOException ex) {
                Logger.getLogger(ModuloProfesor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModuloProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lg.serializar();
            } catch (IOException ex) {
                Logger.getLogger(ModuloProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(false);
        }
    }

}
