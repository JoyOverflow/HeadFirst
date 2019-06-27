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
 * 创建一个仅包含列表的片段（派生自ListFragment无需布局）
 */
public class WorkListFragment extends ListFragment {

    //定义一个接口类型和接口成员字段（初始为null将在与父活动绑定时赋值）
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
     * 创建片段视图
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
        //将适配器设置给列表视图
        setListAdapter(adapter);

        //直接使用基类的布局
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * 片段与父活动关联（将父活动实例赋值给片段类接口成员）
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //活动要实现此接口
        this.listener = (Listener)context;
    }
    /**
     * 重写内置的列表项单击事件（执行接口成员的实现方法）
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
