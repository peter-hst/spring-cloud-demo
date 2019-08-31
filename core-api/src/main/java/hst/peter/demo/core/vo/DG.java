package hst.peter.demo.core.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author peter.huang
 * @date 2019/8/31 16:32
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DG<T> implements Serializable {
    private Long total;
    private List<T> rows;
}
