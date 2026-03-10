# AUTO_FRONT_SCREENPLAY

Framework de automatizacion E2E para frontend con `Java 17 + Serenity BDD + Cucumber + Gradle`, implementado con el patron `Screenplay`.

## Objetivo

Automatizar un flujo E2E completo sobre la aplicacion de gestion de presupuesto:

1. Registro de usuario nuevo
2. Inicio de sesion con las credenciales registradas
3. Creacion de una transaccion de tipo egreso
4. Verificacion de la transaccion en el listado

### Aplicacion bajo prueba (AUT)

| Campo         | Valor                                                                                                 |
|---------------|-------------------------------------------------------------------------------------------------------|
| Repositorio   | [Budget_Management_App / release/1.2.1](https://github.com/ChristopherPalloArias/Budget_Management_App/tree/release/1.2.1) |
| Puerto        | `3000`                                                                                                |
| Base URL      | `http://localhost:3000`                                                                               |

---

## Stack tecnico

| Componente         | Tecnologia / Version                        |
|--------------------|---------------------------------------------|
| Lenguaje           | Java 17                                     |
| Build tool         | Gradle (Wrapper incluido)                   |
| Framework E2E      | Serenity BDD 4.2.9                          |
| BDD / Runner       | Cucumber 7.18.0 + CucumberWithSerenity      |
| Patron UI          | Screenplay (Tasks, Interactions, Questions) |
| Localizadores      | `@Target` / `By` en clases `*UI`           |
| Navegador          | Google Chrome (gestionado por WebDriverManager) |
| Aserciones         | AssertJ 3.26.3                              |

---

## Prerequisitos

1. **JDK 17** instalado y configurado en el `PATH`
2. **Google Chrome** instalado (WebDriverManager descarga el driver automaticamente)
3. **Node.js / npm** para levantar la aplicacion AUT
4. **Gradle Wrapper** ya incluido en el proyecto (no requiere instalacion adicional)

---

## Configuracion del AUT (Budget_Management_App)

Clona y levanta la aplicacion en una terminal separada:

```bash
git clone -b release/1.2.1 https://github.com/ChristopherPalloArias/Budget_Management_App.git
cd Budget_Management_App
npm install
npm run dev -- --port 3000
```

Verifica que la app responda en `http://localhost:3000` antes de ejecutar los tests.

---

## Configuracion del proyecto de pruebas

### serenity.properties

```properties
webdriver.driver=chrome
webdriver.base.url=http://localhost:3000
serenity.project.name=AUTO_FRONT_SCREENPLAY
thucydides.project.name=AUTO_FRONT_SCREENPLAY
serenity.take.screenshots=AFTER_EACH_STEP
headless.mode=false
```

### Datos de prueba (TestDataConstants)

Los datos de prueba estan centralizados en `src/test/java/utils/TestDataConstants.java`:

- **Email de registro**: generado de forma unica por ejecucion (`carlos.andrade.<timestamp>@pruebas.com`) para evitar conflictos de usuario duplicado.
- **Fecha de transaccion**: configurable via sistema con `-Dtest.tx.date=YYYY-MM-DD` (por defecto `2025-12-15`).
- **Base URL**: configurable via `-Dwebdriver.base.url=<url>` (por defecto `http://localhost:3000`).

---

## Ejecucion de pruebas

Desde la raiz del proyecto (`AUTO_FRONT_SCREENPLAY`):

```bash
./gradlew clean test
```

Con reporte Serenity generado al final:

```bash
./gradlew clean test aggregate
```

### Opciones de ejecucion

Forzar URL base diferente:

```bash
./gradlew clean test -Dwebdriver.base.url=http://localhost:4000
```

Cambiar fecha de transaccion:

```bash
./gradlew clean test -Dtest.tx.date=2026-01-20
```

Ejecutar en modo headless:

```bash
./gradlew clean test -Dheadless.mode=true
```

---

## Estructura del proyecto

```text
AUTO_FRONT_SCREENPLAY/
├── build.gradle
├── serenity.properties
└── src/
    └── test/
        ├── java/
        │   ├── screenplay/
        │   │   ├── interactions/          # Interacciones atomicas del actor
        │   │   │   ├── EnterRegistrationData.java
        │   │   │   ├── EnterLoginCredentials.java
        │   │   │   ├── EnterDescription.java
        │   │   │   ├── EnterAmount.java
        │   │   │   ├── EnterTransactionDate.java
        │   │   │   ├── SelectCategory.java
        │   │   │   └── SelectTransactionType.java
        │   │   ├── tasks/                 # Tareas compuestas por interacciones
        │   │   │   ├── OpenTheRegistrationPage.java
        │   │   │   ├── CompleteRegistrationForm.java
        │   │   │   ├── SubmitRegistration.java
        │   │   │   ├── CompleteLoginForm.java
        │   │   │   ├── SubmitLogin.java
        │   │   │   ├── OpenTransactionsSection.java
        │   │   │   ├── OpenNewTransactionModal.java
        │   │   │   ├── CompleteTransactionForm.java
        │   │   │   └── SubmitTransaction.java
        │   │   ├── questions/             # Preguntas sobre el estado de la UI
        │   │   │   ├── IsOnLoginPage.java
        │   │   │   ├── IsSuccessToastVisible.java
        │   │   │   ├── IsDashboardVisible.java
        │   │   │   ├── IsNewTransactionModalVisible.java
        │   │   │   └── IsTransactionListed.java
        │   │   ├── ui/                    # Localizadores de elementos UI (@Target)
        │   │   │   ├── RegisterPageUI.java
        │   │   │   ├── LoginPageUI.java
        │   │   │   ├── DashboardPageUI.java
        │   │   │   └── TransactionPageUI.java
        │   │   └── model/                 # Modelos de datos de prueba
        │   │       ├── RegistrationData.java
        │   │       ├── LoginCredentials.java
        │   │       └── TransactionData.java
        │   ├── POM/                       # Implementacion alternativa con Page Object Model
        │   │   ├── pages/
        │   │   ├── steps/
        │   │   ├── stepdefinitions/
        │   │   └── runner/
        │   │       └── TestRunner.java    # Runner principal (@e2e)
        │   └── utils/
        │       └── TestDataConstants.java # Constantes y datos de prueba centralizados
        └── resources/
            ├── features/
            │   └── budget_management.feature
            ├── serenity.conf
            ├── junit-platform.properties
            └── logback-test.xml
```

---

## Patron Screenplay aplicado

El patron Screenplay organiza la automatizacion en tres conceptos principales:

### Tasks (Tareas)
Representan acciones de alto nivel que un actor puede realizar. Componen una o mas interacciones.

```
OpenTheRegistrationPage  →  navega a /register
CompleteRegistrationForm →  llena el formulario con RegistrationData
SubmitRegistration       →  hace clic en el boton de registro
```

### Interactions (Interacciones)
Acciones atomicas sobre la UI. Son las unidades minimas de ejecucion.

```
EnterRegistrationData    →  escribe nombre, email, password y confirmacion
EnterLoginCredentials    →  escribe email y password en el login
EnterAmount              →  escribe el monto de la transaccion
SelectTransactionType    →  selecciona EXPENSE del selector de tipo
```

### Questions (Preguntas)
Consultan el estado actual de la UI para verificar resultados esperados.

```
IsOnLoginPage            →  verifica redireccion al login post-registro
IsSuccessToastVisible    →  verifica toast de exito
IsDashboardVisible       →  verifica que el panel principal esta visible
IsTransactionListed      →  verifica que la transaccion aparece en el listado
```

### UI (Localizadores)
Clases que centralizan los `@Target` (equivalente screenplay a `@FindBy`) para cada pagina.

---

## Escenario automatizado

Archivo: `src/test/resources/features/budget_management.feature`

```gherkin
Feature: Alta E2E de usuario y primer egreso

  @e2e
  Scenario: Registro exitoso, autenticacion y creacion de egreso visible en el listado
    Given la aplicacion de gestion de presupuesto esta disponible
    When  el usuario se registra con datos validos
    Then  el sistema confirma la creacion exitosa de la cuenta
    And   el usuario es redirigido a la pagina de inicio de sesion
    When  el usuario inicia sesion con las credenciales registradas
    Then  el sistema confirma el inicio de sesion exitoso
    And   el usuario accede al panel principal de la aplicacion
    When  el usuario navega a la seccion de transacciones
    And   el usuario crea una nueva transaccion de tipo egreso con datos validos
    Then  el sistema registra la transaccion correctamente
    And   la transaccion aparece en el listado de transacciones
```

Tag ejecutado: `@e2e`

---

## Reportes

Serenity genera el reporte en:

```
target/site/serenity/index.html
```

Abrir en macOS:

```bash
open target/site/serenity/index.html
```

Abrir en Linux:

```bash
xdg-open target/site/serenity/index.html
```

El reporte incluye: capturas de pantalla por paso, trazabilidad Gherkin → Task → Interaction, y metricas de ejecucion.

---

## Troubleshooting

### Error `ERR_CONNECTION_REFUSED`
La app AUT no esta levantada. Ejecuta `npm run dev -- --port 3000` en el directorio de `Budget_Management_App` y verifica que responda en `http://localhost:3000`.

### Fallas por elementos no encontrados (`NoSuchElementException`)
- Verifica que la version del AUT sea exactamente `release/1.2.1`.
- Revisa los localizadores en `src/test/java/screenplay/ui/`.
- Asegurate de que no haya cambios en la estructura HTML de la aplicacion.

### Error de usuario duplicado en registro
El email se genera con timestamp unico por ejecucion. Si persiste el error, verifica que la base de datos del AUT no este en un estado corrupto o reinicia la aplicacion.

### Chrome / CDP warnings en consola
Son advertencias de compatibilidad de version entre ChromeDriver y Chrome. No rompen la ejecucion. Actualiza WebDriverManager si persisten errores reales.

### `./gradlew` sin permisos de ejecucion
```bash
chmod +x gradlew
./gradlew clean test
```
