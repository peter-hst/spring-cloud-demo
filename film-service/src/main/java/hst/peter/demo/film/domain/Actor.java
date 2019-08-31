package hst.peter.demo.film.domain;

import hst.peter.demo.core.domain.Module;
import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.util.List;

/**
 * @author peter.huang
 * @date 2019/8/31 22:54
 */

@Entity(name = "tbl_actor")
@Data
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Actor extends Module {
    private String name;
    private String country;
    private String summary;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "tbl_film_actor",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> films;
}
