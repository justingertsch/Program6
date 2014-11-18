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
            name.replaceAll("\\s+","");
            if(name.contains(":"))
            {
                Directory dir = new Directory(name, passedParent);
                passedParent.add(dir);
                if(level < passedLevel)
                {
                    process(in, level, passedParent.up());
                }
                else
                {
                    process(in, level, dir);
                }
            }
            else
            {
                DirFile dirFile = new DirFile(name, passedParent);
                passedParent.add(dirFile);
                if(level< passedLevel)
                {
                    process(in, level, passedParent.up());
                }
                else
                {
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
