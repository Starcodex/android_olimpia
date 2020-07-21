package com.starcodex.olimpia.ui.stepform.fragment

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stepstatus.*
import javax.inject.Inject

class StepStatusFragment() : Fragment() {

    @Inject
    lateinit var pagerControl: ActivityPagerControl

    private lateinit var wifiManager : WifiManager
    private lateinit var bluetoothManager : BluetoothManager


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        wifiManager = requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        bluetoothManager = requireActivity().applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_stepstatus, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonNext.setOnClickListener {
            pagerControl.updateWBStatus(parseStatus(checkWifiStatus.isChecked), parseStatus(checkBluetoothStatus.isChecked))
            pagerControl.nextPage()
        }
        buttonPrevious.setOnClickListener { pagerControl.previouspage() }

        checkWifiStatus.isChecked = wifiManager.isWifiEnabled
        checkBluetoothStatus.isChecked = bluetoothManager.adapter.isEnabled

    }

    fun parseStatus(status: Boolean) :String{
        return when(status){
            true -> "on"
            false -> "off"
        }
    }

    val WifiStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
            checkWifiStatus.isChecked = (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED)
        }
    }

    val BluetoothtatusReceiver = object :  BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val bluetoothExtra = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF)
            checkBluetoothStatus.isChecked = (bluetoothExtra == BluetoothAdapter.STATE_ON)
        }
    }

    override fun onStart() {
        super.onStart()
        val intentFilterWifi = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        requireActivity().registerReceiver(WifiStatusReceiver, intentFilterWifi)

        val intentFilterBt = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        requireActivity().registerReceiver(BluetoothtatusReceiver, intentFilterBt)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(WifiStatusReceiver)
        requireActivity().unregisterReceiver(BluetoothtatusReceiver)
    }
}