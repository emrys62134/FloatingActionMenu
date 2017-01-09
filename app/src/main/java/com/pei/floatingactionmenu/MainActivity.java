package com.pei.floatingactionmenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView iv;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        iv = (ImageView) findViewById(R.id.iv);
        et = (EditText) findViewById(R.id.et);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contentString = et.getText().toString();
                if (!contentString.equals("")) {
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    iv.setImageBitmap(qrCodeBitmap);
                } else {
//                    Toast.makeText(SelectionSupplementActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //悬浮按钮
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.mipmap.ic_launcher);

        FloatingActionButton actionButton = new FloatingActionButton
                .Builder(this).setContentView(icon)
                .setPosition(4)// 数字代表不同位置1-5
                .build();

        actionButton.setScaleX(0.8f);
        actionButton.setScaleY(0.8f);
        actionButton.setX(-1f);
        actionButton.setY(-60f);



        SubActionButton.Builder itemBuilder = new
                SubActionButton.Builder(this);

        ImageView itemIconPhoto = new ImageView(this);
        ImageView itemIconSay = new ImageView(this);
        ImageView itemIconFind = new ImageView(this);


        itemIconPhoto.setImageResource(R.mipmap.ic_launcher);
        itemIconSay.setImageResource(R.mipmap.ic_launcher);
        itemIconFind.setImageResource(R.mipmap.ic_launcher);

        SubActionButton buttonPhoto = itemBuilder.setContentView(itemIconPhoto).build();
        SubActionButton buttonSay = itemBuilder.setContentView(itemIconSay).build();
        SubActionButton buttonFind = itemBuilder.setContentView(itemIconFind).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonPhoto)
                .addSubActionView(buttonSay)
                .addSubActionView(buttonFind)
                .attachTo(actionButton)
                .build();

        //扫码功能
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Toast.makeText(MainActivity.this, "开始扫码", Toast.LENGTH_SHORT).show();
                Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //跳转到生成二维码的页面
        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this , SelectionSupplementActivity.class);
//                startActivity(intent);
                Toast.makeText(MainActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
