package hst.peter.demo.film.controller;

import hst.peter.demo.core.controller.CrudController;
import hst.peter.demo.film.domain.Film;
import hst.peter.demo.film.repository.FilmRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter.huang
 * @date 2019/9/1 7:43
 */
@RestController
@RequestMapping("/film")
public class FilmController extends CrudController<FilmRepository, Film> {
    private FilmRepository filmRepository;

    public FilmController(FilmRepository repository) {
        super(repository);
        this.filmRepository = repository;
    }
}
