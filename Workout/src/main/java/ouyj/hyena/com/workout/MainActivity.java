package ouyj.hyena.com.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 主活动（需实现子列表片段的接口）
 */
public class MainActivity extends AppCompatActivity implements WorkListFragment.Listener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 子片段的列表项被点击后会调用此方法
     * @param id
     */
    @Override
    public void itemClicked(long id) {
        //是否存在指定控件(帧布局)
        View container = findViewById(R.id.container);
        if (container != null) {

            //(平板)在活动内加载指定片段
            WorkDetailFragment details = new WorkDetailFragment();
            details.setWorkout(id);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
        else {
            //(手机)启动另一个活动
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }
    }
}
