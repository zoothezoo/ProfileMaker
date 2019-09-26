package jp.co.cyberagent.dojo2019.ui.MainActivity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.activity_main.*


const val LABEL_TEXT_KEY = "labelText"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.DarkThemeApp)
        setContentView(R.layout.activity_main)

        //val pager :ViewPager = this.findViewById(R.id.viewpager_main)
        //val fragmentManager : FragmentManager = supportFragmentManager
        //val viewadapter = MainViewPagerAdapter(fragmentManager)
        //pager.adapter = viewadapter

        val fragmentAdapter = MainViewPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        viewpager_main.offscreenPageLimit = 1
        tab_main.setupWithViewPager(viewpager_main)

        //var nightflag = false
        //nightbutton.setOnClickListener {
        //    val mode = if(!nightflag){
        //        nightflag = true
        //        AppCompatDelegate.MODE_NIGHT_YES
        //    } else {
        //        nightflag = false
        //        AppCompatDelegate.MODE_NIGHT_NO
        //    }
        //    AppCompatDelegate.setDefaultNightMode(mode)
        //}

        var mode: Int = AppCompatDelegate.MODE_NIGHT_NO
        nightbutton.setOnClickListener {
            var currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (currentNightMode) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    mode = AppCompatDelegate.MODE_NIGHT_YES
                } // Night mode is not active, we're using the light theme
                Configuration.UI_MODE_NIGHT_YES -> {
                    mode = AppCompatDelegate.MODE_NIGHT_NO
                } // Night mode is active, we're using dark theme
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }


        //初期画面: 友人一覧フラグメント

        //QR表示はinput画面でやろうかな

        //val inputfragment = inputAccountFragment()
        //val inputtransaction = supportFragmentManager.beginTransaction()
        //inputtransaction.replace(R.id.friendcontainer,inputfragment)
        //inputtransaction.commit()

        //qrbutton.setOnClickListener{
        //    val fragment = inputAccountFragment()
        //    val transaction = supportFragmentManager.beginTransaction()
        //    //transaction.add(R.id.qrcontainer,fragment)
        //    transaction.replace(R.id.friendcontainer,fragment)
        //    transaction.commit()
        //}

        //fributton.setOnClickListener{
        //    val fragment = FriendsFragment()
        //    val transaction = supportFragmentManager.beginTransaction()
        //    //transaction.add(R.id.friendcontainer,fragment)
        //    transaction.replace(R.id.friendcontainer,fragment)
        //    transaction.commit()
        //}


    }
}
