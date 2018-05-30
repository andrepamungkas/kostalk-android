package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("newPassword")
    @Expose
    private String newPassword;

    public ResetPasswordRequest(String phone, String code, String newPassword) {
        this.phone = phone;
        this.code = code;
        this.newPassword = newPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
