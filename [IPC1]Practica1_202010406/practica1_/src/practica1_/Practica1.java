package practica1_;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Practica1 {
  static int[][] arrMsg;
  
  static int[][] arrClaveA;
  
  static int[][] arrClaveB;
  
  static int[][] C;
  
  static int[][] adjun;
  
  static float[][] inv;
  
  static int[][] multi;
  
  static int[][] resta;
  
  static int[][] desencriptado;
  
  public static int columnas;
  
  public static int fil_M;
  
  public static int col_M;
  
  public static int fil_A;
  
  public static int col_A;
  
  public static int deter;
  
  public static String mensaje;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("============= MENU =============");
    System.out.println("| 1. Encriptar                |");
    System.out.println("| 2. Desencriptar             |");
    System.out.println("| 3. Generar Reportes         |");
    System.out.println("| 4. Salir                    |");
    System.out.println("===============================");
    System.out.println("Ingrese una opcion: ");
    int opcion = Integer.parseInt(br.readLine());
    while (opcion < 1 || opcion > 4) {
      System.out.println("Ingrese una opcion valida");
      opcion = Integer.parseInt(br.readLine());
    } 
    switch (opcion) {
      case 1:
        while (opcion != 5) {
          System.out.println("======== MENU ENCRIPTAR ========");
          System.out.println("| 1. Ingreso Mensaje          |");
          System.out.println("| 2. Ingreso Matriz Clave A   |");
          System.out.println("| 3. Ingreso Matriz Clave B   |");
          System.out.println("| 4. Encriptar                |");
          System.out.println("| 5. Regresar                  |");
          System.out.println("===============================");
          encriptar();
        } 
        break;
      case 2:
        desencriptar();
        break;
      case 3:
        reportes();
        break;
      case 4:
        System.exit(0);
        break;
    } 
  }
  
  public static void encriptar() {
    try {
      int filas, i;
      char[] posiciones;
      int number[], j, index, k;
      String pathFile;
      FileReader fr;
      BufferedReader Bfread;
      Scanner scan;
      int fila;
      String lineaEntrada, pathFile1;
      FileReader fre;
      BufferedReader Bfreader;
      Scanner scan1;
      int colB;
      String lineaEntrada1;
      int f, sum, m;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Ingrese la opcion");
      int opcionEnc = Integer.parseInt(br.readLine());
      while (opcionEnc < 1 || opcionEnc > 5) {
        System.out.println("Ingrese una opcion correcta");
        opcionEnc = Integer.parseInt(br.readLine());
      } 
      switch (opcionEnc) {
        case 1:
          System.out.println("Escriba el mensaje");
          mensaje = br.readLine();
          filas = 3;
          columnas = mensaje.length();
          for (i = 0; i < columnas; i++) {
            if (columnas % 3 != 0) {
              columnas++;
              mensaje += " ";
            } 
          } 
          columnas /= 3;
          mensaje = mensaje.toLowerCase();
          posiciones = new char[mensaje.length()];
          number = new int[mensaje.length()];
          for (j = 0; j < mensaje.length(); j++) {
            posiciones[j] = mensaje.charAt(j);
            char primerC = 'a';
            number[j] = posiciones[j] - primerC;
            if (posiciones[j] == 'ñ')
              number[j] = 14; 
            if (number[j] >= 14)
              number[j] = number[j] + 1; 
            if (posiciones[j] < 'a' || posiciones[j] > 'z')
              number[j] = 27; 
          } 
          System.out.println();
          index = 0;
          arrMsg = new int[filas][columnas];
          for (k = 0; k < columnas; k++) {
            for (int n = 0; n < 3; n++) {
              arrMsg[n][k] = number[index];
              index++;
            } 
          } 
          fil_M = arrMsg.length;
          col_M = (arrMsg[0]).length;
          break;
        case 2:
          System.out.println("Ingrese la direccion del archivo: ");
          pathFile = br.readLine();
          fr = new FileReader(pathFile);
          Bfread = new BufferedReader(fr);
          scan = new Scanner(Bfread);
          fila = 0;
          lineaEntrada = "";
          arrClaveA = new int[3][3];
          try {
            while (scan.hasNextLine()) {
              lineaEntrada = scan.nextLine();
              String[] arrEntrada = lineaEntrada.split(",");
              for (int n = 0; n < 3; n++)
                arrClaveA[fila][n] = Integer.parseInt(arrEntrada[n]); 
              fila++;
            } 
          } catch (Exception e) {
            System.out.println(e);
          } 
          fil_A = arrClaveA.length;
          col_A = (arrClaveA[0]).length;
          break;
        case 3:
          System.out.println("Ingrese la direccion del archivo: ");
          pathFile1 = br.readLine();
          fre = new FileReader(pathFile1);
          Bfreader = new BufferedReader(fre);
          scan1 = new Scanner(Bfreader);
          colB = 0;
          lineaEntrada1 = "";
          colB = columnas;
          lineaEntrada1 = "";
          f = 0;
          arrClaveB = new int[3][colB];
          try {
            while (scan1.hasNextLine()) {
              lineaEntrada1 = scan1.nextLine();
              String[] a = lineaEntrada1.split(",");
              for (int n = 0; n < 3; n++)
                arrClaveB[n][f] = Integer.parseInt(a[n]); 
              f++;
            } 
          } catch (Exception e) {
            System.out.println(e);
          } 
          break;
        case 4:
          System.out.println("El mensaje Encriptado es: ");
          sum = 0;
          multi = new int[fil_A][col_M];
          for (m = 0; m < 3; m++) {
            for (int n = 0; n < col_M; n++) {
              for (int i1 = 0; i1 < 3; i1++)
                sum += arrClaveA[m][i1] * arrMsg[i1][n]; 
              multi[m][n] = sum;
              sum = 0;
            } 
          } 
          C = new int[multi.length][(multi[0]).length];
          for (m = 0; m < multi.length; m++) {
            for (int n = 0; n < (multi[0]).length; n++) {
              C[m][n] = multi[m][n] + arrClaveB[m][n];
              System.out.print(C[m][n] + " ");
            } 
          } 
          System.out.println("\n");
          break;
        case 5:
          main(null);
          break;
      } 
    } catch (IOException ex) {
      Logger.getLogger(Practica1.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
  
  public static void desencriptar() throws IOException {
    //BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("El mensaje descifrado es: ");
    resta = new int[C.length][(C[0]).length];
    for (int i = 0; i < C.length; i++) {
      for (int i1 = 0; i1 < (C[0]).length; i1++)
        resta[i][i1] = C[i][i1] - arrClaveB[i][i1]; 
    } 
    int n = 3;
    adjun = new int[n][n];
    inv = new float[n][n];
    deter = determinante(arrClaveA, n);
    determinante(arrClaveA, n);
    adjunta(arrClaveA, adjun, n);
    inversa(arrClaveA, inv, n);
    float sum = 1.0F;
    desencriptado = new int[arrMsg.length][(arrMsg[0]).length];
    for (int j = 0; j < resta.length; j++) {
      for (int i1 = 0; i1 < (resta[0]).length; i1++) {
        for (int i2 = 0; i2 < 3; i2++)
          sum += inv[j][i2] * resta[i2][i1]; 
        desencriptado[j][i1] = (int)sum;
        sum = 0.0F;
      } 
    } 
    int index = 0;
    int[] numbers = new int[desencriptado.length * (desencriptado[0]).length];
    for (int k = 0; k < (desencriptado[0]).length; k++) {
      for (int i1 = 0; i1 < desencriptado.length; i1++) {
        numbers[index] = desencriptado[i1][k];
        index = (index + 1) % numbers.length;
      } 
    } 
    char[] pos = new char[numbers.length];
    int m;
    for (m = 0; m < numbers.length; m++) {
      char pC = 'a';
      pos[m] = (char)((char)numbers[m] + pC);
      if (numbers[m] == 27) {
        pos[m] = ' ';
      } else if (numbers[m] == 14) {
        pos[m] = 'ñ';
      } else if (numbers[m] >= 14) {
        pos[m] = (char)(pos[m] - 1);
      } 
    } 
    for (m = 0; m < pos.length; m++)
      System.out.print(pos[m]); 
    System.out.println();
    main(null);
  }
  
  static void obtenerCof(int[][] A, int[][] temporal, int q, int p, int n) {
    int i = 0, j = 0;
    for (int fila = 0; fila < n; fila++) {
      for (int col = 0; col < n; col++) {
        if (fila != p && col != q) {
          temporal[i][j++] = A[fila][col];
          if (j == n - 1) {
            j = 0;
            i++;
          } 
        } 
      } 
    } 
  }
  
  public static int determinante(int[][] A, int N) {
    int d = 0;
    if (N == 1) {
      d = A[0][0];
    } else if (N == 2) {
      d = A[0][0] * A[1][1] - A[1][0] * A[0][1];
    } else {
      int[][] matrizNueva = new int[N - 1][];
      d = 0;
      for (int saltarCol = 0; saltarCol < N; saltarCol++) {
        int potenciaNegativaPositiva;
        for (int k = 0; k < N - 1; k++)
          matrizNueva[k] = new int[N - 1]; 
        for (int i = 1; i < N; i++) {
          int saltarFila = 0;
          for (int j = 0; j < N; j++) {
            if (j != saltarCol) {
              matrizNueva[i - 1][saltarFila] = A[i][j];
              saltarFila++;
            } 
          } 
        } 
        if (saltarCol % 2 == 0) {
          potenciaNegativaPositiva = 1;
        } else {
          potenciaNegativaPositiva = -1;
        } 
        d += potenciaNegativaPositiva * A[0][saltarCol] * determinante(matrizNueva, N - 1);
      } 
    } 
    return d;
  }
  
  static void adjunta(int[][] A, int[][] adj, int N) {
    if (N == 1) {
      adj[0][0] = 1;
      return;
    } 
    int signo = 1;
    int[][] temp = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        obtenerCof(A, temp, i, j, N);
        signo = ((i + j) % 2 == 0) ? 1 : -1;
        adj[j][i] = signo * determinante(temp, N - 1);
      } 
    } 
  }
  
  static boolean inversa(int[][] A, float[][] inversa, int N) {
    int det = determinante(A, N);
    if (det == 0) {
      System.out.println("Matriz singular, no se puede encontrar su inversa");
      return false;
    } 
    int[][] adj = new int[N][N];
    adjunta(A, adj, N);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++)
        inversa[j][i] = adj[i][j] / det; 
    } 
    return true;
  }
  
  public static void reportes() throws IOException {
    System.out.println("CREAR ARCHIVO");
    File file = new File("Reporte.html");
    //FileWriter fw = null;
    if (!file.exists())
      file.createNewFile(); 
    try (PrintWriter pw = new PrintWriter(file)) {
      pw.println("<style>body{background-color: skyblue; font-family: Arial;}h1{text-align: center}</style>");
      pw.println("<h1>===========REPORTES===========</h1>");
      pw.println("<h2>Reporte Encriptar</h2>");
      pw.println("<h3>Texto a Encriptar</h3>");
      pw.println(mensaje);
      pw.println("<h3>Matriz del Mensaje</h3>");
      int i;
      for (i = 0; i < fil_M; i++) {
        for (int j = 0; j < col_M; j++)
          pw.println(arrMsg[i][j]); 
      } 
      pw.println("<h3>Matriz del Clave A</h3>");
      for (i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++)
          pw.println(arrClaveA[i][j]); 
      } 
      pw.println("<h3>Matriz del Clave B</h3>");
      for (i = 0; i < 3; i++) {
        for (int j = 0; j < columnas; j++)
          pw.println(arrClaveB[i][j]); 
      } 
      pw.println("<h4>Se multiplico A*M y nos dio la siguinte matriz: </h4>");
      for (i = 0; i < fil_A; i++) {
        for (int j = 0; j < col_M; j++)
          pw.println(multi[i][j]); 
      } 
      pw.println("<h4>Y a la multiplicacion de estas matrices se le sumo B y nos dio la matriz: </h4>");
      for (i = 0; i < multi.length; i++) {
        for (int j = 0; j < (multi[0]).length; j++)
          pw.println(C[i][j]); 
      } 
      pw.println("<h2>Reporte Desencriptar</h2>");
      pw.println("<h3>Matriz Encriptada</h3>");
      for (i = 0; i < C.length; i++) {
        for (int j = 0; j < (C[0]).length; j++)
          pw.println(C[i][j]); 
      } 
      pw.println("<h5>Obtenemos la matriz adjunta eliminando filas y columnas y multiplicando los que quedan dentro del rango</h5>");
      pw.println("<h3>Y esto nos da la Matriz adjunta</h3>");
      for (i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++)
          pw.println(adjun[i][j]); 
      } 
      pw.println("<h5>Dividimos los valores de la matriz adjunta entre el determinante: " + deter);
      pw.println("<h3>Y nos da la Matriz inversa de A</h3>");
      for (i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++)
          pw.println(inv[i][j]); 
      } 
      pw.println("<h5>Multiplicamos la matriz inversa por la resta de la matriz encripta menos la matriz B");
      pw.println("<h3>Resta de Matriz C(encriptada) - B</h3>");
      for (i = 0; i < C.length; i++) {
        for (int j = 0; j < (C[0]).length; j++)
          pw.println(resta[i][j]); 
      } 
      pw.println("<h3>Multiplicacion de resta de matrices por inversa</h3>");
      for (i = 0; i < resta.length; i++) {
        for (int j = 0; j < (resta[0]).length; j++)
          pw.println(desencriptado[i][j]); 
      } 
      LocalDateTime ahora = LocalDateTime.now();
      int anio = ahora.getYear();
      int mes = ahora.getMonthValue();
      int dia = ahora.getDayOfMonth();
      int hora = ahora.getHour();
      int minutos = ahora.getMinute();
      int segundos = ahora.getSecond();
      pw.println("<br>");
      pw.println("<br>");
      pw.println("<br>");
      pw.println("<br>");
      pw.println("Archivo generado en: ");
      pw.println(dia + "/" + mes + "/" + anio + " a las " + hora + ":" + minutos + ":" + segundos);
      pw.close();
    }
    System.out.println("Reporte Generado");
    Desktop desktop = Desktop.getDesktop();
    desktop.open(file);
    main(null);
  }
}