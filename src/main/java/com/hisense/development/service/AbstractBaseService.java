package com.hisense.development.service;

import com.hisense.development.dao.BaseDao;
import com.hisense.development.entity.BaseBo;
import com.hisense.development.entity.User;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public abstract class AbstractBaseService<T extends BaseBo> implements BaseService<T> {
    @Autowired
    BaseDao<T> baseDao;

    @Override
    public Either<Exception, Boolean> create(T bo) {
        if (bo.getName() != null && baseDao.find(bo.getName()) == null) {
            logger().info("Creating: " + bo);
            return doAction(() -> baseDao.create(bo));
        }
        return Either.left(new Exception());
    }


    public Either<Exception, Boolean> update(T bo) {
        logger().info("Updating: " + bo);
        return doAction(() -> find(bo.getId()).map(s -> baseDao.update(bo)).getOrElse(false));
    }

    public Option<T> find(String id) {
        logger().debug("Finding: " + id);
        return doAction(() -> baseDao.find(id)).toOption().flatMap(Option::of);
    }

    public Option<T> find(Long id) {
        return doAction(() -> baseDao.find(id)).toOption().flatMap(Option::of);
    }

    public List<T> findAll() {
        return doAction(()->List.ofAll(baseDao.findAll())).get() ;
    }

    public Either<Exception, Boolean> delete(List<Long> ids) {
        logger().info("Deleting: " + ids);
        return doAction(() -> baseDao.delete(ids.toJavaList()));
    }

    public Either<Exception, Boolean> deleteAll() {
        return doAction(() -> baseDao.deleteAll());
    }
}
