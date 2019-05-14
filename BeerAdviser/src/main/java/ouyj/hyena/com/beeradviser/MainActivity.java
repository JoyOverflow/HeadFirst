package ouyj.hyena.com.beeradviser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 按钮单击事件处理（方法必需）
     * 1.public和void返回值
     * 2.view型参数,即触发它的GUI控件
     * @param view
     */
    public void onClickFind(View view) {

        //查找视图的引用
        TextView brands =  findViewById(R.id.brands);

        //获取列表框的选中项（将Object转换为String）
        Spinner color = findViewById(R.id.color);
        String beerType = String.valueOf(color.getSelectedItem());

        //根据颜色得到动态的啤酒集合
        BeerExpert expert = new BeerExpert();
        List<String> brandsList = expert.getBrands(beerType);

        //将泛型集合转换为字串
        StringBuilder sb = new StringBuilder();
        for (String brand : brandsList) {
            sb.append(brand).append('\n');
        }
        //设置文本显示视图的文本
        brands.setText(sb);
    }
    public void onClickSend(View view) {

        //要发送的内容（从文本编辑框得到）
        EditText messageView = findViewById(R.id.message);
        String message = messageView.getText().toString();

        Intent intent;
        switch (view.getId()) {
            case R.id.send1:

                //(1)创建意图来启动指定活动（显式）
                intent = new Intent(this,ReceiveActivity.class);
                //增加额外信息（键为接收活动的常量）
                intent.putExtra(ReceiveActivity.EXTRA_MESSAGE,message);
                startActivity(intent);

                break;
            case R.id.send2:

                //(2)创建意图通过指定一个动作来启动其他应用中的活动（隐式）
                intent = new Intent(Intent.ACTION_SEND);
                //目标活动需能处理此类型的数据
                intent.setType("text/plain");
                //传递数据
                //EXTRA_SUBJECT表示发送的是消息主题,EXTRA_TEXT表示消息内容
                intent.putExtra(Intent.EXTRA_SUBJECT, "消息的主题");
                intent.putExtra(Intent.EXTRA_TEXT, message);


                //设置选择器标题（从字串资源文件中获取字串值）
                String title = getString(R.string.chooser);
                //显示目前系统内能接收此意图的活动选择框（传入自己创建的意图,返回一个封装的选择器意图）
                Intent chosenIntent = Intent.createChooser(intent, title);
                //启动被用户选择的活动（它能处理此意图）
                startActivity(chosenIntent);

                break;
        }
    }
}
