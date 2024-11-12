package com.example.appagenda.model;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appagenda.R;

import java.util.Calendar;

public class ViewFragment extends Fragment {

    private TextView textViewScrollable;
    private String selectedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        Button botaoHoje = view.findViewById(R.id.buttonHoje);
        Button botaoOutroDia = view.findViewById(R.id.buttonOutroDia);
        textViewScrollable = view.findViewById(R.id.textViewScrollable);

        // Configuracao do botão hoje para exibir compromissos do dia atual
        botaoHoje.setOnClickListener(v -> showTodayAppointments());

        // Configuracao do botão outro dia para selecionar uma data
        botaoOutroDia.setOnClickListener(v -> showDatePicker());

        return view;
    }

    private void showTodayAppointments() {
        // Exibe compromissos do dia atual (teste)
        textViewScrollable.setText("Exibindo compromissos para hoje:\n09:00 - Reunião\n13:30 - Almoço com cliente\n15:00 - Revisão de projeto");
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    showAppointmentsForDate(selectedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void showAppointmentsForDate(String date) {
        // Exibição de compromissos para a data selecionada (teste)
        textViewScrollable.setText("Compromissos para dia " + date + ":\n10:00 - Check-up médico\n12:00 - Almoço com equipe\n16:00 - Revisão de orçamento");
        Toast.makeText(getActivity(), "Data selecionada: " + date, Toast.LENGTH_SHORT).show();
    }
}

