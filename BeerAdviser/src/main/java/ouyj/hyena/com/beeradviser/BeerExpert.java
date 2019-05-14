package ouyj.hyena.com.beeradviser;
import java.util.ArrayList;
import java.util.List;

/**
 * 啤酒专家类
 */
public class BeerExpert {
    /**
     * 根据颜色得到推荐的啤酒
     * @param color
     * @return
     */
    List<String> getBrands(String color) {
        //返回泛型集合
        List<String> brands = new ArrayList<>();
        //比较字串并根据其值动态返回
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}
