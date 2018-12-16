package br.com.linkdata.myApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.linkdata.myApp.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	@Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.empresa WHERE f.empresa.codigo = ?1")
	List<Funcionario> findByEmpresa(Integer codigo);
	
}
