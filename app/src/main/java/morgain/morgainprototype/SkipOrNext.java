package morgain.morgainprototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SkipOrNext extends Fragment {
    private Button skip;
    private Button next;
    private MainActivity m;

    public SkipOrNext() {
        // Required empty public constructor
    }

    public static SkipOrNext newInstance(MainActivity m) {
        SkipOrNext fragment = new SkipOrNext();
        fragment.setMainActivity(m);
        return fragment;
    }

    private void setMainActivity(MainActivity m) {
        this.m = m;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        skip = (Button) getView().findViewById(R.id.skip);
        next = (Button) getView().findViewById(R.id.next_q);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.goToQuestions();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skip_or_next, container, false);
    }
}