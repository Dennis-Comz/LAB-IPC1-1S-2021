package proyecto1;

import java.awt.Graphics2D;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import javax.swing.*;
import com.google.gson.*;
import com.lowagie.text.pdf.PdfTemplate;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
        
public class Administracion implements ActionListener, Serializable{

    ObjetosActividades objetosActividades;

    JFrame frame;
    JTabbedPane tabbedPane;
    DefaultTableModel modelProfe, modelCurso, modelAlumno;
    JTable tableProfe, tableCurso, tableAlumno;
    JScrollPane scrollProfe, scrollCurso, scrollAlumno;
    JButton[] buttons,bProfe, bCurso, bAlumno;
    int contadorB, selectedIndex, idc;
    JPanel curso, profe, alumno;
    JButton actualizar, actualizarC, salir;
    JTextField[] textsAc;
    JComboBox comboAc;
    String[] values, profes;
    Integer row, rowc;
    
    public Administracion() {
        objetosActividades = new ObjetosActividades();
        
        frame = new JFrame();
        frame.setTitle("Administracion");
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        
        salir = new JButton("SALIR");
        salir.setBounds(880, 2, 100, 20);
        salir.setBackground(Color.CYAN);
        salir.addActionListener(this);
        
//Profesores modulo administracion
        profe = new JPanel();
        profe.setLayout(null);
        profe.setBackground(Color.yellow);
        profe.add(salir);
        tabbedPane.addTab("Profesores", profe);
        
        String[] columnasProfe = {"Código", "Nombre", "Apellido", "Correo", "Genero"};
        modelProfe = new DefaultTableModel(null, columnasProfe){@Override public boolean isCellEditable(int row, int column) {return false;}};
        tableProfe = new JTable(modelProfe);
        scrollProfe = new JScrollPane(tableProfe);
        scrollProfe.setBounds(10, 50, 500, 450);
        profe.add(scrollProfe);
        
        String[] buttonsProfe = {"Crear", "Carga Masiva", "Actualizar", "Eliminar", "Exportar Listado a PDF"};
        int lengthProfe = buttonsProfe.length;
        bProfe = new JButton[lengthProfe];
        int Ypar = 50;
        int Yimpar = 50;
        int contadorBP = 1;
        for (int i = 0; i < lengthProfe; i++) {
            JButton temporalBtn = new JButton(buttonsProfe[i]);
            if( contadorBP % 2 != 0){
                temporalBtn.setBounds(550, Yimpar, 150, 30);
                Yimpar += 50;
            }else if( contadorBP % 2 == 0){
                temporalBtn.setBounds(750, Ypar, 150, 30);
                Ypar += 50;
            }
            if( (lengthProfe - 1) % 2 == 0 && contadorBP == lengthProfe){
                temporalBtn.setSize(350, 30);
            }
            contadorBP++;
            bProfe[i] = temporalBtn;
            profe.add(temporalBtn);
        }
        
//Cursos modulo Administracion
        curso = new JPanel();
        curso.setLayout(null);
        curso.setBackground(Color.yellow);
        tabbedPane.addTab("Cursos", curso);
        
        String[] columnasCurso = {"Codigo", "Nombre", "Creditos", "Alumnos", "Profesor"}; 
        modelCurso = new DefaultTableModel(null, columnasCurso){@Override public boolean isCellEditable(int row, int column) {return false;}};
        tableCurso = new JTable(modelCurso);
        scrollCurso = new JScrollPane(tableCurso);
        scrollCurso.setBounds(10, 50, 500, 450);
        curso.add(scrollCurso);
        
        String[] buttonsCurso = {"Crear", "Carga Masiva", "Actualizar", "Eliminar", "Exportar Listado a PDF"};
        int lengthCurso = buttonsCurso.length;
        bCurso = new JButton[lengthProfe];
        Ypar = 50;
        Yimpar = 50;
        int contadorBC = 1;
        for (int i = 0; i < lengthCurso; i++) {
            JButton temporalBtn = new JButton(buttonsCurso[i]);
            if( contadorBC % 2 != 0){
                temporalBtn.setBounds(550, Yimpar, 150, 30);
                Yimpar += 50;
            }else if( contadorBC % 2 == 0){
                temporalBtn.setBounds(750, Ypar, 150, 30);
                Ypar += 50;
            }
            if( (lengthCurso - 1) % 2 == 0 && contadorBC == lengthCurso){
                temporalBtn.setSize(350, 30);
            }
            contadorBC++;
            bCurso[i] = temporalBtn;
            curso.add(temporalBtn);
        }
        
//Alumnos modulo Administracion
        alumno = new JPanel();
        alumno.setLayout(null);
        alumno.setBackground(Color.yellow);
        tabbedPane.addTab("Alumnos", alumno);
        
        String[] columnasAlumno = {"Codigo", "Nombre", "Apellido", "Correo", "Genero"};
        modelAlumno = new DefaultTableModel(null, columnasAlumno){@Override public boolean isCellEditable(int row, int column) {return false;}};
        tableAlumno = new JTable(modelAlumno);
        scrollAlumno = new JScrollPane(tableAlumno);
        scrollAlumno.setBounds(10, 50, 500, 450);
        alumno.add(scrollAlumno);
        
        String[] buttonsAlumno = {"Carga Masiva", "Exportar Listado a PDF"};
        int lengthAlumno = buttonsAlumno.length;
        bAlumno = new JButton[lengthAlumno];
        Ypar = 50;
        Yimpar = 50;
        int contadorBA = 1;
        for (int i = 0; i < lengthAlumno; i++) {
            JButton temporalBtn = new JButton(buttonsAlumno[i]);
            if( contadorBA % 2 != 0){
                temporalBtn.setBounds(550, Yimpar, 150, 30);
                Yimpar += 50;
            }else if( contadorBA % 2 == 0){
                temporalBtn.setBounds(750, Ypar, 150, 30);
                Ypar += 50;
            }
            if( (lengthAlumno - 1) % 2 == 0 && contadorBA == lengthAlumno){
                temporalBtn.setSize(350, 30);
            }
            contadorBA++;
            bAlumno[i] = temporalBtn;
            alumno.add(temporalBtn);
        }
        frame.getContentPane().add(tabbedPane);

//Profesores 
    String[] labelsProfe = {"Codigo", "Nombre", "Apellido", "Correo", "Contraseña", "Genero"};
    String[] genero = {"M", "F"};
    bProfe[0].addActionListener((ActionEvent e) ->{
        crear("Agregar Profesor", labelsProfe, objetosActividades.profesores, genero);
    });
    bProfe[1].addActionListener((ActionEvent e) ->{
            try {
                cargaMasiva(objetosActividades.profesores);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }
            grafica(objetosActividades.profesores);
    });
    bProfe[2].addActionListener((ActionEvent e) ->{
        actualizar("Actualizar Profesor", labelsProfe, objetosActividades.profesores, genero);
    });
    bProfe[3].addActionListener((ActionEvent e) ->{
        eliminar(objetosActividades.profesores);
    });
    bProfe[4].addActionListener((ActionEvent e) ->{
       print(tableProfe, "ListadoProfesores"); 
    });
//Cursos
    String[] labelsCurso = {"Codigo", "Nombre", "Creditos", "Profesor"};
    profes = new String[50];
    bCurso[0].addActionListener((ActionEvent e) ->{
        for (int i = 0; i < objetosActividades.contadorProfes; i++) {
            if(objetosActividades.profesores[i] != null){
                profes[i] = (objetosActividades.profesores[i].getNombre() + " " + objetosActividades.profesores[i].getApellido());            
            }
        }
        crear("Agregar Curso", labelsCurso, objetosActividades.cursos, profes);
    });
    bCurso[1].addActionListener((ActionEvent e) ->{
            try {
                cargaMasiva(objetosActividades.cursos);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }
            grafica(objetosActividades.cursos);
    });
    bCurso[2].addActionListener((ActionEvent e) ->{
        for (int i = 0; i < objetosActividades.contadorProfes; i++) {
            if(objetosActividades.profesores[i] != null){
                profes[i] = (objetosActividades.profesores[i].getNombre() + " " + objetosActividades.profesores[i].getApellido());            
            }
        }
        actualizar("Actualizar Curso", labelsCurso, objetosActividades.cursos, profes);
    });
    bCurso[3].addActionListener((ActionEvent e) ->{
        eliminar(objetosActividades.cursos);
    });
    bCurso[4].addActionListener((ActionEvent e) ->{
       print(tableCurso, "ListadoCursos"); 
    });
//Alumnos
    bAlumno[0].addActionListener((ActionEvent e) ->{
        try {
                cargaMasiva(objetosActividades.alumnos);
        } catch (FileNotFoundException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        grafica(objetosActividades.alumnos);
    });
    bAlumno[1].addActionListener((ActionEvent e) ->{
       print(tableAlumno, "ListadoAlumnos"); 
    });
    
    frame.setVisible(true);
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
    
    public void crear(String title, String[] labels, Object[] arrObjeto, String[] opcion){
        JFrame frameCrear = new JFrame();
        frameCrear.setTitle("Crear");
        frameCrear.setSize(500, 500);
        JPanel panel = new JPanel();
        panel.setBackground(Color.yellow);
        frameCrear.add(panel);
        panel.setLayout(null);
        
        JLabel titulo = new JLabel(title);
        titulo.setBounds(100, 0, 150, 30);
        
        int posY = 50;
        int n = labels.length;
        
        JLabel[] label = new JLabel[n];
        JTextField[] texts = new JTextField[n];
        JComboBox combo = new JComboBox(opcion);
        
        for (int i = 0; i < labels.length; i++) {
            JLabel lb = new JLabel(labels[i]);
            JTextField text = new JTextField();
            
            lb.setBounds(10, posY, 150, 30);
            if(lb.getText().equals("Genero") || lb.getText().equals("Profesor")){
                combo.setBounds(120, posY, 100, 25);
            }else{
                text.setBounds(120, posY, 350, 25);
            }
            posY += 50;
            
            label[i] = lb;
            texts[i] = text;
            panel.add(combo);
            panel.add(text);
            panel.add(lb);
        }
        JButton agregar = new JButton("Agregar");
        agregar.setSize(250, 70);
        agregar.setBounds(100, 350, 250, 50);
        String[] entradas = new String[5];
        agregar.addActionListener((ActionEvent e) -> {
                for (int j = 0; j < arrObjeto.length; j++) {
                    if(arrObjeto[j] == null){
                        if(arrObjeto.equals(objetosActividades.profesores)){
                            objetosActividades.guardarProfe(Integer.parseInt(texts[0].getText()), texts[1].getText(), texts[2].getText(), texts[3].getText(), combo.getSelectedItem().toString(), texts[4].getText());
                            entradas[0] = String.valueOf(objetosActividades.profesores[j].getCodigo());
                            entradas[1] = objetosActividades.profesores[j].getNombre();
                            entradas[2] = objetosActividades.profesores[j].getApellido();
                            entradas[3] = objetosActividades.profesores[j].getCorreo();
                            entradas[4] = objetosActividades.profesores[j].getGenero();
                            modelProfe.addRow(entradas);
                        }else if(arrObjeto.equals(objetosActividades.cursos)){
                            int id = 0;
                            for (int i = 0; i < objetosActividades.contadorProfes; i++) {
                                if(!opcion[i].equals("")){
                                    String temporalName = (objetosActividades.profesores[i].getNombre() + " " + objetosActividades.profesores[i].getApellido());
                                    if(opcion[i].equals(temporalName)){
                                        id = objetosActividades.profesores[i].getCodigo();
                                    }
                                }                                
                            }
                            objetosActividades.guardarCurso(Integer.parseInt(texts[0].getText()), texts[1].getText(), Integer.parseInt(texts[2].getText()), id);
                            System.out.println(objetosActividades.cursos[j].getIdProf());
                            entradas[0] = String.valueOf(objetosActividades.cursos[j].getCodigo());
                            entradas[1] = objetosActividades.cursos[j].getNombre();
                            entradas[2] = String.valueOf(objetosActividades.cursos[j].getCreditos());
                            entradas[3] = "0";
                            entradas[4] = String.valueOf(combo.getSelectedItem());
                            modelCurso.addRow(entradas);
                        }
                        break;
                    }
                }
                                   
                for (int i = 0; i < texts.length; i++) {
                    texts[i].setText("");
                }
        }
        );   
        
        panel.add(agregar);
        panel.add(titulo);
        frameCrear.setVisible(true);
    }
    
    public void cargaMasiva(Object[] objeto) throws FileNotFoundException{
        JFrame cargaMas = new JFrame();
        cargaMas.setTitle("Carga Masiva");
        cargaMas.setSize(200, 150);

        JFileChooser fc = new JFileChooser();
        int op = fc.showOpenDialog(cargaMas);
        if(objeto.equals(objetosActividades.profesores)){
            if(op == JFileChooser.APPROVE_OPTION){
                    String path = String.valueOf(fc.getSelectedFile());
                    try{
                        Gson gson = new Gson();

                        Reader reader = Files.newBufferedReader(Paths.get(path));
                        String[] temporal = new String[5];
                        
                        
                            Profesor[] profesores = gson.fromJson(reader, Profesor[].class);

                            String contra = "1234";
                            for (Profesor profesor : profesores) {
                                temporal[0] = String.valueOf(profesor.getCodigo());
                                temporal[1] = profesor.getNombre();
                                temporal[2] = profesor.getApellido();
                                temporal[3] = profesor.getCorreo();
                                temporal[4] = profesor.getGenero().toUpperCase();
                                objetosActividades.guardarProfe(profesor.getCodigo(), profesor.getNombre(), profesor.getApellido(), profesor.getCorreo(), profesor.getGenero().toUpperCase(), contra);
                                modelProfe.addRow(temporal);
                            }
                            for (int i = 0; i < 5; i++) {
                                temporal[i] = "";
                            }
    
                        reader.close();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
            }
        }else if(objeto.equals(objetosActividades.cursos)){
            if(op == JFileChooser.APPROVE_OPTION){
                String path = String.valueOf(fc.getSelectedFile());
                try{

                    Reader reader = Files.newBufferedReader(Paths.get(path));
                    
                    JsonParser parser = new JsonParser();
                    JsonArray gsonArr = parser.parse(reader).getAsJsonArray();
                    
                    String[] temporal = new String[5];                        
                    for (JsonElement obj : gsonArr) {
                        
                        JsonObject gsonObj = obj.getAsJsonObject();
                        int codigo = gsonObj.get("codigo").getAsInt();
                        String nombre = gsonObj.get("nombre").getAsString();
                        int creditos = gsonObj.get("creditos").getAsInt();
                        int profesor = gsonObj.get("profesor").getAsInt();
                        String nameProfesor = "";
                        for (int i = 0; i < objetosActividades.contadorProfes; i++) {
                            if(profesor == objetosActividades.profesores[i].getCodigo()){
                                nameProfesor = objetosActividades.profesores[i].getNombre() + " " + objetosActividades.profesores[i].getApellido(); 
                            }
                        }
                        temporal[0] = String.valueOf(codigo);
                        temporal[1] = nombre;
                        temporal[2] = String.valueOf(creditos);
                        temporal[3] = "0";
                        temporal[4] = String.valueOf(nameProfesor);
                        objetosActividades.guardarCurso(codigo, nombre, creditos, profesor);
                        modelCurso.addRow(temporal);
                    }
                    reader.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }             
        }else if(objeto.equals(objetosActividades.alumnos)){
            if(op == JFileChooser.APPROVE_OPTION){
                String path = String.valueOf(fc.getSelectedFile());
                try{

                    Reader reader = Files.newBufferedReader(Paths.get(path));
                    
                    JsonParser parser = new JsonParser();
                    JsonArray gsonArr = parser.parse(reader).getAsJsonArray();
                    
                    String[] temporal = new String[5];                        
                    for (JsonElement obj : gsonArr) {
                        
                        JsonObject gsonObj = obj.getAsJsonObject();
                        int codigo = gsonObj.get("codigo").getAsInt();
                        String nombre = gsonObj.get("nombre").getAsString();
                        String apellido = gsonObj.get("apellido").getAsString();
                        String correo = gsonObj.get("correo").getAsString();
                        String genero = gsonObj.get("genero").getAsString().toUpperCase();

                        temporal[0] = String.valueOf(codigo);
                        temporal[1] = nombre;
                        temporal[2] = apellido;
                        temporal[3] = correo;
                        temporal[4] = genero;
                        objetosActividades.guardarAlumno(codigo, nombre, apellido, correo, genero);
                        modelAlumno.addRow(temporal);
                    }
                    reader.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }             
        }
    }
    
    public void actualizar(String title, String[] labels, Object[] objeto, String[] opcion){
       row = tableProfe.getSelectedRow();
       rowc = tableCurso.getSelectedRow();
       Integer column = tableProfe.getSelectedColumn();
            JFrame frameAc = new JFrame();
            frameAc.setTitle("Crear");
            frameAc.setSize(500, 500);
            JPanel panelAc = new JPanel();
            panelAc.setBackground(Color.yellow);
            frameAc.add(panelAc);
            panelAc.setLayout(null);
            
            JLabel titulo = new JLabel(title);
            titulo.setBounds(100, 0, 150, 30);

            int posY = 50;
            int n = labels.length;

            JLabel[] label = new JLabel[n];
            textsAc = new JTextField[n];
            comboAc = new JComboBox(opcion);

            for (int i = 0; i < labels.length; i++) {
                JLabel lb = new JLabel(labels[i]);
                JTextField text = new JTextField();

                lb.setBounds(10, posY, 150, 30);
                if(lb.getText().equals("Genero") || lb.getText().equals("Profesor")){
                    comboAc.setBounds(120, posY, 100, 25);
                }else{
                    text.setBounds(120, posY, 350, 25);
                }
                posY += 50;

                label[i] = lb;
                textsAc[i] = text;
                panelAc.add(comboAc);
                panelAc.add(text);
                panelAc.add(lb);
            }
            
            if(objeto.equals(objetosActividades.profesores)){
                        textsAc[0].setEditable(false);
                        values = new String[5];
                        values[0] = String.valueOf(tableProfe.getValueAt(row, 0));
                        values[1] = String.valueOf(tableProfe.getValueAt(row, 1));
                        values[2] = String.valueOf(tableProfe.getValueAt(row, 2));
                        values[3] = String.valueOf(tableProfe.getValueAt(row, 3));
                        comboAc.setSelectedItem(tableProfe.getValueAt(row, 4));
                        
                        textsAc[0].setText(values[0]);
                        textsAc[1].setText(values[1]);
                        textsAc[2].setText(values[2]);
                        textsAc[3].setText(values[3]);
                        
                for (int i = 0; i < objetosActividades.contadorProfes; i++) {
                    if(Integer.parseInt(textsAc[0].getText()) == objetosActividades.profesores[i].getCodigo()){
                        values[4] = String.valueOf(objetosActividades.profesores[i].getContra());
                        textsAc[4].setText(values[4]);
                        actualizar = new JButton("Actualizar");
                        actualizar.setSize(250, 70);
                        actualizar.setBounds(100, 350, 250, 50);
                        String[] entradas = new String[5];
                        actualizar.addActionListener(this);
                    panelAc.add(actualizar);
                    }
                }
            }else if(objeto.equals(objetosActividades.cursos)){
                textsAc[0].setEditable(false);
                values = new String[4];
                values[0] = String.valueOf(tableCurso.getValueAt(rowc, 0)); //Codigo
                values[1] = String.valueOf(tableCurso.getValueAt(rowc, 1)); 
                values[2] = String.valueOf(tableCurso.getValueAt(rowc, 2));
                comboAc.setSelectedItem(tableCurso.getValueAt(rowc, 4));
                
                textsAc[0].setText(values[0]);
                textsAc[1].setText(values[1]);
                textsAc[2].setText(values[2]);

                for (int i = 0; i < objetosActividades.contadorCursos; i++) {
                    if(Integer.parseInt(textsAc[0].getText()) == objetosActividades.cursos[i].getCodigo()){
                        values[3] = String.valueOf(objetosActividades.cursos[i].getIdProf());
                        actualizarC = new JButton("Actualizar");
                        actualizarC.setSize(250, 70);
                        actualizarC.setBounds(100, 350, 250, 50);
                        String[] entradas = new String[5];
                        actualizarC.addActionListener(this);
                    panelAc.add(actualizarC);
                    }
                }
            }
            frameAc.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == actualizar){
                String[] c = {textsAc[1].getText(), textsAc[2].getText(), textsAc[3].getText(), textsAc[4].getText(), String.valueOf(comboAc.getSelectedItem())};
                tableProfe.setValueAt(textsAc[1].getText(), row, 1);
                tableProfe.setValueAt(textsAc[2].getText(), row, 2);
                tableProfe.setValueAt(textsAc[3].getText(), row, 3);
                tableProfe.setValueAt(comboAc.getSelectedItem(), row, 4);
                cambio(c, objetosActividades.profesores, row);
        }else if(e.getSource() == actualizarC){
                idc = 0;
                for (int i = 0; i < objetosActividades.contadorProfes; i++) {
                    if(!profes[i].equals("")){
                        if(profes[i].equals(comboAc.getSelectedItem())){
                            idc = objetosActividades.profesores[i].getCodigo();
                        break;
                        }
                    }                                
                }
                String[] c = {textsAc[1].getText(), textsAc[2].getText(), String.valueOf(idc)};
                tableCurso.setValueAt(textsAc[1].getText(), rowc, 1);
                tableCurso.setValueAt(textsAc[2].getText(), rowc, 2);
                tableCurso.setValueAt(comboAc.getSelectedItem(), rowc, 4);
                cambio(c, objetosActividades.cursos, rowc);
        }else if(e.getSource() == salir){
            Login lv = new Login();
            lv.loginSave(objetosActividades);
            try {
                lv.displayLogin();
            } catch (IOException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(false);
        }
    }
    
    public void cambio(String[] cambios, Object[] objeto, int posicion){
        if(objeto.equals(objetosActividades.profesores)){
            objetosActividades.profesores[posicion].setNombre(cambios[0]);
            objetosActividades.profesores[posicion].setApellido(cambios[1]);
            objetosActividades.profesores[posicion].setCorreo(cambios[2]);
            objetosActividades.profesores[posicion].setContra(cambios[3]);
            objetosActividades.profesores[posicion].setGenero(cambios[4]);    
        }else if(objeto.equals(objetosActividades.cursos)){
            objetosActividades.cursos[posicion].setNombre(cambios[0]);
            objetosActividades.cursos[posicion].setCreditos(Integer.parseInt(cambios[1]));
            objetosActividades.cursos[posicion].setIdProf(Integer.parseInt(cambios[2]));
        }
    }
    
    public void eliminar(Object[] objeto){
        row = tableProfe.getSelectedRow();
        rowc = tableCurso.getSelectedRow();
        
        if(objeto.equals(objetosActividades.profesores)){
            int codigoP = Integer.parseInt(tableProfe.getValueAt(row, 0).toString());
            for (int i = 0; i < objetosActividades.contadorProfes; i++) {
                if(codigoP == objetosActividades.profesores[i].getCodigo()){
                    objetosActividades.profesores[i] = null;
                    ((DefaultTableModel)tableProfe.getModel()).removeRow(row);
                    break;
                }                
            }
        }else if(objeto.equals(objetosActividades.cursos)){
            int codigoC = Integer.parseInt(tableCurso.getValueAt(rowc, 0).toString());
            for (int i = 0; i < objetosActividades.contadorCursos; i++) {
                if(codigoC == objetosActividades.cursos[i].getCodigo()){
                    objetosActividades.cursos[i] = null;
                    ((DefaultTableModel)tableCurso.getModel()).removeRow(rowc);
                    objetosActividades.contadorCursos--;  
                    break;
                }                
            }
        }
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
    public void grafica(Object[] objeto){
        if(objeto.equals(objetosActividades.profesores)){
            if(tableProfe.getRowCount() == 0){

            }else{
                int contadorM = 0;
                int contadorF = 0;
                String[] generos = new String[tableProfe.getRowCount()];
                for (int i = 0; i < objetosActividades.contadorProfes; i++) {
                    generos[i] = objetosActividades.profesores[i].getGenero();
                    if(generos[i].equals("M")){
                        contadorM += 1;
                    }else if ("F".equals(objetosActividades.profesores[i].getGenero())){
                        contadorF += 1;
                    }
                }
                DefaultPieDataset dataset = new DefaultPieDataset();
                dataset.setValue("Masculino", new Integer(contadorM));
                dataset.setValue("Femenino", new Integer(contadorF));
                JFreeChart chart = ChartFactory.createPieChart("GENEROS", dataset, true, true, true);
                PiePlot P = (PiePlot)chart.getPlot();
                ChartPanel cp = new ChartPanel(chart);
                cp.setBounds(550, 250, 400, 300);
                profe.add(cp, BorderLayout.CENTER);
                profe.validate();            
            }            
        }else if(objeto.equals(objetosActividades.alumnos)){
            if(tableAlumno.getRowCount() == 0){

            }else{
                int contadorM = 0;
                int contadorF = 0;
                for (int i = 0; i < objetosActividades.contadorAlumnos; i++) {
                    if("M".equals(objetosActividades.alumnos[i].getGenero())){
                        contadorM += 1;
                    }else if ("F".equals(objetosActividades.alumnos[i].getGenero())){
                        contadorF += 1;
                    }
                }
                DefaultPieDataset dataset = new DefaultPieDataset();
                dataset.setValue("Masculino", new Integer(contadorM));
                dataset.setValue("Femenino", new Integer(contadorF));
                JFreeChart chart = ChartFactory.createPieChart("GENEROS", dataset, true, true, true);
                PiePlot P = (PiePlot)chart.getPlot();
                ChartPanel cp = new ChartPanel(chart);
                cp.setBounds(550, 250, 400, 300);
                alumno.add(cp, BorderLayout.CENTER);
                alumno.validate();            
            }
        }else if(objeto.equals(objetosActividades.cursos)){
            if(tableCurso.getRowCount() == 0){

            }else{
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.addValue(1, objetosActividades.cursos[0].getNombre(), objetosActividades.cursos[0].getNombre());
                dataset.addValue(2, objetosActividades.cursos[1].getNombre(), objetosActividades.cursos[1].getNombre());
                dataset.addValue(3, objetosActividades.cursos[2].getNombre(), objetosActividades.cursos[2].getNombre());
                JFreeChart barchart = ChartFactory.createBarChart("Cantidad de Alumnos", "Cursos", "Alumnos", dataset, PlotOrientation.VERTICAL, true, true, false);
                ChartPanel cp = new ChartPanel(barchart);
                cp.setBounds(550, 250, 400, 300);
                curso.add(cp, BorderLayout.CENTER);
                curso.validate();            
            }
        }

    }
}