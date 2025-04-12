
package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String input) {
        super(input);
        input = input.toUpperCase();
        if (input.endsWith("KB")) {
            bytes = Long.parseLong(input.replace("KB", "")) * 1024;
        } else if (input.endsWith("MB")) {
            bytes = Long.parseLong(input.replace("MB", "")) * 1024 * 1024;
        } else {
            bytes = Long.parseLong(input.replace("B", ""));
        }
    }

    public long getBytes() {
        return bytes;
    }
}
