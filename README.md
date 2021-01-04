From https://time.geekbang.org/column/intro/382

# 构建

```
./gradlew build
```

# 运行效果

```
java -jar build/libs/todo-1.0-SNAPSHOT.jar add 中文测试
java -jar build/libs/todo-1.0-SNAPSHOT.jar add "english test"
java -jar build/libs/todo-1.0-SNAPSHOT.jar add other
java -jar build/libs/todo-1.0-SNAPSHOT.jar list
java -jar build/libs/todo-1.0-SNAPSHOT.jar done 3
java -jar build/libs/todo-1.0-SNAPSHOT.jar list
java -jar build/libs/todo-1.0-SNAPSHOT.jar list --all
```

# 第二阶段功能

1. 添加新用户
2. 用户登录和登出
3. 登录不同的用户看到的数据列表不同
4. 未登录时为匿名用户

```
java -jar build/libs/todo-1.0-SNAPSHOT.jar adduser -u testuser
java -jar build/libs/todo-1.0-SNAPSHOT.jar login -u testuser
java -jar build/libs/todo-1.0-SNAPSHOT.jar list --all
java -jar build/libs/todo-1.0-SNAPSHOT.jar logout
```

# 第三阶段功能

1. 导出
2. 导入

```
java -jar build/libs/todo-1.0-SNAPSHOT.jar export -o /tmp/export
java -jar build/libs/todo-1.0-SNAPSHOT.jar import -f /tmp/export
```
