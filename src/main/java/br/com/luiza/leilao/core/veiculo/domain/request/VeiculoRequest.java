package br.com.luiza.core.veiculo.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoRequest {

    private Integer id;
    @NotNull
    private String dataLance;
    @NotNull
    private String lote;
    @NotNull
    private String codigoControle;
    @NotNull
    private String marca;
    @NotNull
    private String modelo;
    @NotNull
    private Integer anoFabricacao;
    @NotNull
    private Integer anoModelo;
    @NotNull
    private Double valorLance;
    @NotNull
    private String usuarioLance;
}
