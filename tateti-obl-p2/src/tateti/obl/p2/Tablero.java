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
public class Tablero {
    private int filas = 3;
    private int columnas = 6;
    private Celda[][] celdas;
    
    public Tablero() {
        this.celdas = new Celda[filas][columnas];
        inicializarCeldas();
    }
    
    public Tablero(boolean mostrarBordes, String movimientos){
        this.celdas = new Celda[filas][columnas];
        inicializarCeldas();
        aplicarMovimientos(movimientos);
    }       
    
    // Inicializar el tablero
    private void inicializarCeldas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }
    private void aplicarMovimientos(String[] movimientos) {
        // chequear si la pos esta vacia
    }
    
    public void mostrarTablero(boolean mostrarBordes) {
        // 
        if (mostrarBordes) {
            // print guias
        }
    }
    
}
