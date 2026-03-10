package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import screenplay.questions.IsDashboardVisible;
import screenplay.questions.IsNewTransactionModalVisible;
import screenplay.questions.IsOnLoginPage;
import screenplay.questions.IsSuccessToastVisible;
import screenplay.questions.IsTransactionListed;
import screenplay.model.TransactionData;
import screenplay.tasks.CompleteLoginForm;
import screenplay.tasks.CompleteRegistrationForm;
import screenplay.tasks.CompleteTransactionForm;
import screenplay.tasks.OpenNewTransactionModal;
import screenplay.tasks.OpenTheRegistrationPage;
import screenplay.tasks.OpenTransactionsSection;
import screenplay.tasks.SubmitLogin;
import screenplay.tasks.SubmitRegistration;
import screenplay.tasks.SubmitTransaction;

import io.cucumber.java.Before;

import static org.hamcrest.Matchers.is;

public class BudgetStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver browser;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("la aplicación de gestión de presupuesto está disponible")
    public void laAplicacionEstaDisponible() {
        OnStage.theActorCalled("Usuario").can(BrowseTheWeb.with(browser));
        OnStage.theActorInTheSpotlight().attemptsTo(OpenTheRegistrationPage.now());
    }

    @When("el usuario se registra con datos válidos")
    public void elUsuarioSeRegistraConDatosValidos() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            CompleteRegistrationForm.withValidData(),
            SubmitRegistration.form()
        );
    }

    @Then("el sistema confirma la creación exitosa de la cuenta")
    public void elSistemaConfirmaLaCreacionExitosa() {
        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsOnLoginPage.value(), is(true))
        );
    }

    @And("el usuario es redirigido a la página de inicio de sesión")
    public void elUsuarioEsRedirigidoALogin() {
        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsOnLoginPage.value(), is(true))
        );
    }

    @When("el usuario inicia sesión con las credenciales registradas")
    public void elUsuarioIniciaSesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            CompleteLoginForm.withRegisteredCredentials(),
            SubmitLogin.form()
        );
    }

    @Then("el sistema confirma el inicio de sesión exitoso")
    public void elSistemaConfirmaElInicioSesion() {
        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsDashboardVisible.value(), is(true))
        );
    }

    @And("el usuario accede al panel principal de la aplicación")
    public void elUsuarioAccedeAlPanel() {
        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsDashboardVisible.value(), is(true))
        );
    }

    @When("el usuario navega a la sección de transacciones")
    public void elUsuarioNavegarATransacciones() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenTransactionsSection.now());
    }

    @And("el usuario crea una nueva transacción de tipo egreso con datos válidos")
    public void elUsuarioCreaUnaTransaccion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            OpenNewTransactionModal.now()
        );

        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsNewTransactionModalVisible.value(), is(true))
        );

        OnStage.theActorInTheSpotlight().attemptsTo(
            CompleteTransactionForm.withValidData(),
            SubmitTransaction.form()
        );
    }

    @Then("el sistema registra la transacción correctamente")
    public void elSistemaRegistraLaTransaccion() {
        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsSuccessToastVisible.value(), is(true))
        );
    }

    @And("la transacción aparece en el listado de transacciones")
    public void laTransaccionApareceEnElListado() {
        OnStage.theActorInTheSpotlight().should(
            GivenWhenThen.seeThat(IsTransactionListed.withDescription(TransactionData.validExpense().getDescription()), is(true))
        );
    }
}