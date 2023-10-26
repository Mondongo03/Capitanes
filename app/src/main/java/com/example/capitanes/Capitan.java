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

                String nombreCapitanes;

                @Override
                public void run() {
                    if (segundos<0) {
                        segundos = random.nextInt(3)+3;
                    }
                    switch (capitan){
                        case 13: nombreCapitanes = "Rukia Kuchiki"; break;
                        case 12: nombreCapitanes = "Mayuri Kurotsuchi"; break;
                        case 11: nombreCapitanes = "Kenpachi Zaraki"; break;
                        case 10: nombreCapitanes = "Tōshirō Hitsugaya"; break;
                        case 9: nombreCapitanes = "Kensei Muguruma"; break;
                        case 8: nombreCapitanes = "Lisa Yadōmaru"; break;
                        case 7: nombreCapitanes = "Tetsuzaemon Iba"; break;
                        case 6: nombreCapitanes = "Byakuya Kuchiki"; break;
                        case 5: nombreCapitanes = "Shinji Hirako"; break;
                        case 4: nombreCapitanes = "Isane Kotetsu"; break;
                        case 3: nombreCapitanes = "Rōjūrō Ōtoribashi"; break;
                        case 2: nombreCapitanes = "Suì-Fēng"; break;
                        case 1: nombreCapitanes = "Syunsui Kyōraku"; break;

                    }
                    segundos--;
                    capitanListener.cuandoDeLaOrden("Capitan" + capitan + ":" + (segundos == 0 ? "CAMBIO" : "Capitan del escuadron Nº"+capitan+" "+nombreCapitanes));
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