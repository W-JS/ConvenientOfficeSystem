package cn.huat.util;


import javax.servlet.http.HttpServlet;


/**
 * ClassName: MessageCheck <br/>
 * Description: <br/>
 * date: 2020/12/15 9:53<br/>
 *
 * @author suhd<br />
 */
public class MessageCheckUtil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String apiUrl = "https://sms_developer.zhenzikj.com";
    private static String appId = "107449";
    private static String appSecret = "27e45b48-68b4-449c-8503-1327c6cf4a1d";


    public static String getMessageCode(String telephoneNumber){
        String code = getSmsCode();
//        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
//        Map<String,Object> params = new HashMap<>();
//        params.put("number", telephoneNumber);
//        params.put("templateId", "2696");
//        String[] templateParams = new String[2];
//        templateParams[0] = code;
//        templateParams[1] = "5分钟";
//        params.put("templateParams", templateParams);
//        try {
//            String result = client.send(params);
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(code);
        return code;
    }

    private static String getSmsCode(){
        String code=(int)((Math.random()*9+1)*100000)+"";
        return code;
    }

}
