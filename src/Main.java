
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main
{
	static HashMap<String, Boolean>  de_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean>  fr_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean>  ru_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean> eng_map = new HashMap<String, Boolean>();

    public static void main(String[] args) throws IOException
    {
        ArrayList<File> sub_dirs = new ArrayList<File>();

        final String filePath = "/home/matthias/Desktop/language_detective/word_lists_2";

        listf( filePath, sub_dirs );

        for(File dir : sub_dirs)
        {
        	String word_holding_directory_path = dir.toString().toLowerCase();
        	

        	
            BufferedReader br = new BufferedReader(new FileReader( dir ));
            String line = null;
            while ((line = br.readLine()) != null)
            {
                //System.out.println(line);
                if(word_holding_directory_path.toLowerCase().contains("/de/") )
                {
                	de_map.put(line, false);	
                }
                if(word_holding_directory_path.toLowerCase().contains("/ru/") )
                {
                	ru_map.put(line, false);
                }
                if(word_holding_directory_path.toLowerCase().contains("/fr/") )
                {
                	fr_map.put(line, false);
                }
                if(word_holding_directory_path.toLowerCase().contains("/eng/") )
                {
                	eng_map.put(line, false);
                }
            }
        }
        
        //print debugging
        for (Map.Entry entry : de_map.entrySet()) 
        {
            System.out.println(entry.getKey() + ", " + entry.getValue());
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







