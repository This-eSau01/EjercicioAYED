import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LecturaArchivo {

    public static void main(String[] args) {

        String ruta = "paquete.txt";

        try {
            File archivo = new File(ruta);
            Scanner sc = new Scanner(archivo);

            String linea;

            int capacidad = 0;
            int zonas = 0;
            int paquetes = 0;

            while (sc.hasNextLine()) {

                linea = sc.nextLine();

                if (linea.contains("CAPACIDAD")) {
                    int pos = linea.indexOf("=");
                    capacidad = Integer.parseInt(linea.substring(pos + 1));
                }

                else if (linea.contains("ZONAS")) {
                    int pos = linea.indexOf("=");
                    zonas = Integer.parseInt(linea.substring(pos + 1));
                }

                else if (linea.contains("PAQUETES_POR_ZONA")) {
                    int pos = linea.indexOf("=");
                    paquetes = Integer.parseInt(linea.substring(pos + 1));
                }

                else if (linea.contains(",")) {

                    if (linea.contains("Codigo")) continue;

                    int pos1 = linea.indexOf(",");
                    int pos2 = linea.indexOf(",", pos1 + 1);
                    int pos3 = linea.indexOf(",", pos2 + 1);
                    int pos4 = linea.indexOf(",", pos3 + 1);

                    String codigo = linea.substring(0, pos1);
                    int peso = Integer.parseInt(linea.substring(pos1 + 1, pos2));
                    String prioridad = linea.substring(pos2 + 1, pos3);
                    int valor = Integer.parseInt(linea.substring(pos3 + 1, pos4));
                    int zona = Integer.parseInt(linea.substring(pos4 + 1));

                    System.out.println("Paquete: " + codigo);
                    System.out.println("Peso: " + peso);
                    System.out.println("Prioridad: " + prioridad);
                    System.out.println("Valor: " + valor);
                    System.out.println("Zona: " + zona);
                    System.out.println();
                }
            }

            sc.close();

            System.out.println("Capacidad: " + capacidad);
            System.out.println("Zonas: " + zonas);
            System.out.println("Paquetes por zona: " + paquetes);

        } catch (IOException e) {
            System.out.println("errro");
        }
    }
}
