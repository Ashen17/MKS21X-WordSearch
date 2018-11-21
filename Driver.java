public class Driver{
  public static void main(String[] args) {
    WordSearch animals = new WordSearch(4, 4);
    System.out.println(animals);
    System.out.println(animals.addWordHorizontal("cat", 1, 1));
    System.out.println(animals);
    System.out.println(animals.addWordVertical("dog", 0, 0));
    System.out.println(animals);
    System.out.println(animals.addWordHorizontal("fox", 0, 1));
    System.out.println(animals);
    System.out.println();
    System.out.println(animals.addWordHorizontal("panda", 3, 0));
    System.out.println(animals);
    System.out.println(animals.addWordVertical("cow", 1, 1));
    System.out.println(animals);
    animals = new WordSearch(4, 4);
    System.out.println(animals.addWordDiagonal("fish", 0, 0));
    System.out.println(animals);
    WordSearch colors = new WordSearch(4, 4, "Words.txt", 31425);
    System.out.println(colors);
    //System.out.println(colors.getWordstoAdd());
    WordSearch whatever = new WordSearch(10, 10, "Words.txt");
    System.out.println(whatever);
    System.out.println(whatever.addWord("boo", 3, 3, 1, 0));//boo goes straight down from [3, 3]
    System.out.println(whatever);
    System.out.println(whatever.addWord("orrery", 4, 3, 0, 1));//true
    System.out.println(whatever);
    System.out.println(whatever.addWord("Yosemite", 3, 8, 1 ,-1));//false
    System.out.println(whatever);
    System.out.println(whatever.addWord("Yose",3, 2, 1, 0 ));//true
    System.out.println(whatever);
    System.out.println(whatever.addWord("Yosemite", 3, 2, 1, 0));//false
    System.out.println(whatever);
    System.out.println(whatever.addWord("annoying", 2, 2, -1, -1));//false
    System.out.println(whatever.addWord("cat",2, 5, 1, 0 ));//false
    System.out.println(whatever.addWord("dog", 0, 0, 0, 0));//false
    System.out.println(whatever);
  }
}
