import java.util.ArrayList;

public class SeleccionPaquetes {

    public static int seleccionarRecursivo(Paquete<?>[] paquetes, int capacidad) {
        return mochilaRecursiva(paquetes, paquetes.length, capacidad);
    }

    private static int mochilaRecursiva(Paquete<?>[] paquetes, int n, int capacidad) {
        if (n == 0 || capacidad == 0) {
            return 0;
        }

        Paquete<?> actual = paquetes[n - 1];

        if (actual.getPeso() > capacidad) {
            return mochilaRecursiva(paquetes, n - 1, capacidad);
        } else {
            int incluir = actual.getValor()
                    + mochilaRecursiva(paquetes, n - 1, capacidad - actual.getPeso());

            int noIncluir = mochilaRecursiva(paquetes, n - 1, capacidad);

            return Math.max(incluir, noIncluir);
        }
    }

    public static int seleccionarDP(Paquete<?>[] paquetes, int capacidad) {
        int n = paquetes.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        for (int i = 1; i <= n; i++) {
            Paquete<?> actual = paquetes[i - 1];

            for (int c = 0; c <= capacidad; c++) {
                if (actual.getPeso() > c) {
                    dp[i][c] = dp[i - 1][c];
                } else {
                    int incluir = actual.getValor() + dp[i - 1][c - actual.getPeso()];
                    int noIncluir = dp[i - 1][c];
                    dp[i][c] = Math.max(incluir, noIncluir);
                }
            }
        }

        return dp[n][capacidad];
    }

    public static ArrayList<Paquete<?>> obtenerSeleccionDP(Paquete<?>[] paquetes, int capacidad) {
        int n = paquetes.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        for (int i = 1; i <= n; i++) {
            Paquete<?> actual = paquetes[i - 1];

            for (int c = 0; c <= capacidad; c++) {
                if (actual.getPeso() > c) {
                    dp[i][c] = dp[i - 1][c];
                } else {
                    int incluir = actual.getValor() + dp[i - 1][c - actual.getPeso()];
                    int noIncluir = dp[i - 1][c];
                    dp[i][c] = Math.max(incluir, noIncluir);
                }
            }
        }

        ArrayList<Paquete<?>> seleccionados = new ArrayList<>();
        int c = capacidad;

        for (int i = n; i > 0; i--) {
            if (dp[i][c] != dp[i - 1][c]) {
                seleccionados.add(paquetes[i - 1]);
                c -= paquetes[i - 1].getPeso();
            }
        }

        return seleccionados;
    }

    public static void mostrarSeleccion(ArrayList<Paquete<?>> seleccionados) {
        int pesoTotal = 0;
        int valorizacionTotal = 0;

        System.out.println(" PAQUETES SELECCIONADOS ");
        for (Paquete<?> p : seleccionados) {
            System.out.println(p);
            pesoTotal += p.getPeso();
            valorizacionTotal += p.getValor();
        }

        System.out.println("Peso total: " + pesoTotal);
        System.out.println("Valor total: " + valorTotal);
    }
}
