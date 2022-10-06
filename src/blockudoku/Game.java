/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 * This class is the backend representation of the game itself 
 * (tray, playability, etc).
 */
public class Game implements Serializable{

    private static final long serialVersionUID = 1L;
    private final Player player;
    private final LocalDateTime date;
    private final Score score;
    private final GameMode gameMode;
    private final Tray tray;
    private final Blocks[] blocks;
    
    /**
     * 
     * @param player this parameter associates the registered Player to the game.
     * @param gameMode this parameter defines the game mode. 
     */
    public Game(Player player, GameMode gameMode){
        this.player = player;
        this.date = LocalDateTime.now();
        this.score = new Score(gameMode);
        this.gameMode = gameMode;
        this.tray = new Tray();
        blocks = new Blocks[3];
    }
    

    /**
     * 
     * @param tempBlocks is an array with 3 blocks
     * saves the block in save file
     */
    protected void saveBlocks(Blocks[] tempBlocks){
        System.arraycopy(tempBlocks, 0, blocks, 0, 3);
    }
    
     /**
     * 
     * @return returns the player's nickame;
     */
    public String getPlayer(){
        return player.getNickName();
    }
    
    /**
     * 
     * @return returns the current score of the game.
     */
    public int getGameScore(){
        return score.getScore();
    }
    
    /**
     * 
     * @return returns the game mode of the current game in String.
     */
    public String getGameModeString(){
        return gameMode.toString();
    }

    /**
     * 
     * @return returns the game mode of the current game.
     */
    public GameMode getGameMode() {
        return gameMode;
    }
    
        /**
     * 
     * @return returns the current date.
     */
    public String getDate(){
        return date.format(DateTimeFormatter.
                ofPattern("dd-MM-yyyy HH:mm:ss"));
    } 
    
    /**
     * 
     * @return returns the "sudoku" board.
     */
    public Tray getTray() {
        return tray;
    }

    /**
     * 
     * @return the array of 3 blocks
     */
    public Blocks[] getBlocks() {
        Blocks[] returnBlocks = new Blocks[3];
        System.arraycopy(blocks, 0, returnBlocks, 0, 3);
        return returnBlocks;
    }
    
    /**
     * 
     * @return returns the elements from the Score class.
     */
    public Score getScore() {
        return score;
    }
}
