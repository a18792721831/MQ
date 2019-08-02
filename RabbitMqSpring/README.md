# [RabbitMqSpring](https://blog.csdn.net/a18792721831/article/details/93843315)
spring与RabbitMQ集成
# 1.中间桥接包
spring与RabbitMq进行集成需要增加中间桥接依赖包：spring-rabbit.
现在最新的版本是2.1.7.RELEASE
# 2.创建监听
MessageListenerContainer:用来监听容器，为消息入队提供异步处理
# 3.RabbitTemplate
用来发送和接收消息
# 4.RabbitAdmin
用来声明队列、交换机、绑定
# 5.发布
发布需要获取到spring的context然后获取到RabiitTemplate
然后用RabbitTemplate的convertAndSend方法发布消息
# 6.消费
消费者就是xml中配置的Listener，实现MessageListener接口
实现onMessage方法，onMessage方法中就是接收到消息后调用的回调方法。

