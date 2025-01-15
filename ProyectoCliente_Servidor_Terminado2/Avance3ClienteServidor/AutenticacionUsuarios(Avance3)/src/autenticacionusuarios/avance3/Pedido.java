package autenticacionusuarios.avance3;

public class Pedido {

    private int id;
    private String cliente;
    private String estado;
    private String fechaEntrega;
    
    public Pedido(int id, String cliente, String estado, String fechaEntrega) {
        this.id = id;
        this.cliente = cliente;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
    }
    
    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "Pedido ID: " + id + ", Cliente: " + cliente + ", Estado: " + estado + ", Fecha de entrega: " + fechaEntrega;
    }
}
