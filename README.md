# 資料結構與演算法練習專案
此專案主要是用來練習和學習資料結構與演算法

## 目前實作：
### LinkedList 雙向鏈結：

插入節點（insertAt）

刪除節點（delete, deleteAt）

反轉    （reverse）

加入節點（append）

### Graph 泛型圖：

加入節點與邊

深度優先搜尋（DFS）

廣度優先搜尋（BFS）

拓樸排序法   (Topological Sort)

### Tree 樹：

最大深度（maxDepth）

最小深度（minDepth）

查找節點深度（findDepth）

比較兩棵樹是否相同（isSameTree）

判斷是否對稱（isSymmetric）

判斷是否存在目標和路徑（hasPathSum）

找出所有符合目標和的路徑（pathSum）

尋找兩節點的最低共同祖先（lowestCommonAncestor）

### Stack 與 Queue：

皆使用 Deque 來實作

QueueWithStacks：使用兩個 Stack 實作 Queue

MinStack<T>：支援泛型，使用 Comparable<T> 進行比較，取得當前最小值

ValidParentheses：括號比對問題，利用 Stack 判斷是否有效配對

StackWithQueues：使用兩個 Queue 實作 Stack

MaxSlidingWindow：使用 Deque 雙端特性，實作滑動視窗最大值

ReverseQueue：使用 Stack 反轉 Queue

### Sorting 排序演算法

Bubble Sort：冒泡排序

Insertion Sort：插入排序

Selection Sort：選擇排序

Merge Sort：合併排序

Quick Sort：快速排序

## 測試方式：

目前所有測試皆使用 main 方法，後續可視情況改為單元測試 JUnit
