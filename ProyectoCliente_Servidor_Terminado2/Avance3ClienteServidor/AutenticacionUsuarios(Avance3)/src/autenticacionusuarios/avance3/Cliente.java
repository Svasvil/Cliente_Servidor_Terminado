
package autenticacionusuarios.avance3;

 import java.io.*;
import java.net.*;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor. Escribe tu pedido");

            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                salida.println(mensaje);
                System.out.println("Pedido realizado con exito ");
                System.out.println("Respuesta del servidor: " + entrada.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}