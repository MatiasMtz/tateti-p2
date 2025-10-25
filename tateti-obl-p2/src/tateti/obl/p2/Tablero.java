package tateti.obl.p2;

public class Tablero {
    private Celda[][] celdas = new Celda[3][6];
    private boolean mostrarBordes;
    boolean[][] celdasGanadoras = new boolean[3][6];

    public Tablero(boolean mostrarBordes) {
        this.mostrarBordes = mostrarBordes;
        inicializarCeldas();
        
    }

    public Tablero(boolean mostrarBordes, String movimientos) {
        this.mostrarBordes = mostrarBordes;
        inicializarCeldas();
        aplicarMovimientos(movimientos);
    }

    // ============================
    // Inicialización
    // ============================
    private void inicializarCeldas() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    private void aplicarMovimientos(String movimientos) {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos para realizar");
        }
        // TODO: implementar luego
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    // ============================
    // Mostrar tablero
    // ============================
    public void setMostrarBordes(boolean mostrarBordes) {
        this.mostrarBordes = mostrarBordes;
    }

    public boolean esMostrarBordes() {
        return mostrarBordes;
    }

    public void mostrarTablero() {
        if (mostrarBordes) mostrarConBordes();
        else mostrarSinBordes();
    }
    
    private void mostrarConBordes() {
        System.out.print("\n    ");
        for (int j = 0; j < 6; j++) System.out.printf("%-3d", j + 1);
        System.out.println("\n   +--+--+--+--+--+--+");

        for (int i = 0; i < 3; i++) {
            char fila = (char) ('A' + i);
            System.out.print("   |");
            for (int j = 0; j < 6; j++) System.out.print(getCeldaParte(celdas[i][j], 1) + "|");
            System.out.println();

            System.out.print(" " + fila + " |");
            for (int j = 0; j < 6; j++) System.out.print(getCeldaParte(celdas[i][j], 2) + "|");
            System.out.println();

            System.out.print("   |");
            for (int j = 0; j < 6; j++) System.out.print(getCeldaParte(celdas[i][j], 3) + "|");
            System.out.println("\n   +--+--+--+--+--+--+");
        }
    }

    public void mostrarFinal(boolean esBlanco) {
        String ganador = esBlanco ? "O" : "X";
        
        System.out.print("\n    ");
        for (int j = 0; j < 6; j++) System.out.printf("%-3d", j + 1);
        System.out.println("\n   +--+--+--+--+--+--+");

        for (int i = 0; i < 3; i++) {
            char fila = (char) ('A' + i);
            System.out.print("   |");
            for (int j = 0; j < 6; j++) {
                if (!celdasGanadoras[i][j])
                    System.out.print(getCeldaParte(celdas[i][j], 1) + "|");
                else
                    System.out.print(ganador + ganador + "|");
            }
            System.out.println();

            System.out.print(" " + fila + " |");
            for (int j = 0; j < 6; j++) {
                if (!celdasGanadoras[i][j])
                    System.out.print(getCeldaParte(celdas[i][j], 2) + "|");
                else
                    System.out.print(ganador + ganador + "|");
            }
            System.out.println();

            System.out.print("   |");
            for (int j = 0; j < 6; j++) {
                if (!celdasGanadoras[i][j])
                    System.out.print(getCeldaParte(celdas[i][j], 3) + "|");
                else
                    System.out.print(ganador + ganador + "|");
            }
            System.out.println("\n   +--+--+--+--+--+--+");
        }
    }

    private void mostrarSinBordes() {
        System.out.println("\n  +--+--+--+--+--+--+");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + " |");
            for (int j = 0; j < 6; j++) System.out.print(getCeldaParte(celdas[i][j], 1) + "|");
            System.out.println();

            System.out.print(" " + " |");
            for (int j = 0; j < 6; j++) System.out.print(getCeldaParte(celdas[i][j], 2) + "|");
            System.out.println();

