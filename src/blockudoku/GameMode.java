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
public enum GameMode implements Serializable{
    
    BASICMODE, ADVANCEDMODE;
    
    @Override
    public String toString(){
        switch(this){
            case BASICMODE:
                return "Modo Básico";
            case ADVANCEDMODE:
                return "Modo Avançado";
            default:
                return "";
        }
    }  
}
