package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberRequest {

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("noHp")
    @Expose
    private String phone;

    @SerializedName("biaya")
    @Expose
    private Integer cost;

    @SerializedName("interval")
    @Expose
    private Integer interval;

    @SerializedName("tanggal_mulai")
    @Expose
    private String startDate;

    public MemberRequest(String name, String phone, Integer cost, Integer interval, String startDate) {
        this.name = name;
        this.phone = phone;
        this.cost = cost;
        this.interval = interval;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
