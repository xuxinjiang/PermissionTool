package com.skl.permissiontool;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.skl.permissionlib.PermissionUtil;
import com.skl.permissionlib.PermissionsInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //可以传数组,还可以传单个多个
        String[] array = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE};

        //可以传list集合
        List<String> list = new ArrayList<>();
        list.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        list.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        list.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        PermissionUtil.builder(this).addPermissions(array).addPerName("定位").execute(new PermissionsInterface() {
            @Override
            public void allAgree() {
                Log.e("yyy", "-----------所有权限被授权时调用---------->");
            }

            @Override
            public void refuse() {
                Log.e("yyy", "-----------至少有一项权限没被授权时调用---------->");
            }

            @Override
            public void agree(String permission) {
                Log.e("yyy", "-----------通过授权的权限---------->" + permission);
            }

            @Override
            public void refuse(String permission) {
                Log.e("yyy", "-----------被拒绝的权限---------->" + permission);
            }

            @Override
            public void refusAndNotPrompt(String permission) {
                Log.e("yyy", "-----------被拒绝且选择了不再提示的权限---------->" + permission);
            }
        });

    }
}
