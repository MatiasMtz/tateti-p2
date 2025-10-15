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
    private Sistema sistema;
    Scanner scanner = new Scanner(System.in);
    
    public Interfaz(Sistema sistema) {
        this.sistema = sistema;
    }
    
    public boolean iniciar() {
        menuPrincipal();  
        int seleccion = leerInput(5);
        scanner.nextLine(); // limpiar buffer de scanner porque mantiene \n de la lectura.
        
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
        System.out.println("*********************************");
        System.out.println("*****-=[REGISTRAR JUGADOR]=-*****");
        System.out.println("*********************************");
        boolean creado = false;
        
        while (!creado) {
            System.out.println("Ingrese el nombre del jugador (o presione ENTER para volver al menú principal): ");
            String nombre = scanner.nextLine().trim();
            
            if (nombre.isEmpty()) {
                System.out.println("Volviendo al menú principal...");
                return;
            }
            
            System.out.println("Ingrese la edad del jugador: ");
            int edad;
            try {
                edad = Integer.parseInt(scanner.nextLine());
                if (edad <= 0) {
                    System.out.println("La edad debe ser un número positivo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido para la edad.");
                continue;
            }
            
            creado = sistema.crearJugador(nombre, edad);
            if (creado) {
                System.out.println("El jugador " + nombre + " ha sido creado correctamente.");
                System.out.println("Presione ENTER para volver al menú principal...");
                scanner.nextLine(); // Esperar la confirmacion del usuario para volver al menu principal.
            } else {
                System.out.println("El jugador " + nombre + " ya existe. Intente con otro nombre.");
            }
        }
    }
    
    public void comenzarPartidaComun() {
        System.out.println("COMENZAR PARTIDA...");
        // mostrar lista numerada de jugadores
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
    private int leerInput(int rango) {
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
