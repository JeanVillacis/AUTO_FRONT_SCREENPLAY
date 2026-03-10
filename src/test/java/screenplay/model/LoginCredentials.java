package screenplay.model;

public class LoginCredentials {

    private final String email;
    private final String password;

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static LoginCredentials registeredUser() {
        return new LoginCredentials("carlos.andrade@pruebas.com", "Prueba2026!");
    }
}
