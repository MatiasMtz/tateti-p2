/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tateti.obl.p2;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author matim
 */
public class TatetiOblP2 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        
        Sistema sistema = new Sistema();
        Interfaz interfaz = new Interfaz(sistema);
        boolean ejecutar = true;
        
        while (ejecutar) {
            ejecutar = interfaz.iniciar();
        }
        
        System.out.println("Programa finalizado. Hasta luego.");
    }
}
