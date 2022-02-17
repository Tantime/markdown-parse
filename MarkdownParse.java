// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1) {
                throw new NoSuchElementException("Could not find open bracket! \n Expected link format: [link](link.com)");
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextOpenBracket == -1) {
                throw new NoSuchElementException("Could not find closed bracket! \n Expected link format: [link](link.com)");
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(nextOpenBracket == -1) {
                throw new NoSuchElementException("Could not find open parenthesis! \n Expected link format: [link](link.com)");
            }
            int closeParen = markdown.indexOf(")", openParen);
            if(nextOpenBracket == -1) {
                throw new NoSuchElementException("Could not find closed parenthesis! \n Expected link format: [link](link.com)");
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            
            System.out.println(closeParen);
            // System.out.println(currentIndex);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}


// week 7 quiz markdownparse
// // File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.ArrayList;

// public class MarkdownParse {
//     public static ArrayList<String> getLinks(String markdown) {
//         ArrayList<String> toReturn = new ArrayList<>();
//         // find the next [, then find the ], then find the (, then take up to
//         // the next )
//         int currentIndex = 0;
//         while(currentIndex < markdown.length()) {
//             int nextOpenBracket = markdown.indexOf("[", currentIndex);
//             System.out.format("%d\t%d\t%s\n", currentIndex, nextOpenBracket, toReturn);
//             int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
//             int openParen = markdown.indexOf("(", nextCloseBracket);
//             int closeParen = markdown.indexOf(")", openParen);
//             if(nextOpenBracket == -1 || nextCloseBracket == -1
//                   || closeParen == -1 || openParen == -1) {
//                 return toReturn;
//             }
//             String potentialLink = markdown.substring(openParen + 1, closeParen);
//             if(potentialLink.indexOf(" ") == -1 && potentialLink.indexOf("\n") == -1) {
//                 toReturn.add(potentialLink);
//                 currentIndex = closeParen + 1;
//             }
//             else {
//                 currentIndex = currentIndex + 1;
//             }
//         }
//         return toReturn;
//     }
//     public static void main(String[] args) throws IOException {
// 		Path fileName = Path.of(args[0]);
// 	    String contents = Files.readString(fileName);
//         ArrayList<String> links = getLinks(contents);
//         System.out.println(links);
//     }
// }