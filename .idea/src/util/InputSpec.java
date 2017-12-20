package util;

import java.lang.reflect.InvocationTargetException;

public class InputSpec {
    public static class Atom {
        public Object value;

        public Atom(int value) {
            this.value = new Integer(value);
        }

        public Atom(float value) {
            this.value = new Float(value);
        }

        public Atom(double value) {
            this.value = new Double(value);
        }
    }

    public String parse_name;
    public String prompt;
    public Atom[] parsed_values;

    public InputSpec(String parse_name, String prompt, Atom... parsed_values) {
        init(parse_name, prompt, parsed_values.length);

        for (int i = 0; i < parsed_values.length; i++)
            this.parsed_values[i] = parsed_values[i];
    }

    public InputSpec(String parse_name, String prompt, int... parsed_values) {
        init(parse_name, prompt, parsed_values.length);

        for (int i = 0; i < parsed_values.length; i++)
            this.parsed_values[i] = new Atom(parsed_values[i]);
    }

    public InputSpec(String parse_name, String prompt, float... parsed_values) {
        init(parse_name, prompt, parsed_values.length);

        for (int i = 0; i < parsed_values.length; i++)
            this.parsed_values[i] = new Atom(parsed_values[i]);
    }

    public InputSpec(String parse_name, String prompt, double... parsed_values) {
        init(parse_name, prompt, parsed_values.length);

        for (int i = 0; i < parsed_values.length; i++)
            this.parsed_values[i] = new Atom(parsed_values[i]);
    }

    void init(String parse_name, String prompt, int num_vals) {
        this.parse_name = parse_name;
        this.prompt = prompt;
        this.parsed_values = new Atom[num_vals];
    }
}
