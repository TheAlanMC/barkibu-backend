package bo.edu.ucb.barkibu.dto;

import java.util.Date;
import java.util.List;

public class PetDataDto {
    private String name;
    private Date born_date;
    private String chip_number;

    public PetDataDto(){
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

    public String getChip_number() {
        return chip_number;
    }

    public void setChip_number(String chip_number) {
        this.chip_number = chip_number;
    }

    @Override
    public String toString() {
        return "PetDataDto{" +
                "name='" + name + '\'' +
                ", born_date=" + born_date +
                ", chip_number='" + chip_number + '\'' +
                '}';
    }
}
