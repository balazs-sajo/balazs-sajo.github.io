import java.io.File;
import java.util.ArrayList;

public class Project 
{
    

    public static void ListDir(File dir, ArrayList<File> Folders) 
    {
        
        File[] Files = dir.listFiles();

        if (Files != null && Files.length > 0) 
        {
            for (File thisFile : Files) 
            {
                if (thisFile.isDirectory()) 
                {
                    Folders.add(thisFile);
                    ListDir(thisFile.getAbsoluteFile(), Folders);
                }              
            }
        }
    }
    public static void main(String[] args) 
    {
        String dirPath = args[0];
        //String dirPath = "C:\\Users\\Balazs\\Pictures";
        File dir = new File(dirPath);
        ArrayList<File> Folders = new ArrayList<>();
        ListDir(dir, Folders);
        Folders.add(dir);
        HtmlUtils.Folder(Folders, dir);
        HtmlUtils.Picture(Folders, dir);
    }
}