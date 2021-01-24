package br.com.luiza.client;

import br.com.luiza.application.exception.ErroApiExternaException;
import br.com.luiza.client.enumarations.OperacaoEnum;
import br.com.luiza.core.veiculo.domain.request.ApiLegadoRequest;
import br.com.luiza.core.veiculo.domain.request.VeiculoLegadoRequest;
import br.com.luiza.core.veiculo.domain.response.VeiculoLegadoResponse;
import br.com.luiza.core.veiculo.domain.response.VeiculoResponse;
import br.com.luiza.core.veiculo.domain.request.VeiculoRequest;
import br.com.luiza.util.JsonParse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@AllArgsConstructor
public class LegadoClient {

    private String legadoUrl;
    private RestTemplate restTemplate;

    public List<VeiculoLegadoResponse> getVeiculos(){
        final String json = JsonParse.toJson(new ApiLegadoRequest(OperacaoEnum.CONSULTAR.getName()));
        HttpEntity<String> entity = getHttpEntity(json);
        ResponseEntity<VeiculoLegadoResponse[]> response = restTemplate.postForEntity(legadoUrl, entity, VeiculoLegadoResponse[].class);
        return Arrays.asList(response.getBody());
    }

    public VeiculoLegadoResponse postVeiculo(VeiculoLegadoRequest request){
        ApiLegadoRequest operacao = new ApiLegadoRequest(OperacaoEnum.CRIAR.getName());
        operacao.setVeiculo(request);
        final String json = JsonParse.toJson(operacao);
        HttpEntity<String> entity = getHttpEntity(json);
        ResponseEntity<String> response = restTemplate.postForEntity(legadoUrl, entity, String.class);
        if(response.getBody().contains("mensagem")){
            throw new ErroApiExternaException(" Erro na chamada da api legado: " + response.getBody());
        }
        return (VeiculoLegadoResponse) JsonParse.fromJson(response.getBody(), new VeiculoLegadoResponse());
    }

    public VeiculoLegadoResponse putVeiculo(VeiculoLegadoRequest request){
        ApiLegadoRequest operacao = new ApiLegadoRequest(OperacaoEnum.ALTERAR.getName());
        operacao.setVeiculo(request);
        final String json = JsonParse.toJson(operacao);
        HttpEntity<String> entity = getHttpEntity(json);
        ResponseEntity<String> response = restTemplate.postForEntity(legadoUrl, entity, String.class);
        if(response.getBody().contains("mensagem")){
            throw new ErroApiExternaException(" Erro na chamada da api legado: " + response.getBody());
        }
        return (VeiculoLegadoResponse) JsonParse.fromJson(response.getBody(), new VeiculoLegadoResponse());
    }

    public String deleteVeiculo(Integer id){
        ApiLegadoRequest operacao = new ApiLegadoRequest(OperacaoEnum.APAGAR.getName());
        operacao.setVeiculo(new VeiculoLegadoRequest(id));
        final String json = JsonParse.toJson(operacao);
        HttpEntity<String> entity = getHttpEntity(json);
        ResponseEntity<String> response = restTemplate.postForEntity(legadoUrl, entity, String.class);
        return response.getBody();
    }

    private HttpEntity<String> getHttpEntity(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<String>(json, headers);
    }
}
