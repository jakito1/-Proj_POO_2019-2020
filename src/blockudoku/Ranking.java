/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;
import java.util.PriorityQueue;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class Ranking implements Serializable{

    private static final long serialVersionUID = 1L;
    private final PriorityQueue<Game> ranking =
            new PriorityQueue<>(10, new GameComparator());
    
    public boolean tryAddRanking(Game game){
        return ranking.offer(game);
    }
    
    public void clearRanking(){
        ranking.clear();
    }
    
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        
        ranking.stream().map((game) -> {
            returnString.append("\n");
            returnString.append(game.getPlayer());
            return game;
        }).map((game) -> {
            returnString.append(" - ");
            returnString.append(game.getDate());
            return game;
        }).map((game) -> {
            returnString.append(": ");
            returnString.append(game.getGameScore());
            return game;
        }).map((game) -> {
            returnString.append("\n");
            returnString.append(game.getGameModeString());
            return game;
        }).forEachOrdered((_item) -> {
            returnString.append("\n");
        });
        return returnString.toString();
    }   
}
