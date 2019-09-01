package hst.peter.demo.film.feign.member;

import hst.peter.demo.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member")
@RequestMapping("/member")
public interface MemberFeignClient {
    @GetMapping("/{id}")
    Result findById(@PathVariable("id") Long id);

    @GetMapping
    Result index(@RequestParam Map<String, Object> map);
}
