package br.com.luiza.core.veiculo.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoLegadoResponse {

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
