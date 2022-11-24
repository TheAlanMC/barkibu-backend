package bo.edu.ucb.barkibu.dto;

public class DeletePetDto {
    private String status;

    public DeletePetDto(){
    }

    public DeletePetDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeletePetDto{" +
                "status='" + status + '\'' +
                '}';
    }
}
