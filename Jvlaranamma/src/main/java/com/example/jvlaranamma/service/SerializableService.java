package com.example.jvlaranamma.service;

import android.content.Context;
import android.util.Log;

import com.example.jvlaranamma.MainActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by johanr on 2014-01-03.
 */
public abstract class SerializableService<T> {
    public abstract String getFilename();


    protected void save(T t) {

        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = MainActivity.getContext().openFileOutput(getFilename(), Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected T load() {
        ObjectInputStream objectInputStream = null;
        T data = null;
        try {
            FileInputStream fileInputStream = MainActivity.getContext().openFileInput(getFilename());
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = (T) objectInputStream.readObject();
        } catch (FileNotFoundException e1) {
            Log.i(SerializableService.class.getName(), "No old song list found on device.");
        } catch (IOException e) {
            Log.i(SerializableService.class.getName(), "No old song list found on device.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
