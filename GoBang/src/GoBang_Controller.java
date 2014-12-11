


import java.awt.event.*;  import javax.swing.*;
public class GoBang_Controller {    
    private GoBang_Model gbm;
    private GoBang_View gbv;
    
    public GoBang_Controller(GoBang_Model gbm, GoBang_View gbv) {
        this.gbm = gbm;
        this.gbv = gbv;
        
        this.gbv.addActionForGameMode(new GameMode());
        this.gbv.addActionForOKMainMenu(new OKMainMenu());
        this.gbv.addActionForEsc(new EscButton());
        
        this.gbv.addActionForStonesP1(new P1Stone());
        this.gbv.addActionForStonesP2(new P2Stone());
        this.gbv.addActionForOKGamer(new OKGamer());        
        
        this.gbv.addActionForRule(new RuleAction());
        this.gbv.addActionForQuit(new QuitAction());  
        this.gbv.addActionForRestart(new RestartAction());
    }

    class GameMode implements ActionListener {  // set game mode based on the chosen radio button
        @Override
        public void actionPerformed(ActionEvent ae) {
            JRadioButton r = (JRadioButton) ae.getSource();
            if (r.isSelected()) {
                if (r.getText().equals("Human vs Human")) {
                    gbm.setGameModeChoice((byte) 1);
                    gbv.getT2().setText("Player 2");
                    gbv.getT2().setEditable(true);
                } else if (r.getText().equals("Human vs Computer")) {
                    gbm.setGameModeChoice((byte) 2);
                    gbv.getT2().setText("Computer");
                    gbv.getT2().setEditable(false);
                }
            }                        
        }
    }

