package com.feliz.memo.service.Impl;

import com.feliz.memo.bean.Memo;
import com.feliz.memo.mapper.MemoMapper;
import com.feliz.memo.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public Memo selectMemoById(Integer id) {
        Memo memo = memoMapper.findMemoById(id);
        return memo;
    }

    @Override
    public Memo[] selectMemos(Integer userId) {
        Memo[] memos = memoMapper.findMemos(userId);
        return memos;
    }


    @Override
    public Memo[] search(Memo memoData,String time,String date) {

        List<Memo> memos = memoMapper.searchMemo(memoData);
        System.out.println(memoData.toString());
        ArrayList<Memo> searchMemo;
        if (!date.isEmpty() && !time.isEmpty()) {
            searchMemo = new ArrayList<Memo>();
            for (Memo m : memos) {
                String st = m.getStartTime();
                String et = m.getEndTime();
                String d = m.getDate();
                if (inDate(date,d) == 1 && inTime(time,st,et)==1) {
                    searchMemo.add(m);
                }
            }
        }else if (!time.isEmpty()) {
            searchMemo = new ArrayList<Memo>();
            for (Memo m : memos) {
                String st = m.getStartTime();
                String et = m.getEndTime();
                if (inTime(time,st,et)==1) {
                    searchMemo.add(m);
                }
            }
        }else if (!date.isEmpty()){
            searchMemo = new ArrayList<Memo>();
            for (Memo m : memos) {
                String d = m.getDate();
                if (date.equals(d)) {
                    searchMemo.add(m);
                }
            }
        } else {
            return memos.toArray(new Memo[0]);
        }

        if (searchMemo == null) {
            return null;
        }else {
            return searchMemo.toArray(new Memo[0]);
        }
    }

    @Override
    public int insertMemo(Memo memo) {
        return memoMapper.insertMemo(memo);
    }

    @Override
    public int updateMemo(Memo memo) {
        memoMapper.updateMemo(memo);
        return 0;
    }

    @Override
    public int deleteMemoById(Integer id) {
        memoMapper.deleteMemoById(id);
        return 0;
    }

    /**
     * 判断time是否在startTime和endTime之间
     * @param time
     * @param startTime
     * @param endTime
     * @return 1:在;0:不在
     */
    private int inTime(String time,String startTime, String endTime) {
        int timeHour = Integer.parseInt(time.split(":")[0]);
        int timeMin = Integer.parseInt(time.split(":")[1]);
        int startTimeHour = Integer.parseInt(startTime.split(":")[0]);
        int startTimeMin = Integer.parseInt(startTime.split(":")[1]);
        int endTimeHour = Integer.parseInt(endTime.split(":")[0]);
        int endTimeMin = Integer.parseInt(endTime.split(":")[1]);

        if (timeHour >= startTimeHour && timeHour <= endTimeHour) {
            if (timeMin >= startTimeMin && timeMin <= endTimeMin) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 判断d1与d2年月日是否相等
     * @param date1
     * @param date2
     * @return 1:相等;0:不相等
     */
    private int inDate(String date1, String date2) {

        SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        try {
            //格式化时间
            Date d1 = dateFormat1.parse(date1);
            date2 = date2.replace("GMT", "").replaceAll("\\(.*\\)", "");
            Date d2 = dateFormat2.parse(date2);

            if (d1.equals(d2)) {
                return 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
