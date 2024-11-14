import model.DIATRABALHO;
import model.Pedido;
import model.Pizza;

import services.PedidoService;
import services.PizzaService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PizzariaAPP {

    public static void main(String[] args) {

            PizzaService pizzaService = new PizzaService();
            PedidoService pedidoService = new PedidoService();

            System.out.println("Criando pizzas");
            Pizza pizza1 = pizzaService.createPizza("Pizza Calabreza", 25.0);
            Pizza pizza2 = pizzaService.createPizza("Pizza Bacon", 30.0);
            Pizza pizza3 = pizzaService.createPizza("Pizza Quatro queijos", 35.0);
            System.out.println(pizza1.toString());
            System.out.println(pizza2.toString());
            System.out.println(pizza3.toString());


            System.out.println("\nCriando pedidos");
            Pedido pedido1 = pedidoService.createPedido(1, LocalDateTime.now(), new Pizza[]{pizza1, pizza2});
            Pedido pedido2 = pedidoService.createPedido(2, LocalDateTime.now().plusHours(1), new Pizza[]{pizza2, pizza3});
            Pedido pedido3 = pedidoService.createPedido(3, LocalDateTime.now().plusHours(2), new Pizza[]{pizza1, pizza3});
            Pedido pedido4 = pedidoService.createPedido(1, LocalDateTime.now().plusDays(2), new Pizza[]{pizza1, pizza3});

            System.out.println("\nConsultando pedidos");
            System.out.println(pedidoService.getPedidoById(1).toString());
            System.out.println(pedidoService.getPedidoById(2).toString());
            System.out.println(pedidoService.getPedidoById(3).toString());

            System.out.println("\nVendo pedidos do dia");
            DIATRABALHO dia1 = pedidoService.getPedidosDia(LocalDate.now());
            DIATRABALHO dia2 = pedidoService.getPedidosDia(LocalDate.now().plusDays(2));
            System.out.println(dia1.toString());
            System.out.println(dia2.toString());

            System.out.println("\nVendo pedidos por cliente");
            System.out.println(pedidoService.getPedidosPorCliente(1));

    }
}


