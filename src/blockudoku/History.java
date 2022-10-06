/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class History implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private final ArrayList<String> history;
    
    public History(){
        this.history = new ArrayList<>();
    }   
    
    protected boolean addGameHistory(Game game){
        String s = "";
        if(game.getGameScore() >= 0){
            s += "Nome do jogador: " + game.getPlayer()
                 + " | Pontuação: " + game.getGameScore() +
                 " | Data: " + game.getDate();
            history.add(s);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();      
        history.forEach((currItem) -> {returnString.append(currItem);});
        return returnString.toString();
    }   
}
