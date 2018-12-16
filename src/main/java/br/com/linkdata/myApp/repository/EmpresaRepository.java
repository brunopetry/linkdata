package br.com.linkdata.myApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.linkdata.myApp.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
