package by.gourianova.binocularvision.bean;

import java.math.BigDecimal;
import java.util.Objects;

public class App extends Entity {
    private int id;
    private String title;
    private boolean inRent;
    private boolean isAvailable;
    private int typeId;
    private int HttpAddressesId;
    private String description;
    private String http_image1;
    private String http_image2;
    private String http_image3;

    public App() {
    }

    public App(int id, boolean inRent, int typeId, int HttpAddressesId, String description, BigDecimal pricePerHour, String http_image1, String http_image2, String http_image3) {
        this.id = id;
        this.inRent = inRent;
        this.typeId = typeId;
        this.HttpAddressesId = HttpAddressesId;
        this.description = description;
        this.http_image1 = http_image1;
        this.http_image2 = http_image2;
        this.http_image3 = http_image3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInRent() {
        return inRent;
    }

    public void setInRent(boolean inRent) {
        this.inRent = inRent;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getHttpAddressesId() {
        return HttpAddressesId;
    }

    public void setHttpAddressesId(int stationId) {
        this.HttpAddressesId = HttpAddressesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHttp_image1() {
        return http_image1;
    }

    public void setHttp_image1(String http_image1) {
        this.http_image1 = http_image1;
    }

    public String getHttp_image2() {
        return http_image2;
    }

    public void setHttp_image2(String http_image2) {
        this.http_image2 = http_image2;
    }

    public String getHttp_image3() {
        return http_image3;
    }

    public void setHttp_image3(String http_image3) {
        this.http_image3 = http_image3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof App)) return false;
        App app = (App) o;
        return getId() == app.getId() &&
                isInRent() == app.isInRent() &&
                getTypeId() == app.getTypeId() &&
                getHttpAddressesId() == app.getHttpAddressesId() &&
                Objects.equals(getDescription(), app.getDescription()) &&
                //   Objects.equals(getPricePerHour(), app.getPricePerHour()) &&
                Objects.equals(getHttp_image1(), app.getHttp_image1()) &&
                Objects.equals(getHttp_image2(), app.getHttp_image2()) &&
                Objects.equals(getHttp_image3(), app.getHttp_image3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isInRent(), getTypeId(), getHttpAddressesId(), getDescription(),
                //getPricePerHour(),
                getHttp_image1(), getHttp_image2(), getHttp_image3());
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", inRent=" + inRent +
                ", typeId=" + typeId +
                ", stationId=" + HttpAddressesId +
                ", type='" + description + '\'' +
                //     ", pricePerHour=" + pricePerHour +
                ", city='" + http_image1 + '\'' +
                ", location='" + http_image2 + '\'' +
                ", picture='" + http_image3 + '\'' +
                "} " + super.toString();
    }
}