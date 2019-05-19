package ouyj.hyena.com.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    //保存意图传递数据的键
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //意图中取得当前训练项目ID
        int workId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);

        //从布局中得到片段控件的引用（并将训练ID传递给它）
        WorkDetailFragment detailFragment = (WorkDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        detailFragment.setWorkout(workId);
    }
}
