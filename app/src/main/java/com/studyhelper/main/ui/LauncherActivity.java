package com.studyhelper.main.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.coder.zzq.smartshow.dialog.DialogBtnClickListener;
import com.coder.zzq.smartshow.dialog.NotificationDialog;
import com.coder.zzq.smartshow.dialog.SmartDialog;
import com.studyhelper.main.MainActivity;
import com.studyhelper.ui.BaseInitialActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2019/7/8.
 * @author jogern
 */
public class LauncherActivity extends BaseInitialActivity {

      private static final int PERMISSIONS_REQUEST = 0x64;

      private List<String>       unPermissions = new ArrayList<>();
      private NotificationDialog notifySureDialog;
      private NotificationDialog notifyPermissionDialog;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                  String[] permissions = new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest
                          .permission.WRITE_EXTERNAL_STORAGE,
//                          Manifest.permission.RECORD_AUDIO,
//                          Manifest.permission.READ_PHONE_STATE,
//                          Manifest.permission.LOCATION_HARDWARE,
                  };
                  int granted = PackageManager.PERMISSION_GRANTED;
                  for (String permission : permissions) {
                        if (ContextCompat.checkSelfPermission(this, permission) != granted) {
                              unPermissions.add(permission);
                        }
                  }
                  if (!unPermissions.isEmpty()) {
                        showSure(unPermissions);
                        return;
                  }
            }
            gotoMain();
      }

      private void gotoMain() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
      }

      @Override
      public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            if (requestCode == PERMISSIONS_REQUEST && grantResults.length > 0) {
                  List<String> refusePermissions = new ArrayList<>();
                  for (int i = 0 ; i < grantResults.length ; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                              continue;
                        }
                        refusePermissions.add(unPermissions.get(i));
                  }
                  if (refusePermissions.isEmpty()) {
                        gotoMain();
                  } else {
                        openPermission(refusePermissions);
                  }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      }

      private void showSure(final List<String> unPermissions) {
            if (notifySureDialog != null) {
                  notifySureDialog.dismiss();
            }
            notifySureDialog = new NotificationDialog();
            notifySureDialog.message("让允许权限申请").messageStyle(0, 20, false);
            notifySureDialog.cancelable(false).cancelableOnTouchOutside(false);
            notifySureDialog.confirmBtn("确定", new DialogBtnClickListener() {
                  @Override
                  public void onBtnClick(SmartDialog smartDialog, int i, Object o) {
                        //将List转为数组
                        String[] permissions = unPermissions.toArray(new String[0]);
                        ActivityCompat.requestPermissions(LauncherActivity.this, permissions, PERMISSIONS_REQUEST);
                        smartDialog.dismiss();
                  }
            });
            notifySureDialog.showInActivity(this);
      }


      void openPermission(List<String> refusePermissions) {
            StringBuilder title = new StringBuilder("请打开：\n");
            String fileTip = "";
            for (String permission : refusePermissions) {
                  if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission) || Manifest.permission
                          .WRITE_EXTERNAL_STORAGE.equals(permission)) {
                        fileTip = "文件读写权限\n";
                  } else if (Manifest.permission.RECORD_AUDIO.equals(permission)) {
                        title.append("录制音频权限\n");
                  }
            }
            title.append(fileTip);
            if (notifyPermissionDialog != null) {
                  notifyPermissionDialog.dismiss();
            }
            notifyPermissionDialog = new NotificationDialog();
            notifyPermissionDialog.message(title.toString()).messageStyle(0, 18, false);
            notifyPermissionDialog.cancelable(false).cancelableOnTouchOutside(false);
            notifyPermissionDialog.confirmBtn("去打开权限", new DialogBtnClickListener() {
                  @Override
                  public void onBtnClick(SmartDialog smartDialog, int i, Object o) {
                        smartDialog.dismiss();
                        gotoOpenPermission();
                  }
            });
            notifyPermissionDialog.showInActivity(this);
      }

      private void gotoOpenPermission() {
            Intent localIntent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            } else {
                  localIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            }
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            String packageName = getPackageName();
            localIntent.setData(Uri.fromParts("package", packageName, null));
            startActivity(localIntent);
            finish();
      }


}
