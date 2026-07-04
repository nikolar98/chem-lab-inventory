package uns.ac.rs.chemlabinventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.chemlabinventory.model.Request;
import uns.ac.rs.chemlabinventory.service.RequestService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/api/requests/chemical-usage")
    @ResponseBody
    public ResponseEntity<Request> createChemicalUsageRequest(@RequestBody Map<String, Object> body,
                                                              Principal principal) {

        Long chemicalBatchId = Long.valueOf(body.get("chemicalBatchId").toString());
        BigDecimal requestedQuantity = new BigDecimal(body.get("requestedQuantity").toString());
        String purpose = body.get("purpose").toString();
        String comment = body.get("comment") != null ? body.get("comment").toString() : "";

        Request request = requestService.createChemicalUsageRequest(
                chemicalBatchId,
                requestedQuantity,
                purpose,
                comment,
                principal.getName()
        );

        return ResponseEntity.ok(request);
    }

    @GetMapping("/api/requests")
    @ResponseBody
    public List<Request> getAllRequests() {
        return requestService.findAll();
    }

    @GetMapping("/api/requests/my")
    @ResponseBody
    public List<Request> getMyRequests(Principal principal) {
        return requestService.findByUserEmail(principal.getName());
    }

    @PostMapping("/api/requests/{id}/approve")
    @ResponseBody
    public void approveRequest(@PathVariable Long id) {
        requestService.approveRequest(id);
    }

    @PostMapping("/api/requests/{id}/reject")
    @ResponseBody
    public void rejectRequest(@PathVariable Long id) {
        requestService.rejectRequest(id);
    }
}