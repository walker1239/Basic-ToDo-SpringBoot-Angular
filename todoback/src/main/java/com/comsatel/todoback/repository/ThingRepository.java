package com.comsatel.todoback.repository;

import java.util.List;

import com.comsatel.todoback.models.Thing;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ThingRepository {
    @Select("select * from todo")
    public List <Thing> findAll();

    @Select("SELECT * FROM todo WHERE id = #{id}")
    public Thing findById(long id);

    @Delete("DELETE FROM todo WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("INSERT INTO todo(id, description, state) " +
        " VALUES (#{id}, #{description}, #{state})")
    public int insert(Thing thing);

    @Update("Update todo set description=#{description}, " +
        " state=#{state} where id=#{id}")
    public int update(Thing thing);
}
