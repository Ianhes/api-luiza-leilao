package br.com.luiza.controller;

import br.com.luiza.core.veiculo.domain.Veiculo;
import br.com.luiza.core.veiculo.domain.request.VeiculoRequest;
import br.com.luiza.core.veiculo.domain.response.VeiculoResponse;
import br.com.luiza.core.veiculo.service.VeiculoService;
import br.com.luiza.core.veiculo.search.VeiculoParametroFiltro;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class VeiculoController {

    private final VeiculoService service;
    private final ModelMapper mapper;

    public Page<VeiculoResponse> getVeiculos(VeiculoParametroFiltro param, Pageable pageable){
        return service.getVeiculos(param, pageable).map(v -> mapper.map(v, VeiculoResponse.class));
    }

    public VeiculoResponse postVeiculo(VeiculoRequest veiculoRequest){
        Veiculo veiculo = service.postVeiculo(veiculoRequest);
        return veiculo != null ? mapper.map(veiculo, VeiculoResponse.class) : null;
    }

    public VeiculoResponse getVeiculo(Integer id){
        Veiculo veiculo = service.getVeiculo(id);
        return veiculo != null ? mapper.map(veiculo, VeiculoResponse.class) : null;
    }

    public VeiculoResponse putVeiculo(VeiculoRequest veiculoRequest, Integer id){
        Veiculo veiculo = service.putVeiculo(veiculoRequest, id);
        return veiculo != null ? mapper.map(veiculo, VeiculoResponse.class) : null;
    }

    public String deleteVeiculo(Integer id){
        return service.deleteVeiculo(id);
    }
}
