package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pessoal.Pessoal;
import pessoal.PessoalDAO;


@RestController
public class PessoalController {

    @RequestMapping("/api/servidores")
    public ResponseEntity listaPessoal(){
    	PessoalDAO dao = new PessoalDAO();
    	List<Pessoal> l = dao.getPessoal();
    	if(l==null) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}else {
    	return new ResponseEntity<List<Pessoal>> (l, HttpStatus.OK);
    	}
    }
    @RequestMapping("/api/servidor/{matriculaInterna}")
    @SuppressWarnings("unchecked")
    public ResponseEntity servidor(@PathVariable("matriculaInterna") Integer matriculaInterna) {
    	PessoalDAO dao = new PessoalDAO();
		ResponseEntity<Pessoal> p = dao.getPessoa(matriculaInterna);
		if(p.getBody().getMatriculaInterna()==null) {
			System.out.println("entrou no nulo");
		}
    	return p;
    	
    }
    @RequestMapping(value="/api/servidor/", method=RequestMethod.POST)
    public ResponseEntity post(@RequestBody Pessoal pessoal) {
    	String regex = ("^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)$");
    	List<String> m = new ArrayList<>();
    	boolean matches = true;
        if(!Pattern.matches(regex, pessoal.getDataNascimento())){
        	m.add("[data_nascimento] failed to match API requirements."+
        "It should look like this: 1969-02-12T00:00:00Z");
        	matches = false;
        }
        regex = "^([A-Z][a-z]+([ ]?[a-z]?['-]?[A-Z][a-z]+)*)$";
        if(!Pattern.matches(regex, pessoal.getNome())){
        	m.add(" [nome] failed to match API requirements. "
        			+ "It should look like this: Firstname Middlename(optional) Lastname");
        	matches = false;
        }
        if(!Pattern.matches(regex, pessoal.getNomeIdentificacao())){
        	m.add("[nome_identificacao] failed to match API requirements. "
        			+ "It should look like this: Firstname Middlename(optional) Lastname");
        	matches = false;
        }
        regex = "\\b[MF]{1}\\b";
        if(!Pattern.matches(regex, pessoal.getSexo())){
        	m.add("[sexo] failed to match API requirements."
        			+ " It should look like this: M for male, F for female");
        	matches = false;
        }
        if(!matches){
        	 return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        try {
			MessageDigest mD = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, mD.digest((pessoal.getNome()+df.format(dateobj)).getBytes()));
			hash = hash.mod(BigInteger.valueOf(Long.parseLong("99999")));
			pessoal.setMatriculaInterna(hash.intValue());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        PessoalDAO dao = new PessoalDAO();
        if(dao.insertPessoa(pessoal).getStatusCode().is2xxSuccessful()) {
        	return new ResponseEntity<Integer>(pessoal.getMatriculaInterna(), HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	//return new ResponseEntity<Pessoal>(pessoal, HttpStatus.OK); retorna o objeto criado
    }

}
