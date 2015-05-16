import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class niaM
{
    public static void main( String[] args ) throws URISyntaxException, IOException
    {

        File currentDir = new File( "." ); // Read current file location
        
        System.out.println(currentDir.getAbsolutePath());
        
        File targetDir = null;
        if (currentDir.isDirectory()) 
        {
            targetDir = new File( currentDir, "word_lists_2" ); // Construct the target directory file with the right parent directory
        }
        if ( targetDir != null && targetDir.exists() )
        {
            listDirectoryAndFiles( targetDir.toPath() );
        }
    }

    private static void listDirectoryAndFiles( Path path ) throws IOException
    {
        DirectoryStream<Path> dirStream = Files.newDirectoryStream( path );
        for ( Path p : dirStream )
        {
            System.out.println( p.getFileName() );
            if ( p.toFile().isDirectory() )
            {
                listDirectoryAndFiles( p );
            }
        }
    }

}