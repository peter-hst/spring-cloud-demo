package hst.peter.demo.film.controller;

import hst.peter.demo.core.controller.CrudController;
import hst.peter.demo.core.vo.Result;
import hst.peter.demo.film.domain.Film;
import hst.peter.demo.film.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
     * ribbon restTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * ribbon 负载均衡
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public FilmController(FilmRepository repository) {
        super(repository);
        this.filmRepository = repository;
    }

    @GetMapping("/ribbon/{id}")
    public Result ribbonTemplate(@PathVariable("id") Long memberId) {
        return this.restTemplate.getForObject("http://member/member/" + memberId, Result.class);
    }

    @GetMapping("/ribbon/instance")
    public Result ribbonLoadInstance() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("member");
        log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
        return Result.ok(serviceInstance);
    }
}
