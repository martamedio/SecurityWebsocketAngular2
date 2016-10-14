package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import com.service.CustomUserDetailsService;
import com.service.IClientOAuth2DetailsService;

/**
 * Class for Spring Security Configuration.
 */
@Configuration
public class SecurityConfiguration {

	/**
	 * Web Security for managing authentication.
	 */
	@Configuration
	@EnableWebSecurity
	protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		/**
		 * User Service for authentication.
		 */
		@Autowired
		private CustomUserDetailsService customUserService;

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected final void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
		}

		@Override
		public void configure(WebSecurity webSecurity) throws Exception {
			// Websocket security
			webSecurity.ignoring().antMatchers("/app/**");
			webSecurity.ignoring().antMatchers("/message");
		}

	}

	/**
	 * Set the application as resource server, also in this class we set the
	 * permissions to accesing to our REST services.
	 */
	@Configuration
	@EnableResourceServer
	protected static class ResourceServer extends ResourceServerConfigurerAdapter {

		@Override
		public final void configure(final HttpSecurity http) throws Exception {
			// CSRF disabled because we only use our own services (necesary for
			// third parties)
			http.csrf().disable();

			// Anyone can request for a security token
			http.authorizeRequests().antMatchers("/oauth/token", "/", "/app/*").anonymous();

			// All petitions except token one needs authentication
			http.authorizeRequests().anyRequest().fullyAuthenticated();

		}

		/**
		 * Oauth2 Configuration.
		 */
		@Configuration
		@EnableAuthorizationServer
		protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

			/**
			 * Authentication Provider.
			 */
			@Autowired
			@Qualifier("authenticationManagerBean")
			private AuthenticationManager authenticationManager;

			/**
			 * User Service for authentication.
			 */
			@Autowired
			private CustomUserDetailsService customUserService;

			/**
			 * We need to override the Spring Security service for users,
			 * because we want to use our own custom user service (connected to
			 * BBDD).
			 *
			 * @return UserDetailsService Returns an instance of our custom
			 *         service
			 */
			@Bean
			public UserDetailsService userDetailsService() {
				return customUserService;
			}

			/**
			 * Client Service for authentication.
			 */
			@Autowired
			private IClientOAuth2DetailsService customClientDetailsService;

			/**
			 * We need to override the Spring Security service for clients,
			 * because we want to use our own custom client service (in memory,
			 * but it migth goes to BBDD).
			 *
			 * @return ClientDetailsService Returns an instance of our custom
			 *         service
			 * @throws Exception
			 *             Error
			 */
			@Bean
			public ClientDetailsService clientDetailsService() throws Exception {
				return customClientDetailsService;
			}

			@Override
			public final void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
				endpoints.authenticationManager(authenticationManager);
			}

			/**
			 * We need to set our custom client service for OAuth2
			 * Configuration.
			 */
			@Override
			public final void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
				clients.withClientDetails(clientDetailsService());
			}
		}
	}
}
