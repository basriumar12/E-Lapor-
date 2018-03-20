package info.blogbasbas.e_laporcurahhujan.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by User on 09/03/2018.
 */

public class Value implements Serializable {
    @SerializedName("text")
    @Expose
    public String text;
}
