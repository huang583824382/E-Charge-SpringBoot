package com.example.echarge.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "commodity", schema = "echarge", catalog = "")
public class CommodityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_id")
    private int itemId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "type")
    private Integer type;
    @Basic
    @Column(name = "pub_id")
    private Integer pubId;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "tags")
    private String tags;
    @Basic
    @Column(name = "figure_urls")
    private String figureUrls;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "release_time")
    private Timestamp releaseTime;
    @Basic
    @Column(name = "state")
    private Integer state;
    @Basic
    @Column(name = "place")
    private String place;
    @Basic
    @Column(name = "time_expected")
    private String timeExpected;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFigureUrls() {
        return figureUrls;
    }

    public void setFigureUrls(String figureUrls) {
        this.figureUrls = figureUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTimeExpected() {
        return timeExpected;
    }

    public void setTimeExpected(String timeExpected) {
        this.timeExpected = timeExpected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityEntity commodity = (CommodityEntity) o;

        if (itemId != commodity.itemId) return false;
        if (title != null ? !title.equals(commodity.title) : commodity.title != null) return false;
        if (type != null ? !type.equals(commodity.type) : commodity.type != null) return false;
        if (pubId != null ? !pubId.equals(commodity.pubId) : commodity.pubId != null) return false;
        if (price != null ? !price.equals(commodity.price) : commodity.price != null) return false;
        if (tags != null ? !tags.equals(commodity.tags) : commodity.tags != null) return false;
        if (figureUrls != null ? !figureUrls.equals(commodity.figureUrls) : commodity.figureUrls != null) return false;
        if (description != null ? !description.equals(commodity.description) : commodity.description != null)
            return false;
        if (releaseTime != null ? !releaseTime.equals(commodity.releaseTime) : commodity.releaseTime != null)
            return false;
        if (state != null ? !state.equals(commodity.state) : commodity.state != null) return false;
        if (place != null ? !place.equals(commodity.place) : commodity.place != null) return false;
        if (timeExpected != null ? !timeExpected.equals(commodity.timeExpected) : commodity.timeExpected != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (pubId != null ? pubId.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (figureUrls != null ? figureUrls.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseTime != null ? releaseTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (timeExpected != null ? timeExpected.hashCode() : 0);
        return result;
    }
}
