   import java.io.File;
   import java.io.BufferedReader;
   import java.io.FileReader;
   import java.io.PrintWriter;
   import java.io.IOException;
   import java.io.FileNotFoundException;
   import java.util.ArrayList;
   import java.util.List;
   
 /**
 * Utilites for creating, writing to, and reading from
 * files. In general, methods that fail due to IOExceptions
 * or FileNotFoundExceptions will return null and will NOT
 * throw Exceptions. Banana.
 *
 * @author Michael Pace
 */
   public class FileUtil {
   
      private File file = null;
   	
   	/**
   	* Creates a FileUtil object holding the file specified 
   	* by in.
   	*
   	* @param in File to be opperated on by
   	* the methods of FileUtil.
   	*/
      public FileUtil(File in) {
         file = in;
      }
      
   	/**
   	* Creates a FileUtil object by creating a file specified 
   	* by fileNameAndPath.
   	*
   	* @param fileNameAndPath File to be created and held by 
   	* FileUtil. If the file cannot be created, an IOException 
   	* is thrown. If an IOException is throw the file held by 
   	* FileUtil is null.
   	*/
      public FileUtil(String fileNameAndPath) throws IOException {
         file = createFile(fileNameAndPath);
         if (file == null)
            throw new IOException();
      }
   	
   	/**
   	* Gets the file held by this instance of FileUtil object.
   	*
   	* @return File held by this instance of FileUtil.
   	*/
      public File getFile() {
         return file;
      }
   	
   	/**
   	* Sets the file held by this instance of FileUtil.
   	*
   	* @param in File to be operated on by this FileUtil object.
   	* @return New file held by this instance of FileUtil.
   	*/
      public File setFile(File in) {
         file = in;
         return getFile();
      }
   	
   	/**
   	* Checks to see file specified by fileToCheck exists.
   	* Intended to save the client from having to create the file
   	* object and check its existance directly.
   	*
   	* @param fileToCheck Path/Name of the file to check the existance of
   	*
   	* @return True if the file specified by fileToCheck exists, false if
   	* it does not. Follows the behavior of File.exists().
   	*/
      public static boolean exists(String fileToCheck) {
         File f = new File(fileToCheck);
         return f.exists();
      }
   	
   	/**
   	* Reads the contents of the file stored in the
   	* FileUtil object and returns it as an array of
   	* Strings with one line per array element.
   	*
   	* @return Array of Strings representing the contents
   	* of the File stored in the FileUtil object.
   	*/
      public ArrayList<String> readFile() {
         BufferedReader reader = null;
         try {
            reader = new BufferedReader(new FileReader(file));
         }
            catch(FileNotFoundException e) {
               return null;
            }
         String line;
         ArrayList<String> fileContents = new ArrayList<String>();
         try {
            while((line = reader.readLine()) != null ) {
               fileContents.add(line);
            }
            reader.close();
         }
            catch (IOException e) {}
         return fileContents;
      }
   	
   	/**
   	* Reads the contents of file and returns an ArrayList
   	* with one line per element.
   	*
   	* @param in File to be read
   	* @return ArrayList of Strings from contents of file
   	*/
      public static ArrayList<String> readFile(File in) {
         if (in == null)
            return null;
         BufferedReader reader = null;
         try {
            reader = new BufferedReader(new FileReader(in));
         }
            catch(FileNotFoundException e) {
               return null;
            }
         String line;
         ArrayList<String> fileContents = new ArrayList<String>();
         try {
            while((line = reader.readLine()) != null ) {
               fileContents.add(line);
            }
            reader.close();
         }
            catch (IOException e) {}
         return fileContents;
      }
      
   	/**
   	* Reads the contents of in and returns the contents in 
   	* an array of Strings with one line in each element.
   	*
   	* @param inString Name of file to be read.
   	*
   	* @return Array of Strings representing the contents
   	* of the File stored in the FileUtil object.
   	*/
      public static ArrayList<String> readFile(String inString) {
         if (inString == null || !exists(inString))
            return null;
      	
         File in = createFile(inString);
         BufferedReader reader = null;
      	
         try {
            reader = new BufferedReader(new FileReader(in));
         }
            catch(FileNotFoundException e) {
               return null;
            }
      	
         String line;
         ArrayList<String> fileContents = new ArrayList<String>();
         try {
            while((line = reader.readLine()) != null ) {
               fileContents.add(line);
            }
            reader.close();
         }
            catch (IOException e) {}
         return fileContents;
      }
      
   	/**
   	* Writes the .toString() of each element of contents to 'in', 
   	* one line per element.
   	*
   	* @param contents What's to be written. Uses the .toString() 
   	* of each element.
   	* @param in File to be written to.
   	* @return True if the contents if written to file, false is 
   	* an exception is thrown.
   	*/
      public static boolean writeToFile(File in, char[] contents) {
         if (in == null || contents == null)
            return false;
         PrintWriter out = null;
         try {
            out = new PrintWriter(in);
         }
            catch(FileNotFoundException e) {
               return false;
            }
         for (char part : contents) {
            out.print(part);
         }
         out.close();
         return true;
      }
   	
   	/**
   	* Creates a file of the specified name. If no path is
   	* specified in the name then the file is created in the
   	* directory of this .class file.
   	*
   	* @param fileNameAndPath File name and path of the file to
   	* be created.
   	*
   	* @return The created file, if it was successfully created. If the
   	* file was not created due to an IOException, null is returned, but
   	* an exception is NOT thrown.
   	*/
      public static File createFile(String fileNameAndPath) {
         if (fileNameAndPath == null)
            return null;
         File f = new File(fileNameAndPath);
         try {
            if (!f.exists()) {
               f.createNewFile();
            }
         }//we can't create the file so we exit the method with a null return
            catch(IOException e) {
               return null;
            }
         return f;
      }
      
   	/**
   	* Makes a file and generates one long String from its contents
   	* until the String length is greater than k.
   	*
   	* @param filePath String representing files path
   	* @return String generated from file
   	*/
      public static String createFileString(String filePath, int k)	{
         BufferedReader reader = null;
         String[] temp = null;
         try {
            reader = new BufferedReader(new FileReader(filePath));
         }
            catch(FileNotFoundException e) {
               return null;
            }
         String line = "";
         String result = "";
         try {
            while((line = reader.readLine()) != null && k > result.length()) {
               temp = line.split(" ");
               for(String x : temp)	{
                  result += x + " ";
               }
            }
            reader.close();
         }
            catch (IOException e) {}
         return result;
      }
      
   	/**
   	* Creates a file of the specified name. If no path is
   	* specified in the name then the file is created in the
   	* directory of this .class file. If the file is successfully
   	* created, the contents of 'contents' are written to the file,
   	* one element per line.
   	*
   	* @param fileNameAndPath File name and path of the file to
   	* be created.
   	* @param contents What's to be written to the newly created file.
   	* Makes use of the .toString() of the Objects in contents.
   	* @return The created file, if it was successfully created. If the
   	* file was not created due to an IOException, null is returned, but
   	* an exception is NOT thrown.
   	*/
      public static File createAndWriteToFile(String fileNameAndPath, char[] contents) {
         if (fileNameAndPath == null || contents == null)
            return null;
         File f = createFile(fileNameAndPath);
      	//attempts to write contents to f, if writeToFile fails false is returned
      	//meaning this method failed and null should be returned
         if (!writeToFile(f, contents))
            return null;
         return f;
      }
      
   	/**
   	* This utility method takes an ArrayList of Strings, splits each
   	* by a specific delineator and then adds all of these Strings to  
   	* one resultant Array then adds these to an ArrayList.
   	*
   	* @param line ArrayList with multiple String objects needed to be split
   	* @param delineator character by which to split Strings
   	* @return new ArrayList containing one word per index
   	*/
      public static ArrayList<String[]> splitLines(ArrayList<String> line, String delineator)	{
      //Creates ArrayList the size of line
         ArrayList<String[]> result = new ArrayList<String[]>(line.size());
      // Go through each String in line
         for(int i = 0; i < line.size(); i++)	{
         // String[] temp stores values of subStrings split by " "
            String[] temp = line.get(i).split(delineator);
         // Adds all of them to result
            result.add(temp);
         }
         return result;
      }
      
   	/**
   	* Takes an ArrayList full of Strings and concatenates 
   	* every object into one String.
   	*
   	* @param file ArrayList of Strings
   	* @return String of all objects
   	*/
      public static String makeOne(ArrayList<String> file)	{
         int i = 0;
         String result = "";
         for(String line : file)	{
            result += line;
         }
         return result;
      }
   }