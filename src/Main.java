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
	//These structures hold the word list & corresponding flag indicator for each language
	static HashMap<String, Boolean>  de_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean>  fr_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean>  ru_map = new HashMap<String, Boolean>();
	static HashMap<String, Boolean> eng_map = new HashMap<String, Boolean>();
	
	
    public static void main( String[] args ) throws URISyntaxException, IOException
    {
    	//put them all in a list for later processing
    	List<HashMap<String, Boolean>> lang_maps = new ArrayList<HashMap<String, Boolean>>();
    	
    	lang_maps.add( de_map );
    	lang_maps.add( fr_map );
    	lang_maps.add( ru_map );
    	lang_maps.add( eng_map );
    	
    	
    	
    	//this holds all the dictionary files, i.e. word lists garners from language folders
        ArrayList<Path> dictionary_files = new ArrayList<Path>();
    	
        
        //THIS SEGMENT IS FOR DYNAMICALLY LOCATING THE DIRECTORY, SO THE PROGRAM WORKS "OUT OF THE BOX"
/*******************************************************************************************************************************************/
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
/*******************************************************************************************************************************************/
        
        //this populates word presence data structs for each language
        for(Path dir : dictionary_files)
        {
        	
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
//        for (Map.Entry entry : de_map.entrySet()) 
//        {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
        
        
/*******************************************************************************************************************************************/ 
        //GET THE USER INPUT
        ArrayList<String> input_text = new ArrayList<String>();
        
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a sentence: ");
         
        String [] tokens = in.nextLine().split("\\s");
         
        for (int i = 0; i < tokens.length; i++)
        {
        	input_text.add( tokens[i].toString() );
        }
/*******************************************************************************************************************************************/
        
        //iterate over the hashmaps of all the languages we're considering
        for( int i = 0; i < lang_maps.size(); i++ )
        {
        	HashMap<String, Boolean> working_lang_map = lang_maps.get( i );
        	
            Iterator it = working_lang_map.entrySet().iterator();
            while (it.hasNext()) 
            {
                Map.Entry pair = (Map.Entry)it.next();
                //System.out.println(pair.getKey() + ", " + pair.getValue());

                for(String word : input_text)
                {
                    if(pair.getKey().toString().toLowerCase().trim().equals( word.toLowerCase().trim() ) )
                    {
                    	working_lang_map.put(pair.getKey().toString(), true);
                    }
                }          
            }
        	
        }
        
        
        //print debugging
        System.out.println("post");
        
        for( int i = 0; i < lang_maps.size(); i++ )
        {
        	HashMap<String, Boolean> working_lang_map = lang_maps.get( i );
        	
	        for (Map.Entry entry : working_lang_map.entrySet()) 
	        {
	            System.out.println(entry.getKey() + ", " + entry.getValue());
	        }
        }

        
        
    }


    

    

}











//if (input_text.size() == 0)
//{
// System.out.println("Insufficient input.");
//
//} 
//else
//{
//  for(String word : input_text)
//  {
//      //System.out.println( elem );
//  }
//
//}

// de_map, fr_map , ru_map , eng_map

//MAP FOR GERMAN LANGUAGE:: THIS NEEDS TO BE MORE DYNAMIC
//Iterator it = de_map.entrySet().iterator();
//while (it.hasNext()) 
//{
//  Map.Entry pair = (Map.Entry)it.next();
//  System.out.println(pair.getKey() + ", " + pair.getValue());
//
//  for(String word : input_text)
//  {
//      if(pair.getKey().toString().toLowerCase().trim().equals( word.toLowerCase().trim() ) )
//      {
//      	de_map.put(pair.getKey().toString(), true);
//      }
//  }          
//}









//System.out.println( "pair.getKey().toString().toLowerCase().trim():    " + pair.getKey().toString().toLowerCase().trim() );              	
//System.out.println( "elem.toLowerCase().trim():    " + elem.toLowerCase().trim() );