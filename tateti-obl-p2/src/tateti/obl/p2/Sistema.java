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
    
    public ArrayList<Jugador> getJugadores() {
        // ordenar lista de jugadores alfabeticamente
    }
    
    public ArrayList<Jugador> getRankingEInvictos(int modo) {
        // mediante la var modo cambia el funcionamiento del metodo. Tiene que devolver:
        // 1. el ranking de los jugadores ordenados decreciente por cantidad de partidas ganadas
        // 2. mostrar los jugadores invictos, que nunca jugaron y, o, nunca perdieron, ordenados alfabéticamente (usar getJugadores).
        ArrayList<Jugador> rankingeInvictos = new ArrayList<>(jugadores); // copia del arr principal para que otras clases no accedan
    }
}
