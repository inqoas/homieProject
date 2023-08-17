package tw.idv.tibame.tha102.web.violation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.violation.service.ViolationService;
import tw.idv.tibame.tha102.web.violation.vo.Violation;

import java.util.List;

@RestController
@RequestMapping("/violation")
public class ViolationFindByStatusController {
    private final ViolationService violationService;

    @Autowired
    public ViolationFindByStatusController(ViolationService violationService){
        this.violationService = violationService;
    }

    @GetMapping("/find-by-status")
    public List<Violation> getViolationByViolationStatus(@RequestParam("violation_status") Integer violationStatus){
        return violationService.getViolationByViolationStatus(violationStatus);
    }

    @GetMapping("/find-by-id")
    public Violation getViolationById(@RequestParam("violation_id") Integer violationId){
        return violationService.getViolationById(violationId);
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateViolationStatus(@RequestParam("violation_id") Integer violationId) {
        boolean isUpdated = violationService.updateViolationStatus(violationId, 2);
        if (isUpdated) {
            return ResponseEntity.ok("狀態修改成功");
        } else {
            return ResponseEntity.badRequest().body("無此筆資料");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteViolation(@RequestParam("violation_id") Integer violationId) {
        boolean isDeleted = violationService.deleteViolationById(violationId);
        if (isDeleted) {
            return ResponseEntity.ok("刪除成功");
        } else {
            return ResponseEntity.badRequest().body("無此筆資料");
        }
    }

}
