package net.code;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class TicTacToe {
    private static final int NORMAL_SIZE = 3;
    private static final int INTERMEDIATE_SIZE = 5;
    private static final int HARD_SIZE_ROW = 5;
    private static final int HARD_SIZE_COLUMN = 8;
    private static final int NORMAL_LEVEL_WIN_POSITIONS = 3;
    private static final int INTERMEDIATE_LEVEL_WIN_POSITIONS = 4;
    private static final int HARD_LEVEL_WIN_POSITIONS = 5;
    public static int MAX_ROW;
    public static int MAX_COLUMN;
    public static boolean firstTime = true;
    public static int row,column;
    public static String playerOneToken = "X";
    public static String playerTwoToken = "O";
    public static boolean firstPlayerRound = true;
    public static boolean secondPlayerRound = false;
    public static String welcomeText = "Welcome To Tic Tac Toe Game";
    public static JFrame loadingFrame;
    public static PlayBoard playBoard;
    public static ArrayList<JButton> actionButtons = new ArrayList<>();
    public static void main(String[] args){
        final ImageIcon gameFavicon = new ImageIcon("icons/logo.png");
        selectDifficulty();
        showLoadingScreen();

        // Simulate a 5-second loading process
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hideLoadingScreen();
        playBoard = new PlayBoard(MAX_ROW,MAX_COLUMN);
        final JLabel [][] board = playBoard.getBoard();
        final int FRAME_HEIGHT = 600, FRAME_WIDTH = 600;
        final int mainPanelsWidth = 560;
        final int mainPanelsVerticalSpacing = 15;
        final int mainPanelsMargin = 12;

        /* Tic Tac Toe Game Frame setting -- */
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFFFFF));
        frame.setIconImage(gameFavicon.getImage());
        frame.setResizable(false);

        /* Screen to display alternating players */
        final int screenPanelHeight = 80;
        Color screenPanelBackgroundColor = new Color(0xE40C2B);
        JPanel screenPanel = new JPanel();
        screenPanel.setOpaque(true);
        screenPanel.setLayout(null);
        screenPanel.setBounds(0,0,FRAME_WIDTH,screenPanelHeight);
        screenPanel.setBackground(screenPanelBackgroundColor);

        int screenPanelTextFontSize = 18;
        String fontFamily = "Baskerville";
        Color screenTextColor = new Color(0xFFFFFF);
        Font screenPanelTextFont = new Font(fontFamily,Font.BOLD,screenPanelTextFontSize);
        JLabel screenPanelTextLabel = new JLabel();
        screenPanelTextLabel.setText(welcomeText.toUpperCase());
        screenPanelTextLabel.setForeground(screenTextColor);
        screenPanelTextLabel.setBounds(0,0,screenPanel.getWidth(),screenPanelHeight);
        screenPanelTextLabel.setOpaque(false);
        screenPanelTextLabel.setVerticalAlignment(JLabel.CENTER);
        screenPanelTextLabel.setHorizontalAlignment(JLabel.CENTER);
        screenPanelTextLabel.setFont(screenPanelTextFont);
        screenPanel.add(screenPanelTextLabel);
        frame.add(screenPanel);
        //End of Screen Panel

        /* Action Buttons Panel */
        int labelWidthDivider = 5;
        int actionButtonsPanelHeight = 100;
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new GridLayout(3,1,2,2));
        actionButtonsPanel.setOpaque(false);
        actionButtonsPanel.setBounds(mainPanelsMargin,screenPanelHeight + mainPanelsVerticalSpacing,mainPanelsWidth,actionButtonsPanelHeight);

        /* Row Buttons Panel */
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(null);
        rowPanel.setOpaque(false);
        actionButtonsPanel.add(rowPanel);

        JLabel rowButtonsLabel = new JLabel();
        int labelFontSize = 14;
        String labelFontFamily = "Consolas";
        Font labelTextFont = new Font(labelFontFamily,Font.PLAIN,labelFontSize);
        int rowLabelWidth = mainPanelsWidth/labelWidthDivider;
        int rowLabelHeight = 32;
        rowButtonsLabel.setBounds(0,0,rowLabelWidth,rowLabelHeight);
        rowButtonsLabel.setOpaque(false);
        rowButtonsLabel.setText("ROWS [ X ]");
        rowButtonsLabel.setFont(labelTextFont);
        rowButtonsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        rowButtonsLabel.setVerticalAlignment(SwingConstants.CENTER);
        rowPanel.add(rowButtonsLabel);

        int rowButtonsPanelWidth = mainPanelsWidth - rowLabelWidth;
        JPanel rowButtonsPanel = new JPanel();
        rowButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        rowButtonsPanel.setOpaque(false);
        rowButtonsPanel.setBounds(rowButtonsLabel.getWidth()+mainPanelsMargin,0,rowButtonsPanelWidth, rowLabelHeight);
        rowPanel.add(rowButtonsPanel);

        Color rowButtonsBackgroundColor = new Color(0X0B4141);
        for(int i = 0; i < MAX_ROW; i++){
            JButton button = new JButton(String.valueOf(i));
            button.setHorizontalAlignment(JButton.CENTER);
            button.setVerticalAlignment(JButton.CENTER);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(true);
            button.setBackground(rowButtonsBackgroundColor);
            button.setForeground(Color.WHITE);
            rowButtonsPanel.add(button);
            actionButtons.add(button);
        }

        /* Column Buttons Panel */
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(null);
        columnPanel.setOpaque(false);
        actionButtonsPanel.add(columnPanel);

        JLabel columnButtonsLabel = new JLabel();
        columnButtonsLabel.setBounds(0,0,rowLabelWidth,rowLabelHeight);
        columnButtonsLabel.setOpaque(false);
        columnButtonsLabel.setText("COLUMNS [ Y ]");
        columnButtonsLabel.setFont(labelTextFont);
        columnButtonsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        columnButtonsLabel.setVerticalAlignment(SwingConstants.CENTER);
        columnPanel.add(columnButtonsLabel);

        JPanel columnButtonsPanel = new JPanel();
        columnButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        columnButtonsPanel.setOpaque(false);
        columnButtonsPanel.setBounds(rowButtonsLabel.getWidth()+mainPanelsMargin,0,rowButtonsPanelWidth, rowLabelHeight);
        columnPanel.add(columnButtonsPanel);

        Color columnButtonsBackgroundColor = new Color(0X1181B2);
        for(int i = 0; i < MAX_COLUMN; i++){
            JButton button = new JButton(String.valueOf(i));
            button.setHorizontalAlignment(JButton.CENTER);
            button.setVerticalAlignment(JButton.CENTER);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(true);
            button.setBackground(columnButtonsBackgroundColor);
            button.setForeground(Color.WHITE);
            columnButtonsPanel.add(button);
            actionButtons.add(button);
        }

        // Selected Position Status Panel
        JPanel selectedCellPanel = new JPanel();
        selectedCellPanel.setLayout(null);
        selectedCellPanel.setOpaque(false);
        actionButtonsPanel.add(selectedCellPanel);

        JLabel selectedCellLabel = new JLabel();
        selectedCellLabel.setBounds(0,0,rowLabelWidth,rowLabelHeight);
        selectedCellLabel.setOpaque(false);
        selectedCellLabel.setText("SELECTED CELL");
        selectedCellLabel.setFont(labelTextFont);
        selectedCellLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectedCellLabel.setVerticalAlignment(SwingConstants.CENTER);
        selectedCellPanel.add(selectedCellLabel);

        JPanel cellsPanel = new JPanel();
        cellsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        cellsPanel.setOpaque(false);
        cellsPanel.setBounds(rowButtonsLabel.getWidth()+mainPanelsMargin,0,rowButtonsPanelWidth, rowLabelHeight);
        selectedCellPanel.add(cellsPanel);

        JLabel rowCell = new JLabel();
        rowCell.setText("[ ]");
        rowCell.setFont(labelTextFont);
        cellsPanel.add(rowCell);

        JLabel columnCell = new JLabel();
        columnCell.setText("[ ]");
        columnCell.setFont(labelTextFont);
        cellsPanel.add(columnCell);

        frame.add(actionButtonsPanel);
        /* End of action buttons panel */

        //Game Board Panel
        JPanel gameBoardPanel = new JPanel();
        int gameBoardHeight = 300;
        gameBoardPanel.setLayout(null);
        gameBoardPanel.setOpaque(true);
        gameBoardPanel.setBounds(mainPanelsMargin,screenPanelHeight+ actionButtonsPanelHeight + mainPanelsVerticalSpacing*2,mainPanelsWidth,gameBoardHeight);
        gameBoardPanel.setBackground(new Color(0x1A2238));

        JPanel columnCoordinatesPanel = new JPanel();
        int columnCoordinatePanelHeight = 40;
        columnCoordinatesPanel.setBounds(0,0,mainPanelsWidth,columnCoordinatePanelHeight);
        columnCoordinatesPanel.setOpaque(true);
        columnCoordinatesPanel.setLayout(new GridLayout(1,MAX_COLUMN,0,0));
        columnCoordinatesPanel.setBackground(Color.WHITE);

        JLabel label;
        for(int i = 0; i <= MAX_COLUMN; i++){
            label = new JLabel();
            String text;
            text = (i == 0) ? "" : String.valueOf(i - 1);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font("Comic Sans",Font.BOLD,25));
            label.setBackground(new Color(0xffffff));
            label.setForeground(new Color(0x023b59));
            label.setOpaque(true);
            label.setText(text);
            columnCoordinatesPanel.add(label);
        }
        gameBoardPanel.add(columnCoordinatesPanel);

        /* Retrieve grid with for each grid item */
        GridLayout layout = (GridLayout) columnCoordinatesPanel.getLayout();
        int gridWidth = columnCoordinatesPanel.getWidth()/layout.getColumns();
        int rowCoordinatePanelWidth = gridWidth - 10;
        JPanel rowCoordinatesPanel = new JPanel();
        int rowCoordinatePanelHeight = gameBoardHeight - columnCoordinatePanelHeight;
        rowCoordinatesPanel.setBounds(0,40,rowCoordinatePanelWidth,rowCoordinatePanelHeight);
        rowCoordinatesPanel.setOpaque(true);
        rowCoordinatesPanel.setLayout(new GridLayout(MAX_ROW,1,0,0));
        rowCoordinatesPanel.setBackground(Color.WHITE);

        for(int i = 0; i < MAX_ROW; i++){
            String text = String.valueOf(i);
            label = new JLabel();
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font("Comic Sans",Font.BOLD,25));
            label.setBackground(new Color(0xffffff));
            label.setForeground(new Color(0x023b59));
            label.setOpaque(true);
            label.setText(text);
            rowCoordinatesPanel.add(label);
        }
        gameBoardPanel.add(rowCoordinatesPanel);

        /* Game Cell Panel */
        JPanel cellPanel = new JPanel();
        final Color cellBackgroundColor = new Color(0x0b4141);
        int cellPanelWidth = mainPanelsWidth - rowCoordinatePanelWidth;
        int cellPanelHeight = gameBoardHeight - columnCoordinatePanelHeight;
        cellPanel.setBounds(rowCoordinatePanelWidth, columnCoordinatePanelHeight,cellPanelWidth,cellPanelHeight);
        cellPanel.setOpaque(true);
        cellPanel.setBackground(Color.WHITE);
        cellPanel.setLayout(new GridLayout(MAX_ROW,MAX_COLUMN,2,2));

        Font labelFont = new Font("Arial", Font.BOLD, 25);
        Border labelBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);

        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                label = new JLabel();
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(labelFont);
                label.setBorder(labelBorder);
                label.setBackground(cellBackgroundColor);
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
                label.setName("");
                board[i][j] = label;
                label.setText("");
                cellPanel.add(label);
            }
        }

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(null);
        int remainingHeight = frame.getHeight() - (gameBoardHeight+actionButtonsPanelHeight+screenPanelHeight+mainPanelsVerticalSpacing*2);
        messagePanel.setBounds(mainPanelsMargin,gameBoardHeight+actionButtonsPanelHeight+screenPanelHeight+mainPanelsVerticalSpacing + 15,mainPanelsWidth,remainingHeight);
        messagePanel.setOpaque(false);

        JLabel message = new JLabel("");
        message.setBounds(0,0,messagePanel.getWidth(),messagePanel.getHeight()/2);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);
        message.setOpaque(false);
        messagePanel.add(message);

        JButton restart = new JButton("Restart");
        restart.setBounds(message.getWidth(),0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
        restart.setHorizontalAlignment(JLabel.CENTER);
        restart.setVerticalAlignment(JLabel.CENTER);
        restart.setFocusPainted(false);
        restart.setBorderPainted(false);
        restart.setContentAreaFilled(true);
        restart.setBackground(Color.WHITE);
        restart.setVisible(false);
        restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        messagePanel.add(restart);

        frame.add(messagePanel);
        gameBoardPanel.add(cellPanel);
        frame.add(gameBoardPanel);
        frame.setVisible(true);


        ActionListener listener = e -> {
            if(e.getSource() instanceof JButton button){
                if(firstTime){
                    if(rowButtonsPanel.isAncestorOf(button)){
                        row = Integer.parseInt(button.getText());
                        rowCell.setText("[ " +button.getText()+" ]");
                        firstTime = false;
                    }
                }
                else{
                    if(columnButtonsPanel.isAncestorOf(button)){
                        column = Integer.parseInt(button.getText());
                        columnCell.setText("[ " +button.getText()+" ]");
                        TokenPosition position = new TokenPosition(row,column);
                        boolean tokenExist;
                        boolean win = false;
                        if(firstPlayerRound){
                            tokenExist = playBoard.isTokenAtPos(position,playerOneToken);
                            if(tokenExist){
                                message.setText("Token Exist");
                            }else{
                                playBoard.placeToken(position,playerOneToken);
                                win = playBoard.checkWinner(position);
                                if(win) {
                                    message.setText("PLAYER "+playerOneToken+" WINS");
                                    message.setBounds(0,0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setBounds(message.getWidth(),0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setVisible(true);
                                }
                                firstPlayerRound = false;
                                secondPlayerRound = true;
                                if(playBoard.checkTie(position)){
                                    message.setText("DRAW");
                                    message.setBounds(0,0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setBounds(message.getWidth(),0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setVisible(true);
                                }
                                TicTacToe.resetGame(playBoard,position);
                            }

                        }else{
                            tokenExist = playBoard.isTokenAtPos(position,playerTwoToken);
                            if(tokenExist){
                                message.setText("Token Exist");
                            }else{
                                playBoard.placeToken(position,playerTwoToken);
                                win = playBoard.checkWinner(position);
                                if(win) {
                                    message.setText("PLAYER "+playerTwoToken+" WINS");
                                    message.setBounds(0,0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setBounds(message.getWidth(),0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setVisible(true);
                                }
                                firstPlayerRound = true;
                                secondPlayerRound = false;
                                if(playBoard.checkTie(position)){
                                    message.setText("DRAW");
                                    message.setBounds(0,0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setBounds(message.getWidth(),0,messagePanel.getWidth()/2,messagePanel.getHeight()/2);
                                    restart.setVisible(true);
                                }
                                TicTacToe.resetGame(playBoard,position);
                            }

                        }
                        String info;
                        if(firstPlayerRound && !win){
                            if(playBoard.checkFreeSpace()){
                                info = "Game Over";

                            }else{
                                info = "Player " + playerOneToken + " place Token";
                            }

                        }else if(secondPlayerRound && !win){
                            if(playBoard.checkFreeSpace()){
                                info = "Game Over";

                            }else{
                                info = "Player " + playerTwoToken + " place Token";
                            }
                        }
                        else{
                            info = "Game Over";

                        }
                        screenPanelTextLabel.setText(info.toUpperCase());
                        firstTime = true;
                    }
                }

            }
        };

        for(JButton button : actionButtons){
            button.addActionListener(listener);
        }

        ActionListener restartListener = e -> {
            message.setBounds(0,0,messagePanel.getWidth(),messagePanel.getHeight()/2);
            restart.setVisible(false);
            playBoard.restartGame();
            for(JButton button: actionButtons){
                button.setEnabled(true);
            }
            firstTime = true;
            firstPlayerRound = true;
            secondPlayerRound = false;
            rowCell.setText("[ ]");
            columnCell.setText("[ ]");
            message.setText("");
            screenPanelTextLabel.setText(welcomeText.toUpperCase());
        };
        restart.addActionListener(restartListener);

    }
    public static void resetGame(PlayBoard board, TokenPosition position){
        if(board.checkWinner(position) || (!board.checkWinner(position) && board.checkFreeSpace())){
            for(JButton button: actionButtons){
                button.setEnabled(false);
            }
        }
    }


    private static void selectDifficulty() {
        // Create the drop-down menu for difficulty levels
        String[] difficultyLevels = {"Normal", "Intermediate", "Hard"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficultyLevels);

        // Apply custom styling to the drop-down
        Font font = new Font("Arial", Font.PLAIN, 14);
        Color backgroundColor = new Color(240, 240, 240);
        Color foregroundColor = Color.DARK_GRAY;

        difficultyComboBox.setFont(font);
        difficultyComboBox.setBackground(backgroundColor);
        difficultyComboBox.setForeground(foregroundColor);

        // Create the panel for the difficulty selection
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.add(new JLabel("Select the game level:"));
        difficultyPanel.add(difficultyComboBox);

        // Display the panel and get the user's selection
        int result = JOptionPane.showConfirmDialog(null, difficultyPanel, "Game Levels", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Get the selected difficulty level
            String selectedDifficulty = Objects.requireNonNull(difficultyComboBox.getSelectedItem()).toString();

            // Set the maximum row and column based on the selected difficulty level
            switch (selectedDifficulty) {
                case "Normal" -> {
                    MAX_ROW = NORMAL_SIZE;
                    MAX_COLUMN = NORMAL_SIZE;
                    PlayBoard.setWinPositions(NORMAL_LEVEL_WIN_POSITIONS);
                }
                case "Intermediate" -> {
                    MAX_ROW = INTERMEDIATE_SIZE;
                    MAX_COLUMN = INTERMEDIATE_SIZE;
                    PlayBoard.setWinPositions(INTERMEDIATE_LEVEL_WIN_POSITIONS);
                }
                case "Hard" -> {
                    MAX_ROW = HARD_SIZE_ROW;
                    MAX_COLUMN = HARD_SIZE_COLUMN;
                    PlayBoard.setWinPositions(HARD_LEVEL_WIN_POSITIONS);
                }
                default -> {
                    // Invalid input, display an error message
                    JOptionPane.showMessageDialog(null, "Invalid difficulty level selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    selectDifficulty();
                    return; // Terminate the method after recursive call
                }
            }

            if (MAX_ROW == 0 || MAX_COLUMN == 0) {
                // Invalid input, display an error message
                JOptionPane.showMessageDialog(null, "Invalid difficulty level selected.", "Error", JOptionPane.ERROR_MESSAGE);
                selectDifficulty();
                return; // Terminate the method after recursive call
            }
        } else {
            // User clicked "Cancel" or closed the dialog, terminate the game
            System.exit(0);
        }
    }


    private static void showLoadingScreen() {
        loadingFrame = new JFrame("Game Loading");
        loadingFrame.setSize(300, 120); // Increase the height of the loading frame
        loadingFrame.setUndecorated(true); // Disable the window decorations
        loadingFrame.setResizable(false); // Disable resizing of the window

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setForeground(new Color(59, 89, 182));

        JPanel progressBarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add top margin
        progressBarPanel.add(progressBar);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loadingLabel.setForeground(new Color(59, 89, 182));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));
        panel.add(Box.createVerticalGlue());
        panel.add(progressBarPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(loadingLabel);
        panel.add(Box.createVerticalGlue());

        loadingFrame.getContentPane().setBackground(new Color(240, 240, 240));
        loadingFrame.add(panel);
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingFrame.setVisible(true);
    }



    public static void hideLoadingScreen(){
        loadingFrame.setVisible(false);
    }

}
