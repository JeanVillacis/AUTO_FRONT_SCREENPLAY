package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class LoginPageUI {

    public static final Target EMAIL_INPUT = Target.the("campo email de login")
        .located(By.cssSelector("input#email, input[name='email'], input[type='email']"));

    public static final Target PASSWORD_INPUT = Target.the("campo contrasena de login")
        .located(By.cssSelector("input#password, input[name='password'], input[type='password']"));

    public static final Target LOGIN_SUBMIT_BUTTON = Target.the("boton iniciar sesion")
        .locatedBy("//button[@type='submit' and (contains(., 'Iniciar Sesion') or contains(., 'Iniciar Sesión'))]");

    private LoginPageUI() {}
}
