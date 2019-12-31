package ch.heig.amt.user.mgmt.specs.steps;

import ch.heig.amt.user.mgmt.ApiException;
import ch.heig.amt.user.mgmt.ApiResponse;
import ch.heig.amt.user.mgmt.api.PublicApi;
import ch.heig.amt.user.mgmt.api.dto.Identifiers;
import ch.heig.amt.user.mgmt.specs.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class AuthenticationSteps {

    private Environment environment;
    private PublicApi api;

    private Identifiers identifiers;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public AuthenticationSteps(Environment environment) {
        this.environment = environment;
        api = environment.getPublicApi();
    }

    @Given("^there is a user-mgmt server$")
    public void there_is_a_user_mgmt_server() {
        assertNotNull(api);
    }

    @Given("^I have an identifier payload$")
    public void i_have_an_identifier_payload() {
        identifiers = new Identifiers();
        identifiers.setEmail("admin@amt.ch");
        identifiers.setPassword("mypwd");
    }

    @When("^I POST it to the /api/public/authenticate endpoint$")
    public void i_POST_it_to_the_api_public_authenticate_endpoint() {
        try {
            lastApiResponse = api.authenticateUserWithHttpInfo(identifiers);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        }
        catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(200, lastStatusCode);
    }
}