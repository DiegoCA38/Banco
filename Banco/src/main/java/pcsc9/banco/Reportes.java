
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
    
    //caja que mas atendio
    public void cajaAtencionMayor(boolean mayor1,boolean mayor2,boolean mayor3){
        
        if(mayor1==true){
           JOptionPane.showMessageDialog(null,"La caja 1 atendio la mayor cantidad de tiquetes"); 
        }
        
        if(mayor2==true){ 
           JOptionPane.showMessageDialog(null,"La caja 2 atendio la mayor cantidad de tiquetes"); 
        }
        
        if(mayor3==true){
           JOptionPane.showMessageDialog(null,"La caja 3 antendio la mayor cantidad de tiquetes"); 
        }   
        
    }
    
//    public void cantidadTiquetes(int lineasCaja1, int lineasCaja2, int lineasCaja3){
//        
//        
//        lineasCaja1=-10;
//        lineasCaja2=-10;
//        lineasCaja3=-10;
//       
//       JOptionPane.showMessageDialog(null,"La caja 1 ha atendido a "+lineasCaja1+" clientes \n"
//                                                    + "La caja 2 ha atendido a "+lineasCaja2+" clientes \n"
//                                                    + "La caja 3 ha atendido a "+lineasCaja2+" clientes \n");
//
//    }
    
}