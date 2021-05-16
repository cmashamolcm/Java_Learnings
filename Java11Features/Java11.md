##Java 11:
****
1. Long Term Support
2. Paid if we are using in commercial purpose. But can use for development, testing etc.
###Major Features:
****
1. New methods in String.
2. Epsilon GC - new commer in GC side
3. java FileName.java does javac also along with it.
4. Files imporved to read/ write Strings
5. Collection's toArray() method
6. var can be used inside Lambda
7. Nestbased AccessControl - Helps to access private variables in reflection
8. HttpClient improvements
 

1.  New String Methods: lines(), strip(), isBlank():
    * str.lines(); - returns a stream by splitting in new line \n
    * str.strip(); - cuts blank spaces at the end. If entire string is of blank space, after stripping, empty string comes of size 0.
    * str.isBlank(); - checks if string is empty.
                        isBlank() checks if length of String without spaces is 0.
                        isEmpty() checks if simple length of string is 0.
2. Files:
   * String str = Files.readString(Path.of());
   * Files.writeString(Path.of(), str); 
3. toArray:
   Integer[] a = list.toArray(new Integer[]);
4. Var inside Lambda:
   (int a) -> {
   var b = a;// allowed to use var inside lambda from Java 11 own wards. Not only in methods as local variables
   System.out.println(b);
   };
   