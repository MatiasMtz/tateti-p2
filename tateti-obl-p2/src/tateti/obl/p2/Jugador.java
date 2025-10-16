/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tateti.obl.p2;

/**
 *
 * @author matim
 */
public class Jugador {
    private String nombre;
    private int edad;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasEmpatadas;
    
    public Jugador(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
        this.partidasJugadas = 0;
        this.partidasGanadas = 0;
        this.partidasEmpatadas = 0;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return this.edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public int getPartidasJugadas() {
        return this.partidasJugadas;
    }
    public void sumarPartidaJugada() {
        this.partidasJugadas++;
    }
    
    public int getPartidasGanadas() {
        return this.partidasGanadas;
    }
    public void sumarPartidaGanada() {
        this.partidasGanadas++;
    }
    
    public int getPartidasEmpatadas() {
        return this.partidasEmpatadas;
    }
    public void sumarPartidaEmpatada() {
        this.partidasEmpatadas++;
    }
    
    public int getPartidasPerdidas() {
        return this.partidasJugadas - (this.partidasGanadas + this.partidasEmpatadas);
    }
    
    public void registrarPartida(String resultado) {
        sumarPartidaJugada();
        if (resultado.equals("victoria")) {
            sumarPartidaGanada();
        } else if (resultado.equals("empate")) {
            sumarPartidaEmpatada();
        }
    }
    

}
