/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tateti.obl.p2;
import java.util.*;

/**
 *
 * @author matim
 */
public class Interfaz {
    // private Sistema sistema = new Sistema();
    Scanner scanner = new Scanner(System.in);
    
    public boolean iniciar() {
        menuPrincipal();  
        int seleccion = validarInput(5);
        
        switch (seleccion) {
            case 1:
                registrarJugador();
                break;
            case 2:
                comenzarPartidaComun();
                break;
            case 3:    
                continuarPartida();
                break;
            case 4:
                mostrarRanking();
                break;
            case 5:
                System.out.println("Saliendo del programa...");
                return false;
        }
        return true;
    
    }
    
    public void menuPrincipal() {
        String matias = "Matías Martínez: 282558";
        String franco = "Franco Trenche: 368637";
        
        System.out.println("Trabajo desarrollado por: " + matias + " - " + franco);
        System.out.println();
        System.out.println("********************");
        System.out.println("*****-=[MENÚ]=-*****");
        System.out.println("********************");
        System.out.println("1) Registrar un jugador");
        System.out.println("2) Comienzo de partida común");
        System.out.println("3) Continuación de partida");
        System.out.println("4) Mostrar ranking e invictos");
        System.out.println("********************");
        System.out.println("5) Terminar el programa");
        System.out.println("********************");
        System.out.println();
    }
    
    public void registrarJugador() {
        System.out.println("MENU PARA REGISTRAR JUGADORES");
    }
    
    public void comenzarPartidaComun() {
        System.out.println("COMENZAR PARTIDA...");
    }
    
    public void continuarPartida() {
        System.out.println("MENU PARA CONTINUAR PARTIDA");
    }
    
    public void mostrarRanking() {
        System.out.println("MENU PARA MOSTRAR RANKING");
    }
    
    /**
     * Pide y valida el input ingresado. Si no es correcto continua pidiendo hasta
     * ingresar uno que sea válido.
     * @param rango - (int) Rango máximo de selección (1|---|rango).
     * @return - (int) opción seleccionada.
     */
    public int validarInput(int rango) {
        int seleccion;
        while(true) {
            System.out.println("Seleccione una opción [1-" + rango + "]: ");
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= rango) break;
            } else {
                scanner.nextLine();
            }
            System.out.println("Opcion inválida. Intente nuevamente.");
        }
        return seleccion;
    }
}
