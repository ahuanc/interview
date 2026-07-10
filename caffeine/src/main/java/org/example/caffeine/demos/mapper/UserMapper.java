package org.example.caffeine.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.caffeine.demos.web.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
