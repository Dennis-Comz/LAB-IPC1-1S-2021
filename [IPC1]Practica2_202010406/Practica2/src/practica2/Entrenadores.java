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
public class Entrenadores implements Serializable{
    int id;
    String nombre;
    PokeBall pokeball;
    
    public Entrenadores(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PokeBall getPokeball() {
        return pokeball;
    }

    public void setPokeball(PokeBall pokeball) {
        this.pokeball = pokeball;
    }
}
