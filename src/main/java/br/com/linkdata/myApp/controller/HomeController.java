package br.com.linkdata.myApp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class HomeController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		List<Empresa> empresas = empresaService.getEmpresas();
		mv.addObject("empresas", empresas);

		// List<Funcionario> funcionarios =
		// funcionarioService.getFuncionarios(empresas.get(0).getCodigo());
		// mv.addObject("funcionarios", funcionarios);
		return mv;
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

		ModelAndView mv = new ModelAndView("funcionarios");
		List<Funcionario> funcionarios = funcionarioService.getFuncionarios(codigoEmpresa);
		mv.addObject("funcionarios", funcionarios);
		mv.addObject("codigoEmpresa", codigoEmpresa);

		return mv;
	}

	@GetMapping("/novoFuncionario/{codigoEmpresa}")
	public ModelAndView novoFuncionario(@PathVariable("codigoEmpresa") Integer codigoEmpresa, Funcionario funcionario) {

		ModelAndView mv = new ModelAndView("/formularioFuncionario");
		mv.addObject("funcionario", funcionario);
		mv.addObject("codigoEmpresa", codigoEmpresa);

		funcionario.setEmpresa(empresaService.getEmpresa(codigoEmpresa));

		// mv.addObject("empresas", empresaService.getEmpresas());

		return mv;
	}

	@PostMapping("/salvarFuncionario/{codigoEmpresa}")
	public ModelAndView salvarFuncionario(@PathVariable("codigoEmpresa") Integer codigoEmpresa,
			@Valid Funcionario funcionario, BindingResult result) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("codigoEmpresa", codigoEmpresa);
		if (result.hasErrors()) {
			return novoFuncionario(codigoEmpresa, funcionario);
		}

		funcionarioService.salvar(funcionario);

		return funcionarios(codigoEmpresa);
	}

	@GetMapping("/editarFuncionario/{codigo}/{codigoEmpresa}")
	public ModelAndView editarFuncionario(@PathVariable("codigo") Integer codigo,
			@PathVariable("codigoEmpresa") Integer codigoEmpresa) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("codigoEmpresa", codigoEmpresa);
		return novoFuncionario(codigoEmpresa, funcionarioService.getFuncionario(codigo));
	}

	@GetMapping("/deletaFuncionario/{codigo}/{codigoEmpresa}")
	public ModelAndView deletaFuncionario(@PathVariable("codigo") Integer codigo,
			@PathVariable("codigoEmpresa") Integer codigoEmpresa) {

		funcionarioService.deleta(codigo);

		return funcionarios(codigoEmpresa);
	}

	@PostMapping(value = "/exportarEmpresas")
	public void export(ModelAndView model, HttpServletResponse response) throws IOException, JRException, SQLException {
		List<Empresa> empresas = empresaService.getEmpresas();

		JasperPrint jasperPrint = null;

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"relatorio-empresas.pdf\""));

		OutputStream out = response.getOutputStream();
		jasperPrint = exportPdfFile("empresa", empresas);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}

	private JasperPrint exportPdfFile(String report, List<?> lista) throws JRException, IOException {
		String path = resourceLoader.getResource("classpath:jasper/" + report + ".jrxml").getURI().getPath();
		JasperReport jasperReport = JasperCompileManager.compileReport(path);
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, null, ds);
		return print;
	}

}
