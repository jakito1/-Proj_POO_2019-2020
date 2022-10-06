/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 * this class represents the types of blocks using an enumerator
 */
public enum Shapes implements Serializable{
    BASIC_I, BASIC_Q, BASIC_T, BASIC_L, BASIC_J, BASIC_S,
        BASIC_Z, ADVANCED_I1, ADVANCED_I2, ADVANCED_I3, ADVANCED_L_MIN,
        ADVANCED_L_MAX, ADVANCED_T_EXTENDED, ADVANCED_Q_EXTENDED;
        
    private static final Random RANDOM = new Random();
        
    /**
     * this method generates a random block
     * @param isBasicMode
     * @return 
     */
    protected static Shapes getRandomShape(boolean isBasicMode){
        Shapes[] shapesArray = Shapes.values();
        if (isBasicMode){
            return shapesArray[RANDOM.nextInt(7)];
        }
        return shapesArray[RANDOM.nextInt(shapesArray.length)];
    }
}
