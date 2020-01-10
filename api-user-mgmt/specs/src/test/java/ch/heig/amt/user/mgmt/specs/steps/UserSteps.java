package ch.heig.amt.user.mgmt.specs.steps;

import ch.heig.amt.user.mgmt.ApiException;
import ch.heig.amt.user.mgmt.ApiResponse;
import ch.heig.amt.user.mgmt.api.PrivateApi;
import ch.heig.amt.user.mgmt.api.dto.Identifiers;
import ch.heig.amt.user.mgmt.api.dto.OptionalUser;
import ch.heig.amt.user.mgmt.api.dto.Token;
import ch.heig.amt.user.mgmt.specs.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserSteps {

    private PrivateApi api;
    private OptionalUser user;

    private ApiResponse<OptionalUser> lastApiResponse;
    private ApiException lastApiException;
    private int lastStatusCode;

    public UserSteps(Environment environment) {
        api = environment.getPrivateApi();
    }

    @Given("^there is a private user-mgmt server$")
    public void there_is_a_private_user_mgmt_server() {
        assertNotNull(api);
    }

    @Given("^I have a valid user payload$")
    public void i_have_a_valid_user_payload() {
        user = new OptionalUser();
        user.setFirstName("aFirstName");
        user.setLastName("aLastName");
        user.setActive(true);
    }

    @When("^I PUT it to the /api/private/user endpoint$")
    public void i_PUT_it_to_the_api_private_user_endpoint() {
        try {
            // hardcoded token for user jacques@amt.ch
            String hardcodedToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6ZmFsc2UsImVtYWlsIjoiamFjcXVlc0BhbXQuY2gifQ.UuCtxv8nBb48uj5SztfZ3NEGa2pdpDtJY818DaYlflw";
            lastApiResponse = api.updateUserWithHttpInfo(hardcodedToken, user);
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        }
        catch (ApiException e) {
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code_user(int statusCode) {
        assertEquals(statusCode, lastStatusCode);
    }

    @And("^I receive the updated user$")
    public void i_received_the_updated_user() {
        OptionalUser receivedUser = lastApiResponse.getData();
        assertEquals(user.getFirstName(), receivedUser.getFirstName());
        assertEquals(user.getLastName(), receivedUser.getLastName());
        assertEquals(user.getActive(), receivedUser.getActive());
    }
}
