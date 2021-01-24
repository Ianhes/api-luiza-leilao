package br.com.luiza.application.web;

import br.com.luiza.application.exception.RecursoNaoEncontradoException;
import br.com.luiza.application.web.openapi.ControllerOpenApi;
import br.com.luiza.controller.VeiculoController;
import br.com.luiza.core.veiculo.domain.request.VeiculoRequest;
import br.com.luiza.core.veiculo.domain.response.VeiculoResponse;
import br.com.luiza.core.veiculo.search.VeiculoParametroFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;


@RestController
@Validated
@RequestMapping("/leilao")
public class SpringController implements ControllerOpenApi {

    @Autowired
    private VeiculoController controller;

    @GetMapping(path = "/veiculos")
    public Page<VeiculoResponse> getVeiculos(
           @RequestParam(required = false) String lote, @RequestParam(required = false) String marca,
           @RequestParam(required = false) String modelo, @RequestParam(value = "ano_modelo", required = false) Integer anoModelo,
           @RequestParam(value = "ano_fabricacao", required = false) Integer anoFabricacao, @RequestParam(value = "ate_fabricacao", required = false) Integer ateFabricacao,
           @RequestParam(value = "de_fabricacao", required = false) Integer deFabricacao, @Valid @RequestParam(required = false) @Size(min = 2, max = 2) List<String> sort,
           @PageableDefault(size = 10) Pageable pageable)
    {
        VeiculoParametroFiltro param = new VeiculoParametroFiltro(lote, marca, modelo, anoModelo, anoFabricacao, ateFabricacao,deFabricacao, sort);
        return controller.getVeiculos(param, pageable);
    }

    @GetMapping(path = "/veiculo")
    public ResponseEntity<VeiculoResponse> getVeiculo(@RequestParam Integer id){
        VeiculoResponse response =  controller.getVeiculo(id);
        if(response == null){
            throw new RecursoNaoEncontradoException(" Veiculo não encontrado para o id " + id);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/veiculo")
    public ResponseEntity<VeiculoResponse> postVeiculo(@Valid @RequestBody VeiculoRequest request){
        VeiculoResponse response =  controller.postVeiculo(request);
        if(response == null){
            throw new RecursoNaoEncontradoException(" Erro ao devolver veículo criado ");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/veiculo/{id}")
    public ResponseEntity<VeiculoResponse> putVeiculo(@PathVariable Integer id, @RequestBody VeiculoRequest request){
        VeiculoResponse response =  controller.putVeiculo(request, id);
        if(response == null){
            throw new RecursoNaoEncontradoException(" Erro ao devolver veículo alterado ");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/veiculo/{id}")
    public ResponseEntity<String> deleteVeiculo(@PathVariable Integer id){
        String response =  controller.deleteVeiculo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
