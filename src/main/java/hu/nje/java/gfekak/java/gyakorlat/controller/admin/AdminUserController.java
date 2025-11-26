package hu.nje.java.gfekak.java.gyakorlat.controller.admin;

import hu.nje.java.gfekak.java.gyakorlat.data.User;
import hu.nje.java.gfekak.java.gyakorlat.data.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author danielbodi
 */
@Controller
@RequestMapping("/admin/users")
@Secured("ROLE_ADMIN")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user_list";
    }

    @PostMapping("/role/{id}")
    public String changeRole(@PathVariable Integer id, @RequestParam String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található: " + id));
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/admin/users";
    }
}
