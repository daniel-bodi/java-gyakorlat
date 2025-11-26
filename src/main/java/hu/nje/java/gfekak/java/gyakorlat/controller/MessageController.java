package hu.nje.java.gfekak.java.gyakorlat.controller;

import hu.nje.java.gfekak.java.gyakorlat.data.Message;
import hu.nje.java.gfekak.java.gyakorlat.data.MessageRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

/**
 * @author danielbodi
 */
@Controller
public class MessageController {

    private final MessageRepository messageRepo;

    public MessageController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(@Valid @ModelAttribute("message") Message message, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }

        message.setSentAt(LocalDateTime.now());
        messageRepo.save(message);

        model.addAttribute("success", "Üzenete sikeresen elküldve!");

        return "contact_success";
    }

    @GetMapping("/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", messageRepo.findAllByOrderBySentAtDesc());
        return "messages";
    }
}
