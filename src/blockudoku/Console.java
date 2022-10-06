/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import Helpers.InputReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class Console {

    private final InputReader reader;    
    private Game game;
    private Player player;
    private String playerNick;
    private GameMode mode;
    private Ranking ranking;
    private Blocks[] blockArray;
    private boolean loaded;
    private static final Random RANDOM = new Random();
            
    /**
     * Will build a console instance.
     */
    public Console(){
        this.reader = new InputReader();
        ranking = new Ranking();
        blockArray = new Blocks[3];
        loaded = false;
        Start();
    }
       
    /**
     * Will start the console.
     */
    private void Start(){
        System.out.println("|BLOCKUDOKU|\n");
        playerNick = requestNickName();
        try{
            player = SaveLoad.serializeDataIn("saves\\" + playerNick);
        } catch (IOException | ClassNotFoundException ex) {
            this.player = new Player(playerNick);
        }
        System.out.println("\n\nOlá " + player.getNickName());
        Menu();    
    }
    
    /**
     * Will show the diferent modes, redirecting to the command processor
     */
    private void newGameMenu(){
        System.out.println("1 - Iniciar Novo Jogo - Modo Básico");
        System.out.println("2 - Iniciar Novo Jogo - Modo Avançado");
        System.out.println("0 - Voltar");
        processNewGameCommand();
    }
    
    /**
     * Will show the primary game menu
     */
    private void Menu(){
        System.out.println("1 - Iniciar Novo Jogo");
        System.out.println("2 - Abrir Jogo");
        System.out.println("3 - Mostrar pontuações pessoais");
        System.out.println("4 - RANKING (TOP 10)");
        System.out.println("0 - Sair");
        processMenuCommand();
    }
        
    /**
     * Text processor
     * Will deal with the diferent choices
     */
    private void processMenuCommand(){
        int option;
        do {
            option = reader.getIntegerNumber("Escolha uma opção");
        }while (option < 0 || option > 4);
        System.out.println("\n\n");
        switch (option) {
            case 0:
                if (!ranking.toString().isEmpty()){
                    try{
                        SaveLoad.serializeDataOut(ranking, "saves\\" + "RANKING");
                    }catch (IOException e){
                        System.out.println("Ranking não gravado!");
                        Menu();
                    }
                }
                
                try{
                    SaveLoad.serializeDataOut(player, "saves\\" + playerNick);
                    System.out.println("\nObrigado por jogar BlockuDoku!");
                    System.exit(0);                   
                }catch (IOException e){
                    System.out.println("Perfil não gravado!");
                    Menu();
                }
                break;
            case 1:
                newGameMenu();
                break;
            case 2:
                loadGame();
                break;
            case 3:
                if (player.getHistory().toString().isEmpty()){
                    System.out.println("Não existe qualquer histórico.\n");
                }else{
                    System.out.println(player.getHistory());
                }
                Menu();
                break;
            case 4:
                try{
                    ranking = SaveLoad.serializeDataIn("saves\\" + "RANKING");
                    System.out.println(ranking);
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Ranking Vazio.\n");
                }
                Menu();
                break;
        }
    }
    
    /**
     * Will process the command related to the diferent game modes
     */
    private void processNewGameCommand(){        
        int option;
        do {
            option = reader.getIntegerNumber("Escolha uma opção");
        }while (option < 0 || option > 2);       
        switch (option) {
            case 0:
                System.out.println("\n\n");
                Menu();
                break;
            case 1:
                this.mode = GameMode.BASICMODE;
                game();
                break;
            case 2: 
                this.mode = GameMode.ADVANCEDMODE;
                game();
                break;
        }       
    }
     
    /**
     * This is the main game itself
     * Will process and deal with the inputs related
     */
    private void game(){
        String command = "";
        boolean isBasicMode = (GameMode.BASICMODE == mode);
        if (game == null || !game.getPlayer().equalsIgnoreCase(playerNick)
                || !loaded){
            game = new Game(player, mode);
            blockArray = new Blocks[3];
            game.getTray().setDefaultTray();
        }       
        System.out.println(game.getTray());
        do {
            if (command.equalsIgnoreCase("CANCEL") || command.equalsIgnoreCase("SAVE")){
                break;
            }else{
                if (blockArray[0] == null && blockArray[1] == null 
                        && blockArray[2] == null){
                    for (int i = 0; i < 3; i++){
                        blockArray[i] = new Blocks
                        (Shapes.getRandomShape(isBasicMode), RANDOM.nextInt(3));
                    }
                }               
                while (!game.getTray().checkEndGame(blockArray, game, ranking) ){
                    if (blockArray[0] == null && blockArray[1] == null
                            && blockArray[2] == null){
                        break;
                    }
                    System.out.println("\nScore: " + game.getGameScore() + "\n");
                    showPieces(blockArray);
                    command = reader.getText("Indique a próxima jogada (Bloco-ColunaLinha)");
                    try{
                        blockArray = game.getTray().putOnTray(command, blockArray, game.getScore());
                        System.out.println(game.getTray());
                    }catch (NullPointerException 
                            | ArrayIndexOutOfBoundsException 
                            | NumberFormatException e){
                        if (command.equalsIgnoreCase("CANCEL") || command.equalsIgnoreCase("SAVE")){
                            break;
                        }else{
                            System.out.println("Comando Inválido!");
                            System.out.println(game.getTray());
                        }                   
                    }                                       
                    if (game.getTray().clearTray(game.getScore())){
                        System.out.println(game.getTray());
                    }                   
                }
            }
                       
        } while (!command.equalsIgnoreCase("CANCEL") || !command.equalsIgnoreCase("SAVE"));
        
        command = command.toUpperCase();
        switch (command) {
            case "CANCEL":
                loaded = false;
                Menu();
                break;
            case "SAVE":
                String saveGame = reader.getText("Insira o nome do Save");
                player.saveGame(saveGame, game);
                game.saveBlocks(blockArray);
                loaded = false;
                Menu();
                break;
        }
    }
     
    /**
     * 
     * @param blocks Blocs array
     * Will display the diferent game pieces
     */
    private void showPieces(Blocks[] blocks){
        System.out.println("Blocos a jogar:\n");
        for (int i = 0; i < 3; i++){
            if (blocks[i] != null){
                switch (i){
                    case 0:
                        System.out.println("Bloco A");
                        break;
                    case 1:
                        System.out.println("Bloco B");
                        break;
                    case 2:
                        System.out.println("Bloco C");
                        break;                      
                }
                System.out.println(blocks[i]);
            }
        }
    }
    
    /**
     * 
     * @return String
     * Requests the player nickName and returns it
     */
    private String requestNickName() {
        String nickName = "";
        do {
            nickName = reader.getText("Insira o seu nickname");
            if (nickName.equals("")) {
                System.out.println("Input invalido! Insira um nickname válido.");
            }
        } while (nickName.equals(""));
        return nickName;
    }
    
    /**
     * Will load a previously saved game
     */
    private void loadGame(){
        if (player.getSavesName().equalsIgnoreCase("[]")){
            System.out.println("Não existe nenhum save.\n");
            Menu();
        }else{
            System.out.println(player.getSavesName());
            String loadName = reader.getText("Insira o nome do save: ");
            try{
            game = player.loadGame(loadName);
            mode = player.loadGame(loadName).getGameMode();
            blockArray = player.loadGame(loadName).getBlocks();
            loaded = true;
            game();
            }catch (NullPointerException e){
                System.out.println("Nome inválido.\n");
                Menu();
            }
        }
    }
}
