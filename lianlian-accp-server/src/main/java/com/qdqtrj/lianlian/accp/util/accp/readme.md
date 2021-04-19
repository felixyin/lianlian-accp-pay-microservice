老版本的底层实现，基于httpclient/httpcore5 仅此包中代码为连连支付提供api-demo.jar中代码原封不动复制过来的。 在此基础上，修改内容：

1. 日志
2. 支持jdk11 api
3. 处理eclipse warnings

> todo 此包引用大量过时api，后面会采用spring或okhttp重写