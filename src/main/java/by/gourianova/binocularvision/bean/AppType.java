package by.gourianova.binocularvision.bean;

import java.math.BigDecimal;
import java.util.Objects;

public class AppType extends Entity {
    private int id;
    private String type;
    private BigDecimal pricePerHour;
    private String image;

    public AppType() {
    }

    public AppType(int id, String type, BigDecimal pricePerHour, String image) {
        this.id = id;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppType)) return false;
        AppType appType = (AppType) o;
        return getId() == appType.getId() &&
                Objects.equals(getType(), appType.getType()) &&
                Objects.equals(getPricePerHour(), appType.getPricePerHour()) &&
                Objects.equals(getImage(), appType.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getPricePerHour(), getImage());
    }

    @Override
    public String toString() {
        return "AppType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + pricePerHour +
                ", image='" + image + '\'' +
                "} " + super.toString();
    }
}
