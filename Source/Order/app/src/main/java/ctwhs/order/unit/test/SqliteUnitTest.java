package ctwhs.order.unit.test;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import ctwhs.order.dao.ProductEntDao;
import ctwhs.order.dao.ProductTypeEntDao;
import ctwhs.order.entity.ProductTypeEnt;
import ctwhs.order.sqlite.helper.DBHelper;



/**
 * Created by UNGTQ on 3/29/2017.
 */

public class SqliteUnitTest {

    private Context context;

    public SqliteUnitTest(Context context) {
        this.context = context;
    }

    public void test1(){
        context.deleteDatabase(DBHelper.DATABASE_NAME);

        ProductTypeEntDao productTypeEntDao = new ProductTypeEntDao(context);
        ProductEntDao productDao = new ProductEntDao(context);
        try {
            ProductTypeEnt productTypeEnt = new ProductTypeEnt();
            productTypeEnt.setProductTypeName("Dessert");
            productTypeEntDao.addProductType(productTypeEnt);

            productTypeEnt = new ProductTypeEnt();
            productTypeEnt.setProductTypeName("Soup");
            productTypeEntDao.addProductType(productTypeEnt);

            productTypeEnt = new ProductTypeEnt();
            productTypeEnt.setProductTypeName("Main dish");
            productTypeEntDao.addProductType(productTypeEnt);

            productTypeEnt = new ProductTypeEnt();
            productTypeEnt.setProductTypeName("Alcoholic drink");
            productTypeEntDao.addProductType(productTypeEnt);

            productTypeEnt = new ProductTypeEnt();
            productTypeEnt.setProductTypeName("Non alcoholic drink");
            productTypeEntDao.addProductType(productTypeEnt);

            productTypeEnt = new ProductTypeEnt();
            productTypeEnt.setProductTypeName("Other kind of products");
            productTypeEntDao.addProductType(productTypeEnt);

            List<ProductTypeEnt> entList = productTypeEntDao.queryAll();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            productTypeEntDao.close();
        }
    }

    public void demoTranslateApiGoogle(){
//        // Instantiates a client
//        Translate translate = (Translate) TranslateOptions.builder().build().service();// (Translate) TranslateOptions.defaultInstance().service();
//
//        // The text to translate
//        String text = "Hello, world!";
//
//        List<String> arrText = new ArrayList<String>();
//        arrText.add(text);
//
//
//        // Translates some text into Russian
//        try {
//            translate.translations().list(arrText, "vn");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            GoogleAPI.setHttpReferrer("link");
//            GoogleAPI.setKey("key");
//            String translateText = Translate.DEFAULT.execute("Hello, world!", Language.ENGLISH, Language.JAPANESE);
//            Log.i("TAG", translateText);
//        } catch (GoogleAPIException e) {
//            e.printStackTrace();
//        }
    }
}
