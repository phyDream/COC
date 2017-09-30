package com.cdlixin.coc.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.cdlixin.coc.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片加载帮助类
 */

public class ImageUtil {

    public static final int LOW_RATING = 1;
    public static final int NORMAL_RATING = 2;
    public static final int HIGH_RATING = 3;


    //Picasso 加载网络图片
    public static void loadImg(Context context, String imgPath, ImageView imageView){
        if(TextUtils.isEmpty(imgPath)){
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder_img)
                .error(R.drawable.img_load_error)
                .fitCenter()
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(imgPath)
                .apply(options)
                .into(imageView);
    }

    //picasso加载聊天大图
    public static void LoadImage(Context context , String imgUrl, ImageView img){
        if(TextUtils.isEmpty(imgUrl)){
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder_img)
                .error(R.drawable.img_load_error)
                .fitCenter()
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(img);
    }

    /**
     * 加载首页广告
     * @param context
     * @param imgPath
     * @param imageView
     */
    public static void loadHomeADImage(Context context, String imgPath, ImageView imageView){
        if(TextUtils.isEmpty(imgPath)){
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(imgPath)
                .apply(options)
                .into(imageView);


    }

    // 可信度评价显示
    public static int getCreditworthiness(String trade_rating){
        long rating = Long.parseLong(trade_rating);

        int currentRating = -1;
        if(rating >=6.6){
            currentRating = HIGH_RATING;
        }else if( rating >=3.3 ){
            currentRating = NORMAL_RATING;
        }else if (rating <3.3){
            currentRating = LOW_RATING;
        }

        return currentRating;
    }


    /**
     * 将图片转换成Base64编码的字符串
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     *base64编码字符集转化成图片文件。
     * @param base64Str
     * @param path 文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str, String path){
        byte[] data = Base64.decode(base64Str, Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if(data[i] < 0){
                //调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

}
