package jianqiang.com.testandfix1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import jianqiang.com.testandfix1.test.A;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "euler";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, A.a("good"));
        Log.e(TAG, "" + new A().b("s1", "s2"));
        Log.e(TAG, "" + new A().getI());

        //热修复，加这句话
        Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}