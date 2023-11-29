package com.lumi.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.lumi.PrjEmpresa.entities.Departamento;
import com.lumi.PrjEmpresa.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {

	private final DepartamentoRepository departamentoRepository;
	
	public DepartamentoService(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}
	
	//inserir
	public Departamento savedepartamento(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}
	
	//listar por id
	public Departamento getDepartamentoById(Long depcodigo) {
		return departamentoRepository.findById(depcodigo).orElse(null);
	}
	
	//listar todos
	public List<Departamento> getAllDepartamentos(){
		return departamentoRepository.findAll();
	}
	
	// apagar
	public void deleteDepartamento(Long depcodigo) {
		departamentoRepository.deleteById(depcodigo);
	}
	
	//update com optional 
	public Departamento updateDepartamento(Long depcodigo, Departamento novoDepartamento) {
		Optional<Departamento> departamentoOptional = departamentoRepository.findById(depcodigo);
		if (departamentoOptional.isPresent()) {
			Departamento departamentoExistente = departamentoOptional.get();
			departamentoExistente.setDepnome(novoDepartamento.getDepnome());
			departamentoExistente.setDepcodigo(novoDepartamento.getDepcodigo());
			  return departamentoRepository.save(departamentoExistente); 
        } else {
            return null; 
		}
	}
}
