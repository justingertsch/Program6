import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Justin on 11/17/2014.
 */
public class Directory extends DirectoryComponent
{
    private ArrayList<DirectoryComponent> directoryComponents = new ArrayList<DirectoryComponent>();
    private String name = null;
    private DirectoryComponent parent = null;


    public Directory(String name, DirectoryComponent parent)
    {
        this.name = name;
        this.parent = parent;
    }

    public void add(DirectoryComponent directoryComponent)
    {
        this.directoryComponents.add(directoryComponent);
    }
    public void remove(DirectoryComponent directoryComponent)
    {
        this.directoryComponents.remove(directoryComponent);
    }

    public void list()
    {
        Iterator<DirectoryComponent> iterator = directoryComponents.iterator();
        while(iterator.hasNext())
        {
            DirectoryComponent directoryComponent = iterator.next();
            System.out.print(directoryComponent.getName()+" ");
        }
        System.out.println();
    }

    public void listall(int num)
    {
        String tab = "\t";
        tab  = new String(new char[num]).replace("\0", tab);
        System.out.println(this.name + tab);
        Iterator<DirectoryComponent> iterator = directoryComponents.iterator();
        while(iterator.hasNext())
        {
            DirectoryComponent directoryComponent = iterator.next();
            directoryComponent.listall(num + 1);
        }
    }

    public DirectoryComponent chdir(String directory)
    {
        int index = dirExists(directory);
        if( index >= 0)
        {
            return this.directoryComponents.get(index);
        }

        System.out.println("No such directory");
        return null;
    }

    public DirectoryComponent up()
    {
        if(this.parent == null)
        {
            System.out.println("Already at root directory");
            return this;
        }
        return this.parent;
    }

    public int count(boolean top)
    {
        if(!top)
            return 0;

        int count = 0;
        Iterator<DirectoryComponent> iterator = directoryComponents.iterator();
        while(iterator.hasNext())
        {
            DirectoryComponent directoryComponent = iterator.next();
            count += directoryComponent.count(false);
        }
        return count;

    }

    public int countall(boolean top)
    {
        int count = 0;
        Iterator<DirectoryComponent> iterator = directoryComponents.iterator();
        while(iterator.hasNext())
        {
            DirectoryComponent directoryComponent = iterator.next();
            count += directoryComponent.countall(false);
        }

        if(!top)
            return 0;
        return count;

    }

    private int dirExists(String directory)
    {
        for (int i = 0; i < this.directoryComponents.size(); i++)
        {
            DirectoryComponent dir = this.directoryComponents.get(i);
            if (directory.equals(dir.getName()+":"))
            {
                return i;
            }
        }
        return -1;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return "Name: "+this.name+"  Parent: "+this.parent.toString();
    }
}
