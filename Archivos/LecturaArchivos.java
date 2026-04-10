import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaArchivo {

    public static void main(String[] args) {

        String ruta = "paquete.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            int capacidad = 0;
            int zonas = 0;
            int paquetes = 0;

            while ((linea = br.readLine()) != null) {

                if (linea.contains("CAPACIDAD")) {
                    int pos = linea.indexOf("=");
                    String num = linea.substring(pos + 1);
                    capacidad = Integer.parseInt(num);
                }

                else if (linea.contains("ZONAS")) {
                    int pos = linea.indexOf("=");
                    String num = linea.substring(pos + 1);
                    zonas = Integer.parseInt(num);
                }

                else if (linea.contains("PAQUETES_POR_ZONA")) {
                    int pos = linea.indexOf("=");
                    String num = linea.substring(pos + 1);
                    paquetes = Integer.parseInt(num);
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

            br.close();

            System.out.println("Capacidad: " + capacidad);
            System.out.println("Zonas: " + zonas);
            System.out.println("Paquetes por zona: " + paquetes);

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
