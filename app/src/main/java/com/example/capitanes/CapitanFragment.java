package com.example.capitanes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.capitanes.databinding.FragmentCapitanBinding;
import com.example.capitanes.CapitanViewModel;
import com.example.capitanes.databinding.FragmentCapitanBinding;

public class CapitanFragment extends Fragment {
    private FragmentCapitanBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentCapitanBinding.inflate(inflater, container, false)).getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CapitanViewModel capitanViewModel = new ViewModelProvider(this).get(CapitanViewModel.class);

        capitanViewModel.obtenerCapitan().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer capitan) {
                Glide.with(CapitanFragment.this).load(capitan).into(binding.capitan);
            }
        });

        capitanViewModel.obtenerSegundos().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String segundos) {
                if(segundos.equals("CAMBIO")){
                    binding.cambio.setVisibility(View.VISIBLE);
                } else {
                    binding.cambio.setVisibility(View.GONE);
                }
                binding.segundos.setText(segundos);
            }
        });
    }
}
