package uns.ac.rs.chemlabinventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.model.Request;
import uns.ac.rs.chemlabinventory.service.RequestService;

import java.util.List;

@Controller
public class RequestController {

        private final RequestService requestService;

        public RequestController(RequestService requestService) {
            this.requestService = requestService;
        }

        @PostMapping("/register")
        public String register(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password) {

            requestService.createRegistrationRequest(fullName, email, password);

            return "redirect:/register.html?success";
        }

    @GetMapping("/api/requests")
    @ResponseBody
    public List<Request> getAllRequests() {
        return requestService.findAll();
    }

    @PostMapping("/api/requests/{id}/approve")
    @ResponseBody
    public void approveRequest(@PathVariable Long id) {
        requestService.approveRegistrationRequest(id);
    }

    @PostMapping("/api/requests/{id}/reject")
    @ResponseBody
    public void rejectRequest(@PathVariable Long id) {
        requestService.rejectRegistrationRequest(id);
    }


    }
