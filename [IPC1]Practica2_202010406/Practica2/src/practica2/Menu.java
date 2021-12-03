/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.Scanner;

/**
 *
 * @author dennis
 */
public class Menu {
    
    ObjetosPoke objetospoke;
    
    public Menu(){
        objetospoke = new ObjetosPoke();
    }
    
    public void displayMenu() throws IOException{
        int opcion = 0;
        
        while(opcion != 11){
           System.out.println("=============== MENU ===============");
           System.out.println(" | 1. Cargar Pokemons             |");
           System.out.println(" | 2. Cargar Entrenadores         |");
           System.out.println(" | 3. Cargar Poke Ball            |");
           System.out.println(" | 4. Cargar Gimnasios            |");
           System.out.println(" | 5. Cargar Alimentos            |");
           System.out.println(" | 6. Asignar Pokemons            |");
           System.out.println(" | 7. Asignar Poke Ball           |");
           System.out.println(" | 8. Asignar Actividad de Comida |");
           System.out.println(" | 9. Asignar Actividad de Pelea  |");
           System.out.println(" | 10. Reportes                    |");
           System.out.println(" | 11. Salir                      |");
           System.out.println("====================================");  
           opcion = elegirOpcion();
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           
           switch(opcion){
                case 1:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaPokemon = br.readLine();
                    cargarPokemon(rutaPokemon);
                   break;
                case 2:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaEntrenadores = br.readLine();
                    cargarEntrenador(rutaEntrenadores);
                   break;
                case 3:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaBalls = br.readLine();
                    cargarPokeBall(rutaBalls);
                   break;
                case 4:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaGym = br.readLine();
                    cargarGimnasio(rutaGym);
                   break;
                case 5:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaAlimento = br.readLine();
                    cargarAlimento(rutaAlimento);
                   break;
                case 6:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaAsignarPoke = br.readLine();
                    asignarPokemon(rutaAsignarPoke);
                    break;
                case 7:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaAsignarEntre = br.readLine();
                    asignarPokeball(rutaAsignarEntre);
                   break;
                case 8:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaAsignarComida = br.readLine();
                    asignarAlimento(rutaAsignarComida);
                   break;
                case 9:
                    System.out.println("Ingrese la ruta del archivo: ");
                    String rutaAsignarPelea = br.readLine();
                    asignarPelea(rutaAsignarPelea);
                   break;
                case 10:
                    serializar();
                    reportes();
                   break; 
                case 11:
                    System.exit(0);
                    break;
           }
        }
    }
    
    public int elegirOpcion(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione la opcion a realizar: ");
        int op = Integer.parseInt(sc.nextLine());
        while(op < 1 || op > 10){
            System.out.println("Ingrese una opcion valida");
            op = Integer.parseInt(sc.nextLine());
        }
        return op;
    }
    
