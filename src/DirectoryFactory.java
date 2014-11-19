import java.util.Scanner;

/**
 * Created by Justin on 11/17/2014.
 */
public class DirectoryFactory
{

    public static DirectoryComponent createDirTree(Scanner in)
    {
        String topName = null;
        while(in.hasNextLine())
        {
            topName = in.nextLine();
            break;
        }
        DirectoryComponent parent = new Directory(topName, null);
        process(in, 0, parent);

        return parent;
    }

    private static void process(Scanner in, int passedLevel, DirectoryComponent passedParent)
    {

        while(in.hasNextLine())
        {

            String name = in.nextLine();
            int level = countLevel(name);
            name = name.trim();
            if(name.contains(":"))
            {
                Directory dir = null;

                if(level < passedLevel)
                {

                    dir = new Directory(name, passedParent.up());
                    passedParent.up().add(dir);
                    process(in, level, passedParent.up());
                }
                else
                {
                    dir = new Directory(name, passedParent);
                    passedParent.add(dir);
                    process(in, level, dir);
                }
            }
            else
            {
                DirFile dirFile = null;

                if(level< passedLevel)
                {
                    dirFile = new DirFile(name, passedParent.up());
                    passedParent.up().add(dirFile);
                    process(in, level, passedParent.up());
                }
                else
                {
                    dirFile = new DirFile(name, passedParent);
                    passedParent.add(dirFile);
                    process(in, level, passedParent);
                }
            }

        }

    }

    public static int countLevel(String line)
    {
        int counter = 0;
        int lastIndex = 0;
        String find =  "   ";
        while(lastIndex != -1){

            lastIndex = line.indexOf(find,lastIndex);

            if( lastIndex != -1){
                counter ++;
                lastIndex+=find.length();
            }
        }
        return counter;
    }
}
