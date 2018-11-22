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
      addAllWords();

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
      addAllWords();
    //Use the random seed specified.
}
    private void findWords(String fileName) throws FileNotFoundException{//helper function for constructor
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
    public boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
      if (rowIncrement == 0 && colIncrement == 0){return false;}//checks for improper increments
      try{
        for (int i = 0; i < word.length(); i++){
          if (data[r + i * rowIncrement][c + i * colIncrement] != '_' && data[r][c + i] != word.charAt(i)) {return false;}
        }
      }
      catch (IndexOutOfBoundsException e){return false;}
      for (int pos = 0; pos < word.length(); pos++, r += rowIncrement, c += colIncrement){
        insert(word.charAt(pos), r, c);
      }
      return true;
    }

    public void addAllWords(){
      while (wordsToAdd.size() > 0){
        //addWord(wordsToAdd.get(randgen.nextInt()), randgen.nextInt() % rowSize(), randgen.nextInt() % columnSize(), randgen.nextInt() % 2, randgen.nextInt() % 2); too clunky
        String word = wordsToAdd.get(randgen.nextInt(wordsToAdd.size()));
        int rowdirection = randgen.nextInt() % 2;
        int columndirection = randgen.nextInt() % 2;
        for (int tries = 1; tries <= 50 && !(wordsAdded.contains(word)); tries++){
          int r = (randgen.nextInt(rowSize()));
          int c = (randgen.nextInt(columnSize()));
          if (addWord(word, r, c, rowdirection, columndirection)){
            wordsAdded.add(word);
            wordsToAdd.remove(word);
          }
        }
      }
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

    private void insert(char insert, int r, int c){//adds a single character with given location and character. Written for clarity
          data[r][c] = insert;
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
