# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\zybang\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#udesk
-keep class udesk.** {*;}
-keep class cn.udesk.**{*; }
#七牛
-keep class com.qiniu.android.dns.** {*; }
-keep class okhttp3.** {*;}
-keep class okio.** {*;}
-keep class com.qiniu.android.** {*;}
#smack
-keep class org.jxmpp.** {*;}
-keep class de.measite.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.xmlpull.** {*;}
#Android M 权限
-keep class rx.** {*;}
-keep class com.tbruyelle.rxpermissions.** {*;}

#其它
-keep class com.tencent.bugly.** {*; }
-keep class com.nostra13.universalimageloader.** {*;}
-keep class de.hdodenhof.circleimageview.** {*;}