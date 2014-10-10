   import java.util.ArrayList;
   import java.io.File;
   import java.util.Random;
   
/**
 * This class represents a Trie which will generate specific to
 * the parameters given, k and the input file. A Trie consists
 * of a null parent node and children. Children can have children.
 * The value of each node will be a character from the input file.
 * Also, each node has frequency field - this means that no two nodes
 * should have the same character value. Allows less than linear time 
 * cost. Banana.
 *
 * @author Michael Pace
 */
   public class Trie{
      
   	/** Holds char value */
      private char element;
      
      /** Array that holds Trie's children */
      private ArrayList<Trie> children = new ArrayList<Trie>();
      
   	/** Trie's parent */
      private Trie parent;
      
   	/** Frequency of current Trie */
      private int frequency = 1;
      
   	/** File to be read. */
      private File file = null;
      
   	/**
   	* Parameterless constructor.
   	*/
      public Trie()	{
      }
   
   	/**
   	* Initializes a trie, setting element value.
   	*
   	* @param element char value 
   	*/
      public Trie(char element) {
         this.element = element;
      }
      
   	/**
   	* Initializes a trie, setting element and parent values.
   	*
   	* @param element char value
   	* @param parent Trie that is parent to current Trie 
   	*/
      public Trie(char element, Trie parent) {
         this.element = element;
         this.parent = parent;
      }
   
   	/**
   	* Initializes a trie, setting element and children.
   	*
   	* @param element char value
   	* @param children ArrayList of Trie children to current Trie  
   	*/
      public Trie(char element, ArrayList<Trie> children) {
         this.element = element;
         this.children = children;
      }
     
      /**
   	* Returns the frequency of current trie.
   	*
   	* @return Frequency of trie
   	*/
      public int getFrequency()	{
         return this.frequency;
      }
      
   	/**
   	* Increments frequency by one.
   	*/
      public void increaseFrequency()	{
         frequency++;
      }
      
   	/** 
   	* Gets char element of trie.
   	*
   	* @return char element of Trie
   	*/
      public char getElement() {
         return this.element;
      }
   
   	/** 
   	* Sets char element of Trie. 
   	*
   	* @param element char value of Trie to be set
   	*/
      public void setElement(char element) {
         this.element = element;
      }
   	
   	/** 
   	* Returns parent of Trie.
   	*
   	* @return parent of current Trie
   	*/
      public Trie getParent() {
         return parent;
      }
   
   	/** 
   	* Set's parent of child
   	*
   	* @param parent Parent Trie that current Trie will be child to
   	*/
      public void setParent(Trie parent) {
         this.parent = parent;
      }
   	
   	/** 
   	* Returns Children of Trie in an ArrayList.
   	*
   	* @return ArrayList containing children
   	*/
      public ArrayList<Trie> getChildren() {
         return children;
      }
      
   	/**
   	* Returns child with certain char in trie.
   	*
   	* @return Trie that contains character
   	*/
      public Trie getChild(char character)	{
      // Must have children to continue
         if(this.hasChildren())	{
         // Loop through each child in ArrayList children
            for(int i = 0; i < children.size(); i++)	{
            // Once element matches character, return child
               if(children.get(i).getElement() == character)	{
                  return children.get(i);
               }
            }
         }// Otherwise, return null
         return null;
      }
      
   	/**
   	* Determines if current Trie has ANY children.
   	*
   	* @return Whether Trie has any children
   	*/
      public boolean hasChildren()	{
         boolean doesExist = true;
      // Checks if arraylist is empty
         if(this.children.isEmpty())	{
         // If arraylist is empty, there are no children
            doesExist = false;
         }// Otherwise, children exist
         return doesExist;
      }
   	
   	/**
   	* Determines if child with certain char exists in Trie.
   	*
   	* @return Whether child exists
   	* @param character char that child must have
   	*/
      public boolean hasSpecificChild(char character)	{
         boolean doesExist = false;
         if(hasChildren())	{
            for(int i = 0; i < children.size(); i++)	{
               if(children.get(i).getElement() == character)	{
                  doesExist = true;
               }
            } 
         }
         return doesExist;
      }
      
   	/**
   	* Goes through each Trie recursively and uses trimToSize() on its 
   	* children ArrayList.
   	*
   	* @param Trie Current Trie to be trimmed of all null values
   	*/
      public static Trie trimChildren(Trie trie)	{
         if(trie.hasChildren())	{
            trie.getChildren().trimToSize();
            for(int i = 0; i < trie.getChildren().size(); i++)	{
               trimChildren(trie.getChildren().get(i));
            }
         }
         return trie;
      }
   	
   	/**
   	* Sets children of parent to a given ArrayList.
   	*
   	* @param children ArrayList of Trie to be children  
   	*/
      public void setChildren(ArrayList<Trie> children) {
         this.children = children;
      }
   
   	/** 
   	* Adds one child to current Trie.
   	*
   	* @param childElement char element of new child
   	*/
      public void addChild(char childElement) {
         this.children.add(new Trie(childElement, this));
      }
      
   	/**
   	* Sort children of ArrayList by their frequency. Returns 
   	* ArrayList of same terms grouped, in accending order. Example, 
   	* if there are six G's, 4 W's and two D's they would be stored
   	* G-G-G-G-G-G-W-W-W-W-D-D.
   	*
   	* @param tree ArrayList containing children needed to be sorted
   	* @return Newly sorted ArrayList of children
   	*/
      public ArrayList<Trie> sortFreq(ArrayList<Trie> tree)	{
         ArrayList<Trie> result = new ArrayList<Trie>();
      // Go through each child
         for(int i = 0; i < tree.size(); i++)	{
         // Set temp equal to each child's frequency
            int temp = tree.get(i).getFrequency();
         // Add the child temp number of times to result
            for(int j = 0; j < temp; j++)	{
               result.add(tree.get(i));
            }
         }
         return result;
      }
   
   	/**
   	 * Creates an ArrayList of complexity k from the input file 
   	 * which is then turned into a Trie. 
   	 *
   	 * @param k length/depth of Strings to be cut
   	 * @param File text file to be read
   	 * @return constructed Trie
   	 */
      public Trie prepTrie(int k, File source)	{
      // Copies file contents into an ArrayList line by line
         ArrayList<String> sourceContents = FileUtil.readFile(source);
      // String to hold lines from text
         String text = "";
         int start = 0;
         int end = k + 1;
      // Add lines from text to line, 
         for (String line : sourceContents)	{
            text += line + " ";
         }
      // Builds trie
         ArrayList<String> leaves = 
            new ArrayList<String>(text.length()/end);
      //gets substrings k + 1 long and adds them to branches
         while (end < text.length()) {
            leaves.add(text.substring(start, end));
            start++;
            end++;
         }
      // Trims all null values
         leaves.trimToSize();
         return this.buildTrie(leaves);
      }
      
      /**
   	* Builds Trie from ArrayList made of complexity k from input
   	* file.
   	*
   	* @return constructed Trie from ArrayList
   	* @param words ArrayList from input file
   	*/
      public static Trie buildTrie(ArrayList<String> words) {
         Trie trie = new Trie();
         char character;
         //For every String in words
         for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
            //get the first character of each String in words
               character = word.charAt(i);
               //If Trie already has a child with that character
               if (trie.hasSpecificChild(character)) {
               //Set temporary Trie equal to child
                  trie = trie.getChild(character);
               // Increase its frequency
                  trie.increaseFrequency();
               }
               else {// Otherwise, just add new child to Trie
                  trie.addChild(character);
                  trie = trie.getChild(character);
               }
            }//Goes back to the top of the Trie
            while (trie.getParent() != null) {
               trie.getChildren().trimToSize();
               trie = trie.getParent();
            }
         }
         return trie;
      }
      
      /**
   	* Generates a String from an input file address of desired length
   	* and complexity, k, from a given Trie.
   	*
   	* @param f File path to text in which result/Trie is made from
   	* @param t Trie used to generate output
   	* @param k Int that describes complexity in which result is made
   	* @param length Length of output
   	* @return Randomly generated result from input source
   	*/
      public char[] getResult(Trie t, int k, int length, String f)	{
         Random r = new Random();
         Trie temp = t;
         ArrayList<String> seedlings = FileUtil.readFile(f);
         ArrayList<Trie> leaves = new ArrayList<Trie>();
      // Make seedlings one long String
         String seed = FileUtil.makeOne(seedlings);
      // Generate random index from input file k long
         int front = r.nextInt(seed.length() - k);
         int back = front + k;
      // Make substring from file
         seed = seed.substring(front, back);
         String result = seed;
      // Accessing the Trie   
         for(int i = 0; i < length; i++)	{
            temp = t;// Keeps track of current node accessed
            for (int j = 0; j < seed.length(); j++) {
            //Look through first order of children
            //find child with char in seed
               temp = temp.getChild(seed.charAt(j));
            }
            try{
               leaves = temp.getChildren();
            }// If temp doesn't have children, set temp to parent
               catch(NullPointerException e)	{
                  temp = temp.getParent();
               }
            leaves = temp.sortFreq(leaves);
         //Generate random int <= leaves and concatenate to seed
            int index = r.nextInt(leaves.size());
            result += leaves.get(index);
            seed = result.substring(result.length() - seed.length());
         }
         return result.toCharArray();
      }
      
   	/**
   	* Returns String representation of Trie.
   	*
   	* @return String representation of Trie.
   	*/
      public String toString()	{
         String result = "" + this.getElement();
         return result;
      }
   }