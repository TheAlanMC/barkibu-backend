package bo.edu.ucb.barkibu.entity;

import java.util.Date;

public class PetInfo {
    private Integer petId;
    private String name;
    private String specie;
    private String breed;
    private String photoPath;
    private Date born_date;
    private String chip_number;

    public PetInfo(){
    }

    public PetInfo(Integer petId, String name, String specie, String breed, String photoPath, Date born_date, String chip_number) {
        this.petId = petId;
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.photoPath = photoPath;
        this.born_date = born_date;
        this.chip_number = chip_number;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
        return "PetInfo{" +
                "name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", born_date=" + born_date +
                ", chip_number='" + chip_number + '\'' +
                '}';
    }
}
