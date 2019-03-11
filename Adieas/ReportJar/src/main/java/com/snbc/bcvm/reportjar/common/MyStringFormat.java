package com.snbc.bcvm.reportjar.common;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class MyStringFormat {
    public static final String GBK = "GBK";
    public static final String UTF8 = "utf-8";
    public static final char[] ascii = "0123456789ABCDEF".toCharArray();
    private static char[] HEX_VOCABLE = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static char GetCharByHex(byte bHex) {
        char chResult = 'F';
        if (bHex >= 0x00 && bHex <= 0x09) {
            chResult = (char) (bHex + '0');
        } else if (bHex >= 0x0A && bHex <= 0x0F) {
            chResult = (char) (bHex - 0x0A + 'A');
        }
        return chResult;
    }

    /**
     * 将charsetName编码格式的字节数组转换为字符串
     *
     * @param bytes
     * @param charsetName
     * @return
     */
    public static String getString(byte[] bytes, String charsetName) {
        return new String(bytes, Charset.forName(charsetName));
    }

    /**
     * 将GBK编码格式的字节数组转换为字符串
     *
     * @param bytes
     * @return
     */
    public static String getString(byte[] bytes) {
        return getString(bytes, UTF8);
    }

    /**
     * 字节数组转16进制字符串
     * @param
     * @return
     */
    public static String byteToHex(byte b) {
        StringBuilder sb = new StringBuilder();
        int high = (b >> 4) & 0x0f;
        int low = b & 0x0f;
        sb.append(HEX_VOCABLE[high]);
        sb.append(HEX_VOCABLE[low]);
        return sb.toString();
    }

    //byte数组转字符串
    public static String ArrayToString(byte[] reOdNum){
        StringBuffer sreodnum = new StringBuffer();
        for (int i = 0; i < reOdNum.length; i = i+1) {
            if(reOdNum[i] == 0x00){
                break;
            }else{
                sreodnum.append((char)reOdNum[i]);
            }

        }
        return sreodnum.toString();
    }

    public static int BcdToString(final byte[] pBcd, int iBcdLengh, char[] pResult) {
        for (int iLoop = 0; iLoop < iBcdLengh; ++iLoop) {
            pResult[2 * iLoop] = GetCharByHex((byte) ((pBcd[iLoop] & 0xF0) >> 4));
            pResult[2 * iLoop + 1] = GetCharByHex((byte) (pBcd[iLoop] & 0x0F));
        }
        return iBcdLengh * 2;
    }
    public static int BcdToString(final int[] pBcd, int iBcdLengh, char[] pResult) {
        for (int iLoop = 0; iLoop < iBcdLengh; ++iLoop) {
            pResult[2 * iLoop] = GetCharByHex((byte) ((pBcd[iLoop] & 0xF0) >> 4));
            pResult[2 * iLoop + 1] = GetCharByHex((byte) (pBcd[iLoop] & 0x0F));
        }
        return iBcdLengh * 2;
    }
    public static byte GetHexFromChar(char chValue) {
        byte bResult = 0x00;
        if (chValue >= '0' && chValue <= '9') {
            bResult = (byte) (chValue - '0');
        } else if (chValue >= 'A' && chValue <= 'F') {
            bResult = (byte) (chValue - 'A' + 10);
        } else if (chValue >= 'a' && chValue <= 'f') {
            bResult = (byte) (chValue - 'a' + 10);
        }
        return bResult;
    }

    public static int StringToBcd(char[] pRes, int nLength, byte[] pDes) {
        for (int iLoop = 0; iLoop < ((nLength + 1) >> 1); ++iLoop) {
            pDes[iLoop] = (byte) (
                    (GetHexFromChar(pRes[2 * iLoop]) << 4) |
                            GetHexFromChar(pRes[2 * iLoop + 1])
            );
        }
        return ((nLength + 1) >> 1);
    }

    public static int StringToBcd(String strRes, int nLength, byte[] pDes) {
        for (int iLoop = 0; iLoop < ((nLength + 1) >> 1); ++iLoop) {
            pDes[iLoop] = (byte) (
                    (GetHexFromChar(strRes.charAt(2 * iLoop)) << 4) |
                            GetHexFromChar(strRes.charAt(2 * iLoop + 1))
            );
        }
        return ((nLength + 1) >> 1);
    }

    public static String HexToBinary(String s){
        return Long.toBinaryString(Long.parseLong(s,16));
    }

    public static String toHexString(String s){
        String str = "";
        for (int i=0;i<s.length();i++){
            int ch = (int)s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str+s4;
        }
        return str;
    }

    public static void memcpy(byte[] pDes, int iDesBegin, byte[] pRes, int iResBegin, int iLength) {
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            pDes[iLoop + iDesBegin] = pRes[iLoop + iResBegin];
        }
    }

    public static void rememcpy(byte[] pDes, int iDesBegin, byte[] pRes, int iResBegin, int iLength) {
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            pDes[iLoop + iDesBegin] = pRes[(iLength - iLoop - 1) + iResBegin];
        }
    }

    public static void CopyArray(byte[] pszRes, int iLength, byte[] pszDes, int iBeign) {
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            pszDes[iBeign + iLoop] = pszRes[iLoop];
        }
    }
    public static synchronized void CopyArray(byte[] pszRes, int iLength, int[] pszDes, int iBeign) {
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            pszDes[iBeign + iLoop] = pszRes[iLoop]&0xFF;

        }
    }
    public static void StringToCharArray(char[] pszDes, int iDesBegin, String strRes) {
        for (int iLoop = 0x00; iLoop < strRes.length(); ++iLoop) {
            pszDes[iDesBegin + iLoop] = strRes.charAt(iLoop);
        }
    }

    public static int GetByteFlagPos(byte[] pszRes, int iLength, byte bFlag) {

        int iPos = -1;
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            if (pszRes[iLoop] == bFlag) {
                iPos = iLoop;
                break;
            }
        }
        return iPos;
    }


    public static String AsciiToGbk(byte[] pszRes, int iResBegin, int iLength) {
        byte[] pTemp = new byte[iLength];
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            pTemp[iLoop] = pszRes[iLoop + iResBegin];
        }
        String strRes = null;
        try {
            strRes = new String(pTemp, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strRes;
    }

    public static String AsciiToString(byte[] pszRes, int iResBegin, int iLength) {
        byte[] pTemp = new byte[iLength];
        for (int iLoop = 0x00; iLoop < iLength; ++iLoop) {
            pTemp[iLoop] = pszRes[iLoop + iResBegin];
        }
        String strRes = null;
        try {
            strRes = new String(pTemp, "UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strRes;
    }

    public static void uSleep(int iTimeMillis) {
        try {

            int iMainTimes = (iTimeMillis / 100);
            int iSubTimes = (iTimeMillis % 100);
            int iLoop = 0x00;
            while (iLoop < iMainTimes) {
                Thread.sleep(100);
                ++iLoop;
            }
            Thread.sleep(iSubTimes);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * 计算校验和
     * create at 2016/8/16 0016 16:32
     */

    /**
     * 校验和
     *
     * */
    public static byte[] GetCheckSum(byte[] pszData) {
        byte[] temdata = new byte[pszData.length+2];
        int CRC_CONST_CODE =  0x8408;
        int CRC_CHECK_MASK = 0x0001;
        int CRCValue = 0;
        int SizeCnt = 0;
        int CharCnt = 0;
        for (SizeCnt=0;SizeCnt<pszData.length;SizeCnt++){

                CRCValue ^= pszData[SizeCnt]&0xFF;


            for (CharCnt = 0; CharCnt < 8 ;CharCnt++){
                int result  = (CRCValue & CRC_CHECK_MASK);

                if (result != 0)
                {
                    CRCValue >>= 1;
                    CRCValue ^= CRC_CONST_CODE;
                }else {
                    CRCValue >>= 1;
                }
            }
        }

        System.arraycopy(pszData,0,temdata,0,pszData.length);
        temdata[temdata.length-2] = (byte) (CRCValue&0xFF);
        temdata[temdata.length-1] = (byte) (CRCValue>>8);
        return temdata;
    }
  /**
  * 计算随机数
  * */
    public static int GetRandom(int number){
        int inumber = new Random().nextInt(number)+1;
        return inumber;
    }

    /**
     * 展示当前数组内容
     * */
    public static String ShowProtocol(int[] szCmd, int iDataLength) {
        try {
            char[] szLog = new char[iDataLength * 2 + 4];
            int iRstLength = MyStringFormat.BcdToString(szCmd, iDataLength, szLog);
            //  MyLogFile.WriteLogFile("HcpErrorCode", "ShowProtocol:" + new String(szLog, 0, iRstLength));
            System.out.println(new String(szLog,0,iRstLength));
            return new String(szLog,0,iRstLength);
        } catch (Exception e) {

        }
        return "查询失败";
    }
    /**
     * 展示当前数组内容
     * */
    public static String ShowProtocol(byte[] szCmd, int iDataLength) {
        try {
            char[] szLog = new char[iDataLength * 2 + 4];
            int iRstLength = MyStringFormat.BcdToString(szCmd, iDataLength, szLog);
            //  MyLogFile.WriteLogFile("HcpErrorCode", "ShowProtocol:" + new String(szLog, 0, iRstLength));
            System.out.println(new String(szLog,0,iRstLength));
            return new String(szLog,0,iRstLength);
        } catch (Exception e) {

        }
        return "查询失败";
    }
    /**
     * 持久化异常日志
     *autr 王峰
     */
    public static String GetExceptionLog(Exception e){
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while(cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.flush();
        printWriter.close();
        String result1 = writer.toString();
        return result1;
    }

    /**
     * 异常日志内存信息，返回异常中所有信息
     *autr 郭瑞
     * */
    public static String[] getThrowableStrRep(Throwable throwable){
        if(throwable == null){
            return new String[0];
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();
        LineNumberReader reader = new LineNumberReader(new StringReader(
                sw.toString()));
        ArrayList lines = new ArrayList();

        try{
            String line = reader.readLine();
            while (line != null){
                lines.add(line);
                line = reader.readLine();
            }
        }catch (IOException ex){
            lines.add(ex.toString());
        }

        String[] rep = new String[lines.size()];
        lines.toArray(rep);
        return rep;
    }

    /**
     * 异常日志内存信息,返回异常第一条信息
     *autr 郭瑞
     * */
    public static String getThrowableStrRepF(Throwable throwable){
        if(throwable == null){
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();
        LineNumberReader reader = new LineNumberReader(new StringReader(
                sw.toString()));
        ArrayList lines = new ArrayList();

        try{
            String line = reader.readLine();
            while (line != null){
                lines.add(line);
                line = reader.readLine();
            }
        }catch (IOException ex){
            lines.add(ex.toString());
        }

        String[] rep = new String[lines.size()];
        lines.toArray(rep);
        return rep[0];
    }

    public static byte[] stringtoHexBytes(String str) {
        byte[] bytes=str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Byte.parseByte(Integer.toHexString(bytes[i]));
        }

        return bytes;
    }

    public static int[] byteArrToIntArr(byte[] bytes,int len) {
        int[] rs = new int[len];
        for (int i=0;i<len;i++) {
            rs[i] =bytes[i]&0xFF;
        }
        return  rs;
    }

    public static byte[] getBitArray(byte b){
        byte[] array=new byte[8];
        for(int i=7;i>=0;i--) {
            array[i]=(byte)(b & 1);
            b=(byte)(b >> 1);
        }
        return array;
    }

}
