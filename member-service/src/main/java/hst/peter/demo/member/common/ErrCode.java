package hst.peter.demo.member.common;

public enum ErrCode {
    EXCEPTION(500);

    ErrCode(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
