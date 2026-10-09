package br.com.follon.demo.request;

import lombok.Data;

@Data
public class EvaluationRequest {

    private Long submissionId;
    private Long judgeId;
    private Integer innovation;
    private Integer execution;
    private Integer design;
    private Integer impact;
    private String feedback;
}
