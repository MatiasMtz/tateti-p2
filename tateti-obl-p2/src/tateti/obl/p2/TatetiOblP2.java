/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tateti.obl.p2;

/**
 *
 * @author matim
 */
public class TatetiOblP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        
        Interfaz interfaz = new Interfaz();
        boolean ejecutar = true;
        
        while (ejecutar) {
            ejecutar = interfaz.iniciar();
        }
        
        System.out.println("Programa finalizado. Hasta luego.");
    }
}
