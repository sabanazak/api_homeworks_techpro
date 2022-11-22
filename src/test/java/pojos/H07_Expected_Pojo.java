package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class H07_Expected_Pojo {
    private String name;
    private String job;

    public H07_Expected_Pojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public H07_Expected_Pojo() {
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "H07_Expected_Pojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
