

package eg.iti.pillsmanager.EditDrug;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import eg.iti.pillsmanager.R;
import eg.iti.pillsmanager.addDrug.view.AddMedActivity;
import eg.iti.pillsmanager.database.LocalSource;
import eg.iti.pillsmanager.model.Dose;
import eg.iti.pillsmanager.model.Medicine;
import eg.iti.pillsmanager.model.Repository;
import eg.iti.pillsmanager.model.RepositoryInterface;
import eg.iti.pillsmanager.network.RemoteSource;


public class EditMedFragmentOne extends Fragment {

    TextView name_of_med,form_of_med,reason_of_med,strength_of_med;
    EditText name_med_value,reason_med_value;
    Spinner strength_num,strength_unit,form_med_value;
   Button next_btn;
   Bundle bundle;
    RepositoryInterface repo;
    RemoteSource remoteSource;
      Medicine med;
    LocalSource localSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_edit_med_first, container, false);
        next_btn = view.findViewById(R.id.btn_next_add_second);
        //////////////////////////////////////////////////////////////
        name_of_med = view.findViewById(R.id.name_of_med);
        form_of_med = view.findViewById(R.id.form_of_med);
        reason_of_med = view.findViewById(R.id.reason_of_med);
        strength_of_med = view.findViewById(R.id.strength_of_med);
        /////////////////////////////////////////////////////////////////////////
        name_med_value=view.findViewById(R.id.value_of_name_med);
        reason_med_value=view.findViewById(R.id.value_of_reason_med);
        form_med_value=view.findViewById(R.id.form_of_med_value);
        ///////////////////////////////////////////////////////////
        strength_num=view.findViewById(R.id.med_concentration_num);
        strength_unit=view.findViewById(R.id.med_concentration_symbol);
        String[] arraySpinner = new String[] {
                "100", "200", "300", "400", "500", "600", "700"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        strength_num.setAdapter(adapter);
        TextView textView = (TextView)strength_num.getSelectedView();
        String result = textView.getText().toString();
        /////////////////////////////////////
        strength_unit=view.findViewById(R.id.med_concentration_symbol);
        String[] arraySpinner2 = new String[] {
                "g", "IU", "mcg"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        strength_unit.setAdapter(adapter2);

        String[] arraySpinner3 = new String[] {
                "pills", "solution", "injection","powder","drops","Inhaler"
        };
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        form_med_value.setAdapter(adapter3);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //get data from edit
                 med = new Medicine();
                bundle = new Bundle();
                repo= Repository.getInstance(remoteSource,localSource,getContext());
                repo.getStoredMedicines().observe(getActivity(), new Observer<List<Medicine>>() {
                    @Override
                    public void onChanged(List<Medicine> medicines) {
                        med = medicines.get(0);
                    }
                });
              /* SharedPreferences  sharedPreferences = getActivity().getSharedPreferences(getString(R.string.signup_shared_pre), Context.MODE_PRIVATE);

               String name= sharedPreferences.getString(getString(R.string.name_shared_pre),"name is null");
                Log.i("TAG", "onClick: "+name);
               med.setUser(new User(name,"khaled",0,"","","",""));*/

                med.setFirstName("esraa");

                med.setMedicineName(name_med_value.getText().toString());
                med.setForm(form_med_value.getSelectedItem().toString());
                med.setReason(reason_med_value.getText().toString());
                med.setStrengthValue(Integer.parseInt(strength_num.getSelectedItem().toString()));
                med.setStrength(strength_unit.getSelectedItem().toString());
                med.setActive(true);

                bundle.putSerializable("med1",med);

                Edit_medActivity.showFragmentSecond(bundle);
            }
        });


        return view;
    }
}