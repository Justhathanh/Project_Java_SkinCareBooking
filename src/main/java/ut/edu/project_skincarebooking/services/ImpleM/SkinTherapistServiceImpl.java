package ut.edu.project_skincarebooking.services.ImpleM;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ut.edu.project_skincarebooking.exceptions.SkinTherapistNotFoundException;
import ut.edu.project_skincarebooking.models.SkinTherapist;
import ut.edu.project_skincarebooking.repositories.SkinTherapistRepository;
import ut.edu.project_skincarebooking.services.interF.SkinTherapistService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SkinTherapistServiceImpl implements SkinTherapistService {
    private final SkinTherapistRepository therapistRepository;

    @Override
    public List<SkinTherapist> getAllTherapists() {
        return therapistRepository.findAll();
    }

    @Override
    public SkinTherapist getTherapistById(Long id) {
        return therapistRepository.findById(id)
                .orElseThrow(() -> new SkinTherapistNotFoundException("Skin Therapist not found with ID: " + id));
    }

    @Override
    public SkinTherapist addTherapist(SkinTherapist therapist) {
        return therapistRepository.save(therapist);
    }

    @Override
    public SkinTherapist updateTherapist(Long id, SkinTherapist therapist) {
        SkinTherapist existingTherapist = getTherapistById(id);
        existingTherapist.setName(therapist.getName());
        existingTherapist.setSpecialization(therapist.getSpecialization());
        existingTherapist.setWorkSchedule(therapist.getWorkSchedule());
        return therapistRepository.save(existingTherapist);
    }

    @Override
    public void deleteTherapist(Long id) {
        SkinTherapist therapist = getTherapistById(id);
        therapistRepository.delete(therapist);
    }

    @Override
    public Map<String, String> getTherapistSchedule(Long id) {
        SkinTherapist therapist = getTherapistById(id);
        return therapist.getWorkSchedule();
    }
}
