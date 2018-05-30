package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Anggota implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("noHp")
    @Expose
    private String noHp;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("biaya")
    @Expose
    private Integer biaya;

    @SerializedName("interval")
    @Expose
    private Integer interval;

    @SerializedName("tagihan")
    @Expose
    private Tagihan tagihan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBiaya() {
        return biaya;
    }

    public void setBiaya(Integer biaya) {
        this.biaya = biaya;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Tagihan getTagihan() {
        return tagihan;
    }

    public void setTagihan(Tagihan tagihan) {
        this.tagihan = tagihan;
    }
}
