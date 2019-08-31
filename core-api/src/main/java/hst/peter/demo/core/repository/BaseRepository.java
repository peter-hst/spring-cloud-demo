package hst.peter.demo.core.repository;


import hst.peter.demo.core.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author peter.huang
 * @date 2019/8/31 18:24
 */

public interface BaseRepository<T extends Module> extends JpaRepository<T, Long> {
    Integer deleteByIdIn(List<Long> idList);
}
