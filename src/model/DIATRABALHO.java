package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class DIATRABALHO {

    private int id;
    private Pedido[] pedidos;
    private LocalDate data;

    public DIATRABALHO(int id, Pedido[] pedidos, LocalDate data) {
        this.id = id;
        this.pedidos = pedidos;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido[] getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedido[] pedidos) {
        this.pedidos = pedidos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DIATRABALHO{" +
                "id=" + id +
                ", pedidos=" + Arrays.toString(pedidos) +
                ", data=" + data +
                '}';
    }
}
