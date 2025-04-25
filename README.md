# 資料結構與演算法練習專案
此專案主要是用來練習和學習資料結構與演算法

## 目前實作：
### LinkedList 雙向鏈結：

insertAt：在指定位置插入節點

delete：刪除指定節點

deleteAt：刪除指定位置的節點

reverse：反轉

append：在尾部加入節點

### Graph 泛型圖：

addVertex / addEdge：加入節點與邊

DFS（深度優先搜尋）：執行圖的深度優先搜尋

BFS（廣度優先搜尋）：執行圖的廣度優先搜尋

Topological Sort（拓樸排序法）：對有向無環圖進行拓樸排序

### Tree 樹：

maxDepth：找出樹的最大深度

minDepth：找出樹的最小深度

findDepth：查找指定節點的深度

isSameTree：比較兩棵樹是否完全相同

isSymmetric：判斷樹是否對稱

hasPathSum：判斷是否存在從根到葉的路徑，其節點值總和為指定的目標和

pathSum：找出所有從根到葉的路徑，並且這些路徑的節點值總和為指定的目標和

lowestCommonAncestor：尋找兩個節點的最低共同祖先

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

### Trie

insert：插入一個單字到 Trie

contains：檢查 Trie 中是否包含某個單字

startsWith：檢查 Trie 中是否有任何單字以指定的前綴開頭

delete：刪除 Trie 中的某個單字

### Dynamic Programming 動態規劃：

Fibonacci：斐波那契數列，包含遞迴與動態規劃實作

Climbing Stairs：爬樓梯問題，包含遞迴與動態規劃實作

Maximum Subarray：最大子陣列和

Knapsack Problem：0/1 背包問題

## 測試方式：

目前所有測試皆使用 main 方法，後續可視情況改為單元測試 JUnit
