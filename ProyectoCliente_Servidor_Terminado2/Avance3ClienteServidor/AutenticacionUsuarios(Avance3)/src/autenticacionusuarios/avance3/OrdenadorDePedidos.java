/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autenticacionusuarios.avance3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrdenadorDePedidos {
    // Mapa concurrente para almacenar los pedidos.
    // La clave es el ID del pedido y el valor son los detalles del pedido.
    // Utilizando ConcurrentHashMap garantizamos acceso seguro y concurrente al mapa.

    private static final Map<Integer, String> orders = new ConcurrentHashMap<>();

    //Contador estático para generar IDs de pedidos únicos.
    private static int orderId = 1;

    // procesa un nuevo pedido.    
    // la solicitud del pedido en formato de cadena.
    // este método es sincronizado para garantizar que solo un hilo pueda procesar un pedido a la vez,
    // evitando así conflictos de concurrencia.
    // se extraen los detalles del pedido del string de la solicitud y se almacenan en el mapa.
    public static synchronized void OrdenProcesada(String request) {
        // Genera un nuevo ID de pedido y lo incrementa para el próximo pedido.
        orders.put(orderId++, request.split(" ", 2)[1]);
    }

    //Obtiene una cadena con las actualizaciones de todos los pedidos.
    // Iteramos sobre el mapa de pedidos y construimos una cadena con los detalles de cada pedido.
    //@return Una cadena con las actualizaciones de los pedidos.
    public static String ObtenerActualizacionDeLaOrden() {
        StringBuilder updates = new StringBuilder("Actualizaciones de pedidos:\n");
        for (Map.Entry<Integer, String> entry : orders.entrySet()) {
            updates.append("Pedido ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return updates.toString();
    }
}
