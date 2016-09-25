package com.runzii.traveling;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;
import com.mikepenz.octicons_typeface_library.Octicons;
import com.runzii.traveling.fragments.RecyclerViewFragment;

import java.util.Random;

/**
 * Created by Wouldyou on 2015/6/4.
 */
public class CityActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;

    //save our header or result
    private AccountHeader.Result headerResult = null;
    private Drawer.Result result = null;
    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        //it's a sample ViewPagerAdapter
        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            int oldPosition = -1;
            String[] cities = {"秦皇岛", "北京", "杭州", "伊犁", "丽江", "大理", "凤凰", "厦门", "拉萨", "桂林"};

            @Override
            public Fragment getItem(int position) {
                return RecyclerViewFragment.newInstance(cities[position]);
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);

                //only if position changed
                if (position == oldPosition)
                    return;
                oldPosition = position;

                Random random = new Random();
                int color = 0xff000000 | random.nextInt(0x00ffffff);
                String imageUrl = "";
                switch (position) {
                    case 0:
                        imageUrl = "http://img1.58.com/groupbuy/n_s12275644173422709133%2120811748818944.jpg";
                        break;
                    case 1:
                        imageUrl = "http://www.izmzg.com/empirecms/d/file/zmzg/beijing/jingdian/2012-03-16/a60ca4bef8ab6c4343bc486a548610ea.jpg";
                        break;
                    case 2:
                        imageUrl = "http://img.kpkpw.com/201106/27/14696_1309186930X2Mt.jpg";
                        break;
                    case 3:
                        imageUrl = "http://www.cnwest88.com/uploadfile/2012/0509/20120509094628310.jpg";
                        break;
                    case 4:
                        imageUrl = "http://www.nmghuana.com/UploadFiles/2014-03/nmghuana/2014032610591940146.jpg";
                        break;
                    case 5:
                        imageUrl = "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1107/30/c0/8490657_8490657_1311991396343.jpg";
                        break;
                    case 6:
                        imageUrl = "http://www.ctps.cn/PhotoNet/Profiles/BLUE%E6%9D%B0/2009326130172166.jpg";
                        break;
                    case 7:
                        imageUrl = "http://f.hiphotos.bdimg.com/album/w%3D2048/sign=f28844ce7e3e6709be0042ff0fff9e3d/962bd40735fae6cd2e8713880eb30f2442a70f23.jpg";
                        break;
                    case 8:
                        imageUrl = "http://www.cdtianya.com/baike/uploads/201303/1363830542ySdkzSVt.jpg";
                        break;
                    case 9:
                        imageUrl = "http://c.hiphotos.bdimg.com/album/w%3D2048/sign=e14035a13812b31bc76cca29b220347a/63d0f703918fa0ec9a37cdeb279759ee3c6ddb60.jpg";
                        break;
                }

                final int fadeDuration = 400;
                mViewPager.setImageUrl(imageUrl, fadeDuration);
                mViewPager.setColor(color, fadeDuration);

            }

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return cities[position];
            }
        });

        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager().setCurrentItem(1);

        setUpDrawer(savedInstanceState);
    }

    private void setUpDrawer(Bundle savedInstanceState) {

        final IProfile profile = new ProfileDrawerItem().withName("Runzii Mo")
                .withEmail("806478101@qq.com").withIcon(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.default_photo));

        // Create the AccountHeader
        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Yiying").withEmail("961595299@qq.com").withIcon(getResources().getDrawable(R.drawable.default_photo2));
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar).withStatusBarColor(Color.parseColor("#00000000")).withTranslucentNavigationBar(true).withTranslucentNavigationBarProgrammatically(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("地图").withIcon(GoogleMaterial.Icon.gmd_style).withIdentifier(1).withCheckable(false),
                        new DividerDrawerItem(),
                        new SwitchDrawerItem().withName("接受推送").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener),
                        new SwitchDrawerItem().withName("声音").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(CityActivity.this, FullscreenDrawerActivity.class);
                            }
                            if (intent != null) {
                                CityActivity.this.startActivity(intent);
                            }
                        }
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();


        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 10
            result.setSelectionByIdentifier(10, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
