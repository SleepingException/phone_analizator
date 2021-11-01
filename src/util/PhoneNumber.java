package util;

public class PhoneNumber{
    private int prefix;
    private int start;
    private String operator;

    public PhoneNumber(int prefix, int start, String operator) {
        this.prefix = prefix;
        this.start = start;
        this.operator = operator;
    }

    public int getPrefix() {
        return prefix;
    }

    public int getStart() {
        return start;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "prefix=" + prefix +
                ", start=" + start +
                ", operator='" + operator + '\'' +
                '}';
    }
}
