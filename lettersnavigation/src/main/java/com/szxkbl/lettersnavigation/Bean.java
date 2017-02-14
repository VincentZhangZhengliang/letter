package com.szxkbl.lettersnavigation;

/**
 * @author : Vincent
 * @time : 2017/2/14 16:25.
 * @Discription :
 */

public class Bean {
    private String name;
    private String letter;

    public Bean(String name, String letter) {
        this.name = name;
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", letter='" + letter + '\'' +
                '}';
    }
}
