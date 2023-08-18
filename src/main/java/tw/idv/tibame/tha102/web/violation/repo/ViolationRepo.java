package tw.idv.tibame.tha102.web.violation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.violation.vo.Violation;

import java.util.List;

@Repository
public interface ViolationRepo extends JpaRepository<Violation, Integer> {
    List<Violation> findByViolationStatus(Integer violationStatus);
}
