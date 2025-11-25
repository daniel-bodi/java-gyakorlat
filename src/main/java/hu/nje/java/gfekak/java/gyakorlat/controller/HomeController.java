package hu.nje.java.gfekak.java.gyakorlat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author danielbodi
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String user() {
        return "user";
    }

    @GetMapping("/admin/home")
    public String admin() {
        return "admin";
    }
}
