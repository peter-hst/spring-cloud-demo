package hst.peter.demo.core.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private boolean isOk;
    private Integer code;
    private String msg;
    private Object data;

    public static Result ok() {
        return Result.builder().isOk(true).build();
    }

    public static Result ok(Object data, String msg) {
        return Result.builder().isOk(true).data(data).msg(msg).build();
    }

    public static Result ok(Object data) {
        return Result.builder().isOk(true).data(data).build();
    }

    public static Result fail() {
        return Result.builder().isOk(false).build();
    }

    public static Result fail(Integer code, String msg) {
        return Result.builder().isOk(false).code(code).msg(msg).build();
    }

    public static Result fail(String msg) {
        return Result.builder().isOk(false).msg(msg).build();
    }


}
