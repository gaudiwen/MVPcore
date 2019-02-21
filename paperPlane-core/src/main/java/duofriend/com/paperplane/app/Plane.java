package duofriend.com.paperplane.app;

import android.content.Context;
import android.util.ArrayMap;

import java.util.WeakHashMap;

/**
 * @author: Yzq
 * Created  on 2018/8/3 0003 18:15
 * Email: 277202583@qq.com
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Buddha bless, never BUG
 */

public final class Plane {

   public static Configurator init(Context context){
       getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context);
       return Configurator.getInstance();
   }

   public static ArrayMap<String,Object> getConfigurations(){
       return Configurator.getInstance().getPlaneConfigs();
   }

   public static Context getApplicationContext(){
     return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
   }
}
