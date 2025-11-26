package hu.nje.java.gfekak.java.gyakorlat.controller.admin;

import hu.nje.java.gfekak.java.gyakorlat.data.Category;
import hu.nje.java.gfekak.java.gyakorlat.data.CategoryRepository;
import hu.nje.java.gfekak.java.gyakorlat.data.Pizza;
import hu.nje.java.gfekak.java.gyakorlat.data.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author danielbodi
 */
@Controller
@RequestMapping("/admin/pizza")
@Secured("ROLE_ADMIN")
public class AdminPizzaController {

    private final PizzaRepository pizzaRepository;
    private final CategoryRepository categoryRepository;

    public AdminPizzaController(PizzaRepository pizzaRepository, CategoryRepository categoryRepository) {
        this.pizzaRepository = pizzaRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listPizzas(Model model) {
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "admin/pizza_list";
    }

    @GetMapping("/new")
    public String newPizzaForm(Model model) {
        model.addAttribute("pizza", new PizzaForm());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/pizza_create";
    }

    @PostMapping("/create")
    public String createPizza(@Valid @ModelAttribute("pizza") PizzaForm pizzaForm,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/pizza_create";
        }

        Pizza pizza = new Pizza();
        pizza.setName(pizzaForm.getName());
        pizza.setVegetarian(pizzaForm.isVegetarian());

        Category category = categoryRepository.findById(pizzaForm.getCategory())
                .orElseThrow(() -> new RuntimeException("Category with name [%s] not found"
                        .formatted(pizzaForm.getCategory())));
        pizza.setCategory(category);

        pizzaRepository.save(pizza);

        return "redirect:/admin/pizza";
    }

    @GetMapping("/edit/{name}")
    public String editPizzaForm(@PathVariable String name, Model model) {
        Pizza pizza = pizzaRepository.findById(name).orElseThrow(() -> new IllegalArgumentException("Pizza nem található: " + name));
        model.addAttribute("pizza", pizza);
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/pizza_edit";
    }

    @PostMapping("/update/{name}")
    public String updatePizza(@PathVariable String name, @Valid @ModelAttribute("pizza") PizzaForm pizzaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/pizza_edit";
        }

        Pizza pizza = pizzaRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Pizza with name [%s] not found".formatted(name)));
        pizza.setName(pizzaForm.getName());
        pizza.setVegetarian(pizzaForm.isVegetarian());

        Category category = categoryRepository.findById(pizzaForm.getCategory())
                .orElseThrow(() -> new RuntimeException("Category with name [%s] not found"
                        .formatted(pizzaForm.getCategory())));
        pizza.setCategory(category);

        pizzaRepository.save(pizza);

        return "redirect:/admin/pizza";
    }

    @GetMapping("/delete/{name}")
    public String deletePizza(@PathVariable String name) {
        pizzaRepository.deleteById(name);
        return "redirect:/admin/pizza";
    }
}
