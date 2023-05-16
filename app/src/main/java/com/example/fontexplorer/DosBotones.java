package com.example.fontexplorer;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DosBotones extends DialogFragment {
    public DosBotones.OnDismissListener onDismissListener;

    public DosBotones() {
    }

    public static DosBotones newInstance(String param1, String param2) {
        DosBotones fragment = new DosBotones();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dos_botones, container, false);
        Button ruta = view.findViewById(R.id.ruta);
        ruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDismissListener != null) {
                    onDismissListener.onDismiss(DosBotones.this);
                }
                dismiss();
            }
        });
        Button review = view.findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = getArguments();
                Bundle args2 = new Bundle();
                if (args != null) {
                    Long fuente = args.getLong("fuente");
                    args2.putLong("fuente", fuente);
                    Review review = new Review();
                    review.setArguments(args2);
                    review.show(getChildFragmentManager(), "second_dialog");
                }
            }
        });
        return view;
    }

    public void setOnDismissListener(DosBotones.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public interface OnDismissListener {
        void onDismiss(DosBotones fragment);
    }
    @Override
    public void onStart() {
        super.onStart();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        getDialog().getWindow().setLayout((int) (width * 1), (int) (height * 0.09));
    }
}