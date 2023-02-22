package com.example.lab8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void setSelectedItem(String selectedItem) {
        TextView desc = getView().findViewById(R.id.detailsText);
        ImageView photo = getView().findViewById(R.id.photo);
        switch (selectedItem) {
            case "Космическая Одиссея":
                desc.setText(R.string.odyssey);
                photo.setImageResource(R.drawable.odyssey);
                break;
            case "Чужой":
                desc.setText(R.string.alien);
                photo.setImageResource(R.drawable.alien);
                break;
            case "Аватар":
                desc.setText(R.string.avatar);
                photo.setImageResource(R.drawable.avatar);
                break;
            case "Назад в будущее":
                desc.setText(R.string.bttf);
                photo.setImageResource(R.drawable.bttf);
                break;
            case "Большой Лебовски":
                desc.setText(R.string.big_lebowski);
                photo.setImageResource(R.drawable.big_lebowski);
                break;
            case "Кабинет доктора Калигари":
                desc.setText(R.string.caligari);
                photo.setImageResource(R.drawable.caligari);
                break;
        }
    }
}