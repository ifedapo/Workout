package workout.hfad.com.workout;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {

    //Adds the listener to the fragment
    private WorkoutListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] names = new String[Workout.WORKOUTS.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.WORKOUTS[i].getName();// Create a String array from of the workout names
        }


        // Create an array Adapter and get the context from the layout inflator
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (inflater.getContext(), android.R.layout.simple_list_item_1, names);

        setListAdapter(adapter);   //Bind the array adapter to the list view
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) { //This is called when the fragment gets attached to the activity.
        super.onAttach(activity);
        this.listener = (WorkoutListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);  // Tell the listener when an item in the listView is clicked
        }
    }
}
