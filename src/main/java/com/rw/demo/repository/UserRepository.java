package com.rw.demo.repository;

import com.rw.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 */

@Repository
public class UserRepository {

    /**
     * 采用内存存储的方式
     */
    private ConcurrentHashMap<Integer, User> repository = new ConcurrentHashMap<>();

    /**
     * id生成器
     */
    private final static AtomicInteger idGenerator = new AtomicInteger();

    /**
     * @param user
     * @return
     */
    public boolean save(User user) {
        //id 从1开始
        Integer id = idGenerator.incrementAndGet();
        //设置id
        user.setId(id);
        //put返回null说明返回成功
        return repository.put(id, user) == null;
    }

    /**
     * 返回所有用户列表
     * @return
     */
    public Collection<User> findAll(){
        return repository.values();
    }

}
