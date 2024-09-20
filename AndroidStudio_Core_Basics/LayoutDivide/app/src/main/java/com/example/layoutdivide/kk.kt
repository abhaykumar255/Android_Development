package com.example.layoutdivide

import android.R
import android.app.Activity
import android.os.Bundle


//class MyActivity : Activity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate()
//        /* Check login status */if (loggedIn === true) {
//            this.setContentView(R.layout.logged_in)
//        } else if (loggedIn === false) {
//            this.setContentView(R.layout.not_logged_in)
//        }
//    }
//}


val layoutId =
    if (GlobalStore.configData?._lang.equals("fr")){
    if (GlobalStore.configData?._lang.equals("fr")) R.layout.wyos_light_template
    else if (GlobalStore.configData?._lang.equals("fr") || gpoInfo.wyosTemplate == WYOS_DARK_TEMPLATE) R.layout.wyos_dark_template
    else if (GlobalStore.configData?._lang.equals("en")) R.layout.wyos_light_template_en
    else  R.layout.wyos_dark_template_en

val layoutId =
    if (GlobalStore.configData?._lang.equals("fr")) R.layout.wyos_light_template
    else if (GlobalStore.configData?._lang.equals("fr") || gpoInfo.wyosTemplate == WYOS_DARK_TEMPLATE) R.layout.wyos_dark_template
    else if (GlobalStore.configData?._lang.equals("en")) R.layout.wyos_light_template_en
    else  R.layout.wyos_dark_template_en

//
//public class Login_testActivity extends Activity {
//
////If logged, set user id.
//    private String loginId = null;
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(loginId==null || loginId.equals(""))
//        {
//            //Not Logged. show login form.
//            setContentView(R.layout.login);
//        }
//        else
//        {
//            //Logged. show user's photo.
//            setContentView(R.layout.main);
//        }
//    }

