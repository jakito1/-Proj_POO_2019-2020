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
 * this 
 */
public class Tray implements Serializable{

    private static final long serialVersionUID = 1L;
    private final HashMap<Character, Integer> charValue;
    private final static String[][][] TABULEIRO = {
            {{"   "}, {"A"}, {"B"}, {"C"}, {"D"}, {"E"}, {"F"}, {"G"}, {"H"}, {"I"}},
            {{" 1 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 2 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 3 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 4 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 5 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 6 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 7 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 8 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},
            {{" 9 "}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}, {"."}},            
        };
    private final String[][][] currTabuleiro;
    
    /**
     * this constructor initializes the tray and and populates the with number 
     * and letters
     */
    public Tray(){
        currTabuleiro = new String[10][10][1];
        setDefaultTray();
        charValue = new HashMap<>();
        populateMap();
    }
    
    /**
     * this method adds the letters and numbers that will identify the 
     * tray positions
     */
    private void populateMap(){   
        charValue.put('A', 1);
        charValue.put('B', 2);
        charValue.put('C', 3);
        charValue.put('D', 4);
        charValue.put('E', 5);
        charValue.put('F', 6);
        charValue.put('G', 7);
        charValue.put('H', 8);
        charValue.put('I', 9);
    }

    /**
     * this method handles the user inputs
     * @param command
     * @return the input
     * @throws NullPointerException
     * @throws NumberFormatException 
     */
    private int[] handleCommands(String command) 
            throws NullPointerException, NumberFormatException{
        String newCommand = command.replaceAll("\\s+","");
        String[] tempCommand = newCommand.split("-");
        int[] intCommand = new int[3];
        intCommand[0] = charValue.get(tempCommand[0].toCharArray()[0]) - 1;
        intCommand[1] = charValue.get(tempCommand[1].toCharArray()[0]);
        intCommand[2] = Integer.parseInt(tempCommand[1].
                replaceAll("[^0-9]", ""));
        return intCommand;
    }
    
    /**
     * this method checks if the game is over
     * @param block
     * @param game
     * @param ranking
     * @return returns true if the game is over. False otherwise
     */
    protected boolean checkEndGame(Blocks[] block, Game game, Ranking ranking){
        int nullNumber = 0;
        for (int blockNumber = 0; blockNumber < 3; blockNumber++){
            if (block[blockNumber] != null){
                for (int i = 1; i < 10; i++){
                    for (int j = 1; j < 10; j++){
                        block[blockNumber].tranformCoords(new int[] {i, j});
                        if(!checkConflict(block[blockNumber].getCoords())){
                            return false;                     
                        }
                    }
                }
            }else{
                nullNumber++;
            }           
        }
        if (nullNumber == 3){
            return false;
        }
        ranking.tryAddRanking(game);
        return true;
    }
     
    /**
     * this method puts the blocks on the tray
     * @param command
     * @param block
     * @param score
     * @return return array of blocks
     * @throws NullPointerException 
     */
    protected Blocks[] putOnTray(String command, Blocks[] block, Score score)
    throws NullPointerException{
        int[] splitCommand = handleCommands(command.toUpperCase());
        Blocks blockToPut = block[splitCommand[0]];
        int[] transformationCoords = {splitCommand[1], splitCommand[2]};
        blockToPut = blockToPut.tranformCoords(transformationCoords);
        int[][] tempCoords = blockToPut.getCoords();
        if (checkConflict(tempCoords)){
            return block;
        }
        for (int[] tempCoord : tempCoords) {
            currTabuleiro[tempCoord[1]][tempCoord[0]][0] = "*";
        }
        score.squareAdded(block[splitCommand[0]].getCoords().length);
        block[splitCommand[0]] = null;
        return block;
    }
    
    /**
     * this method checks if the block can be put in specific coordenates.
     * @param tempCoords
     * @return returns true if can place the block. False otherwise 
     */
    private boolean checkConflict(int[][] tempCoords){
        for (int[] tempCoord : tempCoords) {
            if (tempCoord[0] > 9 || tempCoord[0] < 1 || tempCoord[1] > 9 || tempCoord[1] < 1) {
                return true;
            } else if (currTabuleiro[tempCoord[1]][tempCoord[0]][0].equals("*")) {
                return true;
            }           
        }
        return false;
    }
    
    /**
     * this method clears a row
     * @return true if row is cleared. False otherwise
     */
    private boolean clearLines(){
        int checkedTimes = 0;
        for (int i = 1; i < 10; i++){
            for (int j = 1; j < 10; j++){
                if (currTabuleiro[i][j][0].equals("*")){
                    checkedTimes++;
                }else{
                    checkedTimes = 0;
                }
            }
            if (checkedTimes == 9){
                for (int j = 1; j < 10; j++){
                    currTabuleiro[i][j][0] = ".";
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * this method clears a column
     * @return true if column is cleared. False otherwise
     */
    private boolean clearColumns(){
        int checkedTimes = 0;
        for (int i = 1; i < 10; i++){
            for (int j = 1; j < 10; j++){
                if (currTabuleiro[j][i][0].equals("*")){
                    checkedTimes++;
                }else{
                    checkedTimes = 0;
                }
            }
            if (checkedTimes == 9){
                for (int j = 1; j < 10; j++){
                    currTabuleiro[j][i][0] = ".";
                }
                return true;
            }
        }
        return false;
    }
       
    /**
     * this method clears a big square
     * @return true if square is cleared. False otherwise
     */
    private boolean clearSquares(){
        int checkedTimes = 0;
        for(int row = 1; row < 10; row += 3){
            for(int col = 1; col < 10; col += 3){
                for (int squareRow = row; squareRow < row + 3; squareRow++){
                    for (int squareCol = col; squareCol < col + 3; squareCol++){
                        if (currTabuleiro[squareRow][squareCol][0].equals("*")){
                            checkedTimes++;
                        }else{
                            checkedTimes = 0;
                        }
                    }
                }
                if (checkedTimes == 9){
                    for (int squareRow = row; squareRow < row + 3; squareRow++){
                        for (int squareCol = col; squareCol < col + 3; squareCol++){
                            currTabuleiro[squareRow][squareCol][0] = ".";
                        }
                    }
                    return true;
                }
            }               
        }
        return false;
    }
    
    /**
     * this method clears the board
     * @param score
     * @return returns cleared if tray if cleared. returns false on the methods
     * that clear lines/rows/squares otherwise.
     */
    protected boolean clearTray(Score score){
        boolean linesNotReady = clearLines();
        boolean columnsNotReady = clearColumns();
        boolean squaresNotReady = clearSquares();
        boolean cleared = (linesNotReady || columnsNotReady || squaresNotReady);
        while (linesNotReady || columnsNotReady || squaresNotReady){
            if (linesNotReady){
                linesNotReady = clearLines();
                score.squaresCleared(false);
            }else if (columnsNotReady){
                columnsNotReady = clearColumns();
                score.squaresCleared(false);
            }else if (squaresNotReady){
                squaresNotReady = clearSquares();
                score.squaresCleared(true);
            }
        }
        return cleared;
    }
    
    protected final void setDefaultTray(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.arraycopy(TABULEIRO[i][j], 0, 
                        currTabuleiro[i][j], 0, TABULEIRO[i][j].length);
            }
        }
    }
        
    @Override
    public String toString(){
        String returnString = "";            
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (j == 0){
                    returnString += currTabuleiro[i][j][0];
                }else{
                    returnString += "|";
                    returnString += currTabuleiro[i][j][0];
                }                 
            }
            returnString += "\n";
        }
        return returnString;
    }
}