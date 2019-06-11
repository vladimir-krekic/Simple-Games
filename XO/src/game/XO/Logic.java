package game.XO;

import java.util.List;

/**
 * @author Vladimir Krekic
 */

class Logic {

    /**
     * Overloaded wrapper method for calling
     * horizontal and vertical check for winner
     * @param size - integer: represents max number of list's element that needs to be reached
     * @param step - integer: represents steps for first index of actual check
     * @param step2 - integer: represents steps in actual check for horizontalVertical()
     * @param size2 - integer: represents max number of list's element that needs to be reached in horizontalVertical()
     */
    public static void check(int size, int step, int step2, int size2) {
        for(int i = 0; i < size; i = i + step) {
            if(horizontalVertical(Main.list, i, step2, size2)) {
                AlertBox.getAlertBox(message());
            }
        }
    }

    /**
     * Overloaded wrapper method for calling diagonal check for winner
     * @param start - integer: represents first index for actual check
     * @param step - integer: represents steps in actual check for diagonal()
     * @param size - integer: represents max number of list's element that needs to be reached in diagonal()
     */
    public static void check(int start, int step, int size) {
        if(diagonal(Main.list, start, step, size)) {
            AlertBox.getAlertBox(message());
        }
    }

    /**
     * Method that checks for column or row filled with same text
     * @param list - List<Tile> list of filled tiles
     * @param start - integer: represents first index for actual check
     * @param step - integer: represents steps in actual check
     * @param size - integer: represents max number of list's element that needs to be reached
     * @return boolean
     */
    private static boolean horizontalVertical(List<Tile> list, int start, int step, int size){
        String s;
        if(list.get(start).getText().getText().equals("X") || list.get(start).getText().getText().equals("O")) {
            s = list.get(start).getText().getText();
            if(size == 0) size = start + 3;
            for (int i = start; i < size; i = i + step) {
                if (!list.get(i).getText().getText().equals(s)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method that checks for diagonal filled with same text
     * @param list - List<Tile> list of tiles
     * @param start - integer: represents first index for actual check
     * @param step - integer: represents steps in actual check
     * @param size - integer: represents max number of list's element that needs to be reached
     * @return boolean
     */
    private static boolean diagonal(List<Tile> list, int start, int step, int size){
        String s;
        if(list.get(start).getText().getText().equals("X") || list.get(start).getText().getText().equals("O")) {
            s = list.get(start).getText().getText();
            for (int i = start; i < size; i = i + step) {
                if (!list.get(i).getText().getText().equals(s)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Counts number of filled tiles so onClick method
     * can alternate between "X" and "O".
     * Be aware that counter is always one step behind
     * because it is generated before text is being set
     * @param list<Tiles> - List<Tile> list of tiles
     * @return int
     */
    public static int count (List<Tile> list) {
        return (int)list.stream()
                .map(Tile::getText)
                .filter(t -> !t.getText().equals(""))
                .count();
    }

    /**
     * Creates message based on the result of the game
     * @return String
     */
    private static String message (){
        String message;
        if(Logic.count(Main.list) % 2 == 0){
            message = "Player O wins";
        }else {
            message = "Player X wins";
        }
        return  message;
    }

}
