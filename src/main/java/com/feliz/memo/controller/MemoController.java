package com.feliz.memo.controller;

import com.feliz.memo.bean.Memo;
import com.feliz.memo.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    /**
     * 不存在则添加备忘录，存在则修改
     * @param memo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public int addMemo(@RequestBody Memo memo) {
        if (memo.getId() == 0){
            return memoService.insertMemo(memo);
        }
        return memoService.updateMemo(memo);
    }

    @RequestMapping("/get/memos")
    @ResponseBody
    public Memo[] getMemos(Integer userId) {
        Memo[] memos = memoService.selectMemos(userId);
        return memos;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public int deleteMemo(Integer id) {
        return memoService.deleteMemoById(id);
    }

    @RequestMapping("/search")
    @ResponseBody
    public Memo[] searchMemo(Integer userId,String content,String place,String time,String date) {
        System.out.println("userId:"+ userId+"content:"+content+"place:"+place+"time:"+time+"date:"+date);
        Memo memoData = new Memo();
        if (content.isEmpty()) {
            content = null;
        }
        if (place.isEmpty()) {
            place = null;
        }
        memoData.setUserId(userId);
        memoData.setContent(content);
        memoData.setPlace(place);

        Memo[] memos = memoService.search(memoData, time, date);

        return memos;
    }

}
