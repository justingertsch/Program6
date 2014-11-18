/**
 * Created by Justin on 11/17/2014.
 */
public class DirFile extends DirectoryComponent
{
    private String name = null;
    private DirectoryComponent parent = null;
    public DirFile(String name, DirectoryComponent parent)
    {
        this.name = name;
        this.parent = parent;
    }


    public void listall(int num)
    {
        String tab = "\t";
        tab  = new String(new char[num]).replace("\0", tab);
        System.out.println(this.name + tab);
    }

    public int count(boolean top)
    {
        return 1;
    }

    public int countall(boolean top)
    {
        return 1;
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
