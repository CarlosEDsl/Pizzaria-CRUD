package dao;

import model.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class PizzaDAO {

    private final List<Pizza> pizzas = new ArrayList<>();
    private int id = 0;

    public Pizza create(String name, Double price) {
        this.id++;
        Pizza pizza = new Pizza(this.id, name, price);
        this.pizzas.add(pizza);
        return pizza;
    }

    public Optional<Pizza> read(int id) {
        return this.pizzas.stream()
                .filter(pizza -> pizza.getId() == id)
                .findFirst();
    }

    public boolean update(int id, String name, Double price) {
        int pizzaIndex = IntStream.range(0, this.pizzas.size())
                .filter(i -> this.pizzas.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (pizzaIndex != -1) {
            Pizza updatedPizza = new Pizza(id, name, price);
            this.pizzas.set(pizzaIndex, updatedPizza);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        int pizzaIndex = IntStream.range(0, this.pizzas.size())
                .filter(i -> this.pizzas.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (pizzaIndex != -1) {
            this.pizzas.remove(pizzaIndex);
            return true;
        }
        return false;
    }

    public List<Pizza> findAll() {
        return new ArrayList<>(this.pizzas);
    }
}