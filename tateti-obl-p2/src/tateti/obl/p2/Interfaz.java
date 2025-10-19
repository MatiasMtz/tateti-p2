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
                System.out.println("### Saliendo del programa...");
                return false;
        }
        return true;
    
    }
    
    public void menuPrincipal() {
        System.out.println("\n========================================================================");
        System.out.println("Trabajo desarrollado por: Matías Martínez 282558 y Franco Trenche 368637");
        System.out.println("\n******************************");
        System.out.println("------=[MENÚ PRINCIPAL]=------");
        System.out.println("******************************");
        System.out.println("1) Registrar un jugador");
        System.out.println("2) Comienzo de partida común");
        System.out.println("3) Continuación de partida");
        System.out.println("4) Mostrar ranking e invictos");
        System.out.println("******************************");
        System.out.println("5) Terminar el programa");
        System.out.println("******************************");
    }
    
    public void registrarJugador() {
        System.out.println("\n*********************************");
        System.out.println("------=[REGISTRAR JUGADOR]=------");
        System.out.println("*********************************");
        
        String nombre;
        while (true) {
            System.out.print("\n>>> Ingrese el nombre del jugador (o presione ENTER para volver al menú principal): ");
            nombre = scanner.nextLine().trim();
                       
            if (nombre.isEmpty()) {
                System.out.println("### Volviendo al menú principal...");
                return;
            }
            
            if (sistema.existeJugador(nombre)) {
                System.out.println("!!! El jugador [[ " + nombre + " ]] ya existe. Intente con otro nombre.");
                continue;
            }
            break;
        }
        
        int edad;
        while (true) {
            System.out.print("\n>>> Ingrese la edad del jugador: ");
            try {
                edad = Integer.parseInt(scanner.nextLine());
                if (edad <= 0) {
                    System.out.println("!!! La edad debe ser un número positivo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("!!! Ingrese un número válido para la edad.");
            }
        }
            
        boolean creado = sistema.crearJugador(nombre, edad);
        if (creado) {
            System.out.println("\n+++ El jugador [[ " + nombre + " ]] ha sido creado correctamente.");
            System.out.println("### Presione ENTER para volver al menú principal...");
            scanner.nextLine(); // Esperar la confirmacion del usuario para volver al menu principal.
            return;
        } else {
            System.out.println("!!! Error inesperado: no se pudo crear el jugador. Intente nuevamente.");
        }
    }
    
    public void comenzarPartidaComun() {
        ArrayList<Jugador> jugadoresSeleccionados = seleccionarJugador();
        if (jugadoresSeleccionados.size() != 2) {
            System.out.println("!!! Ha ocurrido un error en la selección de jugadores. Vuelva a intentarlo.");
            return;
        }
        
        Partida partida = new Partida(jugadoresSeleccionados.get(0), jugadoresSeleccionados.get(1));
        partida.getTablero().mostrarTablero(partida.isMostrarBordes());
        
        
        // crear instancia de Partida con ambos jugadores. 
        
        System.out.println("JUGADORES SELECCIONADOS: " + jugadoresSeleccionados.get(0).getNombre() + " - " + jugadoresSeleccionados.get(1).getNombre());
    }
    
    public void continuarPartida() {
        ArrayList<Jugador> jugadoresSeleccionados = seleccionarJugador();
        if (jugadoresSeleccionados.size() != 2) {
            System.out.println("!!! Ha ocurrido un error en la selección de jugadores. Vuelva a intentarlo.");
            return;
        }
        String[] movimientos;
        
        // pedir movimientos y transformarlos en arr        
        Partida partida = new Partida(jugadoresSeleccionados.get(0), jugadoresSeleccionados.get(1), movimientos);
    }
    
    public void mostrarRanking() {

        int modo = 1;
        
        while (true) {
            ArrayList<Jugador> jugadores = sistema.getRankingOInvictos(modo);
            System.out.println();
            
            if (modo == 1) {
                System.out.println("************************************");
                System.out.println("------=[RANKING DE VICTORIAS]=------");
                System.out.println("************************************");
                System.out.println("POSICIÓN> NOMBRE DE JUGADOR - VICTORIAS");
            } else {
                System.out.println("**********************************");
                System.out.println("------=[JUGADORES INVICTOS]=------");
                System.out.println("**********************************");
                System.out.println("POSICIÓN> NOMBRE DE JUGADOR");
            }
            
            if (jugadores.isEmpty()) {
                System.out.println("!!! No hay jugadores registrados en el sistema.");
            } else {
                int i = 1;
                for (Jugador j : jugadores) {
                    String tag;
                    int partidas;
                    
                    if (modo == 1) {
                        tag = " partidas ganadas.";
                        partidas = j.getPartidasGanadas();
                    } else {
                        tag = " partidas jugadas.";
                        partidas = j.getPartidasJugadas();
                    }
                
                    System.out.println(i + "> " + j.getNombre() + " - " + partidas + tag);
                    i++;
                }
            }
            
            System.out.println("\n****************************************");
            System.out.println("1) Ver ranking de jugadores por victorias");
            System.out.println("2) Ver jugadores invictos");
            System.out.println("*****************************************");
            System.out.println("3) Volver al menú principal");
            System.out.println("*****************************************");
            
            int seleccion = leerInput(3);
            scanner.nextLine();

            if (seleccion == 3) {
                System.out.println(">>> Volviendo al menú principal...");
                return;
            }
            modo = seleccion;
            System.out.println();
        }
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
            System.out.print("\n>>> Seleccione una opción [1-" + rango + "]: ");
            if (scanner.hasNextInt()) {
                seleccion = scanner.nextInt();
                if (seleccion >= 1 && seleccion <= rango) break;
            } else {
                scanner.nextLine();
            }
            System.out.println("!!! Opcion inválida. Intente nuevamente.");
        }
        return seleccion;
    }
    
    private void mostrarJugadores(ArrayList<Jugador> jugadores) {
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + "> " + jugadores.get(i).getNombre());
        }
    }
    
    private void tituloSeleccionJugador() {
        System.out.println("\n**************************************");
        System.out.println("------=[SELECCIÓN DE JUGADORES]=------");
        System.out.println("**************************************");
    }
    
    private ArrayList<Jugador> seleccionarJugador() {
        tituloSeleccionJugador();
        
        ArrayList<Jugador> jugadores = sistema.ordenarJugadores();
        ArrayList<Jugador> jugadoresSeleccionados = new ArrayList<>();
        
        if (jugadores.size() < 2) {
            System.out.println("!!! Deben crearse al menos 2 jugadores para jugar.");
            System.out.println(">>> Presione ENTER para volver al menú principal...");
            scanner.nextLine();
            return jugadoresSeleccionados;
        }
        
        mostrarJugadores(jugadores);
        System.out.println();
        System.out.println(">>> Seleccione al jugador BLANCO (○)");
        int seleccionBlanco = leerInput(jugadores.size());
        scanner.nextLine();
        jugadoresSeleccionados.add(jugadores.get(seleccionBlanco - 1)); 
        
        ArrayList<Jugador> jugadoresRestantes = new ArrayList<>(jugadores);
        jugadoresRestantes.remove(seleccionBlanco - 1);

        tituloSeleccionJugador();
        mostrarJugadores(jugadoresRestantes);
        System.out.println();
        System.out.println(">>> Seleccione al jugador NEGRO (●)");
        int seleccionNegro = leerInput(jugadoresRestantes.size());
        scanner.nextLine();
        jugadoresSeleccionados.add(jugadores.get(seleccionNegro - 1)); 
        System.out.println();

        return jugadoresSeleccionados;
    }
}
