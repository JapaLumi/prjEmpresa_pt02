package com.lumi.PrjEmpresa.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lumi.PrjEmpresa.entities.Departamento;
import com.lumi.PrjEmpresa.services.DepartamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Departamentos", description = "API TESTE DE GERECIANMENTO DE DEPARTAMENTOS" )
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}
	
	private final DepartamentoService departamentoService;
	
	@Autowired
	public DepartamentoController(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza departamento por ID")
	public ResponseEntity<Departamento> getDepartamento(@PathVariable Long depcodigo) {
		Departamento departamento = departamentoService.getDepartamentoById(depcodigo);
		if (departamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um departamento")
	public Departamento createDepartamento(@RequestBody Departamento departamento) {
		return departamentoService.savedepartamento(departamento);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um departamento")
	public void deleteDepartamento(@PathVariable Long depcodigo) {
		departamentoService.deleteDepartamento(depcodigo);
	}

	// Utilizando o ResponseEntity e RequestEntity
	@GetMapping
	@Operation(summary = "Apresenta todos os departamentos")
	public ResponseEntity<List<Departamento>> getAllDepartamentos(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Departamento> departamentos = departamentoService.getAllDepartamentos();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(departamentos);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera um departamento")
	public Departamento updateDepartamento(@PathVariable Long depcodigo, @RequestBody Departamento departamento) {
		return departamentoService.updateDepartamento(depcodigo, departamento);
	}


}
