package POM.steps;

import net.serenitybdd.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;
import POM.pages.LoginPage;
import POM.pages.RegisterPage;
import POM.pages.TransactionPage;

public class BudgetSteps {

    private static final String BASE_URL = System.getProperty("webdriver.base.url", "http://localhost:3000");

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private TransactionPage transactionPage;

    @Step("El usuario abre la página de registro")
    public void openRegisterPage() {
        registerPage.openPage(BASE_URL + "/register");
    }

    @Step("El usuario completa el formulario de registro con datos válidos")
    public void fillRegistrationForm() {
        registerPage.enterDisplayName("Carlos Andrade");
        registerPage.enterEmail("carlos.andrade." + System.currentTimeMillis() + "@pruebas.com");
        registerPage.enterPassword("Prueba2026!");
        registerPage.enterConfirmPassword("Prueba2026!");
    }

    @Step("El usuario envía el formulario de registro")
    public void submitRegistration() {
        registerPage.clickRegisterButton();
    }

    @Step("El sistema confirma la creación exitosa de la cuenta")
    public void verifyRegistrationSuccess() {
        registerPage.waitUntilRedirectedToLogin();
        assertThat(registerPage.isOnLoginPage())
            .as("La URL actual debe corresponder a la página de login")
            .isTrue();
    }

    @Step("El usuario es redirigido a la página de login")
    public void verifyRedirectToLogin() {
        assertThat(registerPage.isOnLoginPage())
            .as("Se esperaba redirección a login después del registro")
            .isTrue();
    }

    @Step("El usuario abre la página de login")
    public void openLoginPage() {
        loginPage.openPage(BASE_URL + "/login");
    }

    @Step("El usuario completa el formulario de login con credenciales válidas")
    public void fillLoginForm() {
        loginPage.enterEmail("carlos.andrade@pruebas.com");
        loginPage.enterPassword("Prueba2026!");
    }

    @Step("El usuario envía el formulario de login")
    public void submitLogin() {
        loginPage.clickLoginButton();
    }

    @Step("El sistema confirma el inicio de sesión exitoso")
    public void verifyLoginSuccess() {
        loginPage.waitUntilDashboardIsVisible();
        assertThat(loginPage.isDashboardSidebarDisplayed())
            .as("El dashboard debe mostrar el sidebar tras autenticarse")
            .isTrue();
    }

    @Step("El usuario accede al panel principal")
    public void verifyAccessToDashboard() {
        assertThat(loginPage.isDashboardSidebarDisplayed())
            .as("El usuario debe permanecer en el panel principal")
            .isTrue();
    }

    @Step("El usuario navega a la página de transacciones")
    public void navigateToTransactions() {
        transactionPage.openPage(BASE_URL + "/transactions");
    }

    @Step("El usuario abre el modal de nueva transacción")
    public void openNewTransactionModal() {
        transactionPage.clickNewTransactionButton();
        assertThat(transactionPage.isModalDisplayed())
            .as("El modal de nueva transacción debe abrirse")
            .isTrue();
    }

    @Step("El usuario completa el formulario de transacción con datos válidos")
    public void fillTransactionForm() {
        transactionPage.selectTransactionType("EXPENSE");
        transactionPage.enterDescription("Compra supermercado quincenal");
        transactionPage.enterAmount("85.50");
        transactionPage.selectCategory("Alimentación");
        transactionPage.enterDate("2025-12-15");
    }

    @Step("El usuario envía el formulario de transacción")
    public void submitTransaction() {
        transactionPage.clickCreateTransactionButton();
    }

    @Step("El sistema registra la transacción correctamente")
    public void verifyTransactionCreated() {
        assertThat(transactionPage.isSuccessToastDisplayed())
            .as("Debe mostrarse un mensaje de éxito al crear la transacción")
            .isTrue();
    }

    @Step("La transacción aparece en el listado")
    public void verifyTransactionInTable() {
        assertThat(transactionPage.isTransactionInTable("Compra supermercado quincenal"))
            .as("La transacción creada debe estar visible en la tabla")
            .isTrue();
    }
}