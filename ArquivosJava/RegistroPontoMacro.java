package br.com.proway.senior.teste;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroPonto {

	public static void main(String[] args) throws UnknownHostException {
		insereUsuario();
		horaEntrada();
		saidaAlmoco();
		retornoAlmoco();
		horaSaida();
		validacaoHorasNotificao();
		ipLocalNomeMaquina();
	}
	
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	static LocalDateTime entrada;
	static LocalDateTime saida;
	static LocalDateTime horaSaidaAlmoco;
	static LocalDateTime horaRetornoAlmoco;
	static int chDia = 8;
	static int horaAlmoco = 1;

	static String usuario = "1234";

	static void insereUsuario() {
		System.out.println("Insira o seu Usuário: " + usuario + "\n");
	}

	static void horaEntrada() {
		entrada = LocalDateTime.of(2021, 4, 13, 8, 00, 00); // Data/hora estáticos entrada
		System.out.println("Horário de entrada: " + entrada.format(formatter) + "\n");
	}
	
	static void saidaAlmoco() {
		horaSaidaAlmoco = LocalDateTime.of(2021, 4, 13, 12, 00, 00); // Data/hora estáticos entrada
		System.out.println("Horário saída almoço: " + horaSaidaAlmoco.format(formatter) + "\n");
	}
	
	static void retornoAlmoco() {
		horaRetornoAlmoco = LocalDateTime.of(2021, 4, 13, 13, 00, 00); // Data/hora estáticos entrada
		System.out.println("Horário retorno almoço: " + horaRetornoAlmoco.format(formatter) + "\n");
	}
	
	static void horaSaida() {
		saida = LocalDateTime.of(2021, 4, 13, 18, 00, 00); // Data/hora estáticos saída
		System.out.println("Horário de saída: " + saida.format(formatter) + "\n");	
	}
	
	
//	// salva em lista a hora que bateu o ponto
//	public ArrayList<LocalTime> armazenaHora(int id, LocalTime hora) {
//		ArrayList<LocalTime> horas = new ArrayList<>();
//		horas.add(hora);
//		return horas;
//	}
//	
//	// calcula a quantidade de horas em relação aos 4 pontos batidos diariamente e
//		// retorna este valor
//		public LocalDateTime horasTrabalhadas(LocalDateTime entrada, LocalDateTime horaSaidaAlmoco, LocalDateTime horaRetornoAlmoco,
//				LocalDateTime saida) {
//
//			Duration manha = Duration.between(entrada, horaSaidaAlmoco);
//			Duration tarde = Duration.between(horaRetornoAlmoco, saida);
//			long i = manha.toMinutes();
//			long j = tarde.toMinutes();
//			long juncao = i + j;
//			double minuto = juncao;
//			int s = (int) Math.round(minuto * 60);
//			int h = s / 3600;
//			int m = (s - 3600 * h) / 60;
//			s = s % 60;
//
//			LocalDate hoje = LocalDate.now();
//
//			LocalDateTime retorno = LocalDateTime.of(hoje.getYear(), hoje.getMonth(), hoje.getDayOfMonth(), h, m, s);
//			return retorno;
//
//		}
		
		static void validacaoHorasNotificao() {
			Duration duracao = Duration.ZERO;
			/* Caso o usuário tenha esquecido de bater o ponto na saída*/
			if(saida.getHour() == 0) {
				System.out.println("Você não bateu o seu registro de saída");
			} else {
				duracao = Duration.between(entrada, horaSaidaAlmoco);
				System.out.println("Horas manhã: " + duracao.toHours() + "\n");
				
				duracao = Duration.between(horaRetornoAlmoco, saida);
				System.out.println("Horas tarde: " + duracao.toHours() + "\n");
				
				duracao = Duration.between(entrada, saida);
				System.out.println("Resultado total de horas no dia: " + (duracao.toHours() - horaAlmoco + "\n"));

				System.out.println("Resultado horas extras/banco de horas: ");
				System.out.println(duracao.toHours() - chDia - horaAlmoco + "\n");
			}	
			
			/* Caso o usuário bata o ponto consecutivamente (antes dos 5 minutos) */
			if(duracao.toMinutes() < 5 && saida.getHour() != 0) {
				System.out.println("Você bateu o ponto consecutivamente" + "\n");
			}
			
			/* Caso o usuário tenha batido o ponto antes das 8 horas*/
			else if(duracao.toHours() < 8 && saida.getHour() != 0) {
				System.out.println("Você não cumpriu seu horário de trabalho do dia" + "\n");
			} 
			
			/* Caso o usuário tenha cumprido as 8 horas trabalhadas e passado delas*/
			else if(duracao.toHours() >= 8) {
				System.out.println("Parabéns! Você cumpriu sua jornada!!" + "\n");
			}	
		}

		static void ipLocalNomeMaquina() throws UnknownHostException {
			/*Ip da maquina*/
			String ipMaquina = InetAddress.getLocalHost().getHostAddress();
			System.out.println("Ip da maquina: " + ipMaquina + "\n");
			

			/*Nome da maquina*/
			String nomeMaquina = InetAddress.getLocalHost().getHostName();
			System.out.println("Nome da maquina: " + nomeMaquina + "\n");
		}	
	}
