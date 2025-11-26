package hu.nje.java.gfekak.java.gyakorlat.controller;

import hu.nje.java.gfekak.java.gyakorlat.data.CategoryRepository;
import hu.nje.java.gfekak.java.gyakorlat.data.OrderRepository;
import hu.nje.java.gfekak.java.gyakorlat.data.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author danielbodi
 */
@Controller
public class DatabaseController {

    private final PizzaRepository pizzaRepo;
    private final CategoryRepository categoryRepo;
    private final OrderRepository orderRepo;

    public DatabaseController(PizzaRepository pizzaRepo, CategoryRepository categoryRepo, OrderRepository orderRepo) {
        this.pizzaRepo = pizzaRepo;
        this.categoryRepo = categoryRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/database")
    public String showDatabase(Model model) {
        model.addAttribute("pizzas", pizzaRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("orders", orderRepo.findAll());
        return "database";
    }
}
