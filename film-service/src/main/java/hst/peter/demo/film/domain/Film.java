package hst.peter.demo.film.domain;

import hst.peter.demo.core.domain.Module;
import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author peter.huang
 * @date 2019/8/31 22:33
 */

@Entity(name = "tbl_film")
@Data
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Film extends Module {

    @Column(length = 64)
    private String name; // 片名

    private Integer duration; // 影片时长

    private Integer star; // 推荐星级

    @Column(columnDefinition = "text")
    private String intro; //影片介绍

    private LocalDate releaseDate; //上映日期

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "tbl_film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    List<Actor> actors;
}
