package com.hisense.hiutbd.platform.user.serviceimpl;

import com.hisense.hiutbd.platform.base.persistence.BaseMapper;
import com.hisense.hiutbd.platform.base.domain.BaseBo;
import com.hisense.hiutbd.platform.user.service.BaseService;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public abstract class AbstractBaseService<T extends BaseBo> implements BaseService<T> {
    @Autowired
    BaseMapper<T> baseMapper;

    @Override
    public Either<Exception, Boolean> create(T bo) {
        if (bo.getName() != null && baseMapper.find(bo.getName()) == null) {
            logger().info("Creating: " + bo);
            return doAction(() -> baseMapper.create(bo));
        }
        return Either.left(new Exception());
    }


    public Either<Exception, Boolean> update(T bo) {
        logger().info("Updating: " + bo);
        return doAction(() -> find(bo.getId()).map(s -> baseMapper.update(bo)).getOrElse(false));
    }

    public Option<T> find(String id) {
        logger().debug("Finding: " + id);
        return doAction(() -> baseMapper.find(id)).toOption().flatMap(Option::of);
    }

    public Option<T> find(Long id) {
        return doAction(() -> baseMapper.find(id)).toOption().flatMap(Option::of);
    }

    public List<T> findAll() {
        return doAction(()->List.ofAll(baseMapper.findAll())).get() ;
    }

    public Either<Exception, Boolean> delete(List<Long> ids) {
        logger().info("Deleting: " + ids);
        return doAction(() -> baseMapper.delete(ids.toJavaList()));
    }

    public Either<Exception, Boolean> deleteAll() {
        return doAction(() -> baseMapper.deleteAll());
    }
}
