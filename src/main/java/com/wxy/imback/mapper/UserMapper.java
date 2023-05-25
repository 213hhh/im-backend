package com.wxy.imback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxy.imback.model.entity.User;
import com.wxy.imback.model.vo.FriendListVO;
import com.wxy.imback.model.vo.FriendAuditVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【user(user)】的数据库操作Mapper
 * @createDate 2023-05-18 20:31:26
 * @Entity generator.User
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱查询用户信息
     * @param mail
     * @return
     */
    User selectByMail(@Param("mail") String mail);


    /**
     * 查询好友列表
     * @param userId
     * @return
     */
   List<FriendListVO> selectFriendList(@Param("user_id") Long userId);
}




