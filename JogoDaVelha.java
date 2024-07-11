package Exercicios;

import java.util.Scanner;

public class JogoDaVelha {
    private static char[][] tabuleiro = new char[3][3]; //Matriz 3x3
    private static char jogador = 'X'; //Quem começa o jogo

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuarJogando = true;

        while (continuarJogando) {
            iniciarTabuleiro(); //Limpar o tabuleiro
            jogador = 'X'; //Definir quem começa o jogo
            boolean jogoTerminado = false; //Determinar se o jogo terminou ou não

            //Fazer as jogadas e marcar na matriz
            while (!jogoTerminado) {
                exibirTabuleiro(); //Mostrar o tabuleiro atualizado em cada nova jogada
                System.out.println("Jogador " + jogador + ", escolha uma posição de 1 a 9:");
                int posicao = scanner.nextInt(); //Posição escolhida dentro do tabuleiro
                //Verificar se a opção escolhida é valida
                if (fazerJogada(posicao)) {
                	
                	//Se um dos jogadores tiver ganhado ou se der empate
                    if (verificarVitoria()) {
                        exibirTabuleiro();
                        System.out.println("Jogador " + jogador + " é o vencedor!");
                        jogoTerminado = true;
                    } else if (tabuleiroCheio()) {
                        exibirTabuleiro();
                        System.out.println("Empate!");
                        jogoTerminado = true;
                    } else {
                        jogador = (jogador == 'X') ? 'O' : 'X'; //Alterar entre os jogadores durante cada jogada
                    }
                } else {
                    System.out.println("Posição já escolhida ou invalida. Escolha outra posição."); //Se a posição escolhida já estiver ocupada
                }
            }
            
            //Se o jogador desejar continuar o jogo
            System.out.println("Deseja jogar novamente? (S/N)");
            String resposta = scanner.next();
            if (!resposta.equalsIgnoreCase("S")) {
                continuarJogando = false;
            }
        }

        scanner.close();
    }

    //Inicia o tabuleiro vazio para o jogo
    private static void iniciarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    //Exibir o estado atual do tabuleiro
    private static void exibirTabuleiro() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    //Recebe a posição desejada pelo jogador entre 1 a 9
    private static boolean fazerJogada(int posicao) {
        if (posicao < 1 || posicao > 9) {
            return false;
        }

        int linha = (posicao - 1) / 3;
        int coluna = (posicao - 1) % 3;

        //Verifica se a opção escolhida está vazia
        if (tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = jogador;
            return true;
        } else {
            return false; //Se a opção não estiver disponivel, a jogada precisa ser feita novamente
        }
    }

    //Verificar qual jogador ganhou a partida
    private static boolean verificarVitoria() {
        //Verificar as linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
        }
        //Verificar as colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }
        //Verificar as diagonais
        if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) ||
            (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) {
            return true;
        }
        return false; //Se não ocorrer uma vitoria
    }
    
    //Se o tabuleiro estiver cheio, sem nenhum ganhador(empate)
    private static boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true; //Se não estiver cheio
    }
}

