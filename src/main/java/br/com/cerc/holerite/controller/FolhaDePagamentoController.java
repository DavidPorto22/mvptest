package br.com.cerc.holerite.controller;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cerc.holerite.persistence.dto.FolhaDePagamentoDTO;
import br.com.cerc.holerite.service.FolhaDePagamentoService;

@RestController
@RequestMapping("/api/v1/folha")
public class FolhaDePagamentoController {
	private final FolhaDePagamentoService folhaDePagamentoService;
	
	public FolhaDePagamentoController(FolhaDePagamentoService folhaDePagamentoService) {
		this.folhaDePagamentoService = folhaDePagamentoService;
	}

	@ApiOperation(value = "Busca Folha por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna folha existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return new ResponseEntity<>(folhaDePagamentoService.findById(id),HttpStatus.OK);
	}

	@ApiOperation(value = "Busca lista de folhas no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna lista de folhas"),
			@ApiResponse(code = 204, message = "Retorno sem folha")
	})
	@GetMapping("/todas")
	public ResponseEntity<?> listAll(Pageable pageable) {
		return new ResponseEntity<>(folhaDePagamentoService.listAll(pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Busca folha por nome funcionario")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna folha existente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping
	public ResponseEntity<?> listAllByFunc(@RequestParam(value="funcId", defaultValue="") String id) {
		return new ResponseEntity<>(folhaDePagamentoService.listAllByFunc(Long.parseLong(id)), HttpStatus.OK);
	}

	@ApiOperation(value = "Salva nova folha no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna folha cadastrada")
	})
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid FolhaDePagamentoDTO dto){
		return new ResponseEntity<>(folhaDePagamentoService.save(dto), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Deletar folha existente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Caso deletado!"),
			@ApiResponse(code = 400, message = "Id de folha invalido")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		folhaDePagamentoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Atualizar folha existente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna folha atualizado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<?> replace(@RequestBody @Valid FolhaDePagamentoDTO dto, @PathVariable long id) {
		folhaDePagamentoService.replace(dto, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
