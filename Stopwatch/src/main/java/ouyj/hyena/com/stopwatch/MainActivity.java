package ouyj.hyena.com.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //当前的秒数
    private int seconds = 0;
    //是否按下计时按钮
    private boolean running;
    //如果活动被电话中断时中止计数（秒表变为不可见前是否在运行）
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //当有旋转前的保存数据时恢复它
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        //启动计数器
        runTimer();
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
     * 只需调用一次，便会不断循环调用
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
        outState.putBoolean("wasRunning", wasRunning);
    }
    /**
     * 活动被转后台（不可见）或失去焦点（仍可见）时都会调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        //将当前状态（是否运行）保存起来，供活动继续时恢复
        wasRunning = running;
        //当前暂停秒表的计数
        running = false;
    }
    /**
     * 活动从后台移至前台（变为可见）或重获焦点时都会调用
     */
    @Override
    protected void onResume() {
        super.onResume();
        //如果之前秒表是运行的那么恢复运行
        if (wasRunning) {
            running = true;
        }
    }
}
