package ouyj.hyena.com.workout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 内嵌详情片段内的片段
 */
public class WatchFragment extends Fragment implements View.OnClickListener{

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    public WatchFragment() {

    }

    /**
     * 加载秒表片段布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_watch, container, false);

        //运行计时器
        runTimer(layout);

        //查找按钮视图引用（设置事件侦听器）
        Button startButton = layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return layout;
    }
    /**
     * 按钮的事件处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                running = true;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.reset_button:
                running = false;
                seconds = 0;
                break;
        }
    }

    /**
     * 片段创建时恢复旋转前的保存数据
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if (wasRunning) {
                running = true;
            }
        }
    }
    /**
     * 旋转前保存必要数据
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    /**
     * 启动后不断循环的方法
     * @param view
     */
    private void runTimer(View view) {
        final TextView timeView = view.findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                //将秒数格式化为字串
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);

                //累加秒数
                if (running) {
                    seconds++;
                }
                //1秒后再执行
                handler.postDelayed(this, 1000);
            }
        });
    }

    /**
     * 保存暂停前的运行状态并停止计数
     */
    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }
    /**
     * 根据暂停前的运行状态来确定是否继续计数
     */
    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
}
