package ch.heig.amt.user.mgmt.specs.steps;

import ch.heig.amt.user.mgmt.ApiException;
import ch.heig.amt.user.mgmt.ApiResponse;
import ch.heig.amt.user.mgmt.api.PublicApi;
import ch.heig.amt.user.mgmt.api.dto.Identifiers;
import ch.heig.amt.user.mgmt.api.dto.Token;
import ch.heig.amt.user.mgmt.specs.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthenticationSteps {

    private PublicApi api;
    private Identifiers identifiers;

    private ApiResponse<Token> lastApiResponse;
    private ApiException lastApiException;
    private int lastStatusCode;

    public AuthenticationSteps(Environment environment) {
        api = environment.getPublicApi();
    }

    @Given("^there is a public user-mgmt server$")
    public void there_is_a_public_user_mgmt_server() {
        assertNotNull(api);
    }

    @Given("^I have a valid identifier payload$")
    public void i_have_a_valid_identifier_payload() {
        identifiers = new Identifiers();
        identifiers.setEmail("admin@amt.ch");
        identifiers.setPassword("mypwd");
    }

    @Given("^I have an invalid identifier payload$")
    public void i_have_an_invalid_identifier_payload() {
        identifiers = new Identifiers();
        identifiers.setEmail("admin@amt.ch");
        identifiers.setPassword("incorrectpwd");
    }

    @When("^I POST it to the /api/public/authenticate endpoint$")
    public void i_POST_it_to_the_api_public_authenticate_endpoint() {
        try {
            lastApiResponse = api.authenticateUserWithHttpInfo(identifiers);
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        }
        catch (ApiException e) {
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code - auth$")
    public void i_receive_a_status_code_auth(int statusCode) {
        assertEquals(statusCode, lastStatusCode);
    }
}
