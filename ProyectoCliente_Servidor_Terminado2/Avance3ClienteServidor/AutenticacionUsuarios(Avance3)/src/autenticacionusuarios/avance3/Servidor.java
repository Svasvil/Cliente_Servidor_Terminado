package autenticacionusuarios.avance3;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 12345;
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor abierto " + PUERTO);
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                
                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + mensajeCliente);
                    salida.println("Respuesta del servidor: " + mensajeCliente.toUpperCase());
                }
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
