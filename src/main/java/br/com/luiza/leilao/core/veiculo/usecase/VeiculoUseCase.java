package br.com.luiza.core.veiculo.usecase;

import br.com.luiza.core.veiculo.domain.Veiculo;
import br.com.luiza.core.veiculo.predicate.VeiculoPredicate;
import br.com.luiza.core.veiculo.search.VeiculoParametroFiltro;

import java.util.Collections;
import java.util.List;

public class VeiculoUseCase {

    public List<Veiculo> filtrarVeiculos(VeiculoParametroFiltro param, List<Veiculo> veiculosFiltrados) {
        if (param.getDeFabricacao() != null && param.getAteFabricacao() != null) {
            Integer deFabricacao = param.getDeFabricacao();
            Integer ateFecricacao = param.getAteFabricacao();
            veiculosFiltrados = VeiculoPredicate.filtroVeiculos(veiculosFiltrados, VeiculoPredicate.filtroRangeAnoFabricacao(deFabricacao, ateFecricacao));
        }
        if (param.getLote() != null) {
            String lote = param.getLote();
            veiculosFiltrados = VeiculoPredicate.filtroVeiculos(veiculosFiltrados, VeiculoPredicate.filtroPorLote(lote));
        }
        if (param.getMarca() != null) {
            String marca = param.getMarca();
            veiculosFiltrados = VeiculoPredicate.filtroVeiculos(veiculosFiltrados, VeiculoPredicate.filtroPorMarca(marca));
        }
        if (param.getModelo() != null) {
            String modelo = param.getModelo();
            veiculosFiltrados = VeiculoPredicate.filtroVeiculos(veiculosFiltrados, VeiculoPredicate.filtroIniciaisModelo(modelo));
        }
        if (param.getAnoModelo() != null && param.getAnoFabricacao() != null) {
            Integer anoModelo = param.getAnoModelo();
            Integer anoFabricacao = param.getAnoFabricacao();
            veiculosFiltrados.addAll(br.com.luiza.core.veiculo.predicate.VeiculoPredicate
                    .filtroVeiculos(veiculosFiltrados, VeiculoPredicate.filtroAnoModeloAnoFabricacao(anoModelo, anoFabricacao)));
        }
        return veiculosFiltrados;
    }

    public void ordenarPorDataLance(List<Veiculo> veiculos, List<String> sort) {
        Collections.sort(veiculos);
        if (sort != null) {
            if (sort.get(0).equalsIgnoreCase("lance") &&
                    sort.get(1).equalsIgnoreCase("desc")) {
                Collections.sort(veiculos, Collections.reverseOrder());
            }
        }
    }
}

