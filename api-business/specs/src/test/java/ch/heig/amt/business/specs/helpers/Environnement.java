package ch.heig.amt.business.specs.helpers;

import ch.heig.amt.business_spec.ApiException;
import ch.heig.amt.business_spec.ApiResponse;
import ch.heig.amt.business_spec.api.PublicApi;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class Environnement {

    private static final Logger LOG = Logger.getLogger(Environnement.class.getSimpleName());


    private PublicApi publicApi = new PublicApi();
    private int lastStatusCode;

    private String url;
    private ApiResponse<?> lastApiResponse;
    private ApiException lastApiException;

    public Environnement() throws IOException {

        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("environment.properties"));

        url = properties.getProperty("api.business.url");
        publicApi.getApiClient().setBasePath(url);
    }

    public int getLastStatusCode() {
        return lastApiResponse == null ? lastApiException.getCode() : lastApiResponse.getStatusCode();
    }

    public void handleApiException(ApiException e) {
        setLastApiResponse(null);
        setLastApiException(e);
        LOG.severe("ApiException message: " + e.getResponseBody());
    }

    public void handleException(Exception e) {
        LOG.log(Level.SEVERE, e.getMessage(), e);
    }


}
