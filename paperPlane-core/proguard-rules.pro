#指定压缩级别
-optimizationpasses 5

#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
#保留行号
-keepattributes SourceFile,LineNumberTable

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment


#包名不混合大小写
-dontusemixedcaseclassnames

#不跳过非公共的库的类
-dontskipnonpubliclibraryclasses

#混淆时记录日志
-verbose

#关闭预校验
-dontpreverify

#不优化输入的类文件
-dontoptimize

#保护注解
-keepattributes *Annotation*

#保持所有拥有本地方法的类名及本地方法名
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

#保持Activity中View及其子类入参的方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#枚举
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}

#Parcelable
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

#R文件的静态成员
-keepclassmembers class **.R$* {
    public static <fields>;
}

-dontwarn android.support.**

#retrofit 混淆保护
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn okhttp3.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn retrofit2.Platform$Java8

#rxlifecycle2
-dontwarn com.trello.rxlifecycle2.**
#微兔打印机
-dontwarn com.printsdk.**

-dontwarn org.xmlpull.v1.**
-dontwarn org.**

-dontwarn com.ums.**
#keep相关注解
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

-keep class org.xmlpull.v1.** { *; }


-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

-dontwarn com.gt.api.**
-keep class com.gt.api.** { *; }
#该项目的bean类
-keep class com.gt.mbparentmodule.bean.** { *; }


#fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

#crypto
-dontwarn com.sun.crypto.**
-keep class com.sun.crypto.** { *; }

#slf4j
-dontwarn org.slf4j.**
-keep class org.slf4j.** { *; }
#commons
-dontwarn org.apache.commons.**
-keep class org.apache.commons.** { *; }

-keep class com.gt.mbparentmodule.widget.** { *; }


-dontwarn org.bouncycastle.**
-keep class org.bouncycastle.** { *; }
-dontwarn com.synodata.**
-keep class com.synodata.** { *; }
-dontwarn com.gt.mbparentmodule.share.**
-keep class com.gt.mbparentmodule.share.** { *; }
