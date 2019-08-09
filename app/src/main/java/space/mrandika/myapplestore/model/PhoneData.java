package space.mrandika.myapplestore.model;

import java.util.ArrayList;

import space.mrandika.myapplestore.model.Phone;

public class PhoneData {
    public static String[][] data = new String[][] {
            {"https://www.unionwireless.com/content/images/thumbs/0000283_apple-iphone-8-plus_550.png", "iPhone 7", "iPhone 7 is the most powerful phone ever made yet.", "32 GB", "Black", "7000000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000283_apple-iphone-8-plus_550.png", "iPhone 7", "iPhone 7 is the most powerful phone ever made yet.", "64 GB", "Black", "8000000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000283_apple-iphone-8-plus_550.png", "iPhone 7", "iPhone 7 is the most powerful phone ever made yet.", "128 GB", "Black", "9000000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000283_apple-iphone-8-plus_550.png", "iPhone 8", "iPhone 8 is the most powerful phone ever made yet.", "64 GB", "Black", "15000000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000283_apple-iphone-8-plus_550.png", "iPhone 8", "iPhone 8 is the most powerful phone ever made yet.", "128 GB", "Black", "17500000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000387_apple-iphone-x_550.png", "iPhone X", "iPhone X is the most powerful phone ever made yet.", "64 GB", "Black", "17500000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000387_apple-iphone-x_550.png", "iPhone X", "iPhone X is the most powerful phone ever made yet.", "256 GB", "Black", "1900000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000568_apple-xs_550.png", "iPhone Xs", "iPhone Xs is the most powerful phone ever made yet.", "64 GB", "Black", "2000000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000580_apple-xr_550.png", "iPhone Xr", "iPhone Xr is the most powerful phone ever made yet.", "64 GB", "Black", "1500000"},
            {"https://www.unionwireless.com/content/images/thumbs/0000580_apple-xr_550.png", "iPhone Xr", "iPhone Xr is the most powerful phone ever made yet.", "128 GB", "Black", "1700000"},
    };

    public static ArrayList<Phone> getListData() {
        ArrayList<Phone> list = new ArrayList<>();
        for (String[] aData: data) {
            Phone phone = new Phone();
            phone.setPhoto(aData[0]);
            phone.setName(aData[1]);
            phone.setDescription(aData[2]);
            phone.setStorage(aData[3]);
            phone.setColor(aData[4]);
            phone.setPrice(Integer.parseInt(aData[5]));
            list.add(phone);
        }
        return list;
    }
}
