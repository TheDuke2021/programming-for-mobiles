package com.example.lab7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View layout = requireActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null);
        final EditText editText = layout.findViewById(R.id.editText);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Имя")
                .setMessage("Введите Ваше имя")
                .setView(layout)

                .setPositiveButton("Отправить", (dialogInterface, id) -> {
                    ((MyDialogFragmentListener) getActivity()).onReturnValue(editText.getText().toString());
                })

                .setNegativeButton("Завершить работу", (dialogInterface, i) -> {
                    getActivity().finish();
                })

                .setNeutralButton("Закрыть окно", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                })

                .create();

    }

    public interface MyDialogFragmentListener {
        void onReturnValue(String value);
    }

}
