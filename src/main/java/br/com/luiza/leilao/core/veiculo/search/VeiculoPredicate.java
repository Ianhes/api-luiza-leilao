package br.com.luiza.core.veiculo.predicate;

import br.com.luiza.core.veiculo.domain.Veiculo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VeiculoPredicate {

    public static Predicate<Veiculo> filtroPorLote(String lote){
        Predicate<Veiculo> te =  v -> v.getLote() != null ? v.getLote().equalsIgnoreCase(lote) : false;
        return te;
    }
    public static Predicate<Veiculo> filtroPorMarca(String marca){
        Predicate<Veiculo> te = v -> v.getMarca() != null ? v.getMarca().equalsIgnoreCase(marca) : false;
        return te;
    }
    public static Predicate<Veiculo> filtroIniciaisModelo(String modelo){
        String modeloUpperCase = modelo.toUpperCase();
        return v -> v.getModelo() != null ? v.getModelo().contains(modeloUpperCase) : false;
    }
    public static Predicate<Veiculo> filtroAnoModeloAnoFabricacao(Integer anoModelo, Integer anoFabricacao){
        return v -> v.getAnoFabricacao() != null && v.getAnoModelo() != null ?
                v.getAnoFabricacao().intValue() == anoFabricacao && v.getAnoModelo().intValue() == anoModelo : false;
    }
    public static Predicate<Veiculo> filtroRangeAnoFabricacao(Integer deFabricacao, Integer ateFabricacao){
        return v -> v.getAnoFabricacao() != null ? v.getAnoFabricacao() >= deFabricacao && v.getAnoFabricacao() <= ateFabricacao : false;
    }
    public static List<Veiculo> filtroVeiculos (List<Veiculo> veiculos, Predicate<Veiculo> veiculosPredicate){
        return veiculos.stream()
                .filter(veiculosPredicate)
                .collect(Collectors.toList());
    }
}
