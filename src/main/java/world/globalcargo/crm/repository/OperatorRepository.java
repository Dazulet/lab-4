package world.globalcargo.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.globalcargo.crm.entity.Operators;

@Repository
public interface OperatorRepository extends JpaRepository<Operators, Long> {
}
