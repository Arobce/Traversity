package com.roshan.traversity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Rohan on 1/23/2018.
 */

public class ToolsCurrencyConverterFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tools_currency_converter_fragment,container,false);

        String [] sourceCurrencyValues =
                {"Choose Source Currency","US Dollar","Euro","Nepalese Rupees","Indian Rupees"};
        String [] conversionCurrencyValues =
                {"Choose Currency to convert to", "US Dollar","Euro","Nepalese Rupees","Indian Rupees"};

        Spinner spinnerSourceCurrency = (Spinner) rootView.findViewById(R.id.spinnerSourceCurrency);
        Spinner spinnerConversionCurrency = (Spinner) rootView.findViewById(R.id.spinnerConversionCurrency);

        ArrayAdapter<String> adapterSource = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, sourceCurrencyValues);
        ArrayAdapter<String> adapterConverted = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, conversionCurrencyValues);

        adapterSource.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapterConverted.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerSourceCurrency.setAdapter(adapterSource);
        spinnerConversionCurrency.setAdapter(adapterConverted);

        return rootView;

    }
}
