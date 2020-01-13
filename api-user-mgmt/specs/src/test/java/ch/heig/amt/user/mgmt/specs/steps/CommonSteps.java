package ch.heig.amt.user.mgmt.specs.steps;

import ch.heig.amt.user.mgmt.specs.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommonSteps {

    private Environment env;

    public CommonSteps(Environment env) {
        this.env = env;
    }

    @Given("^there is a user-mgmt server$")
    public void there_is_a_user_mgmt_server() {
        assertNotNull(env.getPrivateApi());
        assertNotNull(env.getPublicApi());
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int statusCode) {
        assertEquals(statusCode, env.getLastStatusCode());
    }
}
