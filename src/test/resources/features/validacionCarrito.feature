Feature: Validación de la adición de productos al carrito de compra

  @carrito
  Scenario Outline: Verificar la actualización del carrito al agregar productos
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galería
    When agrego <UNIDADES> del siguiente producto <PRODUCTO>
    Then valido que el carrito de compra se actualice correctamente con <UNIDADES> unidades y un monto total de <MONTO>

    Examples:
      | PRODUCTO                | UNIDADES | MONTO |
      | Sauce Labs Backpack     | 1        | 29.99 |
      | Sauce Labs Bolt T-Shirt | 1        | 15.99 |
      | Sauce Labs Bike Light   | 2        | 19.98 |
