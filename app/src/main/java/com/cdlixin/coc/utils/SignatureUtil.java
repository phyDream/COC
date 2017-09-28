package com.cdlixin.coc.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

    /*
        url = http://api.douban.com/v2/app?method=XXX&timestamp=XXX&app_type=XXX
        tmp = UrlEncode(url)              汉字 -> %xx%xx%xx%xx
        sign= MD5(tmp)
     */

/**
 * Created by phy on 2016/11/22.
 * 签名生成工具
 */

public class SignatureUtil {

    /**
     * 得到请求签名
     * @param baseUrl 请求头url
     * @param sectorUrl 请求模块
     * @param map 请求参数键值对
     * @return
     */
    public static String getSign(String baseUrl, String sectorUrl, Map<String, String> map){
        return getSign(baseUrl, sectorUrl, map, false);
    }

    /**
     * 得到请求签名
     * @param baseUrl 请求头url
     * @param sectorUrl 请求模块
     * @param map 请求参数键值对
     * @param isPost 请求方法是否为post
     * @return
     */
    public static String getSign(String baseUrl, String sectorUrl, Map<String,String> map, boolean isPost){

        if(!map.isEmpty()){
            StringBuffer tamp = new StringBuffer();
            //遍历map拼接URL的键值对,并对map的值（传入的请求参数）进行UTF-8转码操作
            for (Map.Entry<String, String> entry : map.entrySet()) {
                    try {
                        String str = String.format("%s=%s&", entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
//                        String str = String.format("%s=%s&", entry.getKey(), getEncadeStr(entry.getValue()));
                        LogUtil.i("~url参数： "+str);
                        tamp.append(str);
                    } catch (Exception ex) {
                    }
            }
            String parameters = tamp.toString();//url参数拼接部分
            String url = "";
            if(isPost) {
                url = baseUrl+sectorUrl+parameters.substring(0,parameters.length()-1);//得到完整的url
            }
            else {
                url = baseUrl+sectorUrl+"?"+parameters.substring(0,parameters.length()-1);//得到完整的url
            }
            LogUtil.i("~~"+url);
//            LogUtil.i(isPost+"~"+url+"&sign="+getMD5(url));
            return getMD5(url);
        }
        return null;
    }

    //URLEncoder.encode编码后
    public static String getEncadeStr(String str){
        List<String> strs = SignatureUtil.getStrList(str,1024);
        StringBuffer beforeEncadeStr = new StringBuffer();
        for (String s : strs) {
            try {
                String s1 = URLEncoder.encode(s, "UTF-8");
                beforeEncadeStr.append(s1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        LogUtil.i("~字符串长度~"+beforeEncadeStr.length());
        return beforeEncadeStr.toString();
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length,size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString
     *            原始字符串
     * @param length
     *            指定长度
     * @param size
     *            指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length, (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str
     *            原始字符串
     * @param f
     *            开始位置
     * @param t
     *            结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    /**
     * MD5处理
     * @param info
     * @return
     */
    public static String getMD5(String info)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

//            String tmp = URLEncoder.encode(info, "UTF-8");
//            LogUtil.i("~~"+tmp);
//            md5.update(tmp.getBytes("UTF-8"));

            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

//            LogUtil.i("~~"+URLDecoder.decode(strBuf.toString(), "UTF-8"));
            return URLDecoder.decode(strBuf.toString(), "UTF-8");
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
}
