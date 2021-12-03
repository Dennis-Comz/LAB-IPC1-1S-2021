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
public class PokeBall implements Serializable{
    
    int id;
    String tipo;
    Pokemon pokemon;
    
    public PokeBall(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
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
    
    public Pokemon getPokemon(){
        return pokemon;
    }
    
    public void setPokemon(Pokemon pokemon){
        this.pokemon = pokemon;
    }
}
