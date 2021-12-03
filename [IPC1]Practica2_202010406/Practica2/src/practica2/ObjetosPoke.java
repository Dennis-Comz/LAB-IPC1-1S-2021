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
public class ObjetosPoke implements Serializable{
    
    int contadorPokemon;
    int contadorPokeBall;
    int contadorAlimentos;
    int contadorEntrenadores;
    int contadorGimnasios;
    public Pokemon [] pokemon = new Pokemon[150];
    public Entrenadores [] entrenadores = new Entrenadores[25];
    public PokeBall [] pokeball = new PokeBall[150];
    public Gimnasios [] gimnasios = new Gimnasios[25];
    public Alimentos [] alimentos = new Alimentos[15];
    
    public ObjetosPoke(){
        contadorPokemon = 0;
        contadorPokeBall = 0;
        contadorAlimentos = 0;
        contadorEntrenadores = 0;
        contadorGimnasios = 0;
    }
    
    public void guardarPokemon(int id, String tipo, String nombre, double vida, double ptsAtq, boolean capturado, boolean estado){
        Pokemon nuevoPokemon = new Pokemon(id, tipo, nombre, vida, ptsAtq, capturado, estado);
        pokemon[contadorPokemon] = nuevoPokemon;
        contadorPokemon++;
    }
    
    public void guardarEntrenadores(int id, String nombre){
        Entrenadores nuevoEntrenador = new Entrenadores(id, nombre);
        entrenadores[contadorEntrenadores] = nuevoEntrenador;
        contadorEntrenadores++;
    }
    
    public void guardarPokeBall(int id, String tipo){
        PokeBall nuevaBall = new PokeBall(id, tipo);
        pokeball[contadorPokeBall] = nuevaBall;
        contadorPokeBall++;
    }
    
    public void guardarGimnasios(int id, String lugar){
        Gimnasios nuevoGym = new Gimnasios(id, lugar);
        gimnasios[contadorGimnasios] = nuevoGym;
        contadorGimnasios++;         
    }
    
    public void guardarAlimentos(int id, String nombre, Double vida){
        Alimentos nuevoAlimento = new Alimentos(id, nombre, vida);
        alimentos[contadorAlimentos] = nuevoAlimento;
        contadorAlimentos++;
    }
    
    public void asignarPokemon(int idBall, int idPoke){
        Pokemon pokemon_asignable = getPokemonAs(idPoke);
        PokeBall pokeball_asignable = getPokeball(idBall);
        pokeball_asignable.setPokemon(pokemon_asignable);
    }
    
    public Pokemon getPokemonAs(int id){
        for (int i = 0; i < pokemon.length; i++) {
            if(id == pokemon[i].getId()){
                pokemon[i].setCapturado(true);
                return pokemon[i];
            }
        }
        return null;
    }
    
//    Si puede usarse varias veces porque no le cambia valor a nada
    public PokeBall getPokeball(int id){
        for (int i = 0; i < pokeball.length; i++) {
            if(id == pokeball[i].getId()){
                return pokeball[i];
            }
        }
        return null;
    }
    
    public void asignarPokeBall(int idEntrenador, int idBall){
        Entrenadores entrenador_asignable = getEntrenador(idEntrenador);
        PokeBall pokeball_asignable = getPokeball(idBall);
        entrenador_asignable.setPokeball(pokeball_asignable);
    }
    
    public Entrenadores getEntrenador(int id){
        for (int i = 0; i < entrenadores.length; i++) {
            if (id == entrenadores[i].getId()) {
                return entrenadores[i];
            }
        }
        return null;
    }
    
    public void asignarComida(int idAct, int idPoke){
        Alimentos alimento_asignable = getAlimento(idAct);
        Pokemon pokemon_asignable = getPokemonAl(idPoke);
        pokemon_asignable.setAlimento(alimento_asignable);
    }
    
    public Alimentos getAlimento(int id){
        for (int i = 0; i < alimentos.length; i++) {
            if(id == alimentos[i].getId()){
                return alimentos[i];
            }
        }
        return null;
    }
    
    public Pokemon getPokemonAl(int id){
        for (int i = 0; i < pokemon.length; i++) {
            if(id == pokemon[i].getId()){
                if(pokemon[i].getAlimento() != null){
                    double nuevaVida = pokemon[i].getVida() + pokemon[i].getAlimento().getVida();
                    pokemon[i].setVida(nuevaVida); 
                }
                if(pokemon[i].isEstado() == false && pokemon[i].getAlimento() != null){
                    pokemon[i].setEstado(true);
                    double nuevaVida = pokemon[i].getVida() + pokemon[i].getAlimento().getVida();
                    pokemon[i].setVida(nuevaVida); 
                }
                return pokemon[i];
            }
        }
        return null;
    }
    
    public void asignarPelea(int idGym, int idp1, int idp2){
        Gimnasios gimnasio_asignable = getLugar(idGym);
        Pokemon pokemon_asignable1 = getPokemonP1(idp1);
        Pokemon pokemon_asignable2 = getPokemonP1(idp2);
        gimnasio_asignable.setPokemon(pokemon_asignable1, pokemon_asignable2);
    }
    
    public Gimnasios getLugar(int id){
        for (int i = 0; i < gimnasios.length; i++) {
            if(id == gimnasios[i].getId()){
                return gimnasios[i];
            }
        }
        return null;
    }
    
    public Pokemon getPokemonP1(int idp1){
        for (int i = 0; i < pokemon.length; i++) {
            if(idp1 == pokemon[i].getId()){
                return pokemon[i];
            }
        }
        return null;
    }
    
    public Pokemon getPokemonP2(int idp2){
        for (int i = 0; i < pokemon.length; i++) {
            if(idp2 == pokemon[i].getId()){
                return pokemon[i];
            }
        }
        return null;
    }
}
