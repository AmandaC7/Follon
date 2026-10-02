package br.com.follon.demo.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HackathonRequest {

    private String title;
    private String description;
    private String location;
    private String format;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationStart;
    private LocalDate registrationEnd;
    private BigDecimal prize;
}
