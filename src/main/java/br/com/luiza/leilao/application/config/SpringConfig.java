package br.com.luiza.application.config;

import br.com.luiza.client.LegadoClient;
import br.com.luiza.controller.VeiculoController;
import br.com.luiza.core.veiculo.service.VeiculoService;
import br.com.luiza.core.veiculo.usecase.VeiculoUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableAutoConfiguration
public class SpringConfig {

    @Value("${legado.url}")
    private String legadoUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(10000)).build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public LegadoClient legadoClient() {
        return new LegadoClient(legadoUrl, restTemplate());
    }

    @Bean
    public VeiculoUseCase veiculoUseCase(){
        return new VeiculoUseCase();
    }

    @Bean
    public VeiculoService veiculoService() {
        return new VeiculoService(legadoClient(), modelMapper(),veiculoUseCase());
    }

    @Bean
    public VeiculoController veiculoController() {
        return new VeiculoController(veiculoService(), modelMapper());
    }

}

