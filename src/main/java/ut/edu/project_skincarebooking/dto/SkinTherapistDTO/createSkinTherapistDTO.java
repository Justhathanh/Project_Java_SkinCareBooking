package ut.edu.project_skincarebooking.dto.SkinTherapistDTO;

public class createSkinTherapistDTO {
    private String name;
    private String specialty;
    private String phone;

    public createSkinTherapistDTO() {
    }

    public createSkinTherapistDTO(String name, String specialty, String phone) {
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

