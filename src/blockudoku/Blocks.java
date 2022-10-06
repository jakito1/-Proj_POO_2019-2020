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
 * this class represents the blocks for the game
 */
public class Blocks implements Serializable{

    private static final long serialVersionUID = 1L;
    private Shapes pieceShape;
    private int[][] coords;
    private final int[] primaryCoords;
    private HashMap<Shapes, int[][]> coordsTable;
    
    /**
     * 
     * @param pieceShape is the block
     * @param numTimesRotate is the number of times that the block rotates
     * this constructor initializes the primary coordenates (that will help 
     * draw the blocks in the right place of the tray), the hashmap and the 
     * rotation.
     */
    public Blocks(Shapes pieceShape, int numTimesRotate){
        primaryCoords = new int[2];
        startHashMap();
        setShape(pieceShape);
        rotateLeft(numTimesRotate);
    }
    
    /**
     * this method creates all the blocks.
     */
    private void startHashMap(){
        coordsTable = new HashMap<>();
        coordsTable.put(Shapes.BASIC_I, (new int[][] {{ 0, 0 }, { 0, 1 }, 
            { 0, 2 }, { 0, 3 }}));
        coordsTable.put(Shapes.BASIC_Q, (new int[][] {{ 0, 0 }, { 0, 1 }, 
            { 1, 0 }, { 1, 1 } }));
        coordsTable.put(Shapes.BASIC_T, (new int[][] {{ 0, 1 }, { 1, 1 }, 
            { 2, 1 }, { 1, 0 } }));
        coordsTable.put(Shapes.BASIC_L, (new int[][] {{ 0, 0 }, { 1, 0 }, 
            { 1, 1 }, { 1, 2 } }));
        coordsTable.put(Shapes.BASIC_J, (new int[][] {{ 0, 0 }, { 1, 0 }, 
            { 0, 1 }, { 0, 2 } }));
        coordsTable.put(Shapes.BASIC_S, (new int[][] {{ 0, 1 }, { 1, 1 }, 
            { 1, 0 }, { 2, 0 } }));
        coordsTable.put(Shapes.BASIC_Z, (new int[][] {{ 0, 0 }, { 1, 0 }, 
            { 1, 1 }, { 2, 1 } }));
        coordsTable.put(Shapes.ADVANCED_I1, (new int[][] {{ 0, 0 }}));
        coordsTable.put(Shapes.ADVANCED_I2, (new int[][] {{ 0, 0 }, { 0, 1 }}));
        coordsTable.put(Shapes.ADVANCED_I3, (new int[][] {{ 0, 0 }, { 0, 1 }, 
            { 0, 2 }}));
        coordsTable.put(Shapes.ADVANCED_L_MIN, (new int[][] {{ 0, 0 }, { 1, 0 },
            { 1, 1}}));
        coordsTable.put(Shapes.ADVANCED_L_MAX, (new int[][] {{ 0, 0 }, { 1, 0 },
            { -1, 0 }, { 1, 1 }, { 1, 2 }}));
        coordsTable.put(Shapes.ADVANCED_T_EXTENDED, (new int[][] { { 0, 2 },
            { 1, 2 }, { 2, 2 }, { 1, 1 }, { 1, 0 }}));
        coordsTable.put(Shapes.ADVANCED_Q_EXTENDED, (new int[][] { { 0, 0 },
            { 1, 0 }, { 2, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 }, { 0, 2 }, 
            { 1, 2 }, { 2, 2 }}));
    }
      
    /**
     * 
     * @param transformationCoords is the coordenates where the primarycord 
     * will be.
     * @return a transformed block.
     * this method sets the block coordenates as the ones it has to be in the
     * tray.
     */
    protected Blocks tranformCoords(int[] transformationCoords){
        for (int i = 0; i < coords.length; i++){
            if (coords[i][0] != primaryCoords[0] || coords[i][1] != primaryCoords[1]){
                coords[i][0] = transformationCoords[0] + coords[i][0] - primaryCoords[0];
                coords[i][1] = transformationCoords[1] + coords[i][1] - primaryCoords[1];
            }else{
                coords[i] = transformationCoords;
            }
        }
        setPrimaryCoords();
        return this;
    }
    
