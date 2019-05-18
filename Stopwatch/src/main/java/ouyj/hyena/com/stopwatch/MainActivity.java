package ouyj.hyena.com.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 按钮事件处理
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                //启动（通知秒表可进行计数）
                running = true;
                break;
            case R.id.stop_button:
                //停止（通知秒表应暂停计数）
                running = false;
                break;
            case R.id.reset_button:
                //重置（通知秒表暂停计数,并将秒数置0）
                running = false;
                seconds = 0;
                break;
        }
    }
    /**
     * 不断循环调用
     */
    private void runTimer() {

        //得到文本视图引用
        final TextView timeView = findViewById(R.id.time_view);

        //使用Handler提交代码
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                //将秒数转换为时分秒
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                //格式化字串并显示
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                //判断秒表可运行时（递加秒数）
                if (running) {
                    seconds++;
                }
                //1秒后再调用当前Runnable.run
                handler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * 设备旋转时保存指定成员变量（使其值在旋转后不变为0）
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        //outState.putBoolean("wasRunning", wasRunning);
    }
}
