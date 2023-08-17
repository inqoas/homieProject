package tw.idv.tibame.tha102.web.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.emp.vo.Emp;

import java.util.Optional;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Integer> {
    Optional<Emp> findByEmpEmail(String empEmail);
    Optional<Emp> findByResetToken(String resetToken);
}
