package theobaldo;

import javax.swing.JOptionPane;

public class principal {

	static final int QTEREGISTROS = 5;
	public static int QTEINCLUIDOS = 0;

	public static int codigo[] = new int[QTEREGISTROS];
	public static String nome[] = new String[QTEREGISTROS];
	public static String email[] = new String[QTEREGISTROS];
	public static String cidade[] = new String[QTEREGISTROS];
	public static String uf[] = new String[QTEREGISTROS];

	public static void main(String[] args) {
		int opcao = 0;
		limpaMatriz(0);
		while (opcao != 6) {
			try {
				opcao = entradadeDeDadosTexto("Menu de opções\n\n" + "1-Cadastrar\n" + "2-Consultar\n" + "3-Atualizar\n"
						+ "4-Excluir\n" + "5-Listar\n" + "6-Exit\n" + "Escolha a sua opção: ");
				switch (opcao) {
				case 1:
					cadastrar();
					break;
				case 2:
					int registro = consultar(true);
					if(registro==-1)
					{
						Mensagem("Codigo não cadastrado.");
					}
					
					break;
				case 3:
					alterar();
					break;
				case 4:
					excluir();
					break;
				case 5:
					listar();
					break;
				case 6: // Exit
					break;
				default:
					Mensagem("Opção Inválida");
				}
			} catch (Exception ex) {
				Mensagem("Digite uma opção.");
			}
		}
	}

	private static void limpaMatriz(int registro) {
		int cnt = 1;
		if (registro == 0) {
			for (cnt = 0; cnt < QTEREGISTROS; cnt++) {
				codigo[cnt] = 0;
				nome[cnt] = "";
				email[cnt] = "";
				cidade[cnt] = "";
				uf[cnt] = "";
			}
		}
	}

	public static int entradadeDeDadosTexto(String texto) {
		return Integer.parseInt(JOptionPane.showInputDialog(texto));
	}

	public static void Mensagem(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	public static void cadastrar() {
		String sucesso = "";
		int item = 0;
		while(codigo[item]!= 0)
		{
			item++;
		}
		if (item < QTEREGISTROS) {
			codigo[item] = QTEINCLUIDOS+1;
			nome[item] = JOptionPane.showInputDialog("Digite seu nome completo");
			email[item] = JOptionPane.showInputDialog("Digite seu email");
			cidade[item] = JOptionPane.showInputDialog("Digite sua cidade");
			uf[item] = JOptionPane.showInputDialog("Digite sua UF");
			sucesso = "Codigo " + (QTEINCLUIDOS+1) + " incluido com sucesso";
			QTEINCLUIDOS = codigo[item];
			Mensagem(sucesso);
		} else {
			Mensagem("Total de registros alcançados.");
		}
	}
	public static void alterar()
	{
		String sucesso = "";
		int registro = 0;
		registro = consultar(false);
		if (registro != -1) {
			nome[registro] = JOptionPane.showInputDialog("Digite seu nome completo",nome[registro]);
			email[registro] = JOptionPane.showInputDialog("Digite seu email",email[registro]);
			cidade[registro] = JOptionPane.showInputDialog("Digite sua cidade",cidade[registro]);
			uf[registro] = JOptionPane.showInputDialog("Digite sua UF",uf[registro]);
			sucesso = "Codigo " + codigo[registro] + " alterado com sucesso";
			Mensagem(sucesso);
		}
		else
		{
			Mensagem("Codigo não cadastrado.");
		}
		
	}
	
	public static void excluir()
	{
		int xcodigo[] = new int[QTEREGISTROS];
		String xnome[] = new String[QTEREGISTROS];
		String xemail[] = new String[QTEREGISTROS];
		String xcidade[] = new String[QTEREGISTROS];
		String xuf[] = new String[QTEREGISTROS];
		
		String sim_ou_nao = "";
		int registro = 0;
		registro = consultar(false);
		if (registro != -1) {
			sim_ou_nao = JOptionPane.showInputDialog("Vai mesmo excluir (Sim ou Nao)");
			if (sim_ou_nao.equals("SIM") || sim_ou_nao.equals("Sim") || sim_ou_nao.equals("sim")) {
				Mensagem("Registro excluido!");
				int item = 0, itemaux = 0;
				for (item = 0; item < QTEREGISTROS; item++) {
					if (item!=registro) {
						xcodigo[itemaux]=codigo[item];
						xnome[itemaux]=nome[item];
						xemail[itemaux]=email[item];
						xcidade[itemaux]=cidade[item];
						xuf[itemaux]=uf[item];
						itemaux++;
					}
				}
				codigo = xcodigo;
				nome= xnome;
				email=xemail;
				cidade=xcidade;
				uf=xuf;
			}
		}
		else
		{
			Mensagem("Codigo não cadastrado.");
		}
	}

	public static int consultar(boolean exibir) {
		String mostrar = "";
		int item = entradadeDeDadosTexto("Digite codigo a consultar :");

		for (int cnt = 0; cnt < QTEREGISTROS;cnt++) {
			if (codigo[cnt] == item) {
				mostrar = "Codigo :" + codigo[cnt] + "\nNome   :" + nome[cnt] + "\nEmail  :" + email[cnt]
						+ "\nCidade :" + cidade[cnt] + "\nUF   :" + uf[cnt];
				if (exibir)
					Mensagem(mostrar);
				return cnt;
			} 
		}
		return -1;
	}

	public static void listar() {
		String mostrar = "";
		int item = 1;
		for (item = 0; item < QTEREGISTROS; item++) {
			if (codigo[item]!= 0) {
				mostrar = mostrar + "Codigo :" + codigo[item] + " Nome   :" + nome[item] + " Email  :" + email[item]
						+ " Cidade :" + cidade[item] + " UF   :" + uf[item] + "\n";
			}
		}
		if (mostrar.isEmpty()) {
			Mensagem("Nenhum cadastro efetuado.");
		} else {
			Mensagem(mostrar);
		}
	}
}
