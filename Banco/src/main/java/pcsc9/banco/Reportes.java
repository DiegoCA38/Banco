
package pcsc9.banco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import javax.swing.*;
        
public class Reportes{
        
    public void LeerCajas(){
        String rutaArchivo = "C:\\caja1.txt"; // Cambia esto a la ruta correcta

        try {
            FileReader fileReader = new FileReader(rutaArchivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo"+ e.getMessage());
        }
    }
    
}