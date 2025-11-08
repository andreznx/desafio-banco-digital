    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner entrada = new Scanner(System.in);
            Cliente cliente = null;
            Conta contaCorrente = null;
            Conta contaPoupanca = null;

            boolean cadastrado = false;
            boolean logado = false;

            while(true) {
                System.out.println("\n=== Banco Digital ===");
                System.out.println("1 - Cadastrar cliente");
                System.out.println("2 - Fazer login");
                System.out.println("3 - Sair");
                System.out.println("Escolha uma opção: ");
                int opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {
                    case 1:
                        // -- Cadastro ---
                        cliente = new Cliente();

                        System.out.print("Digite seu nome: ");
                        cliente.setNome(entrada.nextLine());

                        System.out.print("Digite seu CPF: ");
                        cliente.setCpf(entrada.nextLine());

                        System.out.print("Digite seu e-mail: ");
                        cliente.setEmail(entrada.nextLine());

                        System.out.println("Digite sua senha: ");
                        cliente.setSenha(entrada.nextLine());

                        contaCorrente = new ContaCorrente(cliente);
                        contaPoupanca = new ContaPoupanca(cliente);

                        System.out.println("\nCadastro realizado com sucesso!");
                        System.out.println("Agência: " + contaCorrente.getAgencia());
                        System.out.println("Número da Conta Corrente: " + contaCorrente.getNumero());
                        System.out.println("Número da Conta Poupança: " + contaPoupanca.getNumero());

                        cadastrado = true;
                        break;
                    case 2:
                        // Login
                        if (!cadastrado) {
                            System.out.println("Nenhum cliente cadastrado. Cadastre-se primeiro!");
                            break;
                        }
                        System.out.println("Digite seu e-mail: ");
                        String emailLogin = entrada.nextLine();

                        System.out.println("Digite sua senha: ");
                        String senhaLogin = entrada.nextLine();

                        if(emailLogin.equals(cliente.getEmail()) && senhaLogin.equals(cliente.getSenha())) {
                            System.out.println("Login realizado com sucesso!");
                            logado = true;
                        } else {
                            System.out.println("E-mail ou senha incorretos!");
                            break;
                        }

                        // Menu de Contas
                        while (logado) {
                            System.out.println("\nMenu de contas");
                            System.out.println("1 - Acessar Conta Corrente");
                            System.out.println("2 - Acessar Conta Poupança");
                            System.out.println("3 - Sair");
                            System.out.print("Escolha");
                            int tipoConta = entrada.nextInt();

                            Conta contaSelecionada = null;
                            switch (tipoConta) {
                                case 1:
                                    contaSelecionada = contaCorrente;
                                    break;
                                case 2:
                                    logado = false;
                                    System.out.println("Saindo da conta...");
                                    continue;
                                default:
                                    System.out.println("Opção inválida!");
                                    continue;

                            }

                            // Menu de Operações
                            int opcaoConta;
                            do {
                                System.out.println("\n--- Operações Bancárias ---");
                                System.out.println("1 - Depositar");
                                System.out.println("2 - Sacar");
                                System.out.println("3 - Transferir");
                                System.out.println("4 - Ver Saldo");
                                System.out.println("5 - Imprimir Extrato");
                                System.out.println("6 - Voltar");
                                System.out.println("Escolha: ");
                                opcaoConta = entrada.nextInt();

                                switch (opcaoConta) {
                                    case 1:
                                        System.out.println("Valor do depósito: ");
                                        double valorDep = entrada.nextDouble();
                                        contaSelecionada.depositar(valorDep);
                                        System.out.println("Depósito realizado!");
                                        break;
                                    case 2:
                                        System.out.print("Valor do saque: ");
                                        double valorSaq = entrada.nextDouble();
                                        contaSelecionada.sacar(valorSaq);
                                        System.out.println("Saque realizado!");
                                        break;
                                    case 3:
                                        System.out.print("Valor da transferência: ");
                                        double valorTransf = entrada.nextDouble();
                                        contaSelecionada.transferir(
                                                valorTransf,
                                                contaSelecionada == contaCorrente ? contaPoupanca : contaCorrente
                                        );
                                        System.out.println("Transferência realizada!");
                                        break;
                                    case 4:
                                        System.out.println("Saldo atual: R$" + contaSelecionada.getSaldo());
                                        break;
                                    case 5:
                                        contaSelecionada.imprimirExtrato();
                                        break;
                                    case 6:
                                        System.out.println("Voltando ao menu de contas...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                }
                            } while(opcaoConta != 6);
                        }
                        break;
                    case 3:
                        System.out.println("Encerrando o sistema. Até logo!");
                        entrada.close();
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }