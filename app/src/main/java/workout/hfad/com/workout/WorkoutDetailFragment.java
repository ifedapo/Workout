package workout.hfad.com.workout;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    //This is the Id of the workout the user chooses
    private long workoutId;
    private static final String WORKOUT_ID_KEY = "work.out.id.key";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong(WORKOUT_ID_KEY);
        } else {
            // Use the fragment Transaction to add the stopwatch fragment to the frame layout
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            StopWatchFragment stopWatchFragment = new StopWatchFragment();
            ft.replace(R.id.stopwatch_container, stopWatchFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView title = view.findViewById(R.id.textTile);
            Workout workout = Workout.WORKOUTS[(int) workoutId];
            title.setText(workout.getName());
            TextView description = view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(WORKOUT_ID_KEY, workoutId);
    }

    // This is a setter method for the workout ID. The activity will use
    // the method to set the value of the id
    public void setWorkout(long id) {
        this.workoutId = id;
    }

}
