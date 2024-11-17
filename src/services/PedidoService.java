package services;

import dao.PedidoDAO;
import model.DIATRABALHO;
import model.Pedido;
import model.Pizza;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PedidoService {

    private final PedidoDAO pedidoDAO;
    private final DIATRABALHOService diatrabalhoService;

    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
        this.diatrabalhoService = new DIATRABALHOService();
    }

    public Pedido createPedido(int cliente, LocalDateTime data, Pizza[] pizzas) {

        DIATRABALHO dia = this.diatrabalhoService.pedidoPorDia(data.toLocalDate());
        Pedido pedido = this.pedidoDAO.create(cliente, data, pizzas);

        if(dia == null) {
            Pedido[] pedidos = new Pedido[1];
            pedidos[0] = pedido;
            this.diatrabalhoService.createDia(pedidos, data.toLocalDate());
        } else {
            this.diatrabalhoService.addPedido(pedido, data.toLocalDate());
        }

        return pedido;
    }

    public Optional<Pedido> getPedidoById(int id) {
        try {
            return this.pedidoDAO.read(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Pedido updatePedido(int id, int cliente, LocalDateTime data, Pizza[] pizzas) {
        Pedido updatedPedido = this.pedidoDAO.update(id, cliente, data, pizzas);
        if (updatedPedido == null) {
            System.out.println("Pedido com ID " + id + " não encontrado para atualização.");
        }
        return updatedPedido;
    }

    public void deletePedido(int id) {
        this.pedidoDAO.delete(id);
    }

    public DIATRABALHO getPedidosDia(LocalDate date) {
        return this.diatrabalhoService.pedidoPorDia(date);
    }

    public List<Pedido> getPedidosPorCliente(int client_id) {
        return this.pedidoDAO.getPedidosPorCliente(client_id);
    }


}
