/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class Player implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String nickName;
    private final History scoreHistory;
    private final HashMap<String, Game> saves;
    
    public Player(String nickName){        
        if(nickName.equals("")){
            this.nickName = "Player";
        }else{
            this.nickName = nickName;
        }     
        
        this.scoreHistory = new History();
        this.saves = new HashMap<>();
    }
    
    protected void saveGame(String saveName, Game currentGame){
        saves.put(saveName, currentGame);
    }
    
    protected Game loadGame(String saveName){
        return saves.get(saveName);
    }
    
    public String getNickName(){
        return nickName;
    }
    
    public void setNickName(String name){
        this.nickName = name;
    }
    
    public History getHistory(){
        return scoreHistory;
    }
    
    public String getSavesName(){
        return saves.keySet().toString();
    }    
}
