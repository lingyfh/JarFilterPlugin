//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.netease.nis.sdkwrapper;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Utils {
    public Utils() {
    }

    public static native Object rL(Object[] var0);

    private static String doTypeShort(String var0) {
        if (var0.startsWith("[")) {
            return var0.replace(".", "/");
        } else if (var0.equals("int")) {
            return "I";
        } else if (var0.equals("float")) {
            return "F";
        } else if (var0.equals("long")) {
            return "J";
        } else if (var0.equals("double")) {
            return "D";
        } else if (var0.equals("short")) {
            return "S";
        } else if (var0.equals("char")) {
            return "C";
        } else if (var0.equals("boolean")) {
            return "Z";
        } else {
            return var0.equals("byte") ? "B" : ("L" + var0 + ";").replace(".", "/");
        }
    }

    public static String getFieldSCDesc(Class var0, String var1, String var2) {
        for(Class var3 = var0; var3 != null; var3 = var3.getSuperclass()) {
            String var4 = vGetFieldSCDesc(var3, var1, var2);
            if (var4 != "") {
                return var4;
            }
        }

        return "";
    }

    private static String vGetFieldSCDesc(Class var0, String var1, String var2) {
        try {
            Field[] var3 = var0.getDeclaredFields();
            Field.setAccessible(var3, true);

            for(int var4 = 0; var4 < var3.length; ++var4) {
                Field var5 = var3[var4];
                String var6 = var5.getType().toString().replace("class ", "").replace("interface ", "");
                if (Modifier.isStatic(var5.getModifiers()) && var5.getName().equals(var1) && var2.equals(doTypeShort(var6))) {
                    return var5.getDeclaringClass().getName().replace(".", "/");
                }
            }

            return "";
        } catch (NoClassDefFoundError var7) {
            return "NoClassDefFoundError";
        }
    }

    public static void showRiskMessage(Context var0, String var1) {
        Log.i("Utils", "showRiskMessage msg = " + var1);
    }

    public static Object getStaticFO(String var0, String var1) {
        try {
            Class var2 = Class.forName(var0.replace('/', '.'));
            Field var3 = var2.getField(var1);
            Class var4 = var3.getType();
            if (var4 == Integer.TYPE) {
                return var3.getInt((Object)null);
            } else if (var4 == Float.TYPE) {
                return var3.getFloat((Object)null);
            } else if (var4 == Long.TYPE) {
                return var3.getLong((Object)null);
            } else if (var4 == Double.TYPE) {
                return var3.getDouble((Object)null);
            } else if (var4 == Short.TYPE) {
                return var3.getShort((Object)null);
            } else if (var4 == Character.TYPE) {
                return var3.getChar((Object)null);
            } else if (var4 == Boolean.TYPE) {
                return var3.getBoolean((Object)null);
            } else {
                return var4 == Byte.TYPE ? var3.getByte((Object)null) : var3.get((Object)null);
            }
        } catch (Exception var5) {
            Log.e("Utils", var5.toString());
            return null;
        }
    }

    static {
        System.loadLibrary("secsdk");
    }
}
