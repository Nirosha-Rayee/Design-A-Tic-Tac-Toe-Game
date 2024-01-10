import controller.GameController;
import models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TTTGame {
    public static void main (String args[]) {

        System.out.println("Hello world! Welcome to Tic Tac Toe!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of the board: ");
        int dimension = scanner.nextInt();

        System.out.println("Enter the No of players: ");
        int noOfPlayers = scanner.nextInt();
        List<Player> players = new LinkedList<>();

        System.out.println("will there be a computer player? (y/n)");
        String isBot = scanner.next();

        if(isBot.equals("y")){
            noOfPlayers = noOfPlayers - 1;


            System.out.println("Enter the name of the Bot: ");
            String name = scanner.next();

            System.out.println("Enter the symbol of the Bot: ");
            String botSymbol = scanner.next();

            System.out.println("Enter the Bot's difficulty level: 1-Easy, 2-Medium, 3-Hard");
            int  DifficultyLevel = scanner.nextInt();

            players.add(new Bot(botSymbol.charAt(0),name, BotDifficultyLevel.EASY));
        }

//        System.out.println("Enter the name of the Bot: ");
//        String name = scanner.next();
//
//        System.out.println("Enter the symbol of the Bot: ");
//        String botSymbol = scanner.next();
//
//        System.out.println("Enter the Bot's difficulty level: 1-Easy, 2-Medium, 3-Hard");
//        int  DifficultyLevel = scanner.nextInt();
//
//        players.add(new Bot(botSymbol.charAt(0),name, BotDifficultyLevel.EASY));

        for(int i = 0; i < noOfPlayers; i++){
            System.out.println("What is the name of the player:"+(i+1));
            String playerName = scanner.next();

            System.out.println("Enter the symbol of the player: ");
            String playerSymbol = scanner.next();

           Player player = new Player(playerSymbol.charAt(0),playerName, PlayerType.HUMAN);
            players.add(player);
        }

//        Game game = Game.getBuilder()
//                .setDimension(dimension)
//                .setPlayers(players)
//                .build();
        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension,players);

//        while(game.getGameStatus() == GameStatus.IN_PROGRESS){
        while(gameController.getGameStatus(game) == GameStatus.IN_PROGRESS){

            //TODO: play the game
            //break;
            System.out.println("current Borad" );
            gameController.displayBoard(game);

            gameController.executeNextMove(game);

        }

//        if(game.getGameStatus() == GameStatus.DRAW){
        if(gameController.getGameStatus(game) == GameStatus.DRAW){
            System.out.println("Game has  drawn");
        }else{
//            System.out.println("Game has been won by "+game.getWinningPlayer());
            System.out.println("Game has been won by "+gameController.getWinnerName(game));

        }
    }
}

