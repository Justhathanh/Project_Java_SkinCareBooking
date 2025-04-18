package ut.edu.project_skincarebooking.dto.SkinTherapistDTO;

public class deleteSkinTherapistDTO {
    private Long id;

    public deleteSkinTherapistDTO() {}

    public deleteSkinTherapistDTO(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}

