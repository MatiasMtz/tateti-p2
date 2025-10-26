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

    public void colocarFicha(String movimiento, boolean turnoBlanco) {
        int largo = movimiento.length();
        if (largo != 3) return;
        
        char filaChar = movimiento.charAt(0);
        char colChar = movimiento.charAt(1);
        char accion = movimiento.charAt(2);

        int fila = filaChar - 'A';
        int col = colChar - '1';

        if (fila < 0 || fila > 2 || col > 5 || (accion != 'C' && accion != 'D' && accion != 'I')) return;
        
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

    public char hayGanador() {
        //Detección de ganador
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
      if (mismo(letras[0][4], letras[1][3], letras[2][2])) {
          celdasGanadoras[0][5] = true; celdasGanadoras[0][4] = true;
          celdasGanadoras[1][4] = true; celdasGanadoras[1][3] = true;
          celdasGanadoras[2][3] = true; celdasGanadoras[2][2] = true;
          return letras[0][4];
      }
      if (mismo(letras[0][3], letras[1][2], letras[2][1])) {
          celdasGanadoras[0][4] = true; celdasGanadoras[0][3] = true;
          celdasGanadoras[1][3] = true; celdasGanadoras[1][2] = true;
          celdasGanadoras[2][2] = true; celdasGanadoras[2][1] = true;
          return letras[0][3];
      }
      if (mismo(letras[0][2], letras[1][1], letras[2][0])) {
          celdasGanadoras[0][3] = true; celdasGanadoras[0][2] = true;
          celdasGanadoras[1][2] = true; celdasGanadoras[1][1] = true;
          celdasGanadoras[2][1] = true; celdasGanadoras[2][0] = true;
          return letras[0][2];
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
    
    public char[][] traducirTablero() {
        // Traducción de celdas a letras
        char[][] letras = new char[3][6];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) { // hasta 5 porque miramos j y j+1
                String v1 = celdas[i][j].getValor();
                String v2 = celdas[i][j + 1].getValor();

                char letra = ' ';
                if (v1 != "")
                    letra = v1.charAt(0);

                if (v1.equals("C") && v2.equals("D")) letra = 'O';
                else if (v1.equals("D") && v2.equals("C")) letra = 'X';

                letras[i][j] = letra;
            }
            if (celdas[i][5].getValor() == "")
                letras[i][5] = ' ';
            else
                letras[i][5] = celdas[i][5].getValor().charAt(0);
        }
        return letras;
    }

    public String hayJugadaGanadora(boolean turnoBlanco) {
        char letraBuscada = turnoBlanco ? 'O' : 'X';
        char[][] letras = traducirTablero();

        // --- Horizontales ---
        for (int i = 0; i < 3; i++) {
            String jugada = verificarLineaGanadora(i, 0, i, 2, i, 4, letraBuscada, letras);
            if (jugada != null) return jugada;
        }

        // --- Verticales ---
        for (int j = 0; j < 6; j++) {
            String jugada = verificarLineaGanadora(0, j, 1, j, 2, j, letraBuscada, letras);
            if (jugada != null) return jugada;
        }

        // --- Diagonales ---
        String jugada = verificarLineaGanadora(0, 0, 1, 1, 2, 2, letraBuscada, letras);
        if (jugada != null) return jugada;

        jugada = verificarLineaGanadora(0, 1, 1, 2, 2, 3, letraBuscada, letras);
        if (jugada != null) return jugada;

        jugada = verificarLineaGanadora(0, 2, 1, 3, 2, 4, letraBuscada, letras);
        if (jugada != null) return jugada;

        jugada = verificarLineaGanadora(0, 4, 1, 3, 2, 2, letraBuscada, letras);
        if (jugada != null) return jugada;

        jugada = verificarLineaGanadora(0, 3, 1, 2, 2, 1, letraBuscada, letras);
        if (jugada != null) return jugada;

        jugada = verificarLineaGanadora(0, 2, 1, 1, 2, 0, letraBuscada, letras);
        if (jugada != null) return jugada;

        return null;
    }

    private String verificarLineaGanadora(int i1, int j1, int i2, int j2, int i3, int j3, char letraBuscada, char[][] letras) {
        int coincidencias = 0;
        int filaFaltante = -1, colFaltante = -1;

        // Contar coincidencias y encontrar la posición faltante
        if (letras[i1][j1] == letraBuscada) {
            coincidencias++;
        } else {
            filaFaltante = i1;
            colFaltante = j1;
        }

        if (letras[i2][j2] == letraBuscada) {
            coincidencias++;
        } else {
            filaFaltante = i2;
            colFaltante = j2;
        }

        if (letras[i3][j3] == letraBuscada) {
            coincidencias++;
        } else {
            filaFaltante = i3;
            colFaltante = j3;
        }

        // Si hay exactamente 2 coincidencias, verificar la posición faltante
        if (coincidencias == 2) {
            return calcularJugadaGanadora(filaFaltante, colFaltante, letraBuscada);
        }

        return null;
    }

    private String calcularJugadaGanadora(int i, int j, char letraBuscada) {
        // Verificar si es posible hacer una jugada en esta posición
        String v1 = celdas[i][j].getValor();
        String v2 = (j < 5) ? celdas[i][j + 1].getValor() : "";

        // Si la posición está completamente vacía, no es posible
        if (v1.isEmpty() && v2.isEmpty()) {
            return null;
        }

        char fila = (char) ('A' + i);
        char columna = (char) ('1' + j);

        if (letraBuscada == 'O') {
            // Buscamos completar 'O' (C+D)
            if (v2.equals("D") && v1.isEmpty()) {
                // Poner "C" en celdas[i][j]
                return "" + fila + columna + "C";
            } else if (v2.equals("D") && v1.equals("D")) {
                // Invertir celdas[i][j]
                return "" + fila + columna + "I";
            } else if (v1.equals("C") && v2.isEmpty()) {
                // Poner "D" en celdas[i][j+1]
                char columnaSiguiente = (char) ('1' + j + 1);
                return "" + fila + columnaSiguiente + "D";
            } else if (v1.equals("C") && v2.equals("C")) {
                // Invertir celdas[i][j+1]
                char columnaSiguiente = (char) ('1' + j + 1);
                return "" + fila + columnaSiguiente + "I";
            }
        } else {
            // Buscamos completar 'X' (D+C)
            if (v2.equals("C") && v1.isEmpty()) {
                // Poner "D" en celdas[i][j]
                return "" + fila + columna + "D";
            } else if (v2.equals("C") && v1.equals("C")) {
                // Invertir celdas[i][j]
                return "" + fila + columna + "I";
            } else if (v1.equals("D") && v2.isEmpty()) {
                // Poner "C" en celdas[i][j+1]
                char columnaSiguiente = (char) ('1' + j + 1);
                return "" + fila + columnaSiguiente + "C";
            } else if (v1.equals("D") && v2.equals("D")) {
                // Invertir celdas[i][j+1]
                char columnaSiguiente = (char) ('1' + j + 1);
                return "" + fila + columnaSiguiente + "I";
            }
        }

        return null;
    }
}
