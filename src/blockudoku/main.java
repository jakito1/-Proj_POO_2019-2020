/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.IOException;

/**
 *
 * @author tidag
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Console console = new Console();
        /*
        Ranking ranking = new Ranking();
        Score score = new Score(GameMode.BASICMODE);
        Player player = new Player("Saving Test");
        History history = new History();
        Tray tray = new Tray();
        Blocks block1 = new Blocks(Shapes.ADVANCED_I3, 0);
        Blocks block2 = new Blocks(Shapes.ADVANCED_I3, 0);
        Blocks block3 = new Blocks(Shapes.BASIC_T, 0);
        Blocks block4 = new Blocks(Shapes.BASIC_T, 1);
        Blocks[] blockArray = {block1, block2, block3};
        
        Game game = new Game(player, GameMode.BASICMODE);
        System.out.println(ranking);
        
        //testing blocks insertion on the same place
        System.out.println(tray);
        tray.putOnTray(" A -a 1 ", blockArray, score);
        System.out.println(tray);
        tray.putOnTray(" b -a 2 ", blockArray, score);
        System.out.println(tray);
        
        //testing block rotation
        tray.setDefaultTray();
        tray.putOnTray(" c -a 2 ", blockArray, score);
        System.out.println(tray);
        blockArray[2] = block4;
        tray.setDefaultTray();
        tray.putOnTray(" c -a 2 ", blockArray, score);
        System.out.println(tray);
        
             
        System.out.println(player.getNickName());
        System.out.println("");
        try{
            SaveLoad.serializeDataOut(player, "TEST FILE");
            System.out.println("Passed\n");
        }catch (IOException e){
            System.out.println("Failed\n");
        }
        try{
            player = SaveLoad.serializeDataIn("TEST FILE");
            System.out.println("Passed\n");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Failed\n");
        }
        System.out.println(player.getNickName());
        */
    }
    
}

