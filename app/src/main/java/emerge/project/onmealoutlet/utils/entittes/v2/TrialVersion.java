package emerge.project.onmealoutlet.utils.entittes.v2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrialVersion implements Serializable  {

    @SerializedName("code")
    Integer code;

    @SerializedName("error")
    boolean error;

    @SerializedName("message")
    String message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
