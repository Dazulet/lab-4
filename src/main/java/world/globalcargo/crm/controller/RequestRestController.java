package world.globalcargo.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.crm.entity.Request;
import world.globalcargo.crm.service.RequestService;

import java.util.List;
import java.util.Map;
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
        Request addRequests = requestService.addRequest(request);
        return new ResponseEntity<>(addRequests, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request requestDetails) {
        Request idRequest = requestService.getRequestById(id);
        if (idRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        idRequest.setUserName(requestDetails.getUserName());
        idRequest.setPhone(requestDetails.getPhone());
        idRequest.setCommentary(requestDetails.getCommentary());
        idRequest.setHandled(requestDetails.isHandled());
        idRequest.setCourse(requestDetails.getCourse());

        Request updatedRequest = requestService.addRequest(idRequest);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Request> patchRequest(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Request request = requestService.getRequestById(id);
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (updates.containsKey("userName")) {
            request.setUserName((String) updates.get("userName"));
        }
        if (updates.containsKey("phone")) {
            request.setPhone((String) updates.get("phone"));
        }
        if (updates.containsKey("commentary")) {
            request.setCommentary((String) updates.get("commentary"));
        }
        if (updates.containsKey("handled")) {
            request.setHandled((Boolean) updates.get("handled"));
        }

        Request updatedRequest = requestService.addRequest(request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        Request idRequest = requestService.getRequestById(id);
        if (idRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }
}