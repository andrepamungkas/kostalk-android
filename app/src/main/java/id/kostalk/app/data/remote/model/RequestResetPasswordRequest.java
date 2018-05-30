package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResetPasswordRequest {

    @SerializedName("kunci")
    @Expose
    private String kunci;

    @SerializedName("kode")
    @Expose
    private String kode;

    public RequestResetPasswordRequest(String kunci) {
        this.kunci = kunci;
    }

    public RequestResetPasswordRequest(String kunci, String kode) {
        this.kunci = kunci;
        this.kode = kode;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
