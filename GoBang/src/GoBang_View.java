


import java.awt.*;  import javax.swing.*;  import java.awt.event.*;
import javax.swing.border.*;
public class GoBang_View extends JFrame {   
    private GoBang_Model gbm;
    
    private ImageIcon blue = new ImageIcon("images/bluedia.jpg");
    private ImageIcon black = new ImageIcon("images/blackdia.jpg");
    private ImageIcon green = new ImageIcon("images/greendia.jpg");
    private ImageIcon purple = new ImageIcon("images/purpledia.jpg");    
    
    private JPanel mainMenu = new JPanel();  // display main menu
    private JRadioButton hh = new JRadioButton("Human vs Human");
    private JRadioButton hc = new JRadioButton("Human vs Computer");
    private JButton ok = new JButton("Continue");
    private JButton exit = new JButton("Exit");
    
    private JPanel players = new JPanel();  // panel for players to type names and choose stones
    private JLabel p1 = new JLabel("Player 1's name:");
    private JLabel p2 = new JLabel("Player 2's name:"); 
    private JButton blueStoneP1 = new JButton(blue);
    private JButton blackStoneP1 = new JButton(black);
    private JButton blueStoneP2 = new JButton(blue);
    private JButton blackStoneP2 = new JButton(black);
    private JButton greenStoneP1 = new JButton(green);    
    private JButton greenStoneP2 = new JButton(green);
    private JButton purpleStoneP1 = new JButton(purple);
    private JButton purpleStoneP2 = new JButton(purple);
    private JButton cont = new JButton("Let's play");
    
    private JPanel board = new JPanel();  // display the game board
    private JPanel stoneStt = new JPanel();
    private JLabel sttP1 = new JLabel();
    private JLabel sttP2 = new JLabel();
    private JButton rest = new JButton("Restart");
    private JButton rule = new JButton("Go Bang rules"); 
    private JTextField t1 = new JTextField(10);
    private JTextField t2 = new JTextField(10);
    private JLabel p1turn = new JLabel();
    private JLabel p2turn = new JLabel();
    private JButton esc = new JButton("Quit the game");   
            
    public GoBang_View(GoBang_Model gbm) {
        this.gbm = gbm;

        mainMenu.setLayout(new GridLayout(3,1,5,20));
        mainMenu.setBorder(new LineBorder(new Color(236, 184, 39), 25)); 
        mainMenu.setBackground(new Color(184, 134, 11));
        
        JPanel name = new JPanel();
        name.setLayout(new FlowLayout(FlowLayout.CENTER,10,30));
        name.setBackground(new Color(184, 134, 11));
        Font f1 = new Font("Broadway", Font.BOLD, 30);
        JLabel l1 = new JLabel("- - -  Go Bang  - - -");
        l1.setFont(f1);
        l1.setForeground(Color.WHITE);
        name.add(l1);
        mainMenu.add(name);
        /**************/
        JPanel gameMode = new JPanel();        
        gameMode.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
        gameMode.setBackground(new Color(184, 134, 11));
                
        JLabel modeLabel = new JLabel("Game Mode");
        modeLabel.setFont(new Font("Arial",Font.PLAIN, 16));
        modeLabel.setForeground(new Color(239,203,131));
        gameMode.add(modeLabel);
        Font f2 = new Font("Arial", Font.BOLD, 18);
        hh.setBackground(new Color(184, 134, 11));
        hh.setFont(f2);  hh.setForeground(Color.YELLOW);
        hc.setBackground(new Color(184, 134, 11));
        hc.setFont(f2);  hc.setForeground(Color.YELLOW);        
        ButtonGroup group1 = new ButtonGroup();
        group1.add(hh);
        group1.add(hc); 
        gameMode.add(hh);
        gameMode.add(hc);
        mainMenu.add(gameMode);
        /**************/
        JPanel action = new JPanel();
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        action.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
        action.setBackground(new Color(184, 134, 11));
        ok.setBackground(Color.BLACK);
        ok.setForeground(Color.WHITE);
        ok.setFont(buttonFont);
        action.add(ok);
        exit.setBackground(Color.RED);
        exit.setForeground(Color.WHITE);
        exit.setFont(buttonFont);
        action.add(exit);
        mainMenu.add(action);
        
        /*-------------------------------*/
        
        players.setBorder(new LineBorder(new Color(236, 184, 39), 25)); 
        players.setBackground(new Color(184, 134, 11));
        players.setLayout(new GridLayout(3,1,10,10));
        
        JPanel textField = new JPanel();
        textField.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        textField.setBackground(new Color(184, 134, 11));
        Font font = new Font("Arial", Font.BOLD, 16);
        p1.setFont(font); p1.setForeground(Color.YELLOW); 
        p2.setFont(font); p2.setForeground(Color.YELLOW);
        t1.setText("Player 1");
        textField.add(p1); textField.add(t1);
        textField.add(new JLabel("           "));
        textField.add(p2); textField.add(t2);
        players.add(textField);
        /**************/
        JPanel stone = new JPanel();
        stone.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
        stone.setBackground(new Color(184, 134, 11));
        JLabel player1Label = new JLabel("Player 1's stone");
        player1Label.setFont(new Font("Arial",Font.PLAIN, 16));
        player1Label.setForeground(new Color(239,203,131));
        stone.add(player1Label);
        blueStoneP1.setBackground(Color.WHITE); blackStoneP1.setBackground(Color.WHITE);
        greenStoneP1.setBackground(Color.WHITE); purpleStoneP1.setBackground(Color.WHITE);
        stone.add(blueStoneP1); stone.add(blackStoneP1);
        stone.add(greenStoneP1); stone.add(purpleStoneP1);
        stone.add(new JLabel("           "));
        JLabel player2Label = new JLabel("Player 2's stone");
        player2Label.setFont(new Font("Arial",Font.PLAIN, 16));
        player2Label.setForeground(new Color(239,203,131));        
        stone.add(player2Label);
        blueStoneP2.setBackground(Color.WHITE); blackStoneP2.setBackground(Color.WHITE);
        greenStoneP2.setBackground(Color.WHITE); purpleStoneP2.setBackground(Color.WHITE);
        stone.add(blueStoneP2); stone.add(blackStoneP2);
        stone.add(greenStoneP2); stone.add(purpleStoneP2);
        players.add(stone);
        /**************/
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        p2.setBackground(new Color(184, 134, 11));;
        cont.setBackground(Color.BLACK);
        cont.setForeground(Color.WHITE);
        cont.setFont(buttonFont);
        p2.add(cont);
        players.add(p2);
        
        /*-------------------------------*/
        
        board.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
        board.setBorder(new LineBorder(new Color(236, 184, 39), 25));
        board.setBackground(new Color(184, 134, 11));
        
        Game game = new Game();          
        for (byte i = 0; i < gbm.slot.length; i++) {
            for (byte j = 0; j < gbm.slot[i].length; j++) {
                game.add(gbm.slot[i][j]);                                
            }
        }
        /**************/
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(6,1,65,0));
        menu.setBackground(new Color(184, 134, 11));
        
