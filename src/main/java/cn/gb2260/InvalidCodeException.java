package cn.gb2260;

public class InvalidCodeException extends RuntimeException {
    public InvalidCodeException(String s) {
        super(s, new Throwable());
    }
}
