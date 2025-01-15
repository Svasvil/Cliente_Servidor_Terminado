package autenticacionusuarios.avance3;

import autenticacionusuarios.avance3.ActualizadorPedidos;
import autenticacionusuarios.avance3.Pedido;
import autenticacionusuarios.avance3.SistemaPedidos;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AutenticacionUsuariosAvance3 {

    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        RestaurantesDB restaurantes = new RestaurantesDB();
        restaurantes.setVisible(true);
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        String opcionPrincipal = JOptionPane.showInputDialog(null, "Bienvenido al sistema\n"
                + "Escoja una opción:\n"
                + "1. Iniciar Sesión\n"
                + "2. Crear cuenta");

        try {
            int eleccionPrincipal = Integer.parseInt(opcionPrincipal);
            switch (eleccionPrincipal) {
                case 1:
                    iniciarSesion(gestionUsuarios);
                    break;
                case 2:
                    crearCuenta(gestionUsuarios);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un número.");
        }
    }
    
    private static void iniciarSesion(GestionUsuarios gestionUsuarios) {
        JOptionPane.showMessageDialog(null, "Iniciando Sesión");
        gestionUsuarios.Login();

        // Conectar con la base de datos
        String url = "jdbc:mysql://localhost:3306/RestaurantesDB";
        String usuario = "root";
        String contrasena = "andy1234";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena); Statement statement = conexion.createStatement(); ResultSet resultado = statement.executeQuery("SELECT * FROM Restaurantes")) {

            System.out.println("\n--- Datos de la tabla Restaurantes ---");
            System.out.println("ID | Nombre Restaurante | Precio Plato | Dirección");
            System.out.println("------------------------------------------------");

            while (resultado.next()) {
                int id = resultado.getInt("ID_Restaurantes");
                String nombre = resultado.getString("Nombre_Restaurante");
                double precio = resultado.getDouble("Precio_Plato");
                String direccion = resultado.getString("Direccion");

                System.out.printf("%d | %s | %.2f | %s%n", id, nombre, precio, direccion);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }

        // Comunicación con el servidor
        try (Socket socket = new Socket(HOST, PUERTO); BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in)); PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor. Escribe un mensaje:");

            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                salida.println(mensaje);
                System.out.println("Respuesta del servidor: " + entrada.readLine());

     
                OrdenadorDePedidos.OrdenProcesada("Pedido de Juan Perez");

        
                String actualizaciones = OrdenadorDePedidos.ObtenerActualizacionDeLaOrden();
                System.out.println(actualizaciones);

                SistemaPedidos sistema = new SistemaPedidos();
                sistema.agregarPedido(new Pedido(1, "Juan Perez", "Pendiente", "2024-12-15"));
                sistema.agregarPedido(new Pedido(2, "Maria Lopez", "Pendiente", "2024-12-20"));
                sistema.agregarPedido(new Pedido(3, "Carlos Garcia", "Pendiente", "2024-12-22"));

                System.out.println("Pedidos iniciales:");
                sistema.mostrarPedidos();

                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new ActualizadorPedidos(sistema));

                try {
                    Thread.sleep(20000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("\nPedidos después de las actualizaciones automáticas:");
                sistema.mostrarPedidos();

                executor.shutdown();
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        }
    }

    private static void crearCuenta(GestionUsuarios gestionUsuarios) {
        JOptionPane.showMessageDialog(null, "Creando Cuenta");
        gestionUsuarios.SingUp();

        String opcionVerDatos = JOptionPane.showInputDialog(null, "¿Desea ver los datos de la cuenta?\n"
                + "1. Sí\n"
                + "2. No");

        try {
            int eleccionVerDatos = Integer.parseInt(opcionVerDatos);
            if (eleccionVerDatos == 1) {
                JOptionPane.showMessageDialog(null, "Mostrando datos de la cuenta creada");
                gestionUsuarios.VerDatosCuentaCreada();

                String opcionModificar = JOptionPane.showInputDialog("\u00bfDesea modificar algún dato?\n"
                        + "1. Sí\n"
                        + "2. No");

                int eleccionModificar = Integer.parseInt(opcionModificar);
                if (eleccionModificar == 1) {
                    String opcionDato = JOptionPane.showInputDialog("\u00bfQué dato desea modificar?\n"
                            + "1. ID\n"
                            + "2. Nombre de usuario\n"
                            + "3. Contraseña");

                    int eleccionDato = Integer.parseInt(opcionDato);
                    switch (eleccionDato) {
                        case 1:
                            gestionUsuarios.ModificarID();
                            break;
                        case 2:
                            gestionUsuarios.ModificarNombre();
                            break;
                        case 3:
                            gestionUsuarios.ModificarContrasena();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                    }
                    JOptionPane.showMessageDialog(null, "Dato modificado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se modificaron datos, se continuará");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Continuando con el sistema");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un número.");
        }
    }
}
