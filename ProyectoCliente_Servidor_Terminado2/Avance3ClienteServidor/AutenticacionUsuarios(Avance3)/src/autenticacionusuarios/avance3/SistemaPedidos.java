package autenticacionusuarios.avance3;

import java.util.ArrayList;
import java.util.List;

public class SistemaPedidos {

    private List<Pedido> pedidos;

    public SistemaPedidos() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void actualizarEstadoPedido(int id, String nuevoEstado) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                pedido.setEstado(nuevoEstado);
                System.out.println("Pedido actualizado: " + pedido);
                return;
            }
        }
        System.out.println("Pedido con ID " + id + " no encontrado.");
    }

    public void mostrarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
}
