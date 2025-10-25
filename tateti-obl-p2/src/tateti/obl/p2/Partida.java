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
    private Jugador jugadorActual;
    private Jugador jugadorGanador;
    private Tablero tablero;
    private boolean turnoBlanco;
    private boolean partidaActiva = true;
    
    // Constructor para nuevas partidas    
    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.jugadorActual = jugadorBlanco;
        this.tablero = new Tablero(true);
        this.turnoBlanco = true;
    }
    
    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro, String[] movimientos) {
        this.jugadorBlanco = jugadorBlanco;
        this.jugadorNegro = jugadorNegro;
        this.tablero = new Tablero(true);
        this.turnoBlanco = (movimientos.length %2 == 0); // movimientos pares - turno de blanco
        if (turnoBlanco) {
            this.jugadorActual = jugadorBlanco;
        } else {
            this.jugadorActual = jugadorNegro;
        }
    }
    
    public Jugador getJugadorBlanco() {
        return this.jugadorBlanco;
    }
    public Jugador getJugadorNegro() {
        return this.jugadorNegro;
    }
    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }
    
    public Tablero getTablero() {
        return this.tablero;
    }
    
    public boolean esTurnoBlanco() {
        return this.turnoBlanco;
    }
    
    public void cambiarTurno() {
        this.turnoBlanco = !turnoBlanco;
        if (turnoBlanco) {
            this.jugadorActual = jugadorBlanco;
        } else {
            this.jugadorActual = jugadorNegro;
        }
    }
    
    public void mostrarBordes(boolean mostrarBordes) {
        tablero.setMostrarBordes(mostrarBordes);
    }
    
    public boolean esJugadaValida(String movimiento) {
        return tablero.esJugadaValida(movimiento, turnoBlanco);
    }
    
    public void realizarMovimiento(String movimiento) {
        tablero.colocarFicha(movimiento, turnoBlanco);
    }
    
    public boolean hayGanador() {
        char ganador = tablero.hayGanador();
        if (ganador == 'O') {
            jugadorGanador = jugadorBlanco;
            return true;
        } else if (ganador == 'X') {
            jugadorGanador = jugadorNegro;
            return true;
        }
        return false;
    }
    
    public String getJugadaGanadora() {
        //return tablero.getJugadaGanadora();
        return "";
    }

    public Jugador getGanador() {
        char ganador = tablero.hayGanador();
        if (ganador == 'O') {
            return jugadorBlanco;
        }
        if (ganador == 'X') {
            return jugadorNegro;
        }
        return null;
    }

    public boolean esEmpate() {
        return tablero.esEmpate();
    }
    
    public void terminarPartida() {
        // Agregar limpieza y metodos necesarios cuando se termina la partida.
        this.partidaActiva = false;
        System.out.println("La partida ha finalizado.");
    }
    
    public boolean esPartidaActiva() {
        return partidaActiva;
    }
}
