package com.feliz.memo.mapper;

import com.feliz.memo.bean.Memo;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MemoMapper {

    @Select("select * from memo where id = #{id}")
    Memo findMemoById(Integer id);

    @Select("select * from memo where date = #{date}")
    Memo[] findMemoByDate(String date);

    @Select("select * from memo where user_id = #{userId}")
    Memo[] findMemos(Integer userId);

//    @Select({"<script>",
//            "select * from memo where user_id=#{userId}",
//            "<when test='content != null and #{content}!=\"\"'>",
//            "and content = #{content}",
//            "</when>",
//            "<when test='place != null and #{place}!=\"\"'>",
//            "and place = #{place}",
//            "</when>",
//            "</script>"})
    List<Memo> searchMemo(@Param("memo") Memo memo);

    @Delete("delete from memo where id = #{id}")
    int deleteMemoById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into memo(user_id,content,place,date,start_time,end_time,remind_time) values (#{userId},#{content},#{place},#{date},#{startTime},#{endTime},#{remindTime})")
    int insertMemo(Memo memo);

    @Update("update Memo set content=#{content},place=#{place},date=#{date},start_time=#{startTime},end_time=#{endTime},remind_time=#{remindTime} where id = #{id}")
    int updateMemo(Memo memo);
}
