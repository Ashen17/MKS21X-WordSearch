import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class WordSearch {
  public static void main(String[] args) {
    String directions = "Format of the function: \n" +
      "java WordSearch r c filename [seed] [answer] \n" +
      "r, c are integers that represent the row and column size of the wordsearch respectively.Either value must be larger than the largest word(s) in filename \n" +
      "filename is a String that is the name of the file that has the lists of words you wish you create a wordsearch out of \n" +
      "seed is integer from 0 to 10000, inclusive. \n" +
      "answer is a string that must be named keys";
    try{
    if (args.length < 3 || (args.length == 4 && Integer.parseInt(args[3]) > 10000) || (args.length == 4 && Integer.parseInt(args[3]) < 0) || Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0) {
      System.out.println(directions);
    }
    else if (args.length == 3){System.out.println(new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]));}
    else if(args.length >= 5 && args[4].equals("keys")){System.out.println((new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]))).Answers());}
    else if(args.length == 4){System.out.println(new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3])));}
    }
    catch (FileNotFoundException e){
      System.out.println(directions);
    }
    catch (IndexOutOfBoundsException e){System.out.println(e);}//from addallwordS(), if r and c are both smaller than the largest word in the file
    catch (NumberFormatException e){
      System.out.println(directions);
    }
  }
    private char[][]data;
    private int seed;
    private Random randgen;//the only random thing generated via clock random
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;

    /* obselete constructor
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      clear();
      seed = (int)(Math.random() * 777777);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
    }
    */

    public WordSearch(int rows, int cols, String fileName) throws FileNotFoundException, IndexOutOfBoundsException{
      data = new char[rows][cols];
      clear();
      seed = (int)(Math.random() * 777777);
      randgen = new Random(seed);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      findWords(fileName);
      addAllWords();
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
    }
    private void findWords(String fileName) throws FileNotFoundException{//helper function for constructor
      File f = new File(fileName);
      Scanner read = new Scanner(f);
      while (read.hasNext()){
        String line = read.nextLine();
        //System.out.println(line);
        //System.out.println(wordsToAdd);
        wordsToAdd.add(line.toUpperCase());
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
        if (word.length() > rowSize() && word.length() > columnSize()){throw new IndexOutOfBoundsException("The word " + word + " is too small for the specified row and column sizes");}
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
    private String Answers(){ //for printing out only the anwers, basically a toString that must be called manually
      String result = "";
      for (int r = 0; r < rowSize(); r++){
        result += "|";
        for (int c = 0; c < columnSize(); c++){
          if (data[r][c] == '_'){result += "  ";}
          else{result += data[r][c] + " ";}
        }
        result += "|\n";
      }
      result += "word: " + wordsAdded + "(" + "seed: " + seed + ")";
      return result;
    }

    public String toString(){
      String result = "";
      for (int r = 0; r < rowSize(); r++){
        result += "|";
        for (int c = 0; c < columnSize(); c++){
          if (data[r][c] == '_'){result += (char)(randgen.nextInt(26) + 65) + " ";}//uses ASCII values typecasted to randomly select characters to fill in fill in "_"'s
          else{result += data[r][c] + " ";}
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

    public boolean addWordHorizontal(String word, int r, int c){//obselete
      if (word.length() > rowSize() - r){return false;}
      for (int i = 0; i < word.length(); i++){
        if (data[r][c + i] != '_' && data[r][c + i] != word.charAt(i)) {return false;}
      }
      for (int i = 0; i < word.length(); i++){
        insert(word.charAt(i), r, c + i);
      }
      return true;
    }

    public boolean addWordVertical(String word,int r, int c){//obselete
      if (word.length() > columnSize() - c){return false;}
          for (int i = 0; i < word.length(); i++){
            if (data[r + i][c] != '_' && data[r + i][c] != word.charAt(i)){return false;}
          }
          for (int i = 0; i < word.length(); i++){
            insert(word.charAt(i), r + i, c);
          }
          return true;
    }
    public boolean addWordDiagonal(String word,int r, int c){//obselete
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
