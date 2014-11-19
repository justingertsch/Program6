import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Justin on 11/17/2014.
 */
public class Explorer
{
    private DirectoryComponent currentDirectory;


    public Explorer(DirectoryComponent directoryComponent)
    {
        this.currentDirectory = directoryComponent;
    }

    public void start()
    {
        Scanner userInput = new Scanner(System.in);
        String[] cmds = null;
        do
        {
            System.out.print(currentDirectory.getName()+"> ");
            String input = userInput.nextLine();
            cmds = input.trim().split(" +");
            if(cmds.length < 1 || cmds.length > 2 || cmds[0] == null)
            {
                invalid();
            }
            else
            {
                executeCommand(cmds);
            }


        }
        while(!cmds[0].equals("q"));
    }

    private void executeCommand(String[] cmds)
    {
        switch(cmds[0])
        {
            case "list":
                this.currentDirectory.list();
                break;

            case "listall":
                this.currentDirectory.listall(0);
                break;

            case "chdir":
                if(cmds.length < 2 || cmds[1] == null)
                {
                    invalid();
                    break;
                }
                DirectoryComponent temp = this.currentDirectory.chdir(cmds[1]);
                if(temp != null)
                    this.currentDirectory = temp;
                break;

            case "up":
                this.currentDirectory = this.currentDirectory.up();
                break;

            case "count":
                System.out.println(this.currentDirectory.count(true));
                break;

            case "countall":
                System.out.println(this.currentDirectory.countall(true));
                break;

            case "q":
                break;

            default:
                invalid();
                break;


        }
    }

    private void invalid()
    {
        System.out.println("Invalid command");
    }
}
