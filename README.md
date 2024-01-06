后端代码在本机运行，端口是9090
请您注意：安卓端运行时请先修改ip地址，一共有两处，（可以全局搜索 192.168） 
在de/rampro/activitydiary/CommActivity.java中将 private String url = "ws://192.168.1.104:9090/websocket/";中的ip换为后端正在运行的（本机的）ip，保证websocket协议能够连接。
在de/rampro/activitydiary/net/RetrofitFactory.java中将.baseUrl("http://192.168.1.104:9090")中的ip换为后端正在运行的（本机的）ip，保证http前后端连接。
