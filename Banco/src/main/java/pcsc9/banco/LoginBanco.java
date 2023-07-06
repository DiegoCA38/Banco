/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class LoginBanco {
    private static Map<String, String> usuarios = new HashMap<>();

    public static void main(String[] args) {
        LoginBanco loginBanco = new LoginBanco();
        loginBanco.mostrarMenu();
    }

    public void mostrarMenu() {
        boolean ejecutar = true;

        while (ejecutar) {
            String opcion = JOptionPane.showInputDialog("Bienvenido al sistema bancario\n"
                    + "1. Registrarse\n"
                    + "2. Iniciar sesion\n"
                    + "3. Salir\n"
                    + "Seleccione una opcion:");

            if (opcion.equals("1")) {
                registrarUsuario();
            } else if (opcion.equals("2")) {
                boolean loginExitoso = iniciarSesion();
                if (loginExitoso) {
                    ejecutar = false;
                    accederSistema();
                }
            } else if (opcion.equals("3")) {
                ejecutar = false;
            }
        }

        JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema bancario");
    }

    private void registrarUsuario() {
        String usuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");

        if (usuarios.containsKey(usuario)) {
            JOptionPane.showMessageDialog(null, "El usuario ya está registrado");
            return;
        }

        String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");

        usuarios.put(usuario, contraseña);
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
    }

    private boolean iniciarSesion() {
        String usuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");

        if (!usuarios.containsKey(usuario)) {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            return false;
        }

        String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
        String contraseñaGuardada = usuarios.get(usuario);

        if (contraseña.equals(contraseñaGuardada)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            return false;
        }
    }

    private void accederSistema() {
        JOptionPane.showMessageDialog(null, "¡Bienvenido al sistema bancario!");
        
    }
}
/*
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
    }*/
