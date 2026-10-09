package br.com.follon.demo.request;

import lombok.Data;

@Data
public class BadgeRequest {

    private Long hackathonId;
    private byte[] photo;

}
