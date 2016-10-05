package com.cqttx.blog.domain.mapper;

import com.cqttx.blog.domain.entities.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author stc
 * @see Mybatis提供查询数据
 */
@Mapper
public interface PermissionMapper {

	// 根据ID查询Permission
	@Select("select * from Permission where id = #{id}")
	public Permission findPermissionById(Integer id);
}
