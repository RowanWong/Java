package cn.im.tool;
import java.io.IOException;

import javax.xml.namespace.QName;



public class WebServiceTool {

		    public static void main(String[] args) throws IOException {  
		    	
		    	
		    	String url = "http://10.121.2.221:8820/IMInterfaceCXF/services/smsCodeService.ws?wsdl";
				String namespace = "http://service.faq.com/";
				String method = "sendSMSDefault";
				String privateKey = "SMSCode@imsm#";
				String senderInfo = "中国电信";
				Long timestamp = System.currentTimeMillis();
				String param0 = "您的";
				String param1 = "获取随机码";
				String param2 = "中国电信";
				String phoneNo = "17722514937";
				String md5Str ="";
				String key = md5Str.substring(md5Str.length()-10).toUpperCase();
				
				Object[] arg={phoneNo,senderInfo,param0,param1,param2, timestamp+"",key};
//				String e = webServiceCall( url, namespace, method, arg) ;
				
				
				Object[] arg1 = {phoneNo, "376915", timestamp+"", key};
//				String e = webServiceCall( url, namespace, "verSMS", arg1) ;
				
				
//				Syst	em.out.println(e);
		    	
		    }  
		    
		    
//		    public static synchronized RPCServiceClient creatRPCServiceClient() throws AxisFault{
//				return new RPCServiceClient();
//			}
//		 
//		    public static String webServiceCall(String url,String namespace,String method,Object[] args) {
//				String callback = "";
////				System.setProperty("http.proxyHost", "10.121.2.5");  
////				System.setProperty("http.proxyPort", "3128");
//				RPCServiceClient serviceClient = null;	
//			
//				try {
//					// 客户端控件
//					serviceClient = creatRPCServiceClient();//new RPCServiceClient();
//					Options options = serviceClient.getOptions();
//					options.setTimeOutInMilliSeconds(3000L);
//					EndpointReference targetEPR = new EndpointReference(url);
//					options.setTo(targetEPR);
//					// 命名空间
//					QName qName = new QName(namespace, method);
//					// 返回值的类型，基本类型为
//					Class<?>[] returnType = new Class[] { String.class };
//					Object[] result = serviceClient.invokeBlocking(qName, args,returnType);
//					if (result != null && result.length > 0 && result[0] != null){
//						callback = (String) result[0];
//					}
//				} catch (Exception e) {
//					System.out.println(e);
//				} finally {
//					try {
//						serviceClient.cleanupTransport();
//						serviceClient.cleanup();
//					} catch (Exception e) {
//						System.out.println(e.toString());
//					}
//				}
//				return callback;
//			
//		    }
		 
	}

