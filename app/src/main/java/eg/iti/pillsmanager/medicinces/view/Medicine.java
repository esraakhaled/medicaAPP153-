package eg.iti.pillsmanager.medicinces.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import eg.iti.pillsmanager.R;

public class Medicine extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View in =inflater.inflate(R.layout.fragment_medicine, container, false);

        return in;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }
}
