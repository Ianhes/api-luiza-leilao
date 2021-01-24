package br.com.luiza.application.exception.handler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    @ApiModelProperty(example = " Error interno ")
    private String mensagem;
    @ApiModelProperty(example = " Erro na validação de campos")
    private List<String> detalhes;
}
