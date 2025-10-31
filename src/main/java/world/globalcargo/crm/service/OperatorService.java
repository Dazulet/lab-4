package world.globalcargo.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.globalcargo.crm.entity.Operators;

import world.globalcargo.crm.repository.OperatorRepository;

import java.util.List;

@Service
public class OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;
    public Operators addOperator(Operators operator) {
        return operatorRepository.save(operator);
    }
    public List<Operators> getAllOperators() {
        return operatorRepository.findAll();
    }

}
