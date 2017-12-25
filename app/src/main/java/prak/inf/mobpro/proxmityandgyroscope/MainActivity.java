package prak.inf.mobpro.proxmityandgyroscope;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        //final Sensor proximitySensor =
                //sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        final Sensor gyroscopeSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        /*if(proximitySensor == null) {
            Log.e(TAG, "Proximity sensor not available.");
            finish(); // Close app
        }*/

 //       SensorEventListener proximitySensorListener = new SensorEventListener() {
 //           @Override
 //           public void onSensorChanged(SensorEvent sensorEvent) {
 //               if(sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
 //                   // Detected something nearby
 //                   getWindow().getDecorView().setBackgroundColor(Color.RED);
 //               } else {
                    // Nothing is nearby
 //                   getWindow().getDecorView().setBackgroundColor(Color.GREEN);
 //               }
 //           }

 //           @Override
 //           public void onAccuracyChanged(Sensor sensor, int i) {
 //           }
 //       };

        SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register the listener
        sensorManager.registerListener(gyroscopeSensorListener,
                gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
// Register it, specifying the polling interval in
// microseconds
        //sensorManager.registerListener(proximitySensorListener,
//                proximitySensor, 2 * 1000 * 1000);

    }
}
