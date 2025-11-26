package hu.nje.java.gfekak.java.gyakorlat.controller.rest;

import hu.nje.java.gfekak.java.gyakorlat.data.Category;
import hu.nje.java.gfekak.java.gyakorlat.data.CategoryRepository;
import hu.nje.java.gfekak.java.gyakorlat.data.Pizza;
import hu.nje.java.gfekak.java.gyakorlat.data.PizzaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author danielbodi
 */
@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    private final PizzaRepository pizzaRepository;
    private final CategoryRepository categoryRepository;

    public PizzaRestController(PizzaRepository pizzaRepository, CategoryRepository categoryRepository) {
        this.pizzaRepository = pizzaRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Pizza> readAll() {
        return pizzaRepository.findAll();
    }

    @PostMapping
    public Pizza create(@RequestBody CreatePizzaRequest createPizzaRequest) {
        Pizza pizza = new Pizza();
        pizza.setName(createPizzaRequest.getName());
        pizza.setVegetarian(createPizzaRequest.isVegetarian());

        Category category = categoryRepository.findById(createPizzaRequest.getCategory())
                .orElseThrow(() -> new RuntimeException("Category with name [%s] not found"
                        .formatted(createPizzaRequest.getCategory())));
        pizza.setCategory(category);

        return pizzaRepository.save(pizza);
    }

    @GetMapping("/{name}")
    public Pizza read(@PathVariable("name") String name) {
        return pizzaRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Pizza with name [%s] not found".formatted(name)));
    }

    @PutMapping("/{name}")
    public Pizza update(@PathVariable("name") String name, @RequestBody UpdatePizzaRequest updatePizzaRequest) {
        Pizza pizza = pizzaRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Pizza with name [%s] not found".formatted(name)));

        pizza.setVegetarian(updatePizzaRequest.isVegetarian());

        Category category = categoryRepository.findById(updatePizzaRequest.getCategory())
                .orElseThrow(() -> new RuntimeException("Category with name [%s] not found"
                        .formatted(updatePizzaRequest.getCategory())));
        pizza.setCategory(category);

        return pizzaRepository.save(pizza);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        pizzaRepository.deleteById(name);
    }
}
