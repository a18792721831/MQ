# [RabbitMqPersistence](https://blog.csdn.net/a18792721831/article/details/93129821)
RabbitMqPersistence:RabbitMq的持久化<br/>
RabbitMq对于Queue中消息的保存方式有disk和RAM两种。<br/>
disk就是把消息写入磁盘。<br/>
RAM不会保存数据。<br/>
disk方式有两种方式触发：<br/>
1. 发布消息时指明需要写入磁盘；<br/>
2. 当消息服务器中内存紧张时，会将部分内存中的消息转移到磁盘。<br/>
disk的实现：<br/>
消息数据会被保存在以.rdq后缀命名的文件中，当文件达到一定的大小
（默认是16777216字节，即16MB）时会生成一个新的文件，当文件
中的已经被删除的消息比例大于阈值时会触发文件合并操作，以提高
磁盘利用率。<br/>
RAM方式：<br/>
只是在RAM中保存内部数据库表数据，而不会保存消息、消息存储索引、
队列索引和其他节点状态等数据。
# 1.Queue持久化
Queue持久化通过设置durable为true来实现的。<br/>
Queue持久化只是持久化了队列，队列里面的消息并不会进行持久化,重启
MQ服务器后，队列因为持久化不会丢失，但是消息却会丢失。
# 2.Message持久化
Message持久化是通过发布消息时的BasicProperties设置的。<br>
basicPublish方法的第三个参数BasicProperties参数，设置为PERSISTENT_TEXT_PLAIN
就表示需要进行持久化。
# 3.Exchange持久化
Exchange持久化类似Queue的持久化，都是使用durable为true来实现的。
# 4.说明
即使对上述3部分都做出了持久化，也不能保证消息在使用过程中完全不会丢失。
比如，如果消息消费者在接受到消息时，autoAck为true,但是消费者在处理消息中发生异常，
那么此消息消费失败，但是队列中没有此消息，造成消息丢失。

上述例子可以设置autoAck为false然后在消费者完全消费完成后手动确认。
当然消息发布也存在同样的事情。

考虑消息确认模式解决。

