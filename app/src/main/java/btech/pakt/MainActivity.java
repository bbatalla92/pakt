package btech.pakt;

import android.app.Activity;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import btech.pakt.fragments.History_Fragment;
import btech.pakt.fragments.Item_Profile_Fragment;
import btech.pakt.fragments.Profile_Fragment;
import btech.pakt.fragments.Search_Fragment;

public class MainActivity extends FragmentActivity implements AppCompatCallback{

    // Fragment Manager and Fragments
    FragmentManager fm;
    public Profile_Fragment profile;
    Item_Profile_Fragment itemDesc;
    Search_Fragment searchFrag;
    History_Fragment historyFrag;

    // NavDrawer
    public static Drawer result;
    ActionBarDrawerToggle mDrawerToggle;
    //Toolbar
    AppCompatDelegate delegate;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create the delegate
        delegate = AppCompatDelegate.create(this, this);

        //call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        initializeTB();
        initializeFM();
        initializeNavDrw();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.openNavDraw) {
            Log.i("Settings", "OPEN SETTINGS");
            result.openDrawer();
            return true;
        }

        return  super.onOptionsItemSelected(item);
    }

    public void initializeFM(){

        fm = getSupportFragmentManager();

        // Initializig Fragments
        profile = new Profile_Fragment();
        itemDesc = new Item_Profile_Fragment();
        searchFrag = new Search_Fragment();
        historyFrag = new History_Fragment();

        fm.beginTransaction().add(R.id.fragmentContainer, profile, "home").commit();

       // fm.beginTransaction().add(R.id.fragmentContainer, itemDesc).commit();
    }


    private void initializeNavDrw() {

        final Toolbar toolbar = new Toolbar(this);

        toolbar.setTitle("ToolBar");


        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.accent_400)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Brennan Batalla")
                                .withEmail("brennan.batalla@gmail.com")
                                .withIcon(getResources().getDrawable(R.mipmap.ic_person_grey600_24dp))


                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_LONG).show();

                        return false;
                    }
                })

                .build();

//create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
             //   .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .withDrawerWidthDp(200)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withTag("home"),
                        new PrimaryDrawerItem().withName("Search").withTag("search"),
                        new PrimaryDrawerItem().withName("My History").withTag("history"),
                        new PrimaryDrawerItem().withName("Payment Info").withTag("paymentInfo"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Settings").withTag("settings"),
                        new SecondaryDrawerItem().withName("Policies").withTag("policies")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch (drawerItem.getTag().toString()) {
                            case "home":
                                if (!profile.isVisible()) {
                                    //Pop all the back stack
                                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                    fm.beginTransaction().replace(R.id.fragmentContainer, profile).commit();

                                }else

                                break;
                            case "search":
                                if(!searchFrag.isVisible())
                                    fm.beginTransaction().replace(R.id.fragmentContainer, searchFrag).addToBackStack("toSearch").commit();
                                break;
                            case "history":
                                if(!searchFrag.isVisible())
                                    fm.beginTransaction().replace(R.id.fragmentContainer, historyFrag).addToBackStack("toHistory").commit();

                                break;
                            default:
                                Toast.makeText(getApplicationContext(), drawerItem.getTag().toString(), Toast.LENGTH_LONG).show();

                        }
                        result.closeDrawer();
                        return true;
                    }
                })
                .withActionBarDrawerToggle(true)
                .build();

        mDrawerToggle = new ActionBarDrawerToggle(
                this,  result.getDrawerLayout(), toolbar,
                R.string.opendrawer, R.string.closedrawer
        );



    }

    public void initializeTB(){



        //use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_main);

        //add the Toolbar
        toolbar= (Toolbar) findViewById(R.id.my_awesome_toolbar);
        delegate.setSupportActionBar(toolbar);


        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitleTextColor(getResources().getColor(R.color.offWhite));
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i("KEY DOwN Button", "KEY DOWN");
        if(keyCode == KeyEvent.KEYCODE_MENU){
            if(!result.isDrawerOpen())
                result.openDrawer();
            else
                result.closeDrawer();
        }


        //return  true;



        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

}

