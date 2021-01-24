package br.com.luiza.core.veiculo.service;

import br.com.luiza.client.LegadoClient;
import br.com.luiza.core.veiculo.domain.Veiculo;
import br.com.luiza.core.veiculo.domain.request.VeiculoLegadoRequest;
import br.com.luiza.core.veiculo.domain.request.VeiculoRequest;
import br.com.luiza.core.veiculo.domain.response.VeiculoLegadoResponse;
import br.com.luiza.core.veiculo.search.VeiculoParametroFiltro;
import br.com.luiza.core.veiculo.usecase.VeiculoUseCase;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class VeiculoService {

    private LegadoClient legadoClient;
    private ModelMapper mapper;
    private VeiculoUseCase useCase;

    public Page<Veiculo> getVeiculos(VeiculoParametroFiltro param, Pageable pageable){
        List<VeiculoLegadoResponse> responseClient = legadoClient.getVeiculos();
        List<Veiculo> veiculos = responseClient.stream()
                .map(v -> mapper.map(v, Veiculo.class))
                .collect(Collectors.toList());
        List<Veiculo> veiculosFiltrados = useCase.filtrarVeiculos(param, veiculos);
        useCase.ordenarPorDataLance(veiculosFiltrados, param.getSort());
        return paginarLista(veiculosFiltrados, pageable);
    }

    public Veiculo postVeiculo(VeiculoRequest veiculoRequest) {
        VeiculoLegadoResponse veiculoClient = legadoClient.postVeiculo(mapper.map(veiculoRequest, VeiculoLegadoRequest.class));
        return mapper.map(veiculoClient, Veiculo.class);
    }

    public Veiculo putVeiculo(VeiculoRequest veiculoRequest, Integer id){
        veiculoRequest.setId(id);
        VeiculoLegadoResponse veiculoClient = legadoClient.putVeiculo(mapper.map(veiculoRequest, VeiculoLegadoRequest.class));
        return mapper.map(veiculoClient, Veiculo.class);
    }

    public String deleteVeiculo(Integer id){
        return legadoClient.deleteVeiculo(id);
    }

    public Veiculo getVeiculo(Integer id){
        List<VeiculoLegadoResponse> listResponseClient = legadoClient.getVeiculos();
        VeiculoLegadoResponse responseClient = listResponseClient.stream()
                .filter(veiculo -> veiculo.getId() == id)
                .findAny().orElse(null);
        return responseClient != null ? mapper.map(responseClient, Veiculo.class) : null;
    }

    private Page<Veiculo> paginarLista(List<Veiculo> lista, Pageable pageable){
        int inicio, fim;
        inicio = (int) pageable.getOffset();
        fim = (inicio + pageable.getPageSize()) > lista.size() ? lista.size() : (inicio + pageable.getPageSize());
        Page<Veiculo> paginacao = new PageImpl<Veiculo>(lista.stream().collect(Collectors.toList()).subList(inicio, fim), pageable, lista.size());
        return paginacao;
    }
}
