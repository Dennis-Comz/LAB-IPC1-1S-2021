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
public class Pokemon implements Serializable{
    
    int id;
    String tipo;
    String nombre;
    double vida;
    double ptsAtq;
    boolean capturado;
    boolean estado;
    Alimentos alimento;

    public Pokemon(int id, String tipo, String nombre, double vida, double ptsAtq, boolean capturado, boolean estado) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.vida = vida;
        this.ptsAtq = ptsAtq;
        this.capturado = capturado;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getPtsAtq() {
        return ptsAtq;
    }

    public void setPtsAtq(double ptsAtq) {
        this.ptsAtq = ptsAtq;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public Alimentos getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimentos alimento) {
        this.alimento = alimento;
    }
    
}
