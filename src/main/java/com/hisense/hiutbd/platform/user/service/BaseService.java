package com.hisense.hiutbd.platform.user.service;

import com.hisense.hiutbd.platform.Loggable;
import javaslang.Function0;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.control.Try;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public interface BaseService<T> extends Loggable {

    Either<Exception, Boolean> create(T bo);

    Either<Exception, Boolean> update(T bo);

    Either<Exception, Boolean> delete(List<Long> ids);

    Option<T> find(Long id);

    Option<T> find(String name);

    List<T> findAll();

    default public <R> Either<Exception, R> doAction(Function0<R> block) {
        Either<Throwable, R> rs = Either.left(new Exception());
        try {
            Try<R> result = Try.of(() -> {
                R value = block.apply();
                return value;
            });
            rs = result.toEither();
            return rs.mapLeft(t -> new Exception(t));
        } catch (Exception e) {
            logger().info(e.getMessage());
        }
        return rs.mapLeft(t -> new Exception(t));

    }

    Either<Exception, Boolean> deleteAll();

}