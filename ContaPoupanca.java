
public class ContaPoupanca extends Conta{
	
	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}
	
	@Override
	public void extrato(Banco banco) {
		System.out.println("\nCONTA POUPANCA");
		super.extrato(banco);
	}
}
