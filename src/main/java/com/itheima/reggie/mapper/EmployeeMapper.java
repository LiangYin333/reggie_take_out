package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * 键盘敲烂,工资过万.
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
