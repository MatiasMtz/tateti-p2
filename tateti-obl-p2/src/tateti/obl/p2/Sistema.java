/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tateti.obl.p2;
import java.util.ArrayList;

/**
 *
 * @author matim
 */
public class Sistema {
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    
    public boolean crearJugador(String nombre, int edad) {
        for (Jugador j : jugadores) {
            if (j.getNombre().equals(nombre)) {
                return false;
            }
        }
        Jugador jugador = new Jugador(nombre, edad);
        jugadores.add(jugador);
        return true;
    }
     
    public ArrayList<Jugador> getRankingOInvictos(int modo) {
        ArrayList<Jugador> copiaJugadores = new ArrayList<>(jugadores);
        switch (modo) {
            case 1:
                // Ordena a los jugadores por partidas ganadas descendente.
                for (int i = 0; i < copiaJugadores.size(); i++) {
                    for (int j = i + 1; j < copiaJugadores.size(); j++) {
                        if (copiaJugadores.get(i).getPartidasGanadas() < copiaJugadores.get(j).getPartidasGanadas()) {
                            Jugador aux = copiaJugadores.get(i);
                            copiaJugadores.set(i, copiaJugadores.get(j));
                            copiaJugadores.set(j, aux);
                        }
                    }
                } 
                break;
            case 2:
                // Elimina jugadores que perdieron alguna partida y ordena al resto alfabeticamente.
                for (int i = 0; i < copiaJugadores.size(); i++) {
                    if (copiaJugadores.get(i).getPartidasPerdidas() > 0) {
                        copiaJugadores.remove(i);
                    }
                }
                copiaJugadores = ordenarJugadores(copiaJugadores);
                break;
        }        
        return copiaJugadores;
    }
    
     public ArrayList<Jugador> ordenarJugadores(ArrayList<Jugador> copiaJugadores) {
        // ordenar lista de jugadores alfabeticamente
        for (int i = 0; i < copiaJugadores.size(); i++) {
            for (int j = i + 1; j < copiaJugadores.size(); j++) {
                if (copiaJugadores.get(i).getNombre().compareToIgnoreCase(copiaJugadores.get(j).getNombre()) > 0) {
                    Jugador aux = copiaJugadores.get(i);
                    copiaJugadores.set(i, copiaJugadores.get(j));
                    copiaJugadores.set(j, aux);
                }
            }
        }
        return copiaJugadores;
    }
}
