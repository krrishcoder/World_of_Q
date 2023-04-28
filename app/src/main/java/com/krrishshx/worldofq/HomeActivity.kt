package com.krrishshx.worldofq

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.krrishshx.worldofq.databinding.ActivityHomeBinding
import com.krrishshx.worldofq.frags.Frag_Profile
import com.krrishshx.worldofq.frags.Frag_question_display
import com.krrishshx.worldofq.frags.Frag_starterForQ
import com.krrishshx.worldofq.view_models.Home_vm
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),Frag_Profile.OnButtonClickListener  , OnNavigationItemSelectedListener , Frag_starterForQ.OnButtonStartTestClickListener{

    lateinit var binding:ActivityHomeBinding
    lateinit var   mOrientationListener : OrientationEventListener
    var flag_orien_changed =0
    private val vm:Home_vm by viewModels()
    lateinit var toggle:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)


        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)

        vm.get_profile_data()


        mOrientationListener = object : OrientationEventListener(this) {
            override fun onOrientationChanged(orientation: Int) {
                // Handle orientation change events here
                if (orientation == ORIENTATION_UNKNOWN) {
                    return
                }

                // Determine the current orientation of the device
                val currentOrientation = resources.configuration.orientation
                if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    // Handle portrait orientation
                    flag_orien_changed=-1
                    vm.orien_changed =-1

                } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // Handle landscape orientation
                    flag_orien_changed =-2
                    vm.orien_changed =-2

                }
            }
        }

        // Start the orientation listener

        // Start the orientation listener
        mOrientationListener.enable()

        vm.toast_live_data.observe(this, Observer {

            if(flag_orien_changed>=0){
                Toast.makeText(this,"$it",Toast.LENGTH_SHORT).show()
            }else{
                flag_orien_changed=0
            }

        })

        ///////////////////////////////////////////////////code - from here//////////////////////////////////////////////////////////////////


        //first fragment enters------------>
        supportFragmentManager.beginTransaction().apply {
            if(vm.orien_changed >=0) {
                Log.d("debug:","replacing the fragment")
                replace(binding.frameLayout.id, Frag_Profile())
                commit()
            }
        }







    }


    //--> when users clicks at a  subject from the list of subjects
    //--> than we add a fragment so that we can show details of that subject

    override fun onButtonClicked(position: Int, view: View?) {

       Log.d("debug:","adding fragment starter to stack")

        vm.get_topic_data(position)
        supportFragmentManager.beginTransaction().apply {
            add(binding.frameLayout.id,Frag_starterForQ())
            addToBackStack("frag starter for q")
            commit()
        }

    }




    override fun onBackPressed() {


     if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
         binding.drawerLayout.closeDrawer(GravityCompat.START)
     }else{
         //back pressed when i was giving test

         if(supportFragmentManager.backStackEntryCount==2){
             AlertDialog.Builder(this)
                 .setTitle("Confirm Exit Test")
                 .setMessage("Are you sure you want to finish the test here?")
                 .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                     vm.stop_timer()

                     supportFragmentManager.popBackStack()
                     Toast.makeText(this, "Test Submitted Automatically", Toast.LENGTH_SHORT).show()
                 })
                 .setNegativeButton("No", null)
                 .show()
         }else{
             Log.d("debug:","pops a fragment in HOMEACTIVITY")
             supportFragmentManager.popBackStack()
         }

         if(supportFragmentManager.backStackEntryCount==0){
              super.onBackPressed();
         }


         }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile ->{
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_logout ->{
                vm.Logout()
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
            else ->{
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }


    override fun onTestStartClicked(topic: String) {

        Log.d("debug:","adding a frag question display in stack")
        supportFragmentManager.beginTransaction().apply {
            add(binding.frameLayout.id,Frag_question_display())
            addToBackStack("frag question display")
            commit()
        }

    }


}