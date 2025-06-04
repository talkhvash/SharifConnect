package shared.model.edu;

public class Captcha {
    private Integer id;
    private String password;
    private String imageBase64;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
