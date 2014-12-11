


import java.util.Random;  import java.awt.*; 
import javax.swing.*;  import java.awt.event.*;
public class GoBang_Model {      
    Slot[][] slot = new Slot[13][13];
    private byte whoseTurn = 1;  // determine the turn of players (1 or player 1 and 2 for player 2
    private int player1Turn, player2Turn;  // number of stones each player's placed          
    private byte gameModeChoice;  // determine the game mode user chose
    private String player1Name, player2Name;  // determine players' name
    
    private String p1Stone, p2Stone;  // determine the type of stones players chose
    private ImageIcon blue = new ImageIcon("images/bluedia.jpg");  
    private ImageIcon black = new ImageIcon("images/blackdia.jpg");
    private ImageIcon green = new ImageIcon("images/greendia.jpg");
    private ImageIcon purple = new ImageIcon("images/purpledia.jpg");
        
    protected JTextField p1NumStone = new JTextField();  // display number of stones player 1 has placed
    protected JTextField p2NumStone = new JTextField();  // display number of stones player 2 has placed
    protected JTextField total = new JTextField();  // display total stones
    protected JTextField status = new JTextField(10);  // display game status (players' turn)
            
    public GoBang_Model() {                
        for (byte i = 0; i < slot.length; i++) {
            for (byte j = 0; j < slot[i].length; j++) {
                slot[i][j] = new Slot();  // initialize the cells
            }
        }  
        status.setText("1st player's turn");       
        status.setEditable(false);
        p1Stone = "";
        p2Stone = "";
    }
    
    public void setP1Stone(String s) {
        p1Stone = s;
    }
    
    public void setP2Stone(String s) {
        p2Stone = s;
    }
    
    public String getP1Stone() {
        return p1Stone;
    }
    
    public String getP2Stone() {
        return p2Stone;
    }

    public void setPlayer1Name(String n) {
        player1Name = n;
    }
    
    public void setPlayer2Name(String n) {
        player2Name = n;
    }

    public int getTotalTurn() {  // calculate total number of stones
        return player1Turn + player2Turn;
    }
      
    public void setGameModeChoice(byte x) {
        gameModeChoice = x;
    }

