package info.blogbasbas.e_laporcurahhujan.json;

import android.renderscript.Sampler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 09/03/2018.
 */

public class FAQ implements Serializable {
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("values")
    @Expose
    public List<Value> values = null;
}
