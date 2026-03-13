# AUTO_FRONT_SCREENPLAY

Automatizacion E2E de frontend para el flujo critico de gestion de presupuesto, construida con enfoque de calidad, trazabilidad y mantenibilidad bajo el patron Screenplay.

## Resumen Ejecutivo

Este proyecto valida un flujo funcional de extremo a extremo:

1. Registro de usuario
2. Inicio de sesion
3. Creacion de transaccion de egreso
4. Confirmacion de visibilidad de la transaccion en listado

La solucion prioriza tres objetivos de QA:

- Confiabilidad: ejecuciones repetibles con datos de prueba controlados
- Observabilidad: evidencia detallada por paso en Serenity Report
- Mantenibilidad: separacion clara de responsabilidades por capas Screenplay

## Alcance de Pruebas

- Tipo de prueba: E2E funcional
- Capa validada: interfaz web + comportamiento funcional del flujo
- Criterio de aprobacion: escenario principal en estado Passed con evidencia completa

## Stack Tecnico

- Java 17
- Gradle Wrapper
- Serenity BDD 4.2.9
- Cucumber 7.18.0
- Selenium WebDriver con Chrome

## Prerequisitos

1. JDK 17 instalado
2. Google Chrome instalado
3. Node.js y npm para levantar la aplicacion objetivo
4. Permiso de ejecucion para gradlew

Comando de apoyo:

```bash
chmod +x gradlew
```

## Aplicacion Bajo Prueba (AUT)

- Repositorio: https://github.com/ChristopherPalloArias/Budget_Management_App/tree/release/1.2.1
- URL objetivo por defecto: http://localhost:3000

### Levantamiento de AUT

```bash
git clone -b release/1.2.1 https://github.com/ChristopherPalloArias/Budget_Management_App.git
cd Budget_Management_App
npm install
npm run dev -- --port 3000
```

Validar previamente en navegador que la aplicacion responde en el puerto configurado.

## Ejecucion de Pruebas

Ejecucion base:

```bash
./gradlew clean test
```

Ejecucion con agregado de reporte:

```bash
./gradlew clean test aggregate
```

## Parametrizacion Util

```bash
./gradlew clean test -Dwebdriver.base.url=http://localhost:4000
./gradlew clean test -Dtest.tx.date=2026-01-20
./gradlew clean test -Dheadless.mode=true
```

## Estructura Real del Proyecto

```text
AUTO_FRONT_SCREENPLAY/
|-- build.gradle
|-- serenity.properties
|-- src/
|   `-- test/
|       |-- java/
|       |   `-- screenplay/
|       |       |-- interactions/
|       |       |   |-- EnterAmount.java
|       |       |   |-- EnterDescription.java
|       |       |   |-- EnterLoginCredentials.java
|       |       |   |-- EnterLoginPassword.java
|       |       |   |-- EnterRegistrationData.java
|       |       |   |-- EnterTransactionDate.java
|       |       |   |-- SelectCategory.java
|       |       |   `-- SelectTransactionType.java
|       |       |-- model/
|       |       |   |-- LoginCredentials.java
|       |       |   |-- RegistrationData.java
|       |       |   `-- TransactionData.java
|       |       |-- questions/
|       |       |   |-- IsDashboardVisible.java
|       |       |   |-- IsNewTransactionModalVisible.java
|       |       |   |-- IsOnLoginPage.java
|       |       |   |-- IsSuccessToastVisible.java
|       |       |   `-- IsTransactionListed.java
|       |       |-- runner/
|       |       |   `-- TestRunner.java
|       |       |-- stepdefinitions/
|       |       |   |-- BudgetStepDefinitions.java
|       |       |   `-- hooks/
|       |       |       `-- ScreenplayHooks.java
|       |       |-- tasks/
|       |       |   |-- CompleteLoginForm.java
|       |       |   |-- CompleteRegistrationForm.java
|       |       |   |-- CompleteTransactionForm.java
|       |       |   |-- OpenNewTransactionModal.java
|       |       |   |-- OpenTheRegistrationPage.java
|       |       |   |-- OpenTransactionsSection.java
|       |       |   |-- SubmitLogin.java
|       |       |   |-- SubmitRegistration.java
|       |       |   `-- SubmitTransaction.java
|       |       `-- ui/
|       |           |-- DashboardPageUI.java
|       |           |-- LoginPageUI.java
|       |           |-- RegisterPageUI.java
|       |           `-- TransactionPageUI.java
|       `-- resources/
|           |-- features/
|           |   `-- budget_management.feature
|           |-- junit-platform.properties
|           |-- logback-test.xml
|           `-- serenity.conf
`-- target/
```

## Arquitectura QA del Framework

- Interactions: acciones atomicas de UI
- Tasks: orquestacion de pasos funcionales
- Questions: validaciones de resultado observable
- Step Definitions: vinculacion Gherkin con acciones Screenplay
- Hooks: configuracion de contexto y ciclo de vida del actor
- Runner: punto de ejecucion de escenarios

Esta arquitectura facilita aislamiento de fallas y reduce impacto de cambios UI.

## Estrategia de Evidencia

- Captura por paso para auditoria visual
- Traza funcional desde Gherkin hasta interacciones concretas
- Reporte unificado para analisis de incidencias

Reporte generado en:

```text
target/site/serenity/index.html
```

Abrir en macOS:

```bash
open target/site/serenity/index.html
```

## Criterios de Calidad para Aprobacion

1. Escenario principal en estado Passed
2. Sin errores funcionales en registro, autenticacion y alta de egreso
3. Evidencia completa en Serenity para cada paso relevante
4. Sin fallas de sincronizacion recurrentes en UI

## Riesgos Comunes y Mitigacion

### Conexion rechazada (ERR_CONNECTION_REFUSED)

Riesgo: AUT apagada o puerto incorrecto.
Mitigacion: validar URL activa antes de ejecutar pruebas.

### Elementos no encontrados (NoSuchElementException)

Riesgo: cambio en DOM o carga incompleta de pantalla.
Mitigacion: revisar localizadores en ui y aplicar esperas sincronizadas por estado.

### Usuario duplicado en registro

Riesgo: datos de entrada no unicos.
Mitigacion: generar datos dinamicos por ejecucion y limpiar datos de prueba si aplica.

## Buenas Practicas de QA para Este Repositorio

1. Mantener escenarios Gherkin con lenguaje de negocio
2. Evitar acoplar assertions a textos inestables de interfaz
3. Reusar tasks e interactions antes de crear nuevas clases
4. Centralizar localizadores exclusivamente en la capa ui
5. Priorizar estabilidad sobre cantidad de escenarios
