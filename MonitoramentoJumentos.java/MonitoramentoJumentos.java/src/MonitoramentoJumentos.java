//Julia Fernandes Silva 325113523

import java.util.Scanner;
import java.text.DecimalFormat;

public class MonitoramentoJumentos {

    public static double calcularTaxaReducao(int populacaoInicial, int populacaoAtual) {
        double taxa = (1.0 - ((double) populacaoAtual / populacaoInicial)) * 100.0;
        return taxa;
    }

    public static boolean estaEmRiscoCritico(double taxaReducao) {
        return taxaReducao >= 90.0;
    }

    public static int simularImpactoAbate(int populacaoAtual, int abatesAnuais, int anosSimulados) {
        int populacaoRestante = populacaoAtual - (abatesAnuais * anosSimulados);
        return populacaoRestante;
    }

    public static void exibirDeclaracaoFinal(int populacaoRestante, int anos) {
        System.out.println("\n--- RESULTADO DA SIMULAÇÃO ---");

        System.out.printf("População restante após %d anos: %d jumentos.\n", anos, populacaoRestante);

        if (populacaoRestante <= 0) {
            System.out.println("!!! ALERTA MÁXIMO: o Jumento esta extinto :(( é necessario uma intervenção. !!!");
        } else {
            System.out.println("Ainda há esperança para os Jumentinhos!! Iniciativas de conservação vão ajudar!.");
        }
    }

    public static void main(String[] args) {
        final int POPULACAO_INICIAL = 1400000;
        final int POPULACAO_ATUAL = 84000;
        final int ABATES_ANUAIS_SIMULADOS = 50000;

        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== S.O.S. JUMENTO NORDESTINO - SISTEMA DE MONITORAMENTO ===");
        System.out.println("População inicial de referência: " + POPULACAO_INICIAL);
        System.out.println("População atual estimada: " + POPULACAO_ATUAL);
        System.out.println("Abates anuais (simulados): " + ABATES_ANUAIS_SIMULADOS);
        System.out.println("----------------------------------------------------------");

        double taxaReducao = calcularTaxaReducao(POPULACAO_INICIAL, POPULACAO_ATUAL);
        System.out.println("[Análise Inicial] Taxa de Redução Histórica: " + df.format(taxaReducao) + "%");

        boolean emRiscoCritico = estaEmRiscoCritico(taxaReducao);

        System.out.print("[Status] Espécie em Risco ");
        if (emRiscoCritico) {
            System.out.println("CRÍTICO (Redução > 90%)!");
        } else {
            System.out.println("ALTO (Redução < 90%).");
        }
        System.out.println("----------------------------------------------------------");

        System.out.print("Quantos anos futuros você deseja simular o impacto dos abates (Ex: 1, 3, 5)? ");
        int anosSimulados = 0;
        try {
            anosSimulados = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Usando 1 ano como padrão.");
            anosSimulados = 1;
        }
        scanner.close();

        int populacaoRestante = simularImpactoAbate(POPULACAO_ATUAL, ABATES_ANUAIS_SIMULADOS, anosSimulados);

        exibirDeclaracaoFinal(populacaoRestante, anosSimulados);

        System.out.println("\nPrograma de Monitoramento Finalizado.");
    }
}