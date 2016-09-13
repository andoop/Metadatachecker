##Metadatachecker
###反编译apk资源文件，获取meta-data信息，以key-value形式打印到控制台
####使用方法：
1. 将要查看的apk文件放入到 CheckTool/apk 文件中，可以放入多个
2. 将要查看的mate-data 的key 写入 CheckTool/matacheck/check.key文件中，一个key对应一行，可以写入多个key
3. 双击执行CheckTool/matacheck/check.bat批处理，在控制台中即可查看check.key中对应key的meta-data信息

>具体实现可以查看源码，可以按需修改