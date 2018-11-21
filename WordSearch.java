import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class WordSearch {
    private char[][]data;
  //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String> wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String> wordsAdded;

    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      clear();
      seed = (int)(Math.random() * 777777);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
    }

    public WordSearch( int rows, int cols, String fileName) {
      data = new char[rows][cols];
      clear();
      //randgen = new Random();
      seed = (int)(Math.random() * 777777);
      randgen = new Random(seed);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      try{findWords(fileName);}
      catch (FileNotFoundException e){System.out.println("File not found: " + fileName);}
      //addAllWords();

    //Choose a randSeed using the clock random
}
    public WordSearch( int rows, int cols, String fileName, int randSeed){
      data = new char[rows][cols];
      clear();
      seed = randSeed;
      randgen = new Random(seed);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      try{findWords(fileName);}
      catch (FileNotFoundException e){System.out.println("File not found: " + fileName);}
      //addAllWords();
    //Use the random seed specified.
}
    private void findWords(String fileName) throws FileNotFoundException{
      File f = new File(fileName);
      Scanner read = new Scanner(f);
      while (read.hasNext()){
        String line = read.nextLine();
        //System.out.println(line);
        //System.out.println(wordsToAdd);
        wordsToAdd.add(line);
      }
    }
    /*
    public ArrayList<String> getWordstoAdd(){//for testing purposes
      return wordsToAdd;
    }*/

    private void clear(){
      for (int r = 0; r < data.length; r++){
        for (int c = 0; c < data[r].length; c++){
          data[r][c] = '_';
        }
      }
    }
    private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
      if (word.length() > rowSize() - r){return false;}
      for (int i = 0; i < word.length(); i++){
        if (data[r][c + i] != '_' && data[r][c + i] != word.charAt(i)) {return false;}
      }
      for (int pos = 0; pos < word.length(); pos++, r += rowIncrement, c += colIncrement){
        insert(word.charAt(pos), r, c);
      }
      return true;
    }

    public String toString(){
      String result = "";
      for (int r = 0; r < rowSize(); r++){
        result += "|";
        for (int c = 0; c < columnSize(); c++){
          result += data[r][c] + " ";
        }
        result += "|\n";
      }
      result += "word: " + wordsAdded + "(" + "seed: " + seed + ")";
      return result;
    }


    public int rowSize(){//to clarify data.length as rowsize
      return data.length;
    }
    public int columnSize(){//to clarify data.length[0] as columnsize
      return data[0].length;
    }

    private boolean insert(char insert, int r, int c){
        if (data[r][c] == insert || data[r][c] == '_'){
          data[r][c] = insert;
          return true;
        }
        return false;
    }

public boolean addWordHorizontal(String word, int r, int c){
  if (word.length() > rowSize() - r){return false;}
  for (int i = 0; i < word.length(); i++){
    if (data[r][c + i] != '_' && data[r][c + i] != word.charAt(i)) {return false;}
  }
  for (int i = 0; i < word.length(); i++){
    insert(word.charAt(i), r, c + i);
  }
  return true;
}
   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param r is the vertical locaiton of where you want the word to start.
     *@param c is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.

     */
  public boolean addWordVertical(String word,int r, int c){
    if (word.length() > columnSize() - c){return false;}
        for (int i = 0; i < word.length(); i++){
          if (data[r + i][c] != '_' && data[r + i][c] != word.charAt(i)){return false;}
        }
        for (int i = 0; i < word.length(); i++){
          insert(word.charAt(i), r + i, c);
        }
        return true;
      }
    public boolean addWordDiagonal(String word,int r, int c){
      if (word.length() > columnSize() - c || word.length() > rowSize() - r){return false;}
          for (int i = 0; i < word.length(); i++){
            if (data[r + i][c + i] != '_' && data[r + i][c + i] != word.charAt(i)){return false;}
          }
          for (int i = 0; i < word.length(); i++){
            insert(word.charAt(i), r + i, c + i);
          }
          return true;
        }

}
