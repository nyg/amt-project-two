package ch.heig.amt.user.mgmt.specs.steps;

import ch.heig.amt.user.mgmt.ApiException;
import ch.heig.amt.user.mgmt.api.dto.OptionalUser;
import ch.heig.amt.user.mgmt.api.dto.User;
import ch.heig.amt.user.mgmt.specs.helpers.Environment;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class UserSteps {

    private Environment env;
    private OptionalUser optionalUser;
    private User user;

    public UserSteps(Environment env) {
        this.env = env;
    }

    @Given("^I have a valid user payload$")
    public void i_have_a_valid_user_payload() {
        optionalUser = new OptionalUser();
        optionalUser.setFirstName("aFirstName");
        optionalUser.setLastName("aLastName");
        optionalUser.setActive(true);
    }

    @Given("^I have an already existing user creation payload$")
    public void i_have_an_already_existing_user_creation_payload() {
        user = new User().email("jean@amt.ch").password("jean123FFF").firstName("Jean").lastName("Bonzini").active(true).admin(false);
    }

    @Given("^I have an invalid user creation payload$")
    public void i_have_an_invalid_user_creation_payload() {
        user = new User().email("marie@amt.ch").password("marie").firstName("Marie").lastName("Bonzini").active(true).admin(false);
    }

    @Given("^I have a valid user creation payload$")
    public void i_have_a_valid_user_creation_payload() {
        user = new User().email("marc@amt.ch").password("marc123FF").firstName("Marc").lastName("Bonzini").active(true).admin(false);
    }

    @When("^I PUT it to the /api/private/user endpoint$")
    public void i_PUT_it_to_the_api_private_user_endpoint() {
        try {
            // hardcoded token for user jacques@amt.ch
            String hardcodedToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6ZmFsc2UsImVtYWlsIjoiamFjcXVlc0BhbXQuY2gifQ.UuCtxv8nBb48uj5SztfZ3NEGa2pdpDtJY818DaYlflw";
            env.setLastApiResponse(env.getPrivateApi().updateUserWithHttpInfo(hardcodedToken, optionalUser));
            env.setLastApiException(null);
        }
        catch (ApiException e) {
            env.setLastApiResponse(null);
            env.setLastApiException(e);
        }
    }

    @When("^I POST it to the /api/public/users endpoint$")
    public void i_POST_it_to_the_api_public_users_endpoint() {
        try {
            env.setLastApiResponse(env.getPublicApi().createUserWithHttpInfo(user));
            env.setLastApiException(null);
        }
        catch (ApiException e) {
            env.setLastApiResponse(null);
            env.setLastApiException(e);
        }
    }

    @And("^I receive the updated user$")
    public void i_received_the_updated_user() {
        OptionalUser receivedUser = (OptionalUser) env.getLastApiResponse().getData();
        assertEquals(optionalUser.getFirstName(), receivedUser.getFirstName());
        assertEquals(optionalUser.getLastName(), receivedUser.getLastName());
        assertEquals(optionalUser.getActive(), receivedUser.getActive());
    }
}
