package com.utils;

/**
 * OAuth2 Util Constants for Spring Security.
 */
public final class OAuth2ClientConstants {

	/**
	 * Constructor.
	 * */
	public OAuth2ClientConstants() {
		super();
	}

    /**
     * Token Validity.
     */
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 3600;

    /**
     * OAuth Custom Scopes.
     */
    public enum Scopes {

        /**
         * Ambito total.
         */
        ADMIN_SCOPE("admin-scope"),

        /**
         * Ambito restringido.
         */
        USER_SCOPE("user-scope");

        /**
         * Valor del ambito.
         */
        public final String scope;

        /**
         * Constructor.
         * @param value valor.
         */
        Scopes(final String value) {
            this.scope = value;
        }

        /**
         * @return el valor del ambito
         */
        public String getScope() {
            return this.scope;
        }
    }

    /**
     * Grant types defined for OAuth2.
     */
    public enum AuthorizedGrantTypes {

        /**
         * Password.
         */
        PASSWORD("password"),

        /**
         * Implicit.
         */
        IMPLICIT("implicit"),

        /**
         * Client Credentials.
         */
        CLIENT_CREDENTIALS("client_credentials"),

        /**
         * Refresh Token.
         */
        REFRESH_TOKEN("refresh_token"),

        /**
         * Authorization Code.
         */
        AUTHORIZATION_CODE("authorization_code");

        /**
         * Nombre del tipo.
         */
        public final String type;

        /**
         * Constructor.
         * @param value name of the type
         */
        AuthorizedGrantTypes(final String value) {
            this.type = value;
        }

        /**
         * Returns the type.
         * @return type
         */
        public String getType() {
            return this.type;
        }
    }

}
