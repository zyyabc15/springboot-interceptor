package com.oo.interceptor.service;

import com.oo.interceptor.entity.UserEntity;
import com.oo.interceptor.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName
 * @Description TODO
 * @Author zhaoyuanyuan-ds
 * @Date 2018/8/1 17:51
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserJpa userJpa;

    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response)throws Exception{
        Specification<UserEntity> specification = new Specification<UserEntity>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"),userEntity.getName()));
                return null;
            }
        };
       UserEntity user = userJpa.findOne(specification).get();
        if(user==null){
            return "用户不存在，登陆失败！";
        }else if(!userEntity.getPwd().equals(user.getPwd())){
            return "密码错误！";
        }else {
            request.getSession().setAttribute("_session_user",userEntity);
            return "登录成功！";
        }

    }
}
