package ouyj.hyena.com.starbuzz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    //用于传递意图数据
    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //取出意图内的数据
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINKNO);

        //从数据源（数组）得到单个类对象
        Drink drink = Drink.drinks[drinkNo];

        //设置图像
        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(drink.getImage());
        photo.setContentDescription(drink.getName());

        //设置文本显示视图
        TextView name = findViewById(R.id.name);
        name.setText(drink.getName());
        TextView description = findViewById(R.id.description);
        description.setText(drink.getDescription());
    }
}
