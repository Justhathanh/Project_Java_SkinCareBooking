package ut.edu.project_skincarebooking.services.interF;

import ut.edu.project_skincarebooking.models.ServiceEntity;

import java.util.List;

public interface ServiceService {
    List<ServiceEntity> getAllServices();
    ServiceEntity getServiceById(Long id);
    ServiceEntity createService(ServiceEntity serviceEntity);
    ServiceEntity updateService(Long id, ServiceEntity serviceEntity);
    void deleteService(Long id);
}
