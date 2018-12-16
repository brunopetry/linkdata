package br.com.linkdata.myApp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.linkdata.myApp.model.Empresa;
import br.com.linkdata.myApp.service.EmpresaService;

@SpringBootApplication
public class MyAppApplication 
//implements CommandLineRunner 
{

//	@Autowired
//	EmpresaService empresaService;

	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}
	
//	@RequestMapping("/")
//	@ResponseBody
//	public String ola(){
//		System.out.println(">>>>>>>>>>>>>>>>>> teste");
//		return "Página inicial.</br>";
//
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(">>>>>>>>>>>>>> bruno");
//		Empresa e = empresaService.getEmpresa(1);
//		System.out.println(e);
//
//	}

}
