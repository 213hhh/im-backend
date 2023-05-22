package com.wxy.imback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxy.imback.model.entity.User;
import com.wxy.imback.model.vo.FriendVO;
import com.wxy.imback.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【user(user)】的数据库操作Mapper
* @createDate 2023-05-18 20:31:26
* @Entity generator.User
*/
public interface UserMapper extends BaseMapper<User> {


    User selectByMail(@Param("mail") String mail);

    List<FriendVO> selectFriendAddList(@Param("user_id") Integer userId);

    void checkFriendById(@Param("id") Integer id);
}




