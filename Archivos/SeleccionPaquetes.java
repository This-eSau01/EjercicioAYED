import java.util.ArrayList;

public class SeleccionPaquetes {

    public static int seleccionarDP(Paquete<?>[] paquetes, int capacidad) {

        int n = paquetes.length;
        int[][] dp = new int[n + 1][capacidad + 1];
        for (int i = 1; i <= n; i++) {
            Paquete<?> actual = paquetes[i - 1];
            for (int w = 0; w <= capacidad; w++) {
                if ((int) actual.getPeso() <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - (int) actual.getPeso()] + actual.getValor()
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
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
            for (int w = 0; w <= capacidad; w++) {
                if ((int) actual.getPeso() <= w) {

                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - (int) actual.getPeso()] + actual.getValor()
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        ArrayList<Paquete<?>> seleccion = new ArrayList<>();
        int w = capacidad;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                Paquete<?> p = paquetes[i - 1];
                seleccion.add(p);
                w -= (int) p.getPeso();
            }
        }

        return seleccion;
    }
    public static void mostrarSeleccion(ArrayList<Paquete<?>> seleccion) {
        int valorizacionTotal = 0;
        for (Paquete<?> p : seleccion) {
            System.out.println(p);
            valorizacionTotal += p.getValor();
        }
        System.out.println("Valor total: " + valorizacionTotal);
    }
}
