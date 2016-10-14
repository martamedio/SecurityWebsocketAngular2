package com.service.impl;

import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.service.IClientOAuth2DetailsService;
import com.utils.OAuth2ClientConstants;

/**
 * Service for Oauth2 clients.
 */
@Service
public class ClientOAuth2DetailsServiceImpl implements IClientOAuth2DetailsService {

    /**
     * Custom client service.
     */
    private ClientDetailsService internalClientDetailsService;

    /**
     * Constructor for Client Service.
     * @throws Exception en la creacion de los clientes.
     */
    public ClientOAuth2DetailsServiceImpl() throws Exception {
		// Dummy client scope
		internalClientDetailsService = new InMemoryClientDetailsServiceBuilder().withClient("admin").secret("admin")
				.authorizedGrantTypes(OAuth2ClientConstants.AuthorizedGrantTypes.PASSWORD.getType())
				.scopes(OAuth2ClientConstants.Scopes.ADMIN_SCOPE.getScope(),
						OAuth2ClientConstants.Scopes.USER_SCOPE.getScope())
				.and().build();
    }

    @Override
    public final ClientDetails loadClientByClientId(final String client) throws ClientRegistrationException {
        return internalClientDetailsService.loadClientByClientId(client);
    }

}
