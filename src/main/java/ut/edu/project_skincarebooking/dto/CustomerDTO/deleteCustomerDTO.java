package ut.edu.project_skincarebooking.dto.CustomerDTO;

public class deleteCustomerDTO {
    private Long id;

    public deleteCustomerDTO() {}

    public deleteCustomerDTO(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
