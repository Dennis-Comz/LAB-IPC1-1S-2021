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
public class Gimnasios implements Serializable{
    int id;
    String lugar;
    Pokemon pokemon;

    public Gimnasios(int id, String lugar) {
        this.id = id;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon = pokemon1;
        this.pokemon = pokemon2;
    }
}
