package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import screenplay.interactions.EnterLoginCredentials;
import screenplay.interactions.EnterLoginPassword;
import screenplay.model.LoginCredentials;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompleteLoginForm implements Task {

    private final LoginCredentials credentials;

    public CompleteLoginForm(LoginCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            EnterLoginCredentials.withEmail(credentials.getEmail()),
            EnterLoginPassword.withPassword(credentials.getPassword())
        );
    }

    public static Performable withRegisteredCredentials() {
        return instrumented(CompleteLoginForm.class, LoginCredentials.registeredUser());
    }
}
