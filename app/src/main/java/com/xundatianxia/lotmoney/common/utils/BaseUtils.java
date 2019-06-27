package com.xundatianxia.lotmoney.common.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ${zhoutao} on 2017/12/13 0013.
 */

public class BaseUtils {

  public static String settingphone(String phone) {
    String phone_s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    return phone_s;
  }


  /**
   * 减免时间
   *
   * @return
   */
  public static String formatDateTime(long mss) {
    String DateTimes = null;
    long days = mss / (60 * 60 * 24);
    long hours = (mss % (60 * 60 * 24)) / (60 * 60);
    long minutes = (mss % (60 * 60)) / 60;
    long seconds = mss % 60;
    if (days > 0) {
      DateTimes = days + "天" + hours + "小时" + minutes + "分钟"
              + seconds + "秒";
    } else if (hours > 0) {
      DateTimes = hours + "小时" + minutes + "分钟"
              + seconds + "秒";
    } else if (minutes > 0) {
      if (seconds > 0) {
        DateTimes = minutes + "分钟"
                + seconds + "秒";
      } else {
        DateTimes = minutes + "分钟";
      }
    } else {
      DateTimes = seconds + "秒";
    }

    return DateTimes;
  }

  /**
   * 返回临时下载apk文件路径
   */
  public static String getDownloadApkDirectory(Context context) {
    File downloadPath = null;
    // 获取系统的保存路径
    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
      downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    } else {
      downloadPath = new File(getDiskCacheDir(context, "apk"));
    }

