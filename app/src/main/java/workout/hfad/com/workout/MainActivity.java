package workout.hfad.com.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity implements WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void itemClicked(long id) {

        //Get a reference to the frame layout that contains WorkoutDetailFragment
        View fragmentContainer = findViewById(R.id.fragment_container);
        if(fragmentContainer != null) { // This code would run only if the fragment container exists.
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();  //Start the fragment transaction
            details.setWorkout(id);
            ft.replace(R.id.fragment_container, details);  //Replace the fragment
            ft.addToBackStack(null);  //Add the fragment to the back stack
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);  //Set the animation for the transaction
            ft.commit();  //Commit the transaction
        }
        else{
            // If the frame layout doesnt exist, then the app must be running on a device with smaller screen
            // Starts the detail Activity, then pass the ID of the workout.

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);
        }
    }
}
