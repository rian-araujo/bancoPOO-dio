
public class Cliente {

	public String nome;
	public String cpfCnpj;
	
	public Cliente(String nome, String cpfCnpj) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
