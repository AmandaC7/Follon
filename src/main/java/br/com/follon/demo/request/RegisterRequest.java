package br.com.follon.demo.request;

import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class RegisterRequest {

    String name;
    String email;
    String password;
    Blob photo;
    List<FieldOfInterestRequest> fieldOfInterest;

}
