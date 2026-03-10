package screenplay.model;

import utils.TestDataConstants;

public class RegistrationData {

    private final String displayName;
    private final String email;
    private final String password;
    private final String confirmPassword;

    public RegistrationData(String displayName, String email, String password, String confirmPassword) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public static RegistrationData valid() {
        return new RegistrationData(
            TestDataConstants.REG_DISPLAY_NAME,
            TestDataConstants.REG_EMAIL,
            TestDataConstants.REG_PASSWORD,
            TestDataConstants.REG_CONFIRM_PASSWORD
        );
    }
}
