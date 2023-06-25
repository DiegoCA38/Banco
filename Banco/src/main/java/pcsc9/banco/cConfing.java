/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class cConfing {
  String NombreBanco;
    int CantidadCajas;
    int CajaPreferencial;
    int CajaRapida;
    int CajaNormal;
    
    
    public static void cConfigInicio(){
    
    File archivo = new File("Prod.txt");        //crear txt
    boolean existe=archivo.exists();    //verificar si existe txt, si existe = true
    
    
    
    if( archivo.length()==0){   //si se genera el txt pero esta vacio
       existe=false;            //considera no existente
    }
    
    if (existe==true){          //confirma si existe
        System.out.println("El archivo 'prod.txt' ya existe.");
        
    }else{
        
        try{
            
        System.out.println("El archivo 'prod.txt' no existe.");
        
        FileWriter filewriter = new FileWriter(archivo);            //tomar o llamar el txt
        BufferedWriter buffWriter = new BufferedWriter(filewriter); //luego escribir en ese txt
       
        buffWriter.write("Nombre del Banco :"+JOptionPane.showInputDialog("Ingrese el nombre del Banco: "));
        buffWriter.newLine();
        
        buffWriter.write("Total de Cajas: "+Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad total de cajas: ")));
        buffWriter.newLine();
        
        buffWriter.write("Caja Preferencial: 1");
        buffWriter.newLine();
        
        buffWriter.write("Cajas Rapida: 1");
        buffWriter.newLine();
        
        buffWriter.write("Caja no Preferencial: "+Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cajas no Preferencial: ")));
        buffWriter.newLine();
        
        buffWriter.close();                 //cerrar escritura
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al crear archivo");
        }
    }
}
}