    class OKMainMenu implements ActionListener {  // display the next panel asking users to enter names and choose stone
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (gbm.getGameModeChoice() == 0) {
                JOptionPane.showMessageDialog(null, "Please choose a Game Mode that you want!", "Game Mode", 
                        JOptionPane.ERROR_MESSAGE);  // inform players that no game mode is chosen
            } else if (gbm.getGameModeChoice() == 1 || gbm.getGameModeChoice() == 2) {
                gbv.getMainMenu().setVisible(false);
                gbv.add(gbv.getPlayers());
                gbv.setSize(900, 300);
                gbv.setLocationRelativeTo(null);     
                gbv.setTitle("Players' names and stones");
                gbv.getPlayers().setVisible(true);
            }
        }
    }
    
    class EscButton implements ActionListener {  // exit the application
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    //--------------
    
    class P1Stone implements ActionListener {  // set player 1's stone based on the chosen button
        @Override
        public void actionPerformed(ActionEvent e) {            
            if (e.getSource() == gbv.getBlueStoneP1()) {
                gbm.setP1Stone("blue");
                gbv.getBlueStoneP2().setVisible(false);  // hide the stone player 1 just chose from player 2's options
                gbv.getBlackStoneP1().setVisible(false);
                gbv.getGreenStoneP1().setVisible(false);  // hide other options from player 1
                gbv.getPurpleStoneP1().setVisible(false);
            } else if (e.getSource() == gbv.getBlackStoneP1()) {
                gbm.setP1Stone("black");
                gbv.getBlackStoneP2().setVisible(false);
                gbv.getBlueStoneP1().setVisible(false);
                gbv.getGreenStoneP1().setVisible(false);
                gbv.getPurpleStoneP1().setVisible(false);
            } else if (e.getSource() == gbv.getGreenStoneP1()) {
                gbm.setP1Stone("green");
                gbv.getGreenStoneP2().setVisible(false);
                gbv.getBlackStoneP1().setVisible(false);
                gbv.getBlueStoneP1().setVisible(false);
                gbv.getPurpleStoneP1().setVisible(false);
            } else if (e.getSource() == gbv.getPurpleStoneP1()) {
                gbm.setP1Stone("purple");
                gbv.getPurpleStoneP2().setVisible(false);
                gbv.getBlackStoneP1().setVisible(false);
                gbv.getGreenStoneP1().setVisible(false);
                gbv.getBlueStoneP1().setVisible(false);
            }                   
        }
    }
    
    class P2Stone implements ActionListener {  // set player 2's stone based on the chosen button
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gbv.getBlueStoneP2()) {
                gbm.setP2Stone("blue");
                gbv.getBlueStoneP1().setVisible(false);  // hide the stone player 2 just chose from player 1's options
                gbv.getBlackStoneP2().setVisible(false);
                gbv.getGreenStoneP2().setVisible(false);  // hide other options from player 2
                gbv.getPurpleStoneP2().setVisible(false);
            } else if (e.getSource() == gbv.getBlackStoneP2()) {
                gbm.setP2Stone("black");
                gbv.getBlackStoneP1().setVisible(false);
                gbv.getBlueStoneP2().setVisible(false);  gbv.getGreenStoneP2().setVisible(false);
                gbv.getPurpleStoneP2().setVisible(false);
            } else if (e.getSource() == gbv.getGreenStoneP2()) {
                gbm.setP2Stone("green");
                gbv.getGreenStoneP1().setVisible(false);
                gbv.getBlackStoneP2().setVisible(false);  gbv.getBlueStoneP2().setVisible(false);
                gbv.getPurpleStoneP2().setVisible(false);
            } else if (e.getSource() == gbv.getPurpleStoneP2()) {
                gbm.setP2Stone("purple");
                gbv.getPurpleStoneP1().setVisible(false);
                gbv.getBlackStoneP2().setVisible(false);  gbv.getGreenStoneP2().setVisible(false);
                gbv.getBlueStoneP2().setVisible(false);
            }        
        }
    }
    
    class OKGamer implements ActionListener {  // display game board
        @Override
        public void actionPerformed(ActionEvent e) {                                      
            if (gbm.getP1Stone().equals("") && gbm.getP2Stone().equals("")) {
                JOptionPane.showMessageDialog(null, "Please choose a stone for each player in order to play", 
                    "Choose stone", JOptionPane.INFORMATION_MESSAGE);  // inform players that no stone is chosen
            } else {
                gbv.getP1Turn().setText(gbv.getT1().getText() + "'s stones");
                gbv.getP2Turn().setText(gbv.getT2().getText() + "'s stones");            
                gbv.setSttP1(gbv.getT1().getText() + " (1st player)");
                gbv.setSttP2(gbv.getT2().getText() + " (2nd player)");
                gbv.setPlayer1Stone(); gbv.setPlayer2Stone();
                gbv.setWinner();                            
                gbv.getPlayers().setVisible(false);            
                gbv.add(gbv.getBoard());
                gbv.setTitle("GoBang Game");
                gbv.setSize(980, 800);
                gbv.setLocationRelativeTo(null);
                gbv.getBoard().setVisible(true);
            }           
        }
    }
    
    //-------------------
    
    class RuleAction implements ActionListener {  // display the game's rule
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "This is a traditional Chinese board game."
                + "\nIn this game, you need to place 5 identical stones vertically/horizontally/diagonally\n  "
                + "  at the intersection of the lines before other player in order to win.\nIf the board "
                + "is full but no one has won, the result will be tied", "Rules", 
                JOptionPane.QUESTION_MESSAGE);
        }
        
    }
    
    class QuitAction implements ActionListener {   // quit the game, return to main menu
        @Override
        public void actionPerformed(ActionEvent e) {
            gbm.restartGame();            
            gbm.setP1Stone(""); gbm.setP2Stone("");
            gbv.getStoneStt().removeAll();
            gbv.setAllStoneVisible();
            gbm.status.setText("1st player's turn");
            gbv.getBoard().setVisible(false);
            gbv.add(gbv.getMainMenu());
            gbv.setTitle("GoBang - Main Menu"); 
            gbv.setSize(800, 500);
            gbv.setLocationRelativeTo(null);
            gbv.getMainMenu().setVisible(true);
            gbv.setResizable(false);
        }        
    } 
    
    class RestartAction implements ActionListener {  // restart the game
        @Override
        public void actionPerformed(ActionEvent e) {
            gbm.restartGame();            
        }
    }    
}
