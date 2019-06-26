package ouyj.hyena.com.starbuzz;

/**
 * 定义饮料类
 */
public class Drink {
    private String name;
    private String description;
    private int imageId;

    //返回饮料对象的数组
    public static final Drink[] drinks = {
        new Drink(
                "Latte",
                "A couple of espresso shots with steamed milk",
                R.drawable.latte),
        new Drink(
                "Cappuccino",
                "Espresso, hot milk, and a steamed milk foam",
                R.drawable.cappuccino),
        new Drink(
                "Filter",
                "Highest quality beans roasted and brewed fresh",
                R.drawable.filter)
    };

    /**
     * 构造方法（名称，描述，图像资源ID）
     * @param name
     * @param description
     * @param imageId
     */
    private Drink(String name, String description, int imageId) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public int getImage() {
        return imageId;
    }

    /**
     * 类对象的描述
     * @return
     */
    public String toString() {
        return this.name;
    }
}
