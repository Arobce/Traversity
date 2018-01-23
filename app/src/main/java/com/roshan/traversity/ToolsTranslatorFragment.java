package com.roshan.traversity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Rohan on 1/23/2018.
 */

public class ToolsTranslatorFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tools_translator_fragment, container, false);

        String [] sourceLanguageValues =
                {"Choose Source Language","English","Spanish","German","Italian","French","Nepali"};
        String [] translationLanguageValues =
                {"Choose Language to Translate to", "English","Spanish","German","Italian","French","Nepali"};

        Spinner spinnerSourceLanguage = (Spinner) rootView.findViewById(R.id.spinnerSourceLanguage);
        Spinner spinnerTranslationLanguage = (Spinner) rootView.findViewById(R.id.spinnerTranslationLanguage);

        ArrayAdapter<String> adapterSource = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, sourceLanguageValues);
        ArrayAdapter<String> adapterTranslation = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, translationLanguageValues);

        adapterSource.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapterTranslation.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerSourceLanguage.setAdapter(adapterSource);
        spinnerTranslationLanguage.setAdapter(adapterTranslation);

        return rootView;
    }
}

