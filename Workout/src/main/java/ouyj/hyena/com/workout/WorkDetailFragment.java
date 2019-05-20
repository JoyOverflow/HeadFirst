package ouyj.hyena.com.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 详情片段
 */
public class WorkDetailFragment extends Fragment {

    //当前训练项目ID
    private long workId;
    public void setWorkout(long id) {
        this.workId = id;
    }

    /**
     * 通过训练项目ID得到项目类对象
     */
    @Override
    public void onStart() {
        super.onStart();

        //得到片段的根视图
        View view = getView();
        if (view != null) {
            Workout workout = Workout.workouts[(int) workId];

            //设置视图的文本内容
            TextView title =  view.findViewById(R.id.textTitle);
            TextView description =  view.findViewById(R.id.textDescription);
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }


    public WorkDetailFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work_detail, container, false);
    }

    /**
     * 片段重建时恢复训练项目ID
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //恢复旋转前保存的数据
        //设被旋转片段事务会成功重放，无需把秒表片段重新加载
        if (savedInstanceState != null) {
            workId = savedInstanceState.getLong("workoutId");
        }
        else{
            //片段第一次创建（替换片段中的片段）

            //创建片段实例
            WatchFragment watchFragment = new WatchFragment();
            //启动片段事务（此片段管理器与父片段关联而不与活动关联）
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            //替换片段并把它增加到后退堆栈
            ft.replace(R.id.stopwatch_container, watchFragment);
            ft.addToBackStack(null);
            //实现谈入谈出效果
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //提交事务
            ft.commit();
        }
    }
    /**
     * 设备旋转前保存当前训练项目ID
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutId", workId);
    }

}
