package ut.edu.project_skincarebooking.services;

import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.exceptions.ServiceNotFoundException;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import ut.edu.project_skincarebooking.repositories.ServiceRepository;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceEntity getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found with ID: " + id));
    }

    @Override
    public ServiceEntity createService(ServiceEntity serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    @Override
    public ServiceEntity updateService(Long id, ServiceEntity updatedService) {
        ServiceEntity existingService = getServiceById(id);
        existingService.setServiceName(updatedService.getServiceName());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        return serviceRepository.save(existingService);
    }

    @Override
    public void deleteService(Long id) {
        ServiceEntity serviceEntity = getServiceById(id);
        serviceRepository.delete(serviceEntity);
    }
}
