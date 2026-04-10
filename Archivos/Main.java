import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String ruta = "paquete.txt";
        ArrayList<Paquete<String>> lista = LecturaArchivo.leer(ruta);
        int zonas = 3;
        int paquetesPorZona = 10;
        Paquete<String>[][] matriz = new Paquete[zonas][paquetesPorZona];
        int[] contador = new int[zonas];
        for (Paquete<String> p : lista) {
            int z = p.getZona() - 1;
            matriz[z][contador[z]++] = p;
        }
        System.out.println("ANTES DE ORDENAR:");
        Ordenamiento.mostrarMatriz(matriz);
        Ordenamiento.mergeSortPorZonas(matriz);
        System.out.println("\nDESPUÉS DE ORDENAR:");
        Ordenamiento.mostrarMatriz(matriz);
        int capacidad = 10;

        for (int i = 0; i < zonas; i++) {
            System.out.println("\nZona " + (i + 1));
            int valorMax = SeleccionPaquetes.seleccionarDP(matriz[i], capacidad);
            System.out.println("Valor máximo: " + valorMax);
            var seleccion = SeleccionPaquetes.obtenerSeleccionDP(matriz[i], capacidad);
            SeleccionPaquetes.mostrarSeleccion(seleccion);
        }
    }
}
