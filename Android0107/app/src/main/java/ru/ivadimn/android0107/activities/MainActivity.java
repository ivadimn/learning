package ru.ivadimn.android0107.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.ivadimn.android0107.R;
import ru.ivadimn.android0107.adapters.FragmentPagerAdapter;
import ru.ivadimn.android0107.fragments.PersonFragment;
import ru.ivadimn.android0107.fragments.PersonListFragment;
import ru.ivadimn.android0107.model.Person;
import ru.ivadimn.android0107.model.Repository;

public class MainActivity extends AppCompatActivity implements PersonListFragment.OnSelectItemListener,
        PersonFragment.OnPersonFragmentListener {


    public static final int VIEW_LIST = 0;
    public static final int VIEW_PERSON = 1;


    private boolean  deleteMode = false;
    private boolean  editMode = false;
    private int modeView = VIEW_LIST;

    private int selectedIndex = -1;
    private boolean isTablet;

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private PersonListFragment personListFragment;
    private PersonFragment personFragment;
    private Menu menu;
    private MenuItem itemCancel;
    private MenuItem itemDelete;
    private MenuItem itemBack;
    private MenuItem itemEdit;
    private MenuItem itemSave;

    private String titleList;
    private String titlePerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleList = getString(R.string.list);
        titlePerson = getString(R.string.person);
        setTitle(titleList);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        personListFragment = PersonListFragment.getFragment();
        personFragment = new PersonFragment();
        if (isTablet) {
            initUITablet();
        }
        else {
            initUIPhone();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;
        itemCancel = menu.findItem(R.id.item_cancel_id);
        itemDelete = menu.findItem(R.id.item_delete_id);
        itemBack = menu.findItem(R.id.item_back_id);
        itemEdit = menu.findItem(R.id.item_edit_id);
        itemSave = menu.findItem(R.id.item_save_id);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item_cancel_id:
                personListFragment.cancelDelete();
                deleteMode = false;
                updateListMenu();
                break;
            case R.id.item_delete_id:
                deletePersons();
                updateListMenu();
                break;
            case R.id.item_back_id:
                editMode = false;
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.item_edit_id:
                editMode = true;
                updatePersonMenu();
                personFragment.setEditEnable(true);
                break;
            case R.id.item_save_id:
                updatePerson();
                break;

        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Repository.savePersons();
    }

    ////PersonListFragment.OnSelectItemListener
    @Override
    public void onSelectItem(int position) {
        selectedIndex = position;
        editMode = (selectedIndex < 0);
        personFragment.initData();
        if (!isTablet)
            viewPager.setCurrentItem(1, true);
        else {
            modeView = VIEW_PERSON;
            updateMenu();
        }

    }

    @Override
    public void setDeleteMode(boolean mode) {
        deleteMode = true;
        updateMenu();
    }


    //PersonFragment.OnPersonFragmentListener
    @Override
    public Person getSelectedPerson() {
        return Repository.getPerson(selectedIndex);
    }


    ///////// PersonAdapter/////////
    public Person getData() {
        return personFragment.getData();
    }


    ///////// PersonListAdapter/////////
    private void deletePersons() {
        if (deleteMode) {
            personListFragment.deletePersons();
            deleteMode = false;
            selectedIndex = -1;
            personFragment.initData();

        }
    }


    private void updateMenu() {
        switch(modeView) {
            case VIEW_LIST:
                menu.setGroupVisible(R.id.list_group_id, true);
                menu.setGroupVisible(R.id.edit_group_id, false);
                updateListMenu();
                break;
            case VIEW_PERSON:
                menu.setGroupVisible(R.id.list_group_id, false);
                menu.setGroupVisible(R.id.edit_group_id, true);
                updatePersonMenu();
                break;
        }
    }

    private void updateListMenu() {
        itemCancel.setVisible(deleteMode);
        itemDelete.setVisible(deleteMode);
    }

    private void updatePersonMenu() {
        itemBack.setVisible(!isTablet);
        itemEdit.setVisible(!editMode);
        itemSave.setVisible(editMode);
    }

    private void updatePerson() {
        Person p = personFragment.getData();
        if (selectedIndex < 0)
            Repository.addPerson(p);
        else
            Repository.setPerson(selectedIndex, p);

        personListFragment.updateList(true);
        editMode = false;
        if (!isTablet)
            viewPager.setCurrentItem(0, true);
        else {
            modeView = VIEW_LIST;
            updateMenu();
        }

    }

    private void initUIPhone() {
        adapter = new FragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(personListFragment);

        adapter.addFragment(personFragment);
        viewPager = (ViewPager) findViewById(R.id.fragment_pager_id);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("VIEW_PAGER", "onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("VIEW_PAGER", "onPageSelected " + position);
                setTitle((position == 0) ? titleList : titlePerson);
                modeView = (position == 0) ? VIEW_LIST : VIEW_PERSON;
                updateMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("VIEW_PAGER", "onPageScrollStateChanged " + state);
            }
        });
    }

    private void initUITablet() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.list_container, personListFragment, PersonListFragment.TAG)
                .add(R.id.person_container, personFragment, personFragment.TAG)
                .commit();
        findViewById(R.id.list_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeView = VIEW_LIST;
                updateMenu();
            }
        });
        findViewById(R.id.person_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeView = VIEW_PERSON;
                updateMenu();
            }
        });
    }

}
