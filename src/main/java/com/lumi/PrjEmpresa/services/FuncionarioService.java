package com.lumi.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.lumi.PrjEmpresa.entities.Funcionario;
import com.lumi.PrjEmpresa.repositories.FuncionarioRepository;

public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// listar todos os funcionarios
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	// listar funcionario por id
	public Funcionario getFuncionarioById(long funcodigo) {
		return funcionarioRepository.findById(funcodigo).orElse(null);
	}

	// salvar funcionario
	public Funcionario saveFuncionario(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	// listar funcionario por nome
	public List<Funcionario> getFuncionariosByFunnomeAproximado(String funnome) {
        return funcionarioRepository.findByNomeContaining(funnome);
    }

	//deletar funcionario
	public boolean deleteFuncionario(Long id) {
		Optional<Funcionario> funcionarioExistente = funcionarioRepository.findById(id);
		if (funcionarioExistente.isPresent()) {
			funcionarioRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	// atualizar funcionarios
	public Funcionario updateFuncionario(Long funcodigo, Funcionario novoFuncionario) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcodigo);
		if (funcionarioOptional.isPresent()) {
			Funcionario funcionarioExistente = funcionarioOptional.get();
			funcionarioExistente.setFunnome(novoFuncionario.getFunnome());
			funcionarioExistente.setFunnascimento(novoFuncionario.getFunnascimento());
			funcionarioExistente.setFunsalario(novoFuncionario.getFunsalario());

			// Atualize o departamento
			if (novoFuncionario.getDepartamento() != null) {
				funcionarioExistente.setDepartamento(novoFuncionario.getDepartamento());
			}		
			return funcionarioRepository.save(funcionarioExistente);
		} else {
			return null; // Ou lançar uma exceção indicando que o funcionário não foi encontrado
		}
	}
}