package hst.peter.demo.film.controller;

import hst.peter.demo.core.controller.CrudController;
import hst.peter.demo.core.vo.Result;
import hst.peter.demo.film.domain.Film;
import hst.peter.demo.film.feign.member.MemberFeignClient;
import hst.peter.demo.film.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author peter.huang
 * @date 2019/9/1 7:43
 */
@RestController
@RequestMapping("/film")
@Slf4j
public class FilmController extends CrudController<FilmRepository, Film> {
    private FilmRepository filmRepository;

    /**
     * Member Feign Client Api 接口
     */
    @Autowired
    private MemberFeignClient memberFeignClient;

    public FilmController(FilmRepository repository) {
        super(repository);
        this.filmRepository = repository;
    }

    /**
     * 测试调用member-service的findById
     *
     * @param id
     * @return
     */
    @GetMapping("/feign/findById/{id}")
    public Result feignFindById(@PathVariable Long id) {
        return Result.ok(memberFeignClient.findById(id));
    }

    /**
     * Feign GET多参数需要使用map 或者 每个参数加@RequestParam("xxx")
     *
     * @param map
     * @return
     */
    @GetMapping("/feign/findPage")
    public Result feignFindPage(@RequestParam HashMap<String, Object> map) {
        return Result.ok(memberFeignClient.index(map));
    }
}
