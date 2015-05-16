
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by matthias on 5/16/15.
 */
public class Main
{

    public static void main(String[] args) throws IOException
    {
        ArrayList<File> sub_dirs = new ArrayList<File>();

        final String start_dir = "/home/matthias/Desktop/language_detective/word_lists_1";
        listf( start_dir, sub_dirs );

        for(File dir : sub_dirs)
        {
            BufferedReader br = new BufferedReader(new FileReader( dir ));
            String line = null;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }

    }

    public static void listf(String directoryName, ArrayList<File> files)
    {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList)
        {
            if (file.isFile())
            {
                files.add(file);
            }
            else if (file.isDirectory())
            {
                listf(file.getAbsolutePath(), files);
            }
        }
    }


}




