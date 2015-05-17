import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main
{
	
	static HashMap<String, Boolean>  de_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean>  fr_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean>  ru_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean> eng_map = new HashMap<String, Boolean>();
	
	
    public static void main( String[] args ) throws URISyntaxException, IOException
    {
        ArrayList<Path> dictionary_files = new ArrayList<Path>();
    	

        File currentDir = new File( "." ); // Read current file location
        //System.out.println(currentDir.getAbsolutePath());
        
        File targetDir = null;
        if (currentDir.isDirectory()) 
        {
            targetDir = new File( currentDir, "word_lists_2" ); // Construct the target directory file with the right parent directory
        }
        if ( targetDir != null && targetDir.exists() )
        {
            SearchDirectories.listDirectoryAndFiles( targetDir.toPath(), dictionary_files );
        }
        

        for(Path dir : dictionary_files)
        {
        	//System.out.println( dir );
        	
        	String word_holding_directory_path = dir.toString().toLowerCase();
        	

        	
            BufferedReader br = new BufferedReader( new FileReader( dir.toString() ) );
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
        
        
        
        //GET THE USER INPUT
        ArrayList<String> input_sentence = new ArrayList<String>();
        
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a sentence: ");
         
        String [] tokens = in.nextLine().split("\\s");
         
        for (int i = 0; i < tokens.length; i++)
        {
        	input_sentence.add( tokens[i].toString() );
        }
         
        if (input_sentence.size() == 0)
        {
           System.out.println("Insufficient input.");
   
        } 
        else
        {
            for(String elem : input_sentence)
            {
                //System.out.println( elem );
            }
        
        }
       
        
        Iterator it = de_map.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + ", " + pair.getValue());

            for(String elem : input_sentence)
            {
                if(pair.getKey().toString().toLowerCase().trim().equals( elem.toLowerCase().trim() ) )
                {
                	de_map.put(pair.getKey().toString(), true);
                }
//                	System.out.println( "pair.getKey().toString().toLowerCase().trim():    " + pair.getKey().toString().toLowerCase().trim() );              	
//                	System.out.println( "elem.toLowerCase().trim():    " + elem.toLowerCase().trim() );

            }
            
        }
        
        
        //print debugging
        System.out.println("post");
        for (Map.Entry entry : de_map.entrySet()) 
        {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

        
        
    }


    

    

}