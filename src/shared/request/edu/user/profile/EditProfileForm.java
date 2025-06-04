package shared.request.edu.user.profile;

public class EditProfileForm {
    private final String phoneNumber;
    private final String email;
    private final String imageBase64;

    public EditProfileForm(String phoneNumber, String email, String imageBase64) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageBase64 = imageBase64;
    }


    // getters and setters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
