package com.krrishshx.worldofq


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.krrishshx.worldofq.databinding.ActivityMainBinding
import com.krrishshx.worldofq.view_models.Main_vm
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    lateinit var binding: ActivityMainBinding
    private val vm: Main_vm by viewModels()

    lateinit var degree_list_adapter : ArrayAdapter<String>

    private  lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        mAuth = FirebaseAuth.getInstance()

        binding.spinnerSelectDegree.onItemSelectedListener = this

        binding.btnRegister.setOnClickListener {
            if(vm.check_entered_data(binding.etLoginName.text!!.toString()
                ,binding.etRollNo.text!!.toString(),binding.etSemNo.text!!.toString(),binding.etGmailId.text!!.toString(),binding.etLoginPassword.text!!.toString())){
                            register_a_new_user(binding.etLoginName.text.toString(),
                binding.etGmailId.text.toString(),
                binding.etRollNo.text.toString().toInt() ,
                binding.etSemNo.text.toString().toInt() ,
                binding.etLoginPassword.text.toString())

                binding.mainViewRegister.visibility = View.GONE
                binding.lottieLoadingView.visibility = View.VISIBLE

            }else{

                Toast.makeText(this,"wrong way to register",Toast.LENGTH_SHORT).show()
            }



        }


        binding.btnShowPassword.setOnClickListener {
            val selectionStart: Int = binding.etLoginPassword.getSelectionStart()
            val selectionEnd: Int = binding.etLoginPassword.getSelectionEnd()

            if (binding.etLoginPassword.getTransformationMethod() is PasswordTransformationMethod) {
                binding.etLoginPassword.setTransformationMethod(null)
            } else {
                binding.etLoginPassword.setTransformationMethod(PasswordTransformationMethod())
            }

            binding.etLoginPassword.setSelection(selectionStart, selectionEnd)
        }


    vm.get_all_degree_avail()

vm.toast_live_data.observe(this, Observer {
    Toast.makeText(this,"$it",Toast.LENGTH_SHORT).show()

    binding.lottieLoadingView.visibility = View.GONE
binding.lottieSuccessView.visibility = View.VISIBLE
    startActivity(Intent(this@MainActivity,HomeActivity::class.java))
    finish()
})


        vm.degree_list_live_data.observe(this, Observer {
            Log.d("debug:","got all the data ${it.toString()}")
            degree_list_adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,it)
            binding.spinnerSelectDegree.adapter = degree_list_adapter
        })



    }

    override fun onStart() {
        super.onStart()
        if(mAuth.currentUser !=null){
            Toast.makeText(this,"already login",Toast.LENGTH_SHORT).show()
        }
    }



    //this will be only called by admin
    fun register_a_new_user(name:String,id:String,roll:Int, semester : Int , password:String){

        if(id.equals("") || roll ==0){
            Toast.makeText(this, "error in id", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.equals("")){
            Toast.makeText(this, "password wrong", Toast.LENGTH_SHORT).show()
            return
        }

        if(semester <=0){
            Toast.makeText(this, "semester empty", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener {

            if (it.isSuccessful) {
                //store info in db

              var uid =  it.result.user?.uid.toString()

                vm.create_user(user_name = name, roll_no = roll.toString(), sem = semester.toString(), degree = vm.arr_degree_with_id.get(vm.degree_selected), gmail_id = uid)


            } else {
                binding.mainViewRegister.visibility = View.VISIBLE
                binding.lottieLoadingView.visibility = View.GONE

                Toast.makeText(this, "error in auth ${it.exception}", Toast.LENGTH_SHORT).show()
                Log.d("debug:","---> ${it.exception}")


            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        vm.degree_selected = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}