import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {

		char comando;
		double valor, saldo;
		int conta, ultimoRegistroCli, ultimoRegistroCc, ultimoRegistroCp, flagconta;
		String nome, cpfCnpj;

		ArrayList<Cliente> listaclientes = new ArrayList<>();
		ArrayList<ContaCorrente> listacontacc = new ArrayList<>();
		ArrayList<ContaPoupanca> listacontacp = new ArrayList<>();

		Scanner scan = new Scanner(System.in);
		Scanner leitor = new Scanner(System.in);

		Banco banco = new Banco("DINHEIRO S.A.", "12.345.678/9990-00");

		// -------------------------------------------------------------------

		listaclientes.add(new Cliente("Joao", "123.456.789-12"));
		ultimoRegistroCli = listaclientes.size() - 1;
		listacontacc.add(new ContaCorrente(listaclientes.get(ultimoRegistroCli)));
		listacontacp.add(new ContaPoupanca(listaclientes.get(ultimoRegistroCli)));

		ultimoRegistroCc = listacontacc.size() - 1;
		ultimoRegistroCp = listacontacp.size() - 1;
		listacontacc.get(ultimoRegistroCc).depositar(1000);
		listacontacc.get(ultimoRegistroCc).transferir(250, listacontacp.get(ultimoRegistroCp));
		listacontacc.get(ultimoRegistroCc).sacar(300);

		listacontacc.get(ultimoRegistroCc).extrato(banco);
		listacontacp.get(ultimoRegistroCp).extrato(banco);

		// -------------------------------------------------------------------

		listaclientes.add(new Cliente("MARIA", "000.111.222-33"));
		ultimoRegistroCli = listaclientes.size() - 1;
		listacontacc.add(new ContaCorrente(listaclientes.get(ultimoRegistroCli)));
		listacontacp.add(new ContaPoupanca(listaclientes.get(ultimoRegistroCli)));

		ultimoRegistroCc = listacontacc.size() - 1;
		ultimoRegistroCp = listacontacp.size() - 1;
		listacontacc.get(ultimoRegistroCc).depositar(2000);
		listacontacc.get(ultimoRegistroCc).transferir(750, listacontacp.get(ultimoRegistroCp));
		listacontacc.get(ultimoRegistroCc).sacar(600);

		listacontacc.get(ultimoRegistroCc).extrato(banco);
		listacontacp.get(ultimoRegistroCp).extrato(banco);

		// -------------------------------------------------------------------

		do {

			System.out.println("Escolha uma das opções abaixo:");

			System.out.println("a - criar nova conta");
			System.out.println("b - sacar valor");
			System.out.println("c - depositar valor");
			System.out.println("d - transferir valor");
			System.out.println("x - sair do programa");
			comando = scan.next().charAt(0);

			switch (comando) {

			// -----------------------------------------------------------------------------------------------

			case 'a': // criar nova conta
				System.out.println("Qual o nome do novo cliente?");
				nome = leitor.nextLine();
				System.out.println("Qual o CPF/CNPJ do novo cliente?");
				cpfCnpj = leitor.nextLine();
				listaclientes.add(new Cliente(nome, cpfCnpj));
				ultimoRegistroCli = listaclientes.size() - 1;
				listacontacc.add(new ContaCorrente(listaclientes.get(ultimoRegistroCli)));
				listacontacp.add(new ContaPoupanca(listaclientes.get(ultimoRegistroCli)));

				System.out.println("nova conta criada!!");

				ultimoRegistroCc = listacontacc.size() - 1;
				ultimoRegistroCp = listacontacp.size() - 1;
				listacontacc.get(ultimoRegistroCc).extrato(banco);
				listacontacp.get(ultimoRegistroCp).extrato(banco);

				break;

			// -----------------------------------------------------------------------------------------------

			case 'b': // sacar
				System.out.println("Qual valor gostaria de sacar?");
				valor = leitor.nextDouble();
				System.out.println("De qual conta?");
				conta = leitor.nextInt();

				if (conta % 2 == 0) {
					conta = (conta / 2) - 1;
					saldo = listacontacp.get(conta).getSaldo();
					flagconta = 0;
				}

				else {
					conta = ((conta + 1) / 2) - 1;
					saldo = listacontacc.get(conta).getSaldo();
					flagconta = 1;
				}

				if (saldo < valor) {
					System.out.println("Desculpe, não há saldo suficiente para a operação.");
					System.out.printf("Saldo atual = %.2f\n\n", saldo);
				}

				else {
					if (flagconta == 1) {
						listacontacc.get(conta).sacar(valor);
						listacontacc.get(conta).extrato(banco);
					}

					else {
						listacontacp.get(conta).sacar(valor);
						listacontacp.get(conta).extrato(banco);
					}

					System.out.println("Saque efetuado com sucesso");

				}

				break;

			// -----------------------------------------------------------------------------------------------

			case 'c': // depositar

				System.out.println("Qual valor gostaria de depositar?");
				valor = leitor.nextDouble();
				System.out.println("Para qual conta?");
				conta = leitor.nextInt();
				ultimoRegistroCp = (listacontacp.size() + 1) * 2;

				if (conta <= ultimoRegistroCp) {
					if (conta % 2 == 0) {
						conta = (conta / 2) - 1;
						flagconta = 0;
					}

					else {
						conta = ((conta + 1) / 2) - 1;
						flagconta = 1;
					}

					if (flagconta == 1) {
						listacontacc.get(conta).depositar(valor);
						listacontacc.get(conta).extrato(banco);
					}

					else {
						listacontacp.get(conta).depositar(valor);
						listacontacp.get(conta).extrato(banco);
					}
				}

				else
					System.out.println("Conta inexistente!");

				break;

			case 'd': // transferir

				System.out.println("Qual a conta origem?");
				int contaOrig = leitor.nextInt();
				System.out.println("Qual a conta destino?");
				int contaDest = leitor.nextInt();
				int flagContaOrig, flagContaDest;
				ultimoRegistroCp = (listacontacp.size() + 1) * 2;

				if (contaOrig <= ultimoRegistroCp && contaDest <= ultimoRegistroCp) {

					System.out.println("Qual valor será transferido?");
					valor = leitor.nextDouble();

					if (contaOrig % 2 == 0) {
						contaOrig = (contaOrig / 2) - 1;
						saldo = listacontacp.get(contaOrig).getSaldo();
						flagContaOrig = 0;
					}

					else {
						contaOrig = ((contaOrig + 1) / 2) - 1;
						saldo = listacontacc.get(contaOrig).getSaldo();
						flagContaOrig = 1;
					}

					if (saldo < valor) {
						System.out.println("Desculpe, não há saldo suficiente para a operação.");
						System.out.printf("Saldo atual = %.2f\n\n", saldo);
					}

					else {
						// -----------------------------------------------------------------------

						if (contaDest % 2 == 0) {
							contaDest = (contaDest / 2) - 1;
							flagContaDest = 0;
						}

						else {
							contaDest = ((contaDest + 1) / 2) - 1;
							flagContaDest = 1;
						}

						// ------------------------------------------------------------------------

						if (flagContaOrig == 1) {
							if (flagContaDest == 1) {

								listacontacc.get(contaOrig).transferir(valor, listacontacc.get(contaDest));

								listacontacc.get(contaOrig).extrato(banco);
								listacontacc.get(contaDest).extrato(banco);
							} else {

								listacontacc.get(contaOrig).transferir(valor, listacontacp.get(contaDest));

								listacontacc.get(contaOrig).extrato(banco);
								listacontacp.get(contaDest).extrato(banco);
							}

						}

						else {
							if (flagContaDest == 1) {

								listacontacp.get(contaOrig).transferir(valor, listacontacc.get(contaDest));

								listacontacp.get(contaOrig).extrato(banco);
								listacontacc.get(contaDest).extrato(banco);
							} else {

								listacontacp.get(contaOrig).transferir(valor, listacontacp.get(contaDest));

								listacontacp.get(contaOrig).extrato(banco);
								listacontacp.get(contaDest).extrato(banco);
							}
						}
					}
				}

				else
					System.out.println("Conta inexistente!");

				break;

			case 'x': // sair do programa
				break;

			case 'X': // sair do programa
				break;

			default:
				System.out.print("digite uma opção apresentada: \n\n");
				break;
			}

		} while (comando != 'x' && comando != 'X');

		System.out.println("Opção sair do programa escolhida! ");
		System.out.println("--- !! FIM DO PROGRAMA !! ---");
	}

}
