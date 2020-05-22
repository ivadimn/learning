package ru.ivadimn.drawer;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

public class ActivityA extends AppCompatActivity {

    private String[] cats;
    private ListView lvCats;
    DrawerLayout drawerLayout;

    private ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        cats = getResources().getStringArray(R.array.cats_array_ru);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        lvCats = (ListView) findViewById(R.id.left_drawer);
        lvCats.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, cats));
        lvCats.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Фрагмент, который появится в основной части разметки и покажет кота
     */
    public static class CatFragment extends Fragment {
        public static final String ARG_CAT_NUMBER = "cat_number";

        public CatFragment() {
            // Для фрагмента требуется пустой конструктор
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cat, container, false);
            int i = getArguments().getInt(ARG_CAT_NUMBER);
            // имена котов на англ. для нахождения имен файлов
            String catName = getResources().getStringArray(R.array.cats_array)[i];

            String catNameTitle = getResources().getStringArray(R.array.cats_array_ru)[i];

            int imageId = getResources().getIdentifier(catName.toLowerCase(Locale.ROOT),
                    "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.imageViewCat)).setImageResource(imageId);
            getActivity().setTitle(catNameTitle);
            return rootView;
        }
    }

    private void selectItem(int position) {
        // Обновляем содержимое экрана, заменяя фрагменты
        Fragment fragment = new CatFragment();
        Bundle args = new Bundle();
        args.putInt(CatFragment.ARG_CAT_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // обновим выбранный элемент списка и закрываем панель
        lvCats.setItemChecked(position, true);
        setTitle(cats[position]);
        drawerLayout.closeDrawer(lvCats);
    }

}
