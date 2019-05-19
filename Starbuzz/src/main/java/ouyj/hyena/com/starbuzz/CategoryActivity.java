package ouyj.hyena.com.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取到活动自带的列表视
        ListView listDrinks = getListView();

        //从类中得到数组
        //创建数组适配器（上下文,布局,数组）
        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drink.drinks);
        //为列表视设置适配器
        listDrinks.setAdapter(listAdapter);
    }
    /**
     * 重写活动内置的列表视单击事件
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //发送意图并传递数据
        Intent intent = new Intent(CategoryActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_DRINKNO, (int) id);
        startActivity(intent);
    }
}
