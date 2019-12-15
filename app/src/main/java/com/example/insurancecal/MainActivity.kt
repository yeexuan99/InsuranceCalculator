package com.example.insurancecal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: MyDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(MyDataModel::class.java)

        if(myData.totalPremium != 0) {
            txtTotal.text = String.format("RM%d", myData.totalPremium)
        }
        btnCal.setOnClickListener{
            myData.totalPremium = calcInsurance()
            txtTotal.text = String.format("RM%d",myData.totalPremium)
        }

        btnReset.setOnClickListener{
            spinner.setSelection(0)
            grpGender.clearCheck()
            txtTotal.setText("")
            myData.totalPremium = 0
            checkSmoke.setChecked(false)
        }
    }

    fun calcInsurance(): Int{

        var age: Int = spinner.selectedItemPosition
        var gender = btnMale.isChecked
        var smoker = checkSmoke.isChecked

        var premium:Int = 0

        if(age == 0){
            //below 17 years old
            premium += 60
        }
        else if (age == 1) {
            //17 - 25
            premium += 70
            if(gender == true){
                premium+=50
            }
            if(smoker == true){
                premium+=100
            }
        }
        else if (age == 2) {
            //26 - 30
            premium += 90
            if (gender == true) {
                premium += 100
            }
            if (smoker == true) {
                premium += 150
            }
        }
        else if (age == 3) {
            //31 - 40
            premium += 120
            if (gender == true) {
                premium += 150
            }
            if (smoker == true) {
                premium += 200
            }
        }
        else if (age == 4) {
            //41 - 55
            premium += 150
            if (gender == true) {
                premium += 200
            }
            if (smoker == true) {
                premium += 250
            }
        }
        else if (age == 5) {
            //more than 55
            premium += 150
            if (gender == true) {
                premium += 200
            }
            if (smoker == true) {
                premium += 300
            }
        }

        //txtTotal.text = String.format("RM%d",premium)
        return premium
    }
}
