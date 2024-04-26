import java.util.Scanner;
public class ConnectFour {
    private String[][] board;
    private int playerTurn;

    public ConnectFour() {
        playerTurn = 1;
        board = new String[6][7];
    }

    // When new game build a board
    private void newGame() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = " - ";
            }
        }
    }

    private void displayBoard() {
        System.out.println(" ");
        System.out.println(" --- CONNECT 4 GAME ---");
        System.out.println(" ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private boolean isColumnFull(int column) {
        return (board[0][column - 1].equals(" R ") || board[0][column - 1].equals(" Y "));
    }

    private int getNextAvailableSlot(int column) {
        int position = 5;
        boolean found = false;
        while (position >= 0 && !found) {
            if (!board[position][column].equals(" R ") && !board[position][column].equals(" Y ")) {
                found = true;
            } else {
                position--;
            }
        }
        return position;
    }

    private void swapPlayerTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (!board[i][j].equals(" R ") && !board[i][j].equals(" Y ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // check winner and return the symbol of it R or Y
    private String checkVerticalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) && board[i][j].equals(board[i + 3][j])) {
                    if (board[i][j].equals(" R ") || board[i][j].equals(" Y ")) {
                        return board[i][j];
                    }
                }
            }
        }
        return null;
    }

    private String checkHorizontalWinner() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) && board[i][j].equals(board[i][j + 3])) {
                    if (board[i][j].equals(" R ") || board[i][j].equals(" Y ")) {
                        return board[i][j];
                    }
                }
            }
        }
        return null;
    }

    private String checkDiagonalLeftWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) && board[i][j].equals(board[i + 3][j + 3])) {
                    if (board[i][j].equals(" R ") || board[i][j].equals(" Y ")) {
                        return board[i][j];
                    }
                }
            }
        }
        return null;
    }

    private String checkDiagonalRightWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i][j].equals(board[i + 1][j - 1]) && board[i][j].equals(board[i + 2][j - 2]) && board[i][j].equals(board[i + 3][j - 3])) {
                    if (board[i][j].equals(" R ") || board[i][j].equals(" Y ")) {
                        return board[i][j];
                    }
                }
            }
        }
        return null;
    }

    private String checkForWinner() {
        String winnerSymbol;
        if ((winnerSymbol = checkVerticalWinner()) != null ||
                (winnerSymbol = checkHorizontalWinner()) != null ||
                (winnerSymbol = checkDiagonalLeftWinner()) != null ||
                (winnerSymbol = checkDiagonalRightWinner()) != null) {
            return winnerSymbol;
        }
        return null;
    }

    private boolean checkForDraw() {
        return (isBoardFull() && checkForWinner() == null);
    }

    private void playTwoPlayer() {
        newGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player 1's name:");
        String player1Name = scanner.nextLine();
        System.out.println(player1Name + ", choose your symbol ('R' for red, 'Y' for yellow):");
        String player1Symbol = scanner.nextLine().toUpperCase();
        boolean validSymbol = false;
        while (!validSymbol) {
            String symbol = scanner.nextLine().toUpperCase();
            if (symbol.equals("R") || symbol.equals("Y")) {
                player1Symbol = symbol;
                validSymbol = true;
            } else {
                System.out.println("Invalid symbol('R' for red or 'Y' for yellow): ");
            }
        }
        System.out.println("Enter player 2's name:");
        String player2Name = scanner.nextLine();
        String player2Symbol = (player1Symbol.equals("R")) ? "Y" : "R";

        while (true) {
            displayBoard();
            if (playerTurn == 1) {
                System.out.println(player1Name + ", enter column (1-7): ");
            } else {
                System.out.println(player2Name + ", enter column (1-7): ");
            }
            int col = scanner.nextInt() - 1;
            if (col >= 0 && col < 7 && !isColumnFull(col + 1)) {
                int row = getNextAvailableSlot(col);
                String symbol = (playerTurn == 1) ? " " + player1Symbol + " " : " " + player2Symbol + " ";
                board[row][col] = symbol;
                if (checkForWinner() != null) {
                    displayBoard();
                    System.out.println((playerTurn == 1) ? player1Name + " wins!" : player2Name + " wins!");
                    break;
                }
                if (checkForDraw()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                swapPlayerTurn();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }


    private void playSinglePlayer() {
        newGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String player1Name = scanner.nextLine();
        System.out.println(player1Name + ", choose your symbol ('R' for red, 'Y' for yellow): ");
        String player1Symbol = scanner.nextLine().toUpperCase();
        boolean validSymbol = false;
        while (!validSymbol) {
            String symbol = scanner.nextLine().toUpperCase();
            if (symbol.equals("R") || symbol.equals("Y")) {
                player1Symbol = symbol;
                validSymbol = true;
            } else {
                System.out.println("Invalid symbol ('R' for red or 'Y' for yellow): ");
            }
        }
        System.out.println("Do you want to go first? (y/n):");
        char first = scanner.next().charAt(0);
        if (first == 'n' || first == 'N') {
            playerTurn = 2;
        }
        while (true) {
            displayBoard();
            if (playerTurn == 1) {
                System.out.println(player1Name + ", enter column (1-7):");
                int col = scanner.nextInt() - 1;
                if (col >= 0 && col < 7 && !isColumnFull(col + 1)) {
                    int row = getNextAvailableSlot(col);
                    board[row][col] = " " + player1Symbol + " ";
                    if (checkForWinner() != null) {
                        displayBoard();
                        System.out.println(player1Name + " wins!");
                        break;
                    }
                    if (checkForDraw()) {
                        displayBoard();
                        System.out.println("Draw!");
                        break;
                    }
                    swapPlayerTurn();
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("AI's turn:");
                int[] move = maxValue(4, Integer.MIN_VALUE, Integer.MAX_VALUE);
                String player2Symbol = (player1Symbol.equals("R")) ? "Y" : "R";
                board[move[1]][move[2]] = " " + player2Symbol + " ";
                if (checkForWinner() != null) {
                    displayBoard();
                    System.out.println("AI wins! You loser.");
                    break;
                }
                if (checkForDraw()) {
                    displayBoard();
                    System.out.println("Draw");
                    break;
                }
                swapPlayerTurn();
            }
        }
        scanner.close();
    }

    private int[] maxValue(int depth, int alpha, int beta) {
        int score = evaluateBoard();

        if (depth == 0 || score == 100 || score == -100 || isBoardFull()) {
            return new int[]{score, -1, -1};
        }

        int maxEval = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int col = 0; col < 7; col++) {
            if (!isColumnFull(col + 1)) {
                int row = getNextAvailableSlot(col);
                board[row][col] = " Y ";
                int[] eval = minValue(depth - 1, alpha, beta);
                board[row][col] = " - ";
                if (eval[0] > maxEval) {
                    maxEval = eval[0];
                    bestMove[0] = row;
                    bestMove[1] = col;
                }
                alpha = Math.max(alpha, eval[0]);
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return new int[]{maxEval, bestMove[0], bestMove[1]};
    }

    private int[] minValue(int depth, int alpha, int beta) {
        int score = evaluateBoard();

        if (depth == 0 || score == 100 || score == -100 || isBoardFull()) {
            return new int[]{score, -1, -1};
        }

        int minEval = Integer.MAX_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int col = 0; col < 7; col++) {
            if (!isColumnFull(col + 1)) {
                int row = getNextAvailableSlot(col);
                board[row][col] = " R ";
                int[] eval = maxValue(depth - 1, alpha, beta);
                board[row][col] = " - ";
                if (eval[0] < minEval) {
                    minEval = eval[0];
                    bestMove[0] = row;
                    bestMove[1] = col;
                }
                beta = Math.min(beta, eval[0]);
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return new int[]{minEval, bestMove[0], bestMove[1]};
    }


    private int evaluateBoard() {
        String winnerSymbol = checkForWinner();
        if (winnerSymbol != null) {
            if (winnerSymbol.equals(" R ")) {
                return -100;
            } else if (winnerSymbol.equals(" Y ")) {
                return 100;
            }
        }
        return 0;
    }


    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Connect Four!");
        System.out.println("Select an option:");
        System.out.println("1. Play against another player");
        System.out.println("2. Play against AI");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                playTwoPlayer();
                break;
            case 2:
                playSinglePlayer();
                break;
            default:
                System.out.println("Invalid option. Exiting game.");
                break;
        }
    }
    public static void main(String[] args) {
        ConnectFour c4 = new ConnectFour();
        c4.startGame();
    }
}