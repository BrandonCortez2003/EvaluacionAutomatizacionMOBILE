package com.nttdata.steps;

import com.nttdata.screens.CarritoScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidacionCarritoSteps {

    CarritoScreen carritoScreen;

    public void validarProductosCargados(){
        carritoScreen.validarProductosCargados();
    }

    public void clickProducto(String nombreProducto) {

        String xpath = "//android.widget.ImageView[@content-desc=\"" + nombreProducto + "\"]";
        WebElement producto = carritoScreen.find(By.xpath(xpath));
        producto.click();
    }
    public void agregarUnidades(int cantidad, String producto) {
        carritoScreen.ajustarCantidad(cantidad);
    }
    public void clickAgregarCarrito() {
        carritoScreen.clickAgregarCarrito();
    }
    public void clickCarrito() {
        carritoScreen.clickCarrito();
    }
    public void validarDatosCarrito(int unidadesEsperadas, double montoEsperado) {

        carritoScreen.validarCantidadEnCarrito(unidadesEsperadas);
        carritoScreen.validarMontoTotal(montoEsperado);
    }



}
