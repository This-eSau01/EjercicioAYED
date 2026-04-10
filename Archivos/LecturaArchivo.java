import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LecturaArchivo {

    public static ArrayList<Paquete<String>> leer(String ruta) {

        ArrayList<Paquete<String>> lista = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(ruta));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.contains(",") && !linea.contains("Codigo")) {

                    String[] datos = linea.split(",");
                    String codigo = datos[0];
                    double peso = Double.parseDouble(datos[1]);
                    String prioridad = datos[2];
                    int valor = Integer.parseInt(datos[3]);
                    int zona = Integer.parseInt(datos[4]);
                    lista.add(new Paquete<>(codigo, peso, prioridad, valor, zona));
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error leyendo archivo");
        }
        return lista;
    }
}
