package cn.see.util.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * @日期：2018/6/6
 * @作者： GuoXinBo
 * @邮箱： guoxinbo@banling.com
 * @说明： 设置默认配置
 */
public class GlideConfiguration implements GlideModule {
    private static final int MEMORY_MAX_SPACE = (int) (Runtime.getRuntime().maxMemory() / 8);

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置加载图片的样式,比默认图片质量好,但占用内存会大点
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        builder.setMemoryCache(new LruResourceCache(MEMORY_MAX_SPACE));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, getDiskFileString(context, "glideCache"), 20 * 1024 * 1024));

    }

    //设置缓存目录
    private String getDiskFileString(Context context, String str) {
        File dirFile = new File(context.getCacheDir().getAbsolutePath().toString() + str);
        File tempFile = new File(dirFile, "bitmaps");
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        return tempFile.getAbsolutePath().toString();
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
