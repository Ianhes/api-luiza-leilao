package br.com.luiza.core.veiculo.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiLegadoRequest {

    @NonNull
    @JsonProperty("OPERACAO")
    private String operacao;
    @JsonProperty("VEICULO")
    private VeiculoLegadoRequest veiculo;
}
