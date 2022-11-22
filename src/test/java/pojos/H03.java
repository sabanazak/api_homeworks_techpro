package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class H03 {
    private H03_Data_Pojo data;
    private H03_Support_Pojo support;

    public H03(H03_Data_Pojo data, H03_Support_Pojo support) {
        this.data = data;
        this.support = support;
    }

    public H03() {
    }

    public H03_Data_Pojo getData() {
        return data;
    }

    public H03_Support_Pojo getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return "H03{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}
