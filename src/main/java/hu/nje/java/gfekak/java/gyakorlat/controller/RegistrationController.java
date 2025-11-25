package hu.nje.java.gfekak.java.gyakorlat.controller;

import hu.nje.java.gfekak.java.gyakorlat.data.User;
import hu.nje.java.gfekak.java.gyakorlat.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author danielbodi
 */
@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/registration")
    public String form(Model model) {
        model.addAttribute("reg", new User());
        return "registration";
    }

    @PostMapping("/registration_process")
    public String registration(@ModelAttribute User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            model.addAttribute("uzenet", "A regisztrációs email már foglalt!");
            return "registration_failure";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_Vendeg");
        userRepository.save(user);

        model.addAttribute("id", user.getId());

        return "registration_success";
    }
}
