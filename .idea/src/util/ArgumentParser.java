package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentParser {
    static final Pattern ASSIGNMENT_RE = Pattern.compile("(^.*)=(.*)$");
    static final Pattern INTEGER_RE = Pattern.compile("0 | [1-9][0-9]*");
    static final Pattern REAL_ISH_RE = Pattern.compile("(0 | [1-9][0-9]*)(\\.[0-9]*)?");

    void parse(ArgSpec[] in_args, LineBuffer lines) {
        HashMap<String, ArgSpec> args = new HashMap<String, ArgSpec>();

        for (int i = 0; i < in_args.length; i++)
            args.put(in_args[i].parse_name, in_args[i]);

        for (int i = 0; i < lines.lines.length; i++) {
            Matcher m = ASSIGNMENT_RE.matcher(lines.lines[i]);
            String name = m.group(1);
            String value = m.group(2);
            ArgSpec arg = args.get(name);

            if (arg.parsed_value instanceof Integer) {
                m = INTEGER_RE.matcher(value);
                arg.parsed_value = Integer.parseInt(m.group());
            } else if (arg.parsed_value instanceof Float) {
                m = REAL_ISH_RE.matcher(value);
                arg.parsed_value = Float.parseFloat(m.group());
            } else if (arg.parsed_value instanceof Double) {
                m = REAL_ISH_RE.matcher(value);
                arg.parsed_value = Double.parseDouble(m.group());
            } else
                throw new RuntimeException("Unimplemented ArgSpec type.");
        }
    }
}
