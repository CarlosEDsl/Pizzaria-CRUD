package services;

import model.DIATRABALHO;
import model.Pedido;
import dao.DIATRABALHODAO;

import java.time.LocalDate;

public class DIATRABALHOService {

    private final DIATRABALHODAO diatrabalhoDAO;

    public DIATRABALHOService() {
        this.diatrabalhoDAO = new DIATRABALHODAO();
    }

    public DIATRABALHO createDia(Pedido[] pedidos, LocalDate data) {
        return this.diatrabalhoDAO.create(pedidos, data);
    }

    public DIATRABALHO getDia(LocalDate data) {
        DIATRABALHO diatrabalho = this.diatrabalhoDAO.read(data);
        if (diatrabalho == null) {
            System.out.println("Dia de trabalho com Data " + data + " não encontrado.");
        }
        return diatrabalho;
    }

    public DIATRABALHO updateDia(int id, Pedido[] pedidos, LocalDate data) {
        DIATRABALHO updatedDayWork = this.diatrabalhoDAO.update(id, pedidos, data);
        if (updatedDayWork == null) {
            System.out.println("Dia de trabalho com ID " + id + " não encontrado para atualização.");
        }
        return updatedDayWork;
    }

    public boolean deleteDia(int id) {
        boolean deleted = this.diatrabalhoDAO.delete(id);
        if (!deleted) {
            System.out.println("Dia de trabalho com ID " + id + " não encontrado para exclusão.");
        }
        return deleted;
    }

    public void addPedido(Pedido pedido, LocalDate date) {
        this.diatrabalhoDAO.addPedido(pedido, date);
    }

    public DIATRABALHO pedidoPorDia(LocalDate date) {
        return this.diatrabalhoDAO.read(date);
    }

}
