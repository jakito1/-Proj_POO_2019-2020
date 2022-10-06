/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class Score implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int score;
    private final GameMode gameMode;
    
    public Score(GameMode gameMode){
        this.score = 0;
        this.gameMode = gameMode;
    }
    
    protected void squaresCleared(boolean isBigSquare){
        score += 36;
        if (isBigSquare){
            score += 10;
        }
    }
    
    protected void squareAdded(int numberSquares){
        score = (gameMode == GameMode.BASICMODE) ? 
                (score + numberSquares) : (score + (2 * numberSquares));
    }
    
    public int getScore(){
        return score;
    }   
}
