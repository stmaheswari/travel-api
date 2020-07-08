package com.afkl.cases.df.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Properties;

/**
 *  Configuration class which loads values from application.properties and
 *  override OAuth2RestTemplate in order to provide the client credentials
 *
 * @author Maheswari
 */
@Configuration
public class TravelAPIConfiguration {

    @Value("${oauth2.client-id}")
    private String clientId;

    @Value("${oauth2.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client-secret-encrypted}")
    private String clientSecretEnc;


    @Value("${oauth2.token-url}")
    private String tokenUrl;

    @Value("${oauth2.grant-type}")
    private String grantType;


    @Value("${endpoint.fareurl}")
    private String fareUrl;

    @Value("${endpoint.searchurl}")
    private String searchUrl;

    @Value("${endpoint.airportsurl}")
    private String airportsUrl;


    @Value("${app.encryptionPwd}")
    private String encryptionPwd;

    @Value("${app.isEncrypted}")
    private boolean isEncrypted;

    @Value("${app.encryptionAlgo}")
    private String encryptionAlgo;

    /**
     * Returns Airport URL
     * @return airportsUrl
     */
    public String getAirportsUrl() {
        return airportsUrl;
    }

    /**
     * Returns Fare URL
     * @return fareUrl
     */
    public String getFareUrl() {
        return fareUrl;
    }

    /**
     * Returns Search URL
     * @return searchUrl
     */
    public String getSearchUrl() {
        return searchUrl;
    }


    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        return new OAuth2RestTemplate(resource());
    }

    /**
     * Obtain the encrypted key
     * @return the string value
     */
    private String decryptedKey() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(encryptionAlgo);
        encryptor.setPassword(encryptionPwd);
        Properties props = new EncryptableProperties(encryptor);
        props.setProperty("password", clientSecretEnc);

        return props.getProperty("password");
    }

    /**
     * OAuth2-protected resource
     * @return OAuth2 resource details
     */
    public OAuth2ProtectedResourceDetails  resource() {
        ClientCredentialsResourceDetails   clientCredentialsResourceDetails = new ClientCredentialsResourceDetails ();
        clientCredentialsResourceDetails.setAccessTokenUri(tokenUrl);
        clientCredentialsResourceDetails.setClientId(clientId);
        if(isEncrypted)
            clientCredentialsResourceDetails.setClientSecret(decryptedKey());
        else
            clientCredentialsResourceDetails.setClientSecret(clientSecret);


        return clientCredentialsResourceDetails;
    }
}

