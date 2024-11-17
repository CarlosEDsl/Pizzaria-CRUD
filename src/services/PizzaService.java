package services;

import dao.PizzaDAO;
import model.Pizza;

import java.util.List;

public class PizzaService {

    private final PizzaDAO pizzaDAO;

    public PizzaService() {
        this.pizzaDAO = new PizzaDAO();
    }

    public Pizza createPizza(String name, Double price) {
        return this.pizzaDAO.create(name, price);
    }

    public Pizza getPizzaById(int id) {
        return this.pizzaDAO.read(id)
                .orElseThrow(() -> new IllegalArgumentException("Pizza com ID " + id + " não encontrada."));
    }

    public boolean updatePizza(int id, String name, Double price) {
        boolean updated = this.pizzaDAO.update(id, name, price);
        if (!updated) {
            System.out.println("Pizza com ID " + id + " não encontrada para atualização.");
        }
        return updated;
    }

    public boolean deletePizza(int id) {
        boolean deleted = this.pizzaDAO.delete(id);
        if (!deleted) {
            System.out.println("Pizza com ID " + id + " não encontrada para exclusão.");
        }
        return deleted;
    }

    public List<Pizza> getAllPizzas() {
        return this.pizzaDAO.findAll();
    }
}
