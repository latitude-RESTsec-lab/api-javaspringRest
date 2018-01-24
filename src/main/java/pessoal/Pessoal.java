package pessoal;

public class Pessoal {
	
	private Integer id;
	private Integer siape;
	private Integer idPessoa;
	private String nome;
	private Integer matriculaInterna;
	private String nomeIdentificacao;
	private String dataNascimento;
	private String sexo;
	
    public Pessoal(Integer id, Integer siape, Integer idPessoa, String nome, Integer matriculaInterna
    		, String nomeIdentificacao, String dataNascimento, String sexo) {
        this.id = id;
        this.siape = siape;
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.matriculaInterna = matriculaInterna;
        this.nomeIdentificacao = nomeIdentificacao;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }
    public Pessoal() {
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSiape() {
		return siape;
	}
	public void setSiape(Integer siape) {
		this.siape = siape;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getMatriculaInterna() {
		return matriculaInterna;
	}
	public void setMatriculaInterna(Integer matriculaInterna) {
		this.matriculaInterna = matriculaInterna;
	}
	public String getNomeIdentificacao() {
		return nomeIdentificacao;
	}
	public void setNomeIdentificacao(String nomeIdentificacao) {
		this.nomeIdentificacao = nomeIdentificacao;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	} 
	
	
	
}
