package hst.peter.demo.core.vo;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author peter.huang
 * @date 2019/8/31 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PageVO implements Serializable {

    @NotNull(message = "请填写页码")
    private Integer page;
    @NotNull(message = "请填写分页行数")
    private Integer size;
    private String[] sort = {"createdTime"};
    private String order = "desc";

    public PageRequest pageRequest() {
        return PageRequest.of((page - 1) * size, size, Sort.by(Sort.Direction.fromString(order), sort));
    }
}
