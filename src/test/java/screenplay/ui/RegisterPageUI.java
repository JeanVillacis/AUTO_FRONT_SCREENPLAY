package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class RegisterPageUI {

    public static final Target DISPLAY_NAME_INPUT = Target.the("campo nombre visible")
        .located(By.cssSelector("#displayName"));

    public static final Target EMAIL_INPUT = Target.the("campo email de registro")
        .located(By.cssSelector("#email"));

    public static final Target PASSWORD_INPUT = Target.the("campo contrasena de registro")
        .located(By.cssSelector("#password"));

    public static final Target CONFIRM_PASSWORD_INPUT = Target.the("campo confirmacion de contrasena")
        .located(By.cssSelector("#confirmPassword"));

    public static final Target REGISTER_SUBMIT_BUTTON = Target.the("boton crear cuenta")
        .locatedBy("//button[@type='submit' and contains(., 'Crear Cuenta')]");

    private RegisterPageUI() {}
}
