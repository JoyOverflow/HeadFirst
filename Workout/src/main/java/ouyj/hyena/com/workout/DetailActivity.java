package ouyj.hyena.com.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 加载片段的活动类
 * 1.布局中指定片段类
 * 2.后动类中得到片段引用并设置训练ID
 * 3.设备旋转时，片段类中负责保存并恢复训练ID
 */
public class DetailActivity extends AppCompatActivity {

    //保存意图传递数据的键
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //从意图中取得当前训练项目ID
        int workId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);

        //从片段管理器中得到片段引用（并设置训练ID）
        WorkDetailFragment fragment = (WorkDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        fragment.setWorkout(workId);
    }
}
