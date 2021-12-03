package proyecto1;

import java.io.Serializable;
public class Curso implements Serializable{
    int codigo, creditos, idProf;
    String nombre;
    Alumno[] alumnos = new Alumno[50];

    public Curso(int codigo,  String nombre, int creditos, int idProf) {
        this.codigo = codigo;
        this.creditos = creditos;
        this.nombre = nombre;
        this.idProf = idProf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }  

    public Alumno[] getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumno alumno, int pos) {
        alumnos[pos] = alumno;
    }
    
}
