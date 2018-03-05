package com.example.LightSensor;

import com.example.wenduchuanganqi.R;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	SensorManager sm;
	Sensor mys;
	TextView txt1,txt2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sm=(SensorManager) this.getSystemService(SENSOR_SERVICE);
		mys=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		txt1=(TextView) this.findViewById(R.id.textView1);
		txt2=(TextView) this.findViewById(R.id.textView2);
		StringBuffer str=new StringBuffer();
		str.append("\n名称：");
		str.append(mys.getName());
		str.append("\n类型编号：");
		str.append(mys.getType());
		str.append("\n耗电量（mA）：");
		str.append(mys.getPower());
		str.append("\n测量最大范围：");
		str.append(mys.getMaximumRange());
		str.append("\n版本：");
		str.append(mys.getVersion());
		txt2.setText(str);
		txt2.setTextSize(25);
		
	}
private SensorEventListener mysel=new SensorEventListener(){

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] value=event.values;
		txt1.setText("\n光照强度是："+value[0]);
		txt1.setTextSize(25);}};

protected void onResume(){
	super.onResume();
	sm.registerListener(mysel, mys,SensorManager.SENSOR_DELAY_NORMAL);
}
protected void onPause(){
	super.onPause();
	sm.unregisterListener(mysel);
}

}
