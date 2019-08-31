package hst.peter.demo.film.controller;

import hst.peter.demo.core.controller.CrudController;
import hst.peter.demo.film.domain.Actor;
import hst.peter.demo.film.repository.ActorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter.huang
 * @date 2019/9/1 7:49
 */

@RestController
@RequestMapping("/actor")
public class ActorController extends CrudController<ActorRepository, Actor> {

    private ActorRepository actorRepository;

    public ActorController(ActorRepository repository) {
        super(repository);
        this.actorRepository = repository;
    }
}
