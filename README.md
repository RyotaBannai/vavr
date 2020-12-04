# vavr
The playground for Vavr.
- [official docs](https://docs.vavr.io/)
- `sorted sets is` backed by `binary tree structures`. `a sorted map` is `a sorted set containing key-value pairs and having an ordering for the keys`.
- `HashMap` is backed by [`hash Array Mapped Trie(HAMT)`](http://lampwww.epfl.ch/papers/idealhashtrees.pdf) and HashSet is backed by `a HAMT containing key-key pairs`.

#### Queue
- A Queue internally consists of `two linked lists`, `a front List`, and `a rear List`. 
  - The front List contains the elements that are dequeued
  - the rear List contains the elements that are enqueued.
  - (This allows enqueue and dequeue operations to perform in O(1). When the front List runs out of elements, front and rear List's are swapped, and the rear List is reversed.)
  