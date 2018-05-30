package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("pemilik")
    @Expose
    private Pemilik pemilik;

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("otp")
    @Expose
    private Otp otp;

    @SerializedName("data")
    @Expose
    private List<Building> buildingList = null;

    @SerializedName("anggotaList")
    @Expose
    private List<Anggota> anggotas = null;

    @SerializedName("histories")
    @Expose
    private List<History> histories = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pemilik getPemilik() {
        return pemilik;
    }

    public void setPemilik(Pemilik pemilik) {
        this.pemilik = pemilik;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Otp getOtp() {
        return otp;
    }

    public void setOtp(Otp otp) {
        this.otp = otp;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<Anggota> getAnggotas() {
        return anggotas;
    }

    public void setAnggotas(List<Anggota> anggotas) {
        this.anggotas = anggotas;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
