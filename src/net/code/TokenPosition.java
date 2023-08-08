package net.code;

public class TokenPosition {
    private int row;
    private int Column;
    public TokenPosition(int Row, int Column){
        this.row = Row;
        this.Column = Column;
    }
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.Column;
    }
    @Override
    public boolean equals(Object object){
        if(object == this) return true;
        if(!(object instanceof TokenPosition)) return false;
        TokenPosition tokenPosition = (TokenPosition) object;
        return Double.compare(row, tokenPosition.row) == 0 && Double.compare(Column, tokenPosition.Column) == 0;
    }
}
