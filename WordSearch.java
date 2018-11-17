public class WordSearch {
  private char[][]data;

    /**Initialize the grid to the size specified

     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      clear();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int r = 0; r < data.length; r++){
        for (int c = 0; c < data[r].length; c++){
          data[r][c] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String result = "";
      for (int r = 0; r < data.length; r++){
        for (int c = 0; c < data[r].length; c++){
          result += data[r][c] + " ";
        }
        result += "\n";
      }
      return result;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned

     * and the board is NOT modified.

     */
    private boolean insert(char insert, int row, int col){
        if (data[row][col] == insert || data[row][col] == '_'){
          data[row][col] = insert;
          return true;
        }
        return false;
    }
    /*
    public boolean addWordHorizontal(String word,int row, int col){
        check = true;
        for (int i = 0; i < word.length() && check; i++){
          insert(word.charAt(i), row, col + i);
        }
    }
    */
public boolean addWordHorizontal(String word, int row, int col){
  for (int i = 0; i < word.length(); i++){
    if (!(data[row][col] == '_' || data[row][col] == word.charAt(i))){return false;}
  }
  for (int i = 0; i < word.length(); i++){
    insert(word.charAt(i), row, col + i);
  }
  return true;
}
   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.

     */
  public boolean addWordVertical(String word,int row, int col){
        for (int i = 0; i < word.length(); i++){
          if (!(data[row][col] == '_' || data[row][col] == word.charAt(i))){return false;}
        }
        for (int i = 0; i < word.length(); i++){
          insert(word.charAt(i), row + i, col);
        }
        return true;
      }

}
