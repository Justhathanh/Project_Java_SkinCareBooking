package ut.edu.project_skincarebooking.dto.SkinTherapistDTO;

public class updateSkinTherapistDTO {
    private Long id;
    private String name;
    private String specialty;
    private String phone;

    public updateSkinTherapistDTO() {}

    public updateSkinTherapistDTO(Long id, String name, String specialty, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

