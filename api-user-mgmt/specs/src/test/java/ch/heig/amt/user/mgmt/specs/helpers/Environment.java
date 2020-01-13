package ch.heig.amt.user.mgmt.specs.helpers;

import ch.heig.amt.user.mgmt.ApiException;
import ch.heig.amt.user.mgmt.ApiResponse;
import ch.heig.amt.user.mgmt.api.PrivateApi;
import ch.heig.amt.user.mgmt.api.PublicApi;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
@Getter
@Setter
public class Environment {

    private PublicApi publicApi = new PublicApi();
    private PrivateApi privateApi = new PrivateApi();

    private String url;

    private ApiResponse<?> lastApiResponse;
    private ApiException lastApiException;
    private int lastStatusCode;

    public Environment() throws IOException {

        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("environment.properties"));

        url = properties.getProperty("ch.heig.amt.user.mgmt.server");
        publicApi.getApiClient().setBasePath(url);
        privateApi.getApiClient().setBasePath(url);
    }

    public int getLastStatusCode() {
        return lastApiResponse == null ? lastApiException.getCode() : lastApiResponse.getStatusCode();
    }
}
