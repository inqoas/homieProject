package tw.idv.tibame.tha102.web.violation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.violation.repo.ViolationRepo;
import tw.idv.tibame.tha102.web.violation.vo.Violation;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationService {
    private final ViolationRepo violationRepo;

    @Autowired
    public ViolationService(ViolationRepo violationRepo){
        this.violationRepo = violationRepo;
    }

    public List<Violation> getViolationByViolationStatus(Integer violationStatus){
        return violationRepo.findByViolationStatus(violationStatus);
    }

    public Violation getViolationById(Integer id){
        return violationRepo.findById(id).orElse(null);
    }

    public boolean updateViolationStatus(Integer violationId, Integer violationStatus) {
        Optional<Violation> optionalViolation = violationRepo.findById(violationId);
        if (optionalViolation.isPresent()) {
            Violation violation = optionalViolation.get();
            violation.setViolationStatus(violationStatus);
            violationRepo.save(violation);
            return true;
        }
        return false;
    }
    public boolean deleteViolationById(Integer violationId) {
        if (violationRepo.existsById(violationId)) {
            violationRepo.deleteById(violationId);
            return true;
        } else {
            return false;
        }
    }
}
