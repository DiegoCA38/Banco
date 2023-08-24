
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
                    
                }
            } else if (opcion.equals("3")) {
                ejecutar = false;
            }
        }

        JOptionPane.showMessageDialog(null, "Bienvenido, Gracias por utilizar el sistema bancario");    //3
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
            System.out.println("Inicio de sesión exitoso");    //1
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            return false;
        }
    }
}

