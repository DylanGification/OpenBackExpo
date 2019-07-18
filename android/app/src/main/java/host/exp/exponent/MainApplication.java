package host.exp.exponent;

import android.content.Context;
import android.util.Log;

import com.facebook.react.ReactPackage;

import org.unimodules.core.interfaces.Package;

import java.util.Arrays;
import java.util.List;

import expo.loaders.provider.interfaces.AppLoaderPackagesProviderInterface;
import host.exp.exponent.generated.BasePackageList;
import okhttp3.OkHttpClient;

import com.facebook.react.shell.MainReactPackage;
import com.openback.OpenBack;
import com.openback.RNOpenBackPackage;
import com.facebook.react.ReactApplication;

public class MainApplication extends ExpoApplication implements AppLoaderPackagesProviderInterface<ReactPackage> {

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    // Needed for `react-native link`
    public List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                // Add your own packages here!
                // TODO: add native modules!

                // Needed for `react-native link`
                new MainReactPackage(),
                new RNOpenBackPackage()
        );
    }

    public List<Package> getExpoPackages() {
        return new BasePackageList().getPackageList();
    }

    @Override
    public String gcmSenderId() {
        return getString(R.string.gcm_defaultSenderId);
    }

    public static OkHttpClient.Builder okHttpClientBuilder(OkHttpClient.Builder builder) {
        // Customize/override OkHttp client here
        return builder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Init OpenBack
        initOpenBack(getApplicationContext());
        Log.d("OpenBackExpo", "OpenBack has been initialized");
    }

    public void initOpenBack(Context context) {
        try {
            OpenBack.start(new OpenBack.Config(context));
        } catch (Exception e) {
            Log.d("OpenBackExpo", "Whoops", e);
        }
    }
}