    public byte getGameModeChoice() {
        return gameModeChoice;
    }
    /*************************/    
    public boolean isTied() {  // check if the board is full of stones but no one wins
        for (byte i = 0; i < slot.length; i++) {
            for (byte j = 0; j < slot[i].length; j++) {
                if (slot[i][j].getStone() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isFinished(byte stone) {  // check if any user has five stones placed vertically/horizontally/diagonally
        for (byte i = 0; i < slot.length; i++) {
            for (byte j = 0; j < slot[i].length; j++) {  
                if (j + 4 < 13 && slot[i][j].getStone() == stone && slot[i][j + 1].getStone() == stone && 
                    slot[i][j + 2].getStone() == stone && slot[i][j + 3].getStone() == stone && 
                    slot[i][j + 4].getStone() == stone) {  // check horizontal
                    return true;    
                }
                
                if (i + 4 < 13 && slot[i][j].getStone() == stone && slot[i + 1][j].getStone() == stone && 
                    slot[i + 2][j].getStone() == stone && slot[i + 3][j].getStone() == stone && 
                    slot[i + 4][j].getStone() == stone) {  // check vertical
                    return true;
                }
                
                if (i + 4 < 13 && j + 4 < 13 && slot[i][j].getStone() == stone && slot[i + 1][j + 1].getStone() == stone && 
                    slot[i + 2][j + 2].getStone() == stone && slot[i + 3][j + 3].getStone() == stone && 
                    slot[i + 4][j + 4].getStone() == stone) {  // check diagonal
                    return true;
                }
                
                if (i + 4 < 13 && j - 4 >= 0 && slot[i][j].getStone() == stone && slot[i + 1][j - 1].getStone() == stone && 
                    slot[i + 2][j - 2].getStone() == stone && slot[i + 3][j - 3].getStone() == stone && 
                    slot[i + 4][j - 4].getStone() == stone) {  // check diagonal
                    return true;
                }
            }
        }
        return false;
    }  
    
    public int[] AIMoves() {  // generate computer's moves for human vs computer       
        for (byte i = 0; i < 13; i++) {  // check player 1's moves and prevent the player from winning
            for (byte j = 0; j < 13; j++) {
                /* check horizontal */
                if (j == 0) {  // check left border
                    if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 && slot[i][j + 2].getStone() == 1 &&
                            slot[i][j + 3].getStone() == 1) {
                        return new int[]{i, (j + 4)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 &&
                            slot[i][j + 2].getStone() == 1 && slot[i][j + 3].getStone() == 0 &&
                            slot[i][j + 4].getStone() == 1) {
                        return new int[]{i, (j + 3)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 0 &&
                            slot[i][j + 2].getStone() == 1 && slot[i][j + 3].getStone() == 1 &&
                            slot[i][j + 4].getStone() == 1) {
                        return new int[]{i, (j + 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 &&
                            slot[i][j + 2].getStone() == 0 && slot[i][j + 3].getStone() == 1 &&
                            slot[i][j + 4].getStone() == 1) {
                        return new int[]{i, (j + 2)};
                    }
                }
                if (j == 9 && slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 && slot[i][j + 2].getStone() == 1 &&
                        slot[i][j + 3].getStone() == 1 && slot[i][j - 1].getStone() == 0) {
                    return new int[]{i, (j - 1)};  // check right border
                }
                if (j - 1 >= 0 && j + 4 < 13) {  // check slots in the middle of the board
                    if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 && slot[i][j + 2].getStone() == 1
                            && slot[i][j + 3].getStone() == 1 && slot[i][j - 1].getStone() == 2
                            && slot[i][j + 4].getStone() == 0) {
                        return new int[]{i, (j + 4)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 &&
                            slot[i][j + 2].getStone() == 1 && slot[i][j + 3].getStone() == 1 &&
                            slot[i][j + 4].getStone() == 2 && slot[i][j - 1].getStone() == 0) {
                        return new int[]{i, (j - 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 &&
                            slot[i][j + 2].getStone() == 1 && slot[i][j + 3].getStone() == 0 && slot[i][j + 4].getStone() == 1) {
                        return new int[] {i, (j + 3)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 &&
                            slot[i][j + 2].getStone() == 0 && slot[i][j + 3].getStone() == 1 &&
                            slot[i][j + 4].getStone() == 1) {
                        return new int[] {i, (j + 2)};
                    }
                }
                if (j - 1 >= 0 && j + 3 < 13) {  // check slots in the middle of the board
                    if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 &&
                            slot[i][j + 2].getStone() == 0 && slot[i][j + 3].getStone() == 1) {
                        return new int[] {i, (j + 2)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 0 &&
                            slot[i][j + 2].getStone() == 1 && slot[i][j + 3].getStone() == 1) {
                        return new int[] {i, (j + 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i][j + 1].getStone() == 1 && slot[i][j + 2].getStone() == 1 &&
                            slot[i][j - 1].getStone() == 0 && slot[i][j + 3].getStone() == 0) {
                        if (slot[i][j - 1].getStone() == 0) {
                            return new int[] {i, (j - 1)};
                        } else if (slot[i][j + 3].getStone() == 0) {
                            return new int[] {i, (j + 3)};
                        }
                    }
                }
                /* check vertical */
                if (i == 0) {  // check the top border
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&
                            slot[i + 2][j].getStone() == 1 && slot[i + 3][j].getStone() == 1) {
                        return new int[] {(i + 4), j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&
                            slot[i + 2][j].getStone() == 1 && slot[i + 3][j].getStone() == 0 &&
                            slot[i + 4][j].getStone() == 1) {
                        return new int[] {(i + 3), j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 0 &&
                            slot[i + 2][j].getStone() == 1 && slot[i + 3][j].getStone() == 1 &&
                            slot[i + 4][j].getStone() == 1) {
                        return new int[] {(i + 1), j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&
                            slot[i + 2][j].getStone() == 0 && slot[i + 3][j].getStone() == 1 &&
                            slot[i + 4][j].getStone() == 1) {
                        return new int[] {(i + 2), j};
                    }
                }
                if (i == 9 && slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&  // check the bottom border
                        slot[i + 2][j].getStone() == 1 && slot[i + 3][j].getStone() == 1 && slot[i - 1][j].getStone() == 0) {
                    return new int[]{(i - 1), j};
                }
                if (i - 1 >= 0 && i + 4 < 13) {  // check slots that are not at the borders
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 && slot[i + 2][j].getStone() == 1
                            && slot[i + 3][j].getStone() == 1 && slot[i - 1][j].getStone() == 2
                            && slot[i + 4][j].getStone() == 0) {
                        return new int[]{i + 4, j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 && slot[i + 2][j].getStone() == 1
                            && slot[i + 3][j].getStone() == 1 && slot[i + 4][j].getStone() == 2
                            && slot[i - 1][j].getStone() == 0) {
                        return new int[]{i - 1, j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&
                            slot[i + 2][j].getStone() == 1 && slot[i + 3][j].getStone() == 0 && slot[i + 4][j].getStone() == 1) {
                        return new int[] {(i + 3), j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&
                            slot[i + 2][j].getStone() == 0 && slot[i + 3][j].getStone() == 1 &&
                            slot[i + 4][j].getStone() == 1) {
                        return new int[] {i + 2, j};
                    }
                }
                if (i - 1 >= 0 && i + 3 < 13) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 &&
                            slot[i + 2][j].getStone() == 0 && slot[i + 3][j].getStone() == 1) {
                        return new int[] {(i + 2), j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 0 &&
                            slot[i + 2][j].getStone() == 1 && slot[i + 3][j].getStone() == 1) {
                        return new int[] {(i + 1), j};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j].getStone() == 1 && slot[i + 2][j].getStone() == 1 &&
                            slot[i - 1][j].getStone() == 0 && slot[i + 3][j].getStone() == 0) {
                        if (slot[i - 1][j].getStone() == 0) {
                            return new int[] {i - 1, j};
                        } else if (slot[i + 3][j].getStone() == 0) {
                            return new int[] {i + 3, j};
                        }
                    }
                }
                /* check diagonal */
                if ((i == 0 || j == 0) && j + 4 < 13 && i + 4 < 13) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 1
                            && slot[i + 4][j + 4].getStone() == 0) {
                        return new int[] {(i + 4), (j + 4)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 0 && slot[i + 3][j + 3].getStone() == 1) {
                        return new int[] {i + 2, j + 2};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 0 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 1) {
                        return new int[] {(i + 1), (j + 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 0 && slot[i + 3][j + 3].getStone() == 1 &&
                            slot[i + 4][j + 4].getStone() == 1) {
                        return new int[] {(i + 2), (j + 2)};
                    }
                }
                if (j == 9 && i + 3 < 13 && j + 3 < 13 && i - 1 >= 0 && j - 1 >= 0) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 1 &&
                            slot[i - 1][j - 1].getStone() == 0) {
                        return new int[]{(i - 1), j - 1};
                    }
                }
                if (i - 1 >= 0 && j - 1 >= 0 && i + 4 <= 12 && j + 4 <= 12) {  // check diagonal
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 1 && 
                            slot[i - 1][j - 1].getStone() == 2 && slot[i + 4][j + 4].getStone() == 0) {
                        return new int[] {(i + 4), (j + 4)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 1 && 
                            slot[i + 4][j + 4].getStone() == 2 && slot[i - 1][j - 1].getStone() == 0) {
                        return new int[] {(i - 1), (j - 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 0 &&
                            slot[i + 4][j + 4].getStone() == 1) {
                        return new int[] {(i + 3), j + 3};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 0 && slot[i + 3][j + 3].getStone() == 1 &&
                            slot[i + 4][j + 4].getStone() == 1) {
                        return new int[] {(i + 2), (j + 2)};
                    }
                }
                if (i - 1 >= 0 && j - 1 >= 0 && i + 3 <= 12 && j + 3 <= 12) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 &&
                            slot[i + 2][j + 2].getStone() == 0 && slot[i + 3][j + 3].getStone() == 1) {
                        return new int[] {i + 2, j + 2};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 0 &&
                            slot[i + 2][j + 2].getStone() == 1 && slot[i + 3][j + 3].getStone() == 1) {
                        return new int[] {(i + 1), (j + 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j + 1].getStone() == 1 && slot[i + 2][j + 2].getStone() == 1
                            && slot[i - 1][j - 1].getStone() == 0 && slot[i + 3][j + 3].getStone() == 0) {
                        if (slot[i - 1][j - 1].getStone() == 0) {
                            return new int[] {(i - 1), (j - 1)};
                        } else if (slot[i + 3][j + 3].getStone() == 0) {
                            return new int[] {(i + 3), (j + 3)};
                        }
                    }
                }

                /* check diagonal */
                if ((i == 0 || j == 12) && i + 4 < 13 && j - 4 >= 0) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 1
                            && slot[i + 4][j - 4].getStone() == 0) {
                        return new int[] {(i + 4), (j - 4)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 0 && slot[i + 3][j - 3].getStone() == 1) {
                        return new int[] {i + 2, j - 2};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 0 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 1) {
                        return new int[] {(i + 1), (j - 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 0 && slot[i + 3][j - 3].getStone() == 1 &&
                            slot[i + 4][j - 4].getStone() == 1) {
                        return new int[] {(i + 2), (j - 2)};
                    }
                }
                if (j == 3 && i - 1 >= 0 && j + 1 < 13 && i + 3 < 13 && i - 3 >= 0) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 1 &&
                            slot[i - 1][j + 1].getStone() == 0) {
                        return new int[]{(i - 1), j + 1};
                    }
                }
                if (i - 1 >= 0 && j + 1 < 13 && i + 4 < 13 && j - 4 >= 0) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 1 && 
                            slot[i - 1][j + 1].getStone() == 2 && slot[i + 4][j - 4].getStone() == 0) {
                        return new int[] {(i + 4), (j - 4)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 1 && 
                            slot[i + 4][j - 4].getStone() == 2 && slot[i - 1][j + 1].getStone() == 0) {
                        return new int[] {(i - 1), (j + 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 0 &&
                            slot[i + 4][j - 4].getStone() == 1) {
                        return new int[] {(i + 3), j - 3};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 0 && slot[i + 3][j - 3].getStone() == 1 && 
                            slot[i + 4][j - 4].getStone() == 1) {
                        return new int[] {(i + 2), (j - 2)};
                    }
                }
                if (i - 1 >= 0 && j + 1 < 13 && i + 4 < 13 && j - 4 >= 0) {
                    if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 &&
                            slot[i + 2][j - 2].getStone() == 0 && slot[i + 3][j - 3].getStone() == 1) {
                        return new int[] {(i + 2), (j - 2)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 0 &&
                            slot[i + 2][j - 2].getStone() == 1 && slot[i + 3][j - 3].getStone() == 1) {
                        return new int[] {(i + 1), (j - 1)};
                    } if (slot[i][j].getStone() == 1 && slot[i + 1][j - 1].getStone() == 1 && slot[i + 2][j - 2].getStone() == 1
                            && slot[i + 3][j - 3].getStone() == 0 && slot[i - 1][j + 1].getStone() == 0) {
                        if (slot[i - 1][j + 1].getStone() == 0) {
                            return new int[] {(i - 1), (j + 1)};
                        } else if (slot[i + 3][j - 3].getStone() == 0) {
                            return new int[] {(i + 3), (j - 3)};
                        }
                    }
                }
            }
        }
        
        for (byte i = 0; i < 13; i++) {  // computer places stone horizontally/vertically/diagonally to win
            for (byte j = 0; j < 13; j++) {
                if (j + 4 < 13 || j - 1 >= 0) {  // check if four stones are already placed horizontally
                    if (j + 4 < 13) {
                        if (slot[i][j].getStone() == 2 && slot[i][j + 1].getStone() == 2 && slot[i][j + 2].getStone() == 2 &&
                                slot[i][j + 3].getStone() == 2 && slot[i][j + 4].getStone() == 0) {
                            return new int[]{i, (j + 4)};
                        }
                    } if (j - 1 >= 0 && j + 3 < 13) {
                        if (slot[i][j].getStone() == 2 && slot[i][j + 1].getStone() == 2 && slot[i][j + 2].getStone() == 2
                                && slot[i][j + 3].getStone() == 2 && slot[i][j - 1].getStone() == 0) {
                            return new int[]{i, (j - 1)};
                        }
                    }
                }
                // check if four stones are placed vertically
                if (i + 4 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j].getStone() == 2 && slot[i + 2][j].getStone() == 2 &&
                            slot[i + 3][j].getStone() == 2 && slot[i + 4][j].getStone() == 0) {
                        return new int[]{i + 4, j};
                    }
                } if (i - 1 >= 0 && i + 3 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j].getStone() == 2 && slot[i + 2][j].getStone() == 2 &&
                            slot[i + 3][j].getStone() == 2 && slot[i - 1][j].getStone() == 0) {
                        return new int[]{i - 1, j};
                    }
                }
                // check if four stones are placed diagonally
                if (i + 4 < 13 && j + 4 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j + 1].getStone() == 2 &&
                            slot[i + 2][j + 2].getStone() == 2 && slot[i + 3][j + 3].getStone() == 2 &&
                            slot[i + 4][j + 4].getStone() == 0) {
                        return new int[]{(i + 4), (j + 4)};
                    }
                } if (i - 1 >= 0 && j - 1 >= 0 && i + 3 < 13 && j + 3 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j + 1].getStone() == 2 &&
                            slot[i + 2][j + 2].getStone() == 2 && slot[i + 3][j + 3].getStone() == 2 &&
                            slot[i - 1][j - 1].getStone() == 0) {
                        return new int[]{(i - 1), (j - 1)};
                    }
                }
                // check if four stones are placed diagonally
                if (i + 4 < 13 && j - 4 >= 0) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j - 1].getStone() == 2 &&
                            slot[i + 2][j - 2].getStone() == 2 && slot[i + 3][j - 3].getStone() == 2 &&
                            slot[i + 4][j - 4].getStone() == 0) {
                        return new int[]{(i + 4), (j - 4)};
                    }
                } if (i - 1 >= 0 && j + 1 < 13 && i + 3 < 13 && j - 3 >= 0) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j - 1].getStone() == 2 &&
                            slot[i + 2][j - 2].getStone() == 2 && slot[i + 3][j - 3].getStone() == 2 &&
                            slot[i - 1][j + 1].getStone() == 0) {
                        return new int[]{(i - 1), (j + 1)};
                    }
                }
                /*********************/
                // check if three stones are placed horizontally
                if (j + 3 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i][j + 1].getStone() == 2 && slot[i][j + 2].getStone() == 2
                            && slot[i][j + 3].getStone() == 0) {
                        return new int[]{i, (j + 3)};
                    }
                } if (j - 1 >= 0 && j + 2 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i][j + 1].getStone() == 2 && slot[i][j + 2].getStone() == 2
                            && slot[i][j - 1].getStone() == 0) {
                        return new int[]{i, (j - 1)};
                    }
                }

                // check if three stones are placed vertically
                if (i + 3 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j].getStone() == 2 && slot[i + 2][j].getStone() == 2
                            && slot[i + 3][j].getStone() == 0) {
                        return new int[]{i + 3, j};
                    }
                } if (i - 1 >= 0 && i + 2 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j].getStone() == 2 && slot[i + 2][j].getStone() == 2
                            && slot[i - 1][j].getStone() == 0) {
                        return new int[]{i - 1, j};
                    }
                }
                 // check if three stones are placed diagonally
                if (i + 3 < 13 && j + 3 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j + 1].getStone() == 2 &&
                            slot[i + 2][j + 2].getStone() == 2 && slot[i + 3][j + 3].getStone() == 0) {
                        return new int[]{(i + 3), (j + 3)};
                    }
                } if (i - 1 >= 0 && j - 1 >= 0 && i + 2 < 13 && j + 2 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j + 1].getStone() == 2 &&
                            slot[i + 2][j + 2].getStone() == 2 && slot[i - 1][j - 1].getStone() == 0) {
                        return new int[]{(i - 1), (j - 1)};
                    }
                }
                // check if three stones are placed diagonally
                if (i + 3 < 13 && j - 3 >= 0) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j - 1].getStone() == 2 &&
                            slot[i + 2][j - 2].getStone() == 2 && slot[i + 3][j - 3].getStone() == 0) {
                        return new int[]{(i + 3), (j - 3)};
                    }
                } if (i - 1 >= 0 && j + 1 < 13 && i + 2 < 13 && j - 2 >= 0) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j - 1].getStone() == 2 &&
                            slot[i + 2][j - 2].getStone() == 2 && slot[i - 1][j + 1].getStone() == 0) {
                        return new int[]{(i - 1), (j + 1)};
                    }
                }
                /*********************/
                 // check if two stones are placed horizontally
                if (j + 2 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i][j + 1].getStone() == 2 && slot[i][j + 2].getStone() == 0) {
                        return new int[]{i, (j + 2)};
                    }
                } if (j - 1 >= 0 && j + 1 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i][j + 1].getStone() == 2 && slot[i][j - 1].getStone() == 0) {
                        return new int[]{i, (j - 1)};
                    }
                }
                // check if two stones are placed vertically
                if (i + 2 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j].getStone() == 2 && slot[i + 2][j].getStone() == 0) {
                        return new int[]{i + 2, j};
                    }
                } if (i - 1 >= 0 && i + 1 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j].getStone() == 2 && slot[i - 1][j].getStone() == 0) {
                        return new int[]{i - 1, j};
                    }
                }
                // check if two stones are placed diagonally
                if (i + 2 < 13 && j + 2 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j + 1].getStone() == 2 && slot[i + 2][j + 2].getStone() == 0) {
                        return new int[]{(i + 2), (j + 2)};
                    }
                } if (i - 1 >= 0 && j - 1 >= 0 && i + 1 < 13 && j + 1 < 13) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j + 1].getStone() == 2 && slot[i - 1][j - 1].getStone() == 0) {
                        return new int[]{(i - 1), (j - 1)};
                    }
                }
                // check if two stones are placed diagonally
                if (i + 2 < 13 && j - 2 >= 0) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j - 1].getStone() == 2 && slot[i + 2][j - 2].getStone() == 0) {
                        return new int[]{(i + 2), (j - 2)};
                    }
                } if (i - 1 >= 0 && j + 1 < 13 && i + 1 < 13 && j - 1 >= 0) {
                    if (slot[i][j].getStone() == 2 && slot[i + 1][j - 1].getStone() == 2 && slot[i - 1][j + 1].getStone() == 0) {
                        return new int[]{(i - 1), (j + 1)};
                    }
                }
            }
        }
        // check if one stone is placed     
        for (byte i = 0; i < 13; i++) {
            for (byte j = 0; j < 13; j++) {
                if (slot[i][j].getStone() == 2) {
                    if (j + 1 < 13) {
                        if (slot[i][j + 1].getStone() == 0) {  // check horizontal
                            return new int[] {i, (j + 1)};
                        }
                    } if (j - 1 >= 0) {
                        if (slot[i][j - 1].getStone() == 0) {
                            return new int[] {i, (j - 1)};
                        }
                    }

                    if (i + 1 < 13) {
                        if (slot[i + 1][j].getStone() == 0) {  // check horizontal
                            return new int[] {i + 1, j};
                        }
                    } if (i - 1 >= 0) {
                        if (slot[i - 1][j].getStone() == 0) {
                            return new int[] {i - 1, j};
                        }
                    }

                    if (i + 1 < 13 && j + 1 < 13) {
                        if (slot[i + 1][j + 1].getStone() == 0) {  // check horizontal
                            return new int[] {i + 1, j + 1};
                        }
                    } if (i - 1 >= 0 && j - 1 >= 0) {
                        if (slot[i - 1][j - 1].getStone() == 0) {
                            return new int[] {i - 1, j - 1};
                        }
                    }

                    if (i + 1 < 13 && j - 1 >= 0) {
                        if (slot[i + 1][j - 1].getStone() == 0) {  // check horizontal
                            return new int[] {i + 1, j - 1};
                        }
                    } if (i - 1 >= 0 && j + 1 < 13) {
                        if (slot[i - 1][j + 1].getStone() == 0) {
                            return new int[] {i - 1, j + 1};
                        }
                    }
                }
            }
        }
        return randomStone();  // return random stones if no conditions above were met
    }

    public int[] randomStone() {  // generate random stone around the 1st stone player 1 just placed
        Random r = new Random();
        int[] random = new int[2];
        for (byte i = 0; i < 13; i++) {
            for (byte j = 0; j < 13; j++) {
                if (slot[i][j].getStone() == 1) {
                    if (i != 0 && i != 12 && j != 0 && j != 12) {  /* check the stone that is not at the borders or corners */
                        do {
                            random[0] = r.nextInt(3) + i - 1;  // place a stone randomly around the stone
                            random[1] = r.nextInt(3) + j - 1;  // player 1 just placed.
                        } while (random[0] == i && random[1] == j);  // make sure the coordinates to place stone doesn't
                    }                                                // match with player 1's stone.
                    else if (i == 0 && j == 0) {  /* check the stone at the top left corner */
                        do {
                            random[0] = r.nextInt(2);
                            random[1] = r.nextInt(2);
                        } while (random[0] == i && random[1] == j);
                    } else if (i == 0 && j != 0) {
                        if (j < 12) {  /* check the stone at the top border */
                            do {
                                random[0] = r.nextInt(2);
                                random[1] = r.nextInt(3) + j - 1;
                            }
                            while (random[0] == i && random[1] == j);
                        } else {  /* check the stone at the top right corner */
                            do {
                                random[0] = r.nextInt(2);
                                random[1] = r.nextInt(2) + j - 1;
                            }
                            while (random[0] == i && random[1] == j);
                            break;
                        }
                    } else if (i != 0 && j == 0) {
                        if (i < 12) {  /* check the stone at the left border */
                            do {
                                random[0] = r.nextInt(3) + (i - 1);
                                random[1] = r.nextInt(2);
                            }
                            while (random[0] == i && random[1] == j);
                        } else {  /* check the stone at the bottom left corner */
                            do {
                                random[0] = r.nextInt(2) + i - 1;
                                random[1] = r.nextInt(2);
                            }
                            while (random[0] == i && random[1] == j);
                        }
                    } else if (i == 12 && j != 0) {
                        if (j < 12) {  /* check the stone at the bottom border */
                            do {
                                random[0] = r.nextInt(2) + 11;
                                random[1] = r.nextInt(3) + j - 1;
                            }
                            while (random[0] == i && random[1] == j);
                        } else {  /* check the stone at the bottom right corner */
                            do {
                                random[0] = r.nextInt(2) + 11;
                                random[1] = r.nextInt(2) + j - 1;
                            }
                            while (random[0] == i && random[1] == j);
                        }
                    } else if (i > 0 && i < 12 && j == 12) {  /* check the stone at the right border */
                        do {
                            random[0] = r.nextInt(3) + i - 1;
                            random[1] = r.nextInt(2) + 11;
                        }
                        while (random[0] == i && random[1] == j);
                    }
                    break;
                }
            }
        }
        return random;
    }
    /********/
    public void restartGame() {  // restart the game
        for (byte i = 0; i < slot.length; i++) {  // delete all stones on the board
            for (byte j = 0; j < slot[i].length; j++) {                           
                slot[i][j].setBackground(Color.white);
                slot[i][j].setLine(true);
                slot[i][j].setStone((byte) 0);     
            }
        }   
        whoseTurn = 1;  // set the turn back to player 1's
        p1NumStone.setText("0");
        p2NumStone.setText("0");
        total.setText("0");
        player1Turn = 0;  // reset number of stones placed
        player2Turn = 0;
        total.setText("0");  
        status.setText("1st player's turn");  // reset game's status
    }
        
    class Slot extends JPanel {  // panel to place stone when clicked
        private boolean line;
        private byte stone;
        private JLabel label = new JLabel("     ");        
        
        public Slot() {    
            setBackground(Color.white);
            setBackground(Color.white);
            line = true;
            stone = 0;
            add(label);
            addMouseListener(new SlotAction());
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            if (line) {  
                g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
            }
            
            if (stone == 1) {  // place player 1's stone
                if (p1Stone.equals("blue")) {
                    g.drawImage(blue.getImage(), 0, 0, this);
                } else if (p1Stone.equals("black")) {
                    g.drawImage(black.getImage(), 0, 0, this);
                } else if (p1Stone.equals("green")) {
                    g.drawImage(green.getImage(), 0, 0, this);
                } else {
                    g.drawImage(purple.getImage(), 0, 0, this);
                }                    
                line = false;
            } else if (stone == 2) {  // place player 2's stone
                if (p2Stone.equals("blue")) {
                    g.drawImage(blue.getImage(), 0, 0, this);
                } else if (p2Stone.equals("black")) {
                    g.drawImage(black.getImage(), 0, 0, this);
                } else if (p2Stone.equals("green")) {
                    g.drawImage(green.getImage(), 0, 0, this);
                } else {
                    g.drawImage(purple.getImage(), 0, 0, this);
                } 
                line = false;
            }
        }
        
        public void setStone(byte s) {            
            stone = s;            
            repaint();  
            if (stone == 1) {
                player1Turn++;  // increase the number of stones player 1 places
                p1NumStone.setText(Integer.toString(player1Turn));
            } else if (stone == 2) {
                player2Turn++;  // increase the number of stones player 2 places
                p2NumStone.setText(Integer.toString(player2Turn));
            }
            total.setText(Integer.toString(getTotalTurn()));  // display total number of stones
        }
        
        public byte getStone() {
            return stone;
        }
        
        public void setLine(boolean a) {
            line = a;
        }
        
        class SlotAction implements MouseListener {
        @Override
            public void mouseClicked(MouseEvent event) {    
                if (gameModeChoice == 1) {  // generate human vs human 
                    if (stone == 0 && whoseTurn != 0) {    
                        setStone(whoseTurn);
                        if (isFinished(whoseTurn)) {  // check if any players win
                            if (whoseTurn == 1) {  // inform players that player 1 has won
                                JOptionPane.showMessageDialog(null, player1Name + " has won the game!", 
                                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            } else if (whoseTurn == 2) {  // inform players that player 2 has won
                                JOptionPane.showMessageDialog(null, player2Name + " has won the game!", 
                                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            }

                            if (whoseTurn == 1) {
                                status.setText("1st player wins");  // change status if player 1 wins
                            } else if (whoseTurn == 2) {
                                status.setText("2nd player wins");  // change status if player 2 wins
                            }

                            whoseTurn = 0;  // prevent players from placing stones
                        } else if (isTied()) {  // check if the game board is full
                            JOptionPane.showMessageDialog(null, "The game board is full. No one wins", 
                                    "Game Over", JOptionPane.INFORMATION_MESSAGE);  // inform players that the result
                                                                                    // is tied
                            status.setText("Tied");  // change status when the board is full
                            whoseTurn = 0;  // prevent players from placing stones
                        } else {
                            if (whoseTurn == 1) {
                                whoseTurn = 2;  // change the turn for 2nd player to place stone
                            } else if (whoseTurn == 2) {                            
                                whoseTurn = 1;  // change the turn for 1st player to place stone
                            }
                            /* change status to inform players when their turns come */
                            if (whoseTurn == 1) {
                                status.setText("1st player's turn");
                            } else if (whoseTurn == 2) {
                                status.setText("2nd player's turn");
                            }
                        }                    
                    } 
                } else if (gameModeChoice == 2) {  // generate human vs computer
                    if (stone == 0 && whoseTurn != 0) {
                        setStone(whoseTurn);
                        if (isFinished((byte) 1)) {  // check if player wins
                            JOptionPane.showMessageDialog(null, player1Name + " has won the game!", 
                                    "Game Over", JOptionPane.INFORMATION_MESSAGE);  // display message if a player wins

                            status.setText("1st player wins");  // set status
                            whoseTurn = 0; 
                        } else if (isTied()) {  
                            JOptionPane.showMessageDialog(null, "The game board is full. No one wins", 
                                    "Game Over", JOptionPane.INFORMATION_MESSAGE);  // display message when the game is tied

                            status.setText("Tied");
                            whoseTurn = 0;
                        } else {
                            status.setText("1st player's turn");
                            whoseTurn = 2;
                            int[] coordinates = AIMoves();
                            slot[coordinates[0]][coordinates[1]].setStone(whoseTurn);  // computer places stones
                            if (isFinished((byte) 2)) {  // check if computer wins
                                JOptionPane.showMessageDialog(null, player2Name + " has won the game!", 
                                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
                                
                                status.setText("2nd player wins");
                                whoseTurn = 0;
                            }
                            whoseTurn = 1;                                                       
                        }                    
                    } 
                }                                                                                 
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e){}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        }
    }
}


