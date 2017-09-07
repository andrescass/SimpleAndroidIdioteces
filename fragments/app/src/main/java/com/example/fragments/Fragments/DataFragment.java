package com.example.fragments.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    private EditText textData;
    private Button btnSend;
    private DataListener callback;


    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Verify if context tha instances the fragment have the interface implemented
            callback = (DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " Should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);
        // Inflate the layout for this fragment

        textData = (EditText) view.findViewById(R.id.editTextData);
        btnSend = (Button) view.findViewById(R.id.buttonSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendText(textData.getText().toString());
            }
        });

        return view;
    }

    private void SendText (String text) {
        callback.SendData(text);
    }

    public interface DataListener {
        void SendData(String text);
    }

}
