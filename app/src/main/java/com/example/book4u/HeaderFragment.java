package com.example.book4u;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class HeaderFragment extends Fragment  {

    public HeaderFragment(){

    }

    public static HeaderFragment newInstance() {
        return new HeaderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false);


    }




        //return inflater.inflate(R.layout.fragment_header, container, false);


}
