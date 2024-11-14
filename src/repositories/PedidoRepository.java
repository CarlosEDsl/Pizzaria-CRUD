    package repositories;

    import model.Pedido;
    import model.Pizza;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.stream.IntStream;

    public class PedidoRepository {

        private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        private int id = 0;

        public PedidoRepository() {

        }

        public Pedido create(int cliente, LocalDateTime data, Pizza[] pizzas) {
            this.id++;

            Pedido pedido = new Pedido(this.id, cliente, data,pizzas);

            this.pedidos.add(pedido);
            return pedido;
        }

        public Pedido read(int id) {
            return this.pedidos.stream()
                    .filter(pedido -> pedido.getId() == id)
                    .toList()
                    .getFirst();
        }

        public Pedido update(int id, int cliente, LocalDateTime data, Pizza[] pizzas) {

            Pedido pedido = new Pedido(id, cliente, data, pizzas);

            int pedidoIndex = IntStream.range(0, this.pedidos.size())
                    .filter(i -> this.pedidos.get(i).getId() == pedido.getId())
                    .findFirst()
                    .orElse(-1);
            if (pedidoIndex != -1) {
                this.pedidos.set(pedidoIndex, pedido);
                return pedido;
            } else {
                return null;
            }
        }

        public void delete(int id) {

            Pedido pedido = this.pedidos.stream()
                    .filter(p -> p.getId() == id)
                    .toList()
                    .getFirst();
            this.pedidos.remove(pedido);
        }

        public List<Pedido> getPedidosPorCliente(int client_id) {
            return this.pedidos.stream()
                    .filter(p -> p.getCliente() == client_id)
                    .toList();
        }


    }
