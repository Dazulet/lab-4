package world.globalcargo.crm.controller;

import world.globalcargo.crm.entity.Request;
import world.globalcargo.crm.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/")
    public String allRequests(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        model.addAttribute("pageTitle", "Все Заявки");
        return "index";
    }

    @GetMapping("/new")
    public String newRequests(Model model) {
        model.addAttribute("requests", requestService.getNewRequests());
        model.addAttribute("pageTitle", "Новые Заявки");
        return "index";
    }

    @GetMapping("/handled")
    public String handledRequests(Model model) {
        model.addAttribute("requests", requestService.getHandledRequests());
        model.addAttribute("pageTitle", "Обработанные заявки");
        return "index";
    }

    @GetMapping("/add-request")
    public String showAddRequestForm(Model model) {
        model.addAttribute("request", new Request());
        return "add-request";
    }

    @PostMapping("/add-request")
    public String addRequest(@ModelAttribute("request") Request request) {
        requestService.addRequest(request);
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String requestDetails(@PathVariable Long id, Model model) {
        Request request = requestService.getRequestById(id);
        if (request != null) {
            model.addAttribute("request", request);
            return "details";
        }
        return "redirect:/";
    }

    @PostMapping("/handle/{id}")
    public String handleRequest(@PathVariable Long id) {
        requestService.handleRequest(id);
        return "redirect:/details/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return "redirect:/";
    }
}