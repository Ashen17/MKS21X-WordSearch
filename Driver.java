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
  }
}
