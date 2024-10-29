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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.appagenda.R;


import java.util.Calendar;

public class EntryFragment extends Fragment {

    private EditText editTextDescription;
    private String date;
    private String time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        Button buttonDate = view.findViewById(R.id.buttonDate);
        Button buttonTime = view.findViewById(R.id.buttonTime);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        Button buttonOk = view.findViewById(R.id.buttonOk);

        // Configura o botão de data
        buttonDate.setOnClickListener(v -> showDatePicker());

        // Configura o botão de hora
        buttonTime.setOnClickListener(v -> showTimePicker());

        // Configura o botão "Ok" para salvar o compromisso
        buttonOk.setOnClickListener(v -> saveAppointment());

        return view;
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, selectedYear, selectedMonth, selectedDay) -> date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear,
                year, month, day);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, selectedHour, selectedMinute) -> time = String.format("%02d:%02d", selectedHour, selectedMinute),
                hour, minute, true);
        timePickerDialog.show();
    }

    private void saveAppointment() {
        String description = editTextDescription.getText().toString().trim();
        if (TextUtils.isEmpty(description) || date == null || time == null) {
            Toast.makeText(getActivity(), "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            // Aqui você pode adicionar a lógica para salvar o compromisso
            Toast.makeText(getActivity(), "Compromisso salvo: " + time + " - " + description + " em " + date, Toast.LENGTH_SHORT).show();
            // Limpar os campos após o salvamento
            editTextDescription.setText("");
            date = null;
            time = null;
        }
    }
}

