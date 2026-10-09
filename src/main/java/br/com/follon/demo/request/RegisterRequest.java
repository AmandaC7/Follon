package br.com.follon.demo.request;

import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {

    String name;
    String email;
    String password;
    String city;
    byte[] photo;
    List<FieldOfInterestRequest> fieldOfInterest;

}