        JPanel status = new JPanel();
        status.setLayout(new FlowLayout(FlowLayout.CENTER,30,40));
        status.setBackground(new Color(184, 134, 11));
        JLabel sttLabel = new JLabel("Game's status"); 
        sttLabel.setForeground(Color.WHITE);
        status.add(sttLabel);
        status.add(gbm.status);
        menu.add(status);
        
        stoneStt.setLayout(new GridLayout(2,2,10,5));  
        stoneStt.setBackground(new Color(184, 134, 11));
        sttP1.setForeground(Color.WHITE);
        sttP2.setForeground(Color.WHITE);
        menu.add(stoneStt);
        
        JPanel restart = new JPanel();
        restart.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
        restart.setBackground(new Color(184, 134, 11));
        rest.setBackground(Color.DARK_GRAY);
        rest.setForeground(Color.yellow);
        restart.add(rest);
        menu.add(restart);
        
        JPanel gameRule = new JPanel();
        gameRule.setLayout(new FlowLayout(FlowLayout.CENTER));
        gameRule.setBackground(new Color(184, 134, 11));
        rule.setBackground(Color.DARK_GRAY);
        rule.setForeground(Color.yellow);
        gameRule.add(rule);
        menu.add(gameRule);
        
        JPanel turn = new JPanel();
        turn.setLayout(new GridLayout(3,2,10,5));
        turn.setBackground(new Color(184, 134, 11));        
        JLabel p3turn = new JLabel("Total stones");
        p1turn.setForeground(Color.WHITE);
        p2turn.setForeground(Color.WHITE);
        p3turn.setForeground(Color.WHITE);
        gbm.p1NumStone.setEditable(false); gbm.p2NumStone.setEditable(false); gbm.total.setEditable(false);
        gbm.p1NumStone.setText("0"); gbm.p2NumStone.setText("0"); gbm.total.setText("0");
        turn.add(p1turn); turn.add(gbm.p1NumStone);
        turn.add(p2turn); turn.add(gbm.p2NumStone);
        turn.add(p3turn); turn.add(gbm.total);
        menu.add(turn);
                
        JPanel exitButton = new JPanel();
        exitButton.setLayout(new FlowLayout(FlowLayout.CENTER,0,40));
        exitButton.setBackground(new Color(184, 134, 11));
        esc.setBackground(Color.DARK_GRAY);
        esc.setForeground(Color.yellow);
        exitButton.add(esc);        
        menu.add(exitButton);
        
        board.add(game);
        board.add(menu);
        
        /*--------------------*/
                
