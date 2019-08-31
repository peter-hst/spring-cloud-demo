package hst.peter.demo.core.controller;

import hst.peter.demo.core.domain.Module;
import hst.peter.demo.core.repository.BaseRepository;
import hst.peter.demo.core.vo.DG;
import hst.peter.demo.core.vo.PageVO;
import hst.peter.demo.core.vo.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author peter.huang
 * @date 2019/8/31 18:01
 */
public abstract class CrudController<T extends BaseRepository<M>, M extends Module> {
    private T repository;

    public CrudController(T repository) {
        this.repository = repository;
    }

    @GetMapping
    public Result index(@Valid PageVO page) {
        final Page<M> p = repository.findAll(page.pageRequest());
        return Result.ok(DG.<M>builder().total(p.getTotalElements()).rows(p.getContent()).build());
    }

    protected M saveOrUpdateBefore(M m) {
        return m;
    }

    @PostMapping
    public Result saveOrUpdate(@Valid @RequestBody M m) {
        m = saveOrUpdateBefore(m);
        if (null == m.getId()) {
            m.setCreatedTime(LocalDateTime.now());
        } else {
            m.setUpdatedTime(LocalDateTime.now());
        }
        return saveOrUpdateAfter(Result.ok(repository.save(m)));
    }

    protected Result saveOrUpdateAfter(Result result) {
        return result;
    }

    protected Long deleteBefore(Long id) {
        return id;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        repository.deleteById(deleteBefore(id));
        return Result.ok("删除成功");
    }

    protected Result deleteAfter(Result result) {
        return result;
    }

    protected List<Long> deleteBefore(List<Long> idList) {
        return idList;
    }

    @DeleteMapping
    public Result delete(@RequestBody List<Long> idList) {
        return deleteAfter(Result.ok(repository.deleteByIdIn(deleteBefore(idList)), "删除成功"));
    }
}
