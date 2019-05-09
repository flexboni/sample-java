package com.example.gbkim.gubonny.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by neozen on 2018-04-04.
 */

public class Utils {

    public static String getSHA256String(String sText) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] digest = md.digest(sText.getBytes());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }

        String sResult = sb.toString();

        return (sResult);
    }

    public static byte[] hexToBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2),
                    16);
        }

        return bytes;
    }

    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }

    // 날짜 비고
    // 참고 : https://rockdrumy.tistory.com/1327
    public static boolean DateCompare(String sPrevious, String sForward) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

        Date dPrevious = null;
        Date dForward = null;

        try {
            dPrevious = format.parse(sPrevious);
            dForward = format.parse(sForward);

            int iCompare = dPrevious.compareTo(dForward);

            if (iCompare > 0) {
                return false;

            } else if (iCompare < 0) {
                return true;

            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            // Message
        }

        return false;
    }
}
