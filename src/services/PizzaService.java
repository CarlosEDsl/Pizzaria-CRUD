package services;

import model.Pizza;
import repositories.PizzaRepository;

public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService() {
        this.pizzaRepository = new PizzaRepository();
    }

    public Pizza createPizza(String name, Double price) {
        return this.pizzaRepository.create(name, price);
    }

    public Pizza getPizzaById(int id) {
        Pizza pizza = this.pizzaRepository.read(id);
        if (pizza == null) {
            System.out.println("Pizza com ID " + id + " não encontrada.");
        }
        return pizza;
    }

    public Pizza updatePizza(int id, String name, Double price) {
        Pizza updatedPizza = this.pizzaRepository.update(id, name, price);
        if (updatedPizza == null) {
            System.out.println("Pizza com ID " + id + " não encontrada para atualização.");
        }
        return updatedPizza;
    }

    public boolean deletePizza(int id) {
        Pizza pizza = this.pizzaRepository.read(id);
        if (pizza != null) {
            this.pizzaRepository.delete(id);
            return true;
        } else {
            System.out.println("Pizza com ID " + id + " não encontrada para exclusão.");
            return false;
        }
    }
}
