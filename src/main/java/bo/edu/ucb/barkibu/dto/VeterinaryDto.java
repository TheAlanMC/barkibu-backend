package bo.edu.ucb.barkibu.dto;

import java.math.BigDecimal;

public class VeterinaryDto {
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String description;

    public VeterinaryDto() {
    }

    public VeterinaryDto(String name, String address, BigDecimal latitude, BigDecimal longitude, String description) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VeterinaryDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean validate() {
        if (this.name == null || this.name.isEmpty()) {
            return false;
        }
        if (this.address == null || this.address.isEmpty()) {
            return false;
        }
        if (this.latitude == null) {
            return false;
        }
        if (this.longitude == null) {
            return false;
        }
        if (this.description == null || this.description.isEmpty()) {
            return false;
        }
        return true;
    }
}