            System.out.print(" " + " |");
            for (int j = 0; j < 6; j++) System.out.print(getCeldaParte(celdas[i][j], 3) + "|");
            System.out.println("\n  +--+--+--+--+--+--+");
        }
    }

    private String getCeldaParte(Celda celda, int parte) {
        String valor = celda.getValor();
        String color = celda.getColor();

        if (valor.isEmpty()) return "  ";
        boolean esBlanco = color.equalsIgnoreCase("blanco");

        if (valor.equalsIgnoreCase("C")) {
            if (parte == 1 || parte == 3) return esBlanco ? " ○" : " ●";
            else return esBlanco ? "○ " : "● ";
        }

        if (valor.equalsIgnoreCase("D")) {
            if (parte == 1 || parte == 3) return esBlanco ? "○ " : "● ";
            else return esBlanco ? " ○" : " ●";
        }

        return "  ";
    }
    
    public static int mapaAbca012(char c) {
        int idx = Character.toUpperCase(c) - 'A';
        return (idx >= 0 && idx <= 2) ? idx : -1;
    }

    // ============================
    // Lógica del juego
    // ============================
    public void colocarFicha(String movimiento, boolean turnoBlanco) {
        int largo = movimiento.length();
        if (largo != 3) return;
        
        char filaChar = movimiento.charAt(0);
        char colChar = movimiento.charAt(1);
        char accion = movimiento.charAt(2);

        int fila = filaChar - 'A';
        int col = colChar - '1';
        
        if (fila < 0 || fila > 2 || col > 5 || (accion != 'C' && accion != 'D')) return;
        
        Celda celda = celdas[fila][col];
        String valorCelda = celda.getValor();
        
        if (accion == 'I') {
            if (!valorCelda.isEmpty()) {
                String colorCelda = celda.getColor();
                String colorEsperado = turnoBlanco ? "blanco" : "negro";
                
                if(colorCelda.equalsIgnoreCase(colorEsperado)) {
                    if (valorCelda.equals("C"))
                        celda.setValor("D");
                    else if (valorCelda.equals("D"))
                        celda.setValor("C");
                }
                
            }
            return;
        }
        
        if (accion == 'D' || accion == 'C') {
            if (celda.getValor().isEmpty()) {
                celda.setValor(String.valueOf(accion));
                celda.setColor(turnoBlanco ? "blanco" : "negro");
            }
        }
        
    }

    public boolean esJugadaValida(String movimiento, boolean esTurnoBlanco) {
        if (movimiento == null || movimiento.isEmpty())
            return false;
        
        movimiento = movimiento.trim().toUpperCase();
    
        if (movimiento.equals("X") || movimiento.equals("B") || 
            movimiento.equals("N") || movimiento.equals("T") || 
            movimiento.equals("H")) {
            return true;
        }

        if (movimiento.length() != 3) {
            return false;
        }

        char filaChar = movimiento.charAt(0);
        char colChar = movimiento.charAt(1);
        char accion = movimiento.charAt(2);

        // Validar fila (A, B, C)
        if (filaChar < 'A' || filaChar > 'C') {
            return false;
        }

        // Validar columna (1-6)
        if (colChar < '1' || colChar > '6') {
            return false;
        }

        // Validar acción (C, D, I)
        if (accion != 'C' && accion != 'D' && accion != 'I') {
            return false;
        }

        int fila = filaChar - 'A';
        int col = colChar - '1';
        Celda celda = celdas[fila][col];

        if (accion == 'I') {
            if (celda.getValor().isEmpty()) {
                return false;
            }
            String colorCelda = celda.getColor();
            String colorEsperado = esTurnoBlanco ? "blanco" : "negro";
            if (!colorCelda.equalsIgnoreCase(colorEsperado)) {
                return false;
            }
            return true;
        }

        if (accion == 'C' || accion == 'D') {
            return celda.getValor().isEmpty();
        }

        return false;
    }

    // ============================
    // Detección de ganador
    // ============================
    public char hayGanador() {
        char[][] letras = traducirTablero();

        // --- Horizontales ---
        for (int i = 0; i < 3; i++) {
            char l = letras[i][0];
            if (l != ' ' && l == letras[i][2] && l == letras[i][4]) {
                for (int j = 0; j < 6; j++)
                    celdasGanadoras[i][j] = true;
                return l;
            }
        }

        // --- Verticales ---
        for (int j = 0; j < 6; j++) {
            char l = letras[0][j];
            if (l != ' ' && l == letras[1][j] && l == letras[2][j]) {
                celdasGanadoras[0][j] = true;
                celdasGanadoras[0][j+1] = true;
                celdasGanadoras[1][j] = true;
                celdasGanadoras[1][j+1] = true;
                celdasGanadoras[2][j] = true;
                celdasGanadoras[2][j+1] = true;
                return l;
            }
        }

        // --- Diagonales ---
      if (mismo(letras[0][0], letras[1][1], letras[2][2])) {
          celdasGanadoras[0][0] = true; celdasGanadoras[0][1] = true;
          celdasGanadoras[1][1] = true; celdasGanadoras[1][2] = true;
          celdasGanadoras[2][2] = true; celdasGanadoras[2][3] = true;
          return letras[0][0];
      }
      if (mismo(letras[0][1], letras[1][2], letras[2][3])) {
          celdasGanadoras[0][1] = true; celdasGanadoras[0][2] = true;
          celdasGanadoras[1][2] = true; celdasGanadoras[1][3] = true;
          celdasGanadoras[2][3] = true; celdasGanadoras[2][4] = true;
          return letras[0][1];
      }
      if (mismo(letras[0][2], letras[1][3], letras[2][4])) {
          celdasGanadoras[0][2] = true; celdasGanadoras[0][3] = true;
          celdasGanadoras[1][3] = true; celdasGanadoras[1][4] = true;
          celdasGanadoras[2][4] = true; celdasGanadoras[2][5] = true;
          return letras[0][2];
      }
      if (mismo(letras[0][5], letras[1][4], letras[2][3])) {
          celdasGanadoras[0][5] = true; celdasGanadoras[0][4] = true;
          celdasGanadoras[1][4] = true; celdasGanadoras[1][3] = true;
          celdasGanadoras[2][3] = true; celdasGanadoras[2][2] = true;
          return letras[0][5];
      }
      if (mismo(letras[0][4], letras[1][3], letras[2][2])) {
          celdasGanadoras[0][4] = true; celdasGanadoras[0][3] = true;
          celdasGanadoras[1][3] = true; celdasGanadoras[1][2] = true;
          celdasGanadoras[2][2] = true; celdasGanadoras[2][1] = true;
          return letras[0][4];
      }
      if (mismo(letras[0][3], letras[1][2], letras[2][1])) {
          celdasGanadoras[0][3] = true; celdasGanadoras[0][2] = true;
          celdasGanadoras[1][2] = true; celdasGanadoras[1][1] = true;
          celdasGanadoras[2][1] = true; celdasGanadoras[2][0] = true;
          return letras[0][3];
      }

        return ' '; // sin ganador
    }

    private boolean mismo(char a, char b, char c) {
        return a != ' ' && a == b && a == c;
    }

    public boolean esEmpate() {
        return estaLleno() && hayGanador() == ' ';
    }

    private boolean estaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (celdas[i][j].getValor().isEmpty()) return false;
            }
        }
        return true;
    }

    // ============================
    // Traducción de celdas a letras
    // ============================
    public char[][] traducirTablero() {
        char[][] letras = new char[3][6];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) { // hasta 5 porque miramos j y j+1
                String v1 = celdas[i][j].getValor();
                String v2 = celdas[i][j + 1].getValor();

                char letra = ' ';
                if (v1.equals("C") && v2.equals("D")) letra = 'O';
                else if (v1.equals("D") && v2.equals("C")) letra = 'X';

                letras[i][j] = letra;
            }
            letras[i][5] = ' ';
        }
        return letras;
    }
}
