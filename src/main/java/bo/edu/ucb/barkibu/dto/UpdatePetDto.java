package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class UpdatePetDto {
    private String name;
    private String gender;
    private Integer breedId;
    private Boolean castrated;
    private Date born_date;
    private String chip_number;

    public UpdatePetDto(String name, String gender, Integer breedId, Boolean castrated, Date born_date, String chip_number) {
        this.name = name;
        this.gender = gender;
        this.breedId = breedId;
        this.castrated = castrated;
        this.born_date = born_date;
        this.chip_number = chip_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public String getChip_number() {
        return chip_number;
    }

    public void setChip_number(String chip_number) {
        this.chip_number = chip_number;
    }

    @Override
    public String toString() {
        return "UpdatePetDto{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", breedId=" + breedId +
                ", castrated=" + castrated +
                ", born_date=" + born_date +
                ", chip_number='" + chip_number + '\'' +
                '}';
    }
    public boolean validate() {
        if (name == null || name.isEmpty()){
            return false;
        }
        if (gender == null || gender.isEmpty()){
            return false;
        }
        if (breedId == null ){
            return false;
        }
        if (castrated == null ){
            return false;
        }
        if (born_date == null ){
            return false;
        }
        if (chip_number == null || chip_number.isEmpty()){
            return false;
        }

        return true;
    }
}

