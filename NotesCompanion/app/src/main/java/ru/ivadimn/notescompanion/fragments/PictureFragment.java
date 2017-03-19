package ru.ivadimn.notescompanion.fragments;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.ivadimn.notescompanion.R;

/**
 * Created by vadim on 12.03.17.
 */

public class PictureFragment extends PagerFragment {

    private ProviderInfo[] providers;
    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_fragment, container, false);
        tv = (TextView) view.findViewById(R.id.tv_id);

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
        tv.setText(sb.toString());                    }
        return view;
    }

}
