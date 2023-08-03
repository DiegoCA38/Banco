
package pcsc9.banco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.PriorityQueue;

/**
 *
 * @author danie
 */
public class Colas {
    
    
    
    private PriorityQueue<Tiquete> caja1 = new PriorityQueue<>();   //creacion e cajas
    private PriorityQueue<Tiquete> caja2 = new PriorityQueue<>();   //tipo B (dos o mas transmites)
    private PriorityQueue<Tiquete> caja3 = new PriorityQueue<>();
    
   public void llenarColas(Tiquete tiquete){   //pone los tiquets en caja al de menor espacio en cola
    
        int cantidadCaja1 = caja1.size();   //determina cantidad de objetos en caja
        int cantidadCaja2 = caja2.size();
        int cantidadCaja3 = caja3.size();

        if(cantidadCaja1 <= cantidadCaja2 && cantidadCaja1 <= cantidadCaja3){       //caja 1 menor carga que las otras dos
          caja1.offer(tiquete);

        }else if(cantidadCaja2 <= cantidadCaja1 && cantidadCaja2 <= cantidadCaja3){ //caja 2 menor carga que las otras dos
           caja2.offer(tiquete);

        }else{                                                                      //caja 3 era el que menos carga tenia
            caja3.offer(tiquete);
        }  
   } 
   
   public void writeCaja(){ //metodo guardar datos en txt
    
    File archivo = new File("Caja1.txt");        //crear txt
    
    //
        
    //
   
   }
    
}
