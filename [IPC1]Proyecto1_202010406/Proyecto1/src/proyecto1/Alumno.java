package proyecto1;

import java.io.Serializable;

public class Alumno implements Serializable{
    int codigo;
    String nombre, apellido, correo, genero;
    double[] notas = new double[3];
    
    public Alumno(int codigo, String nombre, String apellido, String correo, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double[] getNota() {
        return notas;
    }

    public void setNota(double nota, int pos) {
        notas[pos] = nota;
    }
    
    
}
