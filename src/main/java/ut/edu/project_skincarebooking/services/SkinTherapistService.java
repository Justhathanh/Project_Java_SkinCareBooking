package ut.edu.project_skincarebooking.services;

import ut.edu.project_skincarebooking.models.SkinTherapist;
import java.util.List;
import java.util.Map;

public interface SkinTherapistService {
    List<SkinTherapist> getAllTherapists();
    SkinTherapist getTherapistById(Long id);
    SkinTherapist addTherapist(SkinTherapist therapist);
    SkinTherapist updateTherapist(Long id, SkinTherapist therapist);
    void deleteTherapist(Long id);
    Map<String, String> getTherapistSchedule(Long id);
}
