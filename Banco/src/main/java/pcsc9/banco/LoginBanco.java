/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class LoginBanco {
 private static Map<String, String> usuarios = new HashMap<>();
 public void mostrarMenu()
{
        Scanner scanner = new Scanner(System.in);
        boolean ejecutar = true;

        while (ejecutar) {
            String opcion = JOptionPane.showInputDialog("Bienvenido al sistema bancario\n"
                    + "1. Registrarse\n"
                    + "2. Iniciar sesion\n"
                    + "3. Salir\n"
                    + "Seleccione una opcion:");
          
            scanner.nextLine(); 

            if (opcion.equals(1)) {
                    registrarUsuario(scanner);
                    break;
            }
            else if (opcion.equals(2)){ 
                    boolean loginExitoso = iniciarSesion(scanner);
                    if (loginExitoso) {
                        ejecutar = false; 
                        accederSistema(scanner);
                    }
                    break;
            
            }
        }
        
        JOptionPane.showInputDialog("Gracias por utilizar el sistema bancario");
    }

    private static void registrarUsuario(Scanner scanner) {
        JOptionPane.showInputDialog("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine();

        if (usuarios.containsKey(usuario)) {
            JOptionPane.showInputDialog("El usuario ya esta registrado");
            return;
        }

        JOptionPane.showInputDialog("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();

        usuarios.put(usuario, contraseña);
        JOptionPane.showInputDialog("Usuario registrado exitosamente");
    }

    private static boolean iniciarSesion(Scanner scanner) {
        JOptionPane.showInputDialog("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine();

        if (!usuarios.containsKey(usuario)) {
            JOptionPane.showInputDialog("Usuario no registrado");
            return false;
        }

       JOptionPane.showInputDialog("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();

        String contraseñaGuardada = usuarios.get(usuario);
        if (contraseña.equals(contraseñaGuardada)) {
            JOptionPane.showInputDialog("Inicio de sesion exitoso");
            return true;
        } else {
            JOptionPane.showInputDialog("Contraseña incorrecta");
            return false;
        }
    }

    private static void accederSistema(Scanner scanner) {
        boolean ejecutar = true;

        while (ejecutar) {
            JOptionPane.showInputDialog("\nModulos disponibles:");
            JOptionPane.showInputDialog("0. Configuracion");
            JOptionPane.showInputDialog("1.1. Creacion de tiquetes");
            JOptionPane.showInputDialog("1.2. Atención de tiquetes");
            JOptionPane.showInputDialog("1.3. Llenado de las colas");
            JOptionPane.showInputDialog("1.4. Reportes");
            JOptionPane.showInputDialog("9. Cerrar sesion");
            JOptionPane.showInputDialog("Seleccione un modulo: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "0":
                    JOptionPane.showInputDialog("Accediendo al modulo de Configuracion");
                    
                    break;
                case "1.1":
                    JOptionPane.showInputDialog("Accediendo al módulo de Creacion de tiquetes");
                    
                    break;
                case "1.2":
                    JOptionPane.showInputDialog("Accediendo al modulo de Atencion de tiquetes");
                   
                    break;
                case "1.3":
                    JOptionPane.showInputDialog("Accediendo al modulo de Llenado de las colas");
                    
                    break;
                case "1.4":
                    JOptionPane.showInputDialog("Accediendo al modulo de Reportes");
                    
                    break;
                case "9":
                   
                    break;
                default:
                    JOptionPane.showInputDialog("Opcion invalida");
                    break;
            }
        }
    }
}   