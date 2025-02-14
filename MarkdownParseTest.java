import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void getLinks() throws IOException {
        Path fileName = Path.of("test-file.md");
        System.out.println(fileName.toAbsolutePath());
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }

    @Test
    public void getLinks2() throws IOException {
        Path fileName = Path.of("test-file2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of(""), links);
    }

    @Test
    public void getLinks3() throws IOException {
        Path fileName = Path.of("test-file3.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("Could not find open bracket!" + 
            "\n Expected link format: [link](link.com)"), links);
    }

    @Test
    public void getLinks4() throws IOException {
        Path fileName = Path.of("test-file4.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(List.of("Could not find open parenthesis!" +
            "\n Expected link format: [link](link.com)"), links);
    }

    // week 7 quiz tests
    // @Test
    // public void testSingleImage() throws IOException {
    //     Path fileName = Path.of("./test-single-image.md");
    //     String contents= Files.readString(fileName);
    //     List<String> expect = List.of();
    //     assertEquals(MarkdownParse.getLinks(contents), expect);
    // }

    @Test
    public void testLinkAtBeginning() {
        String contents= "[link title](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSpaceInURL() {
        String contents = "[title](space in-url.com)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testSpaceAfterParen() {
        String contents = "[title]( space-in-url.com)";
        List<String> expect = List.of("space-in-url.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void testSpaceBeforeParen() {
        String contents = "[title]   (should-not-count.com)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testMissingCloseParen() {
        String contents = "[link title](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testSnippet1() throws IOException {
        Path fileName = Path.of("snippet1.md");
        System.out.println(fileName.toAbsolutePath());
        String contents = Files.readString(fileName);
        List<String> expect = List.of("google.com", "google.com", "ucsd.edu");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testSnippet2() throws IOException {
        Path fileName = Path.of("snippet2.md");
        System.out.println(fileName.toAbsolutePath());
        String contents = Files.readString(fileName);
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testSnippet3() throws IOException {
        Path fileName = Path.of("snippet3.md");
        System.out.println(fileName.toAbsolutePath());
        String contents = Files.readString(fileName);
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
}