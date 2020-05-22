package ru.ivadimn.notescompanion.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;

/**
 * Created by vadim on 27.04.17.
 */

public class Utils {
    private static ProviderInfo[] providers;

    /*static {
        PackageManager pm = getActivity().getPackageManager();
        StringBuilder sb = new StringBuilder();
        sb.append("Count providers: " + pm.getInstalledPackages(PackageManager.GET_PROVIDERS).size() + "\n");

        for (PackageInfo pack : getActivity().getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
            providers = pack.providers;
            if (providers != null) {
                for (ProviderInfo provider : providers) {
                    if (provider.authority != null) {
                        sb.append(provider.authority + "\n");
                    }
                }
            }
            tv.setText(sb.toString());
        }
    }*/
}
