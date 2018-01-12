package pessoal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoalDAO {
	
	public List<Pessoal> getPessoal() {
		List<Pessoal> listaPessoal = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://10.30.0.10:5432/administrativo",
					"sipac", "1qaz2wsxsipac");
			ResultSet rs = con.createStatement().executeQuery("select s.id_servidor, s.siape, s.id_pessoa, s.matricula_interna, s.nome_identificacao," + 
					"		p.nome, p.data_nascimento, p.sexo from rh.servidor s" + 
					"	inner join comum.pessoa p on s.id_pessoa = p.id_pessoa ");

			while(rs.next()) {
				Pessoal p = new Pessoal();
				p.setId(rs.getInt(1));
				p.setSiape(rs.getString(2));
				p.setIdPessoa(rs.getInt(3));
				p.setMatriculaInterna(rs.getString(4));
				p.setNomeIdentificacao(rs.getString(5));
				p.setNome(rs.getString(6));
				p.setDataNascimento(rs.getString(7));
				p.setSexo(rs.getString(8));
				listaPessoal.add(p);
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaPessoal;
	}
	public Pessoal getPessoa(Integer matriculaInterna) {
		Pessoal p = new Pessoal();
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://10.30.0.10:5432/administrativo",
					"sipac", "1qaz2wsxsipac");
			PreparedStatement st = con.prepareStatement("select s.id_servidor, s.siape, s.id_pessoa, s.matricula_interna, s.nome_identificacao," + 
					"		p.nome, p.data_nascimento, p.sexo from rh.servidor s" + 
					"	inner join comum.pessoa p on s.id_pessoa = p.id_pessoa where s.matricula_interna = ?");
			st.setInt(1, matriculaInterna);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				p.setId(rs.getInt(1));
				p.setSiape(rs.getString(2));
				p.setIdPessoa(rs.getInt(3));
				p.setMatriculaInterna(rs.getString(4));
				p.setNomeIdentificacao(rs.getString(5));
				p.setNome(rs.getString(6));
				p.setDataNascimento(rs.getString(7));
				p.setSexo(rs.getString(8));
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p;
	}
		
	}
