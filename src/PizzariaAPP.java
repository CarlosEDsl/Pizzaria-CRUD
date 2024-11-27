import dao.OrderDAO;
import dao.PizzaDAO;
import dao.WorkedDay;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PizzariaAPP {

        public static void main(String[] args) {
                PizzaDAO pizzaDAO = new PizzaDAO();
                WorkedDay workedDayDAO = new WorkedDay();
                OrderDAO orderDAO = new OrderDAO(workedDayDAO);
                Scanner scanner = new Scanner(System.in);

                int option;

                do {
                        System.out.println("\n=== Select Your Option ===");
                        System.out.println("1. Create/Modify/Search Pizza");
                        System.out.println("2. Create/Modify/Search Order");
                        System.out.println("3. Search Worked Day");
                        System.out.println("4. Exit");
                        System.out.print("Choose an option: ");
                        option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                                case 1 -> managePizzas(scanner, pizzaDAO);
                                case 2 -> manageOrders(scanner, orderDAO, pizzaDAO);
                                case 3 -> manageWorkedDay(scanner, workedDayDAO);
                                case 4 -> System.out.println("Goodbye...");
                                default -> System.out.println("Invalid option, please try again.");
                        }
                } while (option != 3);
                scanner.close();
        }

        private static void managePizzas(Scanner scanner, PizzaDAO pizzaDAO) {
                int option;
                do {
                        System.out.println("\n=== Pizza Management ===");
                        System.out.println("1. Create Pizza");
                        System.out.println("2. Read Pizza by ID");
                        System.out.println("3. Update Pizza");
                        System.out.println("4. Delete Pizza");
                        System.out.println("5. List All Pizzas");
                        System.out.println("6. Exit");
                        System.out.print("Choose an option: ");
                        option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                                case 1 -> createPizza(scanner, pizzaDAO);
                                case 2 -> readPizza(scanner, pizzaDAO);
                                case 3 -> updatePizza(scanner, pizzaDAO);
                                case 4 -> deletePizza(scanner, pizzaDAO);
                                case 5 -> listAllPizzas(pizzaDAO);
                                case 6 -> System.out.println("Exiting pizza management...");
                                default -> System.out.println("Invalid option. Please try again.");
                        }
                } while (option != 6);
        }

        private static void createPizza(Scanner scanner, PizzaDAO pizzaDAO) {
                int option;
                System.out.println("Select Pizza Type");
                System.out.println("\n1. Catupiri");
                System.out.println("2. Frango");
                Pizza pizza;
                option = scanner.nextInt();
                if(option == 1) {
                        System.out.println("\nCatupiri selecionado\n");
                        pizza = new PizzaCatupiri();
                } else {
                        System.out.println("\nFrango selecionado\n");
                        pizza = new PizzaFrango();
                }


                System.out.print("Enter pizza name: ");
                pizza.setName(scanner.nextLine());

                System.out.print("Enter pizza ingredients: ");
                pizza.setIngredients(scanner.nextLine());

                System.out.print("Enter pizza price: ");
                pizza.setPrice(scanner.nextDouble());

                System.out.print("Enter pizza amount: ");
                pizza.setAmount(scanner.nextInt());
                scanner.nextLine();

                Pizza createdPizza = pizzaDAO.create(pizza);
                System.out.println("Pizza created successfully with ID: " + createdPizza.getId());
        }

        private static void readPizza(Scanner scanner, PizzaDAO pizzaDAO) {
                System.out.print("Enter pizza ID to read: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Optional<Pizza> pizza = pizzaDAO.read(id);
                if (pizza.isPresent()) {
                        System.out.println("Pizza found: " + pizza.get());
                } else {
                        System.out.println("Pizza with ID " + id + " not found.");
                }
        }

        private static void updatePizza(Scanner scanner, PizzaDAO pizzaDAO) {
                System.out.print("Enter pizza ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Optional<Pizza> existingPizza = pizzaDAO.read(id);
                if (existingPizza.isEmpty()) {
                        System.out.println("Pizza with ID " + id + " not found.");
                        return;
                }

                Pizza pizza = existingPizza.get();

                System.out.print("Enter new pizza name (current: " + pizza.getName() + "): ");
                pizza.setName(scanner.nextLine());

                System.out.print("Enter new pizza ingredients (current: " + pizza.getIngredients() + "): ");
                pizza.setIngredients(scanner.nextLine());

                System.out.print("Enter new pizza price (current: " + pizza.getPrice() + "): ");
                pizza.setPrice(scanner.nextDouble());

                System.out.print("Enter new pizza amount (current: " + pizza.getAmount() + "): ");
                pizza.setAmount(scanner.nextInt());
                scanner.nextLine();

                boolean updated = pizzaDAO.update(pizza);
                if (updated) {
                        System.out.println("Pizza updated successfully.");
                } else {
                        System.out.println("Failed to update pizza.");
                }
        }

        private static void deletePizza(Scanner scanner, PizzaDAO pizzaDAO) {
                System.out.print("Enter pizza ID to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                boolean deleted = pizzaDAO.delete(id);
                if (deleted) {
                        System.out.println("Pizza deleted successfully.");
                } else {
                        System.out.println("Pizza with ID " + id + " not found.");
                }
        }

        private static void listAllPizzas(PizzaDAO pizzaDAO) {
                List<Pizza> pizzas = pizzaDAO.findAll();
                if (pizzas.isEmpty()) {
                        System.out.println("No pizzas found.");
                } else {
                        System.out.println("\n--- List of Pizzas ---");
                        pizzas.forEach(System.out::println);
                }
        }

        private static void manageOrders(Scanner scanner, OrderDAO orderDAO, PizzaDAO pizzaDAO) {
                int option;
                do {
                        System.out.println("\n=== Order Management ===");
                        System.out.println("1. Create Order");
                        System.out.println("2. Read Order by ID");
                        System.out.println("3. Update Order");
                        System.out.println("4. Delete Order");
                        System.out.println("5. List All Orders");
                        System.out.println("6. Exit");
                        System.out.print("Choose an option: ");
                        option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                                case 1 -> createOrder(scanner, orderDAO, pizzaDAO);
                                case 2 -> readOrder(scanner, orderDAO);
                                case 3 -> updateOrder(scanner, orderDAO, pizzaDAO);
                                case 4 -> deleteOrder(scanner, orderDAO);
                                case 5 -> listAllOrders(orderDAO);
                                case 6 -> System.out.println("Exiting order management...");
                                default -> System.out.println("Invalid option. Please try again.");
                        }
                } while (option != 6);
        }

        private static void createOrder(Scanner scanner, OrderDAO orderDAO, PizzaDAO pizzaDAO) {
                System.out.print("Enter client ID: ");
                int clientId = scanner.nextInt();
                scanner.nextLine();

                List<Pizza> pizzas = pizzaDAO.findAll();
                if (pizzas.isEmpty()) {
                        System.out.println("No pizzas available. Create pizzas first.");
                        return;
                }

                System.out.println("Available pizzas:");
                pizzas.forEach(System.out::println);

                System.out.print("Enter pizza IDs for the order (comma-separated): ");
                String[] pizzaIds = scanner.nextLine().split(",");
                Pizza[] selectedPizzas = new Pizza[pizzaIds.length];

                for (int i = 0; i < pizzaIds.length; i++) {
                        int pizzaId = Integer.parseInt(pizzaIds[i].trim());
                        selectedPizzas[i] = pizzaDAO.read(pizzaId).orElse(null);
                }

                Pedido pedido = orderDAO.create(clientId, LocalDateTime.now(), selectedPizzas);
                System.out.println("Order created successfully with ID: " + pedido.getId());
        }

        private static void readOrder(Scanner scanner, OrderDAO orderDAO) {
                System.out.print("Enter order ID to read: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Optional<Pedido> pedido = orderDAO.read(id);
                if (pedido.isPresent()) {
                        System.out.println("Order found: " + pedido.get());
                } else {
                        System.out.println("Order with ID " + id + " not found.");
                }
        }

        private static void updateOrder(Scanner scanner, OrderDAO orderDAO, PizzaDAO pizzaDAO) {
                System.out.print("Enter order ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Optional<Pedido> existingOrder = orderDAO.read(id);
                if (existingOrder.isEmpty()) {
                        System.out.println("Order with ID " + id + " not found.");
                        return;
                }

                System.out.print("Enter new client ID: ");
                int clientId = scanner.nextInt();
                scanner.nextLine();

                List<Pizza> pizzas = pizzaDAO.findAll();
                System.out.println("Available pizzas:");
                pizzas.forEach(System.out::println);

                System.out.print("Enter new pizza IDs for the order (comma-separated): ");
                String[] pizzaIds = scanner.nextLine().split(",");
                Pizza[] selectedPizzas = new Pizza[pizzaIds.length];

                for (int i = 0; i < pizzaIds.length; i++) {
                        int pizzaId = Integer.parseInt(pizzaIds[i].trim());
                        selectedPizzas[i] = pizzaDAO.read(pizzaId).orElse(null);
                }

                Pedido updatedPedido = orderDAO.update(id, clientId, LocalDateTime.now(), selectedPizzas);
                System.out.println("Order updated successfully.");
        }

        private static void deleteOrder(Scanner scanner, OrderDAO orderDAO) {
                System.out.print("Enter order ID to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                boolean deleted = orderDAO.delete(id);
                if (deleted) {
                        System.out.println("Order deleted successfully.");
                } else {
                        System.out.println("Order with ID " + id + " not found.");
                }
        }

        private static void listAllOrders(OrderDAO orderDAO) {
                List<Pedido> pedidos = orderDAO.findAll();
                if (pedidos.isEmpty()) {
                        System.out.println("No orders found.");
                } else {
                        System.out.println("\n--- List of Orders ---");
                        pedidos.forEach(System.out::println);
                }
        }

        private static void manageWorkedDay(Scanner scanner, WorkedDay workedDayDAO) {
                int option;
                do {
                        System.out.println("\n=== Worked Day Management ===");
                        System.out.println("1. Read Worked Day by Date");
                        System.out.println("2. Delete Worked Day");
                        System.out.println("3. List All Worked Days");
                        System.out.println("4. Exit");
                        System.out.print("Choose an option: ");
                        option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                                case 1 -> readWorkedDay(scanner, workedDayDAO);
                                case 2 -> deleteWorkedDay(scanner, workedDayDAO);
                                case 3 -> listAllWorkedDays(workedDayDAO);
                                case 4 -> System.out.println("Exiting Worked Day Management...");
                                default -> System.out.println("Invalid option. Please try again.");
                        }
                } while (option != 4);
        }

        private static void readWorkedDay(Scanner scanner, WorkedDay workedDayDAO) {
                System.out.print("Enter the date (yyyy-MM-dd) to search: ");
                LocalDate date = LocalDate.parse(scanner.nextLine());

                DIATRABALHO diaTrabalho = workedDayDAO.read(date);
                if (diaTrabalho != null) {
                        System.out.println("Worked Day found: " + diaTrabalho);
                } else {
                        System.out.println("No Worked Day found for the given date.");
                }
        }

        private static void deleteWorkedDay(Scanner scanner, WorkedDay workedDayDAO) {
                System.out.print("Enter the ID of the Worked Day to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                boolean success = workedDayDAO.delete(id);
                if (success) {
                        System.out.println("Worked Day deleted successfully.");
                } else {
                        System.out.println("Worked Day with ID " + id + " not found.");
                }
        }

        private static void listAllWorkedDays(WorkedDay workedDayDAO) {
                List<DIATRABALHO> workedDays = workedDayDAO.findAll();
                if (workedDays.isEmpty()) {
                        System.out.println("No Worked Days found.");
                } else {
                        System.out.println("\n--- List of Worked Days ---");
                        workedDays.forEach(System.out::println);
                }
        }

        private static void managePizza(Scanner scanner) {
                System.out.println("Pizza management is not yet implemented in this example.");
        }

}

