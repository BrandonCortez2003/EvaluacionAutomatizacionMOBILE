package com.nttdata.stepsdefinitions;

import com.nttdata.steps.ValidacionCarritoSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class ValidacionCarritoStepsDef {

    @Steps
    ValidacionCarritoSteps validacionCarritoSteps;

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicaciónDeSauceLabs() {
    }

    @And("valido que carguen correctamente los productos en la galería")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGalería() {

    }

    @When("^agrego (.*) del siguiente producto (.*)$")
    public void agregoUNIDADESDelSiguienteProductoPRODUCTO(int unidades, String producto) {
        validacionCarritoSteps.clickProducto(producto);
        validacionCarritoSteps.agregarUnidades(unidades, producto);
        validacionCarritoSteps.clickAgregarCarrito();


    }

    @Then("^valido que el carrito de compra se actualice correctamente con (.*) unidades y un monto total de (.*)$")
    public void validoQueElCarritoDeCompraSeActualiceCorrectamenteConUNIDADESUnidadesYUnMontoTotalDeMONTO(int unidadesEsperadas, double montoEsperado) {
        validacionCarritoSteps.clickCarrito();
        validacionCarritoSteps.validarDatosCarrito(unidadesEsperadas, montoEsperado);
    }
}
