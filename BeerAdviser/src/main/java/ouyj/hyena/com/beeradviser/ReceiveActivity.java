package ouyj.hyena.com.beeradviser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "key_message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        //获得传递过来的意图并取出数据
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        //判断字串是否为空


        //查找文本显示视图,设置文本
        TextView messageView = findViewById(R.id.message);
        messageView.setText(message);
    }
}
