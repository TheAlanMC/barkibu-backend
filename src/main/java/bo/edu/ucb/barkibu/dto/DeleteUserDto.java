package bo.edu.ucb.barkibu.dto;

public class DeleteUserDto {
    private String status;

    private DeleteUserDto(){
    }

    public DeleteUserDto(String status) {
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
        return "DeleteUserDto{" +
                "status='" + status + '\'' +
                '}';
    }
}
