package autenticacionusuarios.avance3;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActualizadorPedidos implements Runnable {
    private SistemaPedidos sistema;

    public ActualizadorPedidos(SistemaPedidos sistema) {
        this.sistema = sistema;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); 

                int id = (int) (Math.random() * 10) + 1;
                sistema.actualizarEstadoPedido(id, "En proceso");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SistemaPedidos sistema = new SistemaPedidos();
        sistema.agregarPedido(new Pedido(1, "Jordan Gauss", "Listo", "2024-12-15"));
        sistema.agregarPedido(new Pedido(2, "Pedro El escamozo", "Pendiente", "2024-12-20"));

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new ActualizadorPedidos(sistema));

        sistema.mostrarPedidos();
    }
}
