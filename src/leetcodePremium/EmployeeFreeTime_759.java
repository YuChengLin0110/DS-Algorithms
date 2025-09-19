package leetcodePremium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.) Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
Example 1:
Input: schedule = [[[1,2],[5,6]], [[1,3]], [[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf]. We discard any intervals that contain inf as they aren't finite.
Example 2:
Input: schedule = [[[1,3],[6,7]], [[2,4]], [[2,5],[9,12]]]
Output: [[5,6],[7,9]]
Constraints:

1 <= schedule.length, schedule[i].length <= 50

0 <= schedule[i].start < schedule[i].end <= 10^8
 * */

//Definition for an Interval
class Interval {
    public int start;
    public int end;

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
    
    @Override
    public String toString() {
        return "[" + start + "," + end + "]";
    }
}

public class EmployeeFreeTime_759 {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // 存放所有員工的區間
        List<Interval> intervals = new ArrayList<>();
        // 存放合併後的區間
        List<Interval> merge = new ArrayList<>();

        List<Interval> result = new ArrayList<>();
        
        // 把 schedule 攤平成一個 list
        for (List<Interval> employee : schedule) {
            for (Interval interval : employee) {
                intervals.add(interval);
            }
        }
        
        // 依開始時間排序
        intervals.sort((a, b) -> a.start - b.start);
        
        // 合併區間
        Interval prev = intervals.get(0);
        
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            if (curr.start <= prev.end) {
                prev.end = Math.max(curr.end, prev.end);
            } else {
                merge.add(prev);
                prev = curr;
            }
        }
        
        // 最後一個區間記得要加進去
        merge.add(prev);
        
        // 找出合併後相鄰區間的空閒時間
        prev = merge.get(0);

        for (int i = 1; i < merge.size(); i++) {
            Interval curr = merge.get(i);
            
            // 空閒時間就是前一個結束 到 下一個開始
            Interval freeTime = new Interval(prev.end, curr.start);

            result.add(freeTime);

            prev = curr;

        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 2), new Interval(5, 6)));
        schedule.add(Arrays.asList(new Interval(1,3)));
        schedule.add(Arrays.asList(new Interval(4,10)));
        
        EmployeeFreeTime_759 freeTime = new EmployeeFreeTime_759();
        
        List<Interval> result = freeTime.employeeFreeTime(schedule);
        
        for(Interval interval : result) {
            System.out.println(interval);
        }
    }

}
