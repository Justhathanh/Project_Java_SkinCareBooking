package ut.edu.project_skincarebooking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.SkinTherapist;
import ut.edu.project_skincarebooking.services.SkinTherapistService;

import java.util.List;

@RestController
@RequestMapping("/api/therapists")
public class SkinTherapistController {
    private final SkinTherapistService skinTherapistService;

    public SkinTherapistController(SkinTherapistService skinTherapistService) {
        this.skinTherapistService = skinTherapistService;
    }

    // Lấy danh sách tất cả chuyên viên trị liệu
    @GetMapping
    public ResponseEntity<List<SkinTherapist>> getAllTherapists() {
        List<SkinTherapist> therapists = skinTherapistService.getAllTherapists();
        return ResponseEntity.ok(therapists);
    }

    // Lấy chuyên viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SkinTherapist> getTherapistById(@PathVariable Long id) {
        SkinTherapist therapist = skinTherapistService.getTherapistById(id);
        return ResponseEntity.ok(therapist);
    }

    // Thêm mới chuyên viên
    @PostMapping
    public ResponseEntity<SkinTherapist> addTherapist(@RequestBody SkinTherapist therapist) {
        SkinTherapist newTherapist = skinTherapistService.addTherapist(therapist);
        return ResponseEntity.ok(newTherapist);
    }

    // Cập nhật thông tin chuyên viên
    @PutMapping("/{id}")
    public ResponseEntity<SkinTherapist> updateTherapist(@PathVariable Long id, @RequestBody SkinTherapist therapist) {
        SkinTherapist updatedTherapist = skinTherapistService.updateTherapist(id, therapist);
        return ResponseEntity.ok(updatedTherapist);
    }

    // Xóa chuyên viên theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTherapist(@PathVariable Long id) {
        skinTherapistService.deleteTherapist(id);
        return ResponseEntity.ok("Skin Therapist deleted successfully.");
    }
}
