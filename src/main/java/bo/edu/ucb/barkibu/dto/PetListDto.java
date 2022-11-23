package bo.edu.ucb.barkibu.dto;

import java.util.Date;

public class PetListDto {
    private String name;
    private Date born_date;
    private String breed;
    private String specie;
    private String chip_number;
    private String photoPath;
    public PetListDto(){

    }

    public PetListDto(String name, Date born_date, String breed, String specie, String chip_number, String photoPath) {
        this.name = name;
        this.born_date = born_date;
        this.breed = breed;
        this.specie = specie;
        this.chip_number = chip_number;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getChip_number() {
        return chip_number;
    }

    public void setChip_number(String chip_number) {
        this.chip_number = chip_number;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "PetListDto{" +
                "name='" + name + '\'' +
                ", born_date=" + born_date +
                ", breed='" + breed + '\'' +
                ", specie='" + specie + '\'' +
                ", chip_number='" + chip_number + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
