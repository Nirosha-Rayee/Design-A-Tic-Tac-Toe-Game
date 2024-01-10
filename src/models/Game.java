package models;

import exceptions.InvalidGameDimensionException;
import strategy.GameWinningStrategy;
import strategy.OrderOneGameWinningStategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winningPlayer;


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public Player getWinningPlayer() {
        return winningPlayer;
    }
    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private GameWinningStrategy gameWinningStrategy;



    public static Builder getBuilder() {
        return new Builder();
    }



    public void makeNextMove() {
        Player playerWhoMoveItIs = players.get(nextPlayerIndex);
        System.out.println("It is" + playerWhoMoveItIs.getName() + "'s turn");

        Move move = playerWhoMoveItIs.decideMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
           board.applyMove(move);
            moves.add(move);

            //check winner
            if(gameWinningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                winningPlayer = playerWhoMoveItIs;
                System.out.println("Winner is " + winningPlayer.getName());
            }

            //DRAW
            if(moves.size() == board.getSize() * board.getSize()) {
                gameStatus = GameStatus.DRAW;
                System.out.println("Game is a draw");
            }




            nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
            //nextPlayerIndex = nextPlayerIndex + 1;
            //nextPlayerIndex = nextPlayerIndex % players.size();
        }
        else {
            //TODO: throw exception
            System.out.println("Invalid Move");
        }


    }


    public static class Builder{
        private int dimension;
        private List<Player> players;

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public Game build() {

            try{
                isValid();
            }catch (InvalidGameDimensionException e) {
                return null;

            }
            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setNextPlayerIndex(0);

            game.setGameWinningStrategy(new OrderOneGameWinningStategy(dimension));

            return game;
        }

        private boolean  isValid() throws InvalidGameDimensionException {
            if(dimension < 3) {

                throw new InvalidGameDimensionException("Dimension should be greater than 2");
            }
            return true;
        }
    }


}
