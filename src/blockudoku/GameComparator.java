/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class GameComparator implements Comparator<Game>, Serializable{

    private static final long serialVersionUID = 1L;
    
    @Override
    public int compare(Game x, Game y){
        return x.getGameScore() - y.getGameScore();
    }
    
}
