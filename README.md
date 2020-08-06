# 多线程

![多线程和并发类](http://processon.com/chart_image/5f2b5f0e637689313abdf36c.png)

## 作业

1（5分）

用多线程 模拟 秒杀场景。传统规则是 时间到点 后，所有线程来抢商品。在这里 改为 所有线程 ready 好了，就开始抢。

交定金的100个认筹客户，在所有客户准备好的时候，同时来抢10套房子

10套房子，100认筹购房者来抢房子

用程序实现抢房过程



2（5分）

方舟中，Presto JDBC Connection数是固定数量的（假设为3，同一时间最多接受3个请求）

客户每请求一次分析模型就是需要用到一个 connection，查询完成就会释放该连接。

题目：假设陆续有100个请求同时发起，怎么控制在同一时间的Presto查询的并发量为3？

用程序实现控制过程



3(不考核不记分）

数据结构&多线程&并发容器&锁：

hashmap 的底层数据结构，put 和 get 的 底层怎么实现的 为什么? HashMap是否是线程安全 -》Hashtable-〉ConcurrentHashMap -》 ConcurrentHashMap的原理（JDK6 - JDK8) -> synchronized -》 CAS 无锁算法