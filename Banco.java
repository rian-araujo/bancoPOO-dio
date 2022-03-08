import java.util.List;

public class Banco{
	
		protected String nome;
		protected String cnpj;
		protected List<Conta> contas;

		
		
		public Banco(String nome, String cnpj) {
			super();
			this.nome = nome;
			this.cnpj = cnpj;
		}


		public String getCnpj() {
			return cnpj;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public List<Conta> getContas() {
			return contas;
		}

		public void setContas(List<Conta> contas) {
			this.contas = contas;
		}		
		
}