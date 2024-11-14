package model;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Pedido {

    private int id;
    private int cliente;
    private Pizza[] pizzas;
    private LocalDateTime data;

    public Pedido(int id, int cliente, LocalDateTime data, Pizza[] pizzas) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.pizzas = pizzas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setPizzas(Pizza[] pizzas) {
        this.pizzas = pizzas;
    }

    public Pizza[] getPizzas() {
        return this.pizzas;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", pizzas=" + Arrays.toString(pizzas) +
                ", data=" + data +
                '}';
    }
}
