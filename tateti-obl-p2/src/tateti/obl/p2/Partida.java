/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tateti.obl.p2;

/**
 *
 * @author matim
 */
public class Partida {
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private Tablero tablero;
    private boolean turnoBlanco;
    private boolean mostrarBordes = true;
    private boolean partidaActiva = true;
    
    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.tablero = new Tablero();
        this.turnoBlanco = true;
    }
    
    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro, String[] movimientos) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.tablero = new Tablero(/* Params para pasar movimientos y continuar partida*/);
        this.turnoBlanco = (movimientos.length %2 == 0); // movimientos pares - turno de blanco
    }
    
    public Jugador getJugadorBlanco() {
        return this.jugadorBlanco;
    }
    public Jugador getJugadorNegro() {
        return this.jugadorNegro;
    }
    public Tablero getTablero() {
        return this.tablero;
    }
    public boolean isMostrarBordes() {
        return this.mostrarBordes;
    }
    
    
    public void setTurno() {
        this.turnoBlanco = !turnoBlanco;
    }
    
    public void switchBordes() {
        /* Crear logica para pasarle al tablero que no muestre los bordes. */
        this.mostrarBordes = !mostrarBordes;
    }
    
    public void terminarPartida() {
        /* Agregar limpieza y metodos necesarios cuando se termina la partida. */
        this.partidaActiva = false;
    }
}
