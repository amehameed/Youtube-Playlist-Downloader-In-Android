package com.example.fyp.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp.*
import com.example.fyp.Fragment.DownloadFragment
import com.example.fyp.Fragment.FavouriteFragment
import kotlinx.android.synthetic.main.activity_home3.*

class HomeActivity2 : AppCompatActivity()  {
    public lateinit var downloadFragment: DownloadFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home3)
        bottomBar.onItemSelected = {
//            Toast.makeText(applicationContext,""+it,Toast.LENGTH_SHORT)
            Log.e("Seleccted",""+it)
            val manager = supportFragmentManager
            downloadFragment= DownloadFragment()
            if(it==0)
            {
                val transaction = manager.beginTransaction()

                // Replace the fragment on container
                transaction.replace(R.id.mainfragment, FragmentHome())
                transaction.addToBackStack(null)

                // Finishing the transition
                transaction.commit()
            }
            else if (it==1)
            {
                startActivity(Intent(this, DownloadActivity::class.java))
//                val transaction = manager.beginTransaction()
//
//                // Replace the fragment on container
//                transaction.replace(R.id.mainfragment,downloadFragment)
//                transaction.addToBackStack(null)
//
//                // Finishing the transition
//                transaction.commit()
            }
            else
            {
                val transaction = manager.beginTransaction()

                // Replace the fragment on container
                transaction.replace(R.id.mainfragment, FavouriteFragment())
                transaction.addToBackStack(null)

                // Finishing the transition
                transaction.commit()
            }

        }

        bottomBar.onItemReselected = {

        }
        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()

        // Replace the fragment on container
        transaction.replace(R.id.mainfragment, FragmentHome())
        transaction.addToBackStack(null)

        // Finishing the transition
        transaction.commit()
        // Initialize the action bar drawer toggle instance



        // Set navigation view navigation item selected listener
        nav_view2.setNavigationItemSelectedListener{
            when (it.itemId){

                R.id.nav_setting ->{
                    // Multiline action
                    startActivity(Intent(this, SettingActivity::class.java))
                    drawer_id.closeDrawers()
                }
                R.id.nav_about ->{
                    // Multiline action
                    startActivity(Intent(this, AboutActivity::class.java))
                    drawer_id.closeDrawers()
                }
                R.id.exit ->{
                    finishAffinity()
                    System.exit(0)
                }


            }
            // Close the drawer

            true
        }
    }

    fun UpdateUI()
    {
        downloadFragment.UpdateUI()
    }
    var listener: Listener? = null;
    interface Listener {
        fun UpdateUI(status: String)
    }
}
