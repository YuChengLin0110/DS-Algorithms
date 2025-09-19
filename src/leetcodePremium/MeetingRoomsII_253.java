package leetcodePremium;

import java.util.Arrays;

/*
 * 253. Meeting Rooms II

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
return the minimum number of conference rooms required.

Example 1:
    Input: intervals = [[0,30],[5,10],[15,20]]
    Output: 2
    
Example 2:
    Input: intervals = [[7,10],[2,4]]
    Output: 1

 * */
public class MeetingRoomsII_253 {
    
    public int minMeetingRooms(int[][] intervals) {
       int[] startTime = new int[intervals.length];
       int[] endTime = new int[intervals.length];
       int maxRooms = 0;
       
       for(int i = 0 ; i < intervals.length ; i++) {
           startTime[i] = intervals[i][0];
           endTime[i] = intervals[i][1];
       }
       
       Arrays.sort(startTime);
       Arrays.sort(endTime);
       
       int rooms = 0;
       int s = 0;
       int e = 0;
       
       for(s = 0 ; s < startTime.length ; s++) {
           if( startTime[s] < endTime[e]) {
               rooms++;
               maxRooms = Math.max(maxRooms, rooms);
           }else {
               rooms--;
               e++;
           }
       }
        
        return maxRooms;
    }
    
    public static void main(String[] args) {
        int[][] intervals = new int[][] {{0,30}, {5, 10}, {15, 20}};
        
        MeetingRoomsII_253 meetingRoom = new MeetingRoomsII_253();
        
        int rooms = meetingRoom.minMeetingRooms(intervals);
        
        System.out.println(rooms);
    }
    
}
