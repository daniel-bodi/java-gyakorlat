package hu.nje.java.gfekak.java.gyakorlat.controller;

import hu.nje.java.gfekak.java.gyakorlat.data.Pizza;
import hu.nje.java.gfekak.java.gyakorlat.data.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author danielbodi
 */
@Controller
public class ChartController {

    private final PizzaRepository pizzaRepo;

    public ChartController(PizzaRepository pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        List<Pizza> pizzas = pizzaRepo.findAll();

        Map<String, Long> categoryCounts = pizzas.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCategory().getName(),
                        Collectors.counting()
                ));

        model.addAttribute("labels", categoryCounts.keySet());
        model.addAttribute("data", categoryCounts.values());

        return "chart";
    }
}
