package ouyj.hyena.com.bitpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * 加载菜单资源
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //(1)加载菜单资源文件
        getMenuInflater().inflate(R.menu.mains, menu);

        //(2)得到共享动作引用类对象
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        //设置它的共享意图
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"This is example text");
        shareAction.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }
    /**
     * 单击菜单项（返回true表明已处理完单击动作）
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:

                //启动订单活动
                Intent intent = new Intent(
                        this,
                        OrderActivity.class
                );
                startActivity(intent);

                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
