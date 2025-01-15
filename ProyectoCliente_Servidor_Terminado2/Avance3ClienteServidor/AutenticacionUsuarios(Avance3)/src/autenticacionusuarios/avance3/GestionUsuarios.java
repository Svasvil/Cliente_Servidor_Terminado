package autenticacionusuarios.avance3;

import javax.swing.JOptionPane;

public class GestionUsuarios {

    public String[][] usuarios;
    public int Contador;

    public GestionUsuarios() {
        usuarios = new String[5][5];
        Contador = 0;
    }

    public void SingUp() {
        String id = JOptionPane.showInputDialog("Digite el ID del usuario");
        String NombreUsuario = JOptionPane.showInputDialog("Digite el nombre de usuario");
        String ContrasenaUsuario = JOptionPane.showInputDialog("Digite la contraseña");
        usuarios[Contador][0] = id;
        usuarios[Contador][1] = NombreUsuario;
        usuarios[Contador][2] = ContrasenaUsuario;
        Contador++;
    }

    public void Login() {
        Usuarios u1 = new Usuarios(1, "Si", "1234"); // Usuario y contraseña por defecto
        while (true) {
            String NombreUsuarioLog = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
            String ContrasenaUsuarioLog = JOptionPane.showInputDialog("Ingrese su contraseña:");
            if (u1.getNombreUsuario().equals(NombreUsuarioLog) && u1.getContrasena().equals(ContrasenaUsuarioLog)) {
                JOptionPane.showMessageDialog(null, "¡Bienvenido al sistema!");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos, pruebe de nuevo.");
            }
        }
    }

    public void VerDatosCuentaCreada() {
        String resultado = "";
        for (int i = 0; i < Contador; i++) {
            resultado += "ID: " + usuarios[i][0]
                    + ", Nombre: " + usuarios[i][1]
                    + ", Contraseña: " + usuarios[i][2] + "\n";
        }
        JOptionPane.showMessageDialog(null, resultado);
    }

    public void ModificarID() {
        String NuevoID = JOptionPane.showInputDialog("Ingrese el nuevo ID");
        usuarios[Contador][0] = NuevoID;
    }

    public void ModificarNombre() {
        String NuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo Nombre de usuario");
        usuarios[Contador][1] = NuevoNombre;
    }

    public void ModificarContrasena() {
        String NuevaContrasena = JOptionPane.showInputDialog("Ingrese la nueva contrasena ");
        usuarios[Contador][1] = NuevaContrasena;
    }

}
