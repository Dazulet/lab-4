package world.globalcargo.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.globalcargo.crm.entity.Courses;
@Repository
public interface CourseRepository extends JpaRepository<Courses,Long> {
}
