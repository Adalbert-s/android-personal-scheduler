package com.example.appagenda.model;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.appagenda.R;


import java.util.Calendar;

public class EntryFragment extends Fragment {

    private EditText editTextDescription;
    private String date;
    private String time;
    private Button buttonDate;
    private Button buttonTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        buttonDate = view.findViewById(R.id.buttonDate);
        buttonTime = view.findViewById(R.id.buttonTime);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        Button buttonOk = view.findViewById(R.id.buttonOk);

        // Configuracao do botão de data
        buttonDate.setOnClickListener(v -> showDatePicker());

        // Configuracao do botão de hora
        buttonTime.setOnClickListener(v -> showTimePicker());

        // Configuracao do botão ok
        buttonOk.setOnClickListener(v -> saveAppointment());

        return view;
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    date = selectedDay + "/" + (selectedMonth  + 1) + "/" + selectedYear;
                    buttonDate.setText(date);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, selectedHour, selectedMinute) -> {
                    time = String.format("%02d:%02d", selectedHour, selectedMinute);
                    buttonTime.setText(time);
                },
                hour, minute, true);
        timePickerDialog.show();
    }

    private void saveAppointment() {
        String description = editTextDescription.getText().toString().trim();
        if (TextUtils.isEmpty(description) || date == null || time == null) {
            Toast.makeText(getActivity(), "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("SaveAppointment", "Compromisso salvo em: Data = " + date + ", Hora = " + time + ", Descricao = " + description);
            // Limpar os campos após o salvamento
            editTextDescription.setText("");
            buttonDate.setText("Data");
            buttonTime.setText("Hora");
            date = null;
            time = null;
        }
    }
}

