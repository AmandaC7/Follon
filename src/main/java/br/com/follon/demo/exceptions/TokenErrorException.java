package br.com.follon.demo.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;

public class TokenErrorException extends RuntimeException {
    public TokenErrorException(String message, JWTCreationException exception) {
        super(message, exception);
    }
}
