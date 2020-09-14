指令模式 

1. 初始化
定义创建指令 Invoker，将指令注册（通过指令的枚举）到CommandRegisterFactory
2. 调用
调用CommandFactory.invoke