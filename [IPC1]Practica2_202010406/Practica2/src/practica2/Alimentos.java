/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.Serializable;

/**
 *
 * @author dennis
 */
public class Alimentos implements Serializable{
    int id;
    String nombre;
    double vida;
    
    public Alimentos(int id, String nombre, double vida) {
        this.id = id;
        this.nombre = nombre;
        this.vida = vida;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }
}
