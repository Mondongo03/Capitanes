package com.example.capitanes;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import kotlin.jvm.functions.Function1;

public class CapitanViewModel extends AndroidViewModel {
    Capitan capitan;

    LiveData<Integer> ejercicioLiveData;
    LiveData<String> repeticionLiveData;

    public CapitanViewModel(@NonNull Application application) {
        super(application);

        capitan = new Capitan();

        ejercicioLiveData = (LiveData<Integer>) Transformations.switchMap(capitan.ordenLiveData, new Function1<String, LiveData<Integer>>() {

            String ejercicioAnterior;

            @Override
            public LiveData<Integer> invoke(String orden) {

                String ejercicio = orden.split(":")[0];


                    int imagen;
                    switch (ejercicio) {
                        case "Capitan13":
                        default:
                            imagen = R.drawable.c13;
                            break;
                        case "Capitan12":
                            imagen = R.drawable.c12;
                            break;
                        case "Capitan11":
                            imagen = R.drawable.c11;
                            break;
                        case "Capitan10":
                            imagen = R.drawable.c10;
                            break;
                        case "Capitan9":
                            imagen = R.drawable.c9;
                            break;
                        case "Capitan8":
                            imagen = R.drawable.c8;
                            break;
                        case "Capitan7":
                            imagen = R.drawable.c7;
                            break;
                        case "Capitan6":
                            imagen = R.drawable.c6;
                            break;
                        case "Capitan5":
                            imagen = R.drawable.c5;
                            break;
                        case "Capitan4":
                            imagen = R.drawable.c4;
                            break;
                        case "Capitan3":
                            imagen = R.drawable.c3;
                            break;
                        case "Capitan2":
                            imagen = R.drawable.c2;
                            break;
                        case "Capitan1":
                            imagen = R.drawable.c1;
                            break;

                    }

                    return new MutableLiveData<>(imagen);

            }
        });

        repeticionLiveData = (LiveData<String>) Transformations.switchMap(capitan.ordenLiveData, new Function1<String, LiveData<String>>() {
            @Override
            public LiveData<String> invoke(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    public LiveData<Integer> obtenerCapitan() {
        return ejercicioLiveData;
    }

    public LiveData<String> obtenerSegundos() {
        return repeticionLiveData;
    }
}
