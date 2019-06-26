package ouyj.hyena.com.starbuzz;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //列表视的单击事件处理（其内得到点击位置）
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listView,
                                            View v,
                                            int position,
                                            long id) {
                        //点击第0项时发送意图，并启动分类活动
                        if (position == 0) {
                            Intent intent = new Intent(
                                    MainActivity.this,
                                    CategoryActivity.class
                            );
                            startActivity(intent);
                        }
                    }
                };

        //查找列表视并设置单击事件侦听器
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}
