package btech.pakt;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import btech.pakt.fragments.Profile;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Fragment Manager and Fragments
    FragmentManager fm;
    Profile profile;

    // NavDrawer
    private DrawerLayout DL;
    private ListView LV;
    String[] navDrawOptions;
    ActionBarDrawerToggle actionbarListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeFM(){
        profile = new Profile();
        fm = getFragmentManager();

        fm.beginTransaction().add(R.id.fragmentContainer, profile).commit();
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
        Drawer result = new DrawerBuilder()
                .withActivity(this)
             //   .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .withDrawerWidthDp(200)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home"),
                        new PrimaryDrawerItem().withName("Search"),
                        new PrimaryDrawerItem().withName("My History"),
                        new PrimaryDrawerItem().withName("Payment Info"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Settings"),
                        new SecondaryDrawerItem().withName("Policies")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Toast.makeText(getApplicationContext(), drawerItem.toString(),Toast.LENGTH_LONG).show();

                        return true;
                    }
                })

                .build();

//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
