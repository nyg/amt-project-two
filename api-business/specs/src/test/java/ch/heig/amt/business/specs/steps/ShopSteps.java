package ch.heig.amt.business.specs.steps;


import ch.heig.amt.business.specs.helpers.Environnement;
import ch.heig.amt.business_spec.ApiException;
import ch.heig.amt.business_spec.api.dto.Article;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShopSteps {

    private Environnement env;

    public ShopSteps(Environnement env) {
        this.env = env;
    }

    @Given("^there is a business server$")
    public void there_is_a_business_server() {
        assertNotNull(env.getPublicApi());
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int statusCode) {
        assertEquals(statusCode, env.getLastStatusCode());
    }

    @When("^I GET it to the /articles endpoint$")
    public void iGETItToTheArticlesEndpoint() {

        try {
            env.setLastApiResponse(env.getPublicApi().articlesGetWithHttpInfo());
            env.setLastApiException(null);
        }
        catch (ApiException e) {
            env.handleApiException(e);
        }
        catch (Exception e) {
            env.handleException(e);
        }
    }

    @And("^I receive the articles$")
    public void iReceiveTheArticles() {
        List<Article> articles = (List<Article>) env.getLastApiResponse().getData();
        assertEquals(8, articles.size());
    }
}
