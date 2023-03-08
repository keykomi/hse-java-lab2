import java.io.*;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int[] UpperLower = new int[2];
        String path;
        path = OpenFileInput();
        UpperLower = Counter(path);
        OpenFileOutput(UpperLower);
    }

    public static String OpenFileInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Print name of input file:");
        String pathin = scanner.nextLine();

        File fin = new File(pathin);
        if (!fin.exists()) {
            System.err.println("File " + pathin + " not found");
            System.exit(-1);
        } else {
            System.out.println("File " + pathin + " exists");
        }
        return pathin;
    }

    public static int[] Counter(String path) throws FileNotFoundException {
        int[] UpperLower = new int[2];
        try (FileReader reader = new FileReader(path)) {
            int c;
            while ((c = reader.read()) != -1) {
                if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                    if (Character.isUpperCase(c))
                        UpperLower[0]++;
                    else if (Character.isLowerCase(c))
                        UpperLower[1]++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return UpperLower;
    }

    public static void OpenFileOutput(int[] UpperLower){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Print name of output file:");
        String pathout = scanner.nextLine();

        try (FileWriter writer = new FileWriter(pathout, false))
        {
            writer.append("Capital letters: " + UpperLower[0] + "\n");
            writer.append("Lowercase letters: " + UpperLower[1] + "\n");
            writer.flush();
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}