package vetro.uz.authentication.data.sources;

import android.content.Context;
import android.content.SharedPreferences;

public class MyDataPreferences {
    private static MyDataPreferences instance;
    private static SharedPreferences pref;

    private MyDataPreferences(){}
    public static void init(Context context){
        if (instance == null){
            instance = new MyDataPreferences();
            pref = context.getSharedPreferences("AuthApp", Context.MODE_PRIVATE);
        }
    }
    public static MyDataPreferences getInstance(){ return instance;}
    public void saveData(String st){
        pref.edit().putString("DATA",st).apply();
    }
    public String getData(){
        return pref.getString("DATA", "");
    }
}
