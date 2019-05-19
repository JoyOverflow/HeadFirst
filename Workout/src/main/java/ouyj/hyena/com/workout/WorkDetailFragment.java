package ouyj.hyena.com.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        if (savedInstanceState != null) {
            workId = savedInstanceState.getLong("workoutId");
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
