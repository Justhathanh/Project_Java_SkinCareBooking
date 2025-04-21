package ut.edu.project_skincarebooking.controller_for_web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ut.edu.project_skincarebooking.dto.ServiceDTO.ServiceCreateRequest;
import ut.edu.project_skincarebooking.dto.ServiceDTO.ServiceDetailsResponse;
import ut.edu.project_skincarebooking.dto.ServiceDTO.ServiceResponse;
import ut.edu.project_skincarebooking.dto.ServiceDTO.ServiceUpdateRequest;
import ut.edu.project_skincarebooking.exceptions.ServiceNotFoundException;
import ut.edu.project_skincarebooking.models.ServiceEntity;
import ut.edu.project_skincarebooking.services.interF.ServiceService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceWebController {

    private final ServiceService serviceService;

    @GetMapping
    public String getAllServices(Model model) {
        List<ServiceEntity> services = serviceService.getAllServices();
        List<ServiceResponse> serviceResponses = services.stream()
                .map(this::mapToServiceResponse)
                .collect(Collectors.toList());

        model.addAttribute("services", serviceResponses);
        return "service"; // Use existing template path
    }

    @GetMapping("/{id}")
    public String getServiceDetails(@PathVariable Long id, Model model) {
        try {
            ServiceEntity service = serviceService.getServiceById(id);
            ServiceDetailsResponse serviceDetails = mapToServiceDetailsResponse(service);

            model.addAttribute("service", serviceDetails);
            return "service/details";
        } catch (ServiceNotFoundException e) {
            model.addAttribute("errorMessage", "Dịch vụ không tồn tại: " + e.getMessage());
            return "redirect:/service";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("serviceCreateRequest", new ServiceCreateRequest());
        return "service/create";
    }

    @PostMapping("/create")
    public String createService(@ModelAttribute ServiceCreateRequest request, RedirectAttributes redirectAttributes) {
        try {
            ServiceEntity service = mapCreateRequestToEntity(request);
            serviceService.createService(service);
            redirectAttributes.addFlashAttribute("successMessage", "Dịch vụ đã được tạo thành công!");
            return "redirect:/service";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi tạo dịch vụ: " + e.getMessage());
            return "redirect:/service/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            ServiceEntity service = serviceService.getServiceById(id);
            ServiceUpdateRequest updateRequest = mapEntityToUpdateRequest(service);

            model.addAttribute("serviceId", id);
            model.addAttribute("serviceUpdateRequest", updateRequest);
            return "service/edit";
        } catch (ServiceNotFoundException e) {
            model.addAttribute("errorMessage", "Dịch vụ không tồn tại: " + e.getMessage());
            return "redirect:/service";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateService(
            @PathVariable Long id,
            @ModelAttribute ServiceUpdateRequest request,
            RedirectAttributes redirectAttributes) {
        try {
            ServiceEntity service = mapUpdateRequestToEntity(request);
            serviceService.updateService(id, service);
            redirectAttributes.addFlashAttribute("successMessage", "Dịch vụ đã được cập nhật thành công!");
            return "redirect:/service";
        } catch (ServiceNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Dịch vụ không tồn tại: " + e.getMessage());
            return "redirect:/service";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật dịch vụ: " + e.getMessage());
            return "redirect:/service/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        try {
            ServiceEntity service = serviceService.getServiceById(id);
            model.addAttribute("service", mapToServiceResponse(service));
            model.addAttribute("serviceId", id);
            return "service/delete";
        } catch (ServiceNotFoundException e) {
            model.addAttribute("errorMessage", "Dịch vụ không tồn tại: " + e.getMessage());
            return "redirect:/service";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            serviceService.deleteService(id);
            redirectAttributes.addFlashAttribute("successMessage", "Dịch vụ đã được xóa thành công!");
        } catch (ServiceNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Dịch vụ không tồn tại: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa dịch vụ: " + e.getMessage());
        }
        return "redirect:/service";
    }

    // Helper methods for mapping entities to DTOs
    private ServiceResponse mapToServiceResponse(ServiceEntity service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .duration(service.getDuration())
                .build();
    }

    private ServiceDetailsResponse mapToServiceDetailsResponse(ServiceEntity service) {
        return ServiceDetailsResponse.builder()
                .id(service.getId())
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .duration(service.getDuration())
                .therapistCount(service.getTherapists() != null ? service.getTherapists().size() : 0)
                .appointmentCount(service.getAppointments() != null ? service.getAppointments().size() : 0)
                .build();
    }

    // Helper methods for cleaner mapping
    private ServiceEntity mapCreateRequestToEntity(ServiceCreateRequest request) {
        return ServiceEntity.builder()
                .serviceName(request.getServiceName())
                .description(request.getDescription())
                .price(request.getPrice())
                .duration(request.getDuration())
                .build();
    }

    private ServiceUpdateRequest mapEntityToUpdateRequest(ServiceEntity service) {
        return new ServiceUpdateRequest(
                service.getServiceName(),
                service.getDescription(),
                service.getPrice(),
                service.getDuration()
        );
    }

    private ServiceEntity mapUpdateRequestToEntity(ServiceUpdateRequest request) {
        return ServiceEntity.builder()
                .serviceName(request.getServiceName())
                .description(request.getDescription())
                .price(request.getPrice())
                .duration(request.getDuration())
                .build();
    }
}