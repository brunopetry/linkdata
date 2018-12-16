package br.com.linkdata.myApp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.linkdata.myApp.model.Funcionario;
import br.com.linkdata.myApp.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario getFuncionario(int codigo) {
		return funcionarioRepository.findById(codigo).get();
	}

	public List<Funcionario> getFuncionarios(Integer codigo) {
		return funcionarioRepository.findByEmpresa(codigo);
	}

	public void salvar(@Valid Funcionario funcionario) {
		funcionarioRepository.saveAndFlush(funcionario);
	}

	public void deleta(Integer codigo) {
		funcionarioRepository.deleteById(codigo);
	}
}
