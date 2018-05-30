package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("kunci")
    @Expose
    private String kunci;

    public LoginRequest(String kunci) {
        this.kunci = kunci;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }
}
