import java.io.File;
import java.util.Comparator;

public class FileNameComparator implements Comparator<File>
{

    @Override
    public int compare(File o1, File o2) 
    {
        String s1 = o1.getName();
        String s2 = o2.getName();
        boolean s1HasExt = true;
        boolean s2HasExt = true;
        if (s1.indexOf(".") == -1)
        {
            s1HasExt = false;
        }
        if (s2.indexOf(".") == -1)
        {
            s2HasExt = false;
        }
        
        if (s1HasExt)
        {
            s1 = s1.substring(0, s1.indexOf(".")-1);
        }
        if (s2HasExt)
        {
            s2 = s2.substring(0, s2.indexOf(".")-1);
        }

        int Size;

        if (s2.length() < s1.length())
        {
            Size = s2.length();
        }
        else
        {
            Size = s1.length();
        }
        for (int i = 0; i < Size; i++)
        {
            Character c1 = Character.toLowerCase(s1.charAt(i));
            boolean c1IsSpecific = !(Character.isDigit(c1) || Character.isLetter(c1));
            Character c2 = Character.toLowerCase(s2.charAt(i));
            boolean c2IsSpecific = !(Character.isDigit(c2) || Character.isLetter(c2));
            if((c1IsSpecific && !(c2IsSpecific)))
            {
                return -1;
            }
            if((c2IsSpecific && !(c1IsSpecific)))
            {
                return 1;
            }
            if(c1 <  c2 && !(c1IsSpecific) && !(c2IsSpecific))
            {
                return -1;
            }
            if(c1 >  c2 && !(c1IsSpecific) && !(c2IsSpecific))
            {
                return 1;
            }
        }
        return Size;
        }
    
}
