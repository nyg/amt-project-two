package ch.heig.amt.user.mgmt.specs.helpers;

import ch.heig.amt.user.mgmt.api.PrivateApi;
import ch.heig.amt.user.mgmt.api.PublicApi;
import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
@Getter
public class Environment {

    private final PrivateApi privateApi = new PrivateApi();
    private final PublicApi publicApi = new PublicApi();

    public Environment() throws IOException {

        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("environment.properties"));

        String url = properties.getProperty("ch.heig.amt.user.mgmt.server");

        privateApi.getApiClient().setBasePath(url);
        publicApi.getApiClient().setBasePath(url);
    }
}
