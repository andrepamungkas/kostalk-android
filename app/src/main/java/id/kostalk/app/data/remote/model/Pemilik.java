package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pemilik {

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

    @SerializedName("saldo")
    @Expose
    private String saldo;

    public Pemilik(String nama, String noHp, String email) {
        this.nama = nama;
        this.noHp = noHp;
        this.email = email;
    }

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

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
