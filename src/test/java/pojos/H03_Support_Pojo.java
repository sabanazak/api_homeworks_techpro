package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class H03_Support_Pojo {
    private String url;
    private String Text;

    public H03_Support_Pojo(String url, String text) {
        this.url = url;
        Text = text;
    }

    public H03_Support_Pojo() {
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return Text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        Text = text;
    }

    @Override
    public String toString() {
        return "H03_Support_Pojo{" +
                "url='" + url + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }
}
