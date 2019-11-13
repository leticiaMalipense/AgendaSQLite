package br.edu.ifsp.agendasqlite.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import br.edu.ifsp.agendasqlite.R;

public class DatePickeFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR) - 25;

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        EditText edtData = getActivity().findViewById(R.id.editTextDate);
        edtData.setText(day + "/" + month + "/" + year);
        edtData.setError(null);//removes error
        edtData.clearFocus();
    }
}
