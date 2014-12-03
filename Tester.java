public class Tester {
    public static void main (String[] args){
           
            // Tests the Counter and BoardTile classes
           
            BoardTile bt = new BoardTile();
            Counter ct = new Counter();
            bt.createTicTacBoard();
            bt.createSnakeLadderBoard();
            ct.gameWon();
            ct.displayLadder();
            ct.displaySnake();
           
    }

}
