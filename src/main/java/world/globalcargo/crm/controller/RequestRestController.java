package world.globalcargo.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.crm.entity.Request;
import world.globalcargo.crm.service.RequestService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/requests")
public class RequestRestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Request request = requestService.getRequestById(id);
        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Request> addRequest(@RequestBody Request request) {
        Request savedRequest = requestService.addRequest(request);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request requestDetails) {
        Request existingRequest = requestService.getRequestById(id);
        if (existingRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingRequest.setHandled(requestDetails.isHandled());

        Request updatedRequest = requestService.addRequest(existingRequest);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{requestId}/assign-operator/{operatorId}")
    public ResponseEntity<Request> assignOperator(@PathVariable Long requestId, @PathVariable Long operatorId) {
        requestService.assignOperator(requestId, operatorId);
        Request updatedRequest = requestService.getRequestById(requestId);
        if (updatedRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedRequest);
    }
}