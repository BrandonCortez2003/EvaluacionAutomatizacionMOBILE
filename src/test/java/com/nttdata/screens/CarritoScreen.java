package com.nttdata.screens;


import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class CarritoScreen extends PageObject {


    private WebDriverWait wait;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Backpack\"]")
    private WebElement imgProducto;


    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/productTV\"]")
    private List<WebElement> listaDeProductos;


    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElementFacade botonIncrementar;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Decrease item quantity\"]")
    private WebElementFacade botonDecrementar;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElementFacade cantidadActualElement;



    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement btnAgregarCarrito;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    private WebElement btnCarrito;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElementFacade cantidadProductoCarrito;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/totalPriceTV\"]")
    private WebElementFacade montoTotalCarrito;



    public void validarProductosCargados() {

        wait.until(ExpectedConditions.visibilityOfAllElements(listaDeProductos));

        if (listaDeProductos.isEmpty()) {
            throw new AssertionError("No se encontraron productos en la galería.");
        }
    }

    public void ajustarCantidad(int unidades) {
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        waitForVisibilityOf(botonIncrementar);
        waitForVisibilityOf(botonDecrementar);
        waitForVisibilityOf(cantidadActualElement);

        String cantidadTexto = cantidadActualElement.getText().trim();
        int cantidadActual;

        try {
            if (cantidadTexto.isEmpty()) {

                cantidadActual = 1;
            } else {
                cantidadActual = Integer.parseInt(cantidadTexto);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("El texto del elemento de cantidad no es un número válido: " + cantidadTexto, e);
        }

        while (cantidadActual < unidades) {
            botonIncrementar.click();
            cantidadActual++;
        }

        while (cantidadActual > unidades) {
            botonDecrementar.click();
            cantidadActual--;
        }
    }

    private void waitForVisibilityOf(WebElementFacade element) {
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        element.waitUntilVisible();
    }


    public void clickAgregarCarrito(){
        btnAgregarCarrito.click();
    }
    public void clickCarrito(){
        btnCarrito.click();
    }




    public void validarCantidadEnCarrito(int unidadesEsperadas) {
        waitForVisibilityOf(cantidadProductoCarrito);

        String cantidadTexto = cantidadProductoCarrito.getText().trim();
        int cantidadActual;

        try {
            // Convertir el texto a un número entero
            cantidadActual = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
            throw new RuntimeException("El texto del elemento de cantidad no es un número válido: " + cantidadTexto, e);
        }

        // Comparar la cantidad actual con la cantidad esperada
        if (cantidadActual != unidadesEsperadas) {
            throw new AssertionError("Cantidad en el carrito es " + cantidadActual + ", pero se esperaba " + unidadesEsperadas);
        }
    }

    public void validarMontoTotal(double montoEsperado) {
        waitForVisibilityOf(montoTotalCarrito);

        String montoTexto = montoTotalCarrito.getText().trim().replace("$", "").replace(",", "");
        double montoActual;

        try {
            montoActual = Double.parseDouble(montoTexto);
        } catch (NumberFormatException e) {
            throw new RuntimeException("El texto del elemento de monto total no es un número válido: " + montoTexto, e);
        }

        if (Math.abs(montoActual - montoEsperado) > 0.01) { // Tolerancia de 0.01 para errores de redondeo
            throw new AssertionError("Monto total en el carrito es " + montoActual + ", pero se esperaba " + montoEsperado);
        }
    }





}
