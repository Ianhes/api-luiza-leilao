package br.com.luiza.application.web.openapi;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PaginacaoOpenApi {

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private int page;

    @ApiModelProperty(example = "10", value = "Quantidade de elementos por página")
    private int size;

    @ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação")
    private List<String> sort;

}
