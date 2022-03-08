
public class ContaCorrente extends Conta {
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}
	
	@Override
	public void extrato(Banco banco) {
		System.out.println("\nCONTA CORRENTE");
		super.extrato(banco);
	}
	
}
