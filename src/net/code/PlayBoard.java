package net.code;

import javax.swing.*;
public class PlayBoard {
    private final JLabel[][] board;
    private static int winPositions;
    public PlayBoard(int rows, int columns){
        board = new JLabel[rows][columns];
    }
    public boolean checkFreeSpace() {
        boolean noFreeSpace = true;
        for (JLabel[] labels : board) {
            for (JLabel label : labels) {
                if (label.getName().equals("")) {
                    noFreeSpace = false;
                    break;
                }
            }
        }
        return noFreeSpace;
    }
    public static void setWinPositions(int positions){
        winPositions = positions;
    }
    public void restartGame(){
        for (JLabel[] labels : board) {
            for (JLabel label : labels) {
                label.setName("");
                label.setIcon(new ImageIcon());
            }
        }
    }
    public void placeToken(TokenPosition position, String Token) {
        ImageIcon cellIcon;
        if(Token.equals("X")){
            cellIcon = new ImageIcon("icons/x.png");
            board[position.getRow()][position.getColumn()].setIcon(new ImageIcon(cellIcon.getImage()));
        }
        else{
            cellIcon = new ImageIcon("icons/o.png");
            board[position.getRow()][position.getColumn()].setIcon(new ImageIcon(cellIcon.getImage()));
        }
        board[position.getRow()][position.getColumn()].setName(Token);
    }
    public boolean checkWinner(TokenPosition position) {
        return checkHorizontalWin(position) || checkVerticalWin(position) || checkDiagonalWin(position);
    }

    public boolean checkTie(TokenPosition position) {
        return !checkWinner(position) && checkFreeSpace();
    }

public boolean checkHorizontalWin(TokenPosition position) {
        boolean horizontalWin = false;
        String Token = tokenAtPosition(position);
        int columnIndex = position.getColumn();
        int matchCount = 1;
        while(columnIndex > 0 && columnIndex < board[0].length){
            columnIndex--;
            if(board[position.getRow()][columnIndex].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }

        columnIndex = position.getColumn();
        while(columnIndex >= 0 && columnIndex < board[0].length - 1){
            columnIndex++;
            if(board[position.getRow()][columnIndex].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }

        if(matchCount >= winPositions){
            horizontalWin = true;
        }
        return horizontalWin;

    }
    public boolean checkVerticalWin(TokenPosition position) {
        boolean verticalWin = false;
        String Token = tokenAtPosition(position);
        int rowIndex = position.getRow();
        int matchCount = 1;
        while(rowIndex > 0 && rowIndex < board.length){
            rowIndex--;
            if(board[rowIndex][position.getColumn()].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }

        rowIndex = position.getRow();
        while(rowIndex >= 0 && rowIndex < board.length - 1){
            rowIndex++;
            if(board[rowIndex][position.getColumn()].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }

        if(matchCount >= winPositions){
            verticalWin = true;
        }
        return verticalWin;
    }
    public boolean checkDiagonalWin(TokenPosition position) {
        boolean diagonalWin = false;
        String Token = tokenAtPosition(position);
        int rowIndex = position.getRow();
        int columnIndex = position.getColumn();
        int matchCount = 1;
        while(rowIndex > 0 && rowIndex < board.length && columnIndex > 0 && columnIndex < board[0].length){
            rowIndex--;
            columnIndex--;
            if(board[rowIndex][columnIndex].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }
        rowIndex = position.getRow();
        columnIndex = position.getColumn();

        while(rowIndex >= 0 && rowIndex < board.length - 1 && columnIndex >= 0 && columnIndex < board[0].length - 1){
            rowIndex++;
            columnIndex++;
            if(board[rowIndex][columnIndex].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }
        if(matchCount >= winPositions){
            return true;
        }
        matchCount = 1;
        rowIndex = position.getRow();
        columnIndex = position.getColumn();

        while(rowIndex >= 0 && rowIndex < board.length - 1 && columnIndex > 0 && columnIndex < board[0].length){
            rowIndex++;
            columnIndex--;
            if(board[rowIndex][columnIndex].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }

        rowIndex = position.getRow();
        columnIndex = position.getColumn();

        while(rowIndex > 0 && rowIndex < board.length && columnIndex >= 0 && columnIndex < board[0].length -1){
            rowIndex--;
            columnIndex++;
            if(board[rowIndex][columnIndex].getName().equals(Token)){
                matchCount++;
            }else{
                break;
            }
        }

        if(matchCount >= winPositions){
            diagonalWin = true;
        }
        return diagonalWin;
    }
    public String tokenAtPosition(TokenPosition position) {
        return board[position.getRow()][position.getColumn()].getName();
    }
    public boolean isTokenAtPos(TokenPosition position, String Token) {
        return !board[position.getRow()][position.getColumn()].getName().equals("");
    }
    public JLabel[][] getBoard(){
        return this.board;
    }
}
