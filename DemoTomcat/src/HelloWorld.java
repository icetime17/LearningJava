
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.text.MessageFormat;
import java.util.Date;


public class HelloWorld extends HttpServlet implements DefaultAction {

    private String message;

    public HelloWorld() throws ServletException {
        System.out.println("func HelloWorld");
    }

    public HelloWorld(String message) throws ServletException
    {
        System.out.println("func HelloWorld msg");
        this.message = message;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("get from hello world");


        parseRequestHeader(request);
        parseRequestParameters(request);


        String name = request.getParameter("name");
//        Boolean age = request.getParameter("age") instanceof String;
        String ageString = request.getParameter("age");
        int age = -1;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String city = request.getParameter("city");
        System.out.println(name);
        System.out.println(age);
        System.out.println(city);


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("post from hello world");


        parseRequestHeader(request);
        parseRequestParameters(request);


        String name = request.getParameter("name");
//        Boolean age = request.getParameter("age") instanceof String;
        String ageString = request.getParameter("age");
        int age = -1;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String city = request.getParameter("city");
        System.out.println(name);
        System.out.println(age);
        System.out.println(city);


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }


    public void destroy()
    {
        // do nothing.
    }


    // 解析Header
    private void parseRequestHeader(HttpServletRequest request) {
        System.out.println("_______ parseRequestHeader");
        String url = request.getRequestURL().toString(); // http://localhost:8080/helloworld
        String uri = request.getRequestURI(); // /helloworld
        String query = request.getQueryString(); // name=Chris&age=18&city=Shanghai&name=Ada&age=17&city=Xiamen
        String remoteAddr = request.getRemoteAddr(); // 0:0:0:0:0:0:0:1
        String remoteHost = request.getRemoteHost(); // 0:0:0:0:0:0:0:1
        int remotePort = request.getRemotePort(); // 51728
        String remoteUser = request.getRemoteUser(); // null
        String method = request.getMethod(); // GET
        String pathInfo = request.getPathInfo(); // null
        String localAddr = request.getLocalAddr(); // 0:0:0:0:0:0:0:1
        String localName = request.getLocalName(); // localhost
        System.out.println(url);
        System.out.println(uri);
        System.out.println(query);
        System.out.println(remoteAddr);
        System.out.println(remoteHost);
        System.out.println(remotePort);
        System.out.println(remoteUser);
        System.out.println(method);
        System.out.println(pathInfo);
        System.out.println(localAddr);
        System.out.println(localName);


        // header
        /*
        cache-control : no-cache
        postman-token : 4e62d07b-472d-47e0-b0fe-04450aa0f253
        user-agent : PostmanRuntime/7.2.0
        accept : *\/*
        host : localhost:8080
        cookie : JSESSIONID=367123C43446D50207178E692FE29C1C
        accept-encoding : gzip, deflate
        connection : keep-alive
         */
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + " : " + headerValue);
        }

        // Accept-Encoding : gzip, deflate
        String acceptEncoding = request.getHeader("Accept-Encoding");
        System.out.println("Accept-Encoding" + " : " + acceptEncoding);

        // Accept-Encoding : gzip, deflate
        Enumeration<String> encodingValues = request.getHeaders("Accept-Encoding");
        while (encodingValues.hasMoreElements()) {
            String encodingValue = (String)encodingValues.nextElement();
            System.out.println("Accept-Encoding" + " : " + encodingValue);
        }

        // 以Map的形式取出参数.
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValueArr = entry.getValue();
            String paramValue = "";
            for (int i = 0; paramValueArr != null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length - 1) {
                    paramValue += paramValueArr[i];
                } else {
                    paramValue += paramValueArr[i] + ",";
                }
            }
            System.out.println(MessageFormat.format("{0}={1}", paramName, paramValue));
        }


        System.out.println("\n");
    }

    // 解析Parameter
    private void parseRequestParameters(HttpServletRequest request) {
        System.out.println("_______ parseRequestParameters");
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            System.out.println(paramName);
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                System.out.println(paramValue);
            } else {
                // get请求可以有多个key值相同的key-value对.
                for (int i=0;i<paramValues.length;i++) {
                    String paramValue = paramValues[i];
                    System.out.println(paramValue);
                }
            }
        }

        System.out.println("\n");
    }

    public static void main(String[] args) throws IOException {
        List item = new ArrayList();
        item.add("string");
        item.add(1);
        item.add(true);
        item.add(new Date());
        System.out.println(item);



        for (String str : args) {
            System.out.println(str);
        }


        HelloWorld helloWorld;
        try {
            helloWorld = new HelloWorld("new message");
            helloWorld.testFile();
            helloWorld.testConcatMp3();
        } catch (ServletException e) {
            e.printStackTrace();
            return;
        }

        // 如果都不添加package, 则同一个目录下的类可以引用.
        Person person = new Person("Chris", 18, "Shanghai");
        person.saySomething();


        // 函数式->lambda
        MathOperation addition = (int a, int b) -> a+b;
        MathOperation subtraction = (a,b) -> a-b;
        MathOperation multiplication = (int a, int b) -> {return a*b;};
        MathOperation division = (int a, int b) -> a/b;
        System.out.println("10+5=" + helloWorld.operate(10,5, addition));
        System.out.println("10-5=" + helloWorld.operate(10,5, subtraction));
        System.out.println("10*5=" + helloWorld.operate(10,5, multiplication));
        System.out.println("10/5=" + helloWorld.operate(10,5, division));

        GreetingService greetingService1 = message -> System.out.println("hello "+message);
        GreetingService greetingService2 = (message) -> System.out.println("hello "+message);
        greetingService1.sayMessage("greetingService1");
        greetingService2.sayMessage("greetingService2");

        // 函数式->方法引用
        List names = new ArrayList();
        names.add("Apple");
        names.add("Google");
        names.add("Facebook");
        names.add("Microsoft");
        names.add("Amazon");
        names.forEach(System.out::println);

        // 函数式->Predicate
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        eval(list, n->true); // 输出所有整数
        eval(list, n->n%2==0); // 输出所有偶数
        eval(list, n->n>3); // 输出所有大于3的整数

        // 函数式->默认方法
        helloWorld.action();
    }

    public void action() {
        System.out.println("HelloWorld->action");
    }

    // File操作
    public void testFile() throws IOException {
        System.out.println(message);

        /*
        // 读取输入字符
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input char, and press q to exit.");
        char c;
        do {
            c = (char)br.read();
            System.out.println(c);
        } while (c != 'q');
        */

        /*
        // 读取输入字符串
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter quit to exit.");
        String str = "";
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("quit"));
        */

        /*
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            // next会以空格作为结束.
            // nextLine仅以enter作为结束.
//            String str = scanner.next();
            String str = scanner.nextLine();
            System.out.println(str);
        }
        scanner.close();
        */

        /*
        // 读取文件(二进制)
        File file = new File("HelloWorld.java");
        InputStream fileStream = new FileInputStream(file);
        int size = fileStream.available();
        for (int i = 0; i < size; i++) {
            char c = (char)fileStream.read();
            System.out.println(c);
        }
        fileStream.close();
        */

        /*
        // 读取文件, 写入到string中.
        // 使用InputStreamReader
        File file = new File("HelloWorld.java");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isReader = new InputStreamReader(fis, "UTF-8");
        StringBuffer sb = new StringBuffer();
        while (isReader.ready()) {
            sb.append((char)isReader.read());
        }
        System.out.println(sb.toString());

        isReader.close();
        fis.close();
        */

        /*
        // 写入文件
        // 使用OutputStreamWriter
        File file = new File("a.txt");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
        writer.append("输入内容");
        writer.append("\r\n");
        writer.append("this is a new file.");
        writer.close();
        fos.close();
        */

        /*
        String dirName = "./";
        File dir = new File(dirName);
        if (dir.isDirectory()) {
            System.out.println(dirName + " is a dir.");
            String[] files = dir.list();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i];
                File file = new File(dirName + "/" + fileName);
                if (file.isDirectory()) {
                    System.out.println(fileName + " is a dir.");
                } else {
                    System.out.println(fileName + " is a file.");
                }
            }
        } else {
            System.out.println(dirName + " is a file.");
        }
        */
    }

    // 拼接mp3
    public void testConcatMp3() throws IOException {
        String[] files = {"./mp3/1.mp3", "./mp3/2.mp3"};
        // 一次读取8k内容
        byte[] size = new byte[1024*8];

        FileOutputStream fos = null;
        FileInputStream fis = null;

        try {
            fos = new FileOutputStream("./mp3/final.mp3");
            for (int i = 0; i < files.length; i++) {
                String file = files[i];
                int count = 0;
                fis = new FileInputStream(file);
                // 跳过前3M
                //fis.skip(1024*1024*3);;
                while (fis.read(size) != -1) {
                    fos.write(size);
                    count++;
                    // 仅截取2M内容
//                    if (count == 1024*2/8) {
//                        break;
//                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 函数式

    interface MathOperation {
        int operation(int a, int b);
    }
    interface GreetingService {
        void sayMessage(String message);
    }

    // 接收两个整数和一个操作lambda表达式
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    // Predicate接收输入, 返回判断结果
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n: list) {
            if (predicate.test(n)) {
                System.out.println(n);
            }
        }
    }
}

interface DefaultAction {
    default void action() {
        System.out.println("DefaultAction->action");
    }
}
