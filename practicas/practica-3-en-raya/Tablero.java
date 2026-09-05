public class Tablero {
    private char[][] matriz;

    public Tablero() {
        matriz = new char[3][3];
        limpiar();
    }

    public void limpiar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = ' ';
            }
        }
    }

    public void mostrar() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public boolean hacerMovimiento(int fila, int col, char simbolo) {
        if (fila >= 0 && fila < 3 && col >= 0 && col < 3 && matriz[fila][col] == ' ') {
            matriz[fila][col] = simbolo;
            return true;
        }
        return false;
    }

    public boolean hayGanador(char s) {
        // Revisar filas y columnas
        for (int i = 0; i < 3; i++) {
            if ((matriz[i][0] == s && matriz[i][1] == s && matriz[i][2] == s) ||
                (matriz[0][i] == s && matriz[1][i] == s && matriz[2][i] == s)) {
                return true;
            }
        }
        // Revisar diagonales
        return (matriz[0][0] == s && matriz[1][1] == s && matriz[2][2] == s) ||
               (matriz[0][2] == s && matriz[1][1] == s && matriz[2][0] == s);
    }

    public boolean estaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == ' ') return false;
            }
        }
        return true;
    }
}