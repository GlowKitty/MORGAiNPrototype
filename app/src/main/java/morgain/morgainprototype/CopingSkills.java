package morgain.morgainprototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class CopingSkills extends Fragment {


    public CopingSkills() {
        // Required empty public constructor
    }
    public static CopingSkills newInstance() {
        CopingSkills fragment = new CopingSkills();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TabHost host = (TabHost) getView().findViewById(R.id.tab_host);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Cope 1");
        spec.setContent(R.id.tab1);
        getFragmentManager().beginTransaction().add(R.id.tab1, CopeOne.newInstance()).commit();
        spec.setIndicator("Cope 1");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Cope 2");
        spec.setContent(R.id.tab2);
        getFragmentManager().beginTransaction().add(R.id.tab2, CopeTwo.newInstance()).commit();
        spec.setIndicator("Cope 2");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Cope 3");
        spec.setContent(R.id.tab3);
        getFragmentManager().beginTransaction().add(R.id.tab3, CopeThree.newInstance()).commit();
        spec.setIndicator("Cope 3");
        host.addTab(spec);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coping_skills, container, false);
    }

}
