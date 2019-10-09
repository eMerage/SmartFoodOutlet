package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rider implements Serializable {

    @SerializedName("id")
    int id;


    @SerializedName("name")
    String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
