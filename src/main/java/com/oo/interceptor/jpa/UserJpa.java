package com.oo.interceptor.jpa;

import com.oo.interceptor.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName
 * @Description TODO
 * @Author zhaoyuanyuan-ds
 * @Date 2018/8/1 17:49
 * @Version 1.0
 **/
public interface UserJpa extends JpaRepository<UserEntity,Long>,JpaSpecificationExecutor<UserEntity> {
}
