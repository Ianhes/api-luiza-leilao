package br.com.luiza.application.web.openapi;

import br.com.luiza.core.veiculo.domain.request.VeiculoRequest;
import br.com.luiza.core.veiculo.domain.response.VeiculoResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Api(tags = "Leilão de Veiculos")
public interface ControllerOpenApi {

    @ApiOperation(value = "Buscar veiculos", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar veiculos", response = VeiculoResponse.class),
            @ApiResponse(code = 204, message = "Nenhum veiculo encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lote", value = "Lote dos veiculos", required = false, dataType = "String", example = "0196"),
            @ApiImplicitParam(name = "marca", value = "Marca dos veiculos", required = false, dataType = "String", example = "NISSAN"),
            @ApiImplicitParam(name = "modelo", value = "Modelo dos veiculos", required = false, dataType = "String", example = "LIVINA"),
            @ApiImplicitParam(name = "ano_modelo", value = "Ano Modelo para busca combinada do Ano Fabricação", required = false, dataType = "String", example = "2020"),
            @ApiImplicitParam(name = "ano_fabricacao", value = "Ano Fabricação para busca combinada do Ano Modelo", required = false, dataType = "String", example = "2020"),
            @ApiImplicitParam(name = "ate_fabricacao", value = "Ano Fabricação para busca de veiculos no range de anos", required = false, dataType = "String", example = "2020"),
            @ApiImplicitParam(name = "de_fabricacao", value = "Ano Fabricação para busca de veiculos no range de anos", required = false, dataType = "String", example = "2020"),
            @ApiImplicitParam(name = "sort", value = "Definir ordenação ", required = false, dataType = "String", example = "lance,asc")
    })
    public Page<VeiculoResponse> getVeiculos(String lote, String marca, String modelo, Integer anoModelo, Integer anoFabricacao,
                                             Integer ateFabricacao, Integer deFabricacao,  @Valid @RequestParam(required = false) @Size(min = 2, max = 2) List<String> sort,
                                             @PageableDefault(size = 10) Pageable pageable);

    @ApiOperation(value = "Buscar veiculo por id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Buscar veiculo por id", response = VeiculoResponse.class),
            @ApiResponse(code = 204, message = "Nenhum veiculo encontrado"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID do veiculo", required = true, dataType = "int", example = "1")
    public ResponseEntity<VeiculoResponse> getVeiculo(@RequestParam Integer id);

    @ApiOperation(value = "Gravar um veiculo", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Gravar um veiculo", response = VeiculoResponse.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    public ResponseEntity<VeiculoResponse> postVeiculo(@Valid @RequestBody VeiculoRequest request);

    @ApiOperation(value = "Alterar veiculo por id", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Alterar veiculo por id", response = VeiculoResponse.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID do veiculo", required = true, dataType = "int", example = "1")
    public ResponseEntity<VeiculoResponse> putVeiculo(@PathVariable Integer id, @RequestBody VeiculoRequest request);

    @ApiOperation(value = "Deletar veiculo por id", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deletar veiculo por id", response = VeiculoResponse.class),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado", response = Problem.class) })
    @ApiImplicitParam(name = "id", value = "ID do veiculo", required = true, dataType = "int", example = "1")
    public ResponseEntity<String> deleteVeiculo(@PathVariable Integer id);
}
