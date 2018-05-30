package id.kostalk.app.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuildingRequest {

    public BuildingRequest() {
    }

    public static class Add {

        @SerializedName("name")
        @Expose
        private String name;

        public Add(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Update {

        @SerializedName("name")
        @Expose
        private String name;

        public Update(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
