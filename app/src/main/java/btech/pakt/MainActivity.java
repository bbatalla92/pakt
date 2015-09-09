package btech.pakt;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import btech.pakt.fragments.Item_Profile_Fragment;
import btech.pakt.fragments.Profile;
import btech.pakt.fragments.Search_Fragment;

public class MainActivity extends Activity{

    // Fragment Manager and Fragments
    FragmentManager fm;
    Profile profile;
    Item_Profile_Fragment itemDesc;
    Search_Fragment searchFrag;

    // NavDrawer
    Drawer result;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeTB();
        initializeFM();
        initializeNavDrw();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeFM(){

        fm = getFragmentManager();

        // Initializig Fragments
        profile = new Profile();
        itemDesc = new Item_Profile_Fragment();
        searchFrag = new Search_Fragment();


        fm.beginTransaction().add(R.id.fragmentContainer, profile, "home").commit();

       // fm.beginTransaction().add(R.id.fragmentContainer, itemDesc).commit();
    }


    private void initializeNavDrw() {

        Toolbar toolbar = new Toolbar(this);

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
                                    Log.i("Pressed Home", "Home is visible");


                                break;
                            case "search":
                                if(!searchFrag.isVisible())
                                fm.beginTransaction().replace(R.id.fragmentContainer, searchFrag).addToBackStack("toSearch").commit();
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

        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.offWhite));


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


       // Log.i("Menu Button", "Going Through");
        if(keyCode == KeyEvent.KEYCODE_MENU){
            if(!result.isDrawerOpen())
                result.openDrawer();
            else
                result.closeDrawer();
        }

        return super.onKeyDown(keyCode, event);
    }
}

