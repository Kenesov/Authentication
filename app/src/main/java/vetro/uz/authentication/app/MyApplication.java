package vetro.uz.authentication.app;

import android.app.Application;

import vetro.uz.authentication.data.sources.MyDataPreferences;
import vetro.uz.authentication.domain.AppRepositoryImpl;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        MyDataPreferences.init(this);
        AppRepositoryImpl.init();
        super.onCreate();
    }
}
