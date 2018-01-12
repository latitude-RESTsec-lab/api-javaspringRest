package application;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pessoal.Pessoal;
import pessoal.PessoalDAO;


@RestController 
public class PessoalController {

    @RequestMapping("/api/servidores")
    public List<Pessoal> listaPessoal(){
    	PessoalDAO dao = new PessoalDAO();
    	return dao.getPessoal();
    }
    @RequestMapping("/api/servidor/{matriculaInterna}")
    public Pessoal servidor(@PathVariable("matriculaInterna") Integer matriculaInterna) {
    	PessoalDAO dao = new PessoalDAO();
    	return dao.getPessoa(matriculaInterna);
    	
    }
}
