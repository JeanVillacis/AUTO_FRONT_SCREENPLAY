Feature: Gestión de presupuesto personal
  Como usuario nuevo de la aplicación de presupuesto
  Quiero registrarme, iniciar sesión y gestionar mis transacciones
  Para comenzar el control de mis finanzas personales

  Background:
    Given el usuario está en la página de inicio de la aplicación

  @e2e
  Scenario Outline: Happy Path E2E - Registro, sesión y gestión de transacciones
    When el usuario se registra con nombre "<displayName>" email "<email>" y contraseña "<password>"
    Then el sistema confirma la creación exitosa de la cuenta
    And el usuario es redirigido a la página de inicio de sesión
    When el usuario inicia sesión con las credenciales registradas
    Then el sistema confirma el inicio de sesión exitoso
    And el usuario accede al panel principal de la aplicación
    When el usuario navega a la sección de transacciones
    And el usuario crea una transacción de tipo "<tipo>" descripción "<descripcion>" monto "<monto>" categoría "<categoria>" y fecha "<fecha>"
    Then el sistema registra la transacción correctamente
    And la transacción "<descripcion>" aparece en el listado de transacciones

    Examples:
      | displayName    | email                          | password    | tipo    | descripcion                   | monto | categoria    | fecha      |
      | Luis Ramírez   | luis.ramirez4@pruebas.com       | Prueba2026! | EXPENSE | Compra supermercado quincenal  | 85.50 | Alimentación | 2025-12-15 |
      | María López    | maria.lopez4@pruebas.com       | Prueba2026! | EXPENSE | Pago de servicios básicos      | 120   | Alimentación | 2025-12-10 |
      | Ana Torres     | ana.torres4@pruebas.com        | Prueba2026! | EXPENSE | Alquiler departamento          | 450   | Alimentación | 2025-12-05 |
      | Pedro García   | pedro.garcia4@pruebas.com      | Prueba2026! | INCOME  | Salario mensual diciembre      | 1500  | Salario      | 2025-12-01 |
