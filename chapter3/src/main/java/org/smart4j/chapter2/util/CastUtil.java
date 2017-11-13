package org.smart4j.chapter2.util;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 2017-08-01-16:46
 */
public class CastUtil {

    public  static  String castString(Object object){
        return CastUtil.castString(object,"");
    }

    /**
     * 把一个其他类型转化为字符串，如果类型为空，那么为默认值。
     * @param object
     * @param defaultValue
     * @return
     */
    public  static  String castString(Object object,String defaultValue){
        return object!=null?String.valueOf(object):defaultValue;
    }

    /**
     * 转化为double
     * 1、先转为string
     * 2、在转化为double
     * 3、如果出现异常，把转化结果指定为默认值。
     * @param object
     * @return
     */
    public  static  double castDouble(Object object){
        return CastUtil.castDouble(object,0);
    }

    public  static  double castDouble(Object object,double defaultValue){
        double doubleValue=defaultValue;
        if (object!=null){
            String strValue=castString(object);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    doubleValue=Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleValue =defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public  static  long castLong(Object object){
        return CastUtil.castLong(object,0);
    }

    public  static  long castLong(Object object,long defaultValue){
        long longValue=defaultValue;
        if (object!=null){
            String strValue=castString(object);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    longValue=Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue =defaultValue;
                }
            }
        }
        return longValue;
    }

    public  static  int castInt(Object object){
        return CastUtil.castInt(object,0);
    }

    public  static  int castInt(Object object,int defaultValue){
        int intValue=defaultValue;
        if (object!=null){
            String strValue=castString(object);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    intValue=Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue =defaultValue;
                }
            }
        }
        return intValue;
    }

    public  static  boolean castBoolean(Object object){
        return CastUtil.castBoolean(object,false);
    }

    public  static  boolean castBoolean(Object object,boolean defaultValue){
        boolean booleanValue=defaultValue;
        if (object!=null){
            booleanValue=Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }
}
