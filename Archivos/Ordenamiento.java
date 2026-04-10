
import java.util.Comparator;

public class Ordenamiento {
    @SuppressWarnings("unchecked")
    public static <T> Comparator<Paquete<T>> comparadorDescendente() {
        return (a, b) -> {
            T pa = a.getPrioridad();
            T pb = b.getPrioridad();

            if (pa instanceof String) {
                return valorPrioridad((String) pb) - valorPrioridad((String) pa);
            } else if (pa instanceof Integer) {
                return (Integer) pb - (Integer) pa;
            } else if (pa instanceof Comparable) {
                return ((Comparable<T>) pb).compareTo(pa);
            }
            return 0;
        };
    }

    private static int valorPrioridad(String p) {
        switch (p.trim().toLowerCase()) {
            case "alta":  return 3;
            case "media": return 2;
            case "baja":  return 1;
            default:      return 0;
        }
    }

    //Aplico Merge Sort en cada fila de la matriz
    public static <T> void mergeSortPorZonas(Paquete<T>[][] zonas) {
        Comparator<Paquete<T>> comp = comparadorDescendente();
        for (int i = 0; i < zonas.length; i++) {
            System.out.println("\n[Merge Sort] Zona " + (i + 1) + " — ANTES:");
            mostrarZona(zonas[i]);
            mergeSort(zonas[i], 0, zonas[i].length - 1, comp);
            System.out.println("[Merge Sort] Zona " + (i + 1) + " — DESPUÉS:");
            mostrarZona(zonas[i]);
        }
    }

    private static <T> void mergeSort(Paquete<T>[] zona, int izq, int der, Comparator<Paquete<T>> comp) {
        if (izq >= der) return;
        int mid = (izq + der) / 2;
        mergeSort(zona, izq, mid, comp);
        mergeSort(zona, mid + 1, der, comp);
        merge(zona, izq, mid, der, comp);
    }

    @SuppressWarnings("unchecked")
    private static <T> void merge(Paquete<T>[] zona, int izq, int mid, int der, Comparator<Paquete<T>> comp) {
        int n1 = mid - izq + 1;
        int n2 = der - mid;

        Paquete<T>[] izqArr = new Paquete[n1];
        Paquete<T>[] derArr = new Paquete[n2];

        for (int i = 0; i < n1; i++) izqArr[i] = zona[izq + i];
        for (int j = 0; j < n2; j++) derArr[j] = zona[mid + 1 + j];

        int i = 0, j = 0, k = izq;
        while (i < n1 && j < n2) {
            if (comp.compare(izqArr[i], derArr[j]) <= 0) {
                zona[k++] = izqArr[i++];
            } else {
                zona[k++] = derArr[j++];
            }
        }
        while (i < n1) zona[k++] = izqArr[i++];
        while (j < n2) zona[k++] = derArr[j++];
    }

    // Quickshor por cada fila de la matriz 
    public static <T> void quickSortPorZonas(Paquete<T>[][] zonas) {
        Comparator<Paquete<T>> comp = comparadorDescendente();
        for (int i = 0; i < zonas.length; i++) {
            System.out.println("\n[Quick Sort] Zona " + (i + 1) + " — ANTES:");
            mostrarZona(zonas[i]);
            quickSort(zonas[i], 0, zonas[i].length - 1, comp);
            System.out.println("[Quick Sort] Zona " + (i + 1) + " — DESPUÉS:");
            mostrarZona(zonas[i]);
        }
    }

    private static <T> void quickSort(Paquete<T>[] zona, int izq, int der, Comparator<Paquete<T>> comp) {
        if (izq >= der) return;
        int pivot = particionar(zona, izq, der, comp);
        quickSort(zona, izq, pivot - 1, comp);
        quickSort(zona, pivot + 1, der, comp);
    }

    private static <T> int particionar(Paquete<T>[] zona, int izq, int der, Comparator<Paquete<T>> comp) {
        Paquete<T> pivote = zona[der];
        int i = izq - 1;

        for (int j = izq; j < der; j++) {
            if (comp.compare(zona[j], pivote) <= 0) {
                i++;
                Paquete<T> temp = zona[i];
                zona[i] = zona[j];
                zona[j] = temp;
            }
        }

        Paquete<T> temp = zona[i + 1];
        zona[i + 1] = zona[der];
        zona[der] = temp;

        return i + 1;
    }
    
    public static <T> void mostrarZona(Paquete<T>[] zona) {
        System.out.printf("  %-8s %-8s %-10s %-8s %-6s%n",
                "Código", "Peso", "Prioridad", "Valor", "Zona");
        System.out.println("  " + "-".repeat(46));
        for (Paquete<T> p : zona) {
            if (p != null) {
                System.out.printf("  %-8s %-8.1f %-10s %-8d %-6s%n",
                        p.getCodigo(),
                        p.getPeso(),
                        p.getPrioridad().toString(),
                        p.getValor(),
                        p.getZona());
            }
        }
    }

    public static <T> void mostrarMatriz(Paquete<T>[][] zonas) {
        System.out.println("=== MATRIZ DE PAQUETES POR ZONA ===");
        for (int i = 0; i < zonas.length; i++) {
            System.out.println("\n--- Zona " + (i + 1) + " ---");
            mostrarZona(zonas[i]);
        }
    }
}
