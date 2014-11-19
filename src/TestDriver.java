import java.io.*;
import java.io.File;
import java.util.Scanner;

/**
 * Created by Justin on 11/17/2014.
 */
public class TestDriver
{
    public static void main(String[] args)
    {
        Scanner in = null;
        try
        {
            in = new Scanner(new File("directory.dat"));

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            System.exit(1);
        }

        Explorer exp = new Explorer(DirectoryFactory.createDirTree(in));
        exp.start();

    }
}
