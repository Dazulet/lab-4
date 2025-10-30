package world.globalcargo.crm.service;

import world.globalcargo.crm.entity.Operators;
import world.globalcargo.crm.entity.Request;
import world.globalcargo.crm.repository.OperatorRepository;
import world.globalcargo.crm.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAllByOrderByIdDesc();
    }

    public List<Request> getNewRequests() {
        return requestRepository.findByHandledFalseOrderByIdDesc();
    }

    public List<Request> getHandledRequests() {
        return requestRepository.findByHandledTrueOrderByIdDesc();
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public Request addRequest(Request request) {
        return requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public void handleRequest(Long id) {
        Request request = getRequestById(id);
        if (request != null && !request.isHandled()) {
            request.setHandled(true);
            requestRepository.save(request);
        }
    }


    public void assignOperator(Long requestId, Long operatorId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        Operators operator = operatorRepository.findById(operatorId).orElse(null);

        if (request != null && operator != null) {
            if (!request.getOperators().contains(operator)) {
                request.getOperators().add(operator);
                request.setHandled(true);
                requestRepository.save(request);
            }
        }
    }
    public void unassignOperator(Long requestId, Long operatorId) {
      Request request = requestRepository.findById(requestId).orElse(null);
        Operators operator = operatorRepository.findById(operatorId).orElse(null);

        if (request != null && operator != null) {
            request.getOperators().remove(operator);
            if (request.getOperators().isEmpty()) {
                request.setHandled(false);
            }
            requestRepository.save(request);
        }
    }

}