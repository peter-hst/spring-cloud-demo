package hst.peter.demo.film.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Hystrix断路器测试代码
     *
     * @param memberId
     * @return
     */
    // #ref ->  https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica#configuration
    //    @HystrixCommand(fallbackMethod = "ribbonTemplateFallBack")
    @HystrixCommand(fallbackMethod = "ribbonTemplateFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    @HystrixProperty(name="execution.isolation.strategy",value = "SEMAPHORE") //隔离策略-> SEMAPHORE或THREAD 默认THREAD，THREAD满后直接拒绝，而不是放入队列中
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }
    )
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

    public Result ribbonTemplateFallBack(Long memberId) {
        log.error("ribbonTemplateFallBack invoked");
        Map<String, Object> map = new HashMap<>();
        map.put("id", -1L);
        map.put("userName", "默认用户");
        return Result.ok(map);
    }
}
