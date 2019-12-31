package io.avalia.fruits.api.spec.helpers;

import io.avalia.fruits.api.PrivateApi;
import io.avalia.fruits.api.PublicApi;
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

        String url = properties.getProperty("io.avalia.fruits.server.url");

        privateApi.getApiClient().setBasePath(url);
        publicApi.getApiClient().setBasePath(url);
    }
}
