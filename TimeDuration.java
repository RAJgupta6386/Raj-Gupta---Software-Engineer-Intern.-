
package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final long milliseconds;

    public TimeDuration(String input) {
        super(input);
        input = input.toLowerCase();
        if (input.endsWith("ms")) {
            milliseconds = Long.parseLong(input.replace("ms", ""));
        } else if (input.endsWith("s")) {
            milliseconds = Long.parseLong(input.replace("s", "")) * 1000;
        } else {
            milliseconds = 0;
        }
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
