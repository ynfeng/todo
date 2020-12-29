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
