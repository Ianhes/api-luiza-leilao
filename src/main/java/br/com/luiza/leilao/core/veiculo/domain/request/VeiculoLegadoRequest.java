package br.com.luiza.core.veiculo.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class VeiculoLegadoRequest {

    @NonNull
    @JsonProperty("ID")
    private Integer id;
    @JsonProperty("DATALANCE")
    private String dataLance;
    @JsonProperty("LOTE")
    private String lote;
    @JsonProperty("CODIGOCONTROLE")
    private String codigoControle;
    @JsonProperty("MARCA")
    private String marca;
    @JsonProperty("MODELO")
    private String modelo;
    @JsonProperty("ANOFABRICACAO")
    private Integer anoFabricacao;
    @JsonProperty("ANOMODELO")
    private Integer anoModelo;
    @JsonProperty("VALORLANCE")
    private Double valorLance;
    @JsonProperty("USUARIOLANCE")
    private String usuarioLance;
}
