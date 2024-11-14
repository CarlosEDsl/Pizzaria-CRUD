package repositories;

import model.Pizza;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class PizzaRepository {

    private final ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private int id = 0;

    public PizzaRepository() {

    }

    public Pizza create(String name, Double price) {
        this.id++;
        Pizza pizza = new Pizza(this.id, name, price);

        this.pizzas.add(pizza);
        return pizza;
    }

    public Pizza read(int id) {
        return this.pizzas.stream()
                .filter(p -> p.getId() == id)
                .toList().getFirst();
    }

    public Pizza update(int id, String name, Double price) {
        Pizza pizza = new Pizza(id, name, price);

        int pizzaIndex = IntStream.range(0, this.pizzas.size())
                .filter(i -> this.pizzas.get(i).getId() == pizza.getId())
                .findFirst()
                .orElse(-1);

        if(pizzaIndex != -1) {
            pizzas.set(pizzaIndex, pizza);
        } else {
            return null;
        }
        return pizza;
    }

    public void delete(int id) {
        int pizzaIndex = IntStream.range(0, this.pizzas.size())
                .filter(i -> this.pizzas.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if(pizzaIndex != -1) {
            pizzas.remove(pizzaIndex);
        }
    }

}
