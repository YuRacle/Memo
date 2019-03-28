package com.feliz.memo.service;

import com.feliz.memo.bean.Memo;
import org.springframework.stereotype.Service;

public interface MemoService {

    /**
     * 查询id查询备忘录
     * @param id
     * @return
     */
    Memo selectMemoById(Integer id);

    /**
     * 根据userId查询该用户所有备忘录
     * @return
     */
    Memo[] selectMemos(Integer userId);

    /**
     * 插入一个备忘录
     * @param memo
     * @return
     */
    int insertMemo(Memo memo);

    /**
     * 修改某个备忘录
     * @param memo
     * @return
     */
    int updateMemo(Memo memo);

    /**
     * 根据id删除某个备忘录
     * @param id
     * @return
     */
    int deleteMemoById(Integer id);

    /**
     * 搜索
     * @param memoData
     * @param time
     * @param date
     * @return
     */
    Memo[] search(Memo memoData,String time,String date);
}
