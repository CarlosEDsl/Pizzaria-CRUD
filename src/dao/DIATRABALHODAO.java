package dao;

import model.DIATRABALHO;
import model.Pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class DIATRABALHODAO {

    private final List<DIATRABALHO> diasTrabalho = new ArrayList<>();
    private int id = 0;

    public DIATRABALHODAO() {}

    public DIATRABALHO create(Pedido[] pedidos, LocalDate data) {
        this.id++;
        DIATRABALHO diaTrabalho = new DIATRABALHO(this.id, pedidos, data);
        this.diasTrabalho.add(diaTrabalho);
        return diaTrabalho;
    }



    public DIATRABALHO read(LocalDate data) {
        Optional<DIATRABALHO> diaTrabalho = this.diasTrabalho.stream()
                .filter(d -> d.getData().equals(data))
                .findFirst();
        return diaTrabalho.orElse(null);
    }

    public DIATRABALHO update(int id, Pedido[] pedidos, LocalDate data) {
        int diaIndex = IntStream.range(0, this.diasTrabalho.size())
                .filter(i -> this.diasTrabalho.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (diaIndex != -1) {
            DIATRABALHO diaTrabalho = this.diasTrabalho.get(diaIndex);
            diaTrabalho.setPedidos(pedidos);
            diaTrabalho.setData(data);
            return diaTrabalho;
        } else {
            return null;
        }
    }

    public boolean delete(int id) {
        int diaIndex = IntStream.range(0, this.diasTrabalho.size())
                .filter(i -> this.diasTrabalho.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (diaIndex != -1) {
            this.diasTrabalho.remove(diaIndex);
            return true;
        }
        return false;
    }

    public void addPedido(Pedido pedido, LocalDate data) {
        Optional<DIATRABALHO> diaTrabalhoOptional = this.diasTrabalho.stream()
                .filter(dia -> dia.getData().equals(data))
                .findFirst();

        if (diaTrabalhoOptional.isPresent()) {
            DIATRABALHO diaTrabalho = diaTrabalhoOptional.get();

            Pedido[] pedidosExistentes = diaTrabalho.getPedidos();
            Pedido[] novosPedidos = new Pedido[pedidosExistentes.length + 1];
            System.arraycopy(pedidosExistentes, 0, novosPedidos, 0, pedidosExistentes.length);
            novosPedidos[pedidosExistentes.length] = pedido;

            diaTrabalho.setPedidos(novosPedidos);
        } else {
            Pedido[] novoArrayPedidos = new Pedido[1];
            novoArrayPedidos[0] = pedido;
            this.create(novoArrayPedidos, data);
        }
    }

    public List<DIATRABALHO> findAll() {
        return new ArrayList<>(this.diasTrabalho);
    }
}
