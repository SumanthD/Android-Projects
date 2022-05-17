package com.example.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.example.movieclient.movieInfo;

public class movieService extends Service {

    public movieService(){}

    List<String> movies = Arrays.asList("Good Will Hunting", "Schindler's List", " Inglourious Basterds ", "City of God", "Goodfellas");
    List<String> directors = Arrays.asList("Gus Van Sant", "Steven Spielberg", "Quentin Tarantino", "Fernando Meirelles", "Martin Scorsese");
    List<String> url = Arrays.asList("https://www.youtube.com/watch?v=oRG2jlQWCsY", "https://www.youtube.com/watch?v=W9vj2Wf57rQ", "https://www.youtube.com/watch?v=coS2CdNd7Io", "https://www.youtube.com/watch?v=1m5gxQh5jeo", "https://www.youtube.com/watch?v=yL9rSwrsMHw");

    private final static Set<UUID> mIDs = new HashSet<UUID>();

    // Implement the Stub for this Object
    private final movieInfo.Stub mBinder = new movieInfo.Stub() {


        @Override
        public Map getall() throws RemoteException {
            Map out = new HashMap();
            synchronized (out) {
                out.put("Titles",movies);
                out.put("Directors", directors);
                out.put("Urls", url);
            }
            return out;
        }

        @Override
        public Map getone(int number) throws RemoteException {
            Map out = new HashMap();
            synchronized (out) {
                out.put("Titles",movies.get(number));
                out.put("Directors", directors.get(number));
                out.put("Urls", url.get(number));
            }
            return out;
        }

        @Override
        public String getUrl(int number) throws RemoteException {
            return url.get(number);
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


}

