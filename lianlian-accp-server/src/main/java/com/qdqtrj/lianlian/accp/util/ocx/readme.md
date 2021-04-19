密码控件需要两个参数从此包中的类生成(反编译自AESWithJCE.jar)：

```java
        String sKey=GetRandom.generateString(32);
        String enStr=AESWithJCE.getCipher(sKey,sKey);
        System.out.println(sKey);
        System.out.println(enStr);
```
