# spark访问本地mysql

* spark版本：2.11
* scala版本：2.12.4

# spark访问hbase

* spark版本：2.11
* scala版本：2.12.4
* hbase版本：1.4.1

# spark sql

* spark版本：2.11
* scala版本：2.11.0

# spark streaming

* spark版本：2.11
* scala版本：2.11.0

# nc使用

* nc -lp 19999 监听19999端口
* netstat -ant | grep 19999 查看19999端口是否启用

# spark streaming kafka

* spark版本：2.11
* scala版本：2.11.0
* kafka版本：2.12-2.0.0

---

# kafka

* 启动服务器
	* 启动zookeeper ``` zookeeper-server-start.sh config/zookeeper.properties ``` 
	* 启动kafka服务器 ``` kafka-server-start.sh config/server.properties ```

* 创建topic
	* 创建主题
		* 创建一个副本，一个副本的主题 ``` kafka-topic.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test ```
	
	* 列出主题
		``` kafka-topic.sh --list --zookeeper localhost:2181 ```

* 发送消息
	``` kafka-console-producer.sh --broker-list localhost:9092 --topic test ```

* 启动消费者
	``` kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning ```
		



