package com.szxkbl.lettersnavigation;

import java.util.Comparator;

/**
 * @author : Vincent
 * @time : 2017/2/14 16:47.
 * @Discription :
 */

public class MyComparator implements Comparator<Bean> {

    @Override
    public int compare(Bean bean, Bean t1) {
        return bean.getLetter().compareTo(t1.getLetter());
    }
}
