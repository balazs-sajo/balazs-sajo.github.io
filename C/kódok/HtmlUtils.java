import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HtmlUtils 
{
    public static String GetHome( File Root, File thisFile)
    {
        String Ret = "index.html";
        File tmp = thisFile;
        if(tmp.getAbsolutePath().contains(Root.getAbsolutePath()))
        {
        while (tmp.getAbsolutePath() != Root.getAbsolutePath())
        {
            if(tmp.getAbsolutePath().equals(Root.getAbsolutePath()))
            {
                return Ret;
            }
            tmp = tmp.getParentFile();
            Ret ="../" + Ret;

        }
        return Ret;
        }
        else
        {
            System.out.println("Hiba   "+ thisFile.getName());
            return "nem";
        }
    }
    public static void Picture(ArrayList < File > Folders, File dir)
     {
        for (File CurrentDir: Folders) 
        {
            FilenameFilter jpgFilefilter = new FilenameFilter(){
                public boolean accept(File dir, String name) 
                {
                    
                        String lowercaseName = name.toLowerCase();
                        if (lowercaseName.endsWith("png")) 
                        {
                            return true;
                        }
                        if (lowercaseName.endsWith("jpg")) 
                        {
                            return true;
                        }
                        if (lowercaseName.endsWith("jpeg")) 
                        {
                            return true;
                        }
                        if (lowercaseName.endsWith("bmp")) 
                        {
                            return true;
                        }
                        else 
                        {
                            return false;
                        }
                }
             };
            File[] FileList = CurrentDir.listFiles(jpgFilefilter);
            Arrays.sort(FileList, new FileNameComparator());
            
            
            for (File CurrentFile: FileList) 
            {
                if (CurrentFile.isFile()) 
                {
                    File[] PictureData = new File[3];
                    PictureData[0] = CurrentFile;
                    PictureData[1] = getBefore(CurrentFile, FileList);
                    PictureData[2] = getNext(CurrentFile, FileList);
                    Generate(PictureData, dir);
                }
            }
        }
    }
    public static void Folder(ArrayList < File > Folders, File dir) 
    {
        for (File CurrentDir: Folders) 
        {
            File[] FolderData = new File[2];
            FolderData[0] = CurrentDir;
            FolderData[1] = CurrentDir.getParentFile();
            Generate(FolderData, dir);
        }
    }

    public static int getIndex(File[] FileList, File f1) 
    {
        for (int i = 0; i < FileList.length; i++) 
        {
            if (f1 == FileList[i]) {
                return i;
            }
        }
        return 0;

    }

    public static File getBefore(File f1, File[] FileList) 
    {
        File isFirst = new File(" isFirst");
        int index = getIndex(FileList, f1);
        if (f1 == FileList[0] || FileList[index - 1].isDirectory()) 
        {
            return isFirst;
        }
        return FileList[index - 1];
    }
    public static File getNext(File f1, File[] FileList) 
    {
        File isLast = new File(" isLast");
        int index = getIndex(FileList, f1);
        if (f1 == FileList[FileList.length - 1] || FileList[index + 1].isDirectory()) 
        {
            return isLast;
        }
        return FileList[index + 1];
    }

    public static void Generate(File[] Data, File dir) 
    {
        if (Data.length == 2) // mappa
        {
            String SP = GetHome(dir, Data[0]);
            
            File CurrentFolder = Data[0];
            List < File > Folders = new ArrayList < > ();
            List < File > Files = new ArrayList < > ();
            File[] ListFiles = CurrentFolder.listFiles();
            for (File cf: ListFiles) 
            {
                if (cf.isDirectory()) 
                {
                    Folders.add(cf);
                }
                else 
                {
                    Files.add(cf);
                }
            }
            try 
            {
                File html = new File(CurrentFolder.getAbsolutePath() + "/index.html/");

                if (html.createNewFile()) 
                {
                    FileWriter writer = new FileWriter(html);
                    writer.write("<html> <head> <title>Projekt</title> <style>\n");
                    writer.write("a:link {color: green; background-color: transparent; text-decoration: none;}\n");
                    writer.write("a:visited {color: black; background-color: transparent; text-decoration: none;}\n");
                    writer.write("a:hover {color: pink; background-color: transparent; text-decoration: none;}\n");
                    writer.write("</style> </head> <body style=\"background-color:khaki;\">\n");
                    writer.write("<h1 align = \"right\"> <a href = \"" + SP + "\"><button> Home Page</button> </a> </h1>\n");
                    writer.write("<h1 style = \"font-size:12px\"> <hr> <p style = \"font-size:24px\"><b>Directories</b></p>\n");
                    if(!(CurrentFolder.getAbsolutePath().equals(dir.getAbsolutePath())))
                    {
                    writer.write("<p> <a style = \"font-size:20px\" href=\"../index.html\">. . .</a> </p>\n");
                    }
                    for (File cf: Folders) 
                    {
                        writer.write("<p> <a style = \"font-size:20px\" href=\"" + cf.getName() + "/index.html\">" + cf.getName() + "</a> </p>\n");
                    }
                    writer.write("<hr><p style =\"font-size:24px\"><b>Pictures</b></p>\n");
                    for (File cf: Files) 
                    {
                        writer.write("<p> <a style = \"font-size:20px\" href=\"" + cf.getName() + ".html\">" + cf.getName() + "</a> </p>\n");
                    }
                    writer.write("</p><p><p> </p> </body></html>");
                    writer.close();

                } 
                else 
                {
                    System.out.println("File already exists.");
                }

            } 
            catch (Exception e) 
            {
                System.out.println("Error !!!");
            }
        }

        if (Data.length == 3) // fájl
        {

        
            String SP = GetHome(dir, Data[0].getParentFile());

            File CurrentFile = Data[0];
            File BeforeFile = Data[1];
            File NextFile = Data[2];
            try 
            {
                File html = new File(CurrentFile.getAbsolutePath() + ".html");

                if (html.createNewFile()) 
                {
                    FileWriter writer = new FileWriter(html);
                    writer.write("<html> <head> <title>Projekt</title> <style>\n");
                    writer.write("a:link {color: green; background-color: transparent; text-decoration: none;}\n");
                    writer.write("a:visited {color: green; background-color: transparent; text-decoration: none;}\n");
                    writer.write("</style> </head> <body style=\"background-color:khaki;\">\n");
                    writer.write("<h1 align = \"right\"> <a href = \"" + SP + "\"><button> Home Page</button> </a> </h1>\n");
                    writer.write("<p> <button onclick=\"document.location='index.html'\">. . .</button> </p>\n");
                    if (BeforeFile.getName() != " isFirst") 
                    {
                        writer.write("<p> <a style = \"font-size:20px\" href=\"" + BeforeFile.getName() + ".html\"> <<&emsp;&emsp;</a>\n");
                    } 
                    else 
                    {
                        writer.write("<p> <a style = \"font-size:20px\" href=\"\"> <<&emsp;&emsp;</a>\n");
                    }
                    if (NextFile.getName() != " isLast") 
                    {
                        writer.write("<a style = \"font-size:20px\" href=\"" + NextFile.getName() + ".html\"> <span style=\"padding-left: 200px;\">  >></a> </p>\n");
                        writer.write("<p> <a href = \"" + NextFile.getName() + ".html\"> <img align = \"middle\"  src=\"" + CurrentFile.getName() + "\" width=\"300\" height=\"300\" > </a> </p>\n");
                    } 
                    else 
                    {
                        writer.write("<a style = \"font-size:20px\" href=\"\"> <span style=\"padding-left: 200px;\">  >></a> </p>\n");
                        writer.write("<p> <a href = \"\"> <img align = \"middle\"  src=\"" + CurrentFile.getName() + "\" width=\"300\" height=\"300\" > </a> </p>\n");
                    }
                    writer.write("<p> <span style=\"padding-left: 100px;\">" + CurrentFile.getName() + "</p> </body> </html>\n");
                    writer.close();
                } 
                else 
                {
                    System.out.println("File already exists.");
                }

            } 
            catch (Exception e) 
            {
                System.out.println("Error");
            }
        }
    }
}