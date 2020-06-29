package com.fanr.graduation.common.alipay;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String APP_ID = "2021001155653455";
	
	// 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
    public static String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkBU8yRiTBsRixaaiqWGxhVEZDFPjlrvPaVn/xY5SL9fjQTEvbdPVNrxjdl5F2vqeMTH0LSItW6qO32YRbWkvto1gQUQRkQx7dH4WNWUyPUhiNax46sP0W9IcdxElz95g/Hq7tKf84glc7LzsLsKt92e/v5PI0sG/cdE4QvvQQeo05QnhvSNlLw/0siEqJSC0w/RzsmfZnB9dJoSPNy6v+ukyFMyvPZjeBzZVFv9TxmYg1AxsuI9zhHorLsT1cwTcwOvjQkLPtVFPQ6A5BOitwVCyR5Omxmteett1Up0ysWhCrg6/mgLzFG3A2w37BouGQsL+coFupZFAkPDfi/23PAgMBAAECggEAEuaJucFbevZtYgRK61ZFJvIPJqvEArOmZgKRu7893uL5SQeBtkhD/C+xLdfJa2WM6R3nSuWRKPLg6s6l8eD13uxzuW2ck/EoHVMEkMU5jUpoieNOGHVMtNh4uXVlAXgGkz916N5uCpT6eFd49l/lYxs6CurW/jtWqJC+bi+S8FL/nfNrpQif7hkHQ0VgxThdkqANXyojZDUAtTaOYp+39wZYwJ1Xdjox9boVchiB/XiRKP5NDMAklMEUQw8yI+O7H4E0t9FEB/LeVQ6S7WAe7qbnihOe9/65vwi3/UHTDg+ElYkFZEvRHxGIqHfen+8gR0flwh4IEe/5d8Hyu4WkAQKBgQDbJnojvzfnbUfkOdf0kuYAA3i3FAVURk+gsU2umDRWs2ZcAi9nf1hmqXzbtDYjMmq0ZX1BfsNN1YOWVMHCxpr2LXxBWSiJnEe0+sO0HTKk99N9U0xiFttpmUaybqcgYxcXaWk9g4w7psaFL0ZkUvIIiCOlujgoU2PXqgto5jIzXQKBgQC/mbr9FEKswoUi1g6SWVP66CyIlZ05gYUQSFnYJwLoDHhJOZZ54vsY+7Qki6Qh0LktyqjmU8+zN+NTyCyXOkoVgt6S0lKcceZxAi3YG8pcv+Xl/azVT/p9SJvcmBvSzG27QaRhqfcIV7u0/sr26eK9J7tNnNrWl1sz4E/pG27fGwKBgQCcPAgjZ/Vw9cWk/V/qKrfyFod+7bUG6cFm7EBXUoctsGQvABfPj0f0mKw87XMTEKRly1zKzpZp7OgMyowAUhqzmqXU6L0vrcypR4Ux6Qbr+Qo7dKEl5OmzlOSD3MMlIb+anzm58wiMlk89MADdEQ0f6/6N7f6PMTQi0kWkGUh8uQKBgFNxokwiDL+sn8sCKqp2GOsHPo3liunGjKsAt5A5vTxOYfySBgTjY7QHuvbHkh3gByX4QwK8sZI93tVYMoNSMX2Y11JeIvhbap8A8fJSeBlYwckeZ60m/dKeUrMJ0+REf+2zsuGN4jZR7z/uT3qldcgni51LmMsoc+Ht6R9+gRTNAoGAUTXm4VyFvfcb+nJ4lfH/ChRVrRUw1tNIO5trDjTT+pJYfLmfB2YN3+GgdEpjKssoMIqAix8UnxaFPGLqYSWWX4Ka9Gl5w3655NFvvoiW//nfOqmgEVJP74GD8LcRuhGgkDij7fEQ+/I8KYM4/NSUCcyMeW0g9dZbn5ptvJLFsKA=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApAVPMkYkwbEYsWmoqlhsYVRGQxT45a7z2lZ/8WOUi/X40ExL23T1Ta8Y3ZeRdr6njEx9C0iLVuqjt9mEW1pL7aNYEFEEZEMe3R+FjVlMj1IYjWseOrD9FvSHHcRJc/eYPx6u7Sn/OIJXOy87C7Crfdnv7+TyNLBv3HROEL70EHqNOUJ4b0jZS8P9LIhKiUgtMP0c7Jn2ZwfXSaEjzcur/rpMhTMrz2Y3gc2VRb/U8ZmINQMbLiPc4R6Ky7E9XME3MDr40JCz7VRT0OgOQTorcFQskeTpsZrXnrbdVKdMrFoQq4Ov5oC8xRtwNsN+waLhkLC/nKBbqWRQJDw34v9tzwIDAQAB";

    //异步通知，再这里我们设计自己的后台代码
    public static String notify_url = "http://175.24.88.103:8889/api/alipay/success";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.fanr639.com/success";

	// 签名方式
	public static String SIGN_TYPE = "RSA2";
	
	// 字符编码格式
	public static String CHARSET = "utf-8";
	
	// 支付宝网关
	public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String LOG_PATH = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}