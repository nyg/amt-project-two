package ch.heig.amt.user.mgmt.specs.steps;

import ch.heig.amt.user.mgmt.ApiException;
import ch.heig.amt.user.mgmt.api.PublicApi;
import ch.heig.amt.user.mgmt.api.dto.Identifiers;
import ch.heig.amt.user.mgmt.specs.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class AuthenticationSteps {

    private Environment env;
    private Identifiers identifiers;

    public AuthenticationSteps(Environment env) {
        this.env = env;
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
            env.setLastApiResponse(env.getPublicApi().authenticateUserWithHttpInfo(identifiers));
            env.setLastApiException(null);
        }
        catch (ApiException e) {
            env.setLastApiResponse(null);
            env.setLastApiException(e);
        }
    }
}