    public void cargarPokemon(String path) throws FileNotFoundException, IOException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));

        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.guardarPokemon(Integer.parseInt(entrada[0]), entrada[1], entrada[2], Double.parseDouble(entrada[3]), Double.parseDouble(entrada[4]), entrada[5].equals("capturado") ? true : false, entrada[6].equals("vivo") ? true : false);
            }
        }
        System.out.println("Pokemones Cargados");
    }
    
    public void cargarEntrenador(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));

        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.guardarEntrenadores(Integer.parseInt(entrada[0]), entrada[1]);
            }
        }
        System.out.println("Entrenadores Cargados");
    }
    
    public void cargarPokeBall(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));

        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.guardarPokeBall(Integer.parseInt(entrada[0]), entrada[1]);
            }
        }
        System.out.println("PokeBalls Cargadas");
    }
    
    public void cargarGimnasio(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));

        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.guardarGimnasios(Integer.parseInt(entrada[0]), entrada[1]);
            }
        }
        System.out.println("Gimnasios Cargados");
    }
    
    public void cargarAlimento(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));

        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.guardarAlimentos(Integer.parseInt(entrada[0]), entrada[1], Double.parseDouble(entrada[2]));
            }
        }
        System.out.println("Alimentos Cargados");
    }
    
    public void asignarPokemon(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.asignarPokemon(Integer.parseInt(entrada[0]), Integer.parseInt(entrada[1]));
            }
        }
        System.out.println("Pokemons asignados a su Pokeball");
    }
    
    public void asignarPokeball(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.asignarPokeBall(Integer.parseInt(entrada[0]), Integer.parseInt(entrada[1]));
            }
        }
        System.out.println("Pokemons asignados a su Entrenador");
    }
    
    public void asignarAlimento(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.asignarComida(Integer.parseInt(entrada[0]), Integer.parseInt(entrada[1]));
            }
        }
        System.out.println("Comida asignada a su pokemon");
    }
    
    public void asignarPelea(String path) throws FileNotFoundException{
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        String lineaEntrada = " ";
        String primerLinea = scan.nextLine();
        while(scan.hasNextLine()){
            lineaEntrada = scan.nextLine();
            if(!primerLinea.equals(lineaEntrada)){
                String []entrada = lineaEntrada.split(",");
                objetospoke.asignarPelea(Integer.parseInt(entrada[0]), Integer.parseInt(entrada[1]), Integer.parseInt(entrada[2]));
            }
        }
        System.out.println("Pelea asignada a su gimnasio");
    }
    public void serializar() throws FileNotFoundException, IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("objetosPoke.bin"));
        os.writeObject(objetospoke);
        os.close();
    }
    public void reportes() throws IOException{
        File file = new File("Reporte.html");
        
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter pw = new PrintWriter(file);
        char comillas = '"';
        pw.println("<style>"
                + "body{"
                + "background: url(fondo.gif);"
                + "font-family: Arial;"
                + "background-size: 1600px 800px;"
                + "}"
                + "h1{"
                + "text-align: center;"
                + "}"
                + "#container{"
                + "background: white;"
                + "opacity: 0.8;"
                + "}"
                + "h2{"
                + "text-align: center;"
                + "background: orange;"
                + "}"
                + "</style>");
        pw.println("<div id= " + comillas + "container" + comillas + ">");
        pw.println("<h1>Reportes Pokemon</h1>");
        pw.println("<h2>Reporte de Entrenadores</h2>");
        pw.println("<div>");
        for (int i = 0; i < objetospoke.contadorEntrenadores; i++) {
            if(objetospoke.entrenadores[i].getPokeball() != null){
                pw.println(objetospoke.entrenadores[i].getNombre() + " " + objetospoke.entrenadores[i].getPokeball().getPokemon().getNombre() + "<br>");
            }
        }
        pw.println("</div>");
        pw.println("<div>");
        pw.println("<h2>Reporte de Pokemons Salvajes</h2>");
        int contadorSalvajes = 0;
        for (int i = 0; i < objetospoke.contadorPokemon; i++) {
            if(objetospoke.pokemon[i].isCapturado() == false){
                pw.println(objetospoke.pokemon[i].getNombre() + ", ");
                contadorSalvajes++;
            }
        }
        pw.println("</div>");
        pw.println("<div>");
        pw.println("<br>Hay un total de: " + contadorSalvajes + " pokemons salvajes");
        pw.println("<h2>Reporte de Comidas</h2>");
        for (int i = 0; i < objetospoke.contadorAlimentos; i++) {
            if(objetospoke.pokemon[i].getAlimento() != null){
                pw.println(objetospoke.pokemon[i].getAlimento().getNombre() + " " + objetospoke.pokemon[i].getNombre() + "<br>");
            }
        }
        pw.println("</div>");
        pw.println("<div>");
        pw.println("<h2>Reporte de Peleas</h2>");
        int contadorPeleas = 0;
        for (int i = 0; i < objetospoke.contadorGimnasios; i++) {
            contadorPeleas++;
            if(objetospoke.gimnasios[i].getPokemon() != null){
                pw.println(contadorPeleas + objetospoke.gimnasios[i].getLugar() + " " + objetospoke.gimnasios[i].getPokemon().getNombre() + "<br>");
            }
        }
        pw.println("<h2>Top 5 de Pokemons con Mayor Ataque</h2>");
        int contadorataque = 0;
        for (int i = 0; i < objetospoke.contadorPokemon; i++) {
            if(objetospoke.pokemon[i].getPtsAtq() > 35){
                contadorataque++;
                if(contadorataque >= 1 && contadorataque <= 5){
                    pw.println(contadorataque + ". " + objetospoke.pokemon[i].getNombre() + " / " + objetospoke.pokemon[i].getPtsAtq() + "<br>");
                }
            }
        }
        pw.println("</div>");
        pw.println("<h2>Top 5 de Pokemons con Mayor Salud</h2>");
        int contadorvida = 0;
        for (int i = 0; i < objetospoke.contadorPokemon; i++) {
            if(objetospoke.pokemon[i].getVida() > 100){
                contadorvida++;
                if(contadorvida >= 1 && contadorvida <= 5){
                    pw.println(contadorvida + ". " + objetospoke.pokemon[i].getNombre() + " / " + objetospoke.pokemon[i].getVida() + "<br>");
                }
            }
        }
        pw.println("<div>");
        pw.println("</div>");
        pw.println("</div>");
        pw.close();
        System.out.println("Reporte Generado");
        
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}
