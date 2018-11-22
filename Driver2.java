public class Driver2{
  public static void main(String[] args) {
    WordSearch meats = new WordSearch(10, 10, "Meats.txt");
    System.out.println(meats);
    WordSearch colors = new WordSearch(10, 10, "Colors.txt");
    System.out.println(colors);
    WordSearch colors2 = new WordSearch(10, 10, "Colors.txt", 1253213);
    System.out.println(colors2);
    WordSearch colors3 = new WordSearch(10, 10, "Colors.txt", 1253213);
    System.out.println(colors3);
  }
}
