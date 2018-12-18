package br.com.linkdata.myApp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.linkdata.myApp.model.Empresa;
import br.com.linkdata.myApp.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public Empresa getEmpresa(int codigo) {
		return empresaRepository.findById(codigo).get();
	}

	public List<Empresa> getEmpresas() {
		return empresaRepository.findAll();
	}

	public void salvar(@Valid Empresa empresa) {
		empresaRepository.saveAndFlush(empresa);
	}

	public void deleta(Integer id) {
		empresaRepository.deleteById(id);
	}

}