    /**
     * 
     * @param numTimesRotate is the number of times the block will rotate
     * @return a transformed block
     * this method rotates times the numTimesRotate
     */
    private Blocks rotateLeft(int numTimesRotate) {
        if (pieceShape == Shapes.ADVANCED_I1 || pieceShape == Shapes.BASIC_Q
                || pieceShape == Shapes.ADVANCED_Q_EXTENDED 
                || numTimesRotate == 0) {
            setPrimaryCoords();
            return this;
        }
        int tempX;
        int tempY;
        for (int i = 0; i < coordsTable.get(pieceShape).length; i++) {
            tempX = -y(i);
            tempY = x(i);
            this.setX(i, tempX);
            this.setY(i, tempY);
        }
        if (numTimesRotate != 0){
            rotateLeft(numTimesRotate - 1);
        }
        setPrimaryCoords();
        return this;   
    }

    /**
     * 
     * @param index is y coordenate
     * @return the x coordenate given y.
     */
    private int x(int index) {
        return coords[index][0];
    }

    /**
     * 
     * @param index is x coordenate
     * @return the y coordenate given x.
     */
    private int y(int index) {
        return coords[index][1];
    }
    
    /**
     * 
     * @param pieceShape is a random block
     * this method creates a new block given the shape we want.
     */
    private void setShape(Shapes pieceShape){
        coords = coordsTable.get(pieceShape).clone();
        this.pieceShape = pieceShape;
        setPrimaryCoords();
    }
    
    /**
     * this method defines the primary coordenates 
     */
    private void setPrimaryCoords(){
        int occurrences = 0;
        int minX = coords[0][0];
        int minY = coords[0][1];
        for (int i = 1; i < coords.length; i++){
            if (minX > coords[i][0]){
                minX = coords[i][0];
                minY = coords[i][1];
                occurrences = 0;
            }else if (minX == coords[i][0]){
                occurrences++;
            }
        }
        if (occurrences == 0){
            primaryCoords[0] = minX;
            primaryCoords[1] = minY;
        }else{
            for (int[] coord : coords) {
                if (minX == coord[0] && minY > coord[1]) {
                    minY = coord[1];
                }
            }
            primaryCoords[0] = minX;
            primaryCoords[1] = minY;
        }
    }
        
    /**
     * this method changes the x coordenate, given a new coordenate and 
     * current x
     * @param index is the new x
     * @param x is the old coordenate
     */
    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    /**
     * this method changes the y coordenate, given a new coordenate and 
     * current y
     * @param index is the new y
     * @param y is the old coordenate
     */
    private void setY(int index, int y) {
        coords[index][1] = y;
    }
    
    /**
     * 
     * @return a block
     */
    public Shapes getPieceShape() {
        return pieceShape;
    }
    
    /**
     * 
     * @return the coordenates of a block
     */
    public int[][] getCoords(){
        return coords.clone();
    }
       
    /**
     * 
     * @return the primary coordenates of a block
     */
    public int[] getPrimaryCoords(){
        return primaryCoords.clone();
    }
    
    /**
     * 
     * @return a drawing of the block
     */
    @Override
    public String toString(){
        String[][][] frame = {
            {{" "}, {" "}, {" "}, {" "}, {" "}},
            {{" "}, {" "}, {" "}, {" "}, {" "}},
            {{" "}, {" "}, {" "}, {" "}, {" "}},
            {{" "}, {" "}, {" "}, {" "}, {" "}},
            {{" "}, {" "}, {" "}, {" "}, {" "}},
            {{" "}, {" "}, {" "}, {" "}, {" "}}};
                
        tranformCoords(new int[] {1, 2});
        for (int[] tempCoord : coords) {
            frame[tempCoord[1]][tempCoord[0]][0] = "*";
        }
        
        String returnString = "";            
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                returnString += frame[i][j][0];               
            }
            returnString += "\n";
        }
        return returnString;
    }
}
