package pessoal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
				p.setSiape(rs.getInt(2));
				p.setIdPessoa(rs.getInt(3));
				p.setMatriculaInterna(rs.getInt(4));
				p.setNomeIdentificacao(rs.getString(5));
				p.setNome(rs.getString(6));
				p.setDataNascimento(rs.getDate(7).toString());
				p.setSexo(rs.getString(8));
				listaPessoal.add(p);
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			e.getMessage();
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
				p.setSiape(rs.getInt(2));
				p.setIdPessoa(rs.getInt(3));
				p.setMatriculaInterna(rs.getInt(4));
				p.setNomeIdentificacao(rs.getString(5));
				p.setNome(rs.getString(6));
				p.setDataNascimento(rs.getDate(7).toString());
				p.setSexo(rs.getString(8));
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return p;
	}
	public String insertPessoa(Pessoal pessoal){
		Connection con = null;
		//PreparedStatement st = null;
		Statement st = null;
		try{
			con = DriverManager.getConnection("jdbc:postgresql://10.30.0.10:5432/administrativo",
					"sipac", "1qaz2wsxsipac");
			String sql = "INSERT INTO rh.servidor_tmp(id_servidor, siape, id_pessoa, matricula_interna,"
					+ " id_foto, nome_identificacao,nome, data_nascimento, sexo)"
					+ " VALUES ("+pessoal.getId()+", "+pessoal.getSiape()+", "+pessoal.getIdPessoa()+", "+pessoal.getMatriculaInterna()+
					", null, '"+pessoal.getNomeIdentificacao()+"', '"+pessoal.getNome()+"', '"+pessoal.getDataNascimento()+"', '"+pessoal.getSexo()+"')";
			st=con.createStatement();			
			st.executeUpdate(sql);
		}catch(Exception e){

		}finally {

            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
				}
				return "200";

            } catch (Exception ex) {
				return "400";
            }

		}
	}
		
	}
