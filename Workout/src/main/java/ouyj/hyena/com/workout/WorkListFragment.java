package ouyj.hyena.com.workout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 列表片段
 */
public class WorkListFragment extends ListFragment {

    //片段的监听接口成员
    private Listener listener;
    interface Listener {
        void itemClicked(long id);
    }

    /**
     * 构造方法
     */
    public WorkListFragment() {
    }

    /**
     * 直接使用基类的布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState)
    {
        //获取字串数组
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();
        }

        //创建数组适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                names);

        //适配器绑定到列表视图
        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * 片段与活动关联
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //活动要实现此接口
        this.listener = (Listener)context;
    }
    /**
     * 列表视图项被单击时触发
     * @param listView
     * @param itemView
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id)
    {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }










}
