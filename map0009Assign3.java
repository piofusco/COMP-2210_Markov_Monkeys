   import java.util.ArrayList;
   import java.util.Random;
   import java.io.IOException;
   import java.io.File;

 /**
 * Main class for Assignment 3. A group of monkeys inside this
 * program are going to randomly generate text based on input files
 * and write them to desire output files to a complexity of k. Banana.
 *
 * @author Michael Pace
 */
   public class map0009Assign3	{
   
    /**
    * Main method for Assignment 3. Based on contents of args[] 
    * constructs Trie from input file of complexity k, then builds 
    * result file individually. Uses Trie class data structure to
    * build seed and generate text. Uses FileUtil class to read/write
    * text to files.
    *
    * @param args Users defined arguments
    */
      public static void main(String[] args)	{
         FileUtil opener = null;
      // Determine if args[] is empty
         if(args.length == 0)	{
            parseArgs();
         }
         else{//Otherwise, evaluate args one at a time
            evalArgs(args);
         }  
      }
      
     /**
     * Utility method that takes an ArrayList<String> of args and evaluates each
     * group of terms. Returns Array of created File objects from args.
     *
     * @param arguments ArrayList holding String args[] from main()
     * @return ArrayList of output files
     */
      public static ArrayList<File> parseArgs()	{
         FileUtil opener = null;
         try	{
            opener = new FileUtil("bananas.txt");
         }
            catch(IOException e)	{// If file DNE
               System.out.print("File does not exist.");
               System.exit(0);
            } 
      //Add each line of "banana.txt" to ArrayList
         ArrayList<String> temp = opener.readFile();
      //Add each argument to one ArrayList, split by " "
         ArrayList<String[]> args = FileUtil.splitLines(temp, " ");
         ArrayList<File> result = new ArrayList<File>(args.size());
      //For each String[] in args, evalArgs and add to result
         for(String[] x : args)	{
            result.add(evalArgs(x));
         }
         return null;
      }
      
   	/**
   	* Evaluates Array of String assuming that the first
   	* is k, the second is length, the third is the input
   	* file and the last is the output file. Assumes array
   	* is not null. 
   	*
   	* @param arguments Array of Strings from main()
   	* @return File output containing generated result
   	*/
      public static File evalArgs(String[] arguments)	{
         FileUtil opener = null;
      //First is k
         int k = Integer.parseInt(arguments[0]);	
         if(k < 0)	{
            System.out.print("Invalid value. K must be > 0.");
            System.exit(0);
         }//Second is length
         int length = Integer.parseInt(arguments[1]);
         if(length < 0)	{
            System.out.print("Invalid value. Length must be > 0.");
            System.exit(0);
         }//Third is input file
         String input = arguments[2];
         try	{
            opener = new FileUtil(input);
         }
            catch(IOException e)	{//Make sure it exists
               System.out.print("Input file does not exist.");
               System.exit(0);//Otherwise exit
            }
      //Make sure k isn't > size of input
         if(map0009Assign3.validate(k, input))	{
            System.out.print("WHAT IN TARNATION! K cannot be > the size"
               + " of the text file!");
            System.exit(0);
         }
      //Create trie and result
         Trie t = new Trie();
         File inFile = FileUtil.createFile(input);
         t = t.prepTrie(k, inFile);
         char[] result = t.getResult(t, k, length, input);
         String output = arguments[3];//Fourth is output file name
         File outFile = FileUtil.createAndWriteToFile(output, result);
         return outFile;
      }
      
      /**
   	* Validates whether source file is longer than int k.
   	*
   	* @param k Complexity of seed
   	* @param file String representation of file path
   	* @return True/False whether k > file
   	*/
      public static boolean validate(int k, String f)	{
         String input = FileUtil.createFileString(f, k);
         if(k > input.length())	{
            return true;
         }
         return false;
      }
   }
