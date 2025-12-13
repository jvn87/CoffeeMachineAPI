package com.coffeemachine.api.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "coffees")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private String intensity;
    private boolean available;
    private String body;
    private String sweetness;
    private String acidity;
    private String bitterness;
    @Column(length = 1000)
    private String description;
    private String origin;
    private String aroma;
    private String caffeineLevel;
    private String type;
    private String beanVariety;

    // Constructors
    public Coffee() {}

    public Coffee(String name, BigDecimal price, String intensity, boolean available, String body,
                  String sweetness, String acidity, String bitterness, String description,
                  String origin, String aroma, String caffeineLevel, String type, String beanVariety) {
        this.name = name;
        this.price = price;
        this.intensity = intensity;
        this.available = available;
        this.body = body;
        this.sweetness = sweetness;
        this.acidity = acidity;
        this.bitterness = bitterness;
        this.description = description;
        this.origin = origin;
        this.aroma = aroma;
        this.caffeineLevel = caffeineLevel;
        this.type = type;
        this.beanVariety = beanVariety;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSweetness() {
        return sweetness;
    }

    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }

    public String getBitterness() {
        return bitterness;
    }

    public void setBitterness(String bitterness) {
        this.bitterness = bitterness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getCaffeineLevel() {
        return caffeineLevel;
    }

    public void setCaffeineLevel(String caffeineLevel) {
        this.caffeineLevel = caffeineLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeanVariety() {
        return beanVariety;
    }

    public void setBeanVariety(String beanVariety) {
        this.beanVariety = beanVariety;
    }
}
