package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tagihan implements Serializable  {

    @SerializedName("jumlah")
    @Expose
    private Integer cost;

    @SerializedName("mulai")
    @Expose
    private String start;

    @SerializedName("akhir")
    @Expose
    private String end;

    @SerializedName("status")
    @Expose
    private String status;

    public Tagihan(Integer cost, String start, String end) {
        this.cost = cost;
        this.start = start;
        this.end = end;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