        add(mainMenu);
        setSize(800, 400);
        setTitle("GoBang - Main Menu");
        ImageIcon icon = new ImageIcon("images/logo.gif");
        Image img = icon.getImage();
        setIconImage(img);
        setResizable(false);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);          
    }
    
    public void setPlayer1Stone() {  // set stones for player 1
        if (gbm.getP1Stone().equals("blue")) {
            stoneStt.add(new JLabel(blue));
        } else if (gbm.getP1Stone().equals("black")) {
            stoneStt.add(new JLabel(black));
        } else if (gbm.getP1Stone().equals("green")) {
            stoneStt.add(new JLabel(green));
        } else {
            stoneStt.add(new JLabel(purple));
        } 
        stoneStt.add(sttP1);
    }
    
    public void setPlayer2Stone() {  // set stones for player 2
        if (gbm.getP2Stone().equals("blue")) {
            stoneStt.add(new JLabel(blue));
        } else if (gbm.getP2Stone().equals("black")) {
            stoneStt.add(new JLabel(black));
        } else if (gbm.getP2Stone().equals("green")) {
            stoneStt.add(new JLabel(green));
        } else {
            stoneStt.add(new JLabel(purple));
        } 
        stoneStt.add(sttP2);
    }
    
    public JPanel getStoneStt() {
        return stoneStt;
    }
    
    public void setSttP1(String s) {
        sttP1.setText(s);
    }
    
    public void setSttP2(String s) {
        sttP2.setText(s);
    }
    
    public JButton getBlueStoneP1() {
        return blueStoneP1;
    }
    
    public JButton getBlueStoneP2() {
        return blueStoneP2;
    }
    
    public JButton getBlackStoneP1() {
        return blackStoneP1;
    }
    
    public JButton getBlackStoneP2() {
        return blackStoneP2;
    }
    
    public JButton getGreenStoneP1() {
        return greenStoneP1;
    }
    
    public JButton getGreenStoneP2() {
        return greenStoneP2;
    }
    
    public JButton getPurpleStoneP1() {
        return purpleStoneP1;
    }
    
    public JButton getPurpleStoneP2() {
        return purpleStoneP2;
    }

    public JTextField getT1() {
        return t1;
    }
    
    public JTextField getT2() {
        return t2;
    }
    
    public JLabel getP1Turn() {
        return p1turn;
    }
    
    public JLabel getP2Turn() {
        return p2turn;
    }

    public JPanel getMainMenu() {
        return mainMenu;
    }

    public JPanel getPlayers() {
        return players;
    }

    public JPanel getBoard() {
        return board;
    }

    /*--------------------*/

    public void addActionForGameMode(ActionListener ae) {
        hh.addActionListener(ae);
        hc.addActionListener(ae);
    }

    public void addActionForOKMainMenu(ActionListener ae) {
        ok.addActionListener(ae);
    }
    
    public void addActionForEsc(ActionListener ae) {
        exit.addActionListener(ae);
    }
    
    /*--------------------*/
    
    public void addActionForStonesP1(ActionListener listen) {
        blueStoneP1.addActionListener(listen);
        blackStoneP1.addActionListener(listen);
        greenStoneP1.addActionListener(listen);
        purpleStoneP1.addActionListener(listen);
    }
    
    public void addActionForStonesP2(ActionListener al) {
        blueStoneP2.addActionListener(al);
        blackStoneP2.addActionListener(al);
        greenStoneP2.addActionListener(al);
        purpleStoneP2.addActionListener(al);
    }
        
    public void addActionForOKGamer(ActionListener ae) {         
        cont.addActionListener(ae);
    }
    
    /*--------------------*/
    
    public void addActionForRule(ActionListener al) {
        rule.addActionListener(al);
    }
    
    public void addActionForRestart(ActionListener al) {
        rest.addActionListener(al);
    }
    
    public void addActionForQuit(ActionListener al) {
        esc.addActionListener(al);
    }
    
    /*--------------------*/

    public void setWinner() {  // display the winner
        gbm.setPlayer1Name(t1.getText());
        gbm.setPlayer2Name(t2.getText());
    }
    
    public void setAllStoneVisible() {  // set all button for choosing stones visible
        blueStoneP1.setVisible(true);
        blueStoneP2.setVisible(true);
        blackStoneP1.setVisible(true);
        blackStoneP2.setVisible(true);
        greenStoneP1.setVisible(true);
        greenStoneP2.setVisible(true);
        purpleStoneP1.setVisible(true);
        purpleStoneP2.setVisible(true);
    }

    /*--------------------*/
                              
    class Game extends JPanel {  /* panel for the game board */
        private int x, y;
        public Game() {
            setLayout(new GridLayout(13,13,20,20));  
            setBackground(Color.white);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            x = 12;
            for (byte i = 0; i < 13; i++) {
                g.drawLine(x, 0, x, 560);
                x += 45;
            }            
            
            y = 13;
            for (byte i = 0; i < 13; i++) {
                g.drawLine(0, y, 560, y);
                y += 46;
            }
        }        
    }            
} 
