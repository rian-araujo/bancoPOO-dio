
public abstract class Conta {
	
	protected static final int AGENCIA_PADRAO = 1;
	protected static int SEQUENCIAL = 0;
	
	protected int numero;
	protected int agencia;
	protected double saldo;
	protected Cliente cliente;
	
	public Conta (Cliente cliente) {
		this.numero = ++SEQUENCIAL;
		this.agencia = Conta.AGENCIA_PADRAO;
		this.cliente = cliente;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getAgencia() {
		return agencia;
	}
	public double getSaldo() {
		return saldo;
	}

	public void sacar(double valor) {
		saldo -= valor;
	}
	
	public void depositar(double valor) {
		saldo += valor;
	}
	
	
	public void transferir(double valor, Conta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}
	
	protected void extrato(Banco banco){
		
		System.out.printf("\n ------- EMISSÃO DE EXTRATO ------- \n");
		System.out.printf("\n BANCO: %s ", banco.getNome());
		System.out.printf("\n CNPJ: %s ", banco.getCnpj());
		System.out.printf("\n\n ----- ----- ----- ----- ----- ----- \n");
		System.out.printf("\n Cliente: %s ", cliente.getNome());
		System.out.printf("\n CPF/CNPJ: %s ", cliente.getCpfCnpj());
		System.out.printf("\n Agencia: %d ", agencia);
		System.out.printf("\n Conta: %d ", numero);
		System.out.printf("\n Saldo: %.2f\n\n", saldo);
		
	}
	
}
