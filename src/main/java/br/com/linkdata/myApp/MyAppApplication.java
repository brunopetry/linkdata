package br.com.linkdata.myApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyAppApplication 
//implements CommandLineRunner 
{

//	@Autowired
//	public EmpresaService empresaService;

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
//		try{
//			List<Empresa> empresas = empresaService.getEmpresas();
//			EmpresaREL relatorio = new EmpresaREL();
//			relatorio.imprimir(empresas);
//		}
//		catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//	}

}
