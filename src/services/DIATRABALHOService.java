package services;

import model.DIATRABALHO;
import model.Pedido;
import repositories.DIATRABALHORepository;

import java.time.LocalDate;

public class DIATRABALHOService {

    private final DIATRABALHORepository diatrabalhoRepository;

    public DIATRABALHOService() {
        this.diatrabalhoRepository = new DIATRABALHORepository();
    }

    public DIATRABALHO createDia(Pedido[] pedidos, LocalDate data) {
        return this.diatrabalhoRepository.create(pedidos, data);
    }

    public DIATRABALHO getDia(LocalDate data) {
        DIATRABALHO diatrabalho = this.diatrabalhoRepository.read(data);
        if (diatrabalho == null) {
            System.out.println("Dia de trabalho com Data " + data + " não encontrado.");
        }
        return diatrabalho;
    }

    public DIATRABALHO updateDia(int id, Pedido[] pedidos, LocalDate data) {
        DIATRABALHO updatedDayWork = this.diatrabalhoRepository.update(id, pedidos, data);
        if (updatedDayWork == null) {
            System.out.println("Dia de trabalho com ID " + id + " não encontrado para atualização.");
        }
        return updatedDayWork;
    }

    public boolean deleteDia(int id) {
        boolean deleted = this.diatrabalhoRepository.delete(id);
        if (!deleted) {
            System.out.println("Dia de trabalho com ID " + id + " não encontrado para exclusão.");
        }
        return deleted;
    }

    public void addPedido(Pedido pedido, LocalDate date) {
        this.diatrabalhoRepository.addPedido(pedido, date);
    }

    public DIATRABALHO pedidoPorDia(LocalDate date) {
        return this.diatrabalhoRepository.read(date);
    }

}
