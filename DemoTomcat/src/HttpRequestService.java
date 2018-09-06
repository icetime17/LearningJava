import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class HttpRequestService {

    public static String get(String url, int timeout) {
        if (url == null || url.length() == 0) {
            return null;
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Connection", "close");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(timeout);
            conn.connect();

            StringBuffer sbf = new StringBuffer();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = bfr.readLine()) != null) {
                sbf.append(line);
            }
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.disconnect();
        }
    }

    public static String post(String url, String json, int timeout) {
        if (url == null || url.length() == 0) {
            return null;
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Connection", "close");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(timeout);
            conn.connect();

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(json.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();

            StringBuffer sbf = new StringBuffer();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = bfr.readLine()) != null) {
                sbf.append(line);
            }
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.disconnect();
        }
    }

    public static String download(String url) {
        if (url == null || url.length() == 0) {
            return null;
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.connect();

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                File dir = new File("files");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, "filename.pdf");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[1024*8*8];
                int len = -1;
                while ((len = inputStream.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                return "success";
            }
            return "fail to download";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            conn.disconnect();
        }
    }

    public static void main(String[] args) {
        String url = "";
        String res = "";

        url = "";
        res = HttpRequestService.get(url, 3000);
        System.out.println(res);

        url = "http://icetime.com/json";
        res = HttpRequestService.get(url, 3000);
        System.out.println(res);

        url = "http://106.14.55.15/caishen/api/0.1/search/hot";
        res = HttpRequestService.get(url, 3000);
        System.out.println(res);

        url = "http://stage.tigerobo.com/caishen/api/0.1/search/all_go";
        String json = "{\"query\":\"工商银行\", \"area\": \"cn\"}";
        res = HttpRequestService.post(url, json, 5000);
        System.out.println(res);

        res = HttpRequestService.download("http://res.aigauss.com/bundles/623884e7b9ed3fb85dbd937586cec6cd/earnings/_p/2018q0pre0005.pdf?Expires=1534808117&OSSAccessKeyId=LTAINZXo4C9s3hVI&Signature=6L%2FWNmk2wc3IOvJqmVqpo7NkXfc%3D");
        System.out.println(res);
    }
}
