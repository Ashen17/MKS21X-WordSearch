public class Driver2{
  public static void main(String[] args) {
    WordSearch meats = new WordSearch(10, 10, "Meats.txt");
    System.out.println(meats);
    WordSearch colors = new WordSearch(10, 10, "Colors.txt");
    System.out.println(colors);
  }
}
