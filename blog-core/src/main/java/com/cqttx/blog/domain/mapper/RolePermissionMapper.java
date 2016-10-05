package com.cqttx.blog.domain.mapper;

import com.cqttx.blog.domain.entities.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author stc
 * @see Mybatis提供查询数据
 */
@Mapper
public interface RolePermissionMapper {
	
	// 根据角色ID查询所有的角色权限
	@Select("select * from RolePermission whrer roleId = #{roleId}")
	public List<RolePermission> findListRolePermissionByRoleId(Integer roleId);
}
