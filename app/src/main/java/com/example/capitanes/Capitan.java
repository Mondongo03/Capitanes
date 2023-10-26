package com.example.capitanes;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.lifecycle.LiveData;

public class  Capitan {
    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            inicio(new CapitanListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            parar();
        }
    };
    interface CapitanListener {
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> mostrandoCapitanes;

    void inicio(CapitanListener capitanListener) {
        if (mostrandoCapitanes == null || mostrandoCapitanes.isCancelled()) {
            mostrandoCapitanes = scheduler.scheduleAtFixedRate(new Runnable() {
                int capitan = 13;
                int segundos = -1;

                @Override
                public void run() {
                    if (segundos<0) {
                        segundos = random.nextInt(3)+3;
                    }
                    segundos--;
                    capitanListener.cuandoDeLaOrden("Capitan" + capitan + ":" + (segundos == 0 ? "CAMBIO" : segundos));
                    if(segundos==0)capitan--;
                }
            }, 0, 1, SECONDS);
        }
    }

    void parar() {
        if (mostrandoCapitanes != null) {
            mostrandoCapitanes.cancel(true);
        }
    }
}