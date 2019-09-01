package hst.peter.demo.film.feign.member;

import feign.hystrix.FallbackFactory;
import hst.peter.demo.core.vo.DG;
import hst.peter.demo.core.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * fallback = MemberFallBackImpl.class 指定fallBack实现类
 */
// @FeignClient(name = "member", fallback = MemberFeignClient.MemberFallBack.class) //没有工厂类时，使用fallback属性方式
@FeignClient(name = "member", fallbackFactory = MemberFeignClient.MemberFallBackFactory.class)
@RequestMapping("/member")
public interface MemberFeignClient {
    @GetMapping("/{id}")
    Result findById(@PathVariable("id") Long id);

    @GetMapping
    Result index(@RequestParam Map<String, Object> map);

    @Slf4j
    class MemberFallBack implements MemberFeignClient {
        @Override
        public Result findById(Long id) {
            log.error("ribbonTemplateFallBack invoked");
            Map<String, Object> map = new HashMap<>();
            map.put("id", -1L);
            map.put("userName", "默认用户");
            return Result.ok(map);
        }

        @Override
        public Result index(Map<String, Object> map) {
            log.error("ribbonTemplateFallBack invoked");
            return Result.ok(DG.builder().total(-1L).build());
        }
    }

    @Component
    @Slf4j
    class MemberFallBackFactory implements FallbackFactory<MemberFeignClient> {
        private MemberFeignClient memberFeignClient = new MemberFallBack();

        @Override
        public MemberFeignClient create(Throwable throwable) {
            log.error("{}", throwable.getMessage());
            return memberFeignClient;
        }
    }
}

