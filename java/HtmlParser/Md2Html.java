package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class Md2Html {
    private final int END_CODE = 65535;
    private Reader reader;
    private char curChar = '0';
    private char prevChar;
    private String outputFile;
    private StringBuilder result;
    private Stack<String> tags;
    private String curTagHtml;
    private String curTagMd;
    private final String tagSymbols = "`*_-+~";

    public Md2Html(String input, String output) throws IOException {
        //reader = new BufferedReader(new FileReader(input, StandardCharsets.UTF_8));
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
        outputFile = output;
    }

    public void parse() throws Exception {
        tags = new Stack<>();
        result = new StringBuilder();
        while (true) {
            readChar();
            if (curChar == END_CODE) {
                break;
            }
            else if (curChar == '\n') {
                continue;
            } else {
                parseParagraphOrHeader();
            }
        }
        reader.close();

        Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
        writer.write(result.toString());
        writer.close();
    }

    private void parseParagraphOrHeader() throws Exception {
        headerOrParagraph();
        while (curChar != END_CODE) {
            curTagHtml = testTag();
            if (curTagHtml.equals("")) {
                if (curChar == prevChar && curChar == '\n') { // end of the paragraphOrHeader
                    break;
                } else {
                    switch (curChar) {
                        case '<' : result.append("&lt;"); break;
                        case '>' : result.append("&gt;"); break;
                        case '&' : result.append("&amp;"); break;
                        case '\\' : break;
                        default: result.append(curChar);
                    }
                }
                readChar();
            } else {
                if (tags.empty() || !tags.peek().equals(curTagHtml)) { // open tag
                    if (curChar == ' ' || curChar == '\n') { // * this isn't tag
                        result.append(curTagMd);
                    } else {
                        result.append("<" + curTagHtml + ">");
                        tags.push(curTagHtml);
                    }
                } else { // close tag
                    result.append("</" + curTagHtml + ">");
                    tags.pop();
                }
            }
        }
        if (result.charAt(result.length() - 1) == '\n') { // \n is just for next paragraph
            result.deleteCharAt(result.length() - 1);
        }
        result.append("</" + tags.pop() + ">\n");
        if (!tags.empty()) {
            throw new Exception("You forgot close tag");
        }
    }

    private String testTag() throws Exception {
        if (tagSymbols.contains(curChar + "")) {
            if (prevChar == '\\') {
                result.append(curChar);
                readChar();
                return testTag(); // (\\*++some text++) --> (*<u>some text</u>)
            }
            readChar();
            if (prevChar == curChar) { // tag with two symbols
                readChar();
                switch (prevChar) {
                    case '-' : curTagMd = "--"; return "s";
                    case '_' : curTagMd = "__"; return "strong";
                    case '*' : curTagMd = "**"; return "strong";
                    case '+' : curTagMd = "++"; return "u";
                    default: throw new Exception("Wrong tag: " + curChar + curChar);
                }
            } else {
                switch (prevChar) {
                    case '`' : curTagMd = "`"; return "code";
                    case '*' : curTagMd = "*"; return "em";
                    case '_' : curTagMd = "_"; return "em";
                    case '~' : curTagMd = "~"; return "mark";
                    case '-' : result.append("-"); return ""; // dash, not tag
                    case '+' : result.append("+"); return ""; // just +, not tag
                    default: return "";
                }
            }
        } else {
            if (prevChar == '\\') { // real \
                result.append("\\");
            }
            return ""; // not tag
        }
    }


    private void headerOrParagraph() throws IOException {
        int levelOfHeader = 0;
        String startTag;
        while (curChar == '#') {
            levelOfHeader++;
            readChar();
        }
        if (levelOfHeader == 0 || levelOfHeader > 6 || curChar != ' ') {
            result.append("<p>");
            tags.push("p");
            for (int i = 0; i < levelOfHeader; i++) {
                result.append("#");
            }
        } else {
            result.append("<h" + levelOfHeader + ">");
            tags.push("h" + levelOfHeader);
            readChar(); // skip one whitespace
        }
    }

    private void readChar() throws IOException {
        prevChar = curChar;
        curChar = (char) reader.read();
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new Exception("You should give 2 args: input and output files");
        }
        new Md2Html(args[0], args[1]).parse();
    }
}