    if (!downloadPath.exists()) {
      downloadPath.mkdirs();
    }
    return downloadPath.getAbsolutePath() + File.separator;
  }

  /**
   * 获取cache文件夹下的路径
   */
  public static String getDiskCacheDir(Context context, String uniqueName) {
    String cachePath = null;
    try {
      if (context != null) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
          /**暂时先注释掉，由于获取该路径出现空指针*/
          cachePath = context.getExternalCacheDir().getPath();
        } else {
          cachePath = context.getCacheDir().getPath();
        }
        return cachePath + File.separator + uniqueName;
      } else {
        cachePath = "/storage/emulated/0/Android/data/com.finnace/cache";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cachePath;
  }

  /**
   * 复制评论
   *
   * @param content 文本内容
   * @param context 资源管理器
   */
  public static void copyComment(String content, Context context) {
    // 得到剪贴板管理器
    ClipData clipData = ClipData.newPlainText("text", content);
    ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    clip.setPrimaryClip(clipData);
  }



  /**
   * 带参数的页面跳转
   *
   * @param context
   * @param pClass
   * @param pBundle
   * @author Jeff
   */
  public static void openActivity(Activity context, Class<?> pClass, Bundle pBundle) {
    if (context == null) {
      return;
    }
    Intent intent = new Intent(context, pClass);
    if (pBundle != null) {
      intent.putExtras(pBundle);
    }
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity(intent);
  }

  /**
   * 带参数的页面跳转
   *
   * @param context
   * @param pClass
   * @param pBundle
   * @author Jeff
   */
  public static void openActivityLeft(Activity context, Class<?> pClass, Bundle pBundle) {
    if (context == null) {
      return;
    }
    Intent intent = new Intent(context, pClass);
    if (pBundle != null) {
      intent.putExtras(pBundle);
    }
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity(intent);
  }


  /**
   * Tests if a code point is "whitespace" as defined in the HTML spec.
   *
   * @param c code point to test
   * @return true if code point is whitespace, false otherwise
   */
  public static boolean isWhitespace(int c) {
    return c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
  }

  /**
   * Tests if a string is blank: null, emtpy, or only whitespace (" ", \r\n, \t, etc)
   *
   * @param string string to test
   * @return if string is blank
   */
  public static boolean isEmpty(String string) {
    if (string == null || string.length() == 0)
      return true;

    int l = string.length();
    for (int i = 0; i < l; i++) {
      if (!isWhitespace(string.codePointAt(i)))
        return false;
    }
    return true;
  }


  /**
   * 正则校验 验证表单
   *
   * @param value
   * @param reg
   * @return
   */
  public static boolean valid(String value, String reg) {
    if (value == null || reg == null) {
      return false;
    }
    return value.matches(reg);
  }

  public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
    if (needRecycle) {
      bmp.recycle();
    }

    byte[] result = output.toByteArray();
    try {
      output.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  /**
   * dip单位转换成像素单位
   *
   * @param context  上下文对象
   * @param dipValue 需要转换的值
   * @return
   * @author Jeff
   */
  public static int dip2px(Context context, float dipValue) {
    if (context == null) {
      return 1;
    }
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  /**
   * 创建控件半园形效果
   *
   * @param strokeWidth 边框宽
   * @param storkeColor 边框辨色
   * @param roundRadius 圆角半径
   * @param fillColor   填充颜色
   * @return
   * @author Aaron
   */
  public static Drawable createGradientDrawable(int strokeWidth, int storkeColor, int roundRadius, int fillColor) {
    GradientDrawable drawable = new GradientDrawable();//创建drawable
    drawable.setColor(fillColor);
    drawable.setCornerRadius(roundRadius);
    drawable.setStroke(strokeWidth, storkeColor);
    return drawable;
  }

  public static Drawable createRectDrawable(int fillColor, int roundRadius) {
    return createGradientDrawable(0, fillColor, roundRadius, fillColor);
  }

  //    i:128;s:92:\"http://www.weilaicaijing.com/wp-content/uploads/2017/12/avatar_user_3_1513218915-128x128.jpg\";
  public static String getSubImageUrl(String imageUrl, String firstTag, String lastTag) {
    if (BaseUtils.isEmpty(imageUrl)) {
      return "";
    }
    if (imageUrl.contains(firstTag) && imageUrl.contains(lastTag)) {
      int first = imageUrl.indexOf(firstTag) + firstTag.length();
      int last = imageUrl.indexOf(lastTag);
      String tmp = imageUrl.substring(first, last).replace("\"", "");
      return tmp;
    }
    return "";
  }

  /**
   * 把View绘制到Bitmap上
   *
   * @param comBitmap 需要绘制的View
   * @param width     该View的宽度
   * @param height    该View的高度
   * @return 返回Bitmap对象
   * add by csj 13-11-6
   */
  public static Bitmap getViewBitmap(View comBitmap, int width, int height) {
    Bitmap bitmap = null;
    if (comBitmap != null) {
      comBitmap.clearFocus();
      comBitmap.setPressed(false);

      boolean willNotCache = comBitmap.willNotCacheDrawing();
      comBitmap.setWillNotCacheDrawing(false);

      // Reset the drawing cache background color to fully transparent
      // for the duration of this operation
      int color = comBitmap.getDrawingCacheBackgroundColor();
      comBitmap.setDrawingCacheBackgroundColor(0);
      float alpha = comBitmap.getAlpha();
      comBitmap.setAlpha(1.0f);

      if (color != 0) {
        comBitmap.destroyDrawingCache();
      }

      int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
      int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
      comBitmap.measure(widthSpec, heightSpec);
      comBitmap.layout(0, 0, width, height);

      comBitmap.buildDrawingCache();
      Bitmap cacheBitmap = comBitmap.getDrawingCache();
      if (cacheBitmap == null) {
        Logger.e("view.ProcessImageToBlur", "failed getViewBitmap(" + comBitmap + ")",
                new RuntimeException());
        return null;
      }
      bitmap = Bitmap.createBitmap(cacheBitmap);
      // Restore the view
      comBitmap.setAlpha(alpha);
      comBitmap.destroyDrawingCache();
      comBitmap.setWillNotCacheDrawing(willNotCache);
      comBitmap.setDrawingCacheBackgroundColor(color);
    }
    return bitmap;
  }

  private static boolean hasExternalStoragePermission(Context context) {
    int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
    return perm == 0;
  }

  public static File getOwnCacheDirectory(Context context, String cacheDir) {
    File appCacheDir = null;
    if ("mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
      appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
    }

    if (appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
      appCacheDir = context.getCacheDir();
    }

    return appCacheDir;
  }

  public static String getVersionName(Context context) {
    // 获取packagemanager的实例
    PackageManager packageManager = context.getPackageManager();
    // getPackageName()是你当前类的包名，0代表是获取版本信息
    try {
      PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      String version = packInfo.versionName;
      return version;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "V1.0.0";
  }

  public static int getVersionCode(Context context) {
    // 获取packagemanager的实例
    PackageManager packageManager = context.getPackageManager();
    // getPackageName()是你当前类的包名，0代表是获取版本信息
    try {
      PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
      int version = packInfo.versionCode;
      return version;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 1;
  }

  // 两次点击按钮之间的点击间隔不能少于1000毫秒
  private static final int MIN_CLICK_DELAY_TIME = 5000;
  private static long lastClickTime;

  public static boolean isFastClick() {
    boolean flag = false;
    long curClickTime = System.currentTimeMillis();
    if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
      flag = true;
    }
    lastClickTime = curClickTime;
    return flag;
  }

  public static String md5(String string) {
    if (TextUtils.isEmpty(string)) {
      return "";
    }
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(string.getBytes());
      String result = "";
      for (byte b : bytes) {
        String temp = Integer.toHexString(b & 0xff);
        if (temp.length() == 1) {
          temp = "0" + temp;
        }
        result += temp;
      }
      return result;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 验证输入的名字是否为“中文”或者是否包含“·”
   */
  public static boolean isLegalName(String name) {
    if (name.contains("·") || name.contains("?")) {
      if (name.matches("^[\\u4e00-\\u9fa5]+[·?][\\u4e00-\\u9fa5]+$")) {
        return true;
      } else {
        return false;
      }
    } else {
      if (name.matches("^[\\u4e00-\\u9fa5]+$")) {
        return true;
      } else {
        return false;
      }
    }
  }


  // 根据路径获得图片并压缩，返回bitmap用于显示
  public static Bitmap getSmallBitmap(String filePath) {
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(filePath, options);
    // Calculate inSampleSize
    options.inSampleSize = 1;
    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(filePath, options);
  }

  //把bitmap转换成String
  public static String bitmapToString(String filePath) {
    Bitmap bm = getSmallBitmap(filePath);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //1.5M的压缩后在100Kb以内，测试得值,压缩后的大小=94486字节,压缩后的大小=74473字节
    //这里的JPEG 如果换成PNG，那么压缩的就有600kB这样
    bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
    byte[] b = baos.toByteArray();
    return Base64.encodeToString(b, Base64.DEFAULT);
  }

}
