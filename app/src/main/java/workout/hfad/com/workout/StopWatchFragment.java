package workout.hfad.com.workout;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchFragment extends Fragment implements View.OnClickListener {
    //Numbers of seconds running
    private int seconds = 0;
    // Is the stopwatch running?
    private boolean running;
    // Says whether the stopwatch was running before the stopwatch was paused
    private boolean wasRunning;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            // Restore the state of the variable from the saved instance state
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if(wasRunning){
                running = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);

        Button startButton = layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);    // This attaches the listener to the button
        Button stopButton = layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);    // This attaches the listener to the button
        Button resetButton = layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);  // This attaches the listener to the button
        return layout;
    }

    @Override
    public void onPause(){
        super.onPause();
        wasRunning = running;    // If the fragment paused, record whether the stopWatch was
        running = false;          // and stop it.
    }

    @Override
    public  void onResume(){
        super.onResume();
        if(wasRunning){
            running = true; // If the stopwatch was running before it was paused, set it running again
        }
    }

    @Override
    public  void onSaveInstanceState(Bundle savedInstanceState){
        // This are used when the device is turned. Put the values of the variables used in the
        // Bundle before the activity is destroyed.
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
        savedInstanceState.putBoolean("running",running);
    }

    public void onClickStart(View view){
       running = true; // This code runs when the user clicks the start button.

    }

    public void onClickStop(View view){
        running = false;  // This code runs when the user clicks the stop button.
    }

    public void onClickReset(View view){
        running = false;    // This code runs when the user clicks the reset button.
        seconds = 0;
    }

    private void runTimer(View view){
        final TextView timeView = view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) /60;
                int secs = seconds % 60;

                String time = String.format("%d:%02d:%02d", hours,minutes,secs);
                timeView.setText(time); //Displays the number of seconds that have passed in the stopwatch

                if(running){
                    seconds++;  //if the stopwatch is running, increment the number of seconds.
                }

                handler.postDelayed(this,1000);  //Run this handler every seconds.
            }
        });
    }

    @Override
    public void onClick(View v) {  //This is the View the user clicked on.
        switch (v.getId()){       // Check the View that was clicked.
            case R.id.start_button:
                onClickStart(v);   // If the Start Button was clicked, call the onStart() method.
                break;
            case R.id.stop_button:
                onClickStop(v);    // If the Stop Button was clicked, call the onStop() method.
                break;
            case R.id.reset_button:
                onClickReset(v);    // If the Reset Button was clicked, call the onReset() method.
                break;
        }
    }
}
