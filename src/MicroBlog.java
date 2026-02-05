import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

class MicroBlog {
    public String truncate(String input) {
        return input.substring(0, input.offsetByCodePoints(0, Math.min(input.codePointCount(0, input.length()), 5)));
    }
}

class RunMicroBlog {
    public static void main(String[] args) {
        MicroBlog mb = new MicroBlog();
        System.out.println(mb.truncate("💖✔️👨‍💻😀🗑️😕"));
    }
}