package br.com.luiza.core.veiculo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo implements Comparable<Veiculo>{

    private Integer id;
    private String dataLance;
    private String lote;
    private String codigoControle;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private Integer anoModelo;
    private Double valorLance;
    private String usuarioLance;

    @Override
    public int compareTo(Veiculo outroVeiculo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        LocalDateTime dataLance = LocalDateTime.parse(this.getDataLance(), formatter);
        LocalDateTime dataLanceOutroVeiculo = LocalDateTime.parse(outroVeiculo.getDataLance(), formatter);
        if (dataLance.isAfter(dataLanceOutroVeiculo)) {
            return -1;
        }else if (dataLance.isBefore(dataLanceOutroVeiculo)) {
            return 1;
        }else{
            return 0;
        }
    }
}
