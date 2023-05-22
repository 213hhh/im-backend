package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxy.imback.model.entity.Friend;
import generator.service.FriendService;
import com.wxy.imback.mapper.FriendMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【friend】的数据库操作Service实现
 * @createDate 2023-05-21 20:42:50
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
        implements FriendService {

}




