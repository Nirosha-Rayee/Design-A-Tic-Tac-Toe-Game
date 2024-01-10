package strategy;

import models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move decideMove(Player player, Board board) {

        for (int i = 0; i < board.getBoard().size(); i++){
            for(int j = 0; j < board.getBoard().size(); j++){
                if(board.getBoard().get(i).get(j).getCellState() == CellState.EMPTY){

                    //bot will always choose the first empty cell
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null; //throw exception
    }
}
