package ut.edu.project_skincarebooking.controllers_for_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import ut.edu.project_skincarebooking.services.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Lấy danh sách tất cả dịch vụ
    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAllServices() {
        List<ServiceEntity> serviceEntities = serviceService.getAllServices();
        return ResponseEntity.ok(serviceEntities);
    }

    // Lấy dịch vụ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable Long id) {
        ServiceEntity serviceEntity = serviceService.getServiceById(id);
        return ResponseEntity.ok(serviceEntity);
    }

    // Thêm mới dịch vụ
    @PostMapping
    public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceEntity serviceEntity) {
        ServiceEntity newServiceEntity = serviceService.createService(serviceEntity);
        return ResponseEntity.ok(newServiceEntity);
    }

    // Cập nhật thông tin dịch vụ
    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateService(@PathVariable Long id, @RequestBody ServiceEntity serviceEntity) {
        ServiceEntity updatedServiceEntity = serviceService.updateService(id, serviceEntity);
        return ResponseEntity.ok(updatedServiceEntity);
    }

    // Xóa dịch vụ theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok("Service deleted successfully.");
    }
}
