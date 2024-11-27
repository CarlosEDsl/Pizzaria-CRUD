package dao;

import model.DIATRABALHO;
import model.Pedido;
import model.Pizza;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class OrderDAO {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final WorkedDay workedDay;
    private int id = 0;

    public OrderDAO(WorkedDay workedDay) {
        this.workedDay = workedDay;
    }

    public Pedido create(int cliente, LocalDateTime data, Pizza[] pizzas) {
        this.id++;
        Pedido pedido = new Pedido(this.id, cliente, data, pizzas);
        DIATRABALHO dia = this.workedDay.pedidoPorDia(data.toLocalDate());

        if(dia == null) {
            Pedido[] pedidos = new Pedido[1];
            pedidos[0] = pedido;
            this.workedDay.create(pedidos, data.toLocalDate());
        } else {
            this.workedDay.addPedido(pedido, data.toLocalDate());
        }

        this.pedidos.add(pedido);
        return pedido;
    }

    public Optional<Pedido> read(int id) {
        return this.pedidos.stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst();
    }

    public Pedido update(int id, int cliente, LocalDateTime data, Pizza[] pizzas) {
        int pedidoIndex = IntStream.range(0, this.pedidos.size())
                .filter(i -> this.pedidos.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (pedidoIndex != -1) {
            Pedido updatedPedido = new Pedido(id, cliente, data, pizzas);
            this.pedidos.set(pedidoIndex, updatedPedido);
            return this.pedidos.get(pedidoIndex);
        }
        return null;
    }

    public boolean delete(int id) {
        return this.pedidos.removeIf(pedido -> pedido.getId() == id);
    }

    public List<Pedido> getPedidosPorCliente(int clientId) {
        return this.pedidos.stream()
                .filter(pedido -> pedido.getCliente() == clientId)
                .toList();
    }

    public List<Pedido> findAll() {
        return new ArrayList<>(this.pedidos); // Retorna uma cópia da lista
    }
}
