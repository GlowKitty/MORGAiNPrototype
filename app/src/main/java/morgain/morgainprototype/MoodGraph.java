package morgain.morgainprototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MoodGraph extends Fragment {
    GraphView graph;
    UserData ud;

    public MoodGraph() {
        // Required empty public constructor
    }
    public static MoodGraph newInstance() {
        MoodGraph fragment = new MoodGraph();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        graph = (GraphView) getView().findViewById(R.id.graph);
        ud = UserData.instantiate(getContext());
        ArrayList<Mood> moods = ud.getMoods();
        DataPoint[] data = new DataPoint[moods.size()];
        for (int i = 0; i < moods.size(); i++) {
            data[i] = new DataPoint(i, moods.get(i).getMoodValue());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        graph.addSeries(series);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_graph, container, false);
    }

}
