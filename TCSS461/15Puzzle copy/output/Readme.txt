search: BFS
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 0 Big-O(4^d) + O(1) enqueue + O(1) dequeue

search: BFS
puzzle start state: 51236 849A7BDEFC
goal state reached: 123456789ABCDEF
10,16668,5361,8481
time taken (in ms): 47 Big-O(4^d) + O(1) enqueue + O(1) dequeue

search: DFS
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 1 Big-O(4^m) where m is max depth/depth selected for DLS + O(1) stack push + O(1) stack pop

search: DFS
puzzle start state: 51236 849A7BDEFC
goal state reached: 123456789ABCDEF
19674,60012,19674,25374
time taken (in ms): 121 Big-O(4^m) where m is max depth/depth selected for DLS + O(1) stack push + O(1) stack pop

search: DLS
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 9 Big-O(4^m) where m is max depth/depth selected for DLS + O(1) stack push + O(1) stack pop
depth selected: 5

search: DLS
puzzle start state: 51236 849A7BDEFC
Goal could not be reached by specified depth
depth selected: 11

search: GBFS
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 1 Big-O(4^m) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h1

search: GBFS
puzzle start state: 51236 849A7BDEFC
goal state reached: 123456789ABCDEF
10,32,10,21
time taken (in ms): 1 Big-O(4^m) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h1

search: GBFS
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 1 Big-O(4^m) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h2

search: GBFS
puzzle start state: 51236 849A7BDEFC
goal state reached: 123456789ABCDEF
10,134,43,63
time taken (in ms): 8 Big-O(4^m) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h2

search: AStar
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 2 Big-O(4^d) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h1

search: AStar
puzzle start state: 51236 849A7BDEFC
goal state reached: 123456789ABCDEF
10,32,10,21
time taken (in ms): 1 Big-O(4^d) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h1

search: AStar
puzzle start state: 123456789AB DEFC
goal state reached: 123456789ABCDEF
1,4,1,2
time taken (in ms): 0 Big-O(4^d) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h2

search: AStar
puzzle start state: 51236 849A7BDEFC
goal state reached: 123456789ABCDEF
10,81,26,47
time taken (in ms): 1 Big-O(4^d) + O(log(n)) priority queue enqueue + O(log(n)) priority queue dequeue
Heuristic selected: h2
