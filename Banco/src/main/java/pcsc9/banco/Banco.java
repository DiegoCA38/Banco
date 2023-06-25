/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pcsc9.banco;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 * steve
 */
public class Banco {

    public static void main(String[] args) {
       Tiquetes tiquetes = new Tiquetes();
       LoginBanco menu = new LoginBanco();
       
      
        File archivo = new File("Prod.txt"); // crear txt
        boolean existe = archivo.exists(); // verificar si existe txt, si existe = true

        if (archivo.length() == 0) { // si se genera el txt pero está vacío
            existe = false; // considera no existente
        }

        if (existe == true) { // confirma si existe
            System.out.println("El archivo 'prod.txt' ya existe.");
        } else {
            try {
                System.out.println("El archivo 'prod.txt' no existe.");

                FileWriter filewriter = new FileWriter(archivo); // tomar o llamar el txt
                BufferedWriter buffWriter = new BufferedWriter(filewriter); // luego escribir en ese txt

                buffWriter.write("Nombre del Banco :" + JOptionPane.showInputDialog("Ingrese el nombre del Banco: "));
                buffWriter.newLine();

                buffWriter.write("Total de Cajas: " + Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad total de cajas: ")));
                buffWriter.newLine();

                buffWriter.write("Caja Preferencial: 1");
                buffWriter.newLine();

                buffWriter.write("Cajas Rapida: 1");
                buffWriter.newLine();

                buffWriter.write("Caja no Preferencial: " + Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cajas no Preferencial: ")));
                buffWriter.newLine();

                buffWriter.close(); // cerrar escritura

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al crear archivo");
            }
        }
    
        while (true) {
            // Mostrar menú de opciones
            String opcion = JOptionPane.showInputDialog("Seleccione una opción:\n1. Crear tiquete\n2. Atender siguiente tiquete\n3. Salir");

            if (opcion.equals("1")) {
                // Crear tiquete
                String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
                String id = JOptionPane.showInputDialog("Ingrese su ID:");
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad:"));
                String tramite = JOptionPane.showInputDialog("Ingrese el tipo de trámite \n1.Depósitos. \n2.Retiros. \n3.Cambio de Divisas");
                String tipo = JOptionPane.showInputDialog("Ingrese el tipo de Ventanilla \nP: preferencial, \nA: un solo trámite, \nB: dos o más trámites");

                Tiquete nuevoTiquete = new Tiquete(nombre, id, edad, tramite, tipo);
                Tiquete ventaTipo = new Ventanilla (nombre, id, edad, tramite, tipo);
                tiquetes.agregarTiquete(ventaTipo);
                tiquetes.agregarTiquete(nuevoTiquete);

                JOptionPane.showMessageDialog(null, "Se ha creado el tiquete Numero\n      " + nuevoTiquete.getID() + "\n Se atendera en ventanilla: \n      " + ventaTipo.getTipo() );
            } else if (opcion.equals("2")) {
                // Atender siguiente tiquete
                Tiquete siguienteTiquete = tiquetes.atenderSiguienteTiquete();

                if (siguienteTiquete != null) {
                    JOptionPane.showMessageDialog(null, "Es el turno del tiquete #" + siguienteTiquete.getID());
                } else {
                    JOptionPane.showMessageDialog(null, "No hay tiquetes pendientes");
                }
            } else if (opcion.equals("3")) {
                // Salir del programa
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }
}