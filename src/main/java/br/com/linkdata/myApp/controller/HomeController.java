package br.com.linkdata.myApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.linkdata.myApp.model.Empresa;
import br.com.linkdata.myApp.model.Funcionario;
import br.com.linkdata.myApp.service.EmpresaService;
import br.com.linkdata.myApp.service.FuncionarioService;

@Controller
public class HomeController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		List<Empresa> empresas = empresaService.getEmpresas();
		model.addObject("empresas", empresas);

		List<Funcionario> funcionarios = funcionarioService.getFuncionarios(empresas.get(0).getCodigo());
		model.addObject("funcionarios", funcionarios);
		return model;
	}
	
	@GetMapping("/novaEmpresa")
	public ModelAndView novaEmpresa(Empresa empresa) {

		ModelAndView mv = new ModelAndView("/formularioEmpresa");
		mv.addObject("empresa", empresa);

		return mv;
	}

	@PostMapping("/salvarEmpresa")
	public ModelAndView salvarEmpresa(@Valid Empresa empresa, BindingResult result) {

		if (result.hasErrors()) {
			return novaEmpresa(empresa);
		}

		empresaService.salvar(empresa);

		return home();
	}

	@GetMapping("/editarEmpresa/{codigo}")
	public ModelAndView editarEmpresa(@PathVariable("codigo") Integer codigo) {

		return novaEmpresa(empresaService.getEmpresa(codigo));
	}

	@GetMapping("/deletaEmpresa/{codigo}")
	public ModelAndView deletaEmpresa(@PathVariable("codigo") Integer codigo) {

		empresaService.deleta(codigo);

		return home();
	}
	
	@GetMapping("/funcionarios/{codigoEmpresa}")
	public ModelAndView funcionarios(@PathVariable("codigoEmpresa") Integer codigoEmpresa) {
		
		ModelAndView model = new ModelAndView("funcionarios");
		List<Funcionario> funcionarios = funcionarioService.getFuncionarios(codigoEmpresa);
		model.addObject("funcionarios", funcionarios);
		
		return model;
	}

}
