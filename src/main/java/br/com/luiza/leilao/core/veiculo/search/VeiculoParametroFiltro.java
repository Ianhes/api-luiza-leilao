package br.com.luiza.core.veiculo.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class VeiculoParametroFiltro {

    private String lote;
    private String marca;
    private String modelo;
    private Integer anoModelo;
    private Integer anoFabricacao;
    private Integer ateFabricacao;
    private Integer deFabricacao;
    private List<String> sort;
}